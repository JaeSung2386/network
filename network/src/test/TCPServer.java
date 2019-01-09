package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	private static final int PORT = 5000;
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		try {
			// 1. Server 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. Binding: ISocket에 SocketAddress(IP + Port) 바인딩
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();
			
			serverSocket.bind(new InetSocketAddress(localhostAddress, 5000));
			System.out.println("[server] binding " + localhostAddress + ":" + PORT);
			
			// 3. accpet( 클라이언트로 부터 연결 요청을 기다린다.)
			Socket socket = serverSocket.accept(); //Blocking
			InetSocketAddress inetRemoteSockAddress =
					(InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress =
					inetRemoteSockAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSockAddress.getPort();
			
			System.out.println(
					"[server] connected by client[" + 
					remoteHostAddress + ":" + 
					remotePort + "]");
			
			// 4. IOStream 받아오기
			try {
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while(true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); //blocking
					if(readByteCount == -1) {
						// 정상종료: remote socket이 close()
						// 메소드를 통해서 정상적으로 소켓을 닫은 경우
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server] received: " + data);
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("UTF-8"));
				}
			} 
			catch(SocketException e) {
				System.out.println("[server] abnormal closed by client");
			}
			catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					// 7. 자원정리(소켓 닫기)
					if(socket != null && socket.isClosed() == false) {
						socket.close();	
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
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
