package com.base.data.access.alyer.netty;

import java.util.logging.Logger;

public class SessionQuitTracer {
    private static final Logger logger = Logger.getLogger(SessionQuitTracer.class.getName());

    public enum QuitTrace {
        ClientNormalQuit, ClientBroken, ServerBroken, Send2ClientBroken, Send2ServerBroken,
        DasMaxResultBufBroken, LoginFailure, NotQuit, ClientUseUnSync, ServerUnsync, AutoKillSlosTrans,
        AutoKillSlowSql, ClientAuthenticationFailed, DALPort, ManualKill, GlobalIdError, DBFuse, ReadOnlyError;
    }


}
