package crazycalculator.datastructure;

@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {
	public EmptyListException() {
		this("Current list");
	}
	
	public EmptyListException(String name) {
		super(name + " is empty!");
	}
}
