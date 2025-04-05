package org.projects.entity;

public class NhanVienEntity {
    private String maNhanVien , tenNhanVien, emailNhanVien , diaChiNhanVien , sdtNhanVien , ngaySinhNhanVien ,buoiLamNhanVien;
    private double luongNhanVienTheoGio , luongNhanVienTheoTong;
    public NhanVienEntity(String ma , String ten , String email , String diaChi , String sdt , String ngaySinh , String buoiLam , double _luongNhanVienTheoGio){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        diaChiNhanVien = diaChi;
        sdtNhanVien = sdt;
        buoiLamNhanVien = buoiLam;
        ngaySinhNhanVien = ngaySinh;
        luongNhanVienTheoGio = _luongNhanVienTheoGio;
    }

    public NhanVienEntity(String ma , String ten , String email , String diaChi , String sdt){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        diaChiNhanVien = diaChi;
        sdtNhanVien = sdt;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
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
}
