package org.projects.Action;

import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.DiaLog.NhaCungCapDialog;
import org.projects.GUI.Panel.NhaCungCap;

import java.awt.event.*;

public class NhaCungCapAction implements ActionListener, MouseListener {
    private NhaCungCap ncc;
    private NhaCungCapDialog nccDialog;
    public NhaCungCapAction(NhaCungCap ncc, NhaCungCapDialog nccDialog) {
        this.ncc = ncc;
        this.nccDialog = nccDialog;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        generalFunction c = (generalFunction) e.getSource();
        System.out.println(c);
        if(ncc != null) {
            for(String name : ncc.getHeader().getHeaderFunc().getHm().keySet()) {
                generalFunction gf = ncc.getHeader().getHeaderFunc().getHm().get(name);
                if(c.equals(gf)) {
                    if(name == null && name.trim().isEmpty()) return;
                    System.out.println("ten cua nut la : " + name);
                    if("add".equals(name)) {
                        new NhaCungCapDialog(name,ncc);
                    }
                }
            }
        }
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
