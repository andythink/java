public class InerThreadTest
{
	private int j = 0;

	public static void main(String[] args)
	{
		InerThreadTest t = new InerThreadTest();
		Add add = t.new Add();
		Sub sub = t.new Sub();

		for (int i = 0; i < 2; i++)
		{
			Thread t1 = new Thread(add);
			t1.start();
			Thread t2 = new Thread(sub);
			t2.start();
		}

	}

	/*
	 * 这里add方法和sub方法加synchronized关键字是因为当两个线程同时操作同一个变量时，
	 * 就算是简单的j++操作时，在系统底层也是通过多条机器语句来实现，所以在执行j++过程也是要耗费时间，
	 * 这时就有可能在执行j++的时候，另外一个线程H就会对j进行操作，因此另外一个线程H可能操作的可能就 不是最新的值了。因此要提供线程同步。
	 */
	private synchronized void add()
	{
		j++;
		System.out.println(Thread.currentThread().getName() + ":" + j);
	}

	private synchronized void sub()
	{
		j--;
		System.out.println(Thread.currentThread().getName() + ":" + j);
	}

	class Add implements Runnable
	{
		public void run()
		{
			for (int i = 0; i < 100; i++)
			{
				add();
			}
		}
	}

	class Sub implements Runnable
	{
		public void run()
		{
			for (int i = 0; i < 100; i++)
			{
				sub();
			}
		}
	}
}