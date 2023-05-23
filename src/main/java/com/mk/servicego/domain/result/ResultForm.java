package com.mk.servicego.domain.result;

import lombok.Data;

/**
 * @author guocz
 * @date 2023/4/10 11:48
 */
@Data
public class ResultForm {

    protected boolean success;
    protected String message;

    public ResultForm(boolean success) {
        this.success = success;
        this.message = "";
    }

    public ResultForm(boolean success, String message) {
        this(success);
        this.message = message;
    }

}
