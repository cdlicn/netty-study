package com.cdlicn.server.session;

public abstract class SessionFactory {

    private static final Session SESSION = new SessionMemoryImpl();

    public static Session getSession() {
        return SESSION;
    }
}
