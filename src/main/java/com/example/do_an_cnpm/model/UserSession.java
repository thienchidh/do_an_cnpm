package com.example.do_an_cnpm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_SESSIONS")
public class UserSession {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String token;

    @Column(name = "dateExpired", nullable = false)
    private Date dateExpired;

    /*@OneToOne
    @JoinColumn(name = "nhanVienId")
    private NhanVien nhanVien;*/

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
}

