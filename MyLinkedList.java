package demo;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

	public class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			this.data = d;
			this.prev = p;
			this.next = n;
		}

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}

	private int theSize;
	private int modCount; // 对链表的修改次数
	public Node<AnyType> beginMarker;
	public Node<AnyType> endMarker;

	public MyLinkedList() {
		doClear();
	}

	public void clear() {
		doClear();
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}

	public void doClear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}

	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		p.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	private AnyType remove(Node<AnyType> p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		theSize--;
		modCount++;
		return p.data;
	}

	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size());
	}

	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;
		if (idx < lower || idx > upper) {
			throw new IndexOutOfBoundsException();
		}

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++) {
				p = p.next;
			}
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--) {
				p = p.prev;
			}
		}
		return p;
	}

	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}

	public class LinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;
		private int exceptedModCount = modCount;
		private boolean okToRemove = false;
		private int i=0;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (modCount != exceptedModCount)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != exceptedModCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			exceptedModCount++;
			okToRemove = false;
		}
	}
}
