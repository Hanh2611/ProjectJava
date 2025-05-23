-- Xóa và tạo lại cơ sở dữ liệu
DROP DATABASE IF EXISTS quanlysieuthimini;
CREATE DATABASE IF NOT EXISTS quanlysieuthimini;
USE quanlysieuthimini;

-- Tạo các bảng
CREATE TABLE danh_muc_quan_ly
(
    ma_danh_muc_quan_ly  INT PRIMARY KEY,
    ten_danh_muc_quan_ly VARCHAR(50) NOT NULL
);

CREATE TABLE nhom_quyen
(
    ma_nhom_quyen  INT PRIMARY KEY AUTO_INCREMENT,
    ten_nhom_quyen VARCHAR(50) NOT NULL
);

CREATE TABLE cap_quyen
(
    ma_nhom_quyen       INT,
    ma_danh_muc_quan_ly INT,
    hanh_dong           ENUM ('them', 'sua', 'xoa', 'xem', 'excel') NOT NULL,
    PRIMARY KEY (ma_nhom_quyen, ma_danh_muc_quan_ly, hanh_dong)
);

CREATE TABLE nguoi_dung
(
    ma_nguoi_dung   INT                                                        NOT NULL PRIMARY KEY auto_increment,
    loai_nguoi_dung ENUM ('nhan_vien_kho', 'khach_hang', 'nhan_vien_ban_hang') NOT NULL,
    ten_nguoi_dung  VARCHAR(50)                                                NOT NULL,
    is_delete     BIT DEFAULT 0
);

CREATE TABLE tai_khoan
(
    ten_dang_nhap    VARCHAR(50) NOT NULL PRIMARY KEY,
    ma_nguoi_dung    INT         NOT NULL,
    mat_khau         VARCHAR(50) NOT NULL,
    trang_thai       ENUM ('hoat_dong', 'da_khoa') DEFAULT 'hoat_dong',
    is_delete     BIT DEFAULT 0
);

CREATE TABLE nhan_vien
(
    ma_nhan_vien  INT         NOT NULL PRIMARY KEY,
    ma_nguoi_dung INT         ,
    ten_nhan_vien VARCHAR(50) NOT NULL,
    email         VARCHAR(50) NOT NULL,
    so_dien_thoai VARCHAR(15) NOT NULL,
    chuc_vu       VARCHAR(50) NOT NULL,
    luong         INT         NOT NULL,
    gioi_tinh     INT         NOT NULL,
    avatar        VARCHAR(255),
    is_delete     BIT DEFAULT 0
);

CREATE TABLE khach_hang
(
    ma_khach_hang  INT         NOT NULL PRIMARY KEY,
    ma_nguoi_dung  INT,
    ten_khach_hang VARCHAR(50) NOT NULL,
    so_dien_thoai  VARCHAR(15) NOT NULL,
    dia_chi        VARCHAR(50) NOT NULL,
    avatar         VARCHAR(255),
    is_delete     BIT DEFAULT 0
);

CREATE TABLE hoa_don
(
    ma_hoa_don    INT PRIMARY KEY AUTO_INCREMENT,
    ma_nhan_vien  INT,
    ma_khach_hang INT,
    ngay_tao      TIMESTAMP                                 DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri  DOUBLE NOT NULL,
    trang_thai    ENUM ('chua_thanh_toan', 'da_thanh_toan') DEFAULT 'chua_thanh_toan',
    is_delete     BIT DEFAULT 0
);

CREATE TABLE danh_muc_san_pham
(
    ma_danh_muc  INT PRIMARY KEY AUTO_INCREMENT,
    ten_danh_muc VARCHAR(50)
);

