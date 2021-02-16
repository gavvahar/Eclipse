package edu.mccc.cos210.ds.test;

import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.impl.Stack;

public class TestIStack1 {

	public static void main(String[] args) {
		new TestIStack1().doIt();
	}

	private void doIt() {
		IStack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.peek());
		stack.pop();
		int n = stack.pop();
		System.out.println(n);
		System.out.println(stack.isEmpty());
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.isEmpty());
		try {
			stack.peek();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
}
