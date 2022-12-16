package jp.co.toshiba.ppok.utils;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 3528226003356812841L;

	public CustomException(final String message) {
		super(message);
	}
}
