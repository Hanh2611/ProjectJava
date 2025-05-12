package org.projects.GUI.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDateToString {
    public static String changeDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
