package com.base.java.util.concurrent.task;

public class DirectTaskExecutor implements TaskExecutor {
    @Override
    public Object execute(Task task) throws Throwable {
        try {
            return task.call();
        } catch (Throwable t0) {
            if(task.supportFallback()) {
                try {
                    return task.callFallback();
                } catch (Throwable t1) {
                    throw t1;
                }
            } else {
                throw t0;
            }
        }
    }
}
