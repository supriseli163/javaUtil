package com.base.java.util.mysql;

import com.google.common.base.Preconditions;
import org.hibernate.Session;

public class SessionHolder {
    private Session session;

    public void holdSession(Session session) {
        Preconditions.checkState(this.session == null, "SessionHolder has been hold");
        this.session = session;
    }

    public Session getSession() {
        Preconditions.checkState(session != null, "SessionHolder has been released");
        return session;
    }

    public void releaseSession() {
        Preconditions.checkState(session != null, "SessionHolder has been released");
        session = null;
    }
}
