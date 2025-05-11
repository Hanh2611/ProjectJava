    package org.projects.GUI.Panel.ThongkePack;

    import org.projects.GUI.Components.PanelBorderRadius;

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;

    import javax.swing.*;

    public class ThongKe extends JPanel{
        //header: title + list button
        private JPanel headerPanel;
        private JLabel headerTitle;
        private JToolBar headerToolbar; // chứa list các nút
        private ButtonThongke btn;
        private List<ButtonThongke> listbtn;
        //center:panel thong ke
        private JPanel centerPanel;
        private thongkeTongquan tktq;
        private thongkeSanpham tksp;
        private thongkeHoadon tkhd;
        private thongkePhieunhap tkpn;
        private thongkeDoanhthu tkdt;
        private HashMap<ButtonThongke,JPanel> listthongke;
        public ThongKe() {
            listbtn = new ArrayList<>();
            listthongke = new HashMap<>();
            this.setLayout(new BorderLayout(10,10));
            this.setBackground(new Color(240, 240, 240));
            this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.init();
            hienthiPanel(listbtn.get(0));
            changeColorButton(listbtn.get(0));
        }
        private void init() {
            JPanel headerPanel = new PanelBorderRadius();
            headerPanel.setLayout(new BorderLayout());
            headerPanel.setBackground(Color.decode("#FFFFFF"));
            headerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            headerTitle = new JLabel("THỐNG KÊ SIÊU THỊ MINI", SwingConstants.CENTER);
            headerTitle.setFont(new Font("Jetbrains Mono", Font.BOLD, 22));
            headerTitle.setForeground(Color.decode("#2d3436"));
            headerTitle.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
            headerPanel.add(headerTitle, BorderLayout.NORTH);

            headerToolbar = new JToolBar();
            headerToolbar.setRollover(true);
            headerToolbar.setFloatable(false);
//            headerToolbar.setBackground(Color.decode("#f1f2f6"));
            String[] listcn = new String[]{"Tổng quan","Sản phẩm", "Hóa đơn", "Phiếu nhập", "Doanh thu"};
            tktq = new thongkeTongquan();
            tksp = new thongkeSanpham();
            tkhd = new thongkeHoadon();
            tkpn = new thongkePhieunhap();
            tkdt = new thongkeDoanhthu();
            JPanel[] listpanel = {tktq,tksp,tkhd,tkpn,tkdt};
            for (int i = 0; i < listcn.length; i++) {
                btn = new ButtonThongke(listcn[i]);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ButtonThongke bttk = (ButtonThongke) e.getSource();
                        changeColorButton(bttk);
                        hienthiPanel(bttk);
                    }
                });
                btn.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));
                listbtn.add(btn);
                listthongke.put(btn,listpanel[i]);
            }
            for(ButtonThongke it : listbtn) {
                headerToolbar.add(it);
            }
            headerPanel.add(headerToolbar, BorderLayout.SOUTH);

            centerPanel = new JPanel(new BorderLayout());
            centerPanel.setPreferredSize(new Dimension(940, 1000));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            this.add(headerPanel, BorderLayout.NORTH);
            this.add(centerPanel, BorderLayout.CENTER);
        }

        //thay doi mau nen + chu cho button
        public void changeColorButton(ButtonThongke bttk) {
            for(ButtonThongke it : listbtn) {
                if(bttk == it) {
                    it.setForeground(Color.decode("#f5f6fa"));
                    it.setBackground(Color.decode("#34495e"));
                } else {
                    it.setForeground(Color.decode("#485460"));
                    it.setBackground(null);
                }
            }
        }

        //hien thi panel thống kê theo từng mục
        public void hienthiPanel(ButtonThongke bttk) {
            JPanel pannelThongke = listthongke.get(bttk);
            if(pannelThongke != null) {
                centerPanel.removeAll();
                centerPanel.add(pannelThongke, BorderLayout.CENTER);
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        }



        public JPanel getHeaderPanel() {
            return headerPanel;
        }

        public JLabel getHeaderTitle() {
            return headerTitle;
        }

        public JToolBar getHeaderToolbar() {
            return headerToolbar;
        }

        public ButtonThongke getBtn() {
            return btn;
        }

        public thongkeTongquan getTktq() {
            return tktq;
        }

        public thongkeSanpham getTksp() {
            return tksp;
        }

        public thongkeHoadon getTkhd() {
            return tkhd;
        }

        public thongkePhieunhap getTkpn() {
            return tkpn;
        }

        public thongkeDoanhthu getTkkh() {
            return tkdt;
        }

        public HashMap<ButtonThongke, JPanel> getListthongke() {
            return listthongke;
        }

    }
