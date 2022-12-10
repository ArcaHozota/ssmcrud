package jp.co.toshiba.ppok.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * the common return class of JSON data
 *
 * @author Administrator
 */
public class Message {

    /**
     * status code
     */
    private int code;

    /**
     * the message of status
     */
    private String returnMsg;

    /**
     * data returned to browsers
     */
    private Map<String, Object> extend = new HashMap<>();

    public static Message success() {
        Message result = new Message();
        result.setCode(200);
        result.setReturnMsg("Retrieve success.");
        return result;
    }

    public static Message failure() {
        Message result = new Message();
        result.setCode(400);
        result.setReturnMsg("Retrieve failed.");
        return result;
    }

    /**
     * add values with messages.
     * @param key
     * @param value
     * @return
     */
    public Message add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String message) {
        this.returnMsg = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
