package com.sjtu.server.vo;

public class JSONPage {
    private Long total;
    private Object data;

    public JSONPage(Long total, Object data) {
        this.total = total;
        this.data = data;
    }

}
