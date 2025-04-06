show databases;
use quanlysieuthimini;
INSERT INTO nhom_quyen (ma_nhom_quyen, ten_nhom_quyen)
VALUES (1, 'Quản trị viên'),
       (2, 'Nhân viên'),
       (4, 'Quản lý'),
       (3, 'Khách');

# data bang nha cung cap
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
        'Nông trại Hòa Lạc, Hà Nội');

INSERT INTO nha_cung_cap (ten_nha_cung_cap, so_dien_thoai, email, dia_chi_nha_cung_cap)
VALUES
    ('Công ty Gia vị Việt SpicePro', '0905111222', 'spicepro.vn@gmail.com', 'KCN Nam Thăng Long, Hà Nội'),
    ('Nhà cung cấp Thịt sạch MeatLand', '0922333444', 'meatland.fresh@email.com', '45 Hoàng Hoa Thám, Hà Nội'),
    ('Công ty Hải sản Biển Đông', '0944555666', 'bien_dong_seafood@gmail.com', 'KCN Cát Lái, TP.HCM'),
    ('Xưởng bánh ngọt SweetHome', '0911222333', 'sweethome.bakery@yahoo.com', '99 Lý Chính Thắng, Đà Nẵng'),
    ('Công ty Đồ uống VinJuice', '0955667788', 'vinjuice.sales@outlook.com', 'KCN Hòa Cầm, Đà Nẵng'),
    ('Cửa hàng Thực phẩm chay An Lạc', '0966778899', 'anlac.vegan@email.com', '23 Nguyễn Huệ, Huế'),
    ('Nhà máy Sữa Hạnh Phúc', '0988999000', 'hanhphuc.dairy@gmail.com', 'Số 4, Điện Biên Phủ, Hà Nội'),
    ('Công ty Ngũ cốc Dinh Dưỡng', '0899001122', 'ngucoc.vn@outlook.com', 'KCN Tân Tạo, TP.HCM'),
    ('Xưởng sản xuất Đồ hộp ViệtCan', '0900333222', 'vietcan.canned@gmail.com', 'Lô B1, KCN Long Thành, Đồng Nai'),
    ('Nhà cung cấp Đồ uống tinh khiết AquaPure', '0922113344', 'aquapure.water@email.com', '15 Cách Mạng Tháng 8, Hà Nội'),
    ('Siêu thị Hàng nhập khẩu GlobalMart', '0988111222', 'globalmart.shop@gmail.com', '50 Trần Hưng Đạo, TP.HCM'),
    ('Công ty Đồ ăn nhanh FastBite', '0911333444', 'fastbite.food@yahoo.com', '120 Lê Văn Sỹ, TP.HCM'),
    ('Nhà cung cấp Hạt điều CashewKing', '0933444555', 'cashewking.vn@email.com', 'KCN Nhơn Trạch, Đồng Nai'),
    ('Nhà máy Mì ăn liền QuickNoodles', '0977888999', 'quicknoodles.foods@outlook.com', 'KCN Vĩnh Lộc, TP.HCM'),
    ('Xưởng sản xuất Trà thảo mộc HerbalTea', '0988222333', 'herbaltea.natural@gmail.com', '88 Phan Đình Phùng, Đà Nẵng'),
    ('Công ty Sản xuất Đường mía SweetSugar', '0911555777', 'sweetsugar.refined@email.com', 'Số 6, Quang Trung, Hà Nội'),
    ('Cửa hàng Đồ khô và Hạt dinh dưỡng NutriStore', '0966555777', 'nutristore.vn@gmail.com', '12 Nguyễn Đình Chiểu, Hà Nội'),
    ('Công ty Bột mì và Ngũ cốc FlourMill', '0933666777', 'flourmill.foods@email.com', 'KCN Biên Hòa, Đồng Nai'),
    ('Nhà máy Sản xuất Gia vị SFlavor', '0955111222', 'sflavor.spices@outlook.com', 'Lô 8, KCN Bình Dương, Bình Dương'),
    ('Công ty Nông sản Xanh FreshAgro', '0999888777', 'freshagro.vn@gmail.com', 'Nông trại Đông Anh, Hà Nội'),
    ('Nhà cung cấp Thực phẩm Đông lạnh ColdPack', '0933222111', 'coldpack.frozen@email.com', 'KCN Tân Thuận, TP.HCM'),
    ('Cửa hàng Rau hữu cơ OrganicFarm', '0909888777', 'organicfarm.vn@gmail.com', 'Nông trại Gia Lâm, Hà Nội'),
    ('Nhà máy Sản xuất Đồ hộp TinCan', '0922000111', 'tincan.foods@email.com', 'Lô C2, KCN Long Hậu, Long An'),
    ('Công ty Cà phê Việt BeansKing', '0988222666', 'beansking.coffee@outlook.com', '110 Điện Biên Phủ, TP.HCM'),
    ('Nhà cung cấp Sữa hạt NutriMilk', '0912333444', 'nutrimilk.dairy@gmail.com', 'KCN Mỹ Phước, Bình Dương'),
    ('Công ty Thịt nguội và Xúc xích SausageLand', '0933666888', 'sausageland.food@email.com', 'KCN Hóc Môn, TP.HCM'),
    ('Xưởng sản xuất Đồ ăn nhanh QuickBites', '0955777888', 'quickbites.fastfood@gmail.com', 'Lô D3, KCN Trà Nóc, Cần Thơ'),
    ('Công ty Đồ uống từ Lúa mạch BarleyDrink', '0977444555', 'barleydrink.brewery@outlook.com', 'KCN Tân An, Long An'),
    ('Nhà cung cấp Dầu thực vật PureOil', '0966333555', 'pureoil.vn@gmail.com', 'Cụm CN Đức Hòa, Long An'),
    ('Cửa hàng Gạo và Nông sản Việt RiceHouse', '0905222333', 'ricehouse.agro@email.com', 'Số 20, Hùng Vương, Hà Nội');


