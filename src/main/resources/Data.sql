show databases;
use quanlysieuthimini;
INSERT INTO nhom_quyen (ma_nhom_quyen, ten_nhom_quyen) VALUES
                                                           (1, 'Quản trị viên'),
                                                           (2, 'Nhân viên'),
                                                           (3, 'Khách'),
                                                           (4, 'Quản lý'),
                                                           (5, 'Chăm sóc khách hàng'),
                                                           (6, 'Kế toán'),
                                                           (7, 'Bảo vệ'),
                                                           (8, 'Thủ kho'),
                                                           (9, 'Nhà cung cấp'),
                                                           (10, 'Tư vấn viên'),
                                                           (11, 'Nhân viên IT'),
                                                           (12, 'Nhân viên Marketing'),
                                                           (13, 'Nhân viên Bán hàng'),
                                                           (14, 'Giám sát viên'),
                                                           (15, 'Nhân viên Kho'),
                                                           (16, 'Nhân viên Giao hàng'),
                                                           (17, 'Hỗ trợ Kỹ thuật'),
                                                           (18, 'Nhân viên Pháp lý'),
                                                           (19, 'Nhân viên Nhân sự'),
                                                           (20, 'Nhân viên Kinh doanh'),
                                                           (21, 'Quản lý Cửa hàng'),
                                                           (22, 'Quản lý Khu vực'),
                                                           (23, 'Trưởng phòng Kinh doanh'),
                                                           (24, 'Trưởng phòng Nhân sự'),
                                                           (25, 'Trưởng phòng IT'),
                                                           (26, 'Trưởng phòng Marketing'),
                                                           (27, 'Trưởng phòng Kế toán'),
                                                           (28, 'Trưởng phòng Pháp lý'),
                                                           (29, 'Quản lý Kho'),
                                                           (30, 'Quản lý Giao hàng'),
                                                           (31, 'Quản lý Dịch vụ khách hàng'),
                                                           (32, 'Quản lý Hành chính'),
                                                           (33, 'Giám đốc Kinh doanh'),
                                                           (34, 'Giám đốc Tài chính'),
                                                           (35, 'Giám đốc Nhân sự'),
                                                           (36, 'Giám đốc Điều hành'),
                                                           (37, 'Chuyên viên Phân tích Dữ liệu'),
                                                           (38, 'Nhân viên Bảo trì'),
                                                           (39, 'Nhân viên Kỹ thuật'),
                                                           (40, 'Nhân viên An ninh');


use quanlysieuthimini;

# data bang nha cung cap
INSERT INTO nha_cung_cap (ma_nha_cung_cap, ten_nha_cung_cap, so_dien_thoai, email, dia_chi_nha_cung_cap)
VALUES
    (1, 'Công ty Thực phẩm Tươi Sống FreshFood', '0912000111', 'freshfood.hn@gmail.com', 'Số 15, Nguyễn Văn Linh, Hà Nội'),
    (2, 'Công ty Nước giải khát AquaVina', '0988123456', 'aquavina.sales@yahoo.com', 'Khu CN Sài Đồng, Long Biên, Hà Nội'),
    (3, 'Nhà cung cấp Bánh kẹo Hữu Nghị', '0903555777', 'huunghi.bakery@gmail.com', '22 Lê Lợi, TP.Hồ Chí Minh'),
    (4, 'Công ty Sữa và Sản phẩm từ Sữa MilkCorp', '0283822222', 'milkcorp.dairy@email.com', 'Lô A3, Khu CN Tân Bình, TP.HCM'),
    (5, 'Cửa hàng Hóa chất Vệ sinh CleanHouse', '0978999888', 'cleanhouse.supplies@outlook.com', '45 Trần Phú, Đà Nẵng'),
    (6, 'Công ty Đồ gia dụng Tiện Lợi', '0910444555', 'tiendungdungcu@gmail.com', 'Số 8, Hoàng Quốc Việt, Hà Nội'),
    (7, 'Nhà cung cấp Hàng đông lạnh Frosty', '0933111222', 'frosty.frozen@email.com', 'Khu CN An Đồn, Quảng Ninh'),
    (8, 'Công ty Hóa mỹ phẩm BeautyCare', '0866800088', 'beautycare.vn@gmail.com', '12 Phan Chu Trinh, Huế'),
    (9, 'Cửa hàng Văn phòng phẩm SmartOffice', '0899990001', 'smartoffice.sg@yahoo.com', '78 Lý Thái Tổ, Hải Phòng'),
    (10, 'Nhà cung cấp Rau củ hữu cơ GreenLife', '0888123456', 'greenlife.organic@email.com', 'Nông trại Hòa Lạc, Hà Nội');

INSERT INTO quyen (ma_quyen, ten_quyen) VALUES
                                            (1, 'Quản lý hệ thống'),
                                            (2, 'Quản lý sản phẩm'),
                                            (3, 'Quản lý nhân viên'),
                                            (4, 'Quản lý khách hàng'),
                                            (5, 'Xem báo cáo');

