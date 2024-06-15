package com.example.PhanHuyTri.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PhongBan")
public class PhongBan {

    @Id
    @Column(length = 2, nullable = false)
    private String maPhong;

    @Column(length = 30, nullable = false)
    private String tenPhong;

    @OneToMany(mappedBy = "phongBan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<NhanVien> nhanVien;
}

