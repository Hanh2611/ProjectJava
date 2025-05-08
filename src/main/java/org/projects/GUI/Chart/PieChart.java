package org.projects.GUI.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PieChart {
    String title;
    ChartPanel chartPanel;
    public static JPanel createPieChart(String title, ChartPanel chartPanel, HashMap<String,Integer> hm,int width,int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setBackground(Color.WHITE);
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(String key : hm.keySet()) {
            dataset.setValue(key, hm.get(key));
        }
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, false, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setLabelGenerator(null);
        plot.setCircular(true);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(new Color(150, 150, 150, 80));
        plot.setSimpleLabels(true);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width, height));
        panel.add(chartPanel);
        return panel;
    }
}
