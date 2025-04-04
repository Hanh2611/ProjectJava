package org.projects.GUI.DiaLog;

import org.projects.GUI.Components.labelText;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.entity.NhaCungCapEntity;
import org.projects.GUI.Components.*;

import javax.swing.*;
import java.awt.*;

public class NhaCungCapDialog extends JDialog {
    private NhaCungCap ncc;
//    private NhaCungCapEntity nccEntity;
    private String nccType; // them , xoa, sua , chi tiet
    private JPanel tenNCC;
    private JPanel sodienthoaiNCC;
    private JPanel emailNCC;
    private JPanel diachiNCC;
    private JButton chucnangBTN;
    private JButton thoatBTN;

    private JPanel centerPanel,bottomPanel;
    public NhaCungCapDialog(String nccType,NhaCungCap ncc) {
        this.ncc = ncc;
        this.nccType = nccType;
        this.setTitle(this.setType());
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        bottomPanel = new JPanel(new FlowLayout(0,5,5));
        this.init();
        this.setVisible(true);
    }
    public void init() {
        centerPanel = new JPanel(new GridLayout(4,1));
        tenNCC = new labelText("tên nhà cung cấp ",30,10);
        sodienthoaiNCC = new labelText("số điện thoại ",30,10);
        emailNCC = new labelText("email",30,10);
        diachiNCC = new labelText("địa chỉ ",30,10);
        centerPanel.add(tenNCC);
        centerPanel.add(sodienthoaiNCC);
        centerPanel.add(emailNCC);
        centerPanel.add(diachiNCC);

        chucnangBTN = typeButton(getNccType());
        thoatBTN = new JButton("Thoát");
        bottomPanel.add(chucnangBTN);
        bottomPanel.add(thoatBTN);

        this.add(centerPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
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
    public String getNccType() {
        return nccType;
    }
}
