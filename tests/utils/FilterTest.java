package utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FilterTest {
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Filter filter = new Filter();
		filter.createFilePathFilter();
		int numFiles = filter.getNumberOfFiles();
		System.out.println(numFiles);
		assertTrue(numFiles > 0);
	}
	
	@Test
	public void testRand() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("TEST1");
		list.add("TEST2");
		list.add("TEST3");
		list.add("TEST4");
		Random rand = new Random();
		System.out.println(rand.nextInt(list.size()));
	}

}
