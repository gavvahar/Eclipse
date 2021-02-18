package edu.mccc.cos210;

import java.util.stream.IntStream;

import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.util.Utility;

public class CollectorTest
{
	public static void main(String[] args)
	{
		new CollectorTest().doIt();
	}
	private void doIt()
	{
		IArray<String> ias = IntStream.range(1, 10)
				.mapToObj(String::valueOf)
				.collect(Utility.toArray())
				;
		ias.forEach(s -> System.out.format("%2s ", s));
		
		System.out.println();
		
		ISinglyLinkedList<String> slls = IntStream.range(10, 20)
				.mapToObj(String::valueOf)
				.collect(Utility.toSinglyLinkedList())
				;
		slls.forEach(s -> System.out.format("%2s ", s));
		
		System.out.println();
		
		IDoublyLinkedList<Integer> dlln = IntStream.range(20, 30)
			.mapToObj(Integer::valueOf)
			.collect(Utility.toDoublyLinkedList())
			;
		dlln.forEach(s -> System.out.format("%2d ", s));
	}
}