package edu.mccc.cos210.words;
import java.io.*;
public class Doubles
{
	public static void main(String[] args) throws Exception
	{
		new Doubles().doIt();
	}
	private void doIt() throws Exception
	{
		try (BufferedReader br = new BufferedReader(new FileReader("./data/pocket.dic")))
		{
			//br.lines().forEach(System.out::println);
			br.lines().forEach(word -> {
				char prevChar = word.charAt(0);
				char prevPrevChar = ' ';
				int doubleCount = 0;
				for(int i = 1; i< word.length(); i++)
				{
					char nextChar = word.charAt(i);
					if(nextChar == prevChar && nextChar != prevPrevChar)
					{
						doubleCount++;
					}
					prevPrevChar = prevChar;
					prevChar = nextChar;
				}
				if(doubleCount > 0)
				{
					System.out.println(word + " " + doubleCount);
				}
			});
		} catch (Exception ex)
		{
			System.err.println(ex);
			System.exit(-1);
		}
	}
}