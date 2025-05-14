package org.projects.GUI.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ChangeDateToString {
    public static String changeDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String[] reverseDate(Date date) {
        if(date == null) return new String[]{"","",""};
        // Chuyển đổi java.util.Date sang java.time.LocalDate
        LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // Định dạng LocalDate thành chuỗi "dd-MM-yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = localDate.format(formatter);

        // Tách chuỗi thành mảng [ngày, tháng, năm]
        return formattedDate.split("-");
    }
}
