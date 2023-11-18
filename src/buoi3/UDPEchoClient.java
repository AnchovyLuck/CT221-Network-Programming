package buoi3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPEchoClient {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			while (true) {
				Scanner kb = new Scanner(System.in);
				System.out.println("Type a string:");
				String str = kb.nextLine();
				
				if (str.equals("EXIT")) {
					break;
				}
				
				byte[] b = str.getBytes();
				int n = b.length;
				InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
				int port = 9999;
				DatagramPacket sendPacket = new DatagramPacket(b, n, serverAddress, port);
				
				ds.send(sendPacket);
				
				byte[] b1 = new byte[60000];
				DatagramPacket receivePacket = new DatagramPacket(b1, 60000);
				
				ds.receive(receivePacket);
				
				byte[] b2 = receivePacket.getData();
				int n2 = receivePacket.getLength();
				
				String result = new String(b2, 0, n2);
				System.out.println("Received: " + result);
			}
			ds.close();
		} catch (SocketException e) {
			System.out.println("Can't create UDP Socket");
		} catch (UnknownHostException e) {
			System.out.println("Wrong server IP address");
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}

}
