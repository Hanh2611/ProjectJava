package org.projects.GUI.Components;

import javax.swing.text.*;
import java.text.DecimalFormat;

public class NumberOnlyFilter extends DocumentFilter {
    private final DecimalFormat format = new DecimalFormat("#,###");

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("\\d+")) {
            super.insertString(fb, offset, string, attr);
            formatCurrency(fb);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d*")) {
            super.replace(fb, offset, length, text, attrs);
            formatCurrency(fb);
        }
    }

    private void formatCurrency(FilterBypass fb) throws BadLocationException {
        Document doc = fb.getDocument();
        String rawText = doc.getText(0, doc.getLength()).replaceAll("[^\\d]", "");

        if (!rawText.isEmpty()) {
            long value = Long.parseLong(rawText);
            String formatted = format.format(value).replace(",", ".") + " ₫"; // Đổi dấu ',' thành '.' và thêm đơn vị
            fb.remove(0, doc.getLength());
            fb.insertString(0, formatted, null);
        }
    }
}
