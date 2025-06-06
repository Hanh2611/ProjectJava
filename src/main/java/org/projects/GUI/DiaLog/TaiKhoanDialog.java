package org.projects.GUI.DiaLog;

import org.projects.Action.TaiKhoanAction;
import org.projects.BUS.PhanQuyenBUS;
import org.projects.BUS.TaiKhoanBUS;
import org.projects.DAO.NhomQuyenDAO;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.PanelBorderRadius;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.TaiKhoan;
import org.projects.entity.TaiKhoanEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TaiKhoanDialog extends JDialog {
    private TaiKhoan tk;
    private TaiKhoanEntity tkEntity;
    private String tkType;

    private PanelBorderRadius center;
    private labelText tendangnhap;
    private labelText matkhau;
    private labelText manhanvien;
    private labelText quyen;
    private labelText trangthai;
    private JPanel header;
    private JPanel content;
    private PanelBorderRadius right;
    private JButton add;
    private JButton cancel;
    private JLabel title;

    private TaiKhoanAction tkAction;
    private mainTransition ts = new mainTransition();

    public TaiKhoanDialog(String tkType, TaiKhoan tk,TaiKhoanEntity tkEntity) {
        this.tk = tk;
        this.tkType = tkType;
        this.tkEntity = tkEntity;
        tkAction = new TaiKhoanAction(tk,this);

        this.setTitle(setType());
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.getContentPane().setBackground(Color.decode("#dff0f5"));
//        this.setBackground(new Color(255, 255, 255));

        init();
        getEdit(tkType);
        ts.showSlideIn(this, 900, 600);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                ts.closeSlideOut(TaiKhoanDialog.this);
            }
        });
    }

    private void init() {
        content = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        content.setPreferredSize(new Dimension(900, 540));
        content.setBackground(Color.decode("#dff0f5"));

        right = new PanelBorderRadius();
        right.setBackgroundColor(new Color(52, 152, 219));
        right.setPreferredSize(new Dimension(400, 480));

        header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        title = new JLabel();
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(600, 50));
        header.add(title);
        header.setOpaque(true);
        header.setBackground(new Color(52, 152, 219));
        header.setPreferredSize(new Dimension(900, 60));

        center = new PanelBorderRadius();
        center.setPreferredSize(new Dimension(460, 480));
        center.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        center.setBorder(new EmptyBorder(20, 20, 20, 20));
        center.setBackground(new Color(240, 240, 240));

        add = new ButtonEditStyle(typeButton(getTKType()), new Color(52, 152, 219), Color.WHITE,420,55);
        add.setBorder(new EmptyBorder(15, 15, 15, 15));
        cancel = new ButtonEditStyle("Thoát", new Color(231, 76, 60), Color.WHITE,420,55);
        cancel.setBorder(new EmptyBorder(15, 15, 15, 15));

        content.add(right);
        content.add(center);

        this.add(header);
        this.add(content);

        // Gắn action
        add.addActionListener(tkAction);
