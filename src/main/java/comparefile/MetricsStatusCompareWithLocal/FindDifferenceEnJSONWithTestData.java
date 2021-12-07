package comparefile.MetricsStatusCompareWithLocal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FindDifferenceEnJSONWithTestData {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		Map<String, Object> globalMap = new HashMap<>();
		File f = new File("en.json");
		if (f.exists()) {
			InputStream is = new FileInputStream("en.json");
			String jsonTxt = IOUtils.toString(is, "UTF-8");
			System.out.println(jsonTxt);
			JSONObject jsonObject = new JSONObject(jsonTxt);

			Iterator<String> keys = jsonObject.keys();

			while (keys.hasNext()) {
				String key = keys.next();
				System.out.println("key name = " + key);
				if (jsonObject.get(key) instanceof JSONObject) {
					System.out.println("found of jsonObject");
					JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
					Iterator<String> key1 = jsonObject1.keys();
					while (key1.hasNext()) {
						String k1 = key1.next();
						System.out.println("key name 1 = " + k1);
						if (jsonObject1.get(k1) instanceof JSONObject) {
							System.out.println("found of jsonObject");
							JSONObject jsonObject2 = (JSONObject) jsonObject1.get(k1);
							Iterator<String> key2 = jsonObject2.keys();
							while (key2.hasNext()) {
								String k2 = key2.next();
								System.out.println("key name 2 = " + k2);
								if (jsonObject2.get(k2) instanceof JSONObject) {
									System.out.println("found of jsonObject");
									JSONObject jsonObject3 = (JSONObject) jsonObject2.get(k2);
									Iterator<String> key3 = jsonObject3.keys();
									while (key3.hasNext()) {
										String k3 = key3.next();
										System.out.println("key name 3 = " + k3);
										if (jsonObject3.get(k3) instanceof JSONObject) {
											System.out.println("found of jsonObject");
										} else if (jsonObject3.get(k3) instanceof JSONArray) {
											System.out.println("found of jsonObject");
										} else if (jsonObject3.get(k3) instanceof String) {
											globalMap.put(key + "." + k1 + "." + k2 + "." + k3, jsonObject3.get(k3));
											System.out.println("found of string");
										} else if (jsonObject2.get(k2) instanceof Boolean) {
											globalMap.put(key + "." + k1 + "." + k2 + "." + k3, jsonObject3.get(k3));
											System.out.println("found of boolean");
										}
									}

								} else if (jsonObject2.get(k2) instanceof JSONArray) {
									System.out.println("found of jsonObject");
								} else if (jsonObject2.get(k2) instanceof String) {
									globalMap.put(key + "." + k1 + "." + k2, jsonObject2.get(k2));
									System.out.println("found of string");
								} else if (jsonObject2.get(k2) instanceof Boolean) {
									globalMap.put(key + "." + k1, jsonObject2.get(k2));
									System.out.println("found of boolean");
								}
							}

						} else if (jsonObject1.get(k1) instanceof JSONArray) {
							System.out.println("found of jsonObject");
						} else if (jsonObject1.get(k1) instanceof String) {
							globalMap.put(key + "." + k1, jsonObject1.get(k1));
							System.out.println("found of string");
						} else if (jsonObject1.get(k1) instanceof Boolean) {
							globalMap.put(key + "." + k1, jsonObject1.get(k1));
							System.out.println("found of boolean");
						}
					}
					// do something with jsonObject here
				} else if (jsonObject.get(key) instanceof JSONArray) {
					System.out.println("found of jsonArray");
					// do something with jsonObject here
				}
			}
			System.out.println("********************************\n \n ");
			for (String k : globalMap.keySet()) {
				String key = k.toString();
				Object value = globalMap.get(k);
				System.out.println(key + " = " + value);
			}
		}

		System.out.println("********************************\n \n ");
		Set<String> set = new HashSet<>();

		File folder = new File("D:\\Winshuttle\\AutFW10Sep\\WSProducts\\UI\\EnterworksUITest\\TestData");

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().contains("TempMapData")) {
				continue;
			}
			if (fileEntry.getName().contains("InputFiles")) {
				continue;
			}
			System.out.println(fileEntry.getName());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileEntry);
			doc.getDocumentElement().normalize();
			NodeList insideNodeList = doc.getElementsByTagName("*");
			Map insideNodeMap = getElementKeyValue(insideNodeList);
			Iterator it = insideNodeMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				System.out.println(pairs.getKey() + " = " + pairs.getValue());
				if (pairs.getKey().equals("Iteration")) {
					continue;
				} else if (pairs.getKey().equals("TestCaseData")) {
					continue;
				} else if (pairs.getKey().equals("Attributes")) {
					continue;
				}
				if (pairs.getValue().toString().contains(".")) {
					set.add((String) pairs.getValue());
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("CompareENTest.txt", true));
		for (String s : globalMap.keySet()) {
			if (set.contains(s)) {
				System.out.println("Found key");
			} else {
				System.out.println("not found");
				writer.append("Key is - " + s + "\n");
			}
		}
		writer.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getElementKeyValue(NodeList nodeList) {
		Map elements = new HashMap();
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); // Take the node from the list
				NodeList valueNode = node.getChildNodes(); // get the children of the node
				String value = (valueNode.item(0) != null) ? valueNode.item(0).getNodeValue() : "";
				elements.put(node.getNodeName(), value);
			}
		}
		return elements;
	}

}
