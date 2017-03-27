package crazycalculator.datastructure;

public class PseudoArray<T> {
	private int size;
	
	private LinkedList<T> linkedList;
	
	public PseudoArray(int size) {
		this.size = size;
		
		linkedList = new LinkedList<T>();
		
		T data = null;
		for(int a = 0; a < size; a++) {
			linkedList.add(data);
		}
	}
	
	public void set(int index, T data) {
		linkedList.set(index, data);
	}
	
	public T get(int index) {
		return linkedList.get(index);
	}
	
	public int length() {
		return size;
	}
	
	public void clear() {
		T data = null;
		for(int a = 0; a < size; a++) {
			linkedList.add(data);
		}
	}
	
	public String displayContents() {
		String contents = "";
		
		for(int a = 0; a < size; a++) {
			contents = contents + "[ " + a + " ]\t= " + linkedList.get(a) + "\n";
		}
		
		return contents;
	}
	
	public String displayLinkedList() {
		return linkedList.displayContents();
	}
}
