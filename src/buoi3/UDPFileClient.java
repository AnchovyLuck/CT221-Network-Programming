package buoi3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPFileClient {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			Scanner kb = new Scanner(System.in);
			System.out.println("Type server's IP address");
			String serverAddress = kb.nextLine();
			while (true) {
				System.out.println("Type the file you want to get content");
				String getFile = kb.nextLine();
				System.out.println("Type the file will take the content");
				String takeFile = kb.nextLine();

				String request = "READUDP " + getFile;
				byte b[] = request.getBytes();
				int n = b.length;
				InetAddress dc = InetAddress.getByName(serverAddress);
				int p = 20233;
				DatagramPacket sendPacket = new DatagramPacket(b, n, dc, p);
				ds.send(sendPacket);

				byte[] b1 = new byte[60000];
				DatagramPacket receivePacket = new DatagramPacket(b1, 60000);

				ds.receive(receivePacket);

				byte[] b2 = receivePacket.getData();
				int n2 = receivePacket.getLength();
				if (n2 == 0) {
					System.out.println("File is empty or not exist!");
				} else {
					FileOutputStream f = new FileOutputStream(takeFile);
					f.write(b2, 0, n2);
					f.close();
					System.out.println("Write result to takeFile successfully!");
				}
			}
		} catch (SocketException e) {
			System.out.println("Can't create Datagram Socket!");
		} catch (UnknownHostException e) {
			System.out.println("Wrong server's IP address!");
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}

}
