show databases;
use quanlysieuthimini;
INSERT INTO nhom_quyen (ma_nhom_quyen, ten_nhom_quyen) VALUES
                                                        (1, 'Quản trị viên'),
                                                      (2, 'Nhân viên'),
                                                      (4, 'Quản lý'),
                                                      (3, 'Khách');

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

select * from nha_cung_cap;

INSERT INTO nhom_quyen (ten_nhom_quyen) VALUES
                                            ('Quản trị viên'),
                                            ('Nhân viên bán hàng'),
                                            ('Nhân viên kho'),
                                            ('Kế toán'),
                                            ('Khách hàng');
INSERT INTO danh_muc_quan_ly (ten_danh_muc_quan_ly) VALUES
                                                        ('Quản lý nhân viên'),
                                                        ('Quản lý khách hàng'),
                                                        ('Quản lý sản phẩm'),
                                                        ('Quản lý đơn hàng'),
                                                        ('Thống kê báo cáo');
INSERT INTO cap_quyen (ma_nhom_quyen, ma_danh_muc_quan_ly, hanh_dong) VALUES
-- Quản trị viên có toàn quyền
(1, 1, 'them'), (1, 1, 'sua'), (1, 1, 'xoa'), (1, 1, 'xem'),
(1, 2, 'them'), (1, 2, 'sua'), (1, 2, 'xoa'), (1, 2, 'xem'),
(1, 3, 'them'), (1, 3, 'sua'), (1, 3, 'xoa'), (1, 3, 'xem'),
(1, 4, 'them'), (1, 4, 'sua'), (1, 4, 'xoa'), (1, 4, 'xem'),
(1, 5, 'xem'),

-- Nhân viên bán hàng chỉ có quyền với khách hàng và đơn hàng
(2, 2, 'them'), (2, 2, 'sua'), (2, 2, 'xem'),
(2, 4, 'them'), (2, 4, 'sua'), (2, 4, 'xem'),

-- Nhân viên kho chỉ có quyền với sản phẩm
(3, 3, 'them'), (3, 3, 'sua'), (3, 3, 'xem'),

-- Kế toán chỉ có quyền xem thống kê
(4, 5, 'xem'),

-- Khách hàng chỉ có thể xem đơn hàng của họ
(5, 4, 'xem');

-- Dữ liệu mẫu cho bảng 'tai_khoan'
INSERT INTO tai_khoan (ten_dang_nhap, ma_nguoi_dung, mat_khau, quyen_nguoi_dung, trang_thai) VALUES
                                                                                                 ('nguyenan', 1, 'password123', 1, 'hoat_dong'),
                                                                                                 ('tranbinh', 2, 'password456', 2, 'da_khoa'),
                                                                                                 ('levan', 3, 'password789', 1, 'hoat_dong'),
                                                                                                 ('phamduong', 4, 'password000', 3, 'hoat_dong'),
                                                                                                 ('hoangem', 5, 'password999', 2, 'hoat_dong');
INSERT INTO tai_khoan (ten_dang_nhap, ma_nguoi_dung, mat_khau, quyen_nguoi_dung, trang_thai) VALUES
                                                                                                 ('mattheao', 6, 'password111', 1, 'hoat_dong'),
                                                                                                 ('vietanh', 7, 'password222', 3, 'hoat_dong'),
                                                                                                 ('duonghoang', 8, 'password333', 2, 'da_khoa'),
                                                                                                 ('lanhha', 9, 'password444', 1, 'hoat_dong'),
                                                                                                 ('hoangnam', 10, 'password555', 2, 'hoat_dong');

-- Thêm nhân viên
INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung) VALUES
    (5, 'nhan_vien');

INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung) VALUES
                                                                            (1, 'nhan_vien'),
                                                                            (2, 'nhan_vien');

-- Thêm khách hàng
INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung) VALUES
                                                                            (3, 'khach_hang'),
                                                                            (4, 'khach_hang');
-- Thêm người dùng mới
INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung) VALUES
                                                            (6, 'nhan_vien'),
                                                            (7, 'khach_hang'),
                                                            (8, 'nhan_vien'),
                                                            (9, 'khach_hang'),
                                                            (10, 'nhan_vien');

