package org.projects.entity;

public class NhomQuyenEntity {
    int maNhomQuyen;
    String tenNomQuyen;
    public NhomQuyenEntity() {}
    public NhomQuyenEntity(int maNhomQuyen, String tenNomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
        this.tenNomQuyen = tenNomQuyen;
    }

    public int getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public String getTenNomQuyen() {
        return tenNomQuyen;
    }

    public void setMaNhomQuyen(int maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }

    public void setTenNomQuyen(String tenNomQuyen) {
        this.tenNomQuyen = tenNomQuyen;
    }
}
