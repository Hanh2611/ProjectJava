package org.projects.GUI.DiaLog;

import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.PanelBorderRadius;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.DiaLog.PhanQuyen.objectFactory;
import org.projects.entity.DanhMucQuanLy;
import org.projects.entity.HoaDonEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class ThanhToan extends JDialog {
    private JPanel titleBar, contentPanel, inputPanel;
    private JButton backButton;
    private HoaDonEntity hoaDon;
    ThanhToan(JFrame parent, HoaDonEntity hoaDon) {
        super(parent, "Thanh Toán", true);
        this.hoaDon = hoaDon;
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setSize(900, 700);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        titleBar = new JPanel();
        objectFactory.titleBar(titleBar, this, "");
        backButton = handleComponents.createButtonIcon("icon/back.svg", 30, 30);
        backButton.setBounds(5, 0, 40, 40);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                initMainFrame();
            }
        });
        this.add(titleBar);
        contentPanel = new JPanel();
        contentPanel.setOpaque(true);
        contentPanel.setBackground(Color.decode("#dff0f5"));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        contentPanel.setPreferredSize(new Dimension(900, 660));
        this.add(contentPanel);
        initMainFrame();
        setVisible(true);
    }

    public void initMainFrame() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
        if (Arrays.asList(titleBar.getComponents()).contains(backButton)) {
            titleBar.remove(backButton);
            titleBar.revalidate();
            titleBar.repaint();
        }
        JButton cashMainButton = ButtonEditStyle.styleButton("TIỀN MẶT", new Color(255, 255, 255), new Color(52, 152, 219));
        cashMainButton.setPreferredSize(new Dimension(300, 410));
        cashMainButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cashMainButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        cashMainButton.setFont(new Font("Jetbrains Mono", Font.BOLD, 16));
        cashMainButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                initCashFrame();
            }
        });
        JButton qrMainButton = ButtonEditStyle.styleButton("CHUYỂN KHOẢN", new Color(255, 255, 255), new Color(52, 152, 219));
        qrMainButton.setPreferredSize(new Dimension(300, 410));
        qrMainButton.setHorizontalTextPosition(SwingConstants.CENTER);
        qrMainButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        qrMainButton.setFont(new Font("Jetbrains Mono", Font.BOLD, 16));
        qrMainButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                initBankFrame();
            }
        });
        ImageIcon iconCash = new ImageIcon(new ImageIcon("src/main/resources/Img/ThanhToan/cash-payment.png").getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH));
        ImageIcon iconQr = new ImageIcon(new ImageIcon("src/main/resources/Img/ThanhToan/qr-payment.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        cashMainButton.setIcon(iconCash);
        qrMainButton.setIcon(iconQr);
        contentPanel.add(cashMainButton);
        contentPanel.add(qrMainButton);

        PanelBorderRadius directionBill = new PanelBorderRadius();
        directionBill.setBackground(Color.WHITE);
        directionBill.setPreferredSize(new Dimension(700, 100));
        contentPanel.add(directionBill);
    }

    public void initCashFrame() {
        if (!Arrays.asList(titleBar.getComponents()).contains(backButton)) {
            titleBar.add(backButton);
        }
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        PanelBorderRadius billDetail  = new PanelBorderRadius();
        billDetail.setPreferredSize(new Dimension(600, 640));

        // Control zone
        PanelBorderRadius control = new PanelBorderRadius();
        control.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 2;
        c.insets = new Insets(0, 0, 10, 0);
        c.fill = GridBagConstraints.BOTH;
        control.setPreferredSize(new Dimension(270, 640));
        control.setBackgroundColor(Color.decode("#dff0f5"));
        PanelBorderRadius total = new PanelBorderRadius();
        total.setOpaque(true);
        total.setBackgroundColor(new Color(52, 152, 219));
        total.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel totalLabel = new JLabel("Tổng: ");
//        JLabel totalLabel = new JLabel("Tổng: " + hoaDon.getTongGiaTri() + "VNĐ");
        totalLabel.setForeground(new Color(255, 255, 255));
        totalLabel.setPreferredSize(new Dimension(270, 100));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        total.add(totalLabel);
        JLabel incomeLabel = new JLabel("Số tiền nhận: ");
        incomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        incomeLabel.setForeground(new Color(255, 255, 255));
        incomeLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        incomeLabel.setPreferredSize(new Dimension(270, 50));
        total.add(incomeLabel);
        JTextField incomeTextField = new JTextField();
        incomeTextField.setBackground(new Color(52, 152, 219));
        incomeTextField.setForeground(new Color(255, 255, 255));
        incomeTextField.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        incomeTextField.setPreferredSize(new Dimension(200, 40));
        incomeTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        ((AbstractDocument) incomeTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) { // Chỉ cho số
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        total.add(incomeTextField);
        JLabel refundLabel = new JLabel("Số tiền thừa: ");
        refundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        refundLabel.setForeground(new Color(255, 255, 255));
        refundLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 24));
        refundLabel.setPreferredSize(new Dimension(270, 100));
        total.add(refundLabel);
        JButton buttonEditStyle = ButtonEditStyle.styleButton("HOÀN TẤT!", Color.decode("#09ed6c"), new Color(255, 255, 255));
        buttonEditStyle.setFont(new Font("Jetbrains Mono", Font.BOLD, 18));
        buttonEditStyle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                doneAction();
            }
        });
        control.add(total, c);
        c.gridy = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        control.add(buttonEditStyle, c);


        contentPanel.add(billDetail);
        contentPanel.add(control);
    }

    public void initBankFrame() {
        if (!Arrays.asList(titleBar.getComponents()).contains(backButton)) {
            titleBar.add(backButton);
        }
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        PanelBorderRadius billDetail  = new PanelBorderRadius();
        billDetail.setPreferredSize(new Dimension(600, 640));

        // Control zone
        PanelBorderRadius control = new PanelBorderRadius();
        control.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 2;
        c.insets = new Insets(0, 0, 10, 0);
        c.fill = GridBagConstraints.BOTH;
        control.setPreferredSize(new Dimension(270, 640));
        control.setBackgroundColor(Color.decode("#dff0f5"));
        PanelBorderRadius total = new PanelBorderRadius();
        total.setOpaque(true);
        total.setBackgroundColor(new Color(52, 152, 219));
        total.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel totalLabel = new JLabel("Tổng: ");
//        JLabel totalLabel = new JLabel("Tổng: " + hoaDon.getTongGiaTri() + "VNĐ");
        totalLabel.setForeground(new Color(255, 255, 255));
        totalLabel.setPreferredSize(new Dimension(270, 100));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        total.add(totalLabel);
        JLabel qr = createQr();
        qr.setPreferredSize(new Dimension(270, 270));
        total.add(qr);
        JButton buttonEditStyle = ButtonEditStyle.styleButton("HOÀN TẤT!", Color.decode("#09ed6c"), new Color(255, 255, 255));
        buttonEditStyle.setFont(new Font("Jetbrains Mono", Font.BOLD, 18));
        control.add(total, c);
        c.gridy = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        control.add(buttonEditStyle, c);


        contentPanel.add(billDetail);
        contentPanel.add(control);
    }

    public void checkIncome() {

    }

    public void doneAction() {
        this.dispose();
    }

    public void checkCashPayment() {

    }

    public void checkQrPayemnt() {

    }

    public JLabel createQr() {
        String bankCode = "970422";
        String stk = "0847979732";
        int amount = 1000000;
        String content = Integer.toString(100000);

        String qrURL = String.format(
                "https://img.vietqr.io/image/%s-%s-compact2.png?amount=%d&addInfo=%s",
                bankCode, stk, amount, content
        );

        try {
            BufferedImage image = ImageIO.read(new URL(qrURL));
            ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(270, 270, Image.SCALE_SMOOTH));

            JLabel qrLabel = new JLabel(icon);
            return qrLabel;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ThanhToan frame = new ThanhToan(null, null);
    }
}

