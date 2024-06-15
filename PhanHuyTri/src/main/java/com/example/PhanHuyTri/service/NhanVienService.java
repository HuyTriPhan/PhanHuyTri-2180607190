package com.example.PhanHuyTri.service;

import com.example.PhanHuyTri.model.NhanVien;
import com.example.PhanHuyTri.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    // Retrieve all employees from the database
    public List<NhanVien> getAllNhanViens() {
        return nhanVienRepository.findAll();
    }

    // Retrieve an employee by its maNV
    public Optional<NhanVien> getNhanVienByMaNV(String maNV) {
        return nhanVienRepository.findById(maNV);
    }

    // Add a new employee to the database
    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    // Update an existing employee
    public NhanVien updateNhanVien(@NotNull NhanVien nhanVien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVien.getMaNV())
                .orElseThrow(() -> new IllegalStateException("Employee with MaNV " +
                        nhanVien.getMaNV() + " does not exist."));
        existingNhanVien.setTenNV(nhanVien.getTenNV());
        existingNhanVien.setPhai(nhanVien.getPhai());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());
        return nhanVienRepository.save(existingNhanVien);
    }

    // Delete an employee by its maNV
    public void deleteNhanVienByMaNV(String maNV) {
        if (!nhanVienRepository.existsById(maNV)) {
            throw new IllegalStateException("Employee with MaNV " + maNV + " does not exist.");
        }
        nhanVienRepository.deleteById(maNV);
    }
}
