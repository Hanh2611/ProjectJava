package org.projects.Action;

import java.awt.Frame;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import org.projects.GUI.MainGUI;

public class MainAction implements MouseInputListener {
    private MainGUI mainFrame;
    public MainAction(MainGUI mainFrame) {
        this.mainFrame = mainFrame; 
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== mainFrame.getCancelIcon()) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát ? ", "Thông báo", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION) {
                mainFrame.dispose();
                System.exit(0); // tat run chuong trinh
            }
        }else if(e.getSource() == mainFrame.getMinusIcon()) {
            mainFrame.setExtendedState(Frame.ICONIFIED);
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

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
