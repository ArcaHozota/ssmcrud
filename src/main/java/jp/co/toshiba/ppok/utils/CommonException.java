package jp.co.toshiba.ppok.utils;

/**
 * 共通業務エラー
 *
 * @author ArkamaHozota
 * @since 11.65
 */
public final class CommonException extends RuntimeException {

	private static final long serialVersionUID = -7417217150801804715L;

	public CommonException() {
		super();
	}

	public CommonException(final String message) {
		super(message);
	}
}
