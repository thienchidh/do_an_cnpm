package com.example.do_an_cnpm.helper;

import com.example.do_an_cnpm.data_source.repo.UserSessionRepository;
import com.example.do_an_cnpm.model.Account;
import com.example.do_an_cnpm.model.Role;
import com.example.do_an_cnpm.model.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationHelperImpl implements AuthenticationHelper {
    private PasswordEncoder passwordEncoder;
    private UserSessionRepository sessionRepo;

    @Override
    public String createSalt() {
        Date today = Calendar.getInstance().getTime();
        return passwordEncoder.encode(today.toString());
    }

    @Override
    public String makePassword(@NonNull Account account, @NonNull String salt) {
        return makePassword(account.getPassword(), salt);
    }

    @Override
    public String makePassword(String passwordNonHash, @NonNull String salt) {
        return passwordEncoder.encode(passwordNonHash + salt);
    }

    @Override
    public String makeToken(@NonNull Account account) {
        return account.getUsername() + passwordEncoder.encode(account.getPassword());
    }

    @Override
    public boolean isLoginSuccess(@NonNull Account accountClient, @NonNull Account accountDB) {
        return passwordEncoder.matches(accountClient.getPassword() + accountDB.getSalt(), accountDB.getPassword());
    }

    @Override
    public boolean isValidToken(@NonNull String token) {
        return sessionRepo.exists(Example.of(UserSession.builder().token(token).build()));
    }

    @Override
    public boolean isAliveToken(@NonNull String token) {
        Optional<UserSession> session = sessionRepo.findOne(Example.of(UserSession.builder().token(token).build()));
        if (session.isPresent()) {
            Date today = Calendar.getInstance().getTime();
            return session.get().getDateExpired().after(today);
        }
        return false;
    }

    @Override
    public boolean isTokenAdmin(@NonNull String token) {
        if (isAliveToken(token)) {
            Optional<UserSession> session = sessionRepo.findOne(Example.of(UserSession.builder().token(token).build()));
            if (session.isPresent()) {
                return session.get().getNhanVien().getChucVu().getTenChucVu().equals(Role.IS_ADMIN);
            }
        }
        return false;
    }

    @Override
    public boolean isTokenUser(@NonNull String token) {
        return !isTokenAdmin(token);
    }
}
