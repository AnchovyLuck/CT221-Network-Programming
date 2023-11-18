package buoi1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNhiPhan {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7777);//Don't close this one
			System.out.println("Created server socket successfully!");
			while (true) { //Let's the loop stay forever!
				try {
					Socket s = ss.accept();
					PhucVuNhiPhan binaryThread = new PhucVuNhiPhan(s);
					binaryThread.start();
				} catch (IOException e) {
					System.out.println("Error when accept a connection!");
				}// The thread is invoked in server side
			}
		} catch (IOException e) {
			System.out.println("Error when creating server socket!");
		}
	}

}
