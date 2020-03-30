package com.example.do_an_cnpm.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Entity(name = "TrinhDoHocVan")
public class TrinhDoHocVan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTrinhDoHocVan")
    private long maTrinhDoHocVan;

    @Column(name = "TenTrinhDoHocVan")
    private String tenTrinhDoHocVan;

    @Column(name = "ChuyenNganh")
    private String chuyenNganh;
}
