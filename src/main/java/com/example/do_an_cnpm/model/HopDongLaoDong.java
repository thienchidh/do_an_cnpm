package com.example.do_an_cnpm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Entity(name = "HopDongLaoDong")
public class HopDongLaoDong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHopDong")
    private long maHopDong;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;

    @Column(name = "LoaiHopDong")
    private String loaiHopDong;

    @Column(name = "TuNgay")
    private Date tuNgay;

    @Column(name = "DenNgay")
    private Date denNgay;
}
