/**
 * 模拟线程死锁
 * @author andy
 *
 */

public class DeadlockRiskTest
{
	public static void main(String[] args)
	{

		DeadlockRisk d = new DeadlockRisk();

		MyThreadA ta = new MyThreadA(d);
		MyThreadB tb = new MyThreadB(d);

		ta.start();
		tb.start();

	}
}

class MyThreadA extends Thread
{

	private DeadlockRisk d;

	MyThreadA(DeadlockRisk d)
	{
		super("A");
		this.d = d;
	}

	public void run()
	{
		d.read();
	}
}

class MyThreadB extends Thread
{

	private DeadlockRisk d;

	MyThreadB(DeadlockRisk d)
	{
		super("B");
		this.d = d;
	}

	public void run()
	{
		d.write(1, 2);
	}
}

class DeadlockRisk
{
	private static class Resource
	{
		public int value;
	}

	private Resource resourceA = new Resource();
	private Resource resourceB = new Resource();

	public int read()
	{
		synchronized (resourceA)
		{
			System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName()
					+ "获得锁：A");
			synchronized (resourceB)
			{
				System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName()
						+ "获得锁：B");
				return resourceB.value + resourceA.value;
			}
		}
	}

	public void write(int a, int b)
	{
		synchronized (resourceB)
		{
			System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName()
					+ "获得锁：B");
			synchronized (resourceA)
			{
				System.out.println("开始时间：" + System.currentTimeMillis() + ",线程名字：" + Thread.currentThread().getName()
						+ "获得锁：A");
				resourceA.value = a;
				resourceB.value = b;
			}
		}
	}
}