CREATE TABLE san_pham
(
    ma_san_pham  INT PRIMARY KEY AUTO_INCREMENT,
    ten_san_pham VARCHAR(255) NOT NULL,
    phan_loai    INT,
    don_vi       VARCHAR(50),
    gia_ban      DOUBLE  DEFAULT 0,
    so_luong_ton DOUBLE  DEFAULT 0,
    quy_cach     ENUM ('Thùng', 'Chai', 'Túi', 'KG', 'Hộp', 'G', 'Khay', 'Gói'),
    img          VARCHAR(255),
    het_hang     BOOLEAN DEFAULT TRUE,
    trang_thai   BOOLEAN DEFAULT TRUE
);

CREATE TABLE chi_tiet_hoa_don
(
    ma_san_pham INT,
    ma_hoa_don  INT,
    so_luong    INT,
    gia_ban     DOUBLE,
    thanh_tien  DOUBLE,
    PRIMARY KEY (ma_san_pham, ma_hoa_don)
);

CREATE TABLE nha_cung_cap
(
    ma_nha_cung_cap      INT PRIMARY KEY auto_increment,
    ten_nha_cung_cap     VARCHAR(50) NOT NULL,
    so_dien_thoai        VARCHAR(15) NOT NULL,
    email                VARCHAR(50) NOT NULL,
    dia_chi_nha_cung_cap VARCHAR(50) NOT NULL,
    isVisible BIT default 0
);

CREATE TABLE phieu_nhap
(
    ma_phieu_nhap     INT PRIMARY KEY AUTO_INCREMENT,
    ma_nhan_vien      INT,
    ma_nha_cung_cap   INT,
    ngay_nhap         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri_nhap DOUBLE,
    is_delete     BIT DEFAULT 0
);

CREATE TABLE chi_tiet_phieu_nhap
(
    ma_phieu_nhap INT,
    ma_san_pham   INT,
    so_luong      INT    NOT NULL,
    gia_nhap      DOUBLE NOT NULL,
    thanh_tien    DOUBLE NOT NULL,
    PRIMARY KEY (ma_phieu_nhap, ma_san_pham)
);

CREATE TABLE quyen_nguoi_dung
(
    ma_nguoi_dung INT,
    ma_nhom_quyen INT,
    PRIMARY KEY (ma_nguoi_dung, ma_nhom_quyen)
);

-- Chèn dữ liệu
INSERT INTO nhom_quyen (ma_nhom_quyen, ten_nhom_quyen)
VALUES (1, 'Quản trị viên'),
       (2, 'Nhân viên'),
       (3, 'Khách'),
       (4, 'Quản lý');

INSERT INTO nha_cung_cap (ma_nha_cung_cap, ten_nha_cung_cap, so_dien_thoai, email, dia_chi_nha_cung_cap)
VALUES (1, 'Công ty Thực phẩm Tươi Sống FreshFood', '0912000111', 'freshfood.hn@gmail.com',
        'Số 15, Nguyễn Văn Linh, Hà Nội'),
       (2, 'Công ty Nước giải khát AquaVina', '0988123456', 'aquavina.sales@yahoo.com',
        'Khu CN Sài Đồng, Long Biên, Hà Nội'),
       (3, 'Nhà cung cấp Bánh kẹo Hữu Nghị', '0903555777', 'huunghi.bakery@gmail.com', '22 Lê Lợi, TP.Hồ Chí Minh'),
       (4, 'Công ty Sữa và Sản phẩm từ Sữa MilkCorp', '0283822222', 'milkcorp.dairy@email.com',
        'Lô A3, Khu CN Tân Bình, TP.HCM'),
       (5, 'Cửa hàng Hóa chất Vệ sinh CleanHouse', '0978999888', 'cleanhouse.supplies@outlook.com',
        '45 Trần Phú, Đà Nẵng'),
       (6, 'Công ty Đồ gia dụng Tiện Lợi', '0910444555', 'tiendungdungcu@gmail.com', 'Số 8, Hoàng Quốc Việt, Hà Nội'),
       (7, 'Nhà cung cấp Hàng đông lạnh Frosty', '0933111222', 'frosty.frozen@email.com', 'Khu CN An Đồn, Quảng Ninh'),
       (8, 'Công ty Hóa mỹ phẩm BeautyCare', '0866800088', 'beautycare.vn@gmail.com', '12 Phan Chu Trinh, Huế'),
       (9, 'Cửa hàng Văn phòng phẩm SmartOffice', '0899990001', 'smartoffice.sg@yahoo.com', '78 Lý Thái Tổ, Hải Phòng'),
       (10, 'Nhà cung cấp Rau củ hữu cơ GreenLife', '0888123456', 'greenlife.organic@email.com',
        'Nông trại Hòa Lạc, Hà Nội'),
       (11, 'Công ty Gia vị Việt SpicePro', '0905111222', 'spicepro.vn@gmail.com', 'KCN Nam Thăng Long, Hà Nội'),
       (12, 'Nhà cung cấp Thịt sạch MeatLand', '0922333444', 'meatland.fresh@email.com', '45 Hoàng Hoa Thám, Hà Nội'),
       (13, 'Công ty Hải sản Biển Đông', '0944555666', 'bien_dong_seafood@gmail.com', 'KCN Cát Lái, TP.HCM'),
       (14, 'Xưởng bánh ngọt SweetHome', '0911222333', 'sweethome.bakery@yahoo.com', '99 Lý Chính Thắng, Đà Nẵng');
