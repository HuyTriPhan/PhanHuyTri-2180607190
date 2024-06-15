package com.example.PhanHuyTri.controller;

import com.example.PhanHuyTri.model.NhanVien;
import com.example.PhanHuyTri.service.NhanVienService;
import com.example.PhanHuyTri.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;


@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private PhongBanService phongBanService;

    // Display a list of all employees
    @GetMapping
    public String showNhanVienList(Model model) {
        List<NhanVien> nhanVien = nhanVienService.getAllNhanViens();
        model.addAttribute("nhanViens", nhanVien);
        return "nhanviens/nhanvien-list";
    }

    // For adding a new employee
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("phongBans", phongBanService.getAllPhongBans()); // Load departments
        return "nhanviens/add-nhanvien";
    }

    // Process the form for adding a new employee
    @PostMapping("/add")
    public String addNhanVien(@Valid @ModelAttribute NhanVien nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phongBans", phongBanService.getAllPhongBans());
            return "nhanviens/add-nhanvien";
        }

        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }

    // For editing an employee
    @GetMapping("/edit/{maNV}")
    public String showEditForm(@PathVariable String maNV, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienByMaNV(maNV)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee MaNV:" + maNV));
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("phongBans", phongBanService.getAllPhongBans()); // Load departments
        return "nhanviens/update-nhanvien";
    }

    // Process the form for updating an employee
    @PostMapping("/update/{maNV}")
    public String updateNhanVien(@PathVariable String maNV, @Valid @ModelAttribute NhanVien nhanVien,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            nhanVien.setMaNV(maNV); // Set maNV to keep it in the form in case of errors
            model.addAttribute("phongBans", phongBanService.getAllPhongBans());
            return "nhanviens/update-nhanvien";
        }

        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }

    // Handle request to delete an employee
    @GetMapping("/delete/{maNV}")
    public String deleteNhanVien(@PathVariable String maNV) {
        nhanVienService.deleteNhanVienByMaNV(maNV);
        return "redirect:/nhanviens";
    }
}