select * from nha_cung_cap;



INSERT INTO nhom_quyen (ma_nhom_quyen, ten_nhom_quyen)
VALUES (1, 'Quản trị viên'),
       (2, 'Nhân viên bán hàng'),
       (3, 'Nhân viên kho'),
       (4, 'Khách hàng');

INSERT INTO danh_muc_quan_ly (ma_danh_muc_quan_ly, ten_danh_muc_quan_ly)
VALUES (1, 'Quản lý nhân viên'),
       (2, 'Quản lý khách hàng'),
       (3, 'Quản lý sản phẩm'),
       (4, 'Quản lý đơn hàng'),
       (5, 'Thống kê báo cáo');

INSERT INTO danh_muc_quan_ly (ma_danh_muc_quan_ly, ten_danh_muc_quan_ly)
VALUES (6, 'Quản lý nhà cung cấp'),
       (7, 'Quản lý hóa đơn'),
       (8, 'Quản lý tài khoản'),
       (9, 'Quản lý phiếu nhập');

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
(1, 5, 'xem'),

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

-- Kế toán chỉ có quyền xem thống kê
(4, 5, 'xem'),

-- Khách hàng chỉ có thể xem đơn hàng của họ
(5, 4, 'xem');

-- Dữ liệu mẫu cho bảng 'tai_khoan'
INSERT INTO tai_khoan (ten_dang_nhap, ma_nguoi_dung, mat_khau, quyen_nguoi_dung, trang_thai)
VALUES ('nguyenan', 1, 'password123', 1, 'hoat_dong'),
       ('tranbinh', 2, 'password456', 2, 'da_khoa'),
       ('levan', 3, 'password789', 1, 'hoat_dong'),
       ('phamduong', 4, 'password000', 3, 'hoat_dong'),
       ('hoangem', 5, 'password999', 2, 'hoat_dong');
INSERT INTO tai_khoan (ten_dang_nhap, ma_nguoi_dung, mat_khau, quyen_nguoi_dung, trang_thai)
VALUES ('mattheao', 6, 'password111', 1, 'hoat_dong'),
       ('vietanh', 7, 'password222', 3, 'hoat_dong'),
       ('duonghoang', 8, 'password333', 2, 'da_khoa'),
       ('lanhha', 9, 'password444', 1, 'hoat_dong'),
       ('hoangnam', 10, 'password555', 2, 'hoat_dong');

-- Thêm nhân viên
INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung)
VALUES (5, 'nhan_vien');

INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung)
VALUES (1, 'nhan_vien'),
       (2, 'nhan_vien');

-- Thêm khách hàng
INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung)
VALUES (3, 'khach_hang'),
       (4, 'khach_hang');
-- Thêm người dùng mới
INSERT INTO nguoi_dung (ma_nguoi_dung, loai_nguoi_dung)
VALUES (6, 'nhan_vien'),
       (7, 'khach_hang'),
       (8, 'nhan_vien'),
       (9, 'khach_hang'),
       (10, 'nhan_vien');

-- Thêm nhân viên
INSERT INTO nhan_vien (ma_nhan_vien, ma_nguoi_dung, ten_nhan_vien, email, so_dien_thoai, chuc_vu)
VALUES (1, 1, 'Nguyễn Văn A', 'nguyenvana@example.com', '0123456789', 'Giám đốc'),
       (2, 2, 'Trần Thị B', 'tranthib@example.com', '0987654321', 'Nhân viên bán hàng'),
       (3, 6, 'Lê Minh C', 'minhc@example.com', '0912345678', 'Kế toán'),
       (4, 8, 'Lê Đức D', 'leducd@example.com', '0934567890', 'Nhân viên kho'),
       (5, 10, 'Nguyễn Thiết F', 'nguyenf@example.com', '0978654321', 'Quản lý sản phẩm'),
       (6, 5, 'Hoàng Minh G', 'hoangmg@example.com', '0913456789', 'Nhân viên kỹ thuật');

