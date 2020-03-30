package com.example.do_an_cnpm.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Entity(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNhanVien")
    private long maNhanVien;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "DanToc")
    private String danToc;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "QueQuan")
    private String queQuan;

    @Column(name = "NgaySinh")
    private String ngaySinh;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaChucVu")
    private ChucVu chucVu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaPhongBan")
    private PhongBan phongBan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaTrinhDoHocVan")
    private TrinhDoHocVan trinhDoHocVan;
}
