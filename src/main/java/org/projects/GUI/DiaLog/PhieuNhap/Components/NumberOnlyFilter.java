package org.projects.GUI.DiaLog.PhieuNhap.Components;


import javax.swing.text.*;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberOnlyFilter extends DocumentFilter {
    private final NumberFormat format = NumberFormat.getNumberInstance(Locale.US);

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("\\d+")) {
            super.insertString(fb, offset, string, attr);
            formatCurrency(fb);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d*")) { // Cho phép xóa số
            super.replace(fb, offset, length, text, attrs);
            formatCurrency(fb);
        }
    }

    private void formatCurrency(FilterBypass fb) throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength()).replaceAll("[^0-9]", "");
        if (!text.isEmpty()) {
            long value = Long.parseLong(text);
            fb.remove(0, fb.getDocument().getLength());
            fb.insertString(0, format.format(value), null);
        }
    }
}
