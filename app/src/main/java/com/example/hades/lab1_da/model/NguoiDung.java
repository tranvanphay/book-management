package com.example.hades.lab1_da.model;

public class NguoiDung {
    public String username;
    public String passwork;
    public String sodienthoai;
    public String hoten;

    public NguoiDung(String username, String passwork, String sodienthoai, String hoten) {
        this.username = username;
        this.passwork = passwork;
        this.sodienthoai = sodienthoai;
        this.hoten = hoten;
    }

    public NguoiDung(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
}
