package org.projects.GUI.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ColumnsChart {
    public static JPanel createColumnsChart(String title, ChartPanel chartPanel, String xAxisLabel, String yAxisLabel, HashMap<String,Integer> hm,int width,int height) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setBackground(Color.WHITE);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key : hm.keySet()) {
            dataset.addValue(hm.get(key), "Số lượng", key);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        chart.setBackgroundPaint(Color.WHITE);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width, height));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }
}
