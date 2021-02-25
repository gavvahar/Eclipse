package edu.mccc.cos210;

import java.util.Comparator;
import java.util.stream.IntStream;

import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.util.Utility;

public class CollectorTest4 {

	public static void main(String[] args) {
		new CollectorTest4().doIt();
	}

	private void doIt() {
		IDoublyLinkedList<Integer> theList = IntStream.range(0, 20)
			.mapToObj(Integer::valueOf)
			.sorted(
				Comparator.comparingInt(CollectorTest4::isOdd)
					.thenComparingInt(CollectorTest4::value)
			)
			.collect(Utility.toDoublyLinkedList())
			;
		theList.forEach(System.out::println);
	}
	
	private static int value(int n)
	{
		if(n % 2 == 1)
		{
			n = -n;
		}
		return n;
	}
	
	private static int isOdd(int n)
	{
		return n % 2;
	}
}