package thread;

public class MultiThreadEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabeticThread();
		Thread thread3 = new DigitThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
