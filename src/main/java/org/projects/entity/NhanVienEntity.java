package org.projects.entity;

public class NhanVienEntity {
    private String  tenNhanVien, emailNhanVien , diaChiNhanVien , sdtNhanVien;
    private int maNhanVien , luong ;
    private boolean gioitinh;
    private String chucvu;
    public NhanVienEntity() {}
    public NhanVienEntity(int ma , String ten , String email , String sdt , String chucvu){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        sdtNhanVien = sdt;
        this.chucvu = chucvu;
    }
    public NhanVienEntity(int ma , String ten , String email , String sdt , String chucvu , int luong , boolean gioitinh){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        sdtNhanVien = sdt;
        this.chucvu = chucvu;
        this.luong = luong;
        this.gioitinh = gioitinh;
    }
    public int getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getEmailNhanVien() {
        return emailNhanVien;
    }

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public String getChucvu() {
        return chucvu;
    }

    public boolean getGioitinh() {
        return gioitinh;
    }

    public int getLuong() {
        return luong;
    }
}
