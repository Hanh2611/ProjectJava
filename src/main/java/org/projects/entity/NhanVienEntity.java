package org.projects.entity;

public class NhanVienEntity {
    private String  tenNhanVien, emailNhanVien , diaChiNhanVien , sdtNhanVien;
    private int maNhanVien , luong ;
    private boolean gioitinh;
    private String chucvu , avatar;
    private boolean is_delete;
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
    public NhanVienEntity(int ma , String ten , String email , String sdt , String chucvu , int luong , boolean gioitinh , String avatar){
        maNhanVien = ma;
        tenNhanVien = ten;
        emailNhanVien = email;
        sdtNhanVien = sdt;
        this.chucvu = chucvu;
        this.luong = luong;
        this.gioitinh = gioitinh;
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
    public boolean isIs_delete() {
        return is_delete;
    }
}
