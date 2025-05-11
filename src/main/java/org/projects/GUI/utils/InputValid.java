package org.projects.GUI.utils;

import org.projects.config.DatabasesConfig;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InputValid {
    private static final String TEN_DANG_NHAP_REGEX = "^[a-z0-9]{4,20}$";
    private static final String TEN_NGUOI_DUNG_REGEX = "^[a-zA-ZÀ-ỹ\\s]{3,50}$";
    private static final String TEN_HOP_LE_REGEX = "^[a-zA-ZÀ-ỹ_\\s]{3,50}$"; // có _
    private static final String MA_HOP_LE_REGEX = "\\d+";
    private static final String EMAIL_HOP_LE_REGEX = "^[A-Za-z0-9+_.-]+@gmail+\\.com$";
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
    public static boolean checkRong_addPlace(String addplace, String key) {
        return key == null || key.trim().isEmpty() || key.equals(addplace);
    }
    public static boolean checkMa(String ma) {
        return ma != null && ma.matches(MA_HOP_LE_REGEX);
    }
    public static boolean checkEmail(String email) {
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
    public static void validateLuongInput(JTextField textField, int index, ArrayList<JLabel> errorLabels , ArrayList<JTextField> listAdd) {
        String luongText = textField.getText().trim();

        if (luongText.isEmpty() || luongText.equals("Nhập lương nhân viên")) {
            InputValid.showError(index,"Vui lòng điền số lương", errorLabels , listAdd , false);
            return;
        }

        try {
            long luong = Long.parseLong(luongText);
            if (luong < 1000) {
                InputValid.showError(index, "Lương phải lớn hơn hoặc bằng 1,000", errorLabels , listAdd , false);
            } else if (luong > Integer.MAX_VALUE) {
                InputValid.showError(index, "Lương được giới hạn ở mức tối đa (số int)", errorLabels , listAdd, false);
            } else {
                InputValid.clearError(index, errorLabels , listAdd , false);
            }
        } catch (NumberFormatException e) {
            InputValid.showError(index, "Lương phải là số nguyên", errorLabels , listAdd, false);
        }
    }
    public static void showError(int index, String message , ArrayList<JLabel> errorLabels , ArrayList<JTextField> listAdd , boolean full) {
        if (index >= 0 && index < errorLabels.size()) {
            errorLabels.get(index).setText(message);
            errorLabels.get(index).setForeground(Color.RED);
            errorLabels.get(index).setVisible(true);
            errorLabels.get(index).revalidate();
            errorLabels.get(index).repaint();
            //errorLabels.get(index).getParent().revalidate();
            //errorLabels.get(index).getParent().repaint();
            setErrorBorder(listAdd.get(index) , full);
        }
    }

    public static void clearError(int index , ArrayList<JLabel> errorLabels , ArrayList<JTextField> listAdd , boolean full) {
        if (index >= 0 && index < errorLabels.size()) {
            errorLabels.get(index).setText("");
            errorLabels.get(index).setVisible(false);
            errorLabels.get(index).revalidate();
            errorLabels.get(index).repaint();
            //errorLabels.get(index).getParent().revalidate();
            //errorLabels.get(index).getParent().repaint();
            setNormalBorder(listAdd.get(index) , full);
        }
    }

    public static void setErrorBorder(JTextField textField , boolean full) {
        if(full){
            Border redBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
            textField.setBorder(redBorder);
        }else{
            Border redBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
            textField.setBorder(redBorder);
        }
    }

    public static void setNormalBorder(JTextField textField , boolean full) {
        if(full){
            Border normalBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green);
            textField.setBorder(normalBorder);
        }else{
            Border normalBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.green);
            textField.setBorder(normalBorder);
        }
    }

    public static void resetBorder(JTextField textField , boolean full) {
        if(full){
            Border normalBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(220, 220, 220));
            textField.setBorder(normalBorder);
        }else{
            Border normalBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220, 220, 220));
            textField.setBorder(normalBorder);
        }
    }
}
