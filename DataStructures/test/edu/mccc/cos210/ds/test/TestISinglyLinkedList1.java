package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.impl.SinglyLinkedList;

public class TestISinglyLinkedList1 {

	public static void main(String[] args) {
		new TestISinglyLinkedList1().doIt();
	}
	
	private void doIt() {
		ISinglyLinkedList<String> sll = new SinglyLinkedList<>();
		sll.addFirst("One");
		sll.addFirst("Two");
		sll.addFirst("Three");
		sll.addFirst("Four");
		for (String s : sll) {
			System.out.println(s);
		}
		System.out.println(sll.getLast());
		System.out.println(sll.getSize());
		String t = sll.removeFirst();
		System.out.println(t);
		sll.addLast("Five");
		sll.addLast("Six");
		for (String s : sll) {
			System.out.println(s);
		}
		System.out.println(sll.getSize());
	}

}
