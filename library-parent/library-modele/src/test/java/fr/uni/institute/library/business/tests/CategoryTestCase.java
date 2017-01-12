package fr.uni.institute.library.business.tests;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.uni.institute.library.business.inventory.Category;
import fr.uni.institute.library.business.inventory.Title;

public class CategoryTestCase  {
		
	private Category category;
	private Title title;
	private int numberTiles;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		category = new Category(1, "Sciences");
		title = new Title(1,"Media1");
		numberTiles = 0;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		category = null;
		title = null;
	}
	
	@Test
	public void testAddTitle() {
		category.addTitle(title);
		assertEquals(numberTiles + 1, category.getTitles().size());
	}

}
