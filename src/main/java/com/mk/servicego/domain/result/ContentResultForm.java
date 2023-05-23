package com.mk.servicego.domain.result;

import lombok.Data;

/**
 * @author guocz
 * @date 2023/4/10 11:49
 */
public class ContentResultForm<T> extends ResultForm {

    private T content;

    public ContentResultForm(boolean success) {
        super(success);
    }

    public ContentResultForm(boolean success, T content) {
        super(success);
        this.content = content;
    }

    public ContentResultForm(boolean success, T content, String message) {
        super(success, message);
        this.content = content;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
