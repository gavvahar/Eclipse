package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.impl.DoublyLinkedList;

public class TestIDoublyLinkedList1 {

	public static void main(String[] args) {
		new TestIDoublyLinkedList1().doIt();
	}
	
	private void doIt() {
		IDoublyLinkedList<String> dll = new DoublyLinkedList<>();
		dll.addFirst("One");
		dll.addFirst("Two");
		dll.addFirst("Three");
		dll.addFirst("Four");
		for (String s : dll) {
			System.out.println(s);
		}
		System.out.println(dll.getLast());
		System.out.println(dll.getSize());
		String t = dll.removeFirst();
		System.out.println(t);
		dll.addLast("Five");
		dll.addLast("Six");
		for (String s : dll) {
			System.out.println(s);
		}
		System.out.println(dll.getSize());
	}

}
