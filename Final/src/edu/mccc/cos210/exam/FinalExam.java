package edu.mccc.cos210.exam;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.util.Utility;

public class FinalExam {

	public static void main(String[] args) {
		new FinalExam().doIt();
	}

	private void doIt() {
		try (
			Stream<String> ss = Files.lines(
				Paths.get("./data/final.txt")
			)
		) {
			ISinglyLinkedList<String> list = ss
				.map(s -> new GradeGenerator(s).toString())
				.sorted(
					 ((Comparator<String>) FinalExam::compareGrade).reversed()
					.thenComparing(FinalExam::compareLastName)
				 )
				.collect(Utility.toSinglyLinkedList())
			;

			list.forEach(System.out::println);

			computeAverages(list);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			System.exit(-1);
		}
	}

	@SuppressWarnings("unused")
	private static void computeAverages(ISinglyLinkedList<String> list) {
		double t1 = 0.0;
		double t2 = 0.0;
		double t3 = 0.0;
		double tg = 0.0;
		for (String s : list) {
			Matcher m = p1.matcher(s);
			if (m.matches() && m.groupCount() == 4) {
				t1 = Double.parseDouble(m.group(1));
				t2 = Double.parseDouble(m.group(2));
				t3 = Double.parseDouble(m.group(3));
				tg = Double.parseDouble(m.group(4));
			}
		}
	}
	
	private static int compareLastName(String one, String two) {
		String s1 = one.substring(one.indexOf("_") + 1, one.indexOf(" "));
		String s2 = two.substring(two.indexOf("_") + 1, two.indexOf(" "));
		return s1.compareTo(s2);
	}

	private static Pattern p1 = Pattern.compile(
		"[A-Za-z_]+ ([0-9]+) ([0-9]+) ([0-9]+) ([0-9.]+) [A-F+-]+"
	);

	private static int compareGrade(String one, String two) {
		String s1 = "0.0";
		String s2 = "0.0";
		Matcher m1 = p1.matcher(one);
		Matcher m2 = p1.matcher(two);
		if (m1.matches() && m1.groupCount() == 4) {
			s1 = m1.group(4);
		}
		if (m2.matches() && m2.groupCount() == 4) {
			s2 = m2.group(4);
		}
		return Double.valueOf(s1).compareTo(Double.valueOf(s2));
	}

	private static class GradeGenerator {
		private String name = "Unknown";
		private String letterGrade = "F";
		private double numGrade = 0;
		private int score1 = 0;
		private int score2 = 0;
		private int score3 = 0;

		private GradeGenerator(String s) {
			Matcher m = Pattern.compile(".*").matcher(s);

			if (m.matches() && m.groupCount() == 4) {
				name = m.group(1);
				score1 = Integer.parseInt(m.group(2));
				score2 = Integer.parseInt(m.group(2));
				score3 = Integer.parseInt(m.group(2));
				numGrade = (score1 + score2 + score3) / 3.0;
				letterGrade = computeLetterGrade(numGrade);
			}
		}

		private String computeLetterGrade(double numGrade) {
			String letter = "F";
			
			IArray<Double> grades = Stream.of(
				100.0, 92.0, 90.0, 88.0, 82.0, 80.0, 78.0, 70.0, 60.0, 0.0
			).collect(Utility.toArray());
			IArray<String> letters = Stream.of(
				"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D", "F"
			).collect(Utility.toArray());

			for (int i = 0; i < grades.getSize(); i++) {
				if (numGrade >= grades.get(i)) {
					letter = letters.get(i);
					break;
				}
			}

			return letter;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(name.replace(' ', '_'));
			sb.append(' ');
			sb.append(score1);
			sb.append(' ');
			sb.append(score2);
			sb.append(' ');
			sb.append(score3);
			sb.append(' ');
			sb.append(String.format("%.1f", numGrade));
			sb.append(' ');
			sb.append(letterGrade);

			return sb.toString();
		}

	}

}
