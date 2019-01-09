package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String dns = null;
		while(true) {
			System.out.print("> ");
			dns = scanner.nextLine();
			if("exit".equals(dns)) {
				break;
			}
			try {
				InetAddress[] inetAddresses = InetAddress.getAllByName(dns);
				for(int n = 0; n < inetAddresses.length; n++)
					System.out.println(
							inetAddresses[n].getHostName() + " : " +
							inetAddresses[n].getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.out.println(
						dns+"을(를) 찾을 수 없습니다.");
			}
		}
		
	}
}
