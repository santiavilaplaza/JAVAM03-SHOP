package exception;


public class LimitLoginException extends Exception{
	private int counter;
	
	public LimitLoginException(int counter) {
		this.counter = counter;
	}
	
	public String toString() {
		return "Ha superado el limite de " + this.counter + " permitidos";
	}
}