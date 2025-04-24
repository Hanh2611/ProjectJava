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
    String title;
    ChartPanel chartPanel;
    HashMap<String,Integer> hm;
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
        plot.setOutlinePaint(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width, height));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    public static JPanel createColumnChart2(String title, ChartPanel chartPanel, String xAxisLabel, String yAxisLabel, HashMap<String,Double> hm,int width,int height) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setBackground(Color.WHITE);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key : hm.keySet()) {
            dataset.addValue(hm.get(key), title.substring(0,17), key);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setOutlinePaint(Color.WHITE);
        plot.setBackgroundPaint(Color.WHITE);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width, height));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public HashMap<String, Integer> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, Integer> hm) {
        this.hm = hm;
    }
}
