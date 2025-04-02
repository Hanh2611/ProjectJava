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
//    private generalFunction generalFunc;
    public NhaCungCapBUS(NhaCungCap ncc) {
        this.ncc = ncc;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel c = (JPanel) e.getSource();
        if(ncc != null) {
            for(String tb : ncc.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = ncc.getHeader().getHeaderFunc().getHm().get(tb);
                if(c == gf) {
                    new NhaCungCapDialog(tb,ncc);
                }
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
