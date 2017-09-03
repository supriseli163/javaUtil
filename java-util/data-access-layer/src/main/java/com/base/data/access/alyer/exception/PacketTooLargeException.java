package com.base.data.access.alyer.exception;

public class PacketTooLargeException extends QuitException {
    private static final long servialVersionUID = -162862259312150403L;

    public PacketTooLargeException(String message) {
        super(message);
    }

    public PacketTooLargeException(String message, Exception cause) {
        super(message, cause);
    }
}
