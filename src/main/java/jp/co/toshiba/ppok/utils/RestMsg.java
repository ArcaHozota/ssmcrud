package jp.co.toshiba.ppok.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * The common class of JSON-data response.
 *
 * @author Administrator
 */
@Data
public class RestMsg {

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
    public static RestMsg success() {
        final RestMsg result = new RestMsg();
        result.setCode(200);
        result.setMessage("Retrieve success.");
        return result;
    }

    /**
     * retrieve failed
     *
     * @return result including error message
     */
    public static RestMsg failure() {
        final RestMsg result = new RestMsg();
        result.setCode(400);
        result.setMessage("Retrieve failed.");
        return result;
    }

    /**
     * no args constructor
     */
    public RestMsg() {
    }

    /**
     * add values with messages
     *
     * @param key   the name pattern of value
     * @param value value
     * @return RestMsg
     */
    public RestMsg add(final String key, final Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
