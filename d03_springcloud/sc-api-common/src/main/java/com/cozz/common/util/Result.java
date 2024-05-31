package com.cozz.common.util;


import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zt
 * @date 2024/2/22
 **/
public class Result extends HashMap<String, Object> {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String TIMESTAMP = "timestamp";

    private static final String STATUS = "status";

    private static final String MESSAGE = "msg";

    private static final String DATA = "data";

    private Result() {
    }

    private Result(boolean status, String msg) {
        this.put(Result.TIMESTAMP, System.currentTimeMillis());
        this.put(Result.STATUS, status);
        this.put(Result.MESSAGE, msg);
    }

    private Result(boolean status, String msg, Object data) {
        this.put(Result.TIMESTAMP, System.currentTimeMillis());
        this.put(Result.STATUS, status);
        this.put(Result.MESSAGE, msg);
        this.put(Result.DATA, data);
    }

    public static Result ok() {
        return new Result(true, "操作成功");
    }

    public static Result ok(String msg) {
        return new Result(true, msg);
    }

    public static Result ok(String msg, Object data) {
        return new Result(true, msg, data);
    }

    public static Result err() {
        return new Result(false, "操作失败");
    }

    public static Result err(String msg) {
        return new Result(false, msg);
    }

    public static Result err(String msg, Object data) {
        return new Result(false, msg, data);
    }

    public Result data(Object data) {
        super.put(Result.DATA, data);
        return this;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public boolean isSuccess() {
        return (boolean) this.get(Result.STATUS);
    }

    public String getMessage() {
        return (String) this.get(Result.MESSAGE);
    }

    public Object getData() {
        return this.get(Result.DATA);
    }

    public Map<String, Object> dataToMap() {
        try {
            return (Map<String, Object>) this.getData();
        } catch (ClassCastException exception) {
            return new HashMap<>();
        }
    }

}
