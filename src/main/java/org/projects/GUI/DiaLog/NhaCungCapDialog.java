package org.projects.GUI.DiaLog;

import org.projects.Action.NhaCungCapAction;
import org.projects.GUI.Components.Transition.mainTransition;
import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.entity.NhaCungCapEntity;
import org.projects.GUI.Components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private mainTransition ts = new mainTransition();

    private JPanel centerPanel,bottomPanel;
    public NhaCungCapDialog(String nccType,NhaCungCap ncc) {
        this.ncc = ncc;
        this.nccType = nccType;
        nccAction = new NhaCungCapAction(ncc, this);
        this.setTitle(this.setType());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(240, 240, 240));
        this.init();
        getEdit(this.getNccType());
        ts.showZoomIn(this,600,400);
    }
    public void init() {
        centerPanel = new JPanel(new GridLayout(4,1,10,10));
        centerPanel.setBorder(new EmptyBorder(20,20,20,20));
        centerPanel.setBackground(new Color(240, 240, 240));

        tenNCC = new labelText("tên nhà cung cấp ",30,5);
        sodienthoaiNCC = new labelText("số điện thoại ",30,5);
        emailNCC = new labelText("email",30,5);
        diachiNCC = new labelText("địa chỉ ",30,5);

        centerPanel.add(tenNCC);
        centerPanel.add(sodienthoaiNCC);
        centerPanel.add(emailNCC);
        centerPanel.add(diachiNCC);

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        bottomPanel.setBackground(new Color(240, 240, 240));

        chucnangBTN = new ButtonEditStyle(typeButton(getNccType()),new Color(52,152,219),Color.WHITE,100,30);
        thoatBTN = new ButtonEditStyle("Thoát",new Color(231,76,60),Color.WHITE,100,30);

        bottomPanel.add(chucnangBTN);
        bottomPanel.add(thoatBTN);

        this.add(centerPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);

        //add action
        chucnangBTN.addActionListener(nccAction);
        thoatBTN.addActionListener(nccAction);
    }

    public String  typeButton(String nccType) {
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
        return namebtn;
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
