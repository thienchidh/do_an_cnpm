package com.example.do_an_cnpm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Entity(name = "PhieuGiaoViec")
public class PhieuGiaoViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhieuGiaoViec")
    private long maPhieuGiaoViec;

    @OneToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaCongViec")
    private CongViec congViec;

    @Column(name = "TuNgay")
    private Date tuNgay;

    @Column(name = "DenNgay")
    private Date denNgay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaTrangThaiCongViec")
    private TrangThaiCongViec trangThaiCongViec;
}
