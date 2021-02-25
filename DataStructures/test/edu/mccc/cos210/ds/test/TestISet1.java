package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.impl.Set;

public class TestISet1 {

	public static void main(String[] args) {
		new TestISet1().doIt();
	}
	
	private void doIt() {
		ISet<String> ss = new Set<>();
		System.out.println(ss.getSize());
		ss.add("A");
		ss.add("A");
		ss.add("A");
		ss.add("A");
		ss.add("A1");
		ss.add("A2");
		ss.add("A3");
		ss.add("A4");
		ss.add("A5");
		ss.add("B");
		ss.add("C");
		ss.add("D");
		ss.add("E");
		ss.add("F");
		ss.add("G");
		ss.add("H");
		ss.add("I");
		ss.add("J");
		ss.add("K");
		ss.add("L");
		ss.add("M");
		ss.add("N");
		ss.add("O");
		ss.add("P");
		ss.add("Q");
		ss.add("R");
		ss.add("S");
		ss.add("T");
		ss.add("U");
		ss.add("B");
		ss.add("W");
		ss.add("X");
		ss.add("Y");
		ss.add("Z");
		System.out.println(ss.contains("A"));
		System.out.println(ss.contains("D"));
		System.out.println(ss.contains("G"));
		ss.remove("G");
		System.out.println(ss.contains("G"));
		IDoublyLinkedList<String> dll = ss.asList();
		dll.forEach(System.out::println);
		ISet<String> st = new Set<>();
		st.addAll(ss);
		dll = st.asList();
		dll.forEach(System.out::println);
		System.out.println(ss.containsAll(st));
		System.out.println(st.containsAll(ss));
		st.remove("H");
		System.out.println(ss.containsAll(st));
		System.out.println(st.containsAll(ss));
		System.out.println(ss);
		st.add("Now");
		st.add("is");
		st.add("the");
		st.add("time");
		st.add("for");
		st.add("all");
		st.add("good");
		st.add("computer");
		st.add("scientists");
		st.add("to");
		st.add("come");
		st.add("to");
		st.add("the");
		st.add("aid");
		st.add("of");
		st.add("their");
		st.add("party");		
		System.out.println(st);
		System.out.println(ss.union(st));
		System.out.println(ss.intersection(st));
	}
}
