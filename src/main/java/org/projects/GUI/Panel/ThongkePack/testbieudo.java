package org.projects.GUI.Panel.ThongkePack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class testbieudo extends JFrame {
    public testbieudo() {
        setTitle("Biểu đồ cột - JFreeChart");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bước 1: Tạo dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Số lượng", "Tháng 1");
        dataset.addValue(15, "Số lượng", "Tháng 2");
        dataset.addValue(8, "Số lượng", "Tháng 3");
        dataset.addValue(20, "Số lượng", "Tháng 4");

        // Bước 2: Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng theo tháng",
                "Tháng",
                "Số lượng",
                dataset
        );

        // Bước 3: Thêm vào JPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new testbieudo().setVisible(true);
            }
        });
    }

}
