package com.base.data.access.alyer.netty;

import com.base.data.access.alyer.exception.QuitException;

public class BatchSessionContext extends ComposableCommand {
    @Override
    protected boolean doInnerExecute() throws QuitException {
        return false;
    }
}
