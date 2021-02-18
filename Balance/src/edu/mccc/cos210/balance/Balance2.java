package edu.mccc.cos210.balance;
import java.util.Scanner;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.impl.Stack;
public class Balance2
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		new Balance2().doIt(scanner);
	}
	private void doIt(Scanner scanner)
	{
		while (scanner.hasNext())
		{
			String s = scanner.next();
			System.out.println(
				s +
				(isValid(s) ? " " : " in") +
				"valid"
			);
		}
	}
	private boolean isValid(String s)
	{
		boolean b = true;
		IStack<String> theStack = new Stack<>();
		for (int i = 0; i < s.length(); i++)
		{
			String t = String.valueOf(s.charAt(i));
			if (t.matches("[\\(\\[]"))
			{
				theStack.push(t);
			} else
			{
				if (theStack.isEmpty())
				{
					b = false;
					break;
				}
				String u = theStack.pop();
				if(!isPair(u, t))
				{
					b = false;
					break;
				}
			}
		}
		if (!theStack.isEmpty())
		{
			b = false;
		}
		return b;
	}
	@SuppressWarnings("unused")
	private boolean isPair(String open, String close)
	{
		boolean b = false;
		if(
			"(".equals(open) && ")".equals(close) ||
			"[".equals(open) && "]".equals(close))
		{
			b = true;
		}
		return b;
	}
}
