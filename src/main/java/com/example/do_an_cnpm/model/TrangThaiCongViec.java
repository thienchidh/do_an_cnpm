package com.example.do_an_cnpm.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Entity(name = "TrangThaiCongViec")
public class TrangThaiCongViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTrangThaiCongViec")
    private long maTrangThaiCongViec;

    @Column(name = "TrangThai")
    private String trangThai;
}
