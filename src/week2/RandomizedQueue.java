package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int end;
	private Item[] queue;

	public RandomizedQueue() {
		// construct an empty randomized queue
		queue = (Item[]) new Object[1];
		end = 0;
	}

	public boolean isEmpty() {
		// is the queue empty?
		return end == 0;
	}

	public int size() {
		// return the number of items on the queue
		return end;
	}

	public void enqueue(Item item) {
		// add the item
		if (item == null) {
			throw new NullPointerException();
		}
		queue[end++] = item;
		
		if (end == queue.length) {
			resizeArray(queue.length * 2);
		}
	}

	public Item dequeue() {
		// remove and return a random item
		if (end == 0) {
			throw new NoSuchElementException();
		}
		int index = StdRandom.uniform(end);
		Item toReturn = queue[index];
		queue[index] = queue[--end];
		queue[end] = null;
		
		if (end <= queue.length / 4) {
			resizeArray(queue.length / 2);
		}

		return toReturn;
	}

	public Item sample() {
		// return (but do not remove) a random item
		if (end == 0) {
			throw new NoSuchElementException();
		}
		int index = StdRandom.uniform(end);
		return queue[index];
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item[] shuffleArray;
		private int current;

		public RandomizedQueueIterator() {
			shuffleArray = queue.clone();
			StdRandom.shuffle(shuffleArray);
			current = 0;
		}

		@Override
		public boolean hasNext() {
			return current < end;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return shuffleArray[current++];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private void resizeArray(int length) {
		Item[] newArray = (Item[]) new Object[length];
		for (int i = 0; i < queue.length; i++) {
			if (i >= newArray.length) {
				break;
			}
			newArray[i] = queue[i];
		}
		queue = newArray;
	}

}
