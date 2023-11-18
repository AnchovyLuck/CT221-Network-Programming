package buoi2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientListFile {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			Socket s = new Socket("127.0.0.1", 8888);

			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			Scanner sc = new Scanner(is);
			PrintStream ps = new PrintStream(os);

			while (true) {
				System.out.println("Type a folder path:");
				String folderPath = kb.nextLine(); // kb.nextLine here, not sc.nextLine, because we type from KeyBoard

				String command = "LIST " + folderPath;
				System.out.println("Created command:" + command);
				ps.println(command);

				int n = -2;
				try {
					n = sc.nextInt();
					sc.nextLine();
				} catch (NoSuchElementException e) {
					System.out.println("the number of file is not exist, maybe the server is down!");
				}

				switch (n) {
				case -1:
					System.out.println("The folder is not exist!");
					break;
				case 0:
					System.out.println("The folder is empty!");
					break;
				default:
					for (int i = 0; i < n; i++) {
						String result = sc.nextLine();
						System.out.println(result);
					}
				}
			}
			// Because I close the socket after listing so it ended

//			s.close(); //Try to comment this one
//			sc.close(); //and this one
//			kb.close(); // also this one

		} catch (UnknownHostException e) {
			System.out.println("Wrong server IP address");
		} catch (IOException e) {
			System.out.println("Error of when input the client socket");
		}
	}

}
