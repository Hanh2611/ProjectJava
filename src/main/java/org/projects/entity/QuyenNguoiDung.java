package org.projects.entity;

public class QuyenNguoiDung {
    private int ma_nguoi_dung, ma_nhom_quyen;
    public QuyenNguoiDung() {}
    public QuyenNguoiDung(int ma_nguoi_dung, int ma_nhom_quyen) {
        this.ma_nguoi_dung = ma_nguoi_dung;
        this.ma_nhom_quyen = ma_nhom_quyen;
    }

    public int getMa_nguoi_dung() {
        return ma_nguoi_dung;
    }

    public void setMa_nguoi_dung(int ma_nguoi_dung) {
        this.ma_nguoi_dung = ma_nguoi_dung;
    }

    public int getMa_nhom_quyen() {
        return ma_nhom_quyen;
    }

    public void setMa_nhom_quyen(int ma_nhom_quyen) {
        this.ma_nhom_quyen = ma_nhom_quyen;
    }
}
