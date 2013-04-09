package term2.stack;

public class Stack {
	private int[] elements;
	private int size;

	public Stack(int capacity) {
		elements = new int[capacity];
	}

	public void push(int j) {
		elements[size++] = j;
	}

	public int pop() {
		return elements[--size];
	}

	public int peek() {
		return elements[size - 1];
	}

	public boolean isEmpty() {
		return size == 0;
	}
}