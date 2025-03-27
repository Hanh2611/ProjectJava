CREATE DATABASE quanlysieuthimini;

SHOW DATABASES;
USE quanlysieuthimini;

-- Bảng quyền
create table quyen (
    ma_quyen int primary key auto_increment,
    ten_quyen varchar(50) not null
);

-- Bảng nhóm quyền
create table nhom_quyen (
    ma_nhom_quyen int primary key auto_increment,
    ten_nhom_quyen varchar(50) not null
);

-- Bảng cấp quyền
create table cap_quyen (
    ma_nhom_quyen int,
    ma_quyen int,
    hanh_dong ENUM('them','sua','xoa','xem') not null,
    unique key (ma_nhom_quyen, ma_quyen),
    foreign key (ma_nhom_quyen) references nhom_quyen(ma_nhom_quyen),
    foreign key (ma_quyen) references quyen(ma_quyen)
);

-- Bảng tài khoản
create table tai_khoan (
    ma_nguoi_dung int primary key auto_increment,
    ten_dang_nhap varchar(50) not null unique,
    mat_khau varchar(50) not null,
    quyen_nguoi_dung int not null,
    trang_thai ENUM('hoat_dong','da_khoa') default 'hoat_dong',
    foreign key (quyen_nguoi_dung) references nhom_quyen(ma_nhom_quyen)
);

-- Bảng nhân viên
create table nhan_vien (
    ma_nhan_vien int NOT NULL,
    ten_nhan_vien varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    chuc_vu varchar(50) NOT NULL
);
-- Bảng khách hàng
create table khach_hang (
    ma_khach_hang int NOT NULL,
    ten_khach_hang varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    dia_chi varchar(50) NOT NULL
);
-- Bảng danh mục sản phẩm
create table danh_muc_san_pham (
   ma_danh_muc INT PRIMARY KEY AUTO_INCREMENT,
   ten_danh_muc VARCHAR(50)
);
-- Bảng sản phẩm
create table san_pham (
    ma_san_pham INT PRIMARY KEY AUTO_INCREMENT,
    ten_san_pham VARCHAR(255) NOT NULL,
    phan_loai INT,
    don_vi VARCHAR(50),
    gia_ban DOUBLE DEFAULT 0,
    so_luong_ton DOUBLE DEFAULT 0,
    quy_cach ENUM('Thùng', 'Chai', 'Túi', 'KG', 'Hộp', 'G'),
    img VARCHAR(255),
    FOREIGN KEY (phan_loai) references danh_muc_san_pham(ma_danh_muc)
);
-- Bảng loại đóng gói
create table loai_dong_goi (
    ma_dong_goi int NOT NULL,
    ten_dong_goi varchar(50) NOT NULL
);
-- Bảng hóa đơn
create table hoa_don (
    ma_hoa_don int NOT NULL,
    ma_nhan_vien int,
    ma_khach_hang int,
    ngay_tao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri double NOT NULL
);
-- Bảng chi tiết hóa đơn
create table chi_tiet_hoa_don (
    ma_phan_loai int,
    ma_hoa_don int,
    so_luong int,
    gia_ban double,
    thanh_tien double
);
-- Bảng phiếu nhập
create table phieu_nhap (
    ma_phieu_nhap int NOT NULL,
    ma_nhan_vien int,
    ma_nha_cung_cap int,
    ngay_nhap TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri_nhap double
);
-- Bảng chi tiết phiếu nhập
create table chi_tiet_phieu_nhap (
    ma_phieu_nhap int,
    ma_phan_loai int,
    so_luong int NOT NULL,
    gia_nhap double NOT NULL,
    thanh_tien double NOT NULL
);
-- Bảng nhà cung cấp
create table nha_cung_cap (
    ma_nha_cung_cap int NOT NULL,
    ten_nha_cung_cap varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    email varchar(50) NOT NULL,
    dia_chi_nha_cung_cap varchar(50) NOT NULL
)
