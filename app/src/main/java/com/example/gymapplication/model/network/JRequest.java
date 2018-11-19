package com.example.gymapplication.model.network;

import com.alibaba.fastjson.annotation.JSONField;

public class JRequest {
    public JRequest() {
        _jRequestRealBodyStr = "";
    }

    @JSONField(serialize=false)
    private String _jRequestRealBodyStr;

    public String findBody() {
        return _jRequestRealBodyStr;
    }

    public void saveBody(String body) {
        this._jRequestRealBodyStr = body;
    }
}
