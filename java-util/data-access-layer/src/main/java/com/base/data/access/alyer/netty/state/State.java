package com.base.data.access.alyer.netty.state;

import com.base.data.access.alyer.exception.QuitException;

public interface State {
    boolean handle() throws QuitException;

    SESSION_STATUS getStatus();
}
