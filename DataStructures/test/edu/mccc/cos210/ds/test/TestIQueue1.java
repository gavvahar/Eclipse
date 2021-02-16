package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IQueue;
import edu.mccc.cos210.ds.impl.Queue;

public class TestIQueue1 {
	
	public static void main(String[] args) {
		new TestIQueue1().doIt();
	}

	private void doIt() {
		IQueue<Integer> queue = new Queue<>();
		queue.enqueu(1);
		queue.enqueu(2);
		queue.enqueu(3);
		System.out.println(queue.peek());
		queue.dequeue();
		int n = queue.dequeue();
		System.out.println(n);
		System.out.println(queue.isEmpty());
		System.out.println(queue.peek());
		queue.dequeue();
		System.out.println(queue.isEmpty());
		try {
			queue.dequeue();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
