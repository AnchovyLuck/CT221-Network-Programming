package buoi1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientNhiPhan {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Type server address:");
			String serverAddress = kb.nextLine();
			System.out.println("Type server port:");
			int serverPort = kb.nextInt(); //The problem is this one
			kb.nextLine();
			//When I try to read a number then press enter,
			//the Scanner think it's an input, so I send 'enter' which
			//is nothing to the server
			
			Socket s = new Socket(serverAddress, serverPort);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			while (true) {
				System.out.println("Type a decimal number from keyboard:");
				String numberString = kb.nextLine();
				System.out.println("Typed: " + numberString);
				os.write(numberString.getBytes());
				
				if (numberString.equals("EXIT")) {
					break;
				}
				
				byte[] b = new byte[1000];
				int n = is.read(b); 
				//Get the bytes from server and return to array b with the length is n
				String binaryString = new String(b, 0, n);
				//Print out the binary string
				System.out.println("Receive: " + binaryString);
			}
			s.close();
			kb.close();
		} catch (UnknownHostException e) {
			System.out.println("Wrong server address");
		} catch (IOException e) {
			System.out.println("Input/output error");
		}
		
		

	}

}
