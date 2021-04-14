package edu.mccc.cos210.pal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class Pal {

	public static void main(String[] args) {
		new Pal().doIt();
	}

	private void doIt() {
		Comparator<String> sorting = (a, b) -> b.length() - a.length();
		//Holder holder = new Holder();
		try (Stream<String> lines = Files.lines(Path.of("./data/pocket.dic"))) {
			lines
			.sorted(sorting)
			.flatMap(str -> Stream.of(Holder.isPalindrome(str)))
				.forEach(b -> {
		            if(b) System.out.println();
		        })
			;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static class Holder {
		private String fwd;
		private String bwd;
		private Holder(String fwd) {
			this.fwd = fwd;
			this.bwd = reverse(fwd);
		}
		private static String reverse(String s) {
			String revStr = "";
			 for ( int i = s.length() - 1; i >= 0; i-- )
			 {
				 revStr = revStr + s.charAt(i);
			 }
			 //System.out.println(s + ":" + revStr);
			return revStr;
		}
		private String getFwd() {
			return this.fwd;
		}
		private static boolean isPalindrome(String str) {
			if(str.length() >= 7)
			{
				if(str.equals(reverse(str)))
				{
					System.out.println(str);
					return true;
				}
			}
			return false;
		}
	}

}