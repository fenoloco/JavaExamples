package com.verifone.soa.common.pool.tcp;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Iso8583Channel{
    
    private SocketChannel socketChannel;
    
    String hostname;
    int port;
    int headerLength;
    
    
    public Iso8583Channel(String hostname, int port, int headerLength) throws IOException{
        // socketConn = new Socket(hostname, port);
    }
    
    public String writeToSocket(String msg) throws IOException{
       /* PrintWriter out = new PrintWriter(socketConn.getOutputStream());
        
        out.println(msg);
        out.flush();
        **/
        return "";
        
    }

    
}
