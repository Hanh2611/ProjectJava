package org.projects.GUI.utils;

import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.config.DatabasesConfig;
import org.projects.entity.NhanVienEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InputValid {
    private static final String TEN_DANG_NHAP_REGEX = "^[a-z0-9]{4,20}$";
    private static final String TEN_NGUOI_DUNG_REGEX = "^[a-zA-ZÀ-ỹ\\s]{3,50}$";
    private static final String TEN_HOP_LE_REGEX = "^[a-zA-ZÀ-ỹ_\\s]{3,50}$"; // có _
    private static final String MA_HOP_LE_REGEX = "\\d+";
    private static final String EMAIL_HOP_LE_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";
    private static final String SO_DIEN_THOAI_HOP_LE_REGEX = "0\\d{9}";


    public static boolean tenDangNhapHopLe(String ten) {
        return ten != null && ten.matches(TEN_DANG_NHAP_REGEX);
    }

    public static boolean tenNguoiDungHopLe(String ten) {
        return ten != null && ten.matches(TEN_NGUOI_DUNG_REGEX);
    }

    public static boolean tenHopLe(String ten) {
        return ten != null && ten.matches(TEN_HOP_LE_REGEX);
    }
    // Regex nhan vien
    public static boolean checkRong_addPlace(String addplace , String key){
        return key == null || key.equals(addplace);
    }
    public static boolean checkMaNhanVien(String ma) {
        return ma != null && ma.matches(MA_HOP_LE_REGEX);
    }
    public static boolean checkEmailNhanVien(String email) {
        return email != null && email.matches(EMAIL_HOP_LE_REGEX);
    }
    public static boolean checkSoDienThoai(String sdt) {
        return sdt != null && sdt.matches(SO_DIEN_THOAI_HOP_LE_REGEX);
    }
    public static boolean checkSameId(int id , String sql){
        try(Connection c = DatabasesConfig.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
