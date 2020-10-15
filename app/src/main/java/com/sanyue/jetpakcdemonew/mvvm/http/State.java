package com.sanyue.jetpakcdemonew.mvvm.http;

/***
 * Create by Yip
 * Create Time 2020/9/29
 */
public class State {
    private StateType code;
    private String msg;

    public State(StateType code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StateType getCode() {
        return code;
    }

    public void setCode(StateType code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
