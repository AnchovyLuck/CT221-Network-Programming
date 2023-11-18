package buoi3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoServer {

	public static void main(String[] args) {
		try {
			DatagramSocket ds  = new DatagramSocket(9999);
			
			byte[] b = new byte[60000];
			
			DatagramPacket receivePacket = new DatagramPacket(b, 60000);
			while (true) {
				ds.receive(receivePacket);
				
				byte[] b1 = receivePacket.getData();
				int n1 = receivePacket.getLength();
				String request = new String(b1, 0, n1);
				String response = request.toUpperCase();
				
				byte[] b2 = response.getBytes();
				int n2 = n1;
				InetAddress clientAddress = receivePacket.getAddress();
				int clientPort = receivePacket.getPort();
				DatagramPacket sendPacket = new DatagramPacket(b2, n2, clientAddress, clientPort);
				
				ds.send(sendPacket);
			}
		} catch (SocketException e) {
			System.out.println("Can't create UDP Socket");
		} catch (UnknownHostException e) {
			System.out.println("Wrong server IP address");
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}

}
