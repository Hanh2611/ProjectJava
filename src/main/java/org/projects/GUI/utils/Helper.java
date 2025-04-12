package org.projects.GUI.utils;

import org.projects.GUI.Panel.SanPham;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Helper {

    public static String imageBasePath = "src\\main\\resources\\img\\product\\";

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

}
