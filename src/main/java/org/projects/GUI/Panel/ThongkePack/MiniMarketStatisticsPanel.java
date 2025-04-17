package org.projects.GUI.Panel.ThongkePack;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class MiniMarketStatisticsPanel extends JPanel {
    public MiniMarketStatisticsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));
        initializeComponents();
    }
    private void initializeComponents() {
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        JLabel headerLabel = new JLabel("Thống Kê Siêu Thị Mini", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Toolbar for navigation
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(52, 152, 219));
        String[] menuItems = {"Sản phẩm", "Hóa đơn", "Phiếu nhập", "Khách hàng", "Nhân viên", "Nhà cung cấp", "Tài khoản"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(52, 152, 219));
            button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            toolBar.add(button);
        }
        headerPanel.add(toolBar, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Top Cards
        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        cardsPanel.setBackground(new Color(240, 240, 240));
        cardsPanel.add(createCard("Số sản phẩm", "150", new Color(100, 181, 246)));
        cardsPanel.add(createCard("Doanh thu", "50,000,000", new Color(100, 181, 246)));
        cardsPanel.add(createCard("Số hóa đơn", "320", new Color(100, 181, 246)));
        cardsPanel.add(createCard("Số khách hàng", "200", new Color(100, 181, 246)));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(cardsPanel, gbc);

        // Histogram (Doanh thu theo ngày)
        JPanel histogramPanel = createHistogram();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.5;
        contentPanel.add(histogramPanel, gbc);

        // Horizontal Bar Chart (Top nhà cung cấp)
        JPanel barChartPanel = createHorizontalBarChart();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        contentPanel.add(barChartPanel, gbc);

        // Pie Charts (Tỷ lệ tồn kho, hóa đơn, khách hàng)
        JPanel pieChartsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        pieChartsPanel.setBackground(new Color(240, 240, 240));
        pieChartsPanel.add(createPieChart("Tồn kho", new String[]{"Còn hàng", "Hết hàng"}, new double[]{70, 30}));
        pieChartsPanel.add(createPieChart("Hóa đơn", new String[]{"Hoàn thành", "Chưa hoàn thành"}, new double[]{85, 15}));
        pieChartsPanel.add(createPieChart("Khách hàng", new String[]{"VIP", "Thường"}, new double[]{20, 80}));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        contentPanel.add(pieChartsPanel, gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String value, Color bgColor) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        card.setPreferredSize(new Dimension(150, 80));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(50, 50, 50));
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valueLabel.setForeground(new Color(52, 152, 219));
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createHistogram() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Doanh thu", "2025-04-10");
        dataset.addValue(15, "Doanh thu", "2025-04-11");
        dataset.addValue(8, "Doanh thu", "2025-04-12");
        dataset.addValue(20, "Doanh thu", "2025-04-13");
        dataset.addValue(12, "Doanh thu", "2025-04-14");

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo ngày", "Ngày", "Doanh thu (triệu VND)",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 200));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createHorizontalBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(200, "Nhà cung cấp", "NCC A");
        dataset.addValue(150, "Nhà cung cấp", "NCC B");
        dataset.addValue(100, "Nhà cung cấp", "NCC C");
        dataset.addValue(80, "Nhà cung cấp", "NCC D");

        JFreeChart chart = ChartFactory.createBarChart(
                "Top nhà cung cấp", "", "Số lượng nhập hàng",
                dataset
        );
        chart.getCategoryPlot().setDomainAxisLocation(org.jfree.chart.axis.AxisLocation.BOTTOM_OR_RIGHT);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(200, 200));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPieChart(String title, String[] labels, double[] values) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < labels.length; i++) {
            dataset.setValue(labels[i], values[i]);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                title, dataset, true, true, false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint(labels[0], new Color(100, 181, 246));
        plot.setSectionPaint(labels[1], new Color(38, 50, 56));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(200, 150));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thống Kê Siêu Thị Mini");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.add(new MiniMarketStatisticsPanel());
            frame.setVisible(true);
        });
    }
}