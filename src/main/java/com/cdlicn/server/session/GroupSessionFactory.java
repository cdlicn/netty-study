package com.cdlicn.server.session;

public abstract class GroupSessionFactory {

    private static final GroupSession SESSION = new GroupSessionMemoryImpl();

    public static GroupSession getGroupSession() {
        return SESSION;
    }
}
