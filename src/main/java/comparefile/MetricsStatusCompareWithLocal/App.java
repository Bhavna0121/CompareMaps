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
public class App {
	public static void main(String[] args) throws IOException {
		Set<String> myfilenames = new HashSet<>();
		FileReader reader = new FileReader("ExcelSheet.properties");
		Properties p = new Properties();
		p.load(reader);

		Enumeration<?> enumkeys = p.propertyNames();
		while (enumkeys.hasMoreElements()) {
			String key = (String) enumkeys.nextElement();
			myfilenames.add(key);
		}
		reader.close();

		Map<String, String> metricsMap = new HashMap<String, String>();

		reader = new FileReader("MetricsResult.properties");
		p = new Properties();
		p.load(reader);

		Enumeration<?> mKeys = p.propertyNames();
		while (mKeys.hasMoreElements()) {
			String key = (String) mKeys.nextElement();
			metricsMap.put(key, p.getProperty(key));
		}
		reader.close();

		Map<String, String> passMap = new HashMap<>();
		Map<String, String> failMap = new HashMap<>();
		Map<String, String> leftMap = new HashMap<>();
		Map<String, String> containsTmportal = new HashMap<>();
		// my keys are excelmap keys
		for (String key : myfilenames) {
			if (metricsMap.containsKey(key)) {
				if (metricsMap.get(key).contains(("Pass"))) {
					passMap.put(key, metricsMap.get(key));
				} else if (metricsMap.get(key).contains(("Fail"))) {
					failMap.put(key, metricsMap.get(key));
				} else {
					// leftMap.put(key, excelMap.get(key));
					containsTmportal.put(key, metricsMap.get(key));
				}
			} else {
				leftMap.put(key, "");
			}
		}
		
		int expected = passMap.size() + failMap.size() + leftMap.size();
		int actual = myfilenames.size();
		int tmpo = containsTmportal.size();

		if (expected == actual) {
			System.out.println("All good ");
		} else if (tmpo == actual) {
			System.out.println("All good ");
		} else {
			System.out.println("Issue with this code");
		}

		System.out.println("\n TMPortal Map ");
		int count = 0;
		for (String k : containsTmportal.keySet()) {
			count++;
			String key = k.toString();
			String value = containsTmportal.get(k);
			System.out.println(count + " : " + key + " = " + value);
		}
		count = 0;
		System.out.println("\n Pass Map ");
		for (String k : passMap.keySet()) {
			count++;
			String key = k.toString();
			String value = passMap.get(k);
			System.out.println(count + " : " + key + " = " + value);
		}
		count = 0;
		System.out.println("\n Fail Map ");
		for (String k : failMap.keySet()) {
			count++;
			String key = k.toString();
			String value = failMap.get(k);
			System.out.println(count + " : " + key + " = " + value);
		}
		count = 0;
		System.out.println("\n Left Map ");
		for (String k : leftMap.keySet()) {
			count++;
			String key = k.toString();
			System.out.println(count + " : " + key);
		}
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
