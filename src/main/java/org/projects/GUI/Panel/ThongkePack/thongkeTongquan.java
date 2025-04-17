package org.projects.GUI.Panel.ThongkePack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.projects.BUS.ThongkeTongQuanBUS;
import org.projects.GUI.Components.CardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class thongkeTongquan extends JPanel {
    //header
    //4 panel ve : san pham,doanh thu,hoa don,khach hang
    private JPanel headerCard;
    private CardPanel sanphamCard;
    private CardPanel doanhthuCard;
    private CardPanel hoadonCard;
    private CardPanel khachhangCard;

    //center
    //2 bieu do cot ve doanh thu theo thang va top nha cung cap
    private JPanel centerChart;
    private JPanel doanhthuPanel;
    private ChartPanel doanhthuChart;
    private HashMap<String,Double> doanhthutheothang;
    private JPanel topnhacungcapPanel;
    private ChartPanel topnhacungcapChart;
    private HashMap<String,Double> topnhacungcap;

    private ThongkeTongQuanBUS tktqBUS = new ThongkeTongQuanBUS();

    public thongkeTongquan() {
        this.setLayout(new BorderLayout(10,10));

        headerCard = new JPanel(new GridLayout(1,4,10,10));
        sanphamCard = new CardPanel("icon/dairy-products.svg","Số sản phẩm",tktqBUS.getTongsoluongton());
        doanhthuCard = new CardPanel("icon/revenue.svg","Doanh thu",tktqBUS.getTonggiatri());
        hoadonCard = new CardPanel("icon/bill.svg","Số hóa đơn",tktqBUS.getSoluonghoadon());
        khachhangCard = new CardPanel("icon/customer.svg","Số khách hàng",tktqBUS.getSoluongkhachhang());
        headerCard.add(sanphamCard);
        headerCard.add(doanhthuCard);
        headerCard.add(hoadonCard);
        headerCard.add(khachhangCard);
        this.add(headerCard, BorderLayout.NORTH);

        //bieu do cot tinh doanh thu theo thang
        centerChart = new JPanel(new FlowLayout(FlowLayout.LEFT,10,15));

        //doanh thu panel
        doanhthuPanel = new JPanel(new BorderLayout());
        doanhthuPanel.setBackground(Color.WHITE);
        doanhthuPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#95a5a6")));
        DefaultCategoryDataset dataChart = new DefaultCategoryDataset();
        doanhthutheothang = tktqBUS.getDoanhthu();
        for(String namthang : doanhthutheothang.keySet()) {
            dataChart.addValue(doanhthutheothang.get(namthang),"Doanh thu",namthang);
        }
        //tao bieu do
        JFreeChart columnsChart = ChartFactory.createBarChart("Doanh thu theo tháng","năm-tháng","Doanh thu(VNĐ)",dataChart);
        doanhthuChart = new ChartPanel(columnsChart);
        doanhthuChart.setPreferredSize(new Dimension(520,300));
        doanhthuPanel.add(doanhthuChart,BorderLayout.CENTER);
        centerChart.add(doanhthuPanel);

        //top nha cung cap panel
        topnhacungcapPanel = new JPanel(new BorderLayout());
        topnhacungcapPanel.setBackground(Color.WHITE);
        topnhacungcapPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#95a5a6")));
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        topnhacungcap = tktqBUS.getNhacungcapvatonggiatrinhap();
        for(String tenncc : topnhacungcap.keySet()) {
            dataset.addValue(topnhacungcap.get(tenncc),"nhà cung cấp",tenncc);
        }
        JFreeChart chartNCC = ChartFactory.createBarChart("Top nhà cung cấp","tên nhà cung cấp","Tổng giá trị nhập",dataset);
        topnhacungcapChart = new ChartPanel(chartNCC);
        topnhacungcapChart.setPreferredSize(new Dimension(360,300));
        topnhacungcapPanel.add(topnhacungcapChart,BorderLayout.CENTER);
        centerChart.add(topnhacungcapPanel);

        this.add(centerChart,BorderLayout.CENTER);
    }

}
