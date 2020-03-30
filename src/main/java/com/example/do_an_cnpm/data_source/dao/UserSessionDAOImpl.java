package com.example.do_an_cnpm.data_source.dao;

import com.example.do_an_cnpm.data_source.repo.UserSessionRepository;
import com.example.do_an_cnpm.helper.AuthenticationHelper;
import com.example.do_an_cnpm.helper.DateHelper;
import com.example.do_an_cnpm.model.Account;
import com.example.do_an_cnpm.model.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserSessionDAOImpl implements UserSessionDAO {

    private UserSessionRepository sessionRepo;
    private AuthenticationHelper authentication;
    private DateHelper dateHelper;

    @Override
    public List<UserSession> findAll() {
        return sessionRepo.findAll();
    }

    @Override
    public <S extends UserSession> List<S> findAll(Example<S> example) {
        return sessionRepo.findAll(example);
    }

    @Override
    public Page<UserSession> findAll(Pageable pageable) {
        return sessionRepo.findAll(pageable);
    }

    @Override
    public Optional<UserSession> findById(long id) {
        return sessionRepo.findById(id);
    }

    @Override
    public void save(UserSession o) {
        sessionRepo.save(o);
    }

    @Override
    public void saveAll(Iterable<UserSession> iterable) {
        sessionRepo.saveAll(iterable);
    }

    @Override
    public void deleteById(long id) {
        sessionRepo.deleteById(id);
    }

    @Override
    public void delete(UserSession o) {
        sessionRepo.delete(o);
    }

    @Override
    public boolean existsById(long id) {
        return sessionRepo.existsById(id);
    }


    @Override
    public UserSession addAgeSession(UserSession session) {
        Date today = Calendar.getInstance().getTime();

        if (session.getDateExpired().after(today)) {
            session.setDateExpired(dateHelper.getDayAfterToday());
            return sessionRepo.save(session);
        }
        return session;
    }

    @Transactional
    @Override
    public UserSession makeUserSession(Account account) {
        Date dateExpiredToken = dateHelper.getDayAfterToday();
        return makeUserSession(account, dateExpiredToken);
    }

    @Transactional
    @Override
    public UserSession makeUserSession(Account account, Date dateExpiredToken) {
        return sessionRepo.save(UserSession.builder()
                .token(authentication.makeToken(account))
                .dateExpired(dateExpiredToken)
                .nhanVien(account.getNhanVien())
                .account(account)
                .build()
        );
    }

    @Override
    public Optional<UserSession> findUserSessionBy(String token) {
        UserSession probe = UserSession.builder().token(token).build();
        return sessionRepo.findOne(Example.of(probe));
    }
}
