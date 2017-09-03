package com.base.data.access.alyer.util.common;

import java.io.IOException;

public class EmptyAppender implements Appendable {
    private static final EmptyAppender EMPTY_APPENDER = new EmptyAppender();

    @Override
    public Appendable append(CharSequence csq) throws IOException {
        return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return this;
    }

    @Override
    public Appendable append(char c) throws IOException {
        return this;
    }
}
