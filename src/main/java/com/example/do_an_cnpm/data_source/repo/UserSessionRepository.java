package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
}
