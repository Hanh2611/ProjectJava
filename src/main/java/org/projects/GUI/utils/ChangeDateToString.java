package org.projects.GUI.utils;

import com.toedter.calendar.JDateChooser;

import java.text.SimpleDateFormat;

public class ChangeDateToString {
    public static String changeDate(JDateChooser date) {
        return (date.getDate() != null) ? new SimpleDateFormat("dd/MM/yyyy").format(date.getDate()) : "";
    }
}