-- Thêm nhân viên
INSERT INTO nhan_vien (ma_nhan_vien, ma_nguoi_dung, ten_nhan_vien, email, so_dien_thoai, chuc_vu) VALUES
                                                                                                      (1, 1, 'Nguyễn Văn A', 'nguyenvana@example.com', '0123456789', 'Giám đốc'),
                                                                                                      (2, 2, 'Trần Thị B', 'tranthib@example.com', '0987654321', 'Nhân viên bán hàng'),
                                                                                                      (3, 6, 'Lê Minh C', 'minhc@example.com', '0912345678', 'Kế toán'),
                                                                                                      (4, 8, 'Lê Đức D', 'leducd@example.com', '0934567890', 'Nhân viên kho'),
                                                                                                      (5, 10, 'Nguyễn Thiết F', 'nguyenf@example.com', '0978654321', 'Quản lý sản phẩm'),
                                                                                                      (6, 5, 'Hoàng Minh G', 'hoangmg@example.com', '0913456789', 'Nhân viên kỹ thuật');

-- Thêm khách hàng
INSERT INTO khach_hang (ma_khach_hang, ma_nguoi_dung, ten_khach_hang, so_dien_thoai, dia_chi) VALUES
                                                                                                  (1, 3, 'Lê Văn C', '0912345678', 'Hà Nội, Việt Nam'),
                                                                                                  (2, 4, 'Phạm Linh H', '0976543210', 'TP. Hồ Chí Minh, Việt Nam'),
                                                                                                  (3, 7, 'Trần Bình I', '0901234567', 'Đà Nẵng, Việt Nam'),
                                                                                                  (4, 9, 'Nguyễn Minh J', '0935678910', 'Cần Thơ, Việt Nam');

-- Thêm hóa đơn
INSERT INTO hoa_don (ma_hoa_don, ma_nhan_vien, ma_khach_hang, ngay_tao, tong_gia_tri, trang_thai) VALUES
                                                                                                      (1, 1, 1, NOW(), 500000, 'chua_thanh_toan'),
                                                                                                      (2, 2, 2, NOW(), 1200000, 'da_thanh_toan'),
                                                                                                      (3, 3, 3, NOW(), 2500000, 'chua_thanh_toan'),
                                                                                                      (4, 4, 4, NOW(), 1700000, 'da_thanh_toan');
-- Thêm chi tiết hóa đơn
delete from chi_tiet_hoa_don
INSERT INTO chi_tiet_hoa_don (ma_san_pham, ma_hoa_don, so_luong, gia_ban, thanh_tien) VALUES
                                                                                          (101, 1, 1, 500000, 500000), -- Hóa đơn 1: Sữa tươi Vinamilk
                                                                                          (102, 2, 2, 500000, 1000000), -- Hóa đơn 2: Cà phê G7
                                                                                          (103, 2, 1, 700000, 700000), -- Hóa đơn 2: Nước ngọt Coca-Cola
                                                                                          (103, 3, 2, 700000, 1400000), -- Hóa đơn 3: Nước ngọt Coca-Cola
                                                                                          (104, 4, 3, 500000, 1500000);

INSERT INTO san_pham (ma_san_pham, ten_san_pham, quy_cach, gia_ban, so_lon_ton, ma_danh_muc) VALUES
                                                                                                 (101, 'Sữa tươi Vinamilk', 'chai', 500000, 100, 1),
                                                                                                 (102, 'Cà phê G7', 'goi', 500000, 50, 2),
                                                                                                 (103, 'Nước ngọt Coca-Cola', 'thung', 700000, 30, 3),
                                                                                                 (104, 'Trà xanh C2', 'chai', 500000, 80, 4),
                                                                                                 (105, 'Bia Heineken', 'thung', 900000, 20, 5);

INSERT INTO danh_muc_san_pham (ma_danh_muc, ten_danh_muc) VALUES
                                                              (1, 'Sữa và sản phẩm từ sữa'),
                                                              (2, 'Cà phê và trà'),
                                                              (3, 'Nước giải khát'),
                                                              (4, 'Trà xanh'),
                                                              (5, 'Bia và rượu');

INSERT INTO phieu_nhap (ma_phieu_nhap, ma_nhan_vien, ma_nha_cung_cap, ngay_nhap, tong_gia_tri_nhap) VALUES
                                                                                                        (1, 1, 1, '2025-04-01 08:30:00', 5000000),
                                                                                                        (2, 2, 2, '2025-04-02 10:15:00', 3000000),
                                                                                                        (3, 3, 3, '2025-04-03 14:45:00', 4500000),
                                                                                                        (4, 4, 4, '2025-04-04 09:00:00', 6000000),
                                                                                                        (5, 5, 5, '2025-04-05 16:20:00', 2500000);

INSERT INTO chi_tiet_phieu_nhap (ma_phieu_nhap, ma_san_pham, so_luong, gia_nhap, thanh_tien) VALUES
                                                                                                 (1, 101, 10, 500000, 5000000),
                                                                                                 (2, 102, 5, 600000, 3000000),
                                                                                                 (3, 103, 7, 642857, 4500000),
                                                                                                 (4, 104, 12, 500000, 6000000),
                                                                                                 (5, 105, 4, 625000, 2500000);