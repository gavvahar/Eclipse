package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.impl.Map;

public class TestIMap1 {

	public static void main(String[] args) {
		new TestIMap1().doIt();
	}

	private void doIt() {
		IMap<String, Integer> theMap = new Map<>();
		Map.Entry<String, Integer> e1 = new Map.Entry<>("ABC", 13);
		Map.Entry<String, Integer> e2 = new Map.Entry<>("ABC", 15);
		System.out.println(e1.equals(e2));
		theMap.put("ABC", 13);
		theMap.put("ABC", 15);
		System.out.println(theMap.getSize());
		theMap.put("DEF", 15);
		System.out.println(theMap.getSize());
		System.out.println(theMap);
		for (String key : theMap.keySet().asList()) {
			System.out.println(key + " " + theMap.get(key));
		}
		theMap.remove("ABC");
		theMap.remove("DEF");
		theMap.remove("ABC");
		System.out.println(theMap);
		theMap.put("Joe", 13);
		theMap.put("Bill", 22);
		theMap.put("Mike", 24);
		theMap.put("Tom", 11);
		theMap.put("Joan", 13);
		theMap.put("Mark", 14);
		theMap.put("Sally", 15);
		theMap.put("Jim", 12);
		theMap.put("Susan", 33);
		theMap.put("Debbie", 53);
		theMap.put("Lars", 13);
		theMap.put("Wilma", 3);
		System.out.println(theMap);
	}
	
}
