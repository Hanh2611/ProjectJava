package org.projects.GUI.Panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


import org.projects.Main;
import org.projects.GUI.LoginGUI;
import org.projects.GUI.MainGUI;
import org.projects.GUI.Components.MenuItemComponents;

public class ListItem extends JPanel{
    private List<MenuItemComponents> list;
    private HashMap<String,JPanel> mapItem;
    private MainGUI mainGui;
    public ListItem(MainGUI mainGui) {
        this.mainGui = mainGui;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.list = new ArrayList<>();
        this.mapItem = new HashMap<>();
        init();
        this.setVisible(true);
    }
    private void init() {
        //khoi tao cac ListItem
        String listItemTaskbar[][] = {
            {"src/main/java/org/projects/assets/icon/homepage.png","Trang chủ","TrangChu"},
            {"src/main/java/org/projects/assets/icon/product.png","Sản phẩm","SanPham"},
            {"src/main/java/org/projects/assets/icon/category.png","Kho hàng","KhoHang"},
            {"src/main/java/org/projects/assets/icon/contact-form.png","Phiếu nhập","PhieuNhap"},
            {"src/main/java/org/projects/assets/icon/export.png","Phiếu xuất","PhieuXuat"},
            {"src/main/java/org/projects/assets/icon/customer.png","Khách hàng","KhachHang"},
            {"src/main/java/org/projects/assets/icon/employee.png","Nhân viên","NhanVien"},
            {"src/main/java/org/projects/assets/icon/roles.png","Phân quyền","PhanQuyen"},
            {"src/main/java/org/projects/assets/icon/statistics.png","Thống kê","ThongKe"},
            {"src/main/java/org/projects/assets/icon/supplier.png","Nhà cung cấp","NhaCungCap"},
            {"src/main/java/org/projects/assets/icon/logout.png","Đăng xuất","DangXuat"},
        };

        //them ten panel vao map de truy xuat den -> hien thi panel tuong ung
        mapItem.put("TrangChu", new TrangChu());
        mapItem.put("SanPham", new SanPham());
        mapItem.put("KhoHang", new KhoHang());
        mapItem.put("PhieuNhap", new PhieuNhap());
        mapItem.put("PhieuXuat", new PhieuXuat());
        mapItem.put("KhachHang", new KhachHang());
        mapItem.put("NhanVien", new NhanVien());
        mapItem.put("PhanQuyen", new PhanQuyen());
        mapItem.put("NhaCungCap", new NhaCungCap());


        for(String[] it : listItemTaskbar) {
            String iconPath = it[0];
            String name = it[1];
            String namePanel = it[2];
            MenuItemComponents c = new MenuItemComponents(iconPath, name, namePanel, mainGui);
            c.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(namePanel.equals("DangXuat")) {
                        mainGui.dispose();
                        new LoginGUI();
                    } else showPanel(namePanel);
                }
            });
            list.add(c);
            this.add(c);
        }
        for(MenuItemComponents it : list) this.add(it);

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    }

    public void showPanel(String name) {
        JPanel panel = mapItem.get(name);
        if(panel != null) {
            mainGui.addPanelContent(panel);
        }
    }

    public List<MenuItemComponents> getList() {
        return list;
    }
}
