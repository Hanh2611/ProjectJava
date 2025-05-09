package org.projects.entity;

public class KhachHangEntity {
    public String ten , diaChi , sdt , avatar;
    public int ma;
    public KhachHangEntity(int ma,  String ten , String sdt, String diaChi){
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public KhachHangEntity(int ma , String ten , String sdt, String diaChi, String avatar){
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.avatar = avatar;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
