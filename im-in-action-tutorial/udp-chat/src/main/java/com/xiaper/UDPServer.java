package com.xiaper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * https://www.pegaxchange.com/2018/01/23/simple-udp-server-and-client-socket-java/
 *
 * @author bytedesk.com on 2019/2/1
 */
public class UDPServer {

    private DatagramSocket udpSocket;
    private int port;

    public UDPServer(int port) throws SocketException, IOException {
        this.port = port;
        this.udpSocket = new DatagramSocket(this.port);
    }

    private void listen() throws Exception {
        System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
        String msg;

        while (true) {

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpSocket.receive(packet);
            msg = new String(packet.getData()).trim();

            System.out.println("Message from " + packet.getAddress().getHostAddress() + ": " + msg);
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8001;
        // Integer.parseInt(args[0])
        UDPServer client = new UDPServer(port);
        client.listen();
    }

}