//        cancel.addActionListener(tkAction);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }


    private String typeButton(String tkType) {
        switch (tkType) {
            case "add":
                return "Thêm";
            case "update":
                return "Cập nhật";
            case "detail":
                return "----";
            default:
                return "";
        }
    }

    private String setType() {
        if (tkType != null) {
            switch (tkType.toLowerCase()) {
                case "add":
                    return "Thêm tài khoản";
                case "update":
                    return "Cập nhật tài khoản";
                case "detail":
                    return "Chi tiết tài khoản";
            }
        }
        return "Tài khoản";
    }

    private void getEdit(String typeTK) {
        switch (tkType.toLowerCase()) {
            case "add":
                initAdd();
                break;
            case "update":
                initUpdate();
                break;
            case "detail":
                initDetail();
                break;
        }
    }

    private void initAdd() {
        title.setText("TẠO TÀI KHOẢN");
        tendangnhap = new labelText("Tên đăng nhập", 30, 5);
        tendangnhap.setOpaque(true);
        tendangnhap.setBackground(new Color(255, 255, 255));
        tendangnhap.setPreferredSize(new Dimension(420, 70));
        matkhau = new labelText("Mật khẩu", 30, 5);
        matkhau.setOpaque(true);
        matkhau.setBackground(new Color(255, 255, 255));
        matkhau.setPreferredSize(new Dimension(420, 70));
        manhanvien = new labelText("Mã nhân viên", (ArrayList<String>) TaiKhoanBUS.laydanhsachnv());
        manhanvien.setOpaque(true);
        manhanvien.setBackground(new Color(255, 255, 255));
        manhanvien.setPreferredSize(new Dimension(420, 70));
        quyen = new labelText("Quyền tài khoản", (ArrayList<String>) PhanQuyenBUS.getTenNhomQuyen());
        quyen.setOpaque(true);
        quyen.setBackground(new Color(255, 255, 255));
        quyen.setPreferredSize(new Dimension(420, 70));

        center.add(tendangnhap);
        center.add(matkhau);
        center.add(manhanvien);
        center.add(quyen);
        center.add(add);
        center.add(cancel);
    }

    private void initUpdate() {
        title.setText("CHỈNH SỬA TÀI KHOẢN");
        matkhau = new labelText("Mật khẩu", 30, 5);

        quyen = new labelText("Quyền", (ArrayList<String>) NhomQuyenDAO.getDanhsachtennhomquyen());
        trangthai = new labelText("Trạng thái", (ArrayList<String>) TaiKhoanBUS.laytrangthai());

        center.add(matkhau);
        center.add(quyen);
        center.add(trangthai);
        center.add(add);
        center.add(cancel);


        if (tkEntity != null) {
            matkhau.getTextField().setText(tkEntity.getMatKhau());
            quyen.getCbx().setSelectedItem(TaiKhoanBUS.getTenQuyen(tkEntity.getMaNguoiDung()));
            trangthai.getCbx().setSelectedItem(tkEntity.getTrangThai());
        }
    }

    private void initDetail() {
        title.setText("CHI TIẾT TÀI KHOẢN");
        tendangnhap = new labelText("Tên đăng nhập", 30, 5);
        labelText tenNguoiDung = new labelText("Tên người dùng", 30, 5);
        labelText loaiTaiKhoan = new labelText("Loại tài khoản", 30, 5);
        quyen = new labelText("Quyền", (ArrayList<String>) NhomQuyenDAO.getDanhsachtennhomquyen());
        trangthai = new labelText("Trạng thái", (ArrayList<String>) TaiKhoanBUS.laytrangthai());

        tendangnhap.getTextField().setEnabled(false);
        tenNguoiDung.getTextField().setEnabled(false);
        loaiTaiKhoan.getTextField().setEnabled(false);
        quyen.getCbx().setEnabled(false);
        trangthai.getCbx().setEnabled(false);

        if (tkEntity != null) {
            tendangnhap.getTextField().setText(tkEntity.getTenDangNhap());
            tenNguoiDung.getTextField().setText(TaiKhoanBUS.getTenNguoiDung(tkEntity.getMaNguoiDung()));
            loaiTaiKhoan.getTextField().setText(TaiKhoanBUS.getLoaiNguoiDung(tkEntity.getMaNguoiDung()));
            quyen.getCbx().setSelectedItem(TaiKhoanBUS.getTenQuyen(tkEntity.getMaNguoiDung()));
            trangthai.getCbx().setSelectedItem((tkEntity.getTrangThai()));
        }

        center.add(tenNguoiDung);
        center.add(tendangnhap);
        center.add(loaiTaiKhoan);
        center.add(quyen);
        center.add(trangthai);
    }
    private String getTKType() {
        return this.tkType;
    }

    public String getTkType() {
        return tkType;
    }

    public void setTkType(String tkType) {
        this.tkType = tkType;
    }

    public JPanel getCenter() {
        return center;
    }

    public void setCenter(PanelBorderRadius center) {
        this.center = center;
    }

    public labelText getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(labelText matkhau) {
        this.matkhau = matkhau;
    }

    public labelText getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(labelText tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public labelText getQuyen() {
        return quyen;
    }

    public void setQuyen(labelText quyen) {
        this.quyen = quyen;
    }

    public labelText getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(labelText trangthai) {
        this.trangthai = trangthai;
    }

    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton add) {
        this.add = add;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

    public TaiKhoan getTk() {
        return tk;
    }

    public void setTk(TaiKhoan tk) {
        this.tk = tk;
    }

    public TaiKhoanEntity getTkEntity() {
        return tkEntity;
    }

    public void setTkEntity(TaiKhoanEntity tkEntity) {
        this.tkEntity = tkEntity;
    }

    public labelText getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(labelText manhanvien) {
        this.manhanvien = manhanvien;
    }
}
