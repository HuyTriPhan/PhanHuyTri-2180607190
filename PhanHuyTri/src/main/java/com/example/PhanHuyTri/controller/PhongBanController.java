package com.example.PhanHuyTri.controller;

import com.example.PhanHuyTri.model.PhongBan;
import com.example.PhanHuyTri.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhongBanController {
    private final PhongBanService phongBanService;

    @GetMapping("/phongban/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongBan", new PhongBan());
        return "phongban/add-phongban";
    }

    @PostMapping("/phongban/add")
    public String addPhongBan(@Valid PhongBan phongBan, BindingResult result) {
        if (result.hasErrors()) {
            return "phongban/add-phongban";
        }
        phongBanService.addPhongBan(phongBan);
        return "redirect:/phongban";
    }

    @GetMapping("/phongban")
    public String listPhongBans(Model model) {
        List<PhongBan> phongBans = phongBanService.getAllPhongBans();
        model.addAttribute("phongBans", phongBans);
        return "phongban/phongban-list";
    }

    @GetMapping("/phongban/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phongBan Id:" + id));
        model.addAttribute("phongBan", phongBan);
        return "phongban/update-phongban";
    }

    @PostMapping("/phongban/update/{id}")
    public String updatePhongBan(@PathVariable("id") String id, @Valid PhongBan phongBan,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongBan.setMaPhong(id);
            return "phongban/update-phongban";
        }
        phongBanService.updatePhongBan(phongBan);
        model.addAttribute("phongBans", phongBanService.getAllPhongBans());
        return "redirect:/phongban";
    }

    @GetMapping("/phongban/delete/{id}")
    public String deletePhongBan(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phongBan Id:" + id));
        phongBanService.deletePhongBanById(id);
        model.addAttribute("phongBans", phongBanService.getAllPhongBans());
        return "redirect:/phongban";
    }
}
