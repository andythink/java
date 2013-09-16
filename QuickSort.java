public class QuickSort
{
	private static int data[] =	{ 49, 38, 65, 97, 76, 13, 27, 49 };

	private static void quickSort(int data[], int s, int t)
	{
		int temp;
		int i, j;
		if (s < t)
		{
			i = s;
			j = t + 1;

			while (true)
			{
				do
					i++;
				while (!(data[i] >= data[s] || i == t));
				do
					j--;
				while (!(data[j] <= data[s] || j == s));
				if (i < j)
				{
					temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				} else
				{
					break;
				}
			}
			temp = data[s];
			data[s] = data[j];
			data[j] = temp;
			quickSort(data, s, j - 1);
			quickSort(data, j + 1, t);
		}
	}

	public static void main(String[] args)
	{
		for (int i = 0; i < data.length; i++)
		{
			System.out.print("  " + data[i]);
		}

		System.out.println("\n---------------------------------------------------------------------------------------");
		
		quickSort(data, 0, data.length -1);

		for (int i = 0; i < data.length; i++)
		{
			System.out.print("  " + data[i]);
		}
	}
}