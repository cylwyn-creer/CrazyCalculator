package crazycalculator.datastructure;

public class LinkedList<T> {
	private int size = 0;
	
	private Link<T> firstLink;
	private Link<T> lastLink;
	
	public LinkedList() {
		firstLink = lastLink = null;
	}
	
	public boolean isEmpty() {
		return firstLink == null;
	}
	
	public int size() {
		return size;
	}
	
	public void clear() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException();
		}
		else {
			firstLink = lastLink = null;
		}
	}
	
	public void insertAtFront(T data) {
		if(isEmpty()) {
			firstLink = new Link<T>(data);
			lastLink = firstLink;
		}
		else {
			Link<T> temporaryLink = firstLink;
			firstLink = new Link<T>(data);
			firstLink.setNextLink(temporaryLink);
			temporaryLink.setPreviousLink(firstLink);
		}
		size++;
	}
	
	public void insertAtBack(T data) {
		Link<T> temporaryLink = lastLink;
		lastLink = new Link<T>(data);
		temporaryLink.setNextLink(lastLink);
		lastLink.setPreviousLink(temporaryLink);
		size++;
	}
	
	public void add(T data) {
		if(isEmpty()) {
			insertAtFront(data);
		}
		else {
			insertAtBack(data);
		}
	}
	
	public void add(int index, T data) throws EmptyListException, IndexOutOfBoundsException {
		if(isEmpty()) {
			throw new EmptyListException();
		}
		
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Link<T> currentLink = firstLink;
		int currentIndex = 0;
		while(currentLink != null) {
			if(currentIndex == index) {
				if(index == 0) {
					insertAtFront(data);
					break;
				}
				
				Link<T> newLink = new Link<T>(data);
				currentLink.getPreviousLink().setNextLink(newLink);
				newLink.setPreviousLink(currentLink.getPreviousLink());
				newLink.setNextLink(currentLink);
				currentLink.setPreviousLink(newLink);
				break;
			}
			else {
				if(index == size()) {
					insertAtBack(data);
					break;
				}
				
				currentIndex++;
				currentLink = currentLink.getNextLink();
			}
		}
		
		size++;
	}
	
	public void set(int index, T data) throws EmptyListException, IndexOutOfBoundsException {
		if(isEmpty()) {
			throw new EmptyListException();
		}
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Link<T> currentLink = firstLink;
		int currentIndex = 0;
		while(currentLink != null) {
			if(currentIndex == index) {
				currentLink.setData(data);
				return;
			}
			else {
				currentIndex++;
				currentLink = currentLink.getNextLink();
			}
		}
	}
	
	public T get(int index) throws EmptyListException, IndexOutOfBoundsException {
		T data = null;
		
		if(isEmpty()) {
			throw new EmptyListException();
		}
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Link<T> currentLink = firstLink;
		int currentIndex = 0;
		while(currentLink != null) {
			if(currentIndex == index) {
				data = currentLink.getData();
				break;
			}
			else {
				currentIndex++;
				currentLink = currentLink.getNextLink();
			}
		}
		
		return data;
	}
	
	public void remove(int index) throws EmptyListException, IndexOutOfBoundsException {
		if(isEmpty()) {
			throw new EmptyListException();
		}
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Link<T> currentLink = firstLink;
		int currentIndex = 0;
		while(currentLink != null) {
			if(currentIndex == index) {
				if(currentLink == firstLink) {
					if(firstLink == lastLink) {
						firstLink = lastLink = null;
					}
					else {
						firstLink = firstLink.getNextLink();
						firstLink.setPreviousLink(null);
					}
					break;
				}
				else {
					currentLink.getPreviousLink().setNextLink(currentLink.getNextLink());
					currentLink.getNextLink().setPreviousLink(currentLink.getPreviousLink());
					break;
				}
			}
			
			currentIndex++;
			currentLink = currentLink.getNextLink();
		}
		
		size--;
	}
	
	public String displayContents() {
		String contents = "";
		
		Link<T> currentLink = firstLink;
		int index = 0;
		while(currentLink.getData() != null) {
			contents = contents + "[ " + index + " ]\t= " + currentLink.getData() + "\n";
			index++;
			
			currentLink = currentLink.getNextLink();
		}
		
		return contents;
	}
}
