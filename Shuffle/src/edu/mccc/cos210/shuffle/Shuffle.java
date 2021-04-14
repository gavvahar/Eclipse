package edu.mccc.cos210.shuffle;

import java.util.Random;

import edu.mccc.cos210.ds.impl.DoublyLinkedList;

public class Shuffle {

	Random rng = new Random();
	
	public static void main(String[] args) {
		new Shuffle().doIt();
	}

	private void doIt() {
		Deck deck = createDeck();
		shuffle(deck);
		System.out.println(deck);
	}

	protected void shuffle(Deck deck) {
	}
	
	private Deck createDeck() {
		Deck deck = new Deck();
		for (int i = 0; i < 52; i++) {
			deck.addLast(new Card(i));
		}
		return deck;
	}
	
	static class Deck extends DoublyLinkedList<Card> {
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			this.forEach(s -> sb.append(s + "\n"));
			return sb.toString().trim();
		}

		public void addLast(Card card) {
			// TODO Auto-generated method stub
			
		}
	}
	
	static class Card {
		int index;
		Card(int index) {
			this.index = index;
		}
		@Override
		public String toString() {
			String[] ranks = {
				"Ace", "Two", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten",
				"Jack", "Queen", "King"
			};
			String[] suits = {
				"Clubs", "Diamonds", "Hearts", "Spades"
			};
			return ranks[index % 13] + " of " + suits[index / 13];
		}
	}
}
