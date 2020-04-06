package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {

    List<PhongBan> findAllByTenPhongBan(String tenPhongBan);

    Optional<PhongBan> findByTenPhongBan(String tenPhongBan);

    boolean existsByTenPhongBan(String tenPhongBan);

}
