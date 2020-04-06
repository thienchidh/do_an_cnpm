package com.example.do_an_cnpm.data_source.repo;

import com.example.do_an_cnpm.model.TrinhDoHocVan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrinhDoHocVanRepository extends JpaRepository<TrinhDoHocVan, Long> {

    boolean existsByTenTrinhDoHocVan(String tenTrinhDoHocVan);

    List<TrinhDoHocVan> findAllByTenTrinhDoHocVan(String tenTrinhDoHocVan);

    Optional<TrinhDoHocVan> findByTenTrinhDoHocVan(String tenTrinhDoHocVan);

}
