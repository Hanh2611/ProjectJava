show databases;
use quanlysieuthimini;
INSERT INTO nhom_quyen (ma_nhom_quyen, ten_nhom_quyen) VALUES
                                                        (1, 'Quản trị viên'),
                                                      (2, 'Nhân viên'),
                                                      (4, 'Quản lý'),
                                                      (3, 'Khách');

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

INSERT INTO tai_khoan (ma_nguoi_dung, ten_dang_nhap, mat_khau, quyen_nguoi_dung, trang_thai) VALUES
                                                                                                 (1, 'admin', 'admin123', 1, 'hoat_dong'),
                                                                                                 (2, 'nv_banhang1', 'pass123', 2, 'hoat_dong'),
                                                                                                 (3, 'nv_banhang2', 'pass456', 2, 'hoat_dong'),
                                                                                                 (4, 'nv_kho1', 'kho123', 3, 'hoat_dong'),
                                                                                                 (5, 'ke_toan1', 'ke_toan456', 4, 'hoat_dong'),
                                                                                                 (6, 'khach_hang1', 'khach123', 5, 'hoat_dong'),
                                                                                                 (7, 'khach_hang2', 'khach456', 5, 'da_khoa');
