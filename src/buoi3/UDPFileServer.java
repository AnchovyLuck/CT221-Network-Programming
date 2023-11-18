package buoi3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPFileServer {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(20233);
			byte[] b = new byte[60000];
			DatagramPacket receivePacket = new DatagramPacket(b, 60000);
			while (true) {
				ds.receive(receivePacket);
				InetAddress clientAddress = receivePacket.getAddress();
				int n = receivePacket.getLength();
				int port = receivePacket.getPort();

				String request = new String(b, 0, n);
				System.out.println("Received request:" + request);
				String getFile = request.substring(8);

				File f = new File(getFile);
				if (f.isFile() && f.exists()) {
					int len = (int) f.length();
					FileInputStream f1 = new FileInputStream(f);
					byte[] b2 = new byte[len];
					f1.read(b2);
					f1.close();
					DatagramPacket sendPackage = new DatagramPacket(b2, len, clientAddress, port);
					ds.send(sendPackage);
				} else {
					byte[] b2 = new byte[10];
					DatagramPacket sendPackage = new DatagramPacket(b2, 0, clientAddress, port);
					ds.send(sendPackage);
				}
			}
		} catch (SocketException e) {
			System.out.println("Can't create UDP socket!");
		} catch (IOException e) {
			System.out.println("I/O error!");
		}

	}

}
