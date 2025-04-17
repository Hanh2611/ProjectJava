package org.projects.GUI.Panel.ThongkePack;

import org.projects.BUS.ThongkeTongQuanBUS;
import org.projects.GUI.Components.CardPanel;

import javax.swing.*;
import java.awt.*;

public class thongkeTongquan extends JPanel {
    //4 panel ve : san pham,doanh thu,hoa don,khach hang
    private JPanel headerCard;
    private CardPanel sanphamCard;
    private CardPanel doanhthuCard;
    private CardPanel hoadonCard;
    private CardPanel khachhangCard;
    private String value; // lay tu database cua cac bang
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
    }
}
