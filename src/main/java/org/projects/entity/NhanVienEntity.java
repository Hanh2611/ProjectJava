package org.projects.entity;

public class NhanVienEntity {
    private String  tenNhanVien, emailNhanVien , diaChiNhanVien , sdtNhanVien , ngaySinhNhanVien ,buoiLamNhanVien;
    private double luongNhanVienTheoGio , luongNhanVienTheoTong;
    private int maNhanVien;
    private String chucvu;
    public NhanVienEntity(int ma , String ten , String email , String diaChi , String sdt , String ngaySinh , String buoiLam , double _luongNhanVienTheoGio){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        diaChiNhanVien = diaChi;
        sdtNhanVien = sdt;
        buoiLamNhanVien = buoiLam;
        ngaySinhNhanVien = ngaySinh;
        luongNhanVienTheoGio = _luongNhanVienTheoGio;
    }

    public NhanVienEntity(int ma , String ten , String email , String sdt , String chucvu){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        sdtNhanVien = sdt;
        this.chucvu = chucvu;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getEmailNhanVien() {
        return emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    public String getDiaChiNhanVien() {
        return diaChiNhanVien;
    }

    public void setDiaChiNhanVien(String diaChiNhanVien) {
        this.diaChiNhanVien = diaChiNhanVien;
    }

    public String getNgaySinhNhanVien() {
        return ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(String ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getBuoiLamNhanVien() {
        return buoiLamNhanVien;
    }

    public void setBuoiLamNhanVien(String buoiLamNhanVien) {
        this.buoiLamNhanVien = buoiLamNhanVien;
    }

    public double getLuongNhanVienTheoGio() {
        return luongNhanVienTheoGio;
    }

    public void setLuongNhanVienTheoGio(double luongNhanVienTheoGio) {
        this.luongNhanVienTheoGio = luongNhanVienTheoGio;
    }

    public double getLuongNhanVienTheoTong() {
        return luongNhanVienTheoTong;
    }

    public void setLuongNhanVienTheoTong(double luongNhanVienTheoTong) {
        this.luongNhanVienTheoTong = luongNhanVienTheoTong;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getChucvu() {
        return chucvu;
    }
}
