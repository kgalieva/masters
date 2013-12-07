package ru.itis;

import java.net.*;
import java.io.*;

public class EchoServer {

    public void initServer() throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 12345.");
            System.exit(1);
        }
        Socket clientSocket = null;
        System.out.println("Waiting for connection.....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Connection successful");
        System.out.println("Waiting for input.....");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Server: " + inputLine);

            if (inputLine.equals("?"))
                inputLine = "\"q.\" ends Client";

            out.println(inputLine);

            if (inputLine.equals("q"))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        EchoServer2 echoServer2 = new EchoServer2();
        echoServer2.initServer();
    }
} 