show databases;

use quanlysieuthimini;

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
      ('Xoài keo 1kg', 4, '1kg', 23900, 20, 'KG', 'xoaikeo1kg'),
      ('Thùng 48 hộp sữa tươi tiệt trùng ít đường TH true MILK 180ml', 5, '48 hộp', 415000, 90, 'Thùng', 'thung48hpsatitittrungitngthtruemilk180ml.jpg');

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
