package muti_thread_timer;

public class Counter implements Runnable {

	private String name;
	private int seconds;
	private int threadID;

	public Counter(String name, int threadID) {
		this.name = name;
		this.seconds = 0;
		this.threadID = threadID;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000L);
				seconds++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		Thread.currentThread().interrupt();
		System.out.println("Thread Interrupted!");
		System.out.println(check());
	}

	public String check() {
		return name + ": " + "ThreadID: " + threadID + ", seconds: " + seconds + "\n";
	}

	public String getName() {
		return name;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getThreadID() {
		return threadID;
	}

	@Override
	public String toString() {
		return "Counter [name=" + name + ", seconds=" + seconds + ", threadID=" + threadID + "]";
	}

}
