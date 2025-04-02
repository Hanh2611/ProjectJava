CREATE DATABASE quanlysieuthimini;
SHOW DATABASES;
USE quanlysieuthimini;

-- Bảng quyền
create table danh_muc_quan_ly (
    ma_danh_muc_quan_ly int primary key auto_increment,
    ten_danh_muc_quan_ly varchar(50) not null
);

-- Bảng nhóm quyền
create table nhom_quyen (
    ma_nhom_quyen int primary key auto_increment,
    ten_nhom_quyen varchar(50) not null
);

-- Bảng cấp quyền
create table cap_quyen (
    ma_nhom_quyen int,
    ma_danh_muc_quan_ly int,
    hanh_dong ENUM('them','sua','xoa','xem') not null,
#     unique key (ma_nhom_quyen, ma_danh_muc_quan_ly),
    foreign key (ma_nhom_quyen) references nhom_quyen(ma_nhom_quyen),
    foreign key (ma_danh_muc_quan_ly) references danh_muc_quan_ly(ma_danh_muc_quan_ly)
);
-- Bảng tài khoản
create table tai_khoan (
    ten_dang_nhap varchar(50) not null primary key,
    ma_nguoi_dung int not null,
    mat_khau varchar(50) not null,
    quyen_nguoi_dung int not null,
    trang_thai ENUM('hoat_dong','da_khoa') default 'hoat_dong',
    foreign key (quyen_nguoi_dung) references nhom_quyen(ma_nhom_quyen)
);
ALTER TABLE tai_khoan
    ADD CONSTRAINT fk_tai_khoan_nguoi_dung FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung(ma_nguoi_dung);

CREATE TABLE nguoi_dung (
                            ma_nguoi_dung INT NOT NULL PRIMARY KEY,
                            loai_nguoi_dung ENUM('nhan_vien', 'khach_hang') NOT NULL,
                            ten_nguoi_dung VARCHAR(50) NOT NULL
);

-- Bảng nhân viên
create table nhan_vien (
    ma_nhan_vien int NOT NULL primary key,
    ma_nguoi_dung int not null,
    ten_nhan_vien varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    chuc_vu varchar(50) NOT NULL
);

ALTER TABLE nhan_vien
    ADD CONSTRAINT fk_nhan_vien_nguoi_dung FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung(ma_nguoi_dung);

-- Bảng khách hàng
create table khach_hang (
    ma_khach_hang int NOT NULL primary key,
    ma_nguoi_dung int,
    ten_khach_hang varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    dia_chi varchar(50) NOT NULL
);
ALTER TABLE khach_hang
    ADD CONSTRAINT fk_khach_hang_nguoi_dung FOREIGN KEY (ma_nguoi_dung) REFERENCES nguoi_dung(ma_nguoi_dung);

-- Bảng hóa đơn
create table hoa_don (
                         ma_hoa_don int NOT NULL,
                         ma_nhan_vien int,
                         ma_khach_hang int,
                         ngay_tao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         tong_gia_tri double NOT NULL,
                         trang_thai ENUM('chua_thanh_toan','da_thanh_toan') default 'chua_thanh_toan'
);
alter table hoa_don
    add constraint primary key (ma_hoa_don);
alter table hoa_don
    add constraint foreign key fk_hoa_don_nhan_vien (ma_nhan_vien) references nhan_vien(ma_nhan_vien),
    add constraint foreign key fk_hoa_don_khach_hang (ma_khach_hang) references khach_hang(ma_khach_hang);
-- Bảng chi tiết hóa đơn
create table chi_tiet_hoa_don (
                                  ma_san_pham int,
                                  ma_hoa_don int,
                                  so_luong int,
                                  gia_ban double,
                                  thanh_tien double
);
alter table chi_tiet_hoa_don
    add constraint primary key (ma_san_pham, ma_hoa_don),
    add constraint foreign key fk_cthd_san_pham (ma_san_pham) references san_pham(ma_san_pham),
    add constraint foreign key fk_cthd_hoa_don (ma_hoa_don) references hoa_don(ma_hoa_don);
-- Bảng sản phẩm
create table san_pham (
    ma_san_pham int NOT NULL primary key ,
    ten_san_pham varchar(50) NOT NULL,
    quy_cach enum('chai', 'goi', 'thung', 'loc'),
    gia_ban int,
    so_lon_ton int,
    ma_danh_muc int
);
alter table san_pham
    add constraint foreign key fk_san_pham_danh_muc (ma_danh_muc) references danh_muc_san_pham(ma_danh_muc);
-- Bảng danh mục sản phẩm
create table danh_muc_san_pham (
    ma_danh_muc int NOT NULL primary key ,
    ten_danh_muc varchar(50)
);
-- Bảng phiếu nhập
create table phieu_nhap (
    ma_phieu_nhap int NOT NULL primary key ,
    ma_nhan_vien int,
    ma_nha_cung_cap int,
    ngay_nhap TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tong_gia_tri_nhap double
);
ALTER TABLE phieu_nhap
    ADD CONSTRAINT fk_phieunhap_nhanvien
        FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien(ma_nhan_vien);
ALTER TABLE phieu_nhap
    ADD CONSTRAINT fk_phieunhap_nhacungcap
        FOREIGN KEY (ma_nha_cung_cap) REFERENCES nha_cung_cap(ma_nha_cung_cap);
-- Bảng chi tiết phiếu nhập
create table chi_tiet_phieu_nhap (
    ma_phieu_nhap int,
    ma_san_pham int,
    so_luong int NOT NULL,
    gia_nhap double NOT NULL,
    thanh_tien double NOT NULL
);
alter table chi_tiet_phieu_nhap
    add constraint pk_chi_tiet_phieu_nhap primary key (ma_phieu_nhap, ma_san_pham),
    add constraint fk_chitietphieunhap_phieunhap foreign key (ma_phieu_nhap) references phieu_nhap(ma_phieu_nhap),
    add constraint fk_chitietphieunhap_sanpham foreign key (ma_san_pham) references san_pham(ma_san_pham);
-- Bảng nhà cung cấp
drop table nha_cung_cap;
create table nha_cung_cap (
    ma_nha_cung_cap int primary key,
    ten_nha_cung_cap varchar(50) NOT NULL,
    so_dien_thoai varchar(15) NOT NULL,
    email varchar(50) NOT NULL,
    dia_chi_nha_cung_cap varchar(50) NOT NULL
);
alter table nha_cung_cap
    add primary key (ma_nha_cung_cap)
