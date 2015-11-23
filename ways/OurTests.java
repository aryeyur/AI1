package technion.ac.il.cs236369.hw2.testCases;

import static org.junit.Assert.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.junit.Test;

/**
 * @author Amir
 *
 */
public class OurTests extends BaseTest {
	public static final String c_DumpFilePath = "D:\\workspace\\wikidata-20150817-partial.json";
	public static final String c_First20FilePath = "D:\\workspace\\first-20.json";
	public static final String c_FullDump = "D:\\workspace\\wikidata-20150817-partial.json";
	
	
	/**
	 * test one base element
	 */
	@Test
	public void test1() {
		Set<String> baseItems = new HashSet<String>();
		baseItems.add("Q42");
		Path path = FileSystems.getDefault().getPath(c_First20FilePath);
		JSONArray actual = parser.find(path, baseItems, 1);
		String expected = "[{\"id\":\"Q24\",\"similarToId\":\"Q42\",\"similarities\":[{\"propertyId\":\"P21\",\"propertyLabel\":null,\"similarValue\":\"Q6581097\",\"similarValueLabel\":null}],\"label\":\"Jack Bauer\",\"similarToLabel\":\"Douglas Adams\",\"dos\":1},{\"id\":\"Q23\",\"similarToId\":\"Q42\",\"similarities\":[{\"propertyId\":\"P21\",\"propertyLabel\":null,\"similarValue\":\"Q6581097\",\"similarValueLabel\":null},{\"propertyId\":\"P1412\",\"propertyLabel\":null,\"similarValue\":\"Q1860\",\"similarValueLabel\":null},{\"propertyId\":\"P103\",\"propertyLabel\":null,\"similarValue\":\"Q1860\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q5\",\"similarValueLabel\":null}],\"label\":\"George Washington\",\"similarToLabel\":\"Douglas Adams\",\"dos\":4}]";
//		System.out.println("expected: " + expected);
//		System.out.println("actual: " + actual.toJSONString());
		try {
			if (SimilarItem.equals(expected, actual)) {
				System.out.println("BasicTest Passed");
			} else {
				throw new AssertionError("Not Equal");
			}
		} catch (AssertionError e) {
			System.out.println("BasicTest NOT Passed !!!");
			System.out.println("Expecting : " + expected);
			System.out.println("GOT : " + actual.toJSONString());
			throw e;
		} 
	}
	 
