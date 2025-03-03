package org.projects.GUI.Panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.projects.GUI.MainGUI;
import org.projects.GUI.Components.MenuItemComponents;


public class WestTaskBar extends JPanel{
    private JPanel thongTinNguoiDung;
    private JPanel MenuTaskBar;
    private ListItem li;
    public WestTaskBar(MainGUI mainGui) {
        this.setLayout(new BorderLayout(0,0));

        //thong tin user
        thongTinNguoiDung = new HeaderInfoUser();
        this.add(thongTinNguoiDung,BorderLayout.NORTH);

        //menu cac item
        MenuTaskBar = new ListItem(mainGui);
        this.add(MenuTaskBar,BorderLayout.CENTER);
    }
}
