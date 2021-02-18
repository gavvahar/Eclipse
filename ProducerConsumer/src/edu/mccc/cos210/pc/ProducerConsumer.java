package edu.mccc.cos210.pc;

import edu.mccc.cos210.ds.IQueue;
import edu.mccc.cos210.ds.impl.Queue;

public class ProducerConsumer {
	
	private IQueue<Integer> theQueue = new Queue<>();
	
	private volatile int value = 0;

	public static void main(String[] args) {
		new ProducerConsumer().doIt();
	}

	private void doIt() {
		startConsumers();
		startProducers();
	}
	
	private void startConsumers() {
		for (int i = 0; i < 10; i++) {
			new Consumer(i).start();
		}
	}

	private void startProducers() {
		for (int i = 0; i < 10; i++) {
			new Producer(i).start();
		}
	}

	private class Producer extends Thread {
		private int id;

		private Producer(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (theQueue) {
					theQueue.enqueue(value);
					theQueue.notifyAll();
					System.out.printf("P-%d-%d\n", id, value++);
				}
				try {
					Thread.sleep((int) (10000 * Math.random()) + 10000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	private class Consumer extends Thread {
		private int id;
		private int value = 0;

		private Consumer(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (theQueue) {
					while (theQueue.isEmpty()) {
						try {
							theQueue.wait();
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					value = theQueue.dequeue();
				}
				try {
					Thread.sleep((int) (10000 * Math.random()) + 10000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.printf("C-%d-%d\n", id, value);
			}
		}
	}
}
