package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.CongViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongViecRepository extends JpaRepository<CongViec, Long> {

}
