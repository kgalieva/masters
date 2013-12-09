import java.io.*;
import java.net.*;
import java.util.*;

class UDPClient {
    private InetAddress IPAddress;
    boolean done;
    boolean keepGoing;

    public UDPClient(String sHostName) {
        String s1;
        ArrayList<String> lines = new ArrayList<>();
        int size;
        BufferedReader br;

        try {
            IPAddress = InetAddress.getByName(sHostName);
            System.out.println("Attemping to connect to " + IPAddress + ") via UDP port 54321");
        } catch (UnknownHostException ex) {
            System.err.println(ex);
            System.exit(1);
        }


        // set up the buffered reader to read from the keyboard
        try {
            br = new BufferedReader(new FileReader("UDPInputFile.txt"));
            s1 = br.readLine();
            while (s1 != null) {
                lines.add(s1);
                s1 = br.readLine();
            }
            size = lines.size();
            System.out.println("ArrayList lines has size of: " + size);

            done = false;

            DatagramSocket clientSocket = new DatagramSocket();
            for (int i = 0; i < size; i++) {

                byte[] sendData;

                s1 = lines.get(i);
                sendData = s1.getBytes();

                System.out.println("Sending data to " + sendData.length +
                        " bytes to server from line " + (i + 1));
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, 54321);

                clientSocket.send(sendPacket);
            }
            done = true;

            byte[] receiveData = new byte[1024];

            keepGoing = true;

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Waiting for return packet");
            clientSocket.setSoTimeout(10000);

            while (keepGoing) {
                try {
                    clientSocket.receive(receivePacket);
                    String modifiedSentence =
                            new String(receivePacket.getData());
                    System.out.println("Message: " + modifiedSentence);

                } catch (SocketTimeoutException ste) {
                    System.out.println("Timeout Occurred: Packet assumed lost");
                    if (done)
                        keepGoing = false;
                }

            }
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    public static void main(String args[]) throws Exception {
        String serverHostname = "127.0.0.1";

        if (args.length > 0)
            serverHostname = args[0];

        new UDPClient(serverHostname);

    }
} 