INSERT INTO cap_quyen (ma_nhom_quyen, ma_quyen, hanh_dong) VALUES
                                                               (1, 1, 'them'), (1, 1, 'sua'), (1, 1, 'xoa'), (1, 1, 'xem'),
                                                               (1, 2, 'them'), (1, 2, 'sua'), (1, 2, 'xoa'), (1, 2, 'xem'),
                                                               (1, 3, 'them'), (1, 3, 'sua'), (1, 3, 'xoa'), (1, 3, 'xem'),
                                                               (1, 4, 'them'), (1, 4, 'sua'), (1, 4, 'xoa'), (1, 4, 'xem'),
                                                               (1, 5, 'xem'),
                                                               (2, 2, 'xem'), (2, 4, 'xem'),
                                                               (3, 2, 'xem'), (3, 4, 'xem');

INSERT INTO tai_khoan (ma_nguoi_dung, ten_dang_nhap, mat_khau, quyen_nguoi_dung) VALUES
                                                                                     (1, 'admin', 'admin123', 1),
                                                                                     (2, 'nv_banhang1', '123456', 2),
                                                                                     (3, 'nv_kho1', '123456', 3),
                                                                                     (4, 'khach1', '123456', 4);

INSERT INTO nhan_vien (ma_nhan_vien, ten_nhan_vien, email, so_dien_thoai, chuc_vu) VALUES
                                                                                       (1, 'Nguyễn Văn A', 'a@gmail.com', '0909123456', 'Quản lý'),
                                                                                       (2, 'Trần Thị B', 'b@gmail.com', '0912345678', 'Nhân viên bán hàng'),
                                                                                       (3, 'Lê Văn C', 'c@gmail.com', '0987654321', 'Nhân viên kho');

INSERT INTO khach_hang (ma_khach_hang, ten_khach_hang, so_dien_thoai, dia_chi) VALUES
                                                                                   (1, 'Lê Thị D', '0909888777', 'Hà Nội'),
                                                                                   (2, 'Phạm Văn E', '0911999666', 'TP. Hồ Chí Minh');

INSERT INTO san_pham (ma_san_pham, ten_san_pham, ma_danh_muc) VALUES
                                                                  (1, 'Sữa tươi Vinamilk', 1),
                                                                  (2, 'Bánh Oreo', 2),
                                                                  (3, 'Nước ngọt Coca', 3);

INSERT INTO danh_muc_san_pham (ma_danh_muc, ten_danh_muc) VALUES
                                                              (1, 'Sữa'),
                                                              (2, 'Bánh kẹo'),
                                                              (3, 'Nước ngọt');

INSERT INTO loai_dong_goi (ma_dong_goi, ten_dong_goi) VALUES
                                                          (1, 'Hộp giấy'),
                                                          (2, 'Lon'),
                                                          (3, 'Chai thủy tinh');

INSERT INTO phan_loai_san_pham (ma_phan_loai, ma_san_pham, ma_dong_goi, quy_cach, so_luong_ton) VALUES
                                                                                                    (1, 1, 1, '300ml', 100),
                                                                                                    (2, 2, 2, '500ml', 50),
                                                                                                    (3, 3, 3, '1kg', 20);

INSERT INTO hoa_don (ma_hoa_don, ma_nhan_vien, ma_khach_hang, tong_gia_tri) VALUES
                                                                                (1, 2, 1, 50000),
                                                                                (2, 2, 2, 70000);

INSERT INTO chi_tiet_hoa_don (ma_phan_loai, ma_hoa_don, so_luong, gia_ban, thanh_tien) VALUES
                                                                                           (1, 1, 2, 25000, 50000),
                                                                                           (2, 2, 1, 70000, 70000);

INSERT INTO phieu_nhap (ma_phieu_nhap, ma_nhan_vien, ma_nha_cung_cap, tong_gia_tri_nhap) VALUES
                                                                                             (1, 3, 1, 100000),
                                                                                             (2, 3, 2, 150000);

INSERT INTO chi_tiet_phieu_nhap (ma_phieu_nhap, ma_phan_loai, so_luong, gia_nhap, thanh_tien) VALUES
                                                                                                  (1, 1, 10, 10000, 100000),
                                                                                                  (2, 2, 5, 30000, 150000);

INSERT INTO nha_cung_cap (ma_nha_cung_cap, ten_nha_cung_cap, so_dien_thoai, email, dia_chi_nha_cung_cap) VALUES
                                                                                                             (1, 'Công ty Vinamilk', '02812345678', 'contact@vinamilk.com', 'TP. Hồ Chí Minh'),
                                                                                                             (2, 'Công ty Pepsi', '02887654321', 'contact@pepsi.com', 'Hà Nội');
