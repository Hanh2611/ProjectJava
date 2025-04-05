package org.projects.GUI.Panel.NhanVienPack;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.projects.GUI.Components.handleComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.projects.GUI.Components.layoutCompoment.addHeader;

public class NhanVien extends JPanel {
    private JTable table;
    AddNhanVienConsole addNhanVienConsole;
    DeleteNhanVienConsole deleteNhanVienConsole;
    public NhanVien() {
        init();
        setupHeader();
        setupLayout();
        testButton();
        addNhanVienConsole = new AddNhanVienConsole();
        deleteNhanVienConsole = new DeleteNhanVienConsole();
    }

    private void init(){
        String [] col = {"Mã NV", "Tên nhân viên", "Email", "SĐT", "Chức vụ"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0){
            // không cho chính sửa trực tiếp trong bảng
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //test
        for(int i = 1; i <= 30; i++) {
            tableModel.addRow(new Object[]{
                    i,
                    "Nhân viên " + i,
                    "Địa chỉ chi tiết số " + i + ", Quận 1, TP.HCM",
                    "0123-456-789",
                    "2023-04-2" + (i % 10)
            });
        }

        table = new JTable(tableModel);
        CustomTable();
    }

    private void CustomTable(){
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(500);
        table.getColumnModel().getColumn(3).setPreferredWidth(220);
        table.getColumnModel().getColumn(4).setPreferredWidth(220);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(new Color(240, 240, 240));
        header.setFont(new Font("JETBRAINS MONO", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        table.setBackground(new Color(245, 245, 245));
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(204, 229, 255));
        table.setFont(new Font("JETBRAINS MONO", Font.PLAIN, 13));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setOpaque(true);
        // Căn giữa nội dung trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void setupHeader(){
        String[][] listItemHeader = {
                {"icon/add.svg", "Thêm", "Them"},
                {"icon/content-writing.svg", "Sửa", "Sua"},
                {"icon/trash.svg", "Xóa", "Xoa"},
                {"icon/details.svg", "Chi tiết", "ChiTiet"},
                {"icon/excel.svg", "Xuất excel", "Excel"}
        };
        addHeader(this , listItemHeader);
    }
    private void setupLayout() {
        setBackground(new Color(240, 240, 240));
        // Scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );
        scrollPane.setBorder(border);
        // Panel trung tâm
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centerPanel.setPreferredSize(new Dimension(940, 650));
        centerPanel.add(scrollPane , gbc);
        add(centerPanel);
    }
    // khi có quyền admin thì sẽ log ra lương , ngày làm việc , thưởng , ...
    public JPanel setupDetailBox_ADMIN(){
        JPanel detailBoxPanel = new JPanel();
        detailBoxPanel.setLayout(new BorderLayout());
        detailBoxPanel.setBackground(Color.RED);
        detailBoxPanel.setPreferredSize(new Dimension(600 , 650));
        JPanel topLayout = new JPanel();
        JPanel botLayout = new JPanel();
        topLayout.setBackground(Color.BLUE);
        botLayout.setBackground(Color.YELLOW);
        // xây dựng top layout
        topLayout.setLayout(new GridLayout(1 , 2));
        topLayout.setPreferredSize(new Dimension(600 , 325));
        JPanel topImage = new JPanel();
        JPanel topInformation = new JPanel();
        topImage.setBackground(Color.PINK);
        topInformation.setBackground(Color.RED);
        topImage.setOpaque(true);
        topInformation.setOpaque(true);
        topLayout.add(topImage);
        topLayout.add(topInformation);
        topLayout.setOpaque(true);


        botLayout.setOpaque(true);
        detailBoxPanel.add(topLayout, BorderLayout.NORTH);
        detailBoxPanel.add(botLayout, BorderLayout.CENTER);
        detailBoxPanel.setOpaque(true);

        return detailBoxPanel;
    }
    // Quyền bình thường chỉ xem được các thông tin của nhân viên , bị ẩn đi các mục quan trọng
    public JPanel setupDetailBox_USER() {
        JPanel detailBoxPanel = new JPanel(new GridBagLayout());
        detailBoxPanel.setOpaque(true);
        detailBoxPanel.setBackground(new Color(240, 240, 240));
        detailBoxPanel.setPreferredSize(new Dimension(700, 400));
        detailBoxPanel.setMaximumSize(new Dimension(700, 400));
        detailBoxPanel.setMinimumSize(new Dimension(700, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Panel bên trái chứa ảnh
        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(true);
        left.setBackground(new Color(240, 240, 240));
        left.setPreferredSize(new Dimension(250, 400));
        left.setMinimumSize(new Dimension(250, 400));
        left.setMaximumSize(new Dimension(250, 400));

        // Load ảnh và resize (nên đảm bảo ảnh có kích thước phù hợp)
        ImageIcon icon = new ImageIcon(new ImageIcon("D:\\Java\\ProjectJava\\src\\main\\resources\\Img\\avatar-anh-meo-cute-5.jpg")
                .getImage().getScaledInstance(250, 400, Image.SCALE_SMOOTH));
        JLabel imgLabel = new RoundedImageLabel(icon, 20, 20);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        left.add(imgLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1;
        detailBoxPanel.add(left, gbc);

        // Panel bên phải chứa thông tin chi tiết
        JPanel right = new JPanel(new BorderLayout());
        right.setOpaque(true);
        right.setBackground(new Color(240, 240, 240));
        right.setPreferredSize(new Dimension(420, 400));
        right.setMaximumSize(new Dimension(420, 400));
        right.setMinimumSize(new Dimension(420, 400));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(true);
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Danh sách các thông tin
        String[] info = {"Mã nhân viên", "Họ và tên", "Ngày sinh", "Tuổi", "Chức vụ", "Số điện thoại", "Email"};
        FlatSVGIcon iconIdNV = new FlatSVGIcon("icon/idNV.svg", 20, 20) ;
        FlatSVGIcon iconNameNV = new FlatSVGIcon("icon/nameNV.svg", 20, 20) ;
        FlatSVGIcon iconBrithdayNV = new FlatSVGIcon("icon/brithday.svg", 20, 20);
        FlatSVGIcon iconEmailNV = new FlatSVGIcon("icon/email.svg", 20, 20) ;
        FlatSVGIcon iconPhoneNV = new FlatSVGIcon("icon/phone.svg", 20, 20) ;
        FlatSVGIcon iconphucvuNV = new FlatSVGIcon("icon/phucvu.svg", 20, 20) ;
        FlatSVGIcon iconageNV = new FlatSVGIcon("icon/age.svg", 20, 20) ;
        FlatSVGIcon[] iconList = {iconIdNV, iconNameNV , iconBrithdayNV, iconageNV , iconphucvuNV , iconPhoneNV,iconEmailNV};
        int index = 0;
        for (String labelText : info) {
            JPanel pan = new JPanel(new FlowLayout(FlowLayout.LEFT , 5 , 0));
            pan.setBackground(new Color(240, 240, 240));
            JLabel iconLabel = new JLabel(iconList[index]);
            pan.add(iconLabel);
            JTextField field = new JTextField(labelText);
            field.setFont(new Font("JetBrains Mono", Font.ITALIC, 15));
            field.setHorizontalAlignment(SwingConstants.LEFT);
            field.setBackground(new Color(240, 240, 240));
            field.setEditable(false);
            field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220,220,220)));
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            pan.add(field);
            infoPanel.add(pan);
            infoPanel.add(Box.createVerticalStrut(10));
            index++;
            index %= iconList.length;
        }

        JPanel genderPanel = getRadioSex(false , true);
        infoPanel.add(genderPanel);

        right.add(infoPanel, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.weightx = 0.6;
        detailBoxPanel.add(right, gbc);

        return detailBoxPanel;
    }

    public static JPanel getRadioSex(boolean edit, boolean data) {
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setOpaque(true);
        genderPanel.setBackground(new Color(240, 240, 240));

        JRadioButton radioNam = new JRadioButton("Nam", data);
        data = !data;
        JRadioButton radioNu = new JRadioButton("Nữ", data);
        radioNam.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        radioNu.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        if(!edit) {
            radioNam.setEnabled(false);
            radioNu.setEnabled(false);
        }

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioNam);
        genderGroup.add(radioNu);

        genderPanel.add(radioNam);
        genderPanel.add(radioNu);
        return genderPanel;
    }


    public void testButton(){
        JButton btnDetail = new JButton("Chi tiết");
        btnDetail.addActionListener(e -> ShowDeleteNhanVienConsole());
        add(btnDetail , BorderLayout.NORTH);
    }

    public void ShowDetailBox_ADMIN(){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel detailPanel = setupDetailBox_USER();
        dialog.setUndecorated(true);
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        dialog.setIconImage(svgIcon.getImage());
        JLabel titleLabel = new JLabel("Chi tiết nhân viên" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);
        JButton cancelButton = add_cancelIcon(dialog);
        titleLabel.add(cancelButton);
        dialog.getContentPane().add(titleLabel, BorderLayout.NORTH);
        dialog.getContentPane().add(detailPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private JButton add_minusIcon(){
        JButton minusIcon;
        minusIcon = handleComponents.createButtonIcon("icon/minus-sign.svg", 15, 20);
        minusIcon.setBounds(470,0,30,30);
        minusIcon.setForeground(Color.WHITE);
        minusIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return minusIcon;
    }

    private JButton add_cancelIcon(JDialog dialog){
        JButton cancelIcon;
        cancelIcon = handleComponents.createButtonIcon("icon/close.svg", 20, 20);
        cancelIcon.setBounds(670,0,30,30);
        cancelIcon.setForeground(Color.WHITE);
        cancelIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        return cancelIcon;
    }


    public void ShowAddNhanVienConsole(){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setUndecorated(true);
        dialog.setSize(new Dimension(500, 700));
        dialog.setMaximumSize(new Dimension(500, 700));
        dialog.setMinimumSize(new Dimension(500, 700));
        JPanel detailPanel = addNhanVienConsole;
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        dialog.setIconImage(svgIcon.getImage());
        JLabel titleLabel = new JLabel("Thêm nhân viên" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);
        dialog.getContentPane().add(titleLabel, BorderLayout.NORTH);
        dialog.getContentPane().add(detailPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void ShowDeleteNhanVienConsole(){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setUndecorated(true);
        dialog.setSize(new Dimension(700, 400));
        dialog.setMinimumSize(new Dimension(700, 400));
        JPanel detailPanel = deleteNhanVienConsole;
        FlatSVGIcon svgIcon = new FlatSVGIcon("icon/cashier.svg", 32, 32);
        dialog.setIconImage(svgIcon.getImage());
        JLabel titleLabel = new JLabel("Xóa nhân viên" , SwingConstants.CENTER);
        titleLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setOpaque(true);
        dialog.getContentPane().add(titleLabel, BorderLayout.NORTH);
        dialog.getContentPane().add(detailPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    // test log
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test NhanVien");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);

            // Tạo đối tượng panel NhanVien
            NhanVien nhanVienPanel = new NhanVien();
            frame.add(nhanVienPanel, BorderLayout.CENTER);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

