package com.qfedu.mtlms.vo;

/**
 * @Description 响应前端ajax请求的对象
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class ResultVO {

    private int code;
    private String msg;
    private Object data;

    public ResultVO() {
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
