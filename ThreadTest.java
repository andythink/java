import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest
{
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		// 第一种实现
		ExtendThread t1 = new ExtendThread("T1");
		// t1.start();

		// 第二种实现
		ImplementRunnableThread r = new ImplementRunnableThread("R");
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		// t2.start();
		// t3.start();

		// 第三种实现
		ExecutorService executor = Executors.newFixedThreadPool(3);
		ImplementCallableThread c = new ImplementCallableThread("Callable");
		Future future1 = executor.submit(c);
		System.out.println("future1=" + future1.get());
		Future future2 = executor.submit(c);
		System.out.println("future2=" + future2.get());
		Future future3 = executor.submit(c);
		System.out.println("future3=" + future3.get());

	}

}

class ExtendThread extends Thread
{
	private String name;

	public ExtendThread(String name)
	{
		super(name);
		this.name = name;
	}

	public void run()
	{
		synchronized (this.name)
		{
			System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName());
			System.out.println("name=" + name);
			System.out.println("结束时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName());

		}
	}

}

class ImplementRunnableThread implements Runnable
{
	private String name;

	public ImplementRunnableThread(String name)
	{
		this.name = name;
	}

	public void run()
	{
		synchronized (this.name)
		{
			System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName());
			System.out.println("name=" + name);
			System.out.println("结束时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName());

		}
	}

}

class ImplementCallableThread implements Callable<String>
{
	private String name;

	public ImplementCallableThread(String name)
	{
		this.name = name;
	}

	@Override
	public String call() throws Exception
	{
		System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName());
		System.out.println("name=" + name);
		System.out.println("结束时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName());
		return "Hello Callable";
	}

}