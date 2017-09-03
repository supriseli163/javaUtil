package com.base.data.access.alyer;

import java.util.logging.Logger;

/**
 * {@code NoThrow} calls a function and catch any exception.
 * This class is intended to used when destory and clear resources, at the occasion nothing helps
 * except write to log.
 * Think twice before use
 */
public class NoThrow {
    private static final Logger logger = Logger.getLogger(NoThrow.class.getName());

    @FunctionalInterface
    public interface NornalFunc {
        void invoke() throws Throwable;
    }

    public static void call(NornalFunc nornalFunc) {
        try {
            nornalFunc.invoke();
        } catch (Throwable t) {
            logger.info(t.getMessage() + t);
        }
    }

    @FunctionalInterface
    public interface ExceptionFunc {
        void invoke(Throwable ex);
    }

    public static void execute(NornalFunc nornalFunc, ExceptionFunc exceptionFunc) {
        try {
            nornalFunc.invoke();
        } catch (Throwable ex) {
            exceptionFunc.invoke(ex);
        }
    }
}
