package com.demooo.model;

import javax.persistence.*;

@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKhachHang;
    private String tenKhachHang;
    @Column(unique = true)
    private String sdt;
    @Column(unique = true)
    private String email;
}
