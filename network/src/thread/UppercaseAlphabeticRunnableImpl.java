package thread;


// 쓰레드 클래스는 아니므로 생성자로 start 불가능
public class UppercaseAlphabeticRunnableImpl extends UppercaseAlphabetic implements Runnable {

	@Override
	public void run() {
		print();
	}

}
