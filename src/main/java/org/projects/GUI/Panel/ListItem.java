package org.projects.GUI.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


import org.projects.GUI.LoginGUI;
import org.projects.GUI.MainGUI;
import org.projects.GUI.Components.MenuItemComponents;
import org.projects.GUI.Panel.NhanVienPack.NhanVien;
import org.projects.GUI.Panel.PhanQuyenPack.PhanQuyen;
import org.projects.GUI.Panel.ThongkePack.ThongKe;


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
                {"icon/homepage.svg", "Trang chủ", "TrangChu"},
                {"icon/dairy-products.svg", "Sản phẩm", "SanPham"},
                {"icon/contact-form.svg", "Phiếu nhập", "PhieuNhap"},
                {"icon/bill.svg", "Hóa đơn", "HoaDon"},
                {"icon/customer.svg", "Khách hàng", "KhachHang"},
                {"icon/cashier.svg", "Nhân viên", "NhanVien"},
                {"icon/roles.svg", "Phân quyền", "PhanQuyen"},
                {"icon/verified-account.svg", "Tài khoản", "TaiKhoan"},
                {"icon/supplier.svg", "Nhà cung cấp", "NhaCungCap"},
                {"icon/analysis.svg", "Thống kê", "ThongKe"},
                {"icon/logout.svg", "Đăng xuất", "DangXuat"}
        };

        //them ten panel vao map de truy xuat den -> hien thi panel tuong ung
        mapItem.put("TrangChu", new TrangChu());
        mapItem.put("SanPham", new SanPham());
        mapItem.put("TaiKhoan", new TaiKhoan());
        mapItem.put("PhieuNhap", new PhieuNhap());
        mapItem.put("HoaDon", new HoaDon());
        mapItem.put("KhachHang", new KhachHang());
        mapItem.put("NhanVien", new NhanVien());
        mapItem.put("NhaCungCap", new NhaCungCap());
        mapItem.put("PhanQuyen", new PhanQuyen());
        mapItem.put("ThongKe", new ThongKe());


        for(String[] it : listItemTaskbar) {
            String iconPath = it[0];
            String name = it[1];
            String namePanel = it[2];
            MenuItemComponents c = new MenuItemComponents(iconPath, name, namePanel, mainGui);
            c.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickChangeColor(e);
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

    public void clickChangeColor(MouseEvent e) {
        for(MenuItemComponents it : list) {
            if(e.getSource() == it) {
                it.getNameLabel().setForeground(Color.WHITE);
                it.setBackground(Color.GRAY);
            } else {
                it.getNameLabel().setForeground(Color.BLACK);
                it.setBackground(Color.WHITE);
            }
        }
    }

    public List<MenuItemComponents> getList() {
        return list;
    }
}
