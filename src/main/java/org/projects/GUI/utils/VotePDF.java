package org.projects.GUI.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import org.projects.DAO.NhaCungCapDAO;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;




public class VotePDF {
    //phiếu nhập
    public static void taoPhieuNhap(PhieuNhapEntity pnEntity, List<ChiTietPhieuNhapFullEntity> lst) {
       try {
           String filePath = saveFilePDF(pnEntity.getMaPN(),"PhieuNhap_");
           if(filePath == null) return;
           PdfWriter writer = new PdfWriter(filePath);
           PdfDocument pdfDoc = new PdfDocument(writer);
           Document doc = new Document(pdfDoc);

           InputStream fontStream = VotePDF.class.getClassLoader().getResourceAsStream("fonts/DejaVuSans.ttf");
           if (fontStream == null) throw new RuntimeException("Không tìm thấy font DejaVuSans.ttf!");

           byte[] fontBytes = fontStream.readAllBytes();
           PdfFont font = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
           doc.setFont(font);


           //tiêu đề
           //tên ncc
           //sđt
           //địa chỉ
           //mail liên hệ
           NhaCungCapEntity nccEntity = new NhaCungCapDAO().layNhaCungCapByMaPhieuNhap(pnEntity.getMaPN());
           Paragraph header = new Paragraph(nccEntity.getTenNCC())
                   .setTextAlignment(TextAlignment.LEFT)
                   .setFontSize(20)
                   .setBold();
           Paragraph header1 = new Paragraph("SĐT: " + nccEntity.getSoDienThoaiNCC())
                   .setTextAlignment(TextAlignment.LEFT)
                   .setFontSize(15)
                   .setBold();
           Paragraph header2 = new Paragraph("ĐC: "+ nccEntity.getDiaCHiNCC())
                   .setTextAlignment(TextAlignment.LEFT)
                   .setFontSize(15)
                   .setBold();
           Paragraph header3 = new Paragraph("LH: " + nccEntity.getEmailNCC())
                   .setTextAlignment(TextAlignment.LEFT)
                   .setFontSize(15)
                   .setBold();
           Paragraph header4 = new Paragraph("Mã phiếu: " + String.valueOf(pnEntity.getMaPN()))
                   .setTextAlignment(TextAlignment.RIGHT)
                   .setFontSize(15)
                   .setBold();
           Paragraph title = new Paragraph("PHIẾU NHẬP KHO")
                   .setTextAlignment(TextAlignment.CENTER)
                   .setFontSize(20)
                   .setBold();
           doc.add(header);
           doc.add(header1);
           doc.add(header2);
           doc.add(header3);
           doc.add(header4);
           doc.add(title);
           doc.add(new Paragraph(" "));
           doc.add(new Paragraph("Tên nhân viên: " + pnEntity.getMaNV()));
           Date ngaynhap = pnEntity.getNgayNhap();
           String[] ngaythangnam = ChangeDateToString.reverseDate(ngaynhap);
           doc.add(new Paragraph("Nhập ngày " + ngaythangnam[0] + " tháng " + ngaythangnam[1] + " năm " + ngaythangnam[2]));

           doc.add(new Paragraph(" "));

           float[] columnWidths = {30f, 200f, 80f, 60f, 60f, 80f};
           Table table = new Table(columnWidths);
           String[] headers = {"STT", "Tên sản phẩm", "Đơn vị", "Số lượng", "Đơn giá", "Thành tiền"};
           for (String h : headers) {
               table.addHeaderCell(new Cell().add(new Paragraph(h).setBold()).setTextAlignment(TextAlignment.CENTER));
           }

           int stt = 1;
           double tongtien = 0;
           for(ChiTietPhieuNhapFullEntity ctpnfEntity : lst) {
               double thanhtien = ctpnfEntity.getThanhtien() * ctpnfEntity.getSoLuong();
               tongtien += thanhtien;
               table.addCell(String.valueOf(stt++));
               table.addCell(ctpnfEntity.getTenSP());
               table.addCell(ctpnfEntity.getDonvi());
               table.addCell(String.valueOf(ctpnfEntity.getSoLuong()));
               table.addCell(FormatMoney.formatCurrency((long) ctpnfEntity.getGia()));
               table.addCell(FormatMoney.formatCurrency((long) thanhtien));
           }
           doc.add(table);

           doc.add(new Paragraph(" "));
           doc.add(new Paragraph("Tổng tiền: " + FormatMoney.formatCurrency((long) tongtien)));
           doc.close();
           JOptionPane.showMessageDialog(null, "Đã xuất phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Lỗi khi xuất phiếu nhập PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
       }
    }

    public static void taoHoaDon(HoaDonEntity hdEntity, List<ChiTietHoaDonFullEntity> lst,double tienkhachtra){
        try {
            String filePath = saveFilePDF(hdEntity.getMaHoaDon(),"HoaDon_");
            if(filePath == null) return;
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);

            InputStream fontStream = VotePDF.class.getClassLoader().getResourceAsStream("fonts/DejaVuSans.ttf");
            if (fontStream == null) throw new RuntimeException("Không tìm thấy font DejaVuSans.ttf!");

            byte[] fontBytes = fontStream.readAllBytes();
            PdfFont font = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
            doc.setFont(font);

            //tieu de
            doc.add(new Paragraph("---------------------------").setTextAlignment(TextAlignment.CENTER));
            doc.add(new Paragraph("SIÊU THỊ MINI").setTextAlignment(TextAlignment.CENTER).setFontSize(20).setBold());
            doc.add(new Paragraph("---------------------------").setTextAlignment(TextAlignment.CENTER));
            doc.add(new Paragraph("Địa chỉ: An dương vương,Quận 5,Thành phố Hồ Chí Minh").setTextAlignment(TextAlignment.LEFT));
            doc.add(new Paragraph("SĐT: 09124788112").setTextAlignment(TextAlignment.LEFT).setBold());

            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();
            Paragraph mahd = new Paragraph("Số HĐ: " + hdEntity.getMaHoaDon()).setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(8);
            doc.add(title);
            doc.add(mahd);
            doc.add(new Paragraph("--------------------------------------------------------------"));
            float[] columnWidths = {200f, 60f, 80f,80f};
            Table table = new Table(columnWidths);
            String[] headers = {"Tên hàng", "Số lượng", "Đơn giá", "Thành tiền"};
            for (String h : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(h).setBold()).setTextAlignment(TextAlignment.CENTER));
            }
            int tongso = 0; // sum so luong hoa don

