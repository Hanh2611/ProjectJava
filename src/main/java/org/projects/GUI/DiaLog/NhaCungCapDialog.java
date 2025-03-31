package org.projects.GUI.DiaLog;

import org.projects.GUI.Panel.NhaCungCap;
import org.projects.entity.NhaCungCapEntity;

import javax.swing.*;
import java.awt.*;

public class NhaCungCapDialog extends JDialog {
    private NhaCungCap ncc;
//    private NhaCungCapEntity nccEntity;
    private String type; // them , xoa, sua , chi tiet
    private JLabel maNCC;
    private JLabel tenNCC;
    private JLabel sodienthoaiNCC;
    private JLabel emailNCC;
    private JLabel diachiNCC;
    private JTextField maNCCField;
    private JTextField tenNCCField;
    private JTextField sodienthoaiNCCField;
    private JTextField emailNCCField;
    private JTextField diachiNCCField;
    private JButton chucnangBTN;
    private JButton thoatBTN;
    public NhaCungCapDialog(String type,NhaCungCap ncc) {
        this.ncc = ncc;
        this.type = type;
        this.setTitle(this.setType());
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(6,2));
        this.init();
        this.setVisible(true);
    }
    public void init() {
        maNCC = new JLabel("mã nhà cung cấp: ");
        maNCCField = new JTextField(10);
        this.add(maNCC);
        this.add(maNCCField);

        tenNCC = new JLabel("tên nhà cung cấp: ");
        tenNCCField = new JTextField(10);
        this.add(tenNCC);
        this.add(tenNCCField);

        sodienthoaiNCC = new JLabel("số điện thoại: ");
        sodienthoaiNCCField = new JTextField(10);
        this.add(sodienthoaiNCC);
        this.add(sodienthoaiNCCField);

        emailNCC = new JLabel("email: ");
        emailNCCField = new JTextField(10);
        this.add(emailNCC);
        this.add(emailNCCField);

        diachiNCC = new JLabel("diachi: ");
        diachiNCCField = new JTextField(10);
        this.add(diachiNCC);
        this.add(diachiNCCField);

        chucnangBTN = new JButton("Chucnang");
        this.add(chucnangBTN);
        thoatBTN = new JButton("Thoat");
        this.add(thoatBTN);

    }
    public String setType() {
        if(type != null) {
            switch (type.toLowerCase()) {
                case "add":
                    return "Thêm nhà cung cấp";
                case "update":
                    return "Cập nhật Thông tin nhà cung cấp";
                case "detail":
                    return "Thông tin nhà cung cấp";
                default:
                    break;
            }
        }
        return null;
    }

}
