package com.arun;

public class MyLinkedList<T> implements Iterable<T> {

	private int theSize;
	private int modCount = 0;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	public MyLinkedList() {
		doClear();
	}

	private void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}

	public void clear() {
		doClear();
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean add(T x) {
		add(size(), x);
		return true;
	}

	public void add(int idx, T x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	private void addBefore(Node<T> p, T x) {
		p.prev = p.prev.next = new Node<T>(x, p.prev, p);
		theSize++;
		modCount++;
	}

	public T get(int idx) {
		return getNode(idx).data;
	}

	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}

	public boolean contains(T x) {
		return contains(x, beginMarker.next);
	}

	private boolean contains(T x, Node<T> t) {
		while (t.next != null) {
			if (t.data.equals(x))
				return true;
			t = t.next;
		}
		return false;
	}

	public T remove(int idx) {
		return remove(getNode(idx));
	}

	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		return p.data;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *            index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<T> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *            index to search at.
	 * @param lower
	 *            lowest valid index.
	 * @param upper
	 *            highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between lower and upper, inclusive.
	 */

	private Node<T> getNode(int idx, int lower, int upper) {
		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException();
		Node<T> p;
		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}
		return p;
	}

	// This method swaps the nodes at the given index positions by changing the
	// links, provided both positions are within the current size. Hence there
	// is no need to check whether the index is in range.
	// Note: index starts from 0
	public void swap(int idx1, int idx2) {
		Node<T> p1 = getNode(idx1);
		Node<T> p2 = getNode(idx2);
		Node<T> temp;
		if (Math.abs(idx1 - idx2) == 1) {
			p2.prev = p1.prev;
			p1.prev.next = p1.prev = p2;
			p1.next = p2.next;
			p2.next.prev = p2.next = p1;
		} else {
			p1.prev.next = p1.next.prev = p2;
			p2.prev.next = p2.next.prev = p1;
			temp = p1.next;
			p1.next = p2.next;
			p2.next = temp;
			temp = p1.prev;
			p1.prev = p2.prev;
			p2.prev = temp;
		}
	}

	public void shift(int n) {
		Node<T> p;
		if (n > 0) {
			n = n % size();
			for (int i = 0; i < n; i++) {
				p = beginMarker.next;
				beginMarker.next = p.next;
				beginMarker.next.prev = beginMarker;
				p.next = endMarker;
				p.prev = endMarker.prev;
				p.prev.next = p;
				endMarker.prev = p;
			}
		} else if (n < 0) {
			n = Math.abs(n);
			for (int i = 0; i < n; i++) {
				p = endMarker.prev;
				endMarker.prev = p.prev;
				endMarker.prev.next = endMarker;
				p.prev = beginMarker;
				p.next = beginMarker.next;
				p.next.prev = p;
				beginMarker.next = p;
			}
		} else
			return;

	}

	// This method is used to remove n number of elements elements from the list
	// starting from the index position idx
	public void erase(int idx, int n) {
		Node<T> start = getNode(idx - 1);
		Node<T> end = getNode(idx + n);
		start.next = end;
		end.prev = start;
	}

	public void insertList(MyLinkedList<T> newList, int idx) {
		Node<T> indexNode = beginMarker;
		Node<T> newStart = newList.beginMarker.next;
		Node<T> newEnd = newList.endMarker.prev;
		for (int i = 0; i < idx; i++)
			indexNode = indexNode.next;
		newEnd.next = indexNode.next;
		newEnd.next.prev = newEnd;
		indexNode.next = newStart;
		newStart.prev = indexNode;
	}

	public java.util.Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements java.util.Iterator<T> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public T next() {
			return null;
		}
	}

	private static class Node<T> {
		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", prev=" + prev.data + ", next="
					+ next.data + "]";
		}

		public T data;
		public Node<T> prev;
		public Node<T> next;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		Node<T> p = beginMarker.next;
		while (p.next != null) {
			sb.append(p.data + " ");
			p = p.next;
		}
		sb.append("]");

		return new String(sb);
	}
}
