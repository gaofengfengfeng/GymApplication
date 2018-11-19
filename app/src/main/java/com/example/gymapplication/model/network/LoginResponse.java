package com.example.gymapplication.model.network;

public class LoginResponse {
    private LoginRet data;
    private String errMsg;
    private Integer errNo;
    private Integer serverTime;

    public LoginRet getData() {
        return data;
    }

    public void setData(LoginRet data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrNo() {
        return errNo;
    }

    public void setErrNo(Integer errNo) {
        this.errNo = errNo;
    }

    public Integer getServerTime() {
        return serverTime;
    }

    public void setServerTime(Integer serverTime) {
        this.serverTime = serverTime;
    }
}
