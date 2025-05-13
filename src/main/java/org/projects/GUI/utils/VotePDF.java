package org.projects.GUI.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import org.projects.DAO.PhieuNhapDAO;
import org.projects.GUI.Panel.PhieuNhap;
import org.projects.entity.ChiTietPhieuNhapFullEntity;
import org.projects.entity.PhieuNhapEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.InputStream;
import java.util.List;




public class VotePDF {
    //phiếu nhập
    public static void taoPhieuNhap(PhieuNhapEntity pnEntity, List<ChiTietPhieuNhapFullEntity> lst) {
       try {
           String filePath = saveFilePDF(pnEntity.getMaPN());
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
           Paragraph header = new Paragraph(new PhieuNhapDAO().getTenNhaCungCapByMaPN(pnEntity.getMaPN()))
                   .setTextAlignment(TextAlignment.CENTER)
                   .setBold();

           Paragraph title = new Paragraph("PHIẾU NHẬP KHO")
                   .setTextAlignment(TextAlignment.CENTER)
                   .setFontSize(16)
                   .setBold();
           doc.add(header);
           doc.add(title);
           doc.add(new Paragraph(" "));
           doc.add(new Paragraph("Mã phiếu nhập: " + pnEntity.getMaPN()));
           doc.add(new Paragraph("Tên nhân viên: " + pnEntity.getMaNV()));
           doc.add(new Paragraph("Ngày nhập: " + pnEntity.getNgayNhap()));
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



    public static  String saveFilePDF(int maphieunhap) {
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Chọn nơi lưu file");
        f.setSelectedFile(new File("PhieuNhap_"+maphieunhap + ".pdf"));
        int userSelection = f.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return f.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }
}
