-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 16, 2019 lúc 05:00 PM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `coffeehousedb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `bill_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `emp_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `cus_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `sale_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `sale_total` double NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`bill_id`, `emp_id`, `cus_id`, `date`, `sale_id`, `sale_total`, `status`) VALUES
('00000', '00004', '00002', '28/05/2019', '', 0, 1),
('00001', '00003', '00004', '23/11/2018', '', 0, 1),
('00002', '00002', '00005', '13/05/2019', '', 0, 1),
('00003', '00002', '00003', '13/05/2019', '', 0, 0),
('00004', '00002', '00006', '14/05/2019', 'B0002', 324000, 1),
('00005', '00002', '00008', '15/05/2019', 'B0001', 40000, 0),
('00006', '00002', '00003', '15/05/2019', '', 260000, 0),
('00007', '00002', '00005', '16/05/2019', 'B0002', 71000, 1),
('00008', '00002', '00007', '16/05/2019', '', 40000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill_detail`
--

CREATE TABLE `bill_detail` (
  `bill_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `prod_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `sale_unit_price` double NOT NULL,
  `sale_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill_detail`
--

INSERT INTO `bill_detail` (`bill_id`, `prod_id`, `amount`, `unit_price`, `sale_unit_price`, `sale_id`) VALUES
('00000', 'BR001', 8, 12000, 12000, ''),
('00001', 'CF001', 3, 18000, 18000, ''),
('00002', 'WI001', 3, 35000, 35000, ''),
('00003', 'CF002', 10, 23000, 23000, ''),
('00003', 'SD001', 12, 10000, 10000, ''),
('00003', 'WI001', 2, 35000, 35000, ''),
('00004', 'BR001', 10, 16000, 16000, ''),
('00004', 'CF002', 5, 23000, 23000, ''),
('00004', 'SD001', 5, 11000, 11000, ''),
('00004', 'SD002', 4, 11000, 11000, ''),
('00005', 'CF001', 5, 18000, 18000, ''),
('00006', 'BR001', 10, 16000, 16000, ''),
('00006', 'FJ001', 2, 25000, 25000, ''),
('00006', 'SD001', 5, 10000, 10000, ''),
('00007', 'SD002', 11, 11000, 11000, ''),
('00008', 'CF005', 4, 10000, 10000, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill_sale`
--

CREATE TABLE `bill_sale` (
  `sale_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `bill_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill_sale`
--

INSERT INTO `bill_sale` (`sale_id`, `bill_id`, `status`) VALUES
('B0001', '00005', 0),
('B0002', '00007', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `cus_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `cus_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`cus_id`, `cus_name`, `phone`, `status`) VALUES
('00001', 'Nguyễn Việt Lào', '0987654321', 0),
('00002', 'Lữ Thị Lãng Băng', '0123456789', 1),
('00003', 'Lê Hằng Hà Sá', '0444555666', 0),
('00004', 'Trần Ngọc Đạo Vũ', '0111222332', 0),
('00005', 'Đào Thị Liên Bích', '0777888999', 0),
('00006', 'Đào Thị Liên Bảo', '0777888998', 0),
('00007', 'Lữ Thị Lãng Băng', '0123456789', 0),
('00008', 'Vũ Thúy Tiến Thủ', '0444888645', 1),
('00009', 'Nguyễn Thị Thu Đào', '0111444777', 1),
('00010', 'Lê Hằng Hà Bố', '0444555666', 0),
('00011', 'Lãm Thanh Viên', '0999444332', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employer`
--

CREATE TABLE `employer` (
  `emp_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `emp_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `salary` double NOT NULL,
  `shift` int(11) NOT NULL,
  `emp_type` tinyint(1) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employer`
--

INSERT INTO `employer` (`emp_id`, `emp_name`, `address`, `phone`, `start_date`, `salary`, `shift`, `emp_type`, `username`, `password`, `status`) VALUES
('00000', 'Administrator', 'root', '0123456789', '01/04/2019', 1200, 1, 1, 'admin', '12345', 0),
('00001', 'Nguyễn Văn An', 'root', '2323344545', '02/05/2019', 700, 3, 0, 'abcd123', '12345', 0),
('00002', 'Trần Thị Bích', 'abc', '3434343434', '11/01/2019', 500, 2, 0, 'employer', '12345', 0),
('00003', 'Đỗ Nam Trung', 'Trump Tower', '9119119119', '24/04/2019', 1, 1, 0, 'donald@trump', '12345', 0),
('00004', 'Lê Minh Quang', '123/45 ABC', '2045398517', '25/04/2019', 340, 3, 0, 'hahahihi', '12345', 0),
('00005', 'Phan Văn Hùng', 'L.A ', '0010020034', '25/04/2019', 150, 1, 0, 'phanvanhung', '12345', 0),
('00006', 'Nevermore', 'DoTA tu', '2525252525', '25/04/2019', 145, 3, 0, 'shadowfiend', '12345', 0),
('00007', 'Trần Thanh Liêm', '123/45 Cao Thắng, P9, Q3', '0123456789', '08/05/2019', 400, 2, 0, 'thanhliem', '12345', 0),
('00008', 'Trần Văn Cảnh', '12345 ABC Tp.XYZ', '0909090909', '13/05/2019', 100, 2, 0, 'tranvancanh', '12345', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `import`
--

CREATE TABLE `import` (
  `imp_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `sup_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `emp_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `import_date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `import`
--

INSERT INTO `import` (`imp_id`, `sup_id`, `emp_id`, `import_date`, `status`) VALUES
('00000', '00004', '00001', '11/04/2019', 0),
('00001', '00004', '00003', '19/04/2019', 0),
('00002', '00005', '00002', '01/05/2019', 1),
('00003', '00004', '00001', '19/04/2019', 0),
('00004', '00010', '00003', '01/05/2019', 0),
('00005', '00007', '00002', '13/05/2019', 0),
('00006', '00009', '00002', '16/05/2019', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `import_detail`
--

CREATE TABLE `import_detail` (
  `imp_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `prod_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `amount` double NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `import_detail`
--

INSERT INTO `import_detail` (`imp_id`, `prod_id`, `amount`, `unit_price`) VALUES
('00001', 'CF001', 1, 18000),
('00001', 'CF003', 15, 11000),
('00002', 'BR001', 10, 12000),
('00002', 'IN005', 14, 5000),
('00003', 'IN001', 10, 1000),
('00003', 'IN004', 15, 6000),
('00003', 'SD001', 13, 10000),
('00004', 'CF002', 3, 28000),
('00004', 'IN007', 12, 11500),
('00004', 'TE001', 3, 15000),
('00005', 'CF002', 2, 23000),
('00005', 'SD001', 10, 10000),
('00005', 'SD002', 2, 10000),
('00005', 'TE001', 2, 16000),
('00006', 'SD001', 12, 10000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ingredient`
--

CREATE TABLE `ingredient` (
  `ing_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `ing_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `measure` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `amount` double NOT NULL,
  `unit_price` double NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ingredient`
--

INSERT INTO `ingredient` (`ing_id`, `ing_name`, `measure`, `amount`, `unit_price`, `status`) VALUES
('IN001', 'Đường', 'kg', 41, 1000, 0),
('IN002', 'Vải', 'kg', 0.3, 15000, 0),
('IN003', 'Đá', 'kg', 0.2, 2000, 0),
('IN004', 'Sữa tươi', 'kg', 60.5, 6000, 0),
('IN005', 'Trân châu', 'kg', 16, 3000, 0),
('IN006', 'Cà phê', 'kg', 2, 12500, 0),
('IN007', 'Sữa ông thọ', 'kg', 12.8, 14000, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `prod_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `prod_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `unit_price` double NOT NULL,
  `amount` int(11) NOT NULL,
  `measure` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `img` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`prod_id`, `prod_name`, `unit_price`, `amount`, `measure`, `status`, `description`, `img`) VALUES
('BR001', 'Bia 333', 16000, 15, 'Lon', 1, 'Những lon bia 333 mang lại chất say nồng và tình bạn gắn bó dài lâu', 'images/BR001.jpg'),
('CF001', 'Bạc Sỉu', 18000, 12, 'Ly', 0, 'Là ly cà phê pha với sữa tươi, giúp bạn có một ngày mới sảng khoái', 'images/CF001.jpg'),
('CF002', 'AMERICANO', 23000, 6, 'Ly', 0, 'Americano được pha chế bằng cách thêm nước vào một hoặc hai shot Espresso để pha loãng độ đặc của cà', 'images/CF003.jpg'),
('CF003', 'Cà Phê Đen', 29000, 8, 'Ly', 0, 'Một tách cà phê đen thơm ngào ngạt, phảng phất mùi cacao là món quà tự thưởng tuyệt vời nhất cho nhữ', 'images/CF003.jpg'),
('CF004', 'Cà Phê Sữa', 16000, 4, 'Ly', 0, 'Ly cà phê sữa mang lại cảm giác hứng khỏi vùng với hương vị thanh tao của sữa', 'images/CF004.jpg'),
('CF005', 'Abc', 10000, 0, 'Ly', 1, 'gsdgdgsg', 'images/CF005.jpg'),
('CF006', 'Xyz', 12000, 1, 'Ly', 0, 'sadfsaf', 'images/CF006.jpg'),
('FJ001', 'Sinh tố bơ', 25000, 9, 'Ly', 0, 'Ly sinh tố cùng với bơ đường sữa ông thọ sữa chua tạo nên một ly sinh tố tuyệt hảo', 'images/FJ001.jpg'),
('SD001', 'Coca Cola', 10000, 50, 'Lon', 0, 'Nước có ga giúp cho những bữa tuyệt thêm hương vị', 'images/SD001.jpg'),
('SD002', 'Pepsi', 11000, 10, 'Lon', 0, 'Là Pepsi', 'images/SD002.jpg'),
('TE001', 'Trà sữa bạc hà', 16000, 15, 'Ly', 0, 'Ly trà sữa mang hương vị bạc hà sẽ đem lại sự nhẹ nhàng trong cơ thể', 'images/TE001.jpg'),
('WI001', 'Rượu nho Thái Lan', 35000, 4, 'Chai', 0, 'Một chút chát, chút ngọt, chút đắng sẽ là một người bạn tuyệt vời bên ta', 'images/WI001.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_type`
--

CREATE TABLE `product_type` (
  `type_id` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `type_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product_type`
--

INSERT INTO `product_type` (`type_id`, `type_name`, `status`) VALUES
('CF', 'Cà phê', 0),
('IN', 'Nguyên liệu', 0),
('FJ', 'Sinh tố', 0),
('SD', 'Nước ngọt có ga', 0),
('WI', 'Rượu', 0),
('BR', 'Bia', 0),
('TE', 'Trà', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `prod_sale`
--

CREATE TABLE `prod_sale` (
  `sale_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `prod_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `prod_sale`
--

INSERT INTO `prod_sale` (`sale_id`, `prod_id`, `status`) VALUES
('A0001', 'CF003', 0),
('A0002', 'FJ001', 0),
('A0003', 'BR001', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `recipe`
--

CREATE TABLE `recipe` (
  `prod_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `ing_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `recipe`
--

INSERT INTO `recipe` (`prod_id`, `ing_id`, `amount`) VALUES
('CF001', 'IN006', 0.5),
('CF003', 'IN003', 0.5),
('CF003', 'IN006', 0.4),
('CF004', 'IN003', 0.4),
('CF004', 'IN006', 0.5),
('CF004', 'IN007', 0.3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sale`
--

CREATE TABLE `sale` (
  `sale_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `sale_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `number` double NOT NULL,
  `measure` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `end_date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `threshold` double NOT NULL,
  `threshold_unit` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sale`
--

INSERT INTO `sale` (`sale_id`, `sale_name`, `number`, `measure`, `start_date`, `end_date`, `threshold`, `threshold_unit`, `status`) VALUES
('A0000', 'Giảm giá 25%', 25, '%', '12/02/2019', '24/07/2019', 1, 'món', 0),
('A0001', 'Combo 4 món giảm 100k', 100000, 'đồng', '25/01/2019', '10/05/2019', 1, 'món', 0),
('A0002', 'Siêu giảm giá!', 50, '%', '01/04/2019', '10/05/2019', 1, 'món', 0),
('A0003', 'Mừng khai trương', 100000, 'đồng', '01/01/2019', '31/03/2019', 1, 'món', 0),
('A0004', 'Giảm 10%', 10, '%', '08/05/2019', '31/05/2019', 1, 'món', 0),
('B0000', 'Hóa đơn trên 300k', 15, '%', '25/01/2019', '30/06/2019', 300000, 'đồng', 0),
('B0001', 'Khuyến mãi mùa hè', 50000, 'đồng', '15/03/2019', '20/08/2019', 4, 'món', 0),
('B0002', 'Mua 2 được quà', 50000, 'đồng', '14/05/2019', '14/05/2019', 2, 'món', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `sup_id` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `sup_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `supplier`
--

INSERT INTO `supplier` (`sup_id`, `sup_name`, `address`, `phone`, `status`) VALUES
('00001', 'Trà Thủy Tinh', '999 Lê Văn Sỹ', '0123456789', 0),
('00002', 'Coca', '14 An Dương Vương', '0987654321', 0),
('00003', 'Thế giới cà phê', '147 Lý Thái Tổ', '0222666777', 1),
('00004', 'Trà sữa Đan Mạch', '212 Cộng Hòa', '0444777888', 0),
('00005', 'Rượu Nho Pháp', '217 Phú Thọ Hòa, Q. Tân Phú', '0258369147', 1),
('00006', 'Cà phê Trung Nguyên', '783 Trần Phú Q 5', '0111333888', 0),
('00007', 'Đá Tiên Tiến', '42 Ông Ích Khiêm Q 11', '0443355778', 0),
('00008', 'Hội Bàn Ghế', '45 Phạm Ngũ Lão Q 3', '0478912365', 1),
('00009', 'Revive Nước Giải Khát', '14 Tân Hóa Q. Tân Phú', '0789123556', 0),
('00010', 'String Dâu', '154 Hai Bà Trưng Q9 ', '0357159258', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `emp_id` (`emp_id`),
  ADD KEY `cus_id` (`cus_id`);

--
-- Chỉ mục cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD PRIMARY KEY (`bill_id`,`prod_id`),
  ADD KEY `prod_id` (`prod_id`);

--
-- Chỉ mục cho bảng `bill_sale`
--
ALTER TABLE `bill_sale`
  ADD PRIMARY KEY (`sale_id`,`bill_id`),
  ADD KEY `bill_id` (`bill_id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cus_id`);

--
-- Chỉ mục cho bảng `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`emp_id`),
  ADD UNIQUE KEY `unique_user` (`username`);

--
-- Chỉ mục cho bảng `import`
--
ALTER TABLE `import`
  ADD PRIMARY KEY (`imp_id`),
  ADD KEY `sup_id` (`sup_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- Chỉ mục cho bảng `import_detail`
--
ALTER TABLE `import_detail`
  ADD PRIMARY KEY (`imp_id`,`prod_id`),
  ADD KEY `prod_id` (`prod_id`);

--
-- Chỉ mục cho bảng `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`ing_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`prod_id`);

--
-- Chỉ mục cho bảng `prod_sale`
--
ALTER TABLE `prod_sale`
  ADD PRIMARY KEY (`sale_id`,`prod_id`),
  ADD KEY `prod_id` (`prod_id`);

--
-- Chỉ mục cho bảng `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`prod_id`,`ing_id`),
  ADD KEY `ing_id` (`ing_id`);

--
-- Chỉ mục cho bảng `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`sale_id`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`sup_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