-- Thêm khách hàng
INSERT INTO khach_hang (ma_khach_hang, ma_nguoi_dung, ten_khach_hang, so_dien_thoai, dia_chi)
VALUES (1, 3, 'Lê Văn C', '0912345678', 'Hà Nội, Việt Nam'),
       (2, 4, 'Phạm Linh H', '0976543210', 'TP. Hồ Chí Minh, Việt Nam'),
       (3, 7, 'Trần Bình I', '0901234567', 'Đà Nẵng, Việt Nam'),
       (4, 9, 'Nguyễn Minh J', '0935678910', 'Cần Thơ, Việt Nam');

-- Thêm hóa đơn
INSERT INTO hoa_don (ma_hoa_don, ma_nhan_vien, ma_khach_hang, ngay_tao, tong_gia_tri, trang_thai)
VALUES (1, 1, 1, NOW(), 500000, 'chua_thanh_toan'),
       (2, 2, 2, NOW(), 1200000, 'da_thanh_toan'),
       (3, 3, 3, NOW(), 2500000, 'chua_thanh_toan'),
       (4, 4, 4, NOW(), 1700000, 'da_thanh_toan');
-- Thêm chi tiết hóa đơn
INSERT INTO chi_tiet_hoa_don (ma_san_pham, ma_hoa_don, so_luong, gia_ban, thanh_tien)
VALUES (101, 1, 1, 500000, 500000),  -- Hóa đơn 1: Sữa tươi Vinamilk
       (102, 2, 2, 500000, 1000000), -- Hóa đơn 2: Cà phê G7
       (103, 2, 1, 700000, 700000),  -- Hóa đơn 2: Nước ngọt Coca-Cola
       (103, 3, 2, 700000, 1400000), -- Hóa đơn 3: Nước ngọt Coca-Cola
       (104, 4, 3, 500000, 1500000);

INSERT INTO danh_muc_san_pham (ten_danh_muc)
VALUES('Nước giải khát'),
      ('Thịt'),
      ('Gạo, Bột ngọt, Đường'),
      ('Rau, Quả'),
      ('Sữa, Sữa chua');

INSERT INTO san_pham (ten_san_pham, phan_loai, don_vi, gia_ban, so_luong_ton, quy_cach, img)
VALUES('Trà xanh C2 hương chanh 360ml',  1, '360ml', 8000, 100, 'Chai', 'traxanhc2hngchanh360ml.jpg'),
      ('Trà xanh C2 hương chanh 500ml',  1, '500ml', 10000, 100, 'Chai', 'traxanhc2hngchanh500ml.jpg'),
      ('Nước ngọt Coca Cola nguyên bản chai 1.5l', 1, '1.5l', 21000, 200, 'Chai', 'ncngtcocacolanguyenbnchai15l.jpg'),
      ('Beefsteak bò Úc 200g', 2, '200g', 99000, 50, 'Hộp', 'beefsteakbouc200g.jpg'),
      ('Ba chỉ bò Úc đông lạnh Mr.T khay 300g', 2, '300g', 119000, 50, 'Hộp', 'bachbouconglnhmrtkhay300g.jpg'),
      ('Gạo thơm Neptune ST25 Extra túi 5kg', 3, '5kg', 123000, 70, 'Túi', 'gothmneptunest25extratui5kg.jpg'),
      ('Xoài keo 1kg', 4, '1kg', 23900, 20, 'KG', 'xoaikeo1kg.jpg'),
      ('Thùng 48 hộp sữa tươi tiệt trùng ít đường TH true MILK 180ml', 5, '48 hộp', 415000, 90, 'Thùng', 'thung48hopsuatuoitiettrungituongthtruemilk180ml.jpg');


INSERT INTO phieu_nhap (ma_phieu_nhap, ma_nhan_vien, ma_nha_cung_cap, ngay_nhap, tong_gia_tri_nhap)
VALUES (1, 1, 1, '2025-04-01 08:30:00', 5000000),
       (2, 2, 2, '2025-04-02 10:15:00', 3000000),
       (3, 3, 3, '2025-04-03 14:45:00', 4500000),
       (4, 4, 4, '2025-04-04 09:00:00', 6000000),
       (5, 5, 5, '2025-04-05 16:20:00', 2500000);

INSERT INTO chi_tiet_phieu_nhap (ma_phieu_nhap, ma_san_pham, so_luong, gia_nhap, thanh_tien)
VALUES (1, 101, 10, 500000, 5000000),
       (2, 102, 5, 600000, 3000000),
       (3, 103, 7, 642857, 4500000),
       (4, 104, 12, 500000, 6000000),
       (5, 105, 4, 625000, 2500000);
insert into quyen_nguoi_dung(ma_nguoi_dung, ma_nhom_quyen)
value (1, 2),
      (2, 4),
      (3, 1),
    (4, 3),
    (5, 5),
    (6, 1),
    (7, 12),
    (8, 15),
    (9, 1),
    (10, 3)