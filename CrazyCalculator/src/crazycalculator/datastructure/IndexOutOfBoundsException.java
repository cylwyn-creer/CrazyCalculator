package crazycalculator.datastructure;

@SuppressWarnings("serial")
public class IndexOutOfBoundsException extends RuntimeException {
	public IndexOutOfBoundsException() {
		super("The specified index is out of range!");
	}
}
