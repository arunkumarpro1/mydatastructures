package com.arun;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>> {

	public BinaryTreeNode<T> root;

	public static class BinaryTreeNode<T> {
		T element;
		BinaryTreeNode<T> leftChild;
		BinaryTreeNode<T> rightChild;

		public BinaryTreeNode(T element, BinaryTreeNode<T> leftChild,
				BinaryTreeNode<T> rightChild) {
			super();
			this.element = element;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	public BinarySearchTree() {
		super();
		root = null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(T x) {
		return contains(x, root);
	}

	private boolean contains(T x, BinaryTreeNode<T> t) {
		if (t == null)
			return false;
		int comp = x.compareTo(t.element);
		if (comp < 0)
			return contains(x, t.leftChild);
		else if (comp > 0)
			return contains(x, t.rightChild);
		else
			return true;
	}

	public T findMin() {
		if (isEmpty())
			throw new EmptyStackException();
		return findMin(root).element;
	}

	/**
	 * Recursive routine to find the minimum element. Empty check is already
	 * done in the caller
	 * 
	 * @param t
	 * @return
	 */
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> t) {
		if (t.leftChild == null)
			return t;
		else
			return findMin(t.leftChild);
	}

	public T findMax() {
		if (isEmpty())
			throw new EmptyStackException();
		return findMax(root).element;
	}

	// Non Recursive routine
	// Empty check is already done in the caller
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> t) {
		while (t.rightChild != null)
			t = t.rightChild;
		return t;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	public void remove(T x) {
		root = remove(x, root);
	}

	public void printTree() {
		printTreeInOrder(root);
		System.out.println();
	}

	// Recursive routine for inserting the given element into the trees
	private BinaryTreeNode<T> insert(T x, BinaryTreeNode<T> t) {
		if (t == null)
			return new BinaryTreeNode<T>(x, null, null);

		int comp = x.compareTo(t.element);
		if (comp < 0)
			t.leftChild = insert(x, t.leftChild);
		else if (comp > 0)
			t.rightChild = insert(x, t.rightChild);
		// Else , the element is already present in the tree
		return t;
	}

	// Recursive routine for removing the given item from the tree
	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if (t == null)
			return t; // do nothing (the element is not found)

		int comp = x.compareTo(t.element);
		if (comp < 0)
			t.leftChild = remove(x, t.leftChild);
		else if (comp > 0)
			t.rightChild = remove(x, t.rightChild);
		else if (t.leftChild == null || t.rightChild == null) {
			t.element = findMin(t.rightChild).element;
			t.rightChild = remove(t.element, t.rightChild);
		} else {
			t = (t.leftChild != null) ? t.leftChild : t.rightChild;
		}
		return t;
	}

	// This routine prints the items of the tree in ascending order using
	// inorder traversal
	private void printTreeInOrder(BinaryTreeNode<T> t) {

		if (t != null) {
			printTreeInOrder(t.leftChild);
			System.out.print(t.element + " ");
			printTreeInOrder(t.rightChild);
		}
	}

	// Driver routine for nodeCount
	public int nodeCount() {
		return nodeCount(root);
	}

	// Recursive routine for finding the count of the nodes
	private int nodeCount(BinaryTreeNode<T> t) {
		if (t == null)
			return 0;
		return 1 + nodeCount(t.leftChild) + nodeCount(t.rightChild);
	}

	/** Driver routine */
	public boolean isFull() {
		return isFull(root);
	}

	/** Recursive routine to check if the tree is full */
	private boolean isFull(BinaryTreeNode<T> t) {
		if (t == null)
			return false; // To check for empty tree

		if (t.leftChild == null && t.rightChild == null)
			return true;
		else if (t.leftChild != null && t.rightChild != null) {
			return (isFull(t.leftChild) && isFull(t.rightChild));
		} else
			return false;
	}

	/** Driver routine for compareStructure */
	public boolean compareStructure(BinaryTreeNode<T> root1) {
		// returns false even if both the trees are empty because empty trees
		// dont have structure
		if (root == null || root1 == null)
			return false;
		return compareStructure(root, root1);
	}

	/**
	 * Recursive routine to check whether both the trees have the same structure
	 */
	private boolean compareStructure(BinaryTreeNode<T> t, BinaryTreeNode<T> t1) {
		if (t == null && t1 == null)
			return true;
		else if (t != null && t1 != null) {
			return (compareStructure(t.leftChild, t1.leftChild) && compareStructure(
					t.rightChild, t1.rightChild));
		} else
			return false;
	}

	/** Driver routine for equals */
	public boolean equals(BinaryTreeNode<T> secondTreeRoot) {
		return equals(root, secondTreeRoot);
	}

	/**
	 * Recursive routine to check whether both the trees are identical
	 */
	private boolean equals(BinaryTreeNode<T> t, BinaryTreeNode<T> t1) {
		if (t == null && t1 == null)
			return true;
		else if (t != null && t1 != null) {
			if (t.element.equals(t1.element))
				return (equals(t.leftChild, t1.leftChild) && equals(
						t.rightChild, t1.rightChild));
			else
				return false;
		} else
			return false;
	}

	/** Driver routine for copy method */
	public BinarySearchTree<T> copy() {
		BinarySearchTree<T> newTree = new BinarySearchTree<>();
		newTree.root = copy(root);
		return newTree;
	}

	/**
	 * Recursive routine to create a new tree that is a copy of the original
	 * tree.
	 */
	private BinaryTreeNode<T> copy(BinaryTreeNode<T> t) {
		if (t != null) {
			BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(t.element, null,
					null);
			newNode.leftChild = copy(t.leftChild);
			newNode.rightChild = copy(t.rightChild);
			return new BinaryTreeNode<T>(t.element, t.leftChild, t.rightChild);
		}
		return null;
	}

	/** Driver routine for mirror method */
	public BinarySearchTree<T> mirror() {
		BinarySearchTree<T> newTree = new BinarySearchTree<>();
		newTree.root = mirror(root);
		return newTree;
	}

	/**
	 * Recursive routine to create a new tree that is a mirror of the original
	 * tree.
	 */
	private BinaryTreeNode<T> mirror(BinaryTreeNode<T> t) {
		if (t != null) {
			BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(t.element, null,
					null);
			newNode.leftChild = mirror(t.rightChild);
			newNode.rightChild = mirror(t.leftChild);
			return newNode;
		}
		return null;
	}

	/** Driver routine for isMirror method */
	public boolean isMirror(BinarySearchTree<T> mirrorTree) {
		return isMirror(root, mirrorTree.root);
	}

	/**
	 * Recursive routine to check if the tree is a mirror of the passed tree.
	 */
	private boolean isMirror(BinaryTreeNode<T> t, BinaryTreeNode<T> tMirror) {
		if (t != null && tMirror != null) {
			if (t.element.equals(tMirror.element))
				return (isMirror(t.leftChild, tMirror.rightChild) && isMirror(
						t.rightChild, tMirror.leftChild));
			else
				return false;
		} else if (t == null && tMirror == null)
			return true;
		return false;
	}

	/** Driver routine for rotateRight method */
	public void rotateRight(T x) {
		root = traverseAndFind(x, root, true);
	}

	/**
	 * Routine to rotate the given node to its right.
	 */
	private BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> k2) {
		BinaryTreeNode<T> k1 = k2.leftChild;
		if (k1 != null) {
			k2.leftChild = k1.rightChild;
			k1.rightChild = k2;
			return k1;
		}
		return k2;
	}

	/** Driver routine for rotateLeft method */
	public void rotateLeft(T x) {
		root = traverseAndFind(x, root, false);
	}

	/**
	 * Routine to rotate the given node to its left.
	 */
	private BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> k2) {
		BinaryTreeNode<T> k1 = k2.rightChild;
		if (k1 != null) {
			k2.rightChild = k1.leftChild;
			k1.leftChild = k2;
			return k1;
		}
		return k2;
	}

	/**
	 * Recursive routine to traverse the tree, finding the given node and call
	 * either rotateRight or rotateLeft method.
	 */
	private BinaryTreeNode<T> traverseAndFind(T x, BinaryTreeNode<T> t,
			boolean isRotateRight) {
		if (t == null)
			return null;

		int comp = x.compareTo(t.element);
		if (comp < 0)
			t.leftChild = traverseAndFind(x, t.leftChild, isRotateRight);
		else if (comp > 0)
			t.rightChild = traverseAndFind(x, t.rightChild, isRotateRight);
		else {
			if (isRotateRight == true)
				return rotateRight(t);
			else
				return rotateLeft(t);
		}
		return t;
	}

	/**
	 * Driver routine for level order traversal of the tree
	 */
	public void printTreeLevelOrder() {
		printTreeLevelOrder(root);
	}

	/**
	 * This routine prints the items of the tree level by level using breadth
	 * first search.
	 * 
	 * @param root
	 */
	private void printTreeLevelOrder(BinaryTreeNode<T> root) {
		if (root == null)
			return;
		Queue<BinaryTreeNode<T>> q = new LinkedList<>();
		q.add(root);
		BinaryTreeNode<T> b;
		while (!q.isEmpty()) {
			b = q.poll();
			if (b.leftChild != null)
				q.add(b.leftChild);
			if (b.rightChild != null)
				q.add(b.rightChild);
			System.out.print(b.element + " ");
		}
	}

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.println("You want to enter the elements of the tree (Y/N)? (If you enter N, a default array of elements will be inserted into the tree");
		Scanner s = new Scanner(System.in);
		int elements[] = { 20, 30, 40, 25, 10, 15, 5 };
		if (s.next().equals("Y")) {
			System.out.println("Enter the count of elements: ");
			int N = s.nextInt();
			elements = new int[N];
			for (int i = 0; i < N; i++) {
				elements[i] = s.nextInt();
			}
		}
		BinarySearchTree<Integer> firstTree = new BinarySearchTree<>();
		for (int i = 0; i < elements.length; i++) {
			firstTree.insert(elements[i]);
		}
		System.out
				.println("The elements of the tree in ascending order (using onorder traversal) are ");
		firstTree.printTree();

		System.out.println("\nThe node count of the tree is "
				+ firstTree.nodeCount());

		System.out.println("\nIs the tree full? " + firstTree.isFull());

		System.out
				.println("\nYou want to enter the elements of the second tree (Y/N)? (If you enter N, a default array of elements will be inserted into the tree");

		// int elements2[] = { 100, 50, 40, 45, 150 };
		int elements2[] = { 30, 20, 10, 25, 40, 45, 35, 50 };
		if (s.next().equals("Y")) {
			System.out.println("Enter the count of elements: ");
			int N = s.nextInt();
			elements2 = new int[N];
			for (int i = 0; i < N; i++) {
				elements2[i] = s.nextInt();
			}
		}

		BinarySearchTree<Integer> secondTree = new BinarySearchTree<>();
		for (int i = 0; i < elements2.length; i++) {
			secondTree.insert(elements2[i]);
		}

		System.out.println("The elements of the second tree are ");
		secondTree.printTree();

		System.out.println("\nBoth the trees have the same structure? "
				+ firstTree.compareStructure(secondTree.root));

		System.out.println("\nBoth the trees are identical? "
				+ firstTree.equals(firstTree.root, secondTree.root));

		BinarySearchTree<Integer> copyTree = firstTree.copy();
		System.out.println("\nThe copy of the first tree is ");
		copyTree.printTree();

		BinarySearchTree<Integer> mirroredTree = firstTree.mirror();
		System.out.println("\nThe mirror of the first tree is ");
		mirroredTree.printTree();

		BinarySearchTree<Integer> mirroredTree2 = secondTree.mirror();
		System.out.println("\nThe mirror of the second tree is ");
		mirroredTree2.printTree();

		System.out.println("\nIs the mirrored tree the mirror of first tree? "
				+ mirroredTree.isMirror(firstTree));
		System.out.println("Is the second tree the mirror of first tree? "
				+ secondTree.isMirror(firstTree));

		System.out
				.println("\n Enter a node from first tree on which right rotation must be performed");
		firstTree.rotateRight(s.nextInt());
		System.out
				.println("The inorder traversal of the first tree after rotating it to its right on the root element 20 is ");
		firstTree.printTree();

		System.out
				.println("\n Enter a node from second tree on which left rotation must be performed");
		secondTree.rotateLeft(s.nextInt());
		System.out
				.println("The inorder traversal of the first tree after rotating it to its left on the element 40 is ");
		secondTree.printTree();

		System.out.println("\nThe level order traversal of the first tree is");
		firstTree.printTreeLevelOrder();
		System.out.println();
		System.out.println("The level order traversal of the second tree is");
		secondTree.printTreeLevelOrder();

		s.close();
	}
}
