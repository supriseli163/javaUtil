package com.base.java.util.mysql;

public interface Model {
    /**
     * Check if this modle could be permanently deleted.
     *
     * @return
     */
     boolean isDeletable();
}
