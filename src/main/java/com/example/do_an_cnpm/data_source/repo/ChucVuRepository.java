package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {

}
