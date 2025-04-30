package org.projects.GUI.utils;

import org.projects.GUI.Panel.SanPham;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Helper {

//    public static String imageBasePath = "src\\main\\resources\\img\\product\\"; window
//    public static String imageBasePath = "src/main/resources/img/product/"; linux
public static String imageBasePath = "src" + File.separator + "main" + File.separator +
        "resources" + File.separator + "img" + File.separator + "product" + File.separator;


    public static String getProductImagePath(String imageFileName) {
        return Helper.imageBasePath + imageFileName;
    }

    public static String formatPrice (double price){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,###'đ'", symbols);
        return formatter.format(price);
    }

    public static int layNgaytrongthang(int thang,int nam) {
        switch (thang) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                // Kiểm tra năm nhuận
                if ((nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                throw new IllegalArgumentException("Tháng không hợp lệ: " + thang);
        }
    }

}
