package com.example.do_an_cnpm;

import com.example.do_an_cnpm.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        var account = Account.builder()
                .username("thienchidh")
                .password("123456")
                .nhanVien(
                        NhanVien.builder()
                                .hoTen("Chí")
                                .chucVu(
                                        ChucVu.builder()
                                                .tenChucVu("Chức vụ")
                                                .build()
                                )
                                .phongBan(
                                        PhongBan.builder()
                                                .tenPhongBan("Tên Phòng Ban")
                                                .build()
                                )
                                .trinhDoHocVan(
                                        TrinhDoHocVan.builder()
                                                .tenTrinhDoHocVan("Trình độ học vấn")
                                                .build()
                                )
                                .build()
                )
                .build();

        var x = new ObjectMapper().writeValueAsString(account);

        System.out.println(x);

        SpringApplication.run(Application.class, args);
    }

}
