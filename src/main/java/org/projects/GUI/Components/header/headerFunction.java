package org.projects.GUI.Components.header;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class headerFunction extends JToolBar {
    private HashMap<String,generalFunction> hm = new HashMap<>();
    public headerFunction(Dimension parentSize, String listItemHeader[][],String[] listAction) {
        this.setPreferredSize(new Dimension((int) (390), parentSize.height));
        this.setFloatable(false);
//        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        init(listItemHeader,listAction);
        this.setVisible(true);
    }
    public void init(String listItemHeader[][],String[] listAction) {
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 0;
//        c.weightx = 0.5;
//        c.weighty = 0.5;
//        c.anchor = GridBagConstraints.CENTER;
        for(String la : listAction) {
            switch (la) {
                case "add":
                    hm.put("add",new generalFunction("icon/add.svg","Thêm","add"));
                    break;
                case "update":
                    hm.put("update",new generalFunction("icon/content-writing.svg","Sửa","update"));
                    break;
                case "delete":
                    hm.put("delete",new generalFunction("icon/trash.svg","Xóa","delete"));
                    break;
                case "detail":
                    hm.put("detail",new generalFunction("icon/details.svg","Chi tiết","detail"));
                    break;
            }
        }

        for(generalFunction gf : hm.values()) {
            this.add(gf);
        }

//        for (String [] i : listItemHeader) {
//          generalFunction  gl = new generalFunction(i[0],i[1],i[2]);
//            this.add(gl, c);
//            this.add(gl);
//            c.gridx++;
//        }
    }
    //getter
    public HashMap<String,generalFunction> getHm() {
        return hm;
    }
}
