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
			br.lines().forEach(System.out::println);
		} catch (Exception ex)
		{
			System.err.println(ex);
			System.exit(-1);
		}
	}
}