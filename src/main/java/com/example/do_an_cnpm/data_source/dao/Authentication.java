package com.example.do_an_cnpm.data_source.dao;

import com.example.do_an_cnpm.model.Account;
import com.example.do_an_cnpm.model.UserSession;
import org.springframework.lang.NonNull;

public interface Authentication {
    UserSession register(@NonNull Account account);

    UserSession login(@NonNull Account account);

    UserSession login(@NonNull String token);

    boolean logout(@NonNull String token);

    boolean changePassword(@NonNull Account account);

    void logoutAllSessionOfUser(Long userId);

//    boolean changePassword(UserSession session, Account account);
}
