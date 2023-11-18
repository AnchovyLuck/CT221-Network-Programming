package buoi2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerList {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("Created server socket successfully!");
			while (true) {
				try {
					Socket s = ss.accept();
					System.out.println("There's a connection from a client!");
					InputStream is = s.getInputStream();
					OutputStream os = s.getOutputStream();
					Scanner sc = new Scanner(is);
					PrintStream ps = new PrintStream(os);

					while (true) {
						String command = sc.nextLine(); // Read command from client
						System.out.println("THE COMMAND RECEIVED: " + command);
						String folderPath = command.substring(5);
						System.out.println("THE FOLDER PATH RECEIVED: " + folderPath);

						File f = new File(folderPath);
						if (f.isDirectory() && f.exists()) {
							String[] childFiles = f.list();
							int n = childFiles.length;
							ps.println(n); // Send the number of file/folder in the pathname string to client
							for (int i = 0; i < n; i++) {
								File childFile = new File(folderPath + "/" + childFiles[i]);
								if (childFile.isFile()) {
									// Send the child file name to client if child is a normal file
									ps.println(childFiles[i]);
								} else {
									// Send the child wrapped by blanket if child is a directory
									ps.println("[" + childFiles[i] + "]");
								}
							}
						} else {
							ps.println(-1);
						}
					}
//					s.close();
				} catch (IOException e) {
					System.out.println("I/O error when get create the socket");
				}
			}
		} catch (IOException e) {
			System.out.println("I/O error with server socket!");
		}
	}

}
