package com.example.do_an_cnpm.controller.rest;

import com.example.do_an_cnpm.data_source.repo.*;
import com.example.do_an_cnpm.helper.AuthenticationHelper;
import com.example.do_an_cnpm.model.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class MyController {
    private static final Logger log = LoggerFactory.getLogger(MyController.class);
    private final AuthenticationHelper authenticationHelper;
    private final HopDongLaoDongRepository hopDongLaoDongRepository;
    private final ChucVuRepository chucVuRepository;
    private final CongViecRepository congViecRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PhieuGiaoViecRepository phieuGiaoViecRepository;
    private final PhongBanRepository phongBanRepository;
    private final TrangThaiCongViecRepository trangThaiCongViecRepository;
    private final TrinhDoHocVanRepository trinhDoHocVanRepository;

    @RequestMapping(
            value = "/lapHopDongLaoDong",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> lapHopDongLapDong(@RequestBody CustomRequest<HopDongLaoDong> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            @NonNull var data = body.getData();

            if (data.getNhanVien() != null) {
                var one = nhanVienRepository.findOne(Example.of(NhanVien.builder()
                        .maNhanVien(data.getNhanVien().getMaNhanVien())
                        .build()));

                if (one.isPresent()) {
                    @NonNull var nhanVienServer = one.get();
                    data.setNhanVien(nhanVienServer);

                    return new ResponseEntity<>(ResponseEntity.ok(hopDongLaoDongRepository.save(data)), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/lapPhieuGiaoViec",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> lapPhieuGiaoViec(@RequestBody CustomRequest<PhieuGiaoViec> body) {

        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            @NonNull var data = body.getData();

            Optional<NhanVien> nhanVienServer = nhanVienRepository.findOne(Example.of(NhanVien.builder().maNhanVien(data.getNhanVien().getMaNhanVien()).build()));

            if (nhanVienServer.isPresent()) {
                Optional<TrangThaiCongViec> one = trangThaiCongViecRepository.findOne(Example.of(TrangThaiCongViec.builder()
                        .maTrangThaiCongViec(data.getTrangThaiCongViec().getMaTrangThaiCongViec())
                        .build()));

                var trangThaiCongViec = one.orElseGet(data::getTrangThaiCongViec);// create if not exist !!!

                var save = phieuGiaoViecRepository.save(PhieuGiaoViec.builder()
                        .nhanVien(nhanVienServer.get())
                        .trangThaiCongViec(trangThaiCongViec)
                        .congViec(data.getCongViec())
                        .build());
                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/kiemTraHienTrangLaoDong",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> kiemTraHienTrangLaoDong(@RequestBody CustomRequest<NhanVien> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            @NonNull var data = body.getData();

            var one = nhanVienRepository.findOne(Example.of(NhanVien.builder().maNhanVien(data.getMaNhanVien()).build()));
            if (one.isPresent()) {
                @NonNull var nhanVienServer = one.get();
                return new ResponseEntity<>(ResponseEntity.ok(phieuGiaoViecRepository.findPhieuGiaoViecsByNhanVien(NhanVien.builder().maNhanVien(nhanVienServer.getMaNhanVien()).build())), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/danhSachChucVu",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> danhSachChucVu(@RequestBody CustomRequest<Void> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            return new ResponseEntity<>(ResponseEntity.ok(chucVuRepository.findAll()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/danhSachCongViec",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> danhSachCongViec(@RequestBody CustomRequest<Void> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            return new ResponseEntity<>(ResponseEntity.ok(congViecRepository.findAll()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/themCongViec",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> themCongViec(@RequestBody CustomRequest<CongViec> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            @NonNull var save = congViecRepository.save(data);
            return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/capNhatCongViec",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> capNhatCongViec(@RequestBody CustomRequest<CongViec> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            var byId = congViecRepository.findById(data.getMaCongViec());
            if (byId.isPresent()) {
                var one = byId.get();
                var save = congViecRepository.save(one
                        .withTenCongViec(data.getTenCongViec())
                        .withKhac(data.getKhac())
                        .withChiTiet(data.getChiTiet())
                );
                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            } else {
                @NonNull var save = congViecRepository.save(data);
                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/xoaCongViec",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> xoaCongViec(@RequestBody CustomRequest<CongViec> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            var ok = data.getMaCongViec();
            congViecRepository.deleteById(ok);
            return new ResponseEntity<>(ResponseEntity.ok(ok), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/danhSachPhongBan",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> danhSachPhongBan(@RequestBody CustomRequest<Void> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            return new ResponseEntity<>(ResponseEntity.ok(phongBanRepository.findAll()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/themPhongBan",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> themPhongBan(@RequestBody CustomRequest<PhongBan> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            @NonNull var phongBan = phongBanRepository.save(data);
            return new ResponseEntity<>(ResponseEntity.ok(phongBan), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/capNhatPhongBan",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> capNhatPhongBan(@RequestBody CustomRequest<PhongBan> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            Optional<PhongBan> byId = phongBanRepository.findById(data.getMaPhongBan());
            if (byId.isPresent()) {
                PhongBan phongBan = byId.get();
                var save = phongBanRepository.save(phongBan
                        .withTenPhongBan(data.getTenPhongBan())
                        .withDiaChi(data.getDiaChi())
                        .withSoDienThoai(data.getSoDienThoai())
                );
                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            } else {
                @NonNull var save = phongBanRepository.save(data);
                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/xoaPhongBan",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> xoaPhongBan(@RequestBody CustomRequest<PhongBan> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            phongBanRepository.deleteById(data.getMaPhongBan());
            return new ResponseEntity<>(ResponseEntity.ok().build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/danhSachNhanVien",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> danhSachNhanVien(@RequestBody CustomRequest<Void> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            return new ResponseEntity<>(ResponseEntity.ok(nhanVienRepository.findAll()), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/timNhanVienById",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> timNhanVienById(@RequestBody CustomRequest<NhanVien> body) {
        @NonNull var token = body.getToken();
        if (authenticationHelper.isAliveToken(token)) {
            return new ResponseEntity<>(ResponseEntity.ok(nhanVienRepository.findById(body.getData().getMaNhanVien())), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


    @RequestMapping(
            value = "/capNhatNhanVien",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> capNhatNhanVien(@RequestBody CustomRequest<NhanVien> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            var chucVu = data.getChucVu();
            if (!chucVuRepository.existsById(chucVu.getMaChucVu())) {
                chucVuRepository.save(chucVu);
            }
            var phongBan = data.getPhongBan();
            if (!phongBanRepository.existsById(phongBan.getMaPhongBan())) {
                phongBanRepository.save(phongBan);
            }

            var trinhDoHocVan = data.getTrinhDoHocVan();
            if (!trinhDoHocVanRepository.existsById(trinhDoHocVan.getMaTrinhDoHocVan())) {
                trinhDoHocVanRepository.save(trinhDoHocVan);
            }

            var byId = nhanVienRepository.findById(data.getMaNhanVien());
            if (byId.isPresent()) {
                var one = byId.get();
                var save = nhanVienRepository.save(one
                        .withHoTen(data.getHoTen())
                        .withChucVu(data.getChucVu())
                        .withDanToc(data.getDanToc())
                        .withGioiTinh(data.getGioiTinh())
                        .withNgaySinh(data.getNgaySinh())
                        .withPhongBan(data.getPhongBan())
                        .withQueQuan(data.getQueQuan())
                        .withTrinhDoHocVan(data.getTrinhDoHocVan())
                        .withSoDienThoai(data.getSoDienThoai())
                );

                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            } else {
                // new
                var save = nhanVienRepository.save(body.getData());
                return new ResponseEntity<>(ResponseEntity.ok(save), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/xoaNhanVien",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> xoaNhanVien(@RequestBody CustomRequest<NhanVien> body) {
        @NonNull var token = body.getToken();
        @NonNull var data = body.getData();
        if (authenticationHelper.isAliveToken(token)) {
            nhanVienRepository.deleteById(data.getMaNhanVien());
            return new ResponseEntity<>(ResponseEntity.ok(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }


}
