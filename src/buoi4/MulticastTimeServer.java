package buoi4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class MulticastTimeServer {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			InetAddress groupAddress = InetAddress.getByName("230.0.0.1");
			int port = 9999;
			int stt = 0;
			while (true) {
				Date d = new Date();
				String str = d.toString();
				byte[] b = str.getBytes();
				
				DatagramPacket sendPackage = new DatagramPacket(b, b.length, groupAddress, port);
				ds.send(sendPackage);
				
				System.out.println("Sent the " + stt++ + " package!");
				Thread.sleep(stt);
			}
		} catch (SocketException e) {
			System.out.println("Can't create UDP socket");
		} catch (UnknownHostException e) {
			System.out.println("Wrong group address");
		} catch (IOException e) {
			System.out.println("I/O error");
		} catch (InterruptedException e) {
			System.out.println("Error when sleep");
		}
	}

}
