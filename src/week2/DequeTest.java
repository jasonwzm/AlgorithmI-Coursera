package week2;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class DequeTest {

	@Test
	public void testAddFirst() {
		Deque<Item> d = new Deque<>();
		assertTrue(d.isEmpty());
		Item item = new Item();
		d.addFirst(item);
		assertFalse(d.isEmpty());
		assertEquals(1, d.size());
	}

	@Test
	public void testRemoveFirst() {
		Deque<Item> d = new Deque<>();
		assertTrue(d.isEmpty());
		Item item = new Item();
		d.addFirst(item);
		assertEquals(1, d.size());
		d.removeFirst();
		assertEquals(0, d.size());
	}

	@Test
	public void testAddFirstRemoveLast() {
		Deque<Item> d = new Deque<>();
		assertTrue(d.isEmpty());
		Item item = new Item();
		d.addFirst(item);
		assertEquals(1, d.size());
		d.removeLast();
		assertEquals(0, d.size());
	}

	@Test
	public void testAddFirstRemoveAndRepeat() {
		Deque<Item> d = new Deque<Item>();
		Item item = new Item();
		d.addFirst(item);
		assertTrue(item == d.removeFirst());
		Item item2 = new Item();
		d.addLast(item2);
		assertTrue(item2 == d.removeLast());
		Item item3 = new Item();
		d.addFirst(item3);
		assertTrue(item3 == d.removeLast());
		Item item4 = new Item();
		d.addLast(item4);
		assertTrue(item4 == d.removeFirst());
	}
	
	@Test
	public void testIterator() {
		Deque<Integer> d = new Deque<Integer>();
		d.addFirst(1);
		d.addFirst(2);
		d.addFirst(3);
		d.addLast(4);
		d.addLast(5);
		d.addFirst(6);
		Iterator<Integer> it = d.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().intValue());
		}
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemoveFirstOnEmpty() {
		Deque<Item> q = new Deque<>();
		q.removeFirst();
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testRemeveLastOnEmpty() {
		Deque<Item> d = new Deque<>();
		d.removeLast();
	}
}
