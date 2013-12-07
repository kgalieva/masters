package ru.itis;

import java.io.*;
import java.net.*;

public class EchoClient {

    String serverHostname = "127.0.0.1";

    public void initClient() throws IOException {
        System.out.println("Attemping to connect to host " +
                serverHostname + " on port 12345.");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(serverHostname, 12345);
            //для передачи данных на сервер
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            //для чтения данных с сервера
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + serverHostname);
            System.exit(1);
        }
        //для чтения с консоли
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Type Message (\"q\" to quit)");
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);

            if (userInput.equals("q")) {
                break;
            }

            System.out.println("echo: " + in.readLine());
            System.out.print("input: ");
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();

    }

    public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.initClient();
    }
}
