package org.projects.GUI.Components.header;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;

public class headerFunction extends JPanel {
    private generalFunction gl;
    private HashMap<String,generalFunction> hm = new HashMap<>();
    public headerFunction(Dimension parentSize, String[][] listItemHeader, List<String> listAction) {
        this.setPreferredSize(new Dimension((int) (390), parentSize.height));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        init(listItemHeader,listAction);
        this.setVisible(true);
    }
    public void init(String[][] listItemHeader, List<String> listAction) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        createHashMap(listAction);
        for(generalFunction gf : hm.values()) {
            this.add(gf,c);
            c.gridx++;
        }
    }
    public void createHashMap(List<String> listAction) {
        for(String ac : listAction) {
            switch (ac) {
                case "them":
                    hm.put("add", new generalFunction("icon/add.svg","Thêm","add"));
                    break;
                case "sua":
                    hm.put("update",new generalFunction("icon/content-writing.svg","Sửa","update"));
                    break;
                case "xoa":
                    hm.put("delete",new generalFunction("icon/trash.svg","Xóa","delete"));
                    break;
                case "xem":
                    hm.put("detail",new generalFunction("icon/details.svg","Chi tiết","detail"));
                    break;
                default:
                    hm.put("excel",new generalFunction("icon/details.svg","Chi tiết","detail"));
                    break;
            }
        }
    }

    public HashMap<String, generalFunction> getHm() {
        return hm;
    }
}
