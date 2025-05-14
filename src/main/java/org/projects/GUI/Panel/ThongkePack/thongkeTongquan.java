package org.projects.GUI.Panel.ThongkePack;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.GradientBarPainter;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.projects.BUS.ThongkeTongQuanBUS;
import org.projects.GUI.Chart.ColumnsChart;
import org.projects.GUI.Components.CardPanel;
import org.projects.GUI.Components.PanelBorderRadius;
import org.projects.GUI.utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
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

    //bottom
    //3 bieu do tròn: tai khoan,hoa don,khach hang
    private JPanel bottomChart;
    private JPanel taikhoanPanel;
    private ChartPanel taikhoanChart;
    private HashMap<Integer,String> taikhoantt;
    private JPanel hoadonPanel;
    private ChartPanel hoadonChart;
    private HashMap<Integer,String> hoadontt;
    private JPanel sanphamPanel;
    private ChartPanel sanphamChart;
    private HashMap<Integer,String> sanphamtt;

    private ThongkeTongQuanBUS tktqBUS;

    public thongkeTongquan() {
        this.setLayout(new BorderLayout(10,10));

        headerCard = new JPanel();
        headerCard.setLayout(new GridLayout(1,4,10,10));
        this.add(headerCard, BorderLayout.NORTH);

        //bieu do cot tinh doanh thu theo thang
        centerChart = new JPanel(new FlowLayout(FlowLayout.LEFT,10,15));
        this.add(centerChart,BorderLayout.CENTER);

        //bottom

        bottomChart = new JPanel(new GridLayout(1,3,10,10));

        this.add(bottomChart,BorderLayout.SOUTH);

        init();
    }

    public void init() {
        tktqBUS = new ThongkeTongQuanBUS();

        headerCard.removeAll();
        centerChart.removeAll();
        bottomChart.removeAll();

        sanphamCard = new CardPanel("icon/dairy-products.svg","Tồn kho",tktqBUS.getTongsoluongton());
        doanhthuCard = new CardPanel("icon/revenue.svg","Doanh thu",tktqBUS.getTonggiatri());
        hoadonCard = new CardPanel("icon/bill.svg","Số hóa đơn",tktqBUS.getSoluonghoadon());
        khachhangCard = new CardPanel("icon/customer.svg","Số khách hàng",tktqBUS.getSoluongkhachhang());

        headerCard.add(sanphamCard);
        headerCard.add(doanhthuCard);
        headerCard.add(hoadonCard);
        headerCard.add(khachhangCard);

        //doanh thu panel
        doanhthuPanel  = ColumnsChart.createColumnChart2("Doanh thu theo tháng",doanhthuChart,"năm-tháng","Doanh thu(VNĐ)",tktqBUS.getDoanhthu(),520,300);
        centerChart.add(doanhthuPanel);

        //top nha cung cap panel
        topnhacungcapPanel = ColumnsChart.createColumnChartWithLongText("Doanh thu theo tháng",topnhacungcapChart,"năm-tháng","Doanh thu(VNĐ)",tktqBUS.getNhacungcapvatonggiatrinhap(),360,300);
        centerChart.add(topnhacungcapPanel);

        taikhoantt = tktqBUS.getTKtrangthai();
        taikhoanPanel = createPieChart("tài khoản",taikhoantt,taikhoanChart);

        hoadontt = tktqBUS.getHDtrangthai();
        hoadonPanel = createPieChart("hóa đơn",hoadontt,hoadonChart);

        sanphamtt = tktqBUS.getSPtrangthai();
        sanphamPanel = createPieChart("Sản phẩm",sanphamtt,sanphamChart);

        bottomChart.add(taikhoanPanel);
        bottomChart.add(hoadonPanel);
        bottomChart.add(sanphamPanel);

        UIUtils.refreshComponent(this);
    }

    //bieu do pie cho tai khoan + ton kho
    private JPanel createPieChart(String title,HashMap<Integer,String> hm,ChartPanel cp) {
            JPanel panel = new PanelBorderRadius();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.setBackground(Color.WHITE);
        DefaultPieDataset dataPie = new DefaultPieDataset();
        for(Integer key : hm.keySet()) {
            dataPie.setValue(hm.get(key),key);
        }

        JFreeChart pieChart = ChartFactory.createPieChart(title,dataPie,true,true,false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        for(Integer key : hm.keySet()) {
            String trangthai = hm.get(key);
            if(trangthai.equalsIgnoreCase("da_thanh_toan") || trangthai.equalsIgnoreCase("hoat_dong") || trangthai.equalsIgnoreCase("Đang kinh doanh")) {
                plot.setSectionPaint(trangthai,Color.decode("#74b9ff"));
            } else if(trangthai.equalsIgnoreCase("da_khoa") || trangthai.equalsIgnoreCase("chua_thanh_toan") || trangthai.equalsIgnoreCase("Ngừng kinh doanh")) {
                plot.setSectionPaint(trangthai,Color.decode("#1B1464"));
            } else {
                plot.setSectionPaint(trangthai,Color.decode("#636e72"));
            }
        }
        plot.setOutlineVisible(false);
        plot.setSimpleLabels(true);
        plot.setInteriorGap(0.04);

        cp = new ChartPanel(pieChart);
        cp.setOpaque(false);
        cp.setPreferredSize(new Dimension(150,200));

        panel.add(cp,BorderLayout.CENTER);
        return panel;
    }

}
