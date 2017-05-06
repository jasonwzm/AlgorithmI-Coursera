package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> pre;

		public Node() {
			this.next = null;
			this.pre = null;
		}

		public Node(Item item) {
			this.item = item;
			this.next = null;
			this.pre = null;
		}
	}

	private Node<Item> start;
	private Node<Item> end;
	private int size;

	public Deque() {
		// construct an empty deque
		size = 0;
		start = new Node<>();
		end = new Node<>();
		start.next = end;
		end.pre = start;
	}

	private class DequeIterator implements Iterator<Item> {

		private Node<Item> curr = start.next;

		@Override
		public boolean hasNext() {
			return curr != end;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = curr.item;
			curr = curr.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public boolean isEmpty() {
		// is the deque empty?
		return size == 0;
	}

	public int size() {
		// return the number of items on the deque
		return size;
	}

	public void addFirst(Item item) {
		// add the item to the front
		if (item == null) {
			throw new NullPointerException();
		}
		Node<Item> newNode = new Node<>(item);
		Node<Item> originalNext = start.next;
		start.next = newNode;
		newNode.next = originalNext;
		originalNext.pre = newNode;
		newNode.pre = start;
		size++;
	}

	public void addLast(Item item) {
		// add the item to the end
		if (item == null) {
			throw new NullPointerException();
		}
		Node<Item> newNode = new Node<>(item);
		Node<Item> originalPre = end.pre;
		end.pre = newNode;
		newNode.pre = originalPre;
		originalPre.next = newNode;
		newNode.next = end;
		size++;
	}

	public Item removeFirst() {
		// remove and return the item from the front
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<Item> toRemove = start.next;
		start.next = toRemove.next;
		start.next.pre = start;
		size--;
		return toRemove.item;
	}

	public Item removeLast() {
		// remove and return the item from the end
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<Item> toRemove = end.pre;
		end.pre = toRemove.pre;
		end.pre.next = end;
		size--;
		return toRemove.item;
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}

}
