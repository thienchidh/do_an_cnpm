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
    @Column(name = "id")
    private long id;

    @Column(name = "trangThai", nullable = false)
    private String trangThai;
}
