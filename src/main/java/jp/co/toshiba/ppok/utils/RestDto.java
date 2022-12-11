package jp.co.toshiba.ppok.utils;

import lombok.Data;

/**
 * DTO for AJAX response
 *
 * @param <T> all type supported by java ver1.8
 * @author Administrator
 */
@Data
public class RestDto<T> {

	/**
	 * 請求成功的信息；
	 */
	private static final String SUCCESS = "SUCCESS";

	/**
	 * 請求失敗的信息；
	 */
	private static final String FAILED = "FAILED";

	/**
	 * 封裝當前請求的處理結果；
	 */
	private String result;

	/**
	 * 請求成功與否的信息；
	 */
	private String msg;

	/**
	 * 返回的數據；
	 */
	private T data;

	/**
	 * 請求成功且不需要返回數據時使用的工具方法；
	 *
	 * @param <Type> all type supported by java ver1.8
	 * @return ResultEntity
	 */
	public static <Type> RestDto<Type> success() {
		return new RestDto<>(SUCCESS, null, null);
	}

	/**
	 * 請求成功時使用的工具方法；
	 *
	 * @param <Type> all type supported by java ver1.8
	 * @param data   返回的數據；
	 * @return ResultEntity
	 */
	public static <Type> RestDto<Type> succeeded(final Type data) {
		return new RestDto<>(SUCCESS, null, data);
	}

	/**
	 * 請求失敗時使用的工具方法；
	 *
	 * @param <Type>  all type supported by java ver1.8
	 * @param message 失敗的處理信息；
	 * @return ResultEntity
	 */
	public static <Type> RestDto<Type> failed(final String message) {
		return new RestDto<Type>(FAILED, message, null);
	}

	/**
	 * 無參構造器；
	 */
	public RestDto() {
		super();
	}

	/**
	 * 全參構造器
	 *
	 * @param result 當前請求的處理結果
	 * @param msg    信息
	 * @param data   數據
	 */
	public RestDto(final String result, final String msg, final T data) {
		super();
		this.result = result;
		this.msg = msg;
		this.data = data;
	}
}
