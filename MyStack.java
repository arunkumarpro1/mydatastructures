package com.arun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MyStack<T> implements Iterable<T> {

	private ArrayList<T> list;

	public MyStack() {
		list = new ArrayList<>();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	private void push(T t) {
		list.add(t); // adds the element to the end of the list
	}

	private T pop() {
		return list.remove(list.size() - 1); // removes the last element and
												// returns it
	}

	private boolean isEmpty() {
		return list.isEmpty(); // checks whether the list is empty
	}

	private static void checkForBalancedSymbols(String expression) {
		MyStack<Character> stack = new MyStack<>();
		HashMap<Character, Character> symbols = new HashMap<>();
		symbols.put(')', '(');
		symbols.put('}', '{');
		symbols.put(']', '[');
		int N = expression.length();
		char ch;
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			ch = expression.charAt(i);
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					flag = true;
					break;
				}
				// to check for matching closing symbol
				if (!(stack.pop().equals(symbols.get(ch)))) {
					System.out.println("Symbols are not balanced");
					return;
				}
			}
		}
		if (!(stack.isEmpty()) || flag == true) {
			System.out.println("Symbols are not balanced");
		} else {
			System.out.println("Symbols are balanced");
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please Enter the expression of bracket symbols: ");
		String expression = s.next();
		s.close();
		checkForBalancedSymbols(expression);
	}

}
