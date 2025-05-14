package org.projects.entity;

import org.projects.GUI.utils.FormatMoney;

public class ThongkeDoanhThuEntity {
    private String thang,nam;
    private int mahoadon;
    private String tenkhachhang;
    private String ngaytaodon;
    private double tongtienhoadon;
    private double tongchiphinhaptrongthang;
    private double loinhuan;
    public ThongkeDoanhThuEntity(int mahoadon,String tenkhachhang,String ngaytaodon,double tongtienhoadon) {
        this.mahoadon = mahoadon;
        this.tenkhachhang = tenkhachhang;
        this.ngaytaodon = ngaytaodon;
        this.tongtienhoadon = tongtienhoadon;
    }
    public ThongkeDoanhThuEntity(String thang,String nam,double tongtienhoadon,double tongchiphinhaptrongthang,double loinhuan) {
        this.thang = thang;
        this.nam = nam;
        this.tongtienhoadon = tongtienhoadon;
        this.tongchiphinhaptrongthang = tongchiphinhaptrongthang;
        this.loinhuan = loinhuan;
    }
    public ThongkeDoanhThuEntity(String nam,double tongtienhoadon,double tongchiphinhaptrongthang,double loinhuan) {
        this.nam = nam;
        this.tongtienhoadon = tongtienhoadon;
        this.tongchiphinhaptrongthang = tongchiphinhaptrongthang;
        this.loinhuan = loinhuan;
    }

    //format tong tien hoa don,tong chi phi nhap trong thang,loi nhuan
    public String getTongtienhoadonformat() {
      return FormatMoney.format(tongtienhoadon);
    }
    public String getTongchiphinhaptrongthangformat() {
        return FormatMoney.format(tongchiphinhaptrongthang);
    }
    public String getLoinhuanformat() {
        return FormatMoney.format(loinhuan);
    }
    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public double getTongchiphinhaptrongthang() {
        return tongchiphinhaptrongthang;
    }

    public void setTongchiphinhaptrongthang(double tongchiphinhaptrongthang) {
        this.tongchiphinhaptrongthang = tongchiphinhaptrongthang;
    }

    public double getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(double loinhuan) {
        this.loinhuan = loinhuan;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getNgaytaodon() {
        return ngaytaodon;
    }

    public void setNgaytaodon(String ngaytaodon) {
        this.ngaytaodon = ngaytaodon;
    }

    public double getTongtienhoadon() {
        return tongtienhoadon;
    }

    public void setTongtienhoadon(double tongtienhoadon) {
        this.tongtienhoadon = tongtienhoadon;
    }
}
