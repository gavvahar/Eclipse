package edu.mccc.cos210.anagrams;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collector;

import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.impl.SinglyLinkedList;
import edu.mccc.cos210.ds.util.Utility;
public class Anagrams {
	private ISinglyLinkedList<Holder> theWords = loadWords();
	public static void main(String[] args)
	{
		new Anagrams().doIt();
	}
	
	private void doIt()
	{
		String word = "quit";
		try (
				Scanner scanner = new Scanner(System.in);
		) {
			while (true)
			{
				word = scanner.next();
				if("quit".equals(word))
				{
					break;
				}
				displayAnagrams(findAnagrams(word));
			}
		} finally {
			System.out.println("Bye!");
		}
    }
	private void displayAnagrams(ISinglyLinkedList<String> list)
	{
		list.stream().forEach(System.out::println);
	}
	private ISinglyLinkedList<String> findAnagrams(String word)
	{
		Holder h = new Holder(word);
		return theWords.parallelStream()
				.filter(w -> h.key.length() == w.key.length())
				.filter(w -> h.key.equals(w.key))
				.map(w -> w.word)
				.collect(Utility.toSinglyLinkedList())
				;
	}
	private ISinglyLinkedList<Holder> loadWords()
	{
		try
		{
			return Files.lines(
				Paths.get("./data/pocket.dic"))
					.map(w -> new Holder(w))
					.peek(System.out::println)
					.collect(Utility.toSinglyLinkedList())
					;
		} catch (IOException ex)
		{
			ex.printStackTrace();
			System.exit(-1);
		}
		return new SinglyLinkedList<Holder>();
	}
	private static class Holder
	{
		private String word;
		private String key;
		private Holder(String s)
		{
			this.word = s;
			this.key = computeKey(s);
		}
		private String computeKey(String word)
		{
			return word.chars()
					.sorted()
					.mapToObj(n -> String.valueOf((char) n))
					.collect(
						Collector.of(
								StringBuilder::new,
								StringBuilder::append,
								StringBuilder::append,
								StringBuilder::toString
						)
					)
				;
		}
		@Override
		public String toString()
		{
			return word + " - " + key;
		}
	}
}