INSERT INTO danh_muc_quan_ly (ma_danh_muc_quan_ly, ten_danh_muc_quan_ly)
VALUES (1, 'NhanVien'),
       (2, 'KhachHang'),
       (3, 'SanPham'),
       (4, 'HoaDon'),
       (5, 'ThongKe'),
       (6, 'NhaCungCap'),
       (7, 'TaiKhoan'),
       (8, 'PhanQuyen'),
       (9, 'PhieuNhap');

INSERT INTO cap_quyen (ma_nhom_quyen, ma_danh_muc_quan_ly, hanh_dong)
VALUES
    -- Quản trị viên có toàn quyền
    (1, 1, 'them'),
    (1, 1, 'sua'),
    (1, 1, 'xoa'),
    (1, 1, 'xem'),
    (1, 2, 'them'),
    (1, 2, 'sua'),
    (1, 2, 'xoa'),
    (1, 2, 'xem'),
    (1, 3, 'them'),
    (1, 3, 'sua'),
    (1, 3, 'xoa'),
    (1, 3, 'xem'),
    (1, 4, 'them'),
    (1, 4, 'sua'),
    (1, 4, 'xoa'),
    (1, 4, 'xem'),
    (1, 5, 'them'),
    (1, 5, 'sua'),
    (1, 5, 'xoa'),
    (1, 5, 'xem'),
    (1, 6, 'them'),
    (1, 6, 'sua'),
    (1, 6, 'xoa'),
    (1, 6, 'xem'),
    (1, 7, 'them'),
    (1, 7, 'sua'),
    (1, 7, 'xoa'),
    (1, 7, 'xem'),
    (1, 8, 'them'),
    (1, 8, 'sua'),
    (1, 8, 'xoa'),
    (1, 8, 'xem'),
    (1, 9, 'them'),
    (1, 9, 'sua'),
    (1, 9, 'xoa'),
    (1, 9, 'xem'),

    -- Nhân viên bán hàng chỉ có quyền với khách hàng và đơn hàng
    (2, 2, 'them'),
    (2, 2, 'sua'),
    (2, 2, 'xem'),
    (2, 4, 'them'),
    (2, 4, 'sua'),
    (2, 4, 'xem'),
    -- Nhân viên kho chỉ có quyền với sản phẩm
    (3, 3, 'them'),
    (3, 3, 'sua'),
    (3, 3, 'xem'),
    -- Quản lý chỉ có quyền xem thống kê
    (4, 5, 'xem'),
    -- Khách hàng chỉ có thể xem đơn hàng của họ
    (3, 4, 'xem');

INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung, ten_nguoi_dung , is_delete)
VALUES (1, 'nhan_vien_ban_hang', 'Nguyen An' , false),
       (2, 'nhan_vien_ban_hang', 'Tran Binh' ,false),
       (3, 'khach_hang', 'Le Van' , false),
       (4, 'khach_hang', 'Pham Duong' , false),
       (5, 'nhan_vien_kho', 'Hoang Em' , false),
       (6, 'nhan_vien_ban_hang', 'Matthe Ao' , false),
       (7, 'khach_hang', 'Viet Anh' , false),
       (8, 'nhan_vien_kho', 'Duong Hoang' ,false),
       (9, 'khach_hang', 'Lan Ha' ,false),
       (10, 'nhan_vien_ban_hang', 'Hoang Nam' , false);

INSERT INTO tai_khoan (ten_dang_nhap, ma_nguoi_dung, mat_khau, trang_thai , is_delete)
VALUES ('a', 1, '123', 'hoat_dong', false),
       ('tranbinh', 2, 'password456',  'da_khoa', false),
       ('levan', 3, 'password789',  'hoat_dong' , false),
       ('phamduong', 4, 'password000', 'hoat_dong' , false),
       ('hoangem', 5, 'password999',  'hoat_dong' , false),
       ('mattheao', 6, 'password111',  'hoat_dong' , false),
       ('vietanh', 7, 'password222', 'hoat_dong' , false),
       ('duonghoang', 8, 'password333', 'da_khoa' , false),
       ('lanhha', 9, 'password444','hoat_dong' , false),
       ('hoangnam', 10, 'password555', 'hoat_dong' , false);

INSERT INTO nhan_vien (ma_nhan_vien, ma_nguoi_dung, ten_nhan_vien, email, so_dien_thoai, chuc_vu, luong, gioi_tinh , avatar , is_delete)
VALUES (1, 1, 'Nguyễn Văn A', 'nguyenvana@gmail.com', '0123456789', 'Giám đốc', 30000000, 1 , 'https://res.cloudinary.com/dmw5hl35v/image/upload/v1746715122/z6578188841594_4fc3c5a58a2114e5f38790fd3309d17e_wnte5i.jpg' , false),
       (2, 2, 'Trần Thị B', 'tranthib@gmail.com', '0987654321', 'Nhân viên bán hàng', 15000000, 0 , null , false),
       (3, 6, 'Lê Minh C', 'minhc@gmail.com', '0912345678', 'Kế toán', 18000000, 1 , null , false),
       (4, 8, 'Lê Đức D', 'leducd@gmail.com', '0934567890', 'Nhân viên kho', 16000000, 1,  null , false),
       (5, 10, 'Nguyễn Thiết F', 'nguyenf@gmail.com', '0978654321', 'Quản lý sản phẩm', 20000000, 1 , null , false),
       (6, 5, 'Hoàng Minh G', 'hoangmg@gmail.com', '0913456789', 'Nhân viên kỹ thuật', 17000000, 1 , null , false);

INSERT INTO khach_hang (ma_khach_hang, ma_nguoi_dung, ten_khach_hang, so_dien_thoai, dia_chi , avatar , is_delete)
VALUES (1, 3, 'Lê Văn C', '0912345678', 'Hà Nội, Việt Nam' ,NULL , false),
       (2, 4, 'Phạm Linh H', '0976543210', 'TP. Hồ Chí Minh, Việt Nam' , null, false),
       (3, 7, 'Trần Bình I', '0901234567', 'Đà Nẵng, Việt Nam' , null , false),
       (4, 9, 'Nguyễn Minh J', '0935678910', 'Cần Thơ, Việt Nam' , null , false),
       (5, 3, 'Hoàng Anh K', '0987654321', 'Hải Phòng, Việt Nam' , null , false),
       (6, 9, 'Đỗ Thị L', '0941234567', 'Nha Trang, Việt Nam' , null, false),
       (7, 7, 'Vũ Quang M', '0918765432', 'Huế, Việt Nam' , null , false);

