package edu.mccc.cos210.anagrams;
import java.io.*;

public class Anagrams {
	public static void main(String[] args) throws Exception
	{
		new Anagrams().doIt();
	}
	
	private void doIt() throws Exception
	{
		try (BufferedReader br = new BufferedReader(new FileReader("./data/pocket.dic")))
		{
			br.lines().forEach(word -> {
	        int n = word.length();
	        permute(word, 0, n - 1);
			});
		} catch (Exception ex)
		{
			System.err.println(ex);
			System.exit(-1);
		}
    }
    private void permute(String str, int l, int r) 
    { 
        if (l == r) 
            System.out.println(str); 
        else { 
            for (int i = l; i <= r; i++) { 
                str = swap(str, l, i); 
                permute(str, l + 1, r); 
                str = swap(str, l, i); 
            } 
        } 
    }
    public String swap(String a, int i, int j) 
    { 
        char temp; 
        char[] charArray = a.toCharArray(); 
        temp = charArray[i]; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return String.valueOf(charArray); 
    } 
}
