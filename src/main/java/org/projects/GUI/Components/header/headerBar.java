package org.projects.GUI.Components.header;

import java.awt.*;
import java.util.List;
import org.projects.GUI.Components.PanelBorderRadius;

public class headerBar extends PanelBorderRadius {
    private headerFunction headerFunc;
    private headerSearch search;
    public headerBar(String[][] listItemHeader, List<String> listAction, String[] listcombobox) {
        this.setPreferredSize(new Dimension(940,100));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,5, 0));
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        init(listItemHeader,listAction,listcombobox);
        this.setVisible(true);
    }
    public void init(String[][] listItemHeader, List<String> listAction, String[] listcombobox) {
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