INSERT INTO danh_muc_san_pham (ten_danh_muc)
VALUES ('Nước giải khát'),
       ('Thịt'),
       ('Gạo, Bột ngọt, Đường'),
       ('Rau, Quả'),
       ('Sữa, Sữa chua'),
       ('Bánh');


INSERT INTO san_pham (ten_san_pham, phan_loai, don_vi, gia_ban, so_luong_ton, quy_cach, img, het_hang)
VALUES ('Trà xanh C2 hương chanh 360ml', 1, '360ml', 8000, 100, 'Chai', 'traxanhc2hngchanh360ml.jpg', 0),
       ('Trà xanh C2 hương chanh 500ml', 1, '500ml', 10000, 100, 'Chai', 'traxanhc2hngchanh500ml.jpg', 0),
       ('Nước ngọt Coca Cola nguyên bản chai 1.5l', 1, '1.5l', 21000, 200, 'Chai', 'ncngtcocacolanguyenbnchai15l.jpg', 0),
       ('Beefsteak bò Úc 200g', 2, '200g', 99000, 50, 'Khay', 'beefsteakbouc200g.jpg', 0),
       ('Ba chỉ bò Úc đông lạnh Mr.T khay 300g', 2, '300g', 119000, 50, 'Hộp', 'bachbouconglnhmrtkhay300g.jpg', 0),
       ('Gạo thơm Neptune ST25 Extra túi 5kg', 3, '5kg', 123000, 70, 'Túi', 'gothmneptunest25extratui5kg.jpg', 0),
       ('Xoài keo 1kg', 4, '2kg', 23900, 20, 'KG', 'xoaikeo1kg.jpg', 0),
       ('Thùng 48 hộp sữa tươi tiệt trùng ít đường TH true MILK 180ml', 5, '48 hộp', 415000, 90, 'Thùng',
        'thung48hopsuatuoitiettrungituongthtruemilk180ml.jpg', 0),
       ('Cà rốt trái từ 150g trở lên', 4, '2kg', 34000, 0, 'KG', 'carottraitu150gtrolen.jpg', 1),
       ('Bột ngọt Meizan gói 1kg', 3, '1kg', 69000, 0, 'Túi', 'botngotmeizangoi1kg.jpg', 1),
       ('Bột ngọt Meizan gói 400g', 3, '400g', 32000, 0, 'Túi', 'botngotmeizangoi400g.jpg', 1),
       ('Bánh bơ trứng Richy Karo gói 270g', 6, '270g', 37000, 0, 'Túi', 'banhbotrungrichykarogoi270g.jpg', 1);



INSERT INTO hoa_don (ma_hoa_don, ma_nhan_vien, ma_khach_hang, ngay_tao, tong_gia_tri, trang_thai)
VALUES (1, 1, 1, NOW(), 160000, 'chua_thanh_toan'),
       (2, 2, 2, NOW(), 175000, 'da_thanh_toan'),
       (3, 3, 3, NOW(), 210000, 'chua_thanh_toan'),
       (4, 4, 4, NOW(), 297000, 'da_thanh_toan'),
       (5, 2, 3, '2025-01-05 10:00:00', 350000, 'da_thanh_toan'),
       (6, 1, 4, '2025-01-15 14:30:00', 189000, 'da_thanh_toan'),
       (7, 3, 1, '2025-01-25 09:15:00', 300000, 'da_thanh_toan'),
       (8, 4, 2, '2025-02-03 11:20:00', 278000, 'da_thanh_toan'),
       (9, 2, 5, '2025-02-12 16:45:00', 160000, 'da_thanh_toan'),
       (10, 1, 3, '2025-02-20 13:10:00', 198000, 'da_thanh_toan'),
       (11, 3, 4, '2023-06-10 08:50:00', 150000, 'da_thanh_toan'),
       (12, 2, 1, '2023-11-22 15:30:00', 72000, 'da_thanh_toan'),
       (13, 4, 5, '2023-03-17 12:00:00', 159000, 'da_thanh_toan'),
       (14, 1, 2, '2022-04-05 10:20:00', 30000, 'da_thanh_toan'),
       (15, 3, 3, '2022-09-14 17:10:00', 88000, 'da_thanh_toan'),
       (16, 2, 4, '2022-12-01 11:40:00', 51000, 'da_thanh_toan'),
       (17, 4, 1, '2021-07-19 09:30:00', 64000, 'da_thanh_toan'),
       (18, 1, 5, '2021-02-28 14:00:00', 27000, 'da_thanh_toan'),
       (19, 3, 2, '2021-10-10 16:20:00', 93000, 'da_thanh_toan'),
       (20, 2, 3, '2020-05-12 13:50:00', 40000, 'da_thanh_toan'),
       (21, 4, 4, '2020-08-25 10:15:00', 75000, 'da_thanh_toan'),
       (22, 1, 1, '2020-11-30 15:45:00', 56000, 'da_thanh_toan');

