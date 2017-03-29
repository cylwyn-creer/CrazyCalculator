package crazycalculator.datastructure;

public class Stack<T> {
	private Queue<T> queue;
	
	public Stack(int maxSize) {
		queue = new Queue<T>(maxSize);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public boolean isFull() {
		return queue.isFull();
	}
	
	public int size() {
		return queue.size();
	}
	
	public void push(T data) {
		try {
			if(isFull()) {
				throw new IndexOutOfBoundsException();
			}
			else {
				queue.enqueue(data);
			}
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Stack is full!");
		}
	}
	
	public T pop() {
		T data = null;
		
		try {
			if(isEmpty()) {
				throw new EmptyListException();
			}
			else {
				for(int a = 1; a < size() ; a++) {
					queue.enqueue(queue.dequeue());
				}
				
				data = queue.dequeue();
			}
		}
		catch(EmptyListException e) {
			System.out.println("Stack is empty!");
		}
		
		return data;
	}
	
	public T peek() {
		T data = null;
		
		try {
			if(isEmpty()) {
				throw new EmptyListException();
			}
			else {
				for(int a = 1; a < size() ; a++) {
					queue.enqueue(queue.dequeue());
				}
				
				data = queue.peek();
			}
		}
		catch(EmptyListException e) {
			System.out.println("Stack is empty!");
		}
		
		return data;
	}
	
	public String displayContents() {
		String contents = "";
		
		for(int a = size() - 1; a >= 0; a--) {
			contents = contents + " " + queue.displayItemAt(a) + "\n";
		}
		
		return contents;
	}
	
	public String getStackString() {
		String stackString = "";
		
		for(int a = 0; a < size(); a++) {
			stackString = stackString + "[" + queue.displayItemAt(a) + "] ";
		}
		
		return stackString;
	}
	
	public String displayQueue() {
		return queue.displayContents();
	}
	
	public String displayPseudoArray() {
		return queue.displayPseudoArray();
	}
	
	public String displayLinkedList() {
		return queue.displayLinkedList();
	}
}
