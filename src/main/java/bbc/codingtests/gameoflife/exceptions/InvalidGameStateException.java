package bbc.codingtests.gameoflife.exceptions;

public class InvalidGameStateException extends Exception {
	/**
	 * Exception class thrown if the input string
	 * is in the wrong format
	 */
	private static final long serialVersionUID = 1L;

	public InvalidGameStateException(String message) {
		super(message);
	}
}
