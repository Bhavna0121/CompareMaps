package comparefile.MetricsStatusCompareWithLocal;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Compare2ValuesSet {
	public static void main(String[] args) throws IOException {
		Set<String> newMap = new HashSet();
		Set<String> oldMap = new HashSet();
		FileReader reader = new FileReader("NewCases.properties");
		Properties p = new Properties();
		p.load(reader);

		Enumeration<?> mKeys = p.propertyNames();
		while (mKeys.hasMoreElements()) {
			String key = (String) mKeys.nextElement();
			newMap.add(key);
		}
		reader.close();

		Set<String> metricsMap = new HashSet();
		reader = new FileReader("OldCases.properties");
		p = new Properties();
		p.load(reader);

		mKeys = p.propertyNames();
		while (mKeys.hasMoreElements()) {
			String key = (String) mKeys.nextElement();
			oldMap.add(key);
		}
		reader.close();

		Set<String> existYes = new HashSet();
		Set<String> existNo = new HashSet();
		for (String key : newMap) {
			if (oldMap.contains(key)) {
				existYes.add(key);
			} else {
				existNo.add(key);
			}
		}

		int expected = newMap.size();
		int actual = existNo.size() + existYes.size();
		int oldcount = oldMap.size();

		if (expected == actual) {
			System.out.println("Exptected New case count " + expected + "  is All good ");
		}
		if (oldcount == existYes.size()) {
			System.out.println("Old count is " + oldcount + " and exist yes is " + existYes.size());
		} else {
			System.out.println("Found issue");
			System.out.println("Old count is " + oldcount + " and exist yes is " + existYes.size());
			Set<String> oldexistYes = new HashSet();
			Set<String> oldexistNo = new HashSet();
			for (String key : oldMap) {
				if (existYes.contains(key)) {
					oldexistYes.add(key);
				} else {
					oldexistNo.add(key);
				}
			}
			int count = 0;
			System.out.println(
					"~~~ *****************\n the count of the cases which are present in old suite but not in existing in new suite  ");
			for (String k : oldexistNo) {
				count++;
				String key = k.toString();
				System.out.println(count + " : " + key);
			} /*
				 * count = 0; System.out.println("~~~ *****************\n Old Exist Yes Map ");
				 * for (String k : oldexistYes) { count++; String key = k.toString();
				 * System.out.println(count + " : " + key); }
				 */
			expected = oldMap.size();
			actual = oldexistNo.size() + oldexistYes.size();
			oldcount = existYes.size();

			if (expected == actual) {
				System.out.println("Exptected New case count " + expected + "  is All good ");
			}
			if (oldcount == oldexistYes.size()) {
				System.out.println("Old count is " + oldcount + " and exist yes is " + oldexistYes.size());
			} else {
				System.out.println("Issue ");
			}
		}
		// end

		System.out.println("\n the count of the cases existing in new suite but not in old suite  ");
		int count = 0;
		for (String k : existNo) {
			count++;
			String key = k.toString();
			System.out.println(key);
		}

		/*
		 * count = 0; System.out.println("\n exist Yes Map "); for (String k : existYes)
		 * { count++; String key = k.toString(); System.out.println(count + " : " +
		 * key); }
		 */
		/*
		 * Set<String> s = new HashSet<String>(metricsMap.keySet());
		 * s.retainAll(excelMap.keySet()); System.out.println(s);
		 * 
		 * MapDifference<String, String> diff = Maps.difference(metricsMap, excelMap);
		 * Set<String> keysOnlyInSource = diff.entriesOnlyOnLeft().keySet(); Set<String>
		 * keysOnlyInTarget = diff.entriesOnlyOnRight().keySet();
		 * System.out.println(keysOnlyInSource); System.out.println(keysOnlyInTarget);
		 */
	}
}
