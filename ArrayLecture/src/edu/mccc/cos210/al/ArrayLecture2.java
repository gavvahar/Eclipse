package edu.mccc.cos210.al;

import java.util.stream.IntStream;

public class ArrayLecture2
{
	public static void main(String[] args)
	{
		new ArrayLecture2().doIt();
	}

	private void doIt()
	{
		int[] na = new int[11];
		System.out.println(na.length);
		for (int i = 0; i < na.length; i++)
		{
			na[i] = i * i;
		}
		for (int i = 0; i < na.length; i++)
		{
//			System.out.println(na[i]);
		}
//		System.out.println("----------");
//		IntStream.range(0,  11).map(n -> n * n).forEach(System.out::println);
		
		double[] da = new double[17];
		for (int i = 0; i < da.length; i++)
		{
			da[i] = Math.pow(2.0,  Double.valueOf(i));
//			System.out.printf("%.0f\n", da[i]);
		}
		
//			IntStream.range(0, 17)
//			.mapToObj(n -> Math.pow(2.0, Double.valueOf(n)))
//			.map(Double::intValue)
//			.forEach(System.out::println)
//			;
		String[][] saa = {
				new String[] { "bob", "tom", "jane" },
				new String[] { "angus", "susan" },
				new String[] { "lucy", "sam", "rosebud" },
				new String[] { "phil", "debbie", "pat", "george" },
		};
		
		//System.out.println(saa.length);
		for (int i = 0; i < saa.length; i++)
		{
			//System.out.println(saa[i].length);
		}
		//System.out.println(saa[2][1]);
		
		for (int i = 0; i< saa.length; i++)
		{
			for (int j = 0; j < saa[i].length; j++)
			{
				/*if (!(i == saa.length && j == saa[i].length))
				{
					System.out.print(saa[i][j] + " ");
				}else {
					System.out.println(saa[i][j]);
				}
				*/
				System.out.print(saa[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("hello");
	}
}