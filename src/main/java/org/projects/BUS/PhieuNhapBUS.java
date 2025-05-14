package org.projects.BUS;

import org.projects.DAO.ChiTietPhieuNhapDAO;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.DiaLog.PhieuNhap.ThemPN;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.entity.ChiTietPhieuNhapEntity;
import org.projects.entity.PhieuNhapEntity;
import org.projects.entity.SanPhamEntity;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapBUS {
    private static PhieuNhap phieuNhap;
    private static PhieuNhapDAO pnDao = new PhieuNhapDAO();
    private static List<PhieuNhapEntity> listPN;
    private SanPhamBus sanPhamBus;
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;


    public PhieuNhapBUS(PhieuNhap phieuNhap) {
        this.phieuNhap = phieuNhap;
    }

    public PhieuNhapBUS() {
        pnDao = new PhieuNhapDAO();
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        sanPhamBus = new SanPhamBus();
    }
    public static List<PhieuNhapEntity> getList(){
        listPN = pnDao.showlist();
        return listPN;
    }

    public boolean themPhieuNhap(PhieuNhapEntity pn, DefaultTableModel modelDanhSachNhap) {
        // Thêm phiếu nhập vào cơ sở dữ liệu
        if (pnDao.them(pn) <= 0) {
            return false;  // Nếu không thành công
        }

        // Thêm chi tiết phiếu nhập
        for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
            int masp = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 0).toString());
            int soluong = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 2).toString());
            double gianhap = ThemPN.parseTien(modelDanhSachNhap.getValueAt(i, 3).toString());
            double thanhtien = soluong * gianhap;

            // Cập nhật số lượng sản phẩm
            SanPhamEntity sp = sanPhamBus.getSanPhamById(masp);
            sp.setSoLuongTon(sp.getSoLuongTon() + soluong);
            if (sp.getSoLuongTon() > 0) {
                sp.setHetHang(false);
            }
            sanPhamBus.updateSanPham(sp);

            // Thêm chi tiết phiếu nhập
            ChiTietPhieuNhapEntity ct = new ChiTietPhieuNhapEntity(pn.getMaPN(), masp, soluong, gianhap, thanhtien);
            chiTietPhieuNhapDAO.them(ct);
        }

        return true;  // Nếu thành công
    }

    public boolean suaPhieuNhap(PhieuNhapEntity pn, DefaultTableModel modelDanhSachNhap) {
        try {
            int result = pnDao.sua(pn);
            if (result <= 0) return false;

            // Lấy danh sách chi tiết phiếu nhập cũ
            List<ChiTietPhieuNhapEntity> danhSachCu = chiTietPhieuNhapDAO.getChiTietByMaPhieuNhap(pn.getMaPN());

            // Trả lại tồn kho cũ
            for (ChiTietPhieuNhapEntity ctCu : danhSachCu) {
                SanPhamEntity sp = sanPhamBus.getSanPhamById(ctCu.getMaSP());
                sp.setSoLuongTon(sp.getSoLuongTon() - ctCu.getSoLuong());
                sanPhamBus.updateSanPham(sp);
            }

            // Xóa chi tiết cũ
            chiTietPhieuNhapDAO.xoaTheoMaPhieuNhap(pn.getMaPN());

            // Thêm chi tiết mới & cập nhật tồn kho
            for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
                int maSP = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 0).toString());
                int soLuong = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 2).toString());
                long giaNhap = ThemPN.parseTien(modelDanhSachNhap.getValueAt(i, 3).toString());
                long thanhTien = soLuong * giaNhap;

                ChiTietPhieuNhapEntity ct = new ChiTietPhieuNhapEntity(pn.getMaPN(), maSP, soLuong, giaNhap, thanhTien);
                chiTietPhieuNhapDAO.them(ct);

                SanPhamEntity sp = sanPhamBus.getSanPhamById(maSP);
                sp.setSoLuongTon(sp.getSoLuongTon() + soLuong);
                if (sp.getSoLuongTon() > 0) sp.setHetHang(false);
                sanPhamBus.updateSanPham(sp);
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean xoa(PhieuNhapEntity pn){

        if(phieuNhap != null){
            if(pnDao.xoa(pn) > 0){
                phieuNhap.reloadDAO();
                listPN = getList();  // Cập nhật lại listPN
                return true;
            }
        }
        return false;
    }
    public int getNextMaPhieuNhap() {
        return pnDao.getMaxMaPN() + 1;
    }
    //khong su dung

    public static List<PhieuNhapEntity> search(String keyword, String textField) {
        listPN = getList();  // Cập nhật lại listPN
        List<PhieuNhapEntity> newlist = new ArrayList<>();
        textField = textField.toLowerCase();
        switch (keyword.toLowerCase()) {
            case "tất cả":
                newlist.addAll(listPN);
                break;
            case "mã":
                for (PhieuNhapEntity phieuNhapEntity : listPN) {
                    if (String.valueOf(phieuNhapEntity.getMaPN()).contains(textField)) {
                        newlist.add(phieuNhapEntity);
                    }
                }
                break;
            case "tên nhân viên":
                for (PhieuNhapEntity phieuNhapEntity : listPN) {
                    if (String.valueOf(phieuNhapEntity.getTenNV()).toLowerCase().contains(textField)) {
                        newlist.add(phieuNhapEntity);
                    }
                }
                break;
            case "tên ncc":
                for (PhieuNhapEntity phieuNhapEntity : listPN) {
                    if (String.valueOf(phieuNhapEntity.getTenNCC()).toLowerCase().contains(textField)) {
                        newlist.add(phieuNhapEntity);
                    }
                }
                break;
            default:
                System.out.println("không tìm thấy " + keyword + " và " + textField);
                break;
        }
        System.out.println("danh sach ban dau: " + listPN.size());
        System.out.println("danh sach sau khi tim kiem theo " + keyword + " va " + textField + ": " + newlist.size());
        return newlist;
    }

    public static boolean isExistedInPhieuNhap(int spId) {
        return pnDao.isExistedInPhieuNhap(spId);
    }

}