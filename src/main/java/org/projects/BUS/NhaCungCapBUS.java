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
    private generalFunction generalFunc;
    public NhaCungCapBUS(NhaCungCap ncc) {
        this.ncc = ncc;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        for(Map.Entry<String, generalFunction> hm : ncc.getHeader().getHeaderFunc().getHm().entrySet()) {
            if(c.equals(hm.getValue())) {
                System.out.println("Mouse Clicked" + hm.getKey());
                new NhaCungCapDialog(hm.getKey(),ncc);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

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
