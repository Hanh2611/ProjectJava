package org.projects.GUI.DiaLog;

import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.projects.BUS.HoaDonBUS;
import org.projects.DAO.ChiTietHoaDonFullDAO;
import org.projects.DAO.HoaDonDAO;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.Components.ButtonEditStyle;
import org.projects.GUI.Components.PanelBorderRadius;
import org.projects.GUI.Components.handleComponents;
import org.projects.GUI.DiaLog.PhanQuyen.objectFactory;
import org.projects.GUI.Panel.HoaDon;
import org.projects.GUI.utils.VotePDF;
import org.projects.entity.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ThanhToan extends JDialog {
    private JPanel titleBar, contentPanel, inputPanel;
    private HoaDon hoaDonPanel;
    private JButton backButton;
    private HoaDonEntity hoaDon;
    private JLabel errorLabel;
    private JTextField incomeTextField;
    private boolean flag = false;
    JLabel refundLabel;
    public ThanhToan(JFrame parent, HoaDonEntity hoaDon, HoaDon hoaDonPanel) {
        super(parent, "Thanh Toán", true);
        this.hoaDon = hoaDon;
        this.hoaDonPanel = hoaDonPanel;
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
        JButton cashMainButton = new ButtonEditStyle("TIỀN MẶT", new Color(255, 255, 255), new Color(52, 152, 219), 300, 410);
        cashMainButton.setPreferredSize(new Dimension(300, 410));
        cashMainButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cashMainButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        cashMainButton.setFont(new Font("Jetbrains Mono", Font.BOLD, 16));
        cashMainButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                initCashFrame();
            }
        });
        JButton qrMainButton = new ButtonEditStyle("CHUYỂN KHOẢN", new Color(255, 255, 255), new Color(52, 152, 219), 300, 410);
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
//        JLabel totalLabel = new JLabel("Tổng: " + hoaDon);
        JLabel totalLabel = new JLabel("Tổng: " + hoaDon.getTongGiaTri() + "VNĐ");
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
        incomeTextField = new JTextField();
        incomeTextField.setBackground(new Color(52, 152, 219));
        incomeTextField.setForeground(new Color(255, 255, 255));
        incomeTextField.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        incomeTextField.setPreferredSize(new Dimension(200, 40));
        incomeTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        checkIncome(incomeTextField);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.decode("#f70000"));
        errorLabel.setFont(new Font("Jetbrains Mono", Font.PLAIN, 12));
        errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        errorLabel.setVerticalAlignment(SwingConstants.TOP);
        errorLabel.setPreferredSize(new Dimension(200, 20));

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
        total.add(errorLabel);
        errorLabel.setVisible(false);
        refundLabel = new JLabel("Số tiền thừa: ");
        refundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        refundLabel.setForeground(new Color(255, 255, 255));
        refundLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        refundLabel.setPreferredSize(new Dimension(270, 100));
        refundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        refundLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        total.add(refundLabel);
        JButton buttonEditStyle = new ButtonEditStyle("HOÀN TẤT!", Color.decode("#09ed6c"), new Color(255, 255, 255), 100, 100);
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
//        JLabel totalLabel = new JLabel("Tổng: ");
        JLabel totalLabel = new JLabel("Tổng: " + hoaDon.getTongGiaTri() + "VNĐ");
        totalLabel.setForeground(new Color(255, 255, 255));
        totalLabel.setPreferredSize(new Dimension(270, 100));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        total.add(totalLabel);
        JLabel qr = createQr();
        qr.setPreferredSize(new Dimension(270, 270));
        total.add(qr);
        JButton buttonEditStyle = new ButtonEditStyle("HOÀN TẤT!", Color.decode("#09ed6c"), new Color(255, 255, 255), 100, 100);
        buttonEditStyle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                checkQrPayemnt();
            }
        });
        buttonEditStyle.setFont(new Font("Jetbrains Mono", Font.BOLD, 18));
        control.add(total, c);
        c.gridy = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        control.add(buttonEditStyle, c);


        contentPanel.add(billDetail);
        contentPanel.add(control);
    }

    public void checkIncome(JTextField incomeTextField) {
        incomeTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                check();
            }
            public void removeUpdate(DocumentEvent e) {
                check();
            }
            public void changedUpdate(DocumentEvent e) {
                check();
            }

            private void check() {
                if (incomeTextField.getText().isEmpty() || incomeTextField.getText().length() > 10 || Integer.parseInt(incomeTextField.getText()) < hoaDon.getTongGiaTri()) {
                    incomeTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#f70000")));
                    if(incomeTextField.getText().length() > 10) {
                        errorLabel.setText("Số quá lớn");
                    } else if (incomeTextField.getText().isEmpty() || Integer.parseInt(incomeTextField.getText()) < hoaDon.getTongGiaTri()) {
                        errorLabel.setText("Số tiền nhận không đủ");
                    }
                    errorLabel.setVisible(true);
                    flag = false;
                } else {
                    incomeTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
                    errorLabel.setVisible(false);
                    refundLabel.setText("Số tiền thừa: " + (Integer.parseInt(incomeTextField.getText()) - hoaDon.getTongGiaTri()) + "VNĐ");
                    flag = true;
                }
            }
        });
    }

    public void doneAction() {
        if (!flag || incomeTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền nhận hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            incomeTextField.grabFocus();
            incomeTextField.selectAll();
            return;
        }

        try {
            double tienKhachTra = Double.parseDouble(incomeTextField.getText());
            if (tienKhachTra < hoaDon.getTongGiaTri()) {
                errorLabel.setText("Số tiền nhận không đủ");
                errorLabel.setVisible(true);
                incomeTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#f70000")));
                return;
            }
            HoaDonEntity hdEntity = new HoaDonDAO().getHoaDonById(hoaDon.getMaHoaDon());
            List<ChiTietHoaDonFullEntity> lst = new ChiTietHoaDonFullDAO().showList(hoaDon.getMaHoaDon());

            // Gọi phương thức in hóa đơn
            VotePDF.taoHoaDon(hdEntity, lst, tienKhachTra);

            // Cập nhật trạng thái hóa đơn (nếu cần)
            HoaDonBUS.payment(hoaDon); // Giả sử phương thức này cập nhật trạng thái thanh toán

            // Đóng dialog
            this.dispose();
            hoaDonPanel.showTrangChinh();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số tiền nhận không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xử lý thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean checkQrPayemnt() {
        try {
            URL url = new URL("https://script.google.com/macros/s/AKfycbx8yH1SkEYBP6p068VAvkWZdin5vX_BAe_EzaLxgXUoFZQF3HvCdPwhyYP6CYRAgZ5R/exec");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            System.out.println("JSON từ Apps Script:");
            System.out.println(response.toString());

            JSONObject obj = new JSONObject(response.toString());
            JSONArray data = obj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject gd = data.getJSONObject(i);
                String moTa = gd.getString("Mô tả");
                int giaTri = gd.getInt("Giá trị");

                System.out.println("Mô tả: " + moTa);
                System.out.println("Giá trị: " + giaTri);
                if (giaTri == hoaDon.getTongGiaTri() && moTa.contains("Thanhtoanhoadon" + hoaDon.getMaHoaDon())) {
                    HoaDonBUS.payment(hoaDon);
                    this.dispose();
                    hoaDonPanel.showTrangChinh();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Thanh toán chưa thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public JLabel createQr() {
        String bankCode = "970422";
        String stk = "0847979732";
        double amountDouble = hoaDon.getTongGiaTri();
        int amount = (int) amountDouble;
        String content = "Thanhtoanhoadon" + hoaDon.getMaHoaDon();
//        String content = "Thanh toan hoa don " + Integer.toString(hoaDon.getMaHoaDon());

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
}

