package org.projects.BUS;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.Panel.NhaCungCap;
import org.projects.entity.NhaCungCapEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class NhaCungCapBUS implements ActionListener, MouseListener {
    private NhaCungCap ncc;
//    private NhaCungCapEntity nccEntity;
    private generalFunction generalFunc;
    public NhaCungCapBUS(NhaCungCap ncc) {
        this.ncc = ncc;
//        this.nccEntity = nccEntity;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        System.out.println("Mouse Clicked");
        for(Map.Entry<String, generalFunction> hm : ncc.getHeader().getHeaderFunc().getHm().entrySet()) {
            if(c.equals(hm.getValue())) {
                new NhaCungCapDialog(hm.getKey(),ncc).setVisible(true);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
