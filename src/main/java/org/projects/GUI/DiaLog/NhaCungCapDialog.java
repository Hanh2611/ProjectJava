package org.projects.GUI.DiaLog;

import org.projects.Action.NhaCungCapAction;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.entity.NhaCungCapEntity;
import org.projects.GUI.Components.*;

import javax.swing.*;
import java.awt.*;

public class NhaCungCapDialog extends JDialog {
    private NhaCungCap ncc;
    private NhaCungCapEntity nccEntity;
    private String nccType; // them , xoa, sua , chi tiet
    private labelText tenNCC;
    private labelText sodienthoaiNCC;
    private labelText emailNCC;
    private labelText diachiNCC;
    private JButton chucnangBTN;
    private JButton thoatBTN;
    private NhaCungCapAction nccAction;

    private JPanel centerPanel,bottomPanel;
    public NhaCungCapDialog(String nccType,NhaCungCap ncc) {
        this.ncc = ncc;
        this.nccType = nccType;
        nccAction = new NhaCungCapAction(ncc, this);
        this.setTitle(this.setType());
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        bottomPanel = new JPanel(new FlowLayout(0,5,5));
        this.init();
        getEdit(this.getNccType());
        this.setVisible(true);
    }
    public void init() {
        centerPanel = new JPanel(new GridLayout(4,1));
        tenNCC = new labelText("tên nhà cung cấp ",30,5);
        sodienthoaiNCC = new labelText("số điện thoại ",30,5);
        emailNCC = new labelText("email",30,5);
        diachiNCC = new labelText("địa chỉ ",30,5);
        centerPanel.add(tenNCC);
        centerPanel.add(sodienthoaiNCC);
        centerPanel.add(emailNCC);
        centerPanel.add(diachiNCC);

        chucnangBTN = typeButton(getNccType());
        chucnangBTN.setForeground(Color.BLACK);
        chucnangBTN.setBackground(Color.decode("#7ed6df"));
        thoatBTN = new JButton("Thoát");
        thoatBTN.setForeground(Color.BLACK);
        thoatBTN.setBackground(Color.decode("#ff7979"));
        bottomPanel.add(chucnangBTN);
        bottomPanel.add(thoatBTN);

        this.add(centerPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);

        //add action
        chucnangBTN.addActionListener(nccAction);
        chucnangBTN.addMouseListener(nccAction);
        thoatBTN.addActionListener(nccAction);
        thoatBTN.addMouseListener(nccAction);
    }

    public JButton typeButton(String nccType) {
        String namebtn= "";
        switch (nccType) {
            case "add":
                namebtn = "Thêm";
                break;
            case "update":
                namebtn = "Cập nhật";
                break;
            case "delete","detail":
                namebtn = "----";
                break;
            default:
                break;
        }
        return new JButton(namebtn);
    }
    public String setType() {
        if(nccType != null) {
            switch (nccType.toLowerCase()) {
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
    public void getEdit(String nccType) {
        switch (nccType) {
            case "update":
                this.getEdit(true);
                break;
            case "detail":
                this.getEdit(false);
                break;
            default:
                break;
        }
    }

    public void getEdit(boolean edit) {
        nccEntity = ncc.getRow();
        if(nccEntity != null) {
            tenNCC.getTextField().setText(nccEntity.getTenNCC());
            sodienthoaiNCC.getTextField().setText(nccEntity.getSoDienThoaiNCC());
            emailNCC.getTextField().setText(nccEntity.getEmailNCC());
            diachiNCC.getTextField().setText(nccEntity.getDiaCHiNCC());


            tenNCC.getTextField().setEnabled(edit);
            sodienthoaiNCC.getTextField().setEnabled(edit);
            emailNCC.getTextField().setEnabled(edit);
            diachiNCC.getTextField().setEnabled(edit);
        }
    }
    public String getNccType() {
        return nccType;
    }

    public JButton getChucnangBTN() {
        return chucnangBTN;
    }
    public JButton getThoatBTN() {
        return thoatBTN;
    }

    public NhaCungCap getNcc() {
        return ncc;
    }

    public labelText getTenNCC() {
        return tenNCC;
    }

    public labelText getSodienthoaiNCC() {
        return sodienthoaiNCC;
    }

    public labelText getEmailNCC() {
        return emailNCC;
    }

    public labelText getDiachiNCC() {
        return diachiNCC;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }
}
