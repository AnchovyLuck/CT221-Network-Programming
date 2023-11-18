package buoi1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PhucVuNhiPhan extends Thread {
	private Socket s;

	public PhucVuNhiPhan(Socket socket) {
		this.s = socket;
	}
	
	public void run() {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while (true) {
				byte[] b = new byte[100];
				int n = is.read(b);
				String request = new String(b, 0, n);
				System.out.println("Receive:" + request);
				
				if (request.equals("EXIT")) {
					break;
				}
				
				String result = new String();
				try {
					int intNumber = Integer.parseInt(request);
					result = Integer.toBinaryString(intNumber);
				} catch (NumberFormatException e) {
					result = "NaN";
				}
				
				os.write(result.getBytes());
			}
			/**
			 * If the for loop is break, then close the connection from a specific client
			 */
			System.out.println("Closing connection from a client");
			s.close();
		} catch (IOException e) {
			System.out.println("Catch a error when serving a client");
		}
	}
	
}
