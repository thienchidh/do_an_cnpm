package com.example.do_an_cnpm.data_source.dao;

import com.example.do_an_cnpm.data_source.repo.NhanVienRepository;
import com.example.do_an_cnpm.model.NhanVien;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDAOImpl implements UserDAO {

    private NhanVienRepository repository;

    @Override
    public <S extends NhanVien> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public List<NhanVien> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NhanVien> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<NhanVien> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void save(NhanVien o) {
        repository.save(o);
    }

    @Override
    public void saveAll(Iterable<NhanVien> iterable) {
        repository.saveAll(iterable);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(NhanVien o) {
        repository.delete(o);
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }
}