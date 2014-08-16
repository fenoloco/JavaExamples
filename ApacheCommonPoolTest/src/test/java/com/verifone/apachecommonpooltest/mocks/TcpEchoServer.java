package com.verifone.apachecommonpooltest.mocks;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

public class TcpEchoServer extends Thread {

    private static final Logger logger = Logger.getLogger(TcpEchoServer.class);
    protected Socket clientSocket;
    private int readSize;

    public static void initEchoServer(String host, int port, int timeout, int readSize) {
        new ServerTCP(host, port, timeout, readSize);
    }

    private TcpEchoServer(Socket clientSoc, int readSize) {
        clientSocket = clientSoc;
        this.readSize = readSize;
        start();
    }

    public void run() {
        logger.debug("New Communication Thread Started");
        try {

            while (true) {
                byte[] messageByte = new byte[readSize];
                int messageRead = clientSocket.getInputStream().read(messageByte);

                if (messageRead == -1) {
                    break;
                }

                if (readSize != messageRead) {
                    throw new IllegalArgumentException("Error al leer: " + messageRead + " bytes");
                }
                String msg = new String(messageByte, "UTF-8");
                logger.debug("Message arrived: " + msg);
                clientSocket.getOutputStream().write(messageByte);
                clientSocket.getOutputStream().flush();
            }

        } catch (IOException e) {
            logger.error("Problem with Communication Server");

        }
    }

    private static class ServerTCP extends Thread {

        private String host;
        private int port;
        private int timeout;
        private int readSize;

        public ServerTCP(String host, int port, int timeout, int readSize) {
            this.host = host;
            this.port = port;
            this.readSize = readSize;
            this.timeout = timeout;
            start();
        }

        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port, 100, InetAddress.getByName(host));
                logger.info("Connection Socket Created");
                try {
                    while (true) {
                        new TcpEchoServer(serverSocket.accept(), readSize);
                    }
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
            } catch (IOException e) {
                logger.error("Could not listen on port: 10008.");
                System.exit(1);
            } finally {
                try {
                    logger.info("Closing Server Connection Socket");
                    serverSocket.close();
                } catch (IOException e) {
                    logger.error("Could not close port: 10008.");
                    System.exit(1);
                }
            }
        }
    }
}
