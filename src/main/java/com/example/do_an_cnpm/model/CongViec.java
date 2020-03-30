package com.example.do_an_cnpm.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Entity(name = "CongViec")
public class CongViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "TenCongViec")
    private String tenCongViec;

    @Column(name = "chiTiet")
    private String chiTiet;

    @Column(name = "Khac")
    private String khac;
}