	/**
	 *test two base elements 
	 */
	@Test
	public void test2(){
		Set<String> baseItems = new HashSet<String>();
		baseItems.add("Q42");
		baseItems.add("Q31");
		Path path = FileSystems.getDefault().getPath(c_First20FilePath);
		JSONArray actual = parser.find(path, baseItems, 1);
		String expected = "[{\"id\":\"Q23\",\"similarToId\":\"Q42\",\"similarities\":[{\"propertyId\":\"P103\",\"propertyLabel\":null,\"similarValue\":\"Q1860\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q5\",\"similarValueLabel\":null},{\"propertyId\":\"P21\",\"propertyLabel\":null,\"similarValue\":\"Q6581097\",\"similarValueLabel\":null},{\"propertyId\":\"P1412\",\"propertyLabel\":null,\"similarValue\":\"Q1860\",\"similarValueLabel\":null}],\"label\":\"George Washington\",\"similarToLabel\":\"Douglas Adams\",\"dos\":4},{\"id\":\"Q24\",\"similarToId\":\"Q42\",\"similarities\":[{\"propertyId\":\"P21\",\"propertyLabel\":null,\"similarValue\":\"Q6581097\",\"similarValueLabel\":null}],\"label\":\"Jack Bauer\",\"similarToLabel\":\"Douglas Adams\",\"dos\":1},{\"id\":\"Q45\",\"similarToId\":\"Q31\",\"similarities\":[{\"propertyId\":\"P530\",\"propertyLabel\":null,\"similarValue\":\"Q38\",\"similarValueLabel\":null},{\"propertyId\":\"P530\",\"propertyLabel\":null,\"similarValue\":\"Q183\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q1065\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q3624078\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q151991\",\"similarValueLabel\":null},{\"propertyId\":\"P131\",\"propertyLabel\":null,\"similarValue\":\"Q1969730\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q6256\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q7184\",\"similarValueLabel\":null},{\"propertyId\":\"P30\",\"propertyLabel\":null,\"similarValue\":\"Q46\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q458\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q42262\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q8908\",\"similarValueLabel\":null},{\"propertyId\":\"P38\",\"propertyLabel\":null,\"similarValue\":\"Q4916\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q41550\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q7825\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q185441\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q81299\",\"similarValueLabel\":null}],\"label\":\"Portugal\",\"similarToLabel\":\"Belgium\",\"dos\":17},{\"id\":\"Q33\",\"similarToId\":\"Q31\",\"similarities\":[{\"propertyId\":\"P530\",\"propertyLabel\":null,\"similarValue\":\"Q38\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q160016\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q151991\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q3624078\",\"similarValueLabel\":null},{\"propertyId\":\"P30\",\"propertyLabel\":null,\"similarValue\":\"Q46\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q41550\",\"similarValueLabel\":null},{\"propertyId\":\"P131\",\"propertyLabel\":null,\"similarValue\":\"Q1969730\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q1065\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q6256\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q6505795\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q7825\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q8908\",\"similarValueLabel\":null},{\"propertyId\":\"P530\",\"propertyLabel\":null,\"similarValue\":\"Q183\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q81299\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q185441\",\"similarValueLabel\":null},{\"propertyId\":\"P421\",\"propertyLabel\":null,\"similarValue\":\"Q6723\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q42262\",\"similarValueLabel\":null},{\"propertyId\":\"P463\",\"propertyLabel\":null,\"similarValue\":\"Q458\",\"similarValueLabel\":null},{\"propertyId\":\"P38\",\"propertyLabel\":null,\"similarValue\":\"Q4916\",\"similarValueLabel\":null}],\"label\":\"Finland\",\"similarToLabel\":\"Belgium\",\"dos\":19}]";
//		System.out.println("expected: " + expected);
//		System.out.println("actual: " + actual.toJSONString());
		try {
			if (SimilarItem.equals(expected, actual)) {
				System.out.println("BasicTest Passed");
			} else {
				throw new AssertionError("Not Equal");
			}
		} catch (AssertionError e) {
			System.out.println("BasicTest NOT Passed !!!");
			System.out.println("Expecting : " + expected);
			System.out.println("GOT : " + actual.toJSONString());
			throw e;
		} 
	}
	
	
	/**
	 * stress test - parse 3.5 GB file
	 */
	@Test
	public void test3() {
		Set<String> baseItems = new HashSet<String>();
		baseItems.add("Q51");
		Path path = FileSystems.getDefault().getPath(c_DumpFilePath);
		JSONArray actual = parser.find(path, baseItems, 1);
		String expected = "[{\"id\":\"Q101949\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"mantle\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q1902276\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P18\",\"propertyLabel\":null,\"similarValue\":\"Antarctica 6400px from Blue Marble.jpg\",\"similarValueLabel\":null}],\"label\":\"Mawson Sea\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q41228\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Southern Hemisphere\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q39061\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Northern Hemisphere\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q83222\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P706\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Mesozoic\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q193927\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Earth's core\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q538\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q5107\",\"similarValueLabel\":null}],\"label\":\"Oceania\",\"similarToLabel\":\"Antarctica\",\"dos\":2}]";
//		System.out.println("expected: " + expected);
//		System.out.println("actual: " + actual.toJSONString());
		try {
			if (SimilarItem.equals(expected, actual)) {
				System.out.println("BasicTest Passed");
			} else {
				throw new AssertionError("Not Equal");
			}
		} catch (AssertionError e) {
			System.out.println("BasicTest NOT Passed !!!");
			System.out.println("Expecting : " + expected);
			System.out.println("GOT : " + actual.toJSONString());
			throw e;
		} 
	}
	
