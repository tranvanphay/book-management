package com.example.hades.lab1_da.model;

public class HoaDonChiTiet {
    public int _id;
    public String _idsach;
    public String _idhoadon;
    public int soluongmua;

    public HoaDonChiTiet(int _id, String _idsach, String _idhoadon, int soluongmua) {
        this._id = _id;
        this._idsach = _idsach;
        this._idhoadon = _idhoadon;
        this.soluongmua = soluongmua;
    }

    public HoaDonChiTiet(String _idsach, String _idhoadon, int soluongmua) {
        this._idsach = _idsach;
        this._idhoadon = _idhoadon;
        this.soluongmua = soluongmua;
    }
}
