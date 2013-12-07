package ru.itis;

import java.net.*;
import java.io.*;

public class EchoServerMult {

    private boolean serverContinue = true;

    public void initServer() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Connection Socket Created");
            try {
                while (serverContinue) {
                    serverSocket.setSoTimeout(10000);
                    System.out.println("Waiting for Connection");
                    try {
                        Socket clientSocket = serverSocket.accept();
                        new Handler(clientSocket, this).start();
                    } catch (SocketTimeoutException ste) {
                        System.out.println("Timeout Occurred");
                    }
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 12345.");
            System.exit(1);
        } finally {
            try {
                System.out.println("Closing Server Connection Socket");
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 12345.");
                System.exit(1);
            }
        }
    }

    public void setServerContinue(boolean serverContinue) {
        this.serverContinue = serverContinue;
    }

    public static void main(String[] args) throws IOException {
        EchoServerMult echoServer = new EchoServerMult();
        echoServer.initServer();
    }
}

class Handler extends Thread {

    private Socket clientSocket;
    private EchoServerMult server;



    public Handler(Socket clientSocket, EchoServerMult echoServer) {
            this.clientSocket = clientSocket;
            server = echoServer;
    }

    public void run() {
        System.out.println("New Communication Thread Started");

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                    true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server: " + inputLine);

                if (inputLine.equals("?")) {
                    inputLine = "\"q\" ends Client, \"stop\" ends Server";
                }

                out.println(inputLine);

                if (inputLine.equals("q")) {
                    break;
                }

                if (inputLine.equals("stop")) {
                    server.setServerContinue(false);
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }
    }
} 
