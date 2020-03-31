package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.NhanVien;
import com.example.do_an_cnpm.model.PhieuGiaoViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuGiaoViecRepository extends JpaRepository<PhieuGiaoViec, Long> {

    List<PhieuGiaoViec> findPhieuGiaoViecsByNhanVien(NhanVien build);

}
