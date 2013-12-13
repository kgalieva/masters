package serialization;

import java.net.*;
import java.io.*;

public class SerialServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(10007);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10007.");
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            System.out.println("Waiting for Client");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

        Point3d pt3 = null;
        Point3d pt4;

        try {
            pt3 = (Point3d) in.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("Server recieved point: " + pt3 + " from Client");

        pt4 = new Point3d(-24, -23, -22);
        System.out.println("Server sending point: " + pt4 + " to Client");
        out.writeObject(pt4);
        out.flush();

        Point3d pt7 = null;
        Point3d pt8;

        try {
            pt7 = (Point4d) in.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("Server recieved point: " + pt7 + " from Client");

        pt8 = new Point4d(-24, -23, -22, -21);
        System.out.println("Server sending point: " + pt8 + " to Client");
        out.writeObject(pt8);
        out.flush();


        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
} 