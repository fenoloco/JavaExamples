package com.verifone.soa.common.pool.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.log4j.Logger;

public class TcpConnectionFactory extends BasePooledObjectFactory<SocketChannel> {

    private static final Logger logger = Logger.getLogger(TcpConnectionFactory.class);

    private final int port;
    private final String host;
    private final int timeout;

    public TcpConnectionFactory(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    @Override
    public SocketChannel create() throws Exception {
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.socket().setSoTimeout(timeout);
            sc.connect(new InetSocketAddress(host, port));
        } catch (Exception ex) {
            logger.error("Error creating connection:" + host + ":" + port, ex);
            throw ex;
        }
        return sc;
    }

    @Override
    public PooledObject<SocketChannel> wrap(SocketChannel element) {
        return new DefaultPooledObject<SocketChannel>(element);
    }

    @Override
    public void passivateObject(PooledObject<SocketChannel> element) throws Exception {
        element.getObject().socket().getOutputStream().flush();
    }

    @Override
    public boolean validateObject(PooledObject<SocketChannel> element) {
        return true; //element.getObject().isConnected();
    }

    @Override
    public void destroyObject(PooledObject<SocketChannel> element) throws Exception {
        super.destroyObject(element);
        SocketChannel sc = element.getObject();
        sc.close();
    }
        

    private static void close(Closeable obj) {
        if (obj != null) {
            try {
                obj.close();
            } catch (IOException ex) {
                logger.error("Error closing connection", ex);
            }
        }
    }

}