INSERT INTO chi_tiet_hoa_don (ma_san_pham, ma_hoa_don, so_luong, gia_ban, thanh_tien)
VALUES (1, 1, 20, 8000, 160000),
       (2, 2, 7, 10000, 70000),
       (3, 2, 5, 21000, 105000),
       (3, 3, 10, 21000, 210000),
       (4, 4, 3, 99000, 297000),
       (2, 5, 10, 15000, 150000),
       (3, 5, 10, 20000, 200000),
       (4, 6, 1, 99000, 99000),
       (1, 6, 10, 9000, 90000),
       (2, 7, 10, 30000, 300000),
       (3, 8, 10, 21000, 210000),
       (4, 8, 1, 68000, 68000),
       (1, 9, 20, 8000, 160000),
       (4, 10, 2, 99000, 198000),
       (2, 11, 10, 15000, 150000),
       (3, 12, 2, 21000, 42000),
       (1, 12, 3, 10000, 30000),
       (4, 13, 1, 99000, 99000),
       (2, 13, 10, 6000, 60000),
       (1, 14, 2, 15000, 30000),
       (3, 15, 1, 21000, 21000),
       (4, 15, 1, 67000, 67000),
       (2, 16, 2, 25500, 51000),
       (1, 17, 2, 12000, 24000),
       (3, 17, 2, 20000, 40000),
       (2, 18, 3, 9000, 27000),
       (4, 19, 1, 93000, 93000),
       (3, 20, 2, 20000, 40000),
       (4, 21, 1, 75000, 75000),
       (1, 22, 2, 10000, 20000),
       (2, 22, 2, 18000, 36000);

INSERT INTO phieu_nhap (ma_phieu_nhap, ma_nhan_vien, ma_nha_cung_cap, ngay_nhap, tong_gia_tri_nhap)
VALUES (1, 1, 1, '2025-04-01 08:30:00', 700000),
       (2, 2, 2, '2025-04-02 10:15:00', 450000),
       (3, 3, 3, '2025-04-03 14:45:00', 1260000),
       (4, 4, 4, '2025-04-04 09:00:00', 4500000),
       (5, 5, 5, '2025-04-05 16:20:00', 2200000),
        (6, 6, 6, '2025-04-05 16:21:00', 2600000),
       (7, 1, 3, '2025-01-12 08:45:00', 120000),
       (8, 2, 4, '2025-01-25 11:00:00', 240000),
       (9, 3, 2, '2025-02-03 14:10:00', 315000),
       (10, 4, 1, '2025-02-17 09:35:00', 380000),
       (11, 5, 5, '2025-03-08 13:25:00', 200000),
       (12, 6, 6, '2025-03-15 16:40:00', 150000),
       (13, 1, 2, '2025-04-12 10:15:00', 99000),
       (14, 2, 3, '2025-04-22 15:30:00', 222000),
       (15, 3, 4, '2025-05-03 09:50:00', 88000),
       (16, 4, 5, '2025-05-10 17:05:00', 154000);


