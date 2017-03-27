package crazycalculator.datastructure;

public class Queue<T> {
	private int size;
	
	private PseudoArray<T> pseudoArray;
	
	public Queue(int maxSize) {
		size = 0;
		
		pseudoArray = new PseudoArray<T>(maxSize);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == pseudoArray.length();
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(T data) {
		try {
			if(isFull()) {
				throw new IndexOutOfBoundsException();
			}
			else {
				pseudoArray.set(size++, data);
			}
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Queue is full!");
		}
	}
	
	public T dequeue() {
		T data = null;
		
		try {
			if(isEmpty()) {
				throw new EmptyListException();
			}
			else {
				data = pseudoArray.get(0);
				
				for(int a = 0; a < size(); a++) {
					pseudoArray.set(a, pseudoArray.get(a + 1));
				}
				
				pseudoArray.set(size--, null);
			}
		}
		catch(EmptyListException e) {
			System.out.println("Queue is empty!");
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
				data = pseudoArray.get(0);
			}
		}
		catch(EmptyListException e) {
			System.out.println("Queue is empty!");
		}
		
		return data;
	}
	
	public void clear() {
		try {
			if(isEmpty()) {
				throw new EmptyListException();
			}
			else {
				pseudoArray.clear();
			}
		}
		catch(EmptyListException e) {
			System.out.println("Queue is empty!");
		}
	}
	
	public T displayItemAt(int index) {
		return pseudoArray.get(index);
	}
	
	public String displayContents() {
		String contents = " ";
		
		for(int a = 0; a < size; a++) {
			contents = contents + pseudoArray.get(a) + " ";
		}
		
		return contents;
	}
	
	public String displayPseudoArray() {
		return pseudoArray.displayContents();
	}
	
	public String displayLinkedList() {
		return pseudoArray.displayLinkedList();
	}
}
