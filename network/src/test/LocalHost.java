package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress inetAddress =
					InetAddress.getLocalHost();
			
			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			
			System.out.println(hostname);
			System.out.println(hostAddress);
			System.out.println(hostname + ":" + hostAddress);
			
			byte[] addresses = inetAddress.getAddress();
			for(byte address : addresses) {
				System.out.print(address & 0x000000ff);
				System.out.print(".");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
