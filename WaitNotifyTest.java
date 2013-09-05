public class WaitNotifyTest
{

	public static void main(String[] p)
	{
		Lock lock = new Lock();
		ThreadA ta = new ThreadA("a", lock);
		ThreadB tb = new ThreadB("b", lock);
		ta.start();
		tb.start();
	}

}

class Lock
{

}

class ThreadA extends Thread
{
	Lock lock;

	ThreadA(String name, Lock lock)
	{
		this.setName(name);
		this.lock = lock;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 5; i++)
		{

			synchronized (lock)
			{
				try
				{
					lock.wait();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
				lock.notify();
			}

		}

	}

}

class ThreadB extends Thread
{
	Lock lock;

	ThreadB(String name, Lock lock)
	{
		this.setName(name);
		this.lock = lock;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 5; i++)
		{
			synchronized (lock)
			{

				System.out.println(Thread.currentThread().getName());
				lock.notify();
				try
				{
					lock.wait();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

		}

	}
}