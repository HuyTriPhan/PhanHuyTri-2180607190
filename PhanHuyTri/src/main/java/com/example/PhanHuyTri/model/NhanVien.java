package com.example.PhanHuyTri.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @Column(length = 3, nullable = false)
    private String maNV;

    @Column(length = 100, nullable = false)
    private String tenNV;

    @Column(length = 3)
    private String phai;

    @Column(length = 200)
    private String noiSinh;

    @Column(nullable = false)
    private int luong;

    @ManyToOne
    @JoinColumn(name = "maPhong", nullable = false)
    private PhongBan phongBan;
}

