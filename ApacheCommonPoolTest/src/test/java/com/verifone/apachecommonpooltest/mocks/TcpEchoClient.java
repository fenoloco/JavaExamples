package com.verifone.apachecommonpooltest.mocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author MauricioF1
 */
public class TcpEchoClient {

    public static void client() throws IOException {

        String hostname = "localhost";
        Socket theSocket = new Socket(hostname, 10800);
        BufferedReader networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(theSocket.getOutputStream());
        System.out.println("Connected to echo server");

        while (true) {
            String theLine = userIn.readLine();
            if (theLine.equals(".")) {
                break;
            }
            out.println(theLine);
            out.flush();
            System.out.println(networkIn.readLine());
        }
        networkIn.close();
        out.close();
    }
}
