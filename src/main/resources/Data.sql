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