	/**
	 * stress with more then one base element
	 */
	@Test
	public void test4() {
		Set<String> baseItems = new HashSet<String>();
		baseItems.add("Q39061");
		baseItems.add("Q2");
		Path path = FileSystems.getDefault().getPath(c_DumpFilePath);
		JSONArray actual = parser.find(path, baseItems, 1);
		String expected = "[{\"id\":\"Q41228\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q399984\",\"similarValueLabel\":null},{\"propertyId\":\"P373\",\"propertyLabel\":null,\"similarValue\":\"Maps of Earth's hemispheres\",\"similarValueLabel\":null}],\"label\":\"Southern Hemisphere\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":3},{\"id\":\"Q538\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Oceania\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1},{\"id\":\"Q101949\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"mantle\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1},{\"id\":\"Q193927\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Earth's core\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1},{\"id\":\"Q51\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Antarctica\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1}]";
//		System.out.println("expected: " + expected);
//		System.out.println("actual: " + actual.toJSONString());
		try {
			if (SimilarItem.equals(expected, actual)) {
				System.out.println("BasicTest Passed");
			} else {
				throw new AssertionError("Not Equal");
			}
		} catch (AssertionError e) {
			System.out.println("BasicTest NOT Passed !!!");
			System.out.println("Expecting : " + expected);
			System.out.println("GOT : " + actual.toJSONString());
			throw e;
		} 
	}
	
	/**
	 * last test with three base elements
	 */
	@Test
	public void test5() {
		Set<String> baseItems = new HashSet<String>();
		baseItems.add("Q39061");
		baseItems.add("Q2");
		baseItems.add("Q51");
		Path path = FileSystems.getDefault().getPath(c_DumpFilePath);
		JSONArray actual = parser.find(path, baseItems, 1);
		String expected = "[{\"id\":\"Q193927\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Earth's core\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1},{\"id\":\"Q39061\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Northern Hemisphere\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q51\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Antarctica\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1},{\"id\":\"Q41228\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Southern Hemisphere\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q83222\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P706\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Mesozoic\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q41228\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q399984\",\"similarValueLabel\":null},{\"propertyId\":\"P373\",\"propertyLabel\":null,\"similarValue\":\"Maps of Earth's hemispheres\",\"similarValueLabel\":null},{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Southern Hemisphere\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":3},{\"id\":\"Q538\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null},{\"propertyId\":\"P31\",\"propertyLabel\":\"instance of\",\"similarValue\":\"Q5107\",\"similarValueLabel\":null}],\"label\":\"Oceania\",\"similarToLabel\":\"Antarctica\",\"dos\":2},{\"id\":\"Q193927\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Earth's core\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q101949\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"mantle\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q1902276\",\"similarToId\":\"Q51\",\"similarities\":[{\"propertyId\":\"P18\",\"propertyLabel\":null,\"similarValue\":\"Antarctica 6400px from Blue Marble.jpg\",\"similarValueLabel\":null}],\"label\":\"Mawson Sea\",\"similarToLabel\":\"Antarctica\",\"dos\":1},{\"id\":\"Q101949\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"mantle\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1},{\"id\":\"Q538\",\"similarToId\":\"Q39061\",\"similarities\":[{\"propertyId\":\"P361\",\"propertyLabel\":null,\"similarValue\":\"Q2\",\"similarValueLabel\":null}],\"label\":\"Oceania\",\"similarToLabel\":\"Northern Hemisphere\",\"dos\":1}]";
//		System.out.println("expected: " + expected);
//		System.out.println("actual: " + actual.toJSONString());
		try {
			if (SimilarItem.equals(expected, actual)) {
				System.out.println("BasicTest Passed");
			} else {
				throw new AssertionError("Not Equal");
			}
		} catch (AssertionError e) {
			System.out.println("BasicTest NOT Passed !!!");
			System.out.println("Expecting : " + expected);
			System.out.println("GOT : " + actual.toJSONString());
			throw e;
		} 
	}
}
