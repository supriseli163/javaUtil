package com.base.data.access.alyer.netty.state;

import com.base.data.access.alyer.exception.QuitException;

public class QuitState implements State {
    private SqlSession

    @Override
    public boolean handle() throws QuitException {
        return false;
    }

    @Override
    public SESSION_STATUS getStatus() {
        return null;
    }
}
