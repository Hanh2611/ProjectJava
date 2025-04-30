package org.projects.entity;


public class ThongkeHoaDonEntity {
    private String ngaytao;
    private int soluong;
    private String trangthai;
    public ThongkeHoaDonEntity(String ngaytao, int soluong, String trangthai) {
        this.ngaytao = ngaytao;
        this.soluong = soluong;
        this.trangthai = trangthai;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
