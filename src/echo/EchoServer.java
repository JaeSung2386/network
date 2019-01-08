package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {

	private static final int PORT = 5000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		try {
			// 1. 소켓 생성
			serverSocket = new ServerSocket();
			// 로컬 IP
			InetAddress inetAddress = InetAddress.getLocalHost();
			// 2. Bind
			serverSocket.bind(new InetSocketAddress(inetAddress, PORT));
			// 3. Accept
			Socket socket = serverSocket.accept();
			// 클라이언트 정보 얻어오기
			InetSocketAddress inetSocketAddress =
					(InetSocketAddress)socket.getRemoteSocketAddress();
			
			System.out.println("[server] 연결됨 from " + 
					inetSocketAddress.getAddress().getHostAddress() + ":" + 
					inetSocketAddress.getPort());
			
			//4. 데이터 읽음
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			
			String readData = null;
			
			while((readData = br.readLine()) != null) {
				// 5. 데이터 읽기
				System.out.println("[server] 데이터 수신 :" + readData);
				// 6. 데이터 보내기
				pw.println(readData);
			}
			
		} 
		catch(SocketException e) {
			System.out.println("[server] abnormal closed by client");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if( serverSocket != null &&
					serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
