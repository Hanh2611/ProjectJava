package org.projects.GUI.Components.header;

import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.utils.FocusListenerUtils;

import javax.swing.*;
import java.awt.*;

public class headerSearch extends javax.swing.JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchComboBox;

    public headerSearch(Dimension parentSize,String[] listcombobox) {
        this.setPreferredSize(new Dimension((int) (540), parentSize.height));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        init(listcombobox);
        this.setVisible(true);
    }

    public void init(String[] listcombobox) {
        searchField = handleComponents.createTextField("Nhập tên đăng nhập.....", 60, 190, 310, 40);
        searchField.addFocusListener(FocusListenerUtils.createPlaceholderTextField("Nhập tên đăng nhập.....", searchField));
        searchButton = handleComponents.createButtonIcon("icon/refresh.svg",40,40);
        searchComboBox = new JComboBox<>(listcombobox);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(10, 5, 10, 5);
        c.gridx = 0;
        c.weightx = 0.0;
        c.ipady = 10;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        this.add(searchComboBox, c);
        c.insets = new Insets(10, 5, 10, 0);
        c.gridx = 1;
        c.weightx = 1.0;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(searchField, c);
        c.insets = new Insets(10, 5, 10, 5);
        c.gridx = 6;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        this.add(searchButton, c);
    }
    //getter
    public JTextField getSearchField() {
        return searchField;
    }
    public JButton getSearchButton() {
        return searchButton;
    }
    public JComboBox<String> getSearchComboBox() {
        return searchComboBox;
    }
    //setter
    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }
    public void setSearchComboBox(JComboBox<String> searchComboBox) {
        this.searchComboBox = searchComboBox;
    }

}
