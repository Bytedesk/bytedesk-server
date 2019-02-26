package com.xiaper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author bytedesk.com on 2019/2/1
 */
public class UDPClient {

    private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;
    private Scanner scanner;

    private UDPClient(String destinationAddr, int port) throws IOException {
        //
        this.serverAddress = InetAddress.getByName(destinationAddr);
        this.port = port;
        udpSocket = new DatagramSocket(this.port);
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        //
        String ip = "127.0.0.1";
        int port = 8001;
        // args[0], Integer.parseInt(args[1])
        UDPClient sender = new UDPClient(ip, port);
        //
        System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
        sender.start();
    }

    private int start() throws IOException {

        String in;
        while (true) {
            //
            in = scanner.nextLine();
            //
            DatagramPacket p = new DatagramPacket(in.getBytes(), in.getBytes().length, serverAddress, port);
            //
            this.udpSocket.send(p);
        }
    }

}
