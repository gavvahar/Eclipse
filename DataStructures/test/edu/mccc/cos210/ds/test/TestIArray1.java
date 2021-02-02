package edu.mccc.cos210.ds.test;

import java.util.Iterator;

import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.impl.Array;

public class TestIArray1 {

	public static void main(String[] args) {
		new TestIArray1().doIt();
	}

	private void doIt() {
		IArray<Integer> a = new Array<>(11);
		for (int i = 0; i < a.getSize(); i++) {
			a.set(i, i * i);
		}
		for (int i = 0; i < a.getSize(); i++) {
			System.out.print(
				a.get(i).toString() +
				(i == a.getSize() - 1 ? "\n" : " ")
			);
		}
		Iterator<Integer> it = a.iterator();
		while (it.hasNext()) {
			int n = it.next();
			if (n == 25) {
				try {
					it.remove();
				} catch (Exception ex) {
					System.err.println(ex);
				}
			}
			System.out.println(n);
		}
		for (int n : a) {
			System.out.println(n);
		}
	}

}
