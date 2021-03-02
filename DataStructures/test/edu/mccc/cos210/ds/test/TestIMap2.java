package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.impl.DoublyLinkedList;
import edu.mccc.cos210.ds.impl.Map;

public class TestIMap2 {

	public static void main(String[] args) {
		new TestIMap2().doIt();
	}

	private void doIt() {
		IMap<String, String> theMap = new Map<>();
		theMap.put("Bob", "Dog");
		theMap.put("Sam", "Cat");
		theMap.remove("Sam");
		theMap.put("Sam", "Fish");
		System.out.println(theMap);
		ISet<String> keys = theMap.keySet();
		System.out.println(keys);
		DoublyLinkedList<String> theList = (DoublyLinkedList<String>) keys.asList();
		System.out.println(theList);
		for(String key : theList)
		{
			String value = theMap.get(key);
			if("Cat".equals(value))
			{
				System.out.println(value);
			}
		}
	}

}
