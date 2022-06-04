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
public class Compare2ValuesMap {
	public static void main(String[] args) throws IOException {
		Map<String, String> newMap = new HashMap<String, String>();
		Map<String, String> oldMap = new HashMap<String, String>();
		FileReader reader = new FileReader("NewCases.properties");
		Properties p = new Properties();
		p.load(reader);

		Enumeration<?> mKeys = p.propertyNames();
		while (mKeys.hasMoreElements()) {
			String key = (String) mKeys.nextElement();
			newMap.put(key, p.getProperty(key));
		}
		reader.close();

		Map<String, String> metricsMap = new HashMap<String, String>();

		reader = new FileReader("OldCases.properties");
		p = new Properties();
		p.load(reader);

		mKeys = p.propertyNames();
		while (mKeys.hasMoreElements()) {
			String key = (String) mKeys.nextElement();
			oldMap.put(key, p.getProperty(key));
		}
		reader.close();

		Map<String, String> existYes = new HashMap<String, String>();
		Map<String, String> existNo = new HashMap<String, String>();
		for (String key : newMap.keySet()) {
			if (oldMap.keySet().contains(key)) {
				existYes.put(key, newMap.get(key));
			} else {
				existNo.put(key, newMap.get(key));
			}
		}

		int expected = newMap.keySet().size();
		int actual = existNo.size() + existYes.size();
		int oldcount = oldMap.keySet().size();

		if (expected == actual) {
			System.out.println("Exptected New case count " + expected + "  is All good ");
		}
		if (oldcount == existYes.size()) {
			System.out.println("Old count is " + oldcount + " and exist yes is " + existYes.size());
		} else {
			System.out.println("Found issue");
			System.out.println("Old count is " + oldcount + " and exist yes is " + existYes.size());
			Map<String, String> oldexistYes = new HashMap<String, String>();
			Map<String, String> oldexistNo = new HashMap<String, String>();
			for (String key : oldMap.keySet()) {
				if (existYes.keySet().contains(key)) {
					oldexistYes.put(key, oldMap.get(key));
				} else {
					oldexistNo.put(key, oldMap.get(key));
				}
			}
			int count = 0;
			System.out.println(
					"~~~ *****************\n the count of the cases which are present in old suite but not in existing in new suite  ");
			for (String k : oldexistNo.keySet()) {
				count++;
				String key = k.toString();
				System.out.println(count + " : " + key + " - " + oldexistNo.get(key));
			} /*
				 * count = 0; System.out.println("~~~ *****************\n Old Exist Yes Map ");
				 * for (String k : oldexistYes) { count++; String key = k.toString();
				 * System.out.println(count + " : " + key); }
				 */
			expected = oldMap.keySet().size();
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
		for (String k : existNo.keySet()) {
			count++;
			String key = k.toString();
			System.out.println(count + " : " + key + " - " + existNo.get(key));
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
