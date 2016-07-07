import junit.framework.TestCase;

public class BooleanSetTest extends TestCase {
	
	public void testConstructor() {
		BooleanBooleanSet s = new BooleanBooleanSet(2);
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));
	}
	
	public void testAdd() {
		BooleanBooleanSet s = new BooleanBooleanSet(2);
		s.add(0);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));
		assertEquals(s.size(), 1)

		s.add(0);                // should have no effect
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));
		assertEquals(s.size(), 1)
	}
	
	public void testRemove() {
		BooleanSet s = new BooleanSet(2);
		s.add(0);
		s.remove(1);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);                // should have no effect
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));
	}
	
	public void testTwoAddsAndRemoves() {
		BooleanSet s = new BooleanSet(2);
		s.add(0);
		s.add(1);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertTrue (s.contains(1));

		s.remove(1);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(1);                // should have no effect
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);                // should have no effect
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));
	}

}
