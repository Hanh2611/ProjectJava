package org.projects.Action;

import org.projects.BUS.ChiTietHoaDonFullBUS;
import org.projects.BUS.HoaDonBUS;
import org.projects.BUS.SanPhamBus;
import org.projects.DAO.ChiTietHoaDonDAO;
import org.projects.GUI.Components.header.generalFunction;
import org.projects.GUI.Panel.HoaDon;
import org.projects.entity.ChiTietHoaDonEntity;
import org.projects.entity.ChiTietHoaDonFullEntity;
import org.projects.entity.HoaDonEntity;
import org.projects.entity.SanPhamEntity;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.List;

public class HoaDonAction  extends MouseAdapter implements ActionListener, DocumentListener, ItemListener {
    private HoaDon hoaDon;
    private final SanPhamBus sanPhamBus;

    public HoaDonAction(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
        this.sanPhamBus = new SanPhamBus();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        generalFunction func = (generalFunction) e.getSource();
        String action = func.getNameFunction();
        if (hoaDon.getCurrentPanel() != 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng quay lại màn hình chính để thực hiện thao tác.");
            return;
        }
        switch (action) {
            case "add":
                hoaDon.showThemHD();
                break;
            case "update":
                JTable table = hoaDon.getTable();
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1) {
                    int maHD = (int) table.getValueAt(selectedRow, 0);
                    ChiTietHoaDonFullBUS bus = new ChiTietHoaDonFullBUS();
                    List<ChiTietHoaDonFullEntity> list = bus.getChiTietHoaDonFull(maHD);
                    if (HoaDonBUS.getHoaDon(maHD).getTrangThai().equals("da_thanh_toan")) {
                        System.out.println("hoaDon.getTrangThai()");
                        hoaDon.getCapNhatHD().getBtnSuaSP().setVisible(false);
                        hoaDon.getCapNhatHD().getBtnSuaSP().setEnabled(false);
                        JOptionPane.showMessageDialog(hoaDon, "Không thể sửa hóa đơn đã thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        hoaDon.showSuaHD(list);
                    }
                    System.out.println(HoaDonBUS.getHoaDon(maHD).getTrangThai());
                }
                break;
            case "delete":
                JTable table1 = hoaDon.getTable();
                int row = table1.getSelectedRow();
                if (row != -1) {
                    int maHD = (int) table1.getModel().getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xóa hóa đơn này",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO(); // Giả sử DAO này đã được định nghĩa
                        List<ChiTietHoaDonEntity> dsCT = chiTietHoaDonDAO.getChiTietByMaHoaDon(maHD);
                        for (ChiTietHoaDonEntity ct : dsCT) {
                            int maSP = ct.getMaSP();
                            int soLuong = ct.getSoLuong();
                            // Lấy sản phẩm từ sanPhamBus
                            SanPhamEntity sp = sanPhamBus.getSanPhamById(maSP);
                            // Với hóa đơn bán, khi hủy hóa đơn, số lượng sản phẩm bán được hoàn trả lại vào kho
                            sp.setSoLuongTon(sp.getSoLuongTon() + soLuong);
                            // Nếu còn hàng, cập nhật trạng thái sản phẩm
                            if (sp.getSoLuongTon() > 0) {
                                sp.setHetHang(false);
                            }
                            sanPhamBus.updateSanPham(sp);
                        }
                        HoaDonEntity hd = new HoaDonEntity();
                        hd.setMaHoaDon(maHD);
                        boolean result = HoaDonBUS.xoa(hd);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công");
                            hoaDon.reloadDAO();
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa thất bại");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để xóa");
                }
                break;
            case "detail":
                JTable table2 = hoaDon.getTable();
                int selectedRow2 = table2.getSelectedRow();
                if (selectedRow2 != -1) {
                    int maHD = (int) table2.getModel().getValueAt(selectedRow2, 0);
                    ChiTietHoaDonFullBUS bus1 = new ChiTietHoaDonFullBUS();
                    List<ChiTietHoaDonFullEntity> list1 = bus1.getChiTietHoaDonFull(maHD);
                    hoaDon.showChiTietHD(list1);

                break;

        }
        }
}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String keyword = e.getItem().toString();
        String textfield = hoaDon.getHeader().getSearch().getSearchField().getText();
        hoaDon.loadList(HoaDonBUS.search(keyword,textfield));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        String keyword = hoaDon.getHeader().getSearch().getSearchComboBox().getSelectedItem().toString();
        String textfield = e.getDocument().toString();
        System.out.println(textfield);
        hoaDon.loadList(HoaDonBUS.search(keyword,textfield));
    }
}

