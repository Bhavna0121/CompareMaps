package comparefile.MetricsStatusCompareWithLocal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JSONFileKEyValue {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		String testdata = "Feature Bar;Repository#Toolbar#Create";
		String duplicatetestdata = "Feature Bar;Repository#Toolbar#Create";
		Map<String, Object> globalMap = new HashMap<>();
		// read json file
		// and put it in format like . and . and . like this
		// kept as per the testdata name = key towards value as that word
		// read xml and fetch all . and . and . data key and values
		// check the difference of . and . and .

		// check the values from json and xmls of english keywords which could be
		// replaced
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
							JSONObject jsonObject2 = (JSONObject) jsonObject1.get(k1);
							Iterator<String> key2 = jsonObject2.keys();
							while (key2.hasNext()) {
								String k2 = key2.next();
								System.out.println("key name 2 = " + k2);
								if (jsonObject2.get(k2) instanceof JSONObject) {
									JSONObject jsonObject3 = (JSONObject) jsonObject2.get(k2);
									Iterator<String> key3 = jsonObject3.keys();
									while (key3.hasNext()) {
										String k3 = key3.next();
										System.out.println("key name 3 = " + k3);
										if (jsonObject3.get(k3) instanceof JSONObject) {
											JSONObject jsonObject4 = (JSONObject) jsonObject3.get(k3);
											Iterator<String> key4 = jsonObject4.keys();
											while (key4.hasNext()) {
												String k4 = key4.next();
												System.out.println("key name 4 = " + k4);
												if (jsonObject4.get(k4) instanceof JSONObject) {
													JSONObject jsonObject5 = (JSONObject) jsonObject4.get(k4);
													Iterator<String> key5 = jsonObject5.keys();
													while (key5.hasNext()) {
														String k5 = key5.next();
														System.out.println("key name 5 = " + k5);
														if (jsonObject5.get(k5) instanceof JSONObject) {
															System.out.println("found of jsonObject");
														} else if (jsonObject5.get(k5) instanceof JSONArray) {
															System.out.println("found of jsonObject");
														} else if (jsonObject5.get(k5) instanceof String) {
															globalMap.put(key + "." + k1 + "." + k2 + "." + k3 + "."
																	+ k4 + "." + k5, jsonObject5.get(k5));
															System.out.println(
																	key + "." + k1 + "." + k2 + "." + k3 + "." + k4
																			+ "." + k5 + "  = " + jsonObject5.get(k5));
														} else if (jsonObject5.get(k5) instanceof Boolean) {
															globalMap.put(key + "." + k1 + "." + k2 + "." + k3 + "."
																	+ k4 + "." + k5, jsonObject5.get(k5));
															System.out.println(
																	key + "." + k1 + "." + k2 + "." + k3 + "." + k4
																			+ "." + k5 + "  = " + jsonObject5.get(k5));
														}
													}
												} else if (jsonObject4.get(k4) instanceof JSONArray) {
													System.out.println("found of jsonObject");
												} else if (jsonObject4.get(k4) instanceof String) {
													globalMap.put(key + "." + k1 + "." + k2 + "." + k3 + "." + k4,
															jsonObject4.get(k4));
													System.out.println(key + "." + k1 + "." + k2 + "." + k3 + "." + k4
															+ "  = " + jsonObject4.get(k3));
												} else if (jsonObject4.get(k4) instanceof Boolean) {
													globalMap.put(key + "." + k1 + "." + k2 + "." + k3 + "." + k4,
															jsonObject4.get(k4));
													System.out.println("found of boolean");
												}
											}
										} else if (jsonObject3.get(k3) instanceof JSONArray) {
											System.out.println("found of jsonObject");
										} else if (jsonObject3.get(k3) instanceof String) {
											globalMap.put(key + "." + k1 + "." + k2 + "." + k3, jsonObject3.get(k3));
											System.out.println("found of string");
										} else if (jsonObject3.get(k3) instanceof Boolean) {
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
									globalMap.put(key + "." + k1 + "." + k2, jsonObject2.get(k2));
									System.out.println("found of boolean");
								}
							}
							// do something with jsonObject here
						} else if (jsonObject1.get(k1) instanceof JSONArray) {
							System.out.println("found of jsonArray");
							// do something with jsonObject here
						} else if (jsonObject1.get(k1) instanceof String) {
							globalMap.put(key + "." + k1, jsonObject1.get(k1));
							System.out.println("found of string");
						} else if (jsonObject1.get(k1) instanceof Boolean) {
							globalMap.put(key + "." + k1, jsonObject1.get(k1));
							System.out.println("found of boolean");
						}
					}
				} else if (jsonObject.get(key) instanceof JSONArray) {
					System.out.println("found of jsonArray");
					// do something with jsonObject here
				} else if (jsonObject.get(key) instanceof String) {
					globalMap.put(key, jsonObject.get(key));
					System.out.println("found of string");
				} else if (jsonObject.get(key) instanceof Boolean) {
					globalMap.put(key, jsonObject.get(key));
					System.out.println("found of boolean");
				}
			}
			/*
			 * BufferedWriter writer = new BufferedWriter(new FileWriter("12english.txt",
			 * true)); for (String k : globalMap.keySet()) { Object value =
			 * globalMap.get(k); writer.append(k + " = " + value + "\n"); } writer.close();
			 */

			List<String> items = Arrays.asList(testdata.split(";"));
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).contains("#")) {
					String[] it = items.get(i).split("#");
					for (int k = 0; k < it.length; k++) {
						for (Entry<String, Object> entry : globalMap.entrySet()) {
							if (entry.getValue().equals(it[k])) {
								duplicatetestdata = duplicatetestdata.replace(it[k], entry.getKey());
								break;
							}
						}
					}
					// check here if matches
				} else {
					// check here
					for (Entry<String, Object> entry : globalMap.entrySet()) {
						if (entry.getValue().equals(items.get(i))) {
							duplicatetestdata = duplicatetestdata.replace(items.get(i), entry.getKey());
							break;
						}
					}
				}
			}
			System.out.println(duplicatetestdata);
		}
	}

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
