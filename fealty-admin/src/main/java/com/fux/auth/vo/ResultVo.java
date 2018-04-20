package com.fux.auth.vo;

import java.util.HashMap;

/**
 * Created by fuxiaoj on 2018/04/02 13:37
 */
public final class ResultVo {

    private boolean success;
    private String code;
    private String descr;
    protected HashMap<String, Object> data;

    public ResultVo() {
        this.data = new HashMap<>();
    }

    public ResultVo(boolean success, String code, String message) {
        this();
        this.success = success;
        this.code = code;
        this.descr = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public ResultVo putData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public static ResultVo successInfo() {
        return successInfo(null, "操作成功");
    }

    public static ResultVo successInfo(String code, String message) {
        return new ResultVo(true, code, message);
    }

    public static ResultVo successInfo(String message) {
        return new ResultVo(true, "success", message);
    }

    public static ResultVo failedInfo() {
        return failedInfo(null, "操作失败");
    }

    public static ResultVo failedInfo(String message) {
        return new ResultVo(false, "error", message);
    }
    public static ResultVo failedInfo(String code, String message) {
        return new ResultVo(false, code, message);
    }
}
