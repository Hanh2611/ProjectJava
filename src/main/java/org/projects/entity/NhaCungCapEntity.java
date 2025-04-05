package org.projects.entity;

public class NhaCungCapEntity {
    private int maNCC;
    private String tenNCC;
    private String soDienThoaiNCC;
    private String emailNCC;
    private String diaChiNCC;
    public NhaCungCapEntity(int maNCC,String tenNCC,String soDienThoaiNCC,String emailNCC,String diaChiNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.soDienThoaiNCC = soDienThoaiNCC;
        this.emailNCC = emailNCC;
        this.diaChiNCC = diaChiNCC;
    }

    public NhaCungCapEntity(String tenNCC,String soDienThoaiNCC,String emailNCC,String diaChiNCC) {
        this.tenNCC = tenNCC;
        this.soDienThoaiNCC = soDienThoaiNCC;
        this.emailNCC = emailNCC;
        this.diaChiNCC = diaChiNCC;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getSoDienThoaiNCC() {
        return soDienThoaiNCC;
    }

    public String getEmailNCC() {
        return emailNCC;
    }

    public String getDiaCHiNCC() {
        return diaChiNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setSoDienThoaiNCC(String soDienThoaiNCC) {
        this.soDienThoaiNCC = soDienThoaiNCC;
    }

    public void setEmailNCC(String emailNCC) {
        this.emailNCC = emailNCC;
    }

    public void setDiaChiNCC(String diaChiNCC) {
        this.diaChiNCC = diaChiNCC;
    }
}
