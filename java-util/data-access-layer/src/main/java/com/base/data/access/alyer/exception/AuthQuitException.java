package com.base.data.access.alyer.exception;

import io.netty.buffer.ByteBuf;

public class AuthQuitException extends  QuitException {
    private static final long servialVersionUID = -5832264467442371374L;

    public ByteBuf authBUf = null;

    public AuthQuitException(String message) {
        super(message);
    }

    public ByteBuf getAuthBUf() {
        return authBUf;
    }
}
