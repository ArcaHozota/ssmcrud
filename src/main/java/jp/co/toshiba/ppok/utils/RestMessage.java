package jp.co.toshiba.ppok.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * The common class of JSON-data response.
 *
 * @author Administrator
 * @since 8.76
 */
public class RestMessage {

	/**
	 * status code
	 */
	private Integer code;

	/**
	 * the message of status
	 */
	private String message;

	/**
	 * data returned to browsers
	 */
	private final Map<String, Object> extend = new HashMap<>();

	/**
	 * retrieve successfully
	 *
	 * @return result including data
	 */
	public static RestMessage success() {
		final RestMessage result = new RestMessage();
		result.setCode(200);
		result.setMessage("Retrieve success.");
		return result;
	}

	/**
	 * retrieve failed
	 *
	 * @return result including error message
	 */
	public static RestMessage failure() {
		final RestMessage result = new RestMessage();
		result.setCode(400);
		result.setMessage("Retrieve failed.");
		return result;
	}

	/**
	 * add values with messages
	 *
	 * @param key   the name pattern of value
	 * @param value value
	 * @return RestMsg
	 */
	public RestMessage add(final String key, final Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	/**
	 * getter for code
	 *
	 * @return code
	 */
	public Integer getCode() {
		return this.code;
	}

	/**
	 * setter of code
	 *
	 * @param code セットする code
	 */
	public void setCode(final Integer code) {
		this.code = code;
	}

	/**
	 * getter for message
	 *
	 * @return message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * setter of message
	 *
	 * @param message セットする message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * getter for extend
	 *
	 * @return extend
	 */
	public Map<String, Object> getExtend() {
		return this.extend;
	}
}