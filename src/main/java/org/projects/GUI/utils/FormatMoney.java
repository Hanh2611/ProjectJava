package org.projects.GUI.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatMoney {
    private static final Locale localeVN = new Locale("vi", "VN");
    private static final NumberFormat vndFormatter = NumberFormat.getCurrencyInstance(localeVN);

    // Định dạng int sang VND
    public static String format(int amount) {
        return vndFormatter.format(amount);
    }

    // Định dạng double sang VND
    public static String format(double amount) {
        return vndFormatter.format(amount);
    }

    static String formatCurrency(long amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(amount).replace(",", ".") + " ₫";
    }
}
