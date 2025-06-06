package org.projects.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import com.formdev.flatlaf.extras.FlatSVGIcon;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.projects.Action.MainAction;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.Panel.ThongkePack.ThongKe;
import org.projects.GUI.Panel.TrangChu;
import org.projects.GUI.Panel.WestTaskBar;
import org.projects.GUI.utils.UIUtils;

public class MainGUI extends JFrame {
    private ThongKe thongKe;
    
    private JPanel title;
    private WestTaskBar MenuTask;
    private JPanel contentItem;
    private JButton minusIcon;
    private JButton cancelIcon;
    private JLabel IconSuperMarketLabel;
    private JLabel textTitle;
    private JPanel contentPanel;
    private CardLayout card;
    
    public MainGUI() {
        // this.setTitle("MAIN GUI");
        this.setSize(1210,820);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.decode("#CAECF7"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.init();
        this.setVisible(true);
    }
    private void init() {
        this.setLayout(new BorderLayout(0,0));
        title = new JPanel();
        title.setPreferredSize(new Dimension(100,40));
        title.setLayout(null);
        title.setBackground(LoginGUI.MainColor);

        //action MainBUS
        MainAction action = new MainAction(this);

        //minus,cancel icon
        minusIcon = handleComponents.createButtonIcon("icon/minus-sign.svg", 20, 20);
        minusIcon.setBounds(1120,7,30,30);
        minusIcon.setForeground(Color.WHITE);
        minusIcon.addMouseListener(action);
        title.add(minusIcon);
        cancelIcon = handleComponents.createButtonIcon("icon/close.svg", 20, 20);
        cancelIcon.setBounds(1160, 7, 30, 30);
        minusIcon.setForeground(Color.WHITE);
        cancelIcon.addMouseListener(action);
        title.add(cancelIcon);

        //icon mini market
        FlatSVGIcon iconSuperMarket = new FlatSVGIcon("icon/marketplace.svg", 20, 20);
        IconSuperMarketLabel = new JLabel(iconSuperMarket);
        IconSuperMarketLabel.setBounds(20,5,30,30);
        title.add(IconSuperMarketLabel);

        textTitle = new JLabel("Siêu thị Mini");
        textTitle.setForeground(Color.BLACK);
        textTitle.setFont(new Font("JetBrains Mono",Font.PLAIN,13));
        textTitle.setBounds(55,5,200,30);
        title.add(textTitle);

        //menu taskbar west
        card = new CardLayout();
        contentPanel = new JPanel(card);
        MenuTask = new WestTaskBar(this);
        MenuTask.setPreferredSize(new Dimension(250,800));


        this.add(title,BorderLayout.NORTH);
        this.add(MenuTask,BorderLayout.WEST);
        this.add(contentPanel,BorderLayout.CENTER);
        UIUtils.refreshMain(this);
    }


    public void addPanelContent(JPanel panel) {
        String namePanel = panel.getClass().getSimpleName();
        contentPanel.add(panel,namePanel);
        card.show(contentPanel, namePanel);
        UIUtils.refreshComponent(contentPanel);
    }
    
    public WestTaskBar getMenuTask() {
        return MenuTask;
    }
    public JPanel getContentItem() {
        return contentItem;
    }
    public JButton getMinusIcon() {
        return minusIcon;
    }
    public JButton getCancelIcon() {
        return cancelIcon;
    }

    public static void main(String[] args) {
        new MainGUI();
    }

}
