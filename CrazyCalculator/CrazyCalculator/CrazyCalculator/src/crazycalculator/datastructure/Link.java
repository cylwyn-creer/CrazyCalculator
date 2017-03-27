package crazycalculator.datastructure;

public class Link<T> {
	private T data;
	
	private Link<T> nextLink;
	private Link<T> previousLink;
	
	public Link(T data) {
		this(data, null, null);
	}
	
	public Link(T data, Link<T> nextLink, Link<T> previousLink) {
		this.data = data;
		
		this.nextLink = nextLink;
		this.previousLink = previousLink;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setNextLink(Link<T> nextLink) {
		this.nextLink = nextLink;
	}
	
	public Link<T> getNextLink() {
		return nextLink;
	}
	
	public void setPreviousLink(Link<T> previousLink) {
		this.previousLink = previousLink;
	}
	
	public Link<T> getPreviousLink() {
		return previousLink;
	}
}
