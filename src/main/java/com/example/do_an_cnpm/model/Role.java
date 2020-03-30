package com.example.do_an_cnpm.model;

public final class Role {
    public static final String IS_ADMIN = _Role.IS_ADMIN.toString();
    public static final String IS_USER = _Role.IS_USER.toString();

    private Role() {
    }

    enum _Role {
        IS_ADMIN,
        IS_USER
    }
}
