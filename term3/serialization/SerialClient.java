package serialization;

import java.io.*;
import java.net.*;

public class SerialClient {
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 10007);

            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new ObjectInputStream(echoSocket.getInputStream());

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: taranis.");
            System.exit(1);
        }
        /*Send and read Point3d*/
        Point3d pt1 = new Point3d(7, 8, 10);
        Point3d pt2 = null;

        System.out.println("Sending point: " + pt1 + " to Server");
        out.writeObject(pt1);
        out.flush();
        System.out.println("Send point, waiting for return value");

        try {
            pt2 = (Point3d) in.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Got point: " + pt2 + " from Server");

        /*Send and read Point4d*/
        Point4d pt5 = new Point4d(7, 8, 10, 12);
        Point4d pt6 = null;

        System.out.println("Sending point: " + pt5 + " to Server");
        out.writeObject(pt5);
        out.flush();
        System.out.println("Send point, waiting for return value");

        try {
            pt6 = (Point4d) in.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Got point: " + pt6 + " from Server");

        out.close();
        in.close();
        echoSocket.close();
    }
}
