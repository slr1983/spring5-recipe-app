package guru.springframework.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {
	
	Category categoryTest = null;

	@Before
	public void setUp() throws Exception {
		
		categoryTest = new Category();
	}

	@Test
	public final void testGetId() {
		
		Long idValue  = 5L;
		
		categoryTest.setId(idValue);
		
		assertEquals(idValue, categoryTest.getId());
	}

	@Test
	public final void testGetDescription() {
		
	}

	@Test
	public final void testSetDescription() {
		
	}

}
