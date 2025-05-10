package org.projects.GUI.utils;

import com.toedter.calendar.JDateChooser;
import org.apache.poi.ss.formula.functions.DateValue;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ChangeDateToString {
    public static String changeDate(JDateChooser date) {
        return (date.getDate() != null) ? new SimpleDateFormat("yyyyy-MM-dd").format(date.getDate()) : "";
    }

}
