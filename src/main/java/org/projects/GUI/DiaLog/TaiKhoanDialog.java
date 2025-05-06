package org.projects.GUI.DiaLog;

import org.projects.Action.TaiKhoanAction;
import org.projects.BUS.TaiKhoanBUS;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.TaiKhoan;
import org.projects.entity.TaiKhoanEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TaiKhoanDialog extends JDialog {
    private TaiKhoan tk;
    private TaiKhoanEntity tkEntity;
    private String tkType;

    private JPanel center;
    private labelText tennguoidung;
    private labelText loainguoidung;
    private labelText tendangnhap;
    private labelText matkhau;
    private labelText quyen;
    private labelText trangthai;

    private JPanel bottom;
    private JButton add;
    private JButton cancel;

    private TaiKhoanAction tkAction;
    private mainTransition ts = new mainTransition();

    public TaiKhoanDialog(String tkType, TaiKhoan tk) {
        this.tk = tk;
        this.tkType = tkType;
        tkAction = new TaiKhoanAction(tk,this);
        this.setTitle(setType());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(240, 240, 240));
        this.tkAction = new TaiKhoanAction(tk, this);

        init();
        getEdit(getTKType());
        ts.showZoomIn(this, 600, 600);
    }

    private void init() {
        center = new JPanel(new GridLayout(6, 1, 10, 10));
        center.setBorder(new EmptyBorder(20, 20, 20, 20));
        center.setBackground(new Color(240, 240, 240));

        tennguoidung = new labelText("Tên người dùng", 30, 5);
        loainguoidung = new labelText("Loại người dùng", (ArrayList<String>) TaiKhoanBUS.getDanhsachLoainguoidung());
        tendangnhap = new labelText("Tên đăng nhập", 30, 5);
        matkhau = new labelText("Mật khẩu", 30, 5);
        quyen = new labelText("Quyền",(ArrayList<String>) TaiKhoanBUS.laytennhomquyen());
        trangthai = new labelText("Trạng thái", (ArrayList<String>) TaiKhoanBUS.laytrangthai());

        center.add(tennguoidung);
        center.add(loainguoidung);
        center.add(tendangnhap);
        center.add(matkhau);
        center.add(quyen);
        center.add(trangthai);

        bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottom.setBackground(new Color(240, 240, 240));

        add = ButtonEditStyle.styleButton(typeButton(getTKType()), new Color(52, 152, 219), Color.WHITE);
        cancel = ButtonEditStyle.styleButton("Thoát", new Color(231, 76, 60), Color.WHITE);

        bottom.add(add);
        bottom.add(cancel);

        this.add(center, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

        // Gắn action
        add.addActionListener(tkAction);
        cancel.addActionListener(tkAction);
    }

    private String typeButton(String tkType) {
        switch (tkType) {
            case "add":
                return "Thêm";
            case "update":
                return "Cập nhật";
            case "delete":
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

    private void getEdit(String tkType) {
        switch (tkType) {
            case "update":
                getEdit(true);
                break;
            case "detail":
                getEdit(false);
                break;
        }
    }

    public void getEdit(Boolean edit) {

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

    public void setCenter(JPanel center) {
        this.center = center;
    }

    public labelText getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(labelText tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public labelText getLoainguoidung() {
        return loainguoidung;
    }

    public void setLoainguoidung(labelText loainguoidung) {
        this.loainguoidung = loainguoidung;
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

    public JPanel getBottom() {
        return bottom;
    }

    public void setBottom(JPanel bottom) {
        this.bottom = bottom;
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
}
