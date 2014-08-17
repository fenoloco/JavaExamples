package org.test.apachecommonpooltest;

import org.test.apachecommonpooltest.mocks.TcpEchoServer;
import org.test.soa.common.pool.tcp.PoolFactory;
import org.test.soa.common.pool.tcp.TcpConnectionPool;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

public class TcpPoolTest extends TestCase {

    private static final Logger logger = Logger.getLogger(TcpPoolTest.class);

    private TcpConnectionPool pool;
    private final AtomicInteger count = new AtomicInteger(0);

    private static final int MAX_TOTAL = 100;
    private static final int MAX_TOTAL_IDLE = 50;
    private static final int MIN_TOTAL_IDLE = 5;

    private static final byte[] msg = "Mensaje de prueba echo\n".getBytes();
    private static final int readSize = msg.length;

    public TcpPoolTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        logger.info("setUp()");
        super.setUp();

        String host = "127.0.0.1";
        int port = 10800;
        int timeout = 3000;

        //levantando el server de prueba
        TcpEchoServer.initEchoServer(host, port, timeout, readSize);

        //create pool
        pool = PoolFactory.createTcpPool(host, port, timeout, MAX_TOTAL_IDLE, MIN_TOTAL_IDLE, MAX_TOTAL);
    }

    @Override
    protected void tearDown() throws Exception {
        logger.info("tearDown()");
        super.tearDown();
        pool.close();
    }

    public void testPoolBasic() throws InterruptedException {
        logger.info("testPoolBasic()");
        try {
            int limit = 3600;
            ExecutorService es = new ThreadPoolExecutor(2000, 2000, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(limit));

            for (int i = 0; i < limit; i++) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        SocketChannel element = null;
                        try {
                            element = pool.borrowObject();
                            count.getAndIncrement();
                            sendEcho(element);
                        } catch (Exception e) {
                            e.printStackTrace(System.err);
                        } finally {
                            if (element != null) {
                                pool.returnObject(element);
                            }
                        }
                    }

                    private void sendEcho(SocketChannel element) {
                        try {
                            element.socket().getOutputStream().write(msg);
                            element.socket().getOutputStream().flush();

                            byte[] messageByte = new byte[readSize];
                            element.socket().getInputStream().read(messageByte);
                            assertEquals(messageByte, msg);

                        } catch (IOException ex) {
                            logger.error("Error on socket", ex);
                        }
                    }
                };
                es.submit(r);
            }

            es.shutdown();

            try {
                es.awaitTermination(5, TimeUnit.MINUTES);

            } catch (InterruptedException ignored) {
            }

            logger.info("Pool Stats:\n Created:[" + pool.getCreatedCount() + "], Borrowed:[" + pool.getBorrowedCount() + "] , Destroyed:[" + pool.getDestroyedCount() + "], Returned:[" + pool.getReturnedCount() + "]");
            Assert.assertEquals(limit, count.get());
            Assert.assertEquals(count.get(), pool.getBorrowedCount());
            Assert.assertTrue(MAX_TOTAL >= pool.getCreatedCount() - pool.getDestroyedCount());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception:" + ex);
        }
    }

}
