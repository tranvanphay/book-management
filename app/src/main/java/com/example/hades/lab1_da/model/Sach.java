package com.example.hades.lab1_da.model;

public class Sach {
    public String _id;
    public String _idloai;
    public String tensach;
    public String tacgia;
    public String nhaxuatban;
    public int giabia;
    public int soluong;


    public Sach(String _id, String _idloai, String tensach, String tacgia, String nhaxuatban, int giabia, int soluong) {
        this._id = _id;
        this._idloai = _idloai;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nhaxuatban = nhaxuatban;
        this.giabia = giabia;
        this.soluong = soluong;
    }

    public Sach() {

    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_idloai() {
        return _idloai;
    }

    public void set_idloai(String _idloai) {
        this._idloai = _idloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNhaxuatban() {
        return nhaxuatban;
    }

    public void setNhaxuatban(String nhaxuatban) {
        this.nhaxuatban = nhaxuatban;
    }

    public int getGiabia() {
        return giabia;
    }

    public void setGiabia(int giabia) {
        this.giabia = giabia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
