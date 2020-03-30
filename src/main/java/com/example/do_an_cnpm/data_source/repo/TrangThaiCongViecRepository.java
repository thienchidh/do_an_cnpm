package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.TrangThaiCongViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiCongViecRepository extends JpaRepository<TrangThaiCongViec, Long> {

}
