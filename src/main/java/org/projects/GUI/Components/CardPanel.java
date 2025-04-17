package org.projects.GUI.Components;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private String nameCard;
    private String value;
    private JLabel iconLabel;
    private JLabel nameCardLabel;
    private JLabel valueLabel;

    public CardPanel(String iconPath,String nameCard, String value) {
        FlatSVGIcon icon = new FlatSVGIcon(iconPath, 40, 40);
        this.setLayout(new BorderLayout(5,5));
        this.setBackground(Color.decode("#d2dae2"));
        this.setPreferredSize(new Dimension(150,80));
        iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        nameCardLabel = new JLabel(nameCard, SwingConstants.CENTER);
        nameCardLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameCardLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        nameCardLabel.setForeground(Color.decode("#4b4b4b"));
        valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setForeground(Color.decode("#3d3d3d"));
        valueLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 18));
        this.add(iconLabel, BorderLayout.WEST);
        this.add(nameCardLabel, BorderLayout.NORTH);
        this.add(valueLabel, BorderLayout.CENTER);
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }

    public JLabel getNameCardLabel() {
        return nameCardLabel;
    }

    public void setNameCardLabel(JLabel nameCardLabel) {
        this.nameCardLabel = nameCardLabel;
    }

    public JLabel getValueLabel() {
        return valueLabel;
    }

    public void setValueLabel(JLabel valueLabel) {
        this.valueLabel = valueLabel;
    }
}
