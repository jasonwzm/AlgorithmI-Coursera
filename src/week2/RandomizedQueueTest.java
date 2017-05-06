package week2;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class RandomizedQueueTest {

	@Test
	public void testEnqueue() {
		RandomizedQueue<Item> rq = new RandomizedQueue<>();
		Item item1 = new Item();
		Item item2 = new Item();
		rq.enqueue(item1);
		assertEquals(rq.size(), 1);
		rq.enqueue(item2);
		assertEquals(rq.size(), 2);
	}

	@Test
	public void testIterator() {
		RandomizedQueue<Item> rq = new RandomizedQueue<>();
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		Item item4 = new Item();
		rq.enqueue(item1);
		rq.enqueue(item2);
		rq.enqueue(item3);
		rq.enqueue(item4);
		Iterator<Item> it = rq.iterator();
		int count = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			count++;
		}
		assertEquals(count, 4);
	}

	@Test
	public void testResize() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.enqueue(1);
		rq.enqueue(3);
		rq.enqueue(4);
		rq.dequeue();
		rq.dequeue();
		assertEquals(rq.size(), 1);
	}
	
	@Test
	public void testIteratorInteger() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.enqueue(1);
		rq.enqueue(3);
		rq.enqueue(4);
		rq.dequeue();
		rq.dequeue();
		Iterator<Integer> it = rq.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().intValue());
		}
	}

}
