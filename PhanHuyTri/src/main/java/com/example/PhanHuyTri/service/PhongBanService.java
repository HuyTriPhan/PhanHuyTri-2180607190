package com.example.PhanHuyTri.service;

import com.example.PhanHuyTri.model.PhongBan;
import com.example.PhanHuyTri.repository.PhongBanRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing PhongBan.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;

    /**
     * Retrieve all PhongBans from the database.
     * @return a list of PhongBans
     */
    public List<PhongBan> getAllPhongBans() {
        return phongBanRepository.findAll();
    }

    /**
     * Retrieve a PhongBan by its id.
     * @param id the id of the PhongBan to retrieve
     * @return an Optional containing the found PhongBan or empty if not found
     */
    public Optional<PhongBan> getPhongBanById(String id) {
        return phongBanRepository.findById(id);
    }

    /**
     * Add a new PhongBan to the database.
     * @param phongBan the PhongBan to add
     */
    public void addPhongBan(PhongBan phongBan) {
        phongBanRepository.save(phongBan);
    }

    /**
     * Update an existing PhongBan.
     * @param phongBan the PhongBan with updated information
     */
    public void updatePhongBan(@NotNull PhongBan phongBan) {
        PhongBan existingPhongBan = phongBanRepository.findById(phongBan.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("PhongBan with ID " +
                        phongBan.getMaPhong() + " does not exist."));
        existingPhongBan.setTenPhong(phongBan.getTenPhong());
        phongBanRepository.save(existingPhongBan);
    }

    /**
     * Delete a PhongBan by its id.
     * @param id the id of the PhongBan to delete
     */
    public void deletePhongBanById(String id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("PhongBan with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}
