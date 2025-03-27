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
