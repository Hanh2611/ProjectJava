package org.projects.entity;

public class DanhMucSanPham {
    private int id;
    private String tenDanhMuc;

    public DanhMucSanPham(int id, String tenDanhMuc) {
        this.id = id;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getId() {
        return id;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
