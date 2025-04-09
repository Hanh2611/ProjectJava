package org.projects.GUI.Panel;

import org.projects.BUS.PhanQuyenBUS;
import org.projects.BUS.TaiKhoanBUS;
import org.projects.GUI.Components.header.headerBar;
import org.projects.GUI.Components.layoutCompoment;
import org.projects.GUI.utils.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

//todo: mình sẽ cho tạo tài khoản mặc định sẽ là khách hàng, sau đó mình sẽ set tài khoản vào với nhân viên
//todo: mã người dùng sẽ được tạo tự động rồi lấy cái mã đó để có thể set cho nhân viên
//todo: trong phần chi tiết ta có tên người dùng(Lấy từ mã người dùng), tên đăng nhập, loại tài khoản, các quyền, trạng thái;
//todo: (Nhớ dùng regex) trong phần thêm ta sẽ nhập tên đăng nhập, mật khẩu, mã nhân viên (vì trước đó cần có nhân viên nên ta sẽ tạo 1 người dùng, rồi sau đó tạo tài khoản với mã người dùng đó)
//todo: (Nhớ dùng regex) trong phần sửa thì ta sẽ được quyền sửa mật khẩu, mã người dùng, quyền, trạng thái
//todo: Trong phần quyền mình có thể dùng combobox kết hợp với tìm kiếm để có thể chọn nhóm quyền 1 cách dễ dàng
//todo: Phần trạng thái cũng sẽ là combobox
//todo: Xóa thì có thể xóa tài khoản, sau đó xóa người dùng
public class TaiKhoan extends JPanel{
    private headerBar header;
    private JTable mainTable;
    private JScrollPane scrollPane;
    private JPanel contentPanel;
    private DefaultTableModel tableModel;
    private String[][] listItemHeader;
    public TaiKhoan() {
        listItemHeader = new String[][]{
                {"icon/add.svg", "Thêm", "add"},
                {"icon/content-writing.svg", "Sửa", "update"},
                {"icon/trash.svg", "Xóa", "delete"},
                {"icon/details.svg", "Chi tiết", "detail"},
                {"icon/excel.svg", "Xuất excel", "export"}
        };
        this.setBackground(Color.decode("#CAECF7"));
        this.setOpaque(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        initHeader();
    }

    public void initHeader() {
        header = new headerBar(listItemHeader, Session.quyenTaiKhoan.get(PhanQuyenBUS.getMaDanhMuc("TaiKhoan") - 1), new String[]{"--"});
        this.add(header);
    }

    public void initContent() {

    }

    public void initTable() {
        String[] col = {"Tên người dùng", "Mã người dùng", "Tên đăng nhập", "Loại người dùng", "Trạng thái"};
        tableModel = new DefaultTableModel(col, 0) {
            public boolean isCellEditable(int row, int column) {return false;}
        };

    }
    public void loadDataIntoTable() {
        List<org.projects.entity.TaiKhoan> listTaiKhoan = TaiKhoanBUS.getListTaiKhoan();
        for (org.projects.entity.TaiKhoan t : listTaiKhoan) {
            tableModel.addRow(new Object[]{TaiKhoanBUS.getTenNguoiDung(t.getMaNguoiDung()), t.getMaNguoiDung(), t.getTenDangNhap(), "", t.getTrangThai()});
        }
    }
}