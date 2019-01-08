package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "218.39.221.76";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 1. 소켓 생성
		Socket socket = null;
		
		socket = new Socket();
		
		// 2. 서버와 연결
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			
			while (true) {
				System.out.print(">>");
				String data = scanner.nextLine();
				if("exit".equals(data)) {
					break;
				}
				pr.println(data);
				System.out.println("<<" + br.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
