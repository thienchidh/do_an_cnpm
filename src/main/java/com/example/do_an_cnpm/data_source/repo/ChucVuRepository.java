package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {
    boolean existsByTenChucVu(String tenChucVu);

    List<ChucVu> findAllByTenChucVu(String tenChucVu);

    Optional<ChucVu> findByTenChucVu(String tenChucVu);

}
