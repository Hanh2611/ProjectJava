package org.projects.GUI.utils;

public class InputValid {
    private static final String TEN_DANG_NHAP_REGEX = "^[a-z0-9]{4,20}$";
    private static final String TEN_NGUOI_DUNG_REGEX = "^[a-zA-ZÀ-ỹ\\s]{3,50}$";
    private static final String TEN_HOP_LE_REGEX = "^[a-zA-ZÀ-ỹ_\\s]{3,50}$"; // có _



    public static boolean tenDangNhapHopLe(String ten) {
        return ten != null && ten.matches(TEN_DANG_NHAP_REGEX);
    }

    public static boolean tenNguoiDungHopLe(String ten) {
        return ten != null && ten.matches(TEN_NGUOI_DUNG_REGEX);
    }

    public static boolean tenHopLe(String ten) {
        return ten != null && ten.matches(TEN_HOP_LE_REGEX);
    }
}
