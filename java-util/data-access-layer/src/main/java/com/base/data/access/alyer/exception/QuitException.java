package com.base.data.access.alyer.exception;

public class QuitException extends Exception {
    private static final long serivalVersionUID = 1678109213830136697L;

    public QuitException(String message) {
        super(message);
    }

    public QuitException(String message, Exception cause) {
        super(message, cause);
    }
}

