package buoi4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class MulticastTimeClient {

	public static void main(String[] args) {
		try {
			MulticastSocket ms = new MulticastSocket(9999);
			
			//Create a new InetAddress instance
			InetAddress dc = InetAddress.getByName("230.0.0.1");
			
			//Create a new SocketAddress instance (not understand yet!)
			SocketAddress socketAddress = new InetSocketAddress(dc, 9999);
			
			//Join the multicast gropu with specified socketAddress
			ms.joinGroup(socketAddress, null);
			// Receive date instance from server through multicast group
			byte b[] = new byte[1000];
			DatagramPacket receivePacket = new DatagramPacket(b,1000);
			ms.receive(receivePacket);
			
			//Create a byte array, a string hold the result and print it out
			byte b1[] = receivePacket.getData();
			int len1 = receivePacket.getLength();
			String result = new String(b1,0,len1);
			System.out.println("It's " + result);
			// Leave group
			ms.leaveGroup(socketAddress, null);
			
			//Terminate the client
			ms.close();
			
		} catch (UnknownHostException e) {
			System.out.println("Wrong server IP address");
		} catch (IOException e) {
			System.out.println("I/O Error");
		}
	}

}
