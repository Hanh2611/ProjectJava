package org.projects.BUS;

import org.projects.DAO.ThongKeTongQuanDAO;

import java.util.HashMap;

public class ThongkeTongQuanBUS {
    private static ThongKeTongQuanDAO tktqDAO = new ThongKeTongQuanDAO();

    public String getTongsoluongton() {
        double tong = tktqDAO.getSoluongtonsanpham();
        if(tong > 0){
            return String.format("%.0f",tong);
        }
        return "hết tồn kho";
    }

    public String getTonggiatri() {
        double tong = tktqDAO.getTonggiatri();
        if(tong > 0){
            return String.format("%,.0fđ",tong);
        }
        return "hết giá trị";
    }
    public String getSoluonghoadon() {
        int soluong = tktqDAO.getSoluonghoadon();
        if(soluong > 0){
            return String.valueOf(soluong);
        }
        return "không có hóa đơn";
    }

    public String getSoluongkhachhang() {
        int soluong = tktqDAO.getSoluongkhachhang();
        if(soluong > 0){
            return String.valueOf(soluong);
        }
        return "không có khách hàng";
    }

    public HashMap<String,Double> getDoanhthu() {
        HashMap<String,Double> doanhthu = tktqDAO.getDoanhthutheothang();
        if(doanhthu.size() > 0){
            return doanhthu;
        }
        return new HashMap<>();
    }

    public HashMap<String,Double> getNhacungcapvatonggiatrinhap() {
        HashMap<String,Double> nccvatonggiatrinhap = tktqDAO.getnhacungcapvatonggiatrinhap();
        if(nccvatonggiatrinhap.size() > 0){
            return nccvatonggiatrinhap;
        }
        return new HashMap<>();
    }
}
