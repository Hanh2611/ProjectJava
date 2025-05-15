package org.projects.GUI.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(JLabel.CENTER);

        if ((column == 5 && "Hết hàng".equals(value))|| (column == 6 && "Ngừng bán".equals(value))) {
            cell.setForeground(Color.RED);
        } else {
            cell.setForeground(table.getForeground());
        }

        return cell;
    }
}