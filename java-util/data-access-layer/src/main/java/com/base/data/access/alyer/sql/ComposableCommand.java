package com.base.data.access.alyer.sql;

import com.base.data.access.alyer.exception.QuitException;
import java.util.List;
import java.util.ArrayList;

/**
 * Composable组成的
 */
public abstract class ComposableCommand {
    private volatile boolean isDone = false;

    final public boolean isDone() {
        return isDone;
    }

    protected abstract boolean doInnerExecute() throws QuitException;

    private List<ComposableCommand> subCmds = new ArrayList<>();

    final public void appendCmd(ComposableCommand cmd) {

    }

    private volatile int index = 0;

    public void execute() throws QuitException {
        if(this.isDone()) {
            return;
        }

        for(; index < )
    }

    private static final ComposableCommand DONE_CMD = new ComposableCommand() {
        @Override
        protected boolean doInnerExecute() throws QuitException {
            return true;
        }
    };
}
