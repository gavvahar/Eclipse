package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.impl.Set;

public class TestISet3 {

	public static void main(String[] args) {
		new TestISet3().doIt();
	}

	private void doIt() {
		ISet<String> theSet1 = new Set<>();
		ISet<String> theSet2 = new Set<>();
		theSet1.add("Hello");
		theSet1.add("GoodBye");
		theSet1.add("Fred");
		theSet2.add("Fred");
		theSet2.add("Mike");
		theSet2.add("Mike");
		System.out.println(theSet1);
		System.out.println(theSet2);
		System.out.println(theSet1.union(theSet2));
		System.out.println(theSet1.intersection(theSet2));
	}

}
