package org.projects.GUI.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.projects.GUI.Components.PanelBorderRadius;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class ColumnsChart {
    String title;
    ChartPanel chartPanel;
    HashMap<String,Integer> hm;
    public static JPanel createColumnsChart(String title, ChartPanel chartPanel, String xAxisLabel, String yAxisLabel, HashMap<String,Integer> hm,int width,int height) {
        JPanel panel = new PanelBorderRadius();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setBackground(Color.WHITE);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key : hm.keySet()) {
            String text = key.length() > 15 ? key.substring(0, 10) + "..." : key;
            dataset.addValue(hm.get(key), "Số lượng", text);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setAntiAlias(true);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0));
        domainAxis.setTickLabelFont(new Font("Jetbrains Mono",Font.BOLD,10));
        plot.setBackgroundPaint(new Color(250, 250, 255));      // nền dịu
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                return new GradientPaint(
                        0f, 0f, new Color(102, 178, 255),
                        0f, 300f, new Color(0, 102, 204)
                );
            }
        };
        renderer.setShadowVisible(true);
        renderer.setShadowPaint(new Color(0, 0, 0, 50));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width-20, height-20));
        chartPanel.setOpaque(false);

        JPanel panelcon = new JPanel(new BorderLayout());
        panelcon.setOpaque(false);
        panelcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelcon.add(chartPanel, BorderLayout.CENTER);
        panel.add(panelcon, BorderLayout.CENTER);
        return panel;
    }

    public static JPanel createColumnChart2(String title, ChartPanel chartPanel, String xAxisLabel, String yAxisLabel, HashMap<String,Double> hm,int width,int height) {
        JPanel panel = new PanelBorderRadius();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key : hm.keySet()) {
            String text = key.length() > 15 ? key.substring(0, 10) + "..." : key;
            dataset.addValue(hm.get(key), title, text);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setAntiAlias(true);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0));
        domainAxis.setTickLabelFont(new Font("Jetbrains Mono",Font.BOLD,10));
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
        plot.setBackgroundPaint(new Color(250, 250, 255));      // nền dịu
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                return new GradientPaint(
                        0f, 0f, new Color(102, 178, 255),
                        0f, 300f, new Color(0, 102, 204)
                );
            }
        };
        renderer.setShadowVisible(true);
        renderer.setShadowPaint(new Color(0, 0, 0, 50));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width-20, height-20));
        chartPanel.setOpaque(false);

        JPanel panelcon = new JPanel(new BorderLayout());
        panelcon.setOpaque(false);
        panelcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelcon.add(chartPanel, BorderLayout.CENTER);

        panel.add(panelcon, BorderLayout.CENTER);
        return panel;
    }

    public static JPanel createColumnChartWithLongText(String title, ChartPanel chartPanel, String xAxisLabel, String yAxisLabel, HashMap<String,Double> hm,int width,int height) {
        JPanel panel = new PanelBorderRadius();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key : hm.keySet()) {
            dataset.addValue(hm.get(key), title, key);
        }
        JFreeChart chart = ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,true,true,false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setAntiAlias(true);
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
        plot.setBackgroundPaint(new Color(250, 250, 255));      // nền dịu
        plot.setOutlineVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                return new GradientPaint(
                        0f, 0f, new Color(102, 178, 255),
                        0f, 300f, new Color(0, 102, 204)
                );
            }
        };
        renderer.setShadowVisible(true);
        renderer.setShadowPaint(new Color(0, 0, 0, 50));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(width-20, height-20));
        chartPanel.setOpaque(false);

        JPanel panelcon = new JPanel(new BorderLayout());
        panelcon.setOpaque(false);
        panelcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelcon.add(chartPanel, BorderLayout.CENTER);

        panel.add(panelcon, BorderLayout.CENTER);
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
