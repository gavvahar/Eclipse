package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.impl.Set;

public class TestISet2 {

	public static void main(String[] args) {
		new TestISet2().doIt();
	}
	
	private void doIt() {
		ISet<String> ss = new Set<>();
		ss.add("A");
		ss.add("B");
		ss.add("C");
		IDoublyLinkedList<String> dll = ss.asList();
		System.out.println("set ss:");
		dll.forEach(System.out::println);
		ISet<String> st = new Set<>();
		st.add("B");
		st.add("C");
		st.add("D");
		st.add("E");
		dll = st.asList();
		System.out.println("set st:");
		dll.forEach(System.out::println);
		System.out.println("union:");
		System.out.println(ss.union(st));
		System.out.println("intersection:");
		System.out.println(ss.intersection(st));
	}
}
