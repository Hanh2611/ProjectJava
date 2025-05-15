package org.projects.BUS;

import org.projects.DAO.ChiTietHoaDonDAO;
import org.projects.DAO.HoaDonDAO;
import org.projects.GUI.Components.CapNhatSoLuongTon;
import org.projects.GUI.DiaLog.HoaDon.ThemHD;
import org.projects.GUI.DiaLog.PhieuNhap.ThemPN;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.ChiTietHoaDonEntity;
import org.projects.entity.HoaDonEntity;
import org.projects.entity.SanPhamEntity;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class HoaDonBUS {
    private static HoaDon hoaDon;
    private static HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private static List<HoaDonEntity> listHoaDon;
    private final SanPhamBus sanPhamBus;
    private ChiTietHoaDonDAO chiTietDAO;
    private ThemHD themHD;

    public HoaDonBUS(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
        this.sanPhamBus = new SanPhamBus();
        this.chiTietDAO = new ChiTietHoaDonDAO(); // ✅ QUAN TRỌNG: khởi tạo ở đây

    }
    public HoaDonBUS() {
        this.sanPhamBus = new SanPhamBus();
        this.chiTietDAO = new ChiTietHoaDonDAO(); // ✅ QUAN TRỌNG: khởi tạo ở đây

    }
    public static List<HoaDonEntity> getList(){
        listHoaDon = hoaDonDAO.showlist();
        return listHoaDon;
    }

    public boolean themHoaDon(HoaDonEntity hoaDon, DefaultTableModel modelDanhSachNhap) {
        int maHD = hoaDonDAO.them(hoaDon);
        if (maHD == -1) {
            return false; // thêm hóa đơn thất bại
        }

        for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
            try {
                int maSP = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 0).toString());
                int soLuong = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 2).toString());
                double giaBan = ThemPN.parseTien(modelDanhSachNhap.getValueAt(i, 3).toString());
                double thanhTien = giaBan * soLuong;
                SanPhamEntity sp = sanPhamBus.getSanPhamById(maSP);
                sp.setSoLuongTon(sp.getSoLuongTon() - soLuong);
                CapNhatSoLuongTon.soLuongNhapGoc.put(maSP, soLuong);
                if (sp.getSoLuongTon() <= 0) {
                    sp.setHetHang(true);
                }
                sanPhamBus.updateSanPham(sp);

                ChiTietHoaDonEntity chiTiet = new ChiTietHoaDonEntity(maSP, maHD, soLuong, giaBan, thanhTien);
                chiTietDAO.them(chiTiet);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    public boolean suaHoaDon(HoaDonEntity hoaDonEntity, List<ChiTietHoaDonEntity> danhSachCu, DefaultTableModel modelDanhSachNhap) {
        // Cập nhật hóa đơn
        int result = hoaDonDAO.sua(hoaDonEntity);
        if (result != 1) {
            return false;  // Cập nhật thất bại
        }

        // Hoàn lại số lượng sản phẩm cũ
        for (ChiTietHoaDonEntity ct : danhSachCu) {
            SanPhamEntity sp = sanPhamBus.getSanPhamById(ct.getMaSP());
            sp.setSoLuongTon(sp.getSoLuongTon() + ct.getSoLuong());
            if (sp.getSoLuongTon() > 0) sp.setHetHang(false);
            sanPhamBus.updateSanPham(sp);
        }

        // Xóa chi tiết hóa đơn cũ
        chiTietDAO.xoaTatCaTheoMaHD(hoaDonEntity.getMaHoaDon());

        // Thêm lại chi tiết hóa đơn mới
        for (int i = 0; i < modelDanhSachNhap.getRowCount(); i++) {
            int maSP = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 0).toString());
            int soLuong = Integer.parseInt(modelDanhSachNhap.getValueAt(i, 2).toString());
            double giaBan = ThemPN.parseTien(modelDanhSachNhap.getValueAt(i, 3).toString());
            double thanhTien = soLuong * giaBan;
            SanPhamEntity sp = sanPhamBus.getSanPhamById(maSP);
            sp.setSoLuongTon(sp.getSoLuongTon() - soLuong);
            if (sp.getSoLuongTon() == 0) sp.setHetHang(true);
            sanPhamBus.updateSanPham(sp);
            ChiTietHoaDonEntity chiTiet = new ChiTietHoaDonEntity(maSP, hoaDonEntity.getMaHoaDon(), soLuong, giaBan, thanhTien);
            chiTietDAO.them(chiTiet);
        }

        return true;  // Cập nhật thành công
    }

    public static boolean xoa(HoaDonEntity pn) {
        if (hoaDon != null) {
            if (hoaDonDAO.xoa(pn) > 0) {
                hoaDon.reloadDAO(); // cái này có thể dư nếu gọi ngoài rồi
                return true;
            }
        }
        return false; // bổ sung return false
    }
    public int getNextMaHoaDon() {
        HoaDonDAO dao = new HoaDonDAO();
        return dao.getMaxMaHoaDon() + 1;
    }
    public static List<HoaDonEntity> search(String keyword, String textField) {
        listHoaDon = getList();  // Cập nhật lại listPN
        List<HoaDonEntity> newlist = new ArrayList<>();
        textField = textField.toLowerCase();
        switch (keyword.toLowerCase()) {
            case "tất cả":
                newlist.addAll(listHoaDon);
                break;
            case "mã":
                for (HoaDonEntity hoaDonEntity : listHoaDon) {
                    if (String.valueOf(hoaDonEntity.getMaHoaDon()).contains(textField)) {
                        newlist.add(hoaDonEntity);
                    }
                }
                break;
            case "tên nhân viên":
                for (HoaDonEntity hoaDonEntity : listHoaDon) {
                    if (String.valueOf(hoaDonEntity.getTenNV()).toLowerCase().contains(textField)) {
                        newlist.add(hoaDonEntity);
                    }
                }
                break;
            case "tên khách hàng":
                for (HoaDonEntity hoaDonEntity : listHoaDon) {
                    if (String.valueOf(hoaDonEntity.getTenKh()).toLowerCase().contains(textField)) {
                        newlist.add(hoaDonEntity);
                    }
                }
                break;
            default:
                System.out.println("không tìm thấy " + keyword + " và " + textField);
                break;
        }
        System.out.println("danh sach ban dau: " + listHoaDon.size());
        System.out.println("danh sach sau khi tim kiem theo " + keyword + " va " + textField + ": " + newlist.size());
        return newlist;
    }

    public static boolean isExistedInHoaDon(int spId){
        return hoaDonDAO.isExistedInHoaDon(spId);
    }

    public static void payment(HoaDonEntity hoaDon) {
        new HoaDonDAO().updateState(hoaDon.getMaHoaDon());
    }
}