INSERT INTO chi_tiet_phieu_nhap (ma_phieu_nhap, ma_san_pham, so_luong, gia_nhap, thanh_tien)
VALUES (1, 1, 100, 7000, 700000),
       (2, 2, 50, 9000, 450000),
       (3, 3, 70, 18000, 1260000),
       (4, 4, 50, 90000, 4500000),
       (5, 5, 20, 110000,2200000),
       (6, 5, 65, 20000 , 1300000),
       (6, 2, 65, 20000 , 1300000),
       (7, 1, 10, 12000, 120000),
       (8, 4, 30, 8000, 240000),
       (9, 5, 15, 21000, 315000),
       (10, 3, 20, 19000, 380000),
       (11, 2, 20, 10000, 200000),
       (12, 6, 10, 15000, 150000),
       (13, 8, 11, 9000, 99000),
       (14, 9, 18, 12333, 222000),
       (15, 7, 8, 11000, 88000),
       (16, 10, 14, 11000, 154000);


    INSERT INTO quyen_nguoi_dung (ma_nguoi_dung, ma_nhom_quyen)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3),
       (5, 2),
       (6, 2),
       (7, 3),
       (8, 2),
       (9, 3),
       (10, 2);

-- Thêm ràng buộc khóa ngoại
ALTER TABLE quyen_nguoi_dung
    ADD CONSTRAINT FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung),
    ADD CONSTRAINT FOREIGN KEY (ma_nhom_quyen) REFERENCES nhom_quyen (ma_nhom_quyen);

ALTER TABLE cap_quyen
    ADD CONSTRAINT FOREIGN KEY (ma_nhom_quyen) REFERENCES nhom_quyen (ma_nhom_quyen),
    ADD CONSTRAINT FOREIGN KEY (ma_danh_muc_quan_ly) REFERENCES danh_muc_quan_ly (ma_danh_muc_quan_ly);

ALTER TABLE tai_khoan
    ADD CONSTRAINT FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

ALTER TABLE nhan_vien
    ADD CONSTRAINT FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

ALTER TABLE khach_hang
    ADD CONSTRAINT FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

ALTER TABLE hoa_don
    ADD CONSTRAINT FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien (ma_nhan_vien),
    ADD CONSTRAINT FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang (ma_khach_hang);

ALTER TABLE san_pham
    ADD CONSTRAINT FOREIGN KEY (phan_loai) REFERENCES danh_muc_san_pham (ma_danh_muc);

ALTER TABLE chi_tiet_hoa_don
    ADD CONSTRAINT FOREIGN KEY (ma_san_pham) REFERENCES san_pham (ma_san_pham),
    ADD CONSTRAINT FOREIGN KEY (ma_hoa_don) REFERENCES hoa_don (ma_hoa_don);

ALTER TABLE phieu_nhap
    ADD CONSTRAINT FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien (ma_nhan_vien),
    ADD CONSTRAINT FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap (ma_nha_cung_cap);

ALTER TABLE chi_tiet_phieu_nhap
    ADD CONSTRAINT FOREIGN KEY (ma_phieu_nhap) REFERENCES phieu_nhap (ma_phieu_nhap),
    ADD CONSTRAINT FOREIGN KEY (ma_san_pham) REFERENCES san_pham (ma_san_pham);

-- Kiểm tra dữ liệu
SELECT *
FROM quyen_nguoi_dung;
SELECT *
FROM nhom_quyen;
SELECT *
FROM cap_quyen;
#update nhan_vien set ma_nguoi_dung = 13 where ma_nhan_vien = 9;
Select * from nhan_vien;
Select * from tai_khoan;
select * from nguoi_dung;
select * from khach_hang;


select * from nha_cung_cap
where isVisible = 0