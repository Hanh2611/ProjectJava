DROP DATABASE IF EXISTS quanlysieuthimini;
CREATE DATABASE IF NOT EXISTS quanlysieuthimini;
SHOW DATABASES;
USE quanlysieuthimini;

-- Bảng quyền
create table danh_muc_quan_ly
(
    ma_danh_muc_quan_ly  int primary key,
    ten_danh_muc_quan_ly varchar(50) not null
);

create table nhom_quyen
(
    ma_nhom_quyen  int primary key auto_increment,
    ten_nhom_quyen varchar(50) not null
);

create table cap_quyen
(
    ma_nhom_quyen       int,
    ma_danh_muc_quan_ly int,
    hanh_dong ENUM ('them','sua','xoa','xem', 'excel') not null
);

CREATE TABLE nguoi_dung
(
    ma_nguoi_dung   INT                                                                 NOT NULL PRIMARY KEY,
    loai_nguoi_dung ENUM ('nhan_vien_kho', 'khach_hang', 'nhan_vien_ban_hang', 'admin') NOT NULL,
    ten_nguoi_dung  VARCHAR(50)                                                         NOT NULL
);

-- Bảng tài khoản
create table tai_khoan
(
    ten_dang_nhap    varchar(50) not null primary key,
    ma_nguoi_dung    int         not null unique,
    mat_khau         varchar(50) not null,
    quyen_nguoi_dung int         not null,
    trang_thai       ENUM ('hoat_dong','da_khoa') default 'hoat_dong'
);

-- Bảng nhân viên
create table nhan_vien
(
    ma_nhan_vien  int         NOT NULL primary key,
    ma_nguoi_dung int         not null,
    ten_nhan_vien varchar(50) NOT NULL,
    email         varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    chuc_vu       varchar(50) NOT NULL
);

-- Bảng khách hàng
create table khach_hang
(
    ma_khach_hang  int         NOT NULL primary key,
    ma_nguoi_dung  int,
    ten_khach_hang varchar(50) NOT NULL,
    so_dien_thoai  varchar(15) NOT NULL,
    dia_chi        varchar(50) NOT NULL
);

-- Bảng hóa đơn
create table hoa_don
(
    ma_hoa_don    int primary key NOT NULL,
    ma_nhan_vien  int,
    ma_khach_hang int,
    ngay_tao      TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri  double          NOT NULL,
    trang_thai    ENUM ('chua_thanh_toan','da_thanh_toan') default 'chua_thanh_toan'
);

-- Bảng danh mục sản phẩm
create table danh_muc_san_pham
(
    ma_danh_muc  INT PRIMARY KEY AUTO_INCREMENT,
    ten_danh_muc VARCHAR(50)
);

-- Bảng sản phẩm
create table san_pham
(
    ma_san_pham  INT PRIMARY KEY AUTO_INCREMENT,
    ten_san_pham VARCHAR(255) NOT NULL,
    phan_loai    INT,
    don_vi       VARCHAR(50),
    gia_ban      DOUBLE DEFAULT 0,
    so_luong_ton DOUBLE DEFAULT 0,
    quy_cach     ENUM ('Thùng', 'Chai', 'Túi', 'KG', 'Hộp', 'G'),
    img          VARCHAR(255)
);

-- Bảng chi tiết hóa đơn
create table chi_tiet_hoa_don
(
    ma_san_pham int,
    ma_hoa_don  int,
    so_luong    int,
    gia_ban     double,
    thanh_tien  double
);

-- Bảng nhà cung cấp
create table nha_cung_cap
(
    ma_nha_cung_cap      int primary key,
    ten_nha_cung_cap     varchar(50) NOT NULL,
    so_dien_thoai        varchar(15) NOT NULL,
    email                varchar(50) NOT NULL,
    dia_chi_nha_cung_cap varchar(50) NOT NULL
);

-- Bảng phiếu nhập
create table phieu_nhap
(
    ma_phieu_nhap     int NOT NULL primary key,
    ma_nhan_vien      int,
    ma_nha_cung_cap   int,
    ngay_nhap         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri_nhap double
);

-- Bảng chi tiết phiếu nhập
create table chi_tiet_phieu_nhap
(
    ma_phieu_nhap int PRIMARY KEY,
    ma_san_pham   int,
    so_luong      int    NOT NULL,
    gia_nhap      double NOT NULL,
    thanh_tien    double NOT NULL
);

create table quyen_nguoi_dung (
    ma_nguoi_dung int,
    ma_nhom_quyen int,
    primary key (ma_nguoi_dung, ma_nhom_quyen)
);

alter table quyen_nguoi_dung
    add constraint foreign key (ma_nguoi_dung) references tai_khoan(ma_nguoi_dung),
    add constraint foreign key (ma_nhom_quyen) references nhom_quyen(ma_nhom_quyen);

alter table cap_quyen
    add constraint foreign key (ma_nhom_quyen) references nhom_quyen (ma_nhom_quyen),
    add constraint foreign key (ma_danh_muc_quan_ly) references danh_muc_quan_ly (ma_danh_muc_quan_ly);

alter table tai_khoan
    add constraint foreign key (quyen_nguoi_dung) references nhom_quyen (ma_nhom_quyen),
    add constraint FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

alter table tai_khoan
    add constraint FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

alter table nhan_vien
    add constraint FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

alter table khach_hang
    add constraint FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung (ma_nguoi_dung);

alter table hoa_don
    add constraint foreign key (ma_nhan_vien) references nhan_vien (ma_nhan_vien),
    add constraint foreign key (ma_khach_hang) references khach_hang (ma_khach_hang);

alter table san_pham
    add constraint foreign key (phan_loai) references danh_muc_san_pham (ma_danh_muc);

alter table phieu_nhap
    add constraint FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien (ma_nhan_vien),
    add constraint FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap (ma_nha_cung_cap);

alter table chi_tiet_hoa_don
    add constraint FOREIGN KEY (ma_san_pham) REFERENCES san_pham (ma_san_pham),
    add constraint FOREIGN KEY (ma_hoa_don) REFERENCES hoa_don (ma_hoa_don);

alter table phieu_nhap
    add constraint FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien (ma_nhan_vien),
    add constraint FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap (ma_nha_cung_cap);

alter table phieu_nhap
    add constraint FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap (ma_nha_cung_cap);

alter table chi_tiet_phieu_nhap
    add constraint foreign key (ma_phieu_nhap) references phieu_nhap (ma_phieu_nhap),
    add constraint foreign key (ma_san_pham) references san_pham (ma_san_pham);
