package com.demooo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GiaoDich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maGiaoDich;
    private String ngayGiaoDich;
    private String loaiDichVu;
    private Double donGia;
    private Double dienTich;

    @OneToMany(mappedBy = "giaoDich")
    private Set<KhachHang> khachHangs;

    public GiaoDich() {
    }

    public GiaoDich(Integer maGiaoDich, String ngayGiaoDich, String loaiDichVu, Double donGia, Double dienTich, Set<KhachHang> khachHangs) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.loaiDichVu = loaiDichVu;
        this.donGia = donGia;
        this.dienTich = dienTich;
        this.khachHangs = khachHangs;
    }

    public Integer getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(Integer maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(String ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public Set<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(Set<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }
}