            for(ChiTietHoaDonFullEntity cthdfEntity : lst) {
                tongso += cthdfEntity.getSoLuong();
                table.addCell(cthdfEntity.getTenSP());
                table.addCell(String.valueOf(cthdfEntity.getSoLuong()));
                table.addCell(String.valueOf(cthdfEntity.getGiaBan()));
                table.addCell(String.valueOf(cthdfEntity.getThanhTien()));
            }
            doc.add(table);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Tổng số: " + tongso).setBold());
            Paragraph tongtienphaitra = new Paragraph("Tổng tiền phải trả: " + hdEntity.getTongGiaTri()).setBold();
//            double sotienkhachtra =
            Paragraph sotienkhachdua = new Paragraph("Tiền khách trả: " + String.valueOf(tienkhachtra)).setBold();
            double tienthua = tienkhachtra - hdEntity.getTongGiaTri();
            Paragraph tienthoilai = new Paragraph("Ttiền thối lại: " + String.valueOf(tienthua)).setBold();
            doc.add(tongtienphaitra);
            doc.add(sotienkhachdua);
            doc.add(tienthoilai);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Xin cảm ơn quý khách!!!!"));
            Paragraph nv = new Paragraph("Thu ngân: " + hdEntity.getMaNV()).setBold();
            doc.add(nv);
            Date ngaytao = hdEntity.getNgayTao();
            String[] ngaythangnam = ChangeDateToString.reverseDate(ngaytao);
            doc.add(new Paragraph("Nhập ngày " + ngaythangnam[0] + " tháng " + ngaythangnam[1] + " năm " + ngaythangnam[2]));
            doc.add(new Paragraph("--------------------------------------------------")).setTextAlignment(TextAlignment.CENTER);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static  String saveFilePDF(int maphieunhap,String loaiPHieu) {
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Chọn nơi lưu file");
        f.setSelectedFile(new File(loaiPHieu+maphieunhap + ".pdf"));
        int userSelection = f.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return f.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }
}
