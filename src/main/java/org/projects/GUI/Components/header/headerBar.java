package org.projects.GUI.Components.header;

import java.awt.*;
import java.util.ArrayList;

public class headerBar extends javax.swing.JPanel {
    private headerFunction headerFunc;
    private headerSearch search;
    public headerBar(String[][] listItemHeader, ArrayList<String> listAction, String[] listcombobox) {
        this.setPreferredSize(new Dimension(940,100));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,5, 0));
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        init(listItemHeader,listAction,listcombobox);
        this.setVisible(true);
    }
    public void init(String[][] listItemHeader, ArrayList<String> listAction, String[] listcombobox) {
        search = new headerSearch(new Dimension(950,100),listcombobox);
        this.add(search);
        headerFunc = new headerFunction(new Dimension(950,100),listItemHeader,listAction);
        this.add(headerFunc);
    }
    //getter
    public headerFunction getHeaderFunc() {
        return headerFunc;
    }
    public headerSearch getSearch() {return search;}
}
