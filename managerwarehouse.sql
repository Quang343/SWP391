
CREATE TABLE `adjustment` (
  `AdjustmentID` int(11) NOT NULL,
  `AdjustDate` datetime DEFAULT current_timestamp(),
  `reason` varchar(255) DEFAULT NULL,
  `adjustmenttype` enum('Add','Remove') NOT NULL,
  `adjustmentquantity` int(11) NOT NULL,
  `batchid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `adjustment`
--

INSERT INTO `adjustment` (`AdjustmentID`, `AdjustDate`, `reason`, `adjustmenttype`, `adjustmentquantity`, `batchid`) VALUES
(2, '2025-06-26 01:17:09', 'Lỗi', 'Remove', 10, 1),
(5, '2025-06-27 10:57:12', 'hỏng', 'Remove', 2, 3),
(6, '2025-06-27 10:57:25', 'hết hạn', 'Remove', 2, 5),
(7, '2025-07-04 10:34:48', 'hư hại', 'Remove', 10, 1),
(8, '2025-07-04 10:41:16', 'Lỗi', 'Remove', 1, 1),
(9, '2025-07-08 18:24:36', 'Lỗi', 'Remove', 1, 6),
(10, '2025-07-24 00:24:34', NULL, 'Remove', 1, 65),
(11, '2025-08-03 03:55:50', 'Điều chỉnh hàng hết hạn', 'Remove', 4, 23),
(12, '2025-08-03 04:10:45', 'Hư hại', 'Remove', 2, 87);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `authorization`
--

CREATE TABLE `authorization` (
  `AuthorizationID` int(11) NOT NULL,
  `RoleID` int(11) DEFAULT NULL,
  `UrlAuthorized` varchar(255) DEFAULT NULL,
  `FeatureDescription` varchar(255) DEFAULT NULL,
  `StatusSetting` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blog`
--

CREATE TABLE `blog` (
  `blogid` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `blogcategoryid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `createdat` datetime DEFAULT NULL,
  `blogdateupdate` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `blog`
--

INSERT INTO `blog` (`blogid`, `userid`, `blogcategoryid`, `title`, `content`, `createdat`, `blogdateupdate`, `status`, `author`) VALUES
(1, 1, 1, 'Hướng dẫn trồng rau sạch tại nhà', 'Mẹo trồng rau sạch trong không gian nhỏ...', '2025-07-06 14:00:00', NULL, 'ACTIVE', 'Đào Huy Hoàng'),
(2, 2, 2, 'Xu hướng chăn nuôi bền vững', 'Phân tích các phương pháp chăn nuôi hiện đại...', '2025-07-06 14:05:00', NULL, 'ACTIVE', 'Linda Green'),
(3, 1, 1, 'Kỹ thuật làm đất chuẩn bị mùa vụ', 'Các bước làm đất hiệu quả cho mùa màng...', '2025-07-06 14:10:00', NULL, 'ACTIVE', 'Tom Carter'),
(4, 2, 2, 'Tác động của thời tiết đến nông nghiệp', 'Dự báo và ứng phó với biến đổi khí hậu...', '2025-07-06 14:15:00', NULL, 'ACTIVE', 'Linda Green'),
(5, 1, 1, 'Phân bón tự nhiên từ rác thải', 'Hướng dẫn làm phân bón hữu cơ tại nhà...', '2025-07-06 14:20:00', NULL, 'ACTIVE', 'Tom Carter'),
(6, 2, 2, 'Thị trường hạt giống năm 2025', 'Phân tích xu hướng hạt giống mới...', '2025-07-06 14:25:00', NULL, 'ACTIVE', 'Linda Green'),
(7, 1, 1, 'Kỹ thuật bảo quản trái cây', 'Cách bảo quản trái cây tươi lâu...', '2025-07-06 14:30:00', NULL, 'ACTIVE', 'Tom Carter'),
(8, 2, 2, 'Công nghệ tưới tiêu tự động', 'Ứng dụng công nghệ trong nông nghiệp...', '2025-07-06 14:35:00', NULL, 'ACTIVE', 'Linda Green'),
(9, 1, 1, 'Lợi ích của trồng cây lâu năm', 'Ưu điểm và kinh nghiệm thực tế...', '2025-07-06 14:40:00', '2025-07-15 15:49:37', 'ACTIVE', 'Tom Carte'),
(10, 2, 2, 'Dự báo giá nông sản cuối năm', 'Phân tích giá cả và thị trường...', '2025-07-06 14:45:00', NULL, 'ACTIVE', 'Linda Green'),
(19, 1, 5, 'Day thon vĩ dạ', 'Bài thơ \"Đây thôn Vĩ Dạ\"...', '2025-07-09 16:35:50', '2025-07-23 01:07:01', 'ACTIVE', 'Con mèo su kim'),
(20, 1, 1, 's', 's', '2025-07-15 16:30:34', NULL, 'DRAFT', 's'),
(21, 1, 2, 'sá', 'ssa', '2025-07-15 16:34:48', '2025-07-23 00:40:49', 'DELETED', 'sá'),
(22, 30, 1, 'hay', '11', '2025-07-15 16:49:52', '2025-07-23 00:40:43', 'DELETED', 'ssssssss'),
(23, 30, 1, 'phim chưởng', 'hay', '2025-07-15 17:04:29', '2025-07-23 00:37:45', 'DELETED', 'năm can'),
(27, 36, 1, 'Công Nghệ Tưới Nhỏ Giọt Tiết Kiệm Nước', 'Hệ thống tưới nhỏ giọt giúp tiết kiệm 50%...', '2025-07-22 14:41:31', '2025-07-23 00:52:39', 'ACTIVE', 'Đào Huy Hoàng'),
(28, 36, 1, 'Tối Ưu Năng Suất Lúa Gạo Với Giống Mới', 'Nghiên cứu giống lúa mới mang lại năng suất vượt trội...', '2025-07-22 20:20:56', '2025-07-23 15:01:15', 'INACTIVE', 'Đào Huy Hoàng'),
(29, 40, 1, 'Sử Dụng Phân Hữu Cơ Trong Canh Tác Sạch', 'Phân hữu cơ là xu hướng bền vững...', '2025-07-23 00:53:27', NULL, 'ACTIVE', 'Đào Huy Hoàng'),
(30, 40, 3, '7 Loại Rau Dễ Trồng Cho Người Mới Bắt Đầu', 'Danh sách các loại rau phổ biến...', '2025-07-23 00:54:37', '2025-07-24 03:54:10', 'INACTIVE', 'Đào Huy Hoàng'),
(31, 40, 1, 'Xu Hướng Nông Nghiệp Số 2025', 'Các giải pháp công nghệ số giúp quản lý trang trại...', '2025-07-23 00:56:21', '2025-07-23 00:57:37', 'ACTIVE', 'Đô Nan Chăm'),
(32, 40, 1, 'Kỹ Thuật Ghép Cành Cho Cây Ăn Quả', 'Hướng dẫn từng bước kỹ thuật ghép cành...', '2025-07-23 00:59:12', NULL, 'ACTIVE', 'Lý Bạch'),
(33, 40, 1, 'Dinh Dưỡng Từ Rau Mầm Tự Trồng', 'Rau mầm giàu vitamin, dễ trồng tại nhà...', '2025-07-23 01:00:06', NULL, 'ACTIVE', 'Lý Bạch'),
(34, 40, 1, 'Quản Lý Dịch Hại Trên Cây Trồng Bằng Sinh Học', 'Ứng dụng biện pháp sinh học giúp kiểm soát sâu bệnh...', '2025-07-23 01:01:07', NULL, 'ACTIVE', 'Đào Huy Hoàng'),
(35, 40, 1, 'Những Điều Lưu Ý Khi Chọn Giống Cây Trồng', 'Các tiêu chí cần thiết khi chọn giống...', '2025-07-23 01:01:58', NULL, 'ACTIVE', 'Nguyễn Đăng Khoa'),
(36, 40, 1, 'Sáng Kiến Đổi Mới Trong Chuỗi Cung Ứng Nông Sản', 'Công nghệ blockchain, truy xuất nguồn gốc...', '2025-07-23 01:05:34', NULL, 'ACTIVE', 'Tk Chiến Fpt'),
(37, NULL, 3, 'Sử dụng hoàn toàn chế phẩm sinh học sản xuất lúa, giảm 15% chi phí', 'Vụ hè thu 2025, Công ty TNHH Trường Sơn BIO phối hợp với Công ty Giống cây trồng miền Nam - Chi nhánh Cờ Đỏ (TP Cần Thơ) triển khai mô hình trình diễn sử dụng hoàn toàn chế phẩm sinh học trên diện tích 2 hecta lúa giống Đài Thơm tại ấp An Thạnh, xã Thạnh Phú, TP Cần Thơ.\n\nMô hình ứng dụng quy trình canh tác gồm 3 lần sử dụng chế phẩm sinh học, từ khâu xử lý rơm rạ, cải tạo đất, diệt ốc bươu vàng đến phòng trừ sâu bệnh, nấm hại và cung cấp dinh dưỡng.\n\nCụ thể, lần 1 sử dụng 5 lít chế phẩm sinh học/hecta để diệt ốc bươu vàng, tích hợp vi sinh vật phân hủy rơm rạ, cải tạo đất, tiêu diệt mầm bệnh, hạ phèn và chống ngộ độc hữu cơ. Lần 2 tiếp tục phun 5 lít chế phẩm diệt nấm bệnh và 5 lít diệt sâu/hecta. Lần 3 sử dụng 3 lít chế phẩm diệt nấm, 3 lít diệt sâu và 5 lít chế phẩm siêu dinh dưỡng/hecta.', '2025-07-24 03:59:17', '2025-07-24 03:59:49', 'ACTIVE', 'Đào Huy Hoàng'),
(38, NULL, 3, 'Lạc lối giữa nông trại cam trù phú xứ Thanh', 'Năm 2016, anh Nguyễn Văn Chung gom hết vốn liếng, cầm cố nhà cửa vay ngân hàng để thuê, mua lại từng thửa đất của bà con quanh vùng làm nông trại. Nhiều người lắc đầu, bảo anh khác người. Có người buông lời: “Thời nay ai còn đi trồng cây ở rừng? Có khùng mới bỏ tiền tỷ lên đồi trồng cam”.\n\nAnh Chung thì khác. Người đàn ông trung niên có cái lý riêng của mình khi đưa ra quyết định táo bạo như vậy. Với anh, trở về với nông nghiệp là cách bản thân trả ơn cho đất. “Mình sống bao năm nhờ đất, ăn cơm nhờ hạt gạo từ đất, giờ còn sức thì phải làm gì đó cho đất được sống lại”, anh tâm sự.\n\nĐất đồi Vân Du khi ấy khô cằn, đá lẫn trong đất, phải dùng cuốc xẻng mà nạy từng viên một. Cỏ dại cao lút đầu người. Mùa nắng đất nứt nẻ như chân ruộng bỏ hoang, mùa mưa nước chảy tràn, xói cả lớp đất mặt', '2025-07-24 04:02:06', NULL, 'ACTIVE', 'Đào Huy Hoàng'),
(39, 40, 3, 'Đề xuất hỗ trợ 3.300 xã phường bán nông sản online', 'Giám đốc cơ quan xúc tiến thương mại của Bộ Nông nghiệp và Môi trường nêu ý tưởng hỗ trợ 3.300 xã phường cả nước bán nông sản online.\n\nĐề xuất được ông Nguyễn Minh Tiến, Giám đốc Trung tâm Xúc tiến Thương mại Nông nghiệp và Môi trường (Bộ Nông nghiệp và Môi trường) nêu tại tọa đàm \"Niềm tin số: Tương lai của Thương mại điện tử\" hôm 17/7.\n\nTừ 1/7, cả nước có hơn 3.300 xã phường sau khi sắp xếp đơn vị hành chính. Theo ông Tiến, để tận dụng kênh thương mại điện tử đang phát triển, cơ quan quản lý và nền tảng bán hàng có thể hợp tác để hỗ trợ từng xã tiếp cận thị trường này.\n\n\"Tại sao chúng ta không hướng tới hỗ trợ mỗi xã đều làm thương mại điện tử. Từ đó, các lãnh đạo địa phương sẽ kể câu chuyện về nông sản, vùng nguyên liệu của mình và tạo được niềm tin để cho người tiêu dùng\", ông nói.', '2025-07-24 04:07:09', '2025-07-24 04:07:25', 'ACTIVE', 'VN Express'),
(40, 40, 2, 'gjh', 'hghfjfdh', '2025-07-25 13:22:50', NULL, 'DRAFT', 'hu');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blogcategory`
--

CREATE TABLE `blogcategory` (
  `blogcategoryid` bigint(20) NOT NULL,
  `categoryname` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `blogcategory`
--

INSERT INTO `blogcategory` (`blogcategoryid`, `categoryname`, `description`) VALUES
(1, 'Kinh nghiệm', 'Chia sẻ kỹ thuật canh tác hiệu quả'),
(2, 'Tin tức', 'Tin tức thị trường nông sản mới nhất'),
(3, 'Góc nhìn', 'Phân tích và bình luận về ngành nông nghiệp'),
(4, 'Xu hướng', 'Các xu hướng phát triển nông nghiệp hiện đại'),
(5, 'Công nghệ', 'Cập nhật công nghệ nông nghiệp thông minh'),
(6, 'Giải pháp', 'Giải pháp thực tiễn nâng cao năng suất'),
(7, 'Hỏi đáp', 'Kênh tương tác và giải đáp thắc mắc cho nông dân');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blogdetail`
--

CREATE TABLE `blogdetail` (
  `blogdetailid` int(11) NOT NULL,
  `blogid` int(11) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `detailcontent` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `blogdetail`
--

INSERT INTO `blogdetail` (`blogdetailid`, `blogid`, `thumbnail`, `detailcontent`) VALUES
(1, 1, '1.jpg', 'Chi tiết cách trồng rau sạch trong chậu...'),
(2, 2, '2.jpg', 'Phân tích chi tiết các phương pháp chăn nuôi bền vững...'),
(3, 3, '3.jpg', 'Hướng dẫn từng bước làm đất chuẩn bị mùa vụ...'),
(4, 4, '4.jpg', 'Tác động cụ thể của thời tiết đến cây trồng...'),
(5, 5, '5.jpg', 'Công thức làm phân bón tự nhiên từ rác thải hữu cơ...'),
(6, 6, '6.jpg', 'Phân tích xu hướng hạt giống mới trên thị trường...'),
(7, 7, '7.jpg', 'Hướng dẫn bảo quản trái cây tươi lâu tại nhà...'),
(8, 8, '8.jpg', 'Ứng dụng công nghệ tưới tiêu tự động trong nông nghiệp...'),
(9, 9, '9.jpg', 'Ưu điểm và kinh nghiệm thực tế trồng cây lâu năm...'),
(10, 10, '10.jpg', 'Dự báo giá cả và phân tích thị trường nông sản cuối năm...'),
(14, 19, 'a593f9a4-4cda-4115-8eb9-b04788dd07c0_anh-nen-2k-dep-nhat_094443817.jpg', 'Nội dung chi tiết mô tả thêm blog.'),
(15, 20, '6135eaa9-5d7e-43ce-89e5-f94b9d611949_piclumenmarquee-06.webp', 'Nội dung chi tiết mô tả thêm blog.'),
(16, 21, 'f6c88b1c-4f09-4926-b4aa-dc7437c72a28_piclumenmarquee-06.webp', 'Nội dung chi tiết mô tả thêm blog.'),
(17, 22, 'b688607e-6f94-42bf-8b79-a663c402516b_piclumenmarquee-06.webp', 'Nội dung chi tiết mô tả thêm blog.'),
(18, 23, 'd9464815-d9ed-44f8-8612-ac4ad9307187_piclumenmarquee-06.webp', 'Nội dung chi tiết mô tả thêm blog.'),
(22, 27, 'f3720f1f-6791-481a-89d2-d8e5a0ccf3b7_15.jpg', 'Nội dung chi tiết mô tả thêm blog.'),
(23, 28, 'df2e07f7-50a6-4a1e-8ed8-38533056e88f_anh-nnthok-1663843300827438862164.webp', 'Nội dung chi tiết mô tả thêm blog.'),
(24, 29, 'acb996bd-7053-47cb-8e36-62f645a19622_q4_3.jpg', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(25, 30, 'ef893e7f-00df-4aa1-892c-728fc04c5786_OIP.webp', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(26, 31, 'da33411c-459b-481d-ab70-27b18a470aa5_OIP%20(3).webp', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(27, 32, '750ccc7c-d154-4f93-b0cf-60ab49e32599_OIP (1).webp', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(28, 33, '0c518c12-1613-4836-b082-e6d4d193f1d6_OIP (7).webp', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(29, 34, '0c4c4ecc-17e4-4d74-b60f-b00c7799d803_OIP (2).webp', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(30, 35, 'a7d548ea-03c8-4611-abc8-ad0ccb5bfefa_OIP (6).webp', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(31, 36, '8fb4b3d8-60a9-44a4-8109-ff6ec31e4602_OIP.jpg', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(32, 37, 'c08889a3-780f-4690-8f4e-3c689dfddda7_Screenshot%202025-07-24%20035838.png', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(33, 38, '12d2be5d-c92f-4958-be70-1094675c8681_Screenshot 2025-07-24 040149.png', 'Nội dung chi tiết mặc định hoặc để rỗng'),
(34, 39, '163ad3ea-7ad2-4a32-8b3c-7a23cecffce0_Screenshot%202025-07-24%20040638.png', 'Nội dung chi tiết mô tả thêm blog.'),
(35, 40, '7a7f4806-d17a-4fa0-985e-f1ffc791c57a_NguyeenXuanChien_HE181121_PT2.jpg', 'Nội dung chi tiết mô tả thêm blog.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `CartID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `createdat` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `CategoryID` int(11) NOT NULL,
  `CategoryName` varchar(100) DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `imageurl` varchar(500) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`CategoryID`, `CategoryName`, `Description`, `status`, `imageurl`, `image_url`) VALUES
(1, 'Rau lá (xà lách, rau muống…)', '300g–500g', 'ACTIVE', '7f227533-527b-44de-a675-38bf893dd166.20190425063710470758rauxanhmax-800x8001-164247973908380094864.jpg', NULL),
(2, 'Củ (khoai, cà rốt, củ cải…)', '500g–1kg', 'ACTIVE', '735bd38c-3473-44ed-8178-ca1baafd492c.cam-nang-phan-biet-tat-tan-tat-cac-loai-cu-ngoai-cho-cho-co-nang-vung-ve-202011231624332434.jpg', NULL),
(3, 'Trái cây (táo, chuối, cam…)', '1kg–2kg', 'ACTIVE', '8051cf52-a8fc-4b9f-b559-5bcbc07104db.photo-1-1528103764161215753461.webp', NULL),
(4, 'Gia vị tươi (gừng, tỏi, hành…)', '100g–300g', 'ACTIVE', '93ac8792-c816-416f-8c68-6285b23dceb8.gia-vi-thai-4.jpg', NULL),
(5, 'Đậu hạt khô', '250g–500g', 'ACTIVE', '3e62f4e9-2dbe-4929-98b0-114f5f7561d5.cach-bao-quan-cac-loai-dau-hat-de-duoc-lau-khong-bi-moi-mot-ghe-tham-202206041222085345.jpg', NULL),
(6, 'Gạo', '2kg–10kg', 'ACTIVE', 'd0f827bb-ff91-4517-b917-012d9d0f1015.20200406_155527_324743_gao_max_1800x1800_jpg_df5a54c3a9.jpg', NULL),
(7, 'Mì, bún khô', '400g–900g', 'ACTIVE', 'fed9d46e-4a21-4548-a8dd-e0ad5a0f5a81.1-1690769279-899-width800height500.jpg', NULL),
(8, 'Sữa tươi', '180ml–1l', 'ACTIVE', '99f528d5-c5d5-48e0-b450-3e2683d91fe8.huong-sua-tuoi.jpg', NULL),
(9, 'Dầu ăn', '250ml–2l', 'ACTIVE', 'd8783c35-1a93-4d33-a741-e701c08fda8d.dau-va-mo-dong-vat-nen-su-dung-thu-nao-16332371228641361194566.jpg', NULL),
(10, 'Nước mắm', '500ml–750ml', 'ACTIVE', 'a6c06bf7-08e0-4546-a1d4-b0bfd017d609.cach_su_dung_nuoc_mam_1.webp', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categoryweight`
--

CREATE TABLE `categoryweight` (
  `CategoryWeightID` int(11) NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `weight` double DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categoryweight`
--

INSERT INTO `categoryweight` (`CategoryWeightID`, `CategoryID`, `weight`, `unit`) VALUES
(1, 1, 300, 'g'),
(2, 1, 500, 'g'),
(3, 1, 750, 'g'),
(4, 2, 500, 'g'),
(5, 2, 1000, 'g'),
(6, 2, 1500, 'g'),
(7, 3, 1000, 'g'),
(8, 3, 1500, 'g'),
(9, 3, 2000, 'g'),
(10, 4, 100, 'g'),
(11, 4, 300, 'g'),
(12, 4, 500, 'g'),
(13, 5, 250, 'g'),
(14, 5, 500, 'g'),
(15, 5, 750, 'g'),
(16, 6, 300, 'g'),
(17, 6, 1000, 'g'),
(18, 6, 5, 'kg'),
(19, 7, 400, 'g'),
(20, 7, 800, 'g'),
(21, 7, 1200, 'g'),
(22, 8, 180, 'ml'),
(23, 8, 500, 'ml'),
(24, 8, 1000, 'ml'),
(25, 9, 250, 'ml'),
(26, 9, 1000, 'ml'),
(27, 9, 2000, 'ml'),
(28, 10, 500, 'ml'),
(29, 10, 750, 'ml'),
(30, 10, 1000, 'ml'),
(31, 3, 500, 'g'),
(32, 5, 100, 'g'),
(33, 5, 300, 'g'),
(34, 7, 300, 'g'),
(35, 7, 1000, 'g'),
(36, 7, 5, 'kg'),
(37, 8, 400, 'g'),
(38, 8, 800, 'g'),
(39, 8, 1200, 'g'),
(40, 9, 180, 'ml'),
(41, 9, 500, 'ml'),
(42, 10, 250, 'ml'),
(43, 10, 2000, 'ml'),
(44, 3, 1, 'kg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `commentblog`
--

CREATE TABLE `commentblog` (
  `commentblogid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `blogid` int(11) NOT NULL,
  `commenttext` text DEFAULT NULL,
  `likes` int(11) DEFAULT 0,
  `dislikes` int(11) DEFAULT 0,
  `status` varchar(50) DEFAULT 'Active',
  `createdat` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `commentproduct`
--

CREATE TABLE `commentproduct` (
  `CommentProductID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `CommentText` text DEFAULT NULL,
  `Dislikes` int(11) DEFAULT 0,
  `Status` varchar(50) DEFAULT NULL,
  `Rating` int(11) DEFAULT 0,
  `CreatedAt` datetime DEFAULT current_timestamp(),
  `Likes` int(11) DEFAULT 0,
  `productid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contactus`
--

CREATE TABLE `contactus` (
  `contactusid` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `device`
--

CREATE TABLE `device` (
  `DeviceID` int(11) NOT NULL,
  `WarehouseID` int(11) NOT NULL,
  `DeviceName` varchar(255) NOT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `forgot_password`
--

CREATE TABLE `forgot_password` (
  `fpid` int(11) NOT NULL,
  `expiration_time` datetime(6) NOT NULL,
  `otp` int(11) NOT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `gallery`
--

CREATE TABLE `gallery` (
  `galleryid` bigint(20) NOT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `gallery`
--

INSERT INTO `gallery` (`galleryid`, `ProductID`, `imageurl`) VALUES
(24, 3, '8d52f2c3-e588-4192-b1e3-97d82cb76c11.jpg'),
(25, 3, '5d16b767-c782-4a44-a8e3-52c204100600.jpg'),
(26, 5, '37b6bb50-537c-4fd9-92b1-4304d04e7115.jpg'),
(27, 5, '56af1880-3bc9-4439-94c2-dc7b344af158.jpg'),
(28, 7, 'd00d48d5-e536-46ae-b47c-21fe6b29e8ec.jpg'),
(29, 7, '42043b64-9676-4e18-9a83-88a8ec7b1f2f.jpg'),
(30, 9, 'ed893729-5407-4a52-bda6-57b6d86be54b.jpg'),
(31, 9, 'a85e5932-1103-40b5-be59-daebfa8663e1.jpg'),
(32, 11, 'd8e0d128-b8cc-48c7-b4dc-86e56703dcd4.png'),
(33, 11, '5ae2ad6e-10d7-4c4f-b11d-6b956e1b7b75.png'),
(34, 12, 'f9c05feb-9f1c-4533-86e7-ca1bb3326fcd.png'),
(35, 12, 'a009cbd2-be54-4149-89c5-84c42987c388.png'),
(36, 13, '73049fe8-0362-402b-8080-d2bdc0f40f6f.jpg'),
(37, 13, '1887a865-e8da-41c3-a9a7-a288c396f094.png'),
(38, 14, '50183267-1544-4a54-98cc-741e866ef986.png'),
(39, 14, '48595c96-cc00-4e5c-ae15-278d7ca98507.jpg'),
(40, 15, 'e2ffed64-868e-4abc-8706-186efec88d5b.png'),
(41, 15, '5a0a5f7b-352a-4a97-96fe-c129233920a1.png'),
(42, 16, '1731c146-c619-4e0e-93d5-c3519d2a15ce.png'),
(43, 16, '7840f505-6335-42c6-8613-f8510af3f0dd.png'),
(44, 16, 'f47e5e6a-980e-4fa3-8d34-50f14c9b29fd.png'),
(45, 17, 'e5c54540-f3bb-46d8-b604-4801318890d7.png'),
(46, 17, '18645b57-1308-4968-888e-4ceb49a80e5a.png'),
(47, 17, '7733effa-6fe4-423d-b633-6a8c3a3154d3.png'),
(48, 18, '12a40c1e-3b15-4171-92e8-c5a31870e4eb.png'),
(49, 18, 'ce2f8c2f-fa33-4fbe-811c-fb3d15450e66.png'),
(50, 18, 'a0ccede2-63fe-4e7d-9b40-190509112614.png'),
(51, 19, '8b41de82-0f69-4c56-a4c7-40005b56b0c3.png'),
(52, 19, '7a3ad0ce-15c8-4654-a93e-d6d1334405a0.png'),
(53, 20, '25f74659-53a3-47c7-9901-495e2aa32b26.png'),
(54, 20, '3d5c07c5-069f-4866-b530-44549a1b2af3.png'),
(55, 2, 'e853f803-1b6f-4061-aefe-33496e832d0b.jpg'),
(56, 4, 'afd786a2-ab1a-41cc-8d55-8c95ecc07153.jpg'),
(57, 6, 'bf491609-41e5-42fb-8d9e-cddc6798046f.jpg'),
(58, 8, '38aace55-b826-43c2-b2e0-683d40d19b14.jpg'),
(59, 10, '584dbd2f-aa00-4655-a3b7-cba1c2fd6f77.jpg'),
(60, 41, '49973682-07bb-48ab-9122-c0926ea6cccf.jpg'),
(62, 1, 'e596743a-73df-4e49-aaff-c62ad947e8bc.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `inventory`
--

CREATE TABLE `inventory` (
  `InventoryID` int(11) NOT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
  `WarehouseID` int(11) DEFAULT NULL,
  `BatchID` int(11) DEFAULT NULL,
  `QuantityInStock` int(11) DEFAULT 0,
  `LastUpdated` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `myaddressbook`
--

CREATE TABLE `myaddressbook` (
  `addressbookid` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `settoaddress` varchar(255) DEFAULT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `myaddressbook`
--

INSERT INTO `myaddressbook` (`addressbookid`, `address`, `createdat`, `email`, `fullname`, `note`, `phone`, `settoaddress`, `userid`) VALUES
(1, 'Hà Nội, HUYỆN THANH OAI, XÃ MỸ HƯNG', '2025-08-01 05:36:41.004000', 'daohuyhoang507@gmail.com', 'Tk_Hoàng_FPT', '...', '0912345678', 'Hạ Bằng', 48),
(2, 'Hà Nội, HUYỆN BA VÌ, XÃ CỔ ĐÔ', '2025-08-03 03:19:11.000000', 'hoangbavi123@gmail.com', 'Đào Huy Hoàng', 'Hoàng ba vì pro', '0967125471', 'số nhà 95', 56),
(3, 'Hà Nội, HUYỆN HOÀI ĐỨC, XÃ MINH KHAI', '2025-08-03 03:23:12.000000', 'hoangbavi123@gmail.com', 'Đào Huy Hoàng', 'Hoài đức xin chào', '0967125471', 'số nhà 951', 55),
(4, 'Hưng Yên, HUYỆN VĂN LÂM, XÃ LẠC ĐẠO', '2025-08-03 03:32:14.000000', 'giang123@gmail.com', 'Đức Giang', 'Giang Nè', '0961245141', '123 đường hưng đạo ', 57),
(5, 'Hưng Yên, THỊ XÃ MỸ HÀO, XÃ NHÂN HÒA', '2025-08-03 03:36:13.000000', 'chien123@gmail.com', 'Nguyễn Xuân Chiến', 'chiến đây', '0987881241', '123 đường bà ', 58),
(6, 'Thanh Hóa, HUYỆN NHƯ THANH, XÃ XUÂN PHÚC', '2025-08-03 03:45:56.000000', 'nhatquang182@gmail.com', 'Dao Quang', 'quang', '0987881241', '12 Tân lập', 59);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `notifications`
--

INSERT INTO `notifications` (`id`, `content`, `created_at`, `status`, `title`, `url`, `userid`) VALUES
(1, 'Người dùng Nguyễn Xuân Chiến đã gửi yêu cầu trở thành người bán.', '2025-08-02 04:40:29.617351', 'SEEN', 'Yêu cầu trở thành người bán', '/admin/seller-applications/4/details', 45),
(2, 'Người dùng Nguyễn Xuân Chiến đã gửi yêu cầu trở thành người bán.', '2025-08-02 05:00:19.452293', 'SEEN', 'Yêu cầu trở thành người bán', '/admin/seller-applications/1/details', 45),
(3, 'Người dùng Nguyễn Xuân Chiến đã gửi yêu cầu trở thành người bán.', '2025-08-02 05:16:25.456387', 'SEEN', 'Yêu cầu trở thành người bán', '/admin/seller-applications/1/details', 45),
(4, 'Người dùng Đào Huy Hoàng vừa đặt đơn hàng #959009', '2025-08-03 03:19:19.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/3/details', 45),
(5, 'Người dùng Đào Huy Hoàng vừa đặt đơn hàng #10370', '2025-08-03 03:20:10.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/4/details', 45),
(6, 'Người dùng Đào Huy Hoàng vừa đặt đơn hàng #60665', '2025-08-03 03:21:00.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/5/details', 45),
(7, 'Người dùng Huy Hoàng vừa đặt đơn hàng #196519', '2025-08-03 03:23:16.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/6/details', 45),
(8, 'Người dùng Huy Hoàng vừa đặt đơn hàng #257286', '2025-08-03 03:24:17.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/7/details', 45),
(9, 'Người dùng Đức Giang vừa đặt đơn hàng #752465', '2025-08-03 03:32:32.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/8/details', 45),
(10, 'Người dùng Đức Giang vừa đặt đơn hàng #814218', '2025-08-03 03:33:34.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/9/details', 45),
(11, 'Người dùng Đức Giang vừa đặt đơn hàng #851234', '2025-08-03 03:34:11.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/10/details', 45),
(12, 'Người dùng Nguyễn Xuân Chiến vừa đặt đơn hàng #977186', '2025-08-03 03:36:17.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/11/details', 45),
(13, 'Người dùng Nguyễn Xuân Chiến vừa đặt đơn hàng #10922', '2025-08-03 03:36:50.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/12/details', 45),
(14, 'Người dùng Nguyễn Xuân Chiến vừa đặt đơn hàng #46836', '2025-08-03 03:37:26.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/13/details', 45),
(15, 'Người dùng Nhật Quang vừa đặt đơn hàng #565520', '2025-08-03 03:46:05.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/14/details', 45),
(16, 'Người dùng Nhật Quang vừa đặt đơn hàng #641069', '2025-08-03 03:47:21.000000', 'UNSEEN', 'Đơn hàng mới', '/admin/orders/15/details', 45);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

CREATE TABLE `order` (
  `orderid` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `discount_amount` decimal(15,2) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `final_amount` decimal(15,2) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `ordercode` varchar(255) NOT NULL,
  `orderdate` datetime DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `status` varchar(50) NOT NULL,
  `totalamount` decimal(15,2) NOT NULL,
  `voucher_code` varchar(50) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  `voucher_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order`
--

INSERT INTO `order` (`orderid`, `address`, `discount_amount`, `email`, `final_amount`, `fullname`, `note`, `ordercode`, `orderdate`, `phone`, `status`, `totalamount`, `voucher_code`, `userid`, `voucher_id`) VALUES
(1, 'Hà Nội, HUYỆN THANH OAI, XÃ MỸ HƯNG', 0.00, 'daohuyhoang507@gmail.com', 15000.00, 'Tk_Hoàng_FPT', '... (Hạ Bằng)', '407001', '2025-08-01 05:36:47', '0912345678', 'DELIVERED', 15000.00, '', 48, NULL),
(2, 'Hà Nội, HUYỆN THANH OAI, XÃ MỸ HƯNG', 0.00, 'daohuyhoang507@gmail.com', 8000.00, 'Tk_Hoàng_FPT', '... (Hạ Bằng)', '945383', '2025-08-01 06:19:05', '0912345678', 'STOCKOUT', 8000.00, '', 48, NULL),
(3, 'Hà Nội, HUYỆN BA VÌ, XÃ CỔ ĐÔ', 0.00, 'hoangbavi123@gmail.com', 2436000.00, 'Đào Huy Hoàng', 'Hoàng ba vì pro (số nhà 95)', '959009', '2025-05-28 03:19:19', '0967125471', 'STOCKOUT', 2436000.00, '', 56, NULL),
(4, 'Hà Nội, HUYỆN BA VÌ, XÃ CỔ ĐÔ', 0.00, 'hoangbavi123@gmail.com', 2245000.00, 'Đào Huy Hoàng', 'Hoàng ba vì pro (số nhà 95)', '10370', '2025-07-06 03:20:10', '0967125471', 'STOCKOUT', 2245000.00, '', 56, NULL),
(5, 'Hà Nội, HUYỆN BA VÌ, XÃ CỔ ĐÔ', 0.00, 'hoangbavi123@gmail.com', 1730000.00, 'Đào Huy Hoàng', 'Hoàng ba vì pro (số nhà 95)', '60665', '2025-06-03 03:21:00', '0967125471', 'STOCKOUT', 1730000.00, '', 56, NULL),
(6, 'Hà Nội, HUYỆN HOÀI ĐỨC, XÃ MINH KHAI', 0.00, 'hoangbavi123@gmail.com', 2266000.00, 'Đào Huy Hoàng', 'Hoài đức xin chào (số nhà 951)', '196519', '2025-07-02 03:23:16', '0967125471', 'STOCKOUT', 2266000.00, '', 55, NULL),
(7, 'Hà Nội, HUYỆN HOÀI ĐỨC, XÃ MINH KHAI', 0.00, 'hoangbavi123@gmail.com', 1780000.00, 'Đào Huy Hoàng', 'Hoài đức xin chào (số nhà 951)', '257286', '2025-08-03 03:24:17', '0967125471', 'STOCKOUT', 1780000.00, '', 55, NULL),
(8, 'Hưng Yên, HUYỆN VĂN LÂM, XÃ LẠC ĐẠO', 0.00, 'giang123@gmail.com', 1090000.00, 'Đức Giang', 'Giang Nè (123 đường hưng đạo )', '752465', '2025-08-03 03:32:32', '0961245141', 'STOCKOUT', 1090000.00, '', 57, NULL),
(9, 'Hưng Yên, HUYỆN VĂN LÂM, XÃ LẠC ĐẠO', 0.00, 'giang123@gmail.com', 1232000.00, 'Đức Giang', 'Giang Nè (123 đường hưng đạo )', '814218', '2025-05-28 03:33:34', '0961245141', 'STOCKOUT', 1232000.00, '', 57, NULL),
(10, 'Hưng Yên, HUYỆN VĂN LÂM, XÃ LẠC ĐẠO', 0.00, 'giang123@gmail.com', 1592000.00, 'Đức Giang', 'Giang Nè (123 đường hưng đạo )', '851234', '2025-06-07 03:34:11', '0961245141', 'STOCKOUT', 1592000.00, '', 57, NULL),
(11, 'Hưng Yên, THỊ XÃ MỸ HÀO, XÃ NHÂN HÒA', 0.00, 'chien123@gmail.com', 1149000.00, 'Nguyễn Xuân Chiến', 'chiến đây (123 đường bà )', '977186', '2025-07-03 03:36:17', '0987881241', 'STOCKOUT', 1149000.00, '', 58, NULL),
(12, 'Hưng Yên, THỊ XÃ MỸ HÀO, XÃ NHÂN HÒA', 0.00, 'chien123@gmail.com', 1185000.00, 'Nguyễn Xuân Chiến', 'chiến đây (123 đường bà )', '10922', '2025-08-03 03:36:50', '0987881241', 'STOCKOUT', 1185000.00, '', 58, NULL),
(13, 'Hưng Yên, THỊ XÃ MỸ HÀO, XÃ NHÂN HÒA', 0.00, 'chien123@gmail.com', 2132000.00, 'Nguyễn Xuân Chiến', 'chiến đây (123 đường bà )', '46836', '2025-07-02 03:37:26', '0987881241', 'STOCKOUT', 2132000.00, '', 58, NULL),
(14, 'Thanh Hóa, HUYỆN NHƯ THANH, XÃ XUÂN PHÚC', 0.00, 'nhatquang182@gmail.com', 1131000.00, 'Dao Quang', 'quang (12 Tân lập)', '565520', '2025-07-22 03:46:05', '0987881241', 'STOCKOUT', 1131000.00, '', 59, NULL),
(15, 'Thanh Hóa, HUYỆN NHƯ THANH, XÃ XUÂN PHÚC', 0.00, 'nhatquang182@gmail.com', 2182000.00, 'Dao Quang', 'quang (12 Tân lập)', '641069', '2025-06-03 03:47:21', '0987881241', 'STOCKOUT', 2182000.00, '', 59, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetail`
--

CREATE TABLE `orderdetail` (
  `orderdetailid` bigint(20) NOT NULL,
  `price` decimal(15,2) NOT NULL,
  `productdetailid` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `orderid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orderdetail`
--

INSERT INTO `orderdetail` (`orderdetailid`, `price`, `productdetailid`, `quantity`, `orderid`) VALUES
(1, 15000.00, 7, 1, 1),
(2, 8000.00, 19, 1, 2),
(3, 228000.00, 1, 19, 3),
(4, 1200000.00, 36, 15, 3),
(5, 1008000.00, 42, 21, 3),
(6, 345000.00, 7, 23, 4),
(7, 700000.00, 30, 40, 4),
(8, 1200000.00, 33, 16, 4),
(9, 408000.00, 1, 34, 5),
(10, 728000.00, 23, 56, 5),
(11, 594000.00, 26, 54, 5),
(12, 408000.00, 1, 34, 6),
(13, 1044000.00, 20, 87, 6),
(14, 814000.00, 60, 22, 6),
(15, 840000.00, 18, 30, 7),
(16, 460000.00, 37, 20, 7),
(17, 190000.00, 40, 10, 7),
(18, 290000.00, 49, 20, 7),
(19, 252000.00, 1, 21, 8),
(20, 448000.00, 4, 32, 8),
(21, 114000.00, 28, 12, 8),
(22, 276000.00, 37, 12, 8),
(23, 384000.00, 20, 32, 9),
(24, 288000.00, 22, 32, 9),
(25, 560000.00, 46, 40, 9),
(26, 336000.00, 4, 24, 10),
(27, 645000.00, 27, 43, 10),
(28, 611000.00, 57, 13, 10),
(29, 800000.00, 36, 10, 11),
(30, 225000.00, 50, 10, 11),
(31, 124000.00, 53, 4, 11),
(32, 688000.00, 21, 43, 12),
(33, 497000.00, 25, 71, 12),
(34, 680000.00, 16, 40, 13),
(35, 330000.00, 32, 10, 13),
(36, 1122000.00, 41, 34, 13),
(37, 480000.00, 7, 32, 14),
(38, 372000.00, 20, 31, 14),
(39, 279000.00, 22, 31, 14),
(40, 72000.00, 1, 6, 15),
(41, 672000.00, 21, 42, 15),
(42, 288000.00, 22, 32, 15),
(43, 480000.00, 34, 20, 15),
(44, 670000.00, 44, 20, 15);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderreview`
--

CREATE TABLE `orderreview` (
  `orderreviewid` bigint(20) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `ordercode` varchar(255) NOT NULL,
  `rating` int(11) DEFAULT 0,
  `updatetimeform` varchar(255) NOT NULL,
  `updatetimeimage` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL,
  `updatedatetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orderreview`
--

INSERT INTO `orderreview` (`orderreviewid`, `image`, `message`, `ordercode`, `rating`, `updatetimeform`, `updatetimeimage`, `userid`, `updatedatetime`) VALUES
(1, '', '', '407001', 0, '0', '0', 48, NULL),
(2, '', '', '945383', 0, '0', '0', 48, NULL),
(3, '', '', '959009', 0, '0', '0', 56, NULL),
(4, '', '', '10370', 0, '0', '0', 56, NULL),
(5, '', '', '60665', 0, '0', '0', 56, NULL),
(6, '', '', '196519', 0, '0', '0', 55, NULL),
(7, '', '', '257286', 0, '0', '0', 55, NULL),
(8, '', '', '752465', 0, '0', '0', 57, NULL),
(9, '', '', '814218', 0, '0', '0', 57, NULL),
(10, '', '', '851234', 0, '0', '0', 57, NULL),
(11, '', '', '977186', 0, '0', '0', 58, NULL),
(12, '', '', '10922', 0, '0', '0', 58, NULL),
(13, '', '', '46836', 0, '0', '0', 58, NULL),
(14, '', '', '565520', 0, '0', '0', 59, NULL),
(15, '', '', '641069', 0, '0', '0', 59, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `PaymentDate` datetime DEFAULT current_timestamp(),
  `Amount` decimal(15,2) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `ProductID` int(11) NOT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`ProductID`, `CategoryID`, `ProductName`, `description`, `Status`) VALUES
(1, 1, 'Rau xà lách', 'Rau xà lách tươi, dùng cho món salad hoặc cuốn gỏi.', 'ACTIVE'),
(2, 1, 'Rau muống', 'Rau muống xanh giòn, thích hợp xào tỏi hoặc luộc.', 'ACTIVE'),
(3, 2, 'Khoai lang tím', 'Khoai lang ngọt bùi, tốt cho tiêu hóa.', 'ACTIVE'),
(4, 2, 'Cà rốt Đà Lạt', 'Cà rốt tươi, màu đẹp, giàu beta-carotene.', 'OUT_OF_STOCK'),
(5, 3, 'Chuối cau', 'Chuối chín tự nhiên, vị ngọt thanh.', 'INACTIVE'),
(6, 3, 'Cam sành', 'Cam vỏ xanh, mọng nước, giàu vitamin C.', 'ACTIVE'),
(7, 4, 'Tỏi ta', 'Tỏi ta tép nhỏ, thơm, chống oxy hóa.', 'ACTIVE'),
(8, 4, 'Hành tím', 'Hành tím tươi, dùng cho món kho, xào.', 'ACTIVE'),
(9, 5, 'Đậu xanh nguyên hạt', 'Đậu xanh sạch, dùng nấu chè hoặc làm nhân bánh.', 'ACTIVE'),
(10, 5, 'Đậu đỏ hạt nhỏ', 'Đậu đỏ dùng nấu chè hoặc súp ngọt.', 'ACTIVE'),
(11, 6, 'Gạo ST25', 'Gạo thơm dẻo đạt giải ngon nhất thế giới.', 'ACTIVE'),
(12, 6, 'Gạo tám thơm', 'Gạo trắng, hạt dài, mùi thơm dịu nhẹ.', 'ACTIVE'),
(13, 7, 'Bún gạo khô', 'Bún gạo đóng gói, dùng chế biến bún bò, bún mắm.', 'ACTIVE'),
(14, 7, 'Mì chũ', 'Mì chũ Bắc Giang, sợi mềm, không chất bảo quản.', 'ACTIVE'),
(15, 8, 'Sữa tươi không đường', 'Sữa thanh trùng nguyên chất, giữ trọn dưỡng chất.', 'ACTIVE'),
(16, 8, 'Sữa tươi có đường', 'Sữa tiệt trùng, phù hợp khẩu vị người Việt.', 'ACTIVE'),
(17, 9, 'Dầu đậu nành', 'Dầu ăn nhẹ, ít cholesterol, dùng chiên xào.', 'ACTIVE'),
(18, 9, 'Dầu hướng dương', 'Dầu thực vật cao cấp, giàu vitamin E.', 'ACTIVE'),
(19, 10, 'Nước mắm Phú Quốc', 'Nước mắm truyền thống, độ đạm cao.', 'ACTIVE'),
(20, 10, 'Nước mắm nhĩ cá cơm', 'Mắm nhĩ nguyên chất, đậm đà vị cá cơm.', 'ACTIVE'),
(41, 3, 'Kiwi Mỹ (Đình)', 'Kiwi xanh đặc biệt giàu chất chống oxy hóa và rất tốt cho hệ tiêu hóa, trong khi kiwi vàng có khả năng cải thiện sức khỏe tim mạch và hỗ trợ hệ miễn dịch.', 'ACTIVE');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productbatch`
--

CREATE TABLE `productbatch` (
  `BatchID` int(11) NOT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
  `manufacturedate` date DEFAULT NULL,
  `importedquantity` int(11) DEFAULT NULL,
  `soldquantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `productbatch`
--

INSERT INTO `productbatch` (`BatchID`, `ProductDetailID`, `manufacturedate`, `importedquantity`, `soldquantity`) VALUES
(1, 1, '2025-05-23', 50, 29),
(2, 2, '2025-08-01', 25, 0),
(3, 3, '2025-08-02', 60, 0),
(4, 4, '2025-07-10', 80, 56),
(5, 5, '2025-08-01', 45, 0),
(6, 6, '2025-07-27', 90, 0),
(7, 7, '2025-07-15', 55, 55),
(8, 8, '2025-06-10', 70, 0),
(9, 9, '2025-06-07', 65, 0),
(10, 10, '2025-07-05', 85, 0),
(11, 11, '2025-07-04', 40, 0),
(12, 12, '2025-07-12', 95, 0),
(13, 13, '2025-07-26', 50, 0),
(14, 14, '2025-07-13', 60, 0),
(15, 15, '2025-07-18', 70, 0),
(16, 16, '2025-07-20', 45, 40),
(17, 17, '2025-07-26', 80, 0),
(18, 18, '2025-07-19', 55, 30),
(19, 19, '2025-07-25', 65, 0),
(20, 20, '2025-04-04', 75, 75),
(21, 21, '2025-04-04', 85, 85),
(22, 22, '2025-07-15', 50, 45),
(23, 23, '2025-05-09', 60, 56),
(24, 24, '2025-07-11', 70, 0),
(25, 25, '2025-06-02', 80, 71),
(26, 26, '2025-06-15', 90, 0),
(27, 27, '2025-06-28', 45, 0),
(28, 28, '2025-07-10', 55, 0),
(29, 29, '2025-06-05', 65, 0),
(30, 30, '2025-05-31', 75, 40),
(31, 31, '2025-07-20', 85, 0),
(32, 32, '2025-06-25', 50, 10),
(33, 33, '2025-05-30', 60, 16),
(34, 34, '2025-07-05', 70, 20),
(35, 35, '2025-06-10', 80, 0),
(36, 36, '2025-05-20', 90, 25),
(37, 37, '2025-07-15', 45, 32),
(38, 38, '2025-06-05', 55, 0),
(39, 39, '2025-05-25', 65, 0),
(40, 40, '2025-07-01', 75, 10),
(41, 41, '2025-06-15', 85, 34),
(42, 42, '2025-05-10', 50, 21),
(43, 43, '2025-07-20', 60, 0),
(44, 44, '2025-06-25', 70, 20),
(45, 45, '2025-05-30', 80, 0),
(46, 46, '2025-07-05', 90, 40),
(47, 47, '2025-06-10', 45, 0),
(48, 48, '2025-05-20', 55, 0),
(49, 49, '2025-07-15', 65, 20),
(50, 50, '2025-06-05', 75, 10),
(51, 51, '2025-05-25', 85, 0),
(52, 52, '2025-07-01', 50, 0),
(53, 53, '2025-06-15', 60, 4),
(54, 54, '2025-05-10', 70, 0),
(55, 55, '2025-07-20', 80, 0),
(56, 56, '2025-06-25', 90, 0),
(57, 57, '2025-05-30', 45, 13),
(58, 58, '2025-07-05', 55, 0),
(59, 59, '2025-06-10', 65, 0),
(60, 60, '2025-05-20', 75, 22),
(61, 1, '2025-05-16', 85, 85),
(62, 2, '2025-08-02', 50, 0),
(63, 3, '2025-07-18', 60, 0),
(64, 4, '2025-08-01', 70, 0),
(65, 5, '2025-08-01', 80, 0),
(66, 6, '2025-07-23', 90, 0),
(67, 7, '2025-07-20', 45, 0),
(68, 8, '2025-06-25', 55, 0),
(69, 9, '2025-06-14', 65, 0),
(70, 10, '2025-07-05', 75, 0),
(71, 11, '2025-06-29', 85, 0),
(72, 12, '2025-08-01', 50, 0),
(73, 13, '2025-07-15', 60, 0),
(74, 14, '2025-08-01', 70, 0),
(75, 15, '2025-07-31', 80, 0),
(76, 16, '2025-08-01', 90, 0),
(77, 17, '2025-08-01', 45, 0),
(78, 18, '2025-08-01', 55, 0),
(79, 19, '2025-07-20', 65, 1),
(80, 20, '2025-06-25', 75, 75),
(81, 21, '2025-08-01', 85, 0),
(82, 22, '2025-05-09', 50, 50),
(83, 23, '2025-07-12', 60, 0),
(84, 24, '2025-07-12', 70, 0),
(85, 25, '2025-07-15', 80, 0),
(86, 26, '2025-06-01', 90, 54),
(87, 27, '2025-05-31', 45, 43),
(88, 28, '2025-07-01', 55, 12),
(89, 29, '2025-06-15', 65, 0),
(90, 30, '2025-05-31', 75, 0),
(91, 31, '2025-07-20', 85, 0),
(92, 32, '2025-06-25', 50, 0),
(93, 33, '2025-05-30', 60, 0),
(94, 34, '2025-07-05', 70, 0),
(95, 35, '2025-06-10', 80, 0),
(96, 36, '2025-05-20', 90, 0),
(97, 37, '2025-07-15', 45, 0),
(98, 38, '2025-06-05', 55, 0),
(99, 39, '2025-05-25', 65, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productdetail`
--

CREATE TABLE `productdetail` (
  `ProductDetailID` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `CategoryWeightID` int(11) DEFAULT NULL,
  `Expiry` int(11) DEFAULT NULL,
  `detailname` varchar(500) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `productdetail`
--

INSERT INTO `productdetail` (`ProductDetailID`, `productid`, `price`, `CategoryWeightID`, `Expiry`, `detailname`, `status`) VALUES
(1, 1, 12000, 1, 1, '300 g', 'ACTIVE'),
(2, 1, 18000, 2, 1, '500 g', 'INACTIVE'),
(3, 1, 25000, 3, 1, '750 g', 'INACTIVE'),
(4, 2, 14000, 1, 1, '300 g', 'ACTIVE'),
(5, 2, 20000, 2, 1, '500 g', 'ACTIVE'),
(6, 2, 27000, 3, 1, '750 g', 'ACTIVE'),
(7, 3, 15000, 4, 3, '500 g', 'ACTIVE'),
(8, 3, 22000, 5, 3, '1000 g', 'ACTIVE'),
(9, 3, 29000, 6, 3, '1500 g', 'ACTIVE'),
(10, 4, 16000, 4, 2, '500 g', 'ACTIVE'),
(11, 4, 24000, 5, 2, '1000 g', 'ACTIVE'),
(12, 4, 30000, 6, 2, '1500 g', 'ACTIVE'),
(13, 5, 18000, 31, 1, '500 g', 'ACTIVE'),
(14, 5, 26000, 7, 1, '1000 g', 'ACTIVE'),
(15, 5, 32000, 8, 1, '1500 g', 'ACTIVE'),
(16, 6, 17000, 7, 1, '1000 g', 'ACTIVE'),
(17, 6, 23000, 8, 1, '1500 g', 'ACTIVE'),
(18, 6, 28000, 9, 1, '2000 g', 'ACTIVE'),
(19, 7, 8000, 10, 2, '100 g', 'ACTIVE'),
(20, 7, 12000, 11, 2, '300 g', 'ACTIVE'),
(21, 7, 16000, 12, 2, '500 g', 'ACTIVE'),
(22, 8, 9000, 10, 2, '100 g', 'ACTIVE'),
(23, 8, 13000, 11, 2, '300 g', 'ACTIVE'),
(24, 8, 17000, 12, 2, '500 g', 'ACTIVE'),
(25, 9, 7000, 32, 3, '100 g', 'ACTIVE'),
(26, 9, 11000, 33, 3, '300 g', 'ACTIVE'),
(27, 9, 15000, 14, 3, '500 g', 'ACTIVE'),
(28, 10, 9500, 32, 3, '100 g', 'ACTIVE'),
(29, 10, 13500, 33, 3, '300 g', 'ACTIVE'),
(30, 10, 17500, 14, 3, '500 g', 'ACTIVE'),
(31, 11, 22000, 16, 24, '300 g', 'ACTIVE'),
(32, 11, 33000, 17, 24, '1000 g', 'ACTIVE'),
(33, 11, 75000, 18, 24, '5 kg', 'ACTIVE'),
(34, 12, 24000, 16, 24, '300 g', 'ACTIVE'),
(35, 12, 36000, 17, 24, '1000 g', 'ACTIVE'),
(36, 12, 80000, 18, 24, '5 kg', 'ACTIVE'),
(37, 13, 23000, 34, 12, '300 g', 'ACTIVE'),
(38, 13, 34000, 35, 12, '1000 g', 'ACTIVE'),
(39, 13, 77000, 36, 12, '5 kg', 'ACTIVE'),
(40, 14, 19000, 19, 12, '400 g', 'ACTIVE'),
(41, 14, 33000, 20, 12, '800 g', 'ACTIVE'),
(42, 14, 48000, 21, 12, '1200 g', 'ACTIVE'),
(43, 15, 19500, 37, 6, '400 g', 'ACTIVE'),
(44, 15, 33500, 38, 6, '800 g', 'ACTIVE'),
(45, 15, 48500, 39, 6, '1200 g', 'ACTIVE'),
(46, 16, 14000, 22, 6, '180 ml', 'ACTIVE'),
(47, 16, 22000, 23, 6, '500 ml', 'ACTIVE'),
(48, 16, 29000, 24, 6, '1000 ml', 'ACTIVE'),
(49, 17, 14500, 40, 24, '180 ml', 'ACTIVE'),
(50, 17, 22500, 41, 24, '500 ml', 'ACTIVE'),
(51, 17, 29500, 26, 24, '1000 ml', 'ACTIVE'),
(52, 18, 16000, 25, 24, '250 ml', 'ACTIVE'),
(53, 18, 31000, 26, 24, '1000 ml', 'ACTIVE'),
(54, 18, 46000, 27, 24, '2000 ml', 'ACTIVE'),
(55, 19, 16500, 42, 70, '250 ml', 'ACTIVE'),
(56, 19, 31500, 30, 70, '1000 ml', 'ACTIVE'),
(57, 19, 47000, 43, 70, '2000 ml', 'ACTIVE'),
(58, 20, 19000, 28, 70, '500 ml', 'ACTIVE'),
(59, 20, 28000, 29, 70, '750 ml', 'ACTIVE'),
(60, 20, 37000, 30, 70, '1000 ml', 'ACTIVE'),
(61, 41, 200000, 44, 1, '1 kg', 'ACTIVE');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `RoleID` int(11) NOT NULL,
  `RoleName` varchar(100) NOT NULL,
  `Status` varchar(20) DEFAULT 'Active',
  `description` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`RoleID`, `RoleName`, `Status`, `description`) VALUES
(1, 'ADMIN', 'Active', NULL),
(2, 'USER', 'Active', NULL),
(3, 'BLOGGER', 'INACTIVE', NULL),
(4, 'SELLER', 'Active', NULL),
(5, 'WAREHOUSE', 'Active', NULL),
(6, 'SHIPPER', 'Active', 'Người giao hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seller_application`
--

CREATE TABLE `seller_application` (
  `id` bigint(20) NOT NULL,
  `contract_expiry_date` date DEFAULT NULL,
  `contract_months` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `cv_image` varchar(255) NOT NULL,
  `status` enum('APPROVED','CANCELLED','COMPLETED','EXPIRED','PENDING','REJECTED') NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `seller_application`
--

INSERT INTO `seller_application` (`id`, `contract_expiry_date`, `contract_months`, `created_at`, `cv_image`, `status`, `user_id`) VALUES
(1, '2025-06-02', 1, '2025-05-02 05:16:25.442357', 'cv.jpg', 'EXPIRED', 48),
(2, NULL, 15, '2025-08-01 06:03:27.666587', 'cv.png', 'REJECTED', 48),
(3, '2027-08-02', 24, '2025-08-01 06:11:25.542905', 'cv.png', 'APPROVED', 48),
(4, NULL, 2, '2025-08-02 04:50:09.723818', 'cv.png', 'CANCELLED', 48);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shipment`
--

CREATE TABLE `shipment` (
  `ShipmentID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `ShipmentDate` datetime DEFAULT NULL,
  `DeliveryAddress` varchar(255) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `soldbyseller`
--

CREATE TABLE `soldbyseller` (
  `id` int(11) NOT NULL,
  `productdetailid` int(11) NOT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `soldbyseller`
--

INSERT INTO `soldbyseller` (`id`, `productdetailid`, `userid`) VALUES
(1, 1, 43),
(2, 2, 43),
(3, 3, 43),
(4, 4, 43),
(5, 5, 43),
(6, 6, 43),
(7, 7, 43),
(8, 8, 43),
(9, 9, 43),
(10, 10, 43),
(11, 11, 43),
(12, 12, 43),
(13, 13, 43),
(14, 14, 43),
(15, 15, 43),
(16, 16, 43),
(17, 17, 43),
(18, 18, 43),
(19, 19, 43),
(20, 20, 43),
(21, 21, 43),
(22, 22, 43),
(23, 23, 43),
(24, 24, 43),
(25, 25, 43),
(26, 26, 43),
(27, 27, 43),
(28, 28, 43),
(29, 29, 43),
(30, 30, 43),
(32, 60, 43),
(33, 59, 43),
(34, 58, 43),
(35, 57, 43),
(36, 56, 43),
(37, 55, 43),
(38, 54, 43),
(39, 53, 43),
(40, 52, 43),
(41, 51, 43),
(42, 50, 43),
(43, 49, 43),
(44, 48, 43),
(45, 47, 43),
(46, 46, 43),
(47, 45, 43),
(48, 44, 43),
(49, 43, 43),
(50, 42, 43),
(51, 41, 43),
(52, 40, 43),
(53, 39, 43),
(54, 38, 43),
(55, 37, 43),
(56, 36, 43),
(57, 35, 43),
(58, 34, 43),
(59, 33, 43),
(60, 32, 43),
(95, 61, 43);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `stockin`
--

CREATE TABLE `stockin` (
  `StockInID` int(11) NOT NULL,
  `SupplierID` int(11) NOT NULL,
  `WarehouseID` int(11) NOT NULL,
  `StockInDate` datetime DEFAULT current_timestamp(),
  `note` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `stockin`
--

INSERT INTO `stockin` (`StockInID`, `SupplierID`, `WarehouseID`, `StockInDate`, `note`) VALUES
(1, 1, 1, '2025-06-20 10:00:00', 'Nhập lô hàng số 1'),
(2, 2, 1, '2025-05-22 12:00:00', 'Nhập lô hàng số 2'),
(3, 3, 1, '2025-05-28 14:00:00', 'Nhập lô hàng số 3'),
(4, 4, 1, '2025-07-08 16:00:00', 'Nhập lô hàng số 4'),
(5, 5, 1, '2025-02-15 09:00:00', 'Nhập lô hàng số 5'),
(6, 6, 1, '2025-03-14 11:00:00', 'Nhập lô hàng số 6'),
(7, 7, 1, '2025-07-11 13:00:00', 'Nhập lô hàng số 7'),
(8, 8, 1, '2025-05-14 15:00:00', 'Nhập lô hàng số 8'),
(9, 1, 1, '2025-06-06 17:00:00', 'Nhập lô hàng số 9'),
(10, 2, 1, '2025-04-25 10:00:00', 'Nhập lô hàng số 10'),
(11, 3, 1, '2025-07-15 12:00:00', 'Nhập lô hàng số 11'),
(12, 4, 1, '2025-05-25 14:00:00', 'Nhập lô hàng số 12'),
(13, 5, 1, '2025-07-17 16:00:00', 'Nhập lô hàng số 13'),
(14, 6, 1, '2025-08-02 09:00:00', 'Nhập lô hàng số 14'),
(15, 7, 1, '2025-07-19 11:00:00', 'Nhập lô hàng số 15'),
(16, 8, 1, '2025-04-23 13:00:00', 'Nhập lô hàng số 16'),
(17, 1, 1, '2025-05-15 15:00:00', 'Nhập lô hàng số 17'),
(18, 2, 1, '2025-03-19 17:00:00', 'Nhập lô hàng số 18'),
(19, 3, 1, '2025-07-12 10:00:00', 'Nhập lô hàng số 19'),
(20, 4, 1, '2025-04-15 12:00:00', 'Nhập lô hàng số 20'),
(21, 5, 1, '2025-04-23 14:00:00', 'Nhập lô hàng số 21'),
(22, 6, 1, '2025-08-02 16:00:00', 'Nhập lô hàng số 22'),
(23, 7, 1, '2025-07-19 09:00:00', 'Nhập lô hàng số 23'),
(24, 8, 1, '2025-07-03 11:00:00', 'Nhập lô hàng số 24'),
(25, 1, 1, '2025-02-12 13:00:00', 'Nhập lô hàng số 25'),
(26, 2, 1, '2025-02-19 15:00:00', 'Nhập lô hàng số 26'),
(27, 3, 1, '2025-08-02 17:00:00', 'Nhập lô hàng số 27'),
(28, 4, 1, '2025-04-18 10:00:00', 'Nhập lô hàng số 28'),
(29, 5, 1, '2025-05-15 12:00:00', 'Nhập lô hàng số 29'),
(30, 6, 1, '2025-06-18 14:00:00', 'Nhập lô hàng số 30'),
(31, 7, 1, '2025-03-13 16:00:00', 'Nhập lô hàng số 31'),
(32, 8, 1, '2025-06-20 09:00:00', 'Nhập lô hàng số 32'),
(33, 1, 1, '2025-02-22 11:00:00', 'Nhập lô hàng số 33'),
(34, 2, 1, '2025-06-22 13:00:00', 'Nhập lô hàng số 34'),
(35, 3, 1, '2025-05-15 15:00:00', 'Nhập lô hàng số 35'),
(36, 4, 1, '2025-06-24 17:00:00', 'Nhập lô hàng số 36'),
(37, 5, 1, '2025-06-25 10:00:00', 'Nhập lô hàng số 37'),
(38, 6, 1, '2025-06-26 12:00:00', 'Nhập lô hàng số 38'),
(39, 7, 1, '2025-04-23 14:00:00', 'Nhập lô hàng số 39');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `stockindetail`
--

CREATE TABLE `stockindetail` (
  `StockInDetailID` int(11) NOT NULL,
  `StockInID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `unitprice` int(11) DEFAULT NULL,
  `BatchID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `stockindetail`
--

INSERT INTO `stockindetail` (`StockInDetailID`, `StockInID`, `Quantity`, `unitprice`, `BatchID`) VALUES
(1, 1, 50, 5000, 1),
(4, 2, 80, 14000, 4),
(5, 2, 45, 20000, 5),
(7, 3, 55, 15000, 7),
(10, 4, 85, 16000, 10),
(11, 4, 40, 24000, 11),
(13, 5, 50, 18000, 13),
(14, 5, 60, 26000, 14),
(16, 6, 45, 17000, 16),
(17, 6, 80, 23000, 17),
(19, 7, 85, 8000, 21),
(20, 7, 75, 12000, 20),
(22, 8, 50, 9000, 22),
(23, 8, 60, 13000, 23),
(25, 9, 80, 7000, 25),
(26, 9, 90, 11000, 86),
(27, 9, 45, 15000, 87),
(28, 10, 55, 9500, 28),
(29, 10, 65, 13500, 29),
(30, 10, 75, 17500, 30),
(31, 11, 85, 22000, 31),
(32, 11, 50, 33000, 32),
(33, 11, 60, 75000, 33),
(34, 12, 40, 24000, 34),
(35, 12, 30, 36000, 35),
(37, 13, 45, 23000, 37),
(38, 13, 55, 34000, 38),
(39, 13, 65, 77000, 39),
(40, 14, 75, 19000, 40),
(41, 14, 85, 33000, 41),
(42, 14, 50, 48000, 42),
(43, 15, 60, 19500, 43),
(44, 15, 70, 33500, 44),
(45, 15, 80, 48500, 45),
(46, 16, 90, 14000, 46),
(47, 16, 45, 22000, 47),
(48, 16, 55, 29000, 48),
(49, 17, 65, 14500, 49),
(50, 17, 75, 22500, 50),
(51, 17, 85, 29500, 51),
(52, 18, 50, 16000, 52),
(53, 18, 60, 31000, 53),
(54, 18, 70, 46000, 54),
(55, 19, 80, 16500, 55),
(56, 19, 90, 31500, 56),
(57, 19, 45, 47000, 57),
(58, 20, 55, 19000, 58),
(59, 20, 65, 28000, 59),
(60, 20, 75, 37000, 60),
(61, 27, 85, 12000, 2),
(62, 27, 50, 18000, 62),
(63, 27, 60, 25000, 63),
(64, 28, 70, 14000, 64),
(65, 28, 80, 20000, 65),
(66, 28, 90, 27000, 66),
(67, 29, 45, 15000, 67),
(68, 29, 55, 22000, 68),
(69, 29, 65, 29000, 69),
(70, 30, 75, 16000, 70),
(71, 30, 85, 24000, 71),
(72, 30, 50, 30000, 72),
(73, 31, 60, 18000, 73),
(74, 31, 70, 26000, 74),
(75, 31, 80, 32000, 75),
(76, 32, 90, 17000, 76),
(77, 32, 45, 23000, 77),
(78, 32, 55, 28000, 78),
(79, 33, 65, 8000, 79),
(80, 33, 75, 12000, 80),
(81, 33, 85, 16000, 81),
(82, 34, 50, 9000, 82),
(83, 34, 60, 13000, 83),
(84, 34, 70, 17000, 84),
(85, 35, 80, 7000, 85),
(88, 36, 55, 9500, 88),
(89, 36, 65, 13500, 89),
(90, 36, 75, 17500, 90),
(91, 37, 85, 22000, 91),
(92, 37, 50, 33000, 92),
(93, 37, 60, 75000, 93),
(94, 38, 70, 24000, 94),
(95, 38, 80, 36000, 95),
(96, 38, 90, 80000, 96),
(97, 39, 45, 23000, 97),
(98, 39, 55, 34000, 98),
(99, 39, 65, 77000, 99),
(100, 1, 85, 5000, 61);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `stockout`
--

CREATE TABLE `stockout` (
  `StockOutID` int(11) NOT NULL,
  `WarehouseID` int(11) NOT NULL,
  `orderid` bigint(20) DEFAULT NULL,
  `stockoutdate` datetime(6) DEFAULT NULL,
  `note` tinytext DEFAULT NULL,
  `status` enum('EXPORTED','RETURNED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `stockout`
--

INSERT INTO `stockout` (`StockOutID`, `WarehouseID`, `orderid`, `stockoutdate`, `note`, `status`) VALUES
(1, 1, 2, '2025-08-03 03:28:32.000000', 'Xuất kho nhanh cho đơn hàng 945383', 'EXPORTED'),
(2, 1, 3, '2025-05-29 03:28:38.000000', 'Xuất kho nhanh cho đơn hàng 959009', 'EXPORTED'),
(3, 1, 4, '2025-07-07 03:28:43.000000', 'Xuất kho nhanh cho đơn hàng 10370', 'EXPORTED'),
(4, 1, 5, '2025-06-04 03:28:47.000000', 'Xuất kho nhanh cho đơn hàng 60665', 'EXPORTED'),
(5, 1, 6, '2025-07-03 03:28:51.000000', 'Xuất kho nhanh cho đơn hàng 196519', 'EXPORTED'),
(6, 1, 7, '2025-08-03 03:28:58.000000', 'Xuất kho nhanh cho đơn hàng 257286', 'EXPORTED'),
(7, 1, 8, '2025-08-03 03:38:35.000000', 'Xuất kho nhanh cho đơn hàng 752465', 'EXPORTED'),
(8, 1, 9, '2025-05-29 03:38:39.000000', 'Xuất kho nhanh cho đơn hàng 814218', 'EXPORTED'),
(10, 1, 10, '2025-07-03 03:38:44.000000', 'Xuất kho nhanh cho đơn hàng 851234', 'EXPORTED'),
(11, 1, 11, '2025-07-04 03:38:48.000000', 'Xuất kho nhanh cho đơn hàng 977186', 'EXPORTED'),
(12, 1, 12, '2025-08-03 03:38:52.000000', 'Xuất kho nhanh cho đơn hàng 10922', 'EXPORTED'),
(13, 1, 13, '2025-07-03 03:38:56.000000', 'Xuất kho nhanh cho đơn hàng 46836', 'EXPORTED'),
(14, 1, 14, '2025-07-23 03:48:01.000000', 'Xuất kho nhanh cho đơn hàng 565520', 'EXPORTED'),
(15, 1, 15, '2025-06-04 03:48:06.000000', 'Xuất kho nhanh cho đơn hàng 641069', 'EXPORTED');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `stockoutdetail`
--

CREATE TABLE `stockoutdetail` (
  `StockOutDetailID` int(11) NOT NULL,
  `StockOutID` int(11) NOT NULL,
  `BatchID` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `orderdetailid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `stockoutdetail`
--

INSERT INTO `stockoutdetail` (`StockOutDetailID`, `StockOutID`, `BatchID`, `quantity`, `orderdetailid`) VALUES
(1, 1, 79, 1, 2),
(2, 2, 61, 19, 3),
(3, 2, 36, 15, 4),
(4, 2, 42, 21, 5),
(5, 3, 7, 23, 6),
(6, 3, 30, 40, 7),
(7, 3, 33, 16, 8),
(8, 4, 61, 34, 9),
(9, 4, 23, 56, 10),
(10, 4, 86, 54, 11),
(11, 5, 61, 32, 12),
(12, 5, 1, 2, 12),
(13, 5, 20, 75, 13),
(14, 5, 80, 12, 13),
(15, 5, 60, 22, 14),
(16, 6, 18, 30, 15),
(17, 6, 37, 20, 16),
(18, 6, 40, 10, 17),
(19, 6, 49, 20, 18),
(20, 7, 1, 21, 19),
(21, 7, 4, 32, 20),
(22, 7, 88, 12, 21),
(23, 7, 37, 12, 22),
(24, 8, 80, 32, 23),
(25, 8, 82, 32, 24),
(26, 8, 46, 40, 25),
(27, 10, 4, 24, 26),
(28, 10, 87, 43, 27),
(29, 10, 57, 13, 28),
(30, 11, 36, 10, 29),
(31, 11, 50, 10, 30),
(32, 11, 53, 4, 31),
(33, 12, 21, 43, 32),
(34, 12, 25, 71, 33),
(35, 13, 16, 40, 34),
(36, 13, 32, 10, 35),
(37, 13, 41, 34, 36),
(38, 14, 7, 32, 37),
(39, 14, 80, 31, 38),
(40, 14, 82, 18, 39),
(41, 14, 22, 13, 39),
(42, 15, 1, 6, 40),
(43, 15, 21, 42, 41),
(44, 15, 22, 32, 42),
(45, 15, 34, 20, 43),
(46, 15, 44, 20, 44);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
--

CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `SupplierName` varchar(255) NOT NULL,
  `contactinfo` varchar(100) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `suppliers`
--

INSERT INTO `suppliers` (`SupplierID`, `SupplierName`, `contactinfo`, `logo`) VALUES
(1, 'Nông Sản Việt', 'nongsanvietnam@gmail.com', 'lt1.png'),
(2, 'Nông Sản Nhà Quê', 'nongsannhaque@gmail.com', 'nongsannhaque_1.png'),
(3, 'Agricultures', 'agricultures@gmail.com', 'nongsanque.png'),
(4, 'Thế giới nông sản', 'thegioinongsan@gmail.com', 'thegioinongsan_1.png'),
(5, 'Farm Food', 'vietgreenfarm@gmail.com', 'farm.png'),
(6, 'Sữa Ba Vì', 'bavisua@gmail.com', 'R_1.jpg'),
(7, 'Organica', 'organica.vn@gmail.com', 'nature-organic-product-logo-with-hand-and-leaf-design-template-free-vector.jpg'),
(8, 'Nông Sản Family', 'nongsachhanoi@gmail.com', 'family.png\r\n');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tokenuser`
--

CREATE TABLE `tokenuser` (
  `tokenuserid` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `updateprofilehistory`
--

CREATE TABLE `updateprofilehistory` (
  `historyid` int(11) NOT NULL,
  `updateinfo` varchar(1000) DEFAULT NULL,
  `updatetime` datetime(6) NOT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `RoleID` int(11) DEFAULT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `FullName` varchar(255) DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `CreatedAt` datetime DEFAULT current_timestamp(),
  `OTP` varchar(10) DEFAULT NULL,
  `LastTimeUpdatePass` datetime DEFAULT NULL,
  `GoogleID` varchar(255) DEFAULT NULL,
  `StatusGG` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`UserID`, `RoleID`, `UserName`, `FullName`, `Image`, `Email`, `Phone`, `Address`, `Gender`, `Status`, `dob`, `CreatedAt`, `OTP`, `LastTimeUpdatePass`, `GoogleID`, `StatusGG`, `password`) VALUES
(40, 6, 'daohuyhoang507', 'Hoang Ba Vi', 'avatar.jpg', 'daohuyhoang507@gmail.com', '0342654334', 'Hà Nội', 'Male', 'Active', '2003-05-17', '2025-07-22 14:40:13', '416934', '2025-08-02 00:56:50', 'Inactive', 'Inactive', '$2a$10$HFoa18HENOCJx.tBki24RueWHJEI.VVUjMXQKS8kIG2wgXgsdeRby'),
(43, 4, 'chengx_295', 'Nguyễn Xuân Chiến', 'avatar.jpg', 'chiennguyena8k22@gmail.com', '0379366518', '', 'Nam', 'Active', '2004-05-29', '2025-07-24 09:12:25', '566474', '2025-08-02 05:09:45', 'Inactive', 'Inactive', '$2a$10$U0kLc4F7TAYqyfpVZWlZ2uScy/yeaOqajUQsd3cwLrpq15e1XAD.e'),
(44, 5, 'warehouse_staff', 'Quang', 'avatar.gif', 'nhatquang182@gmail.com', '0123456789', 'Ba Vì', 'Male', 'Active', '2005-02-25', '2025-07-24 11:22:55', '694783', '2025-08-02 00:42:42', 'Inactive', 'Inactive', '$2a$10$5lcA.CkEr9ijnFwOmT8zgenNr.0K71JDlh7WmYDln7zt6d8CTNMim'),
(45, 1, 'admin_123', '', 'beec5ecc-6ceb-4a77-8e33-f5eb33045fe6.huong-sua-tuoi.jpg', 'thanhhv27042004@gmail.com', '', '', 'Male', 'Active', NULL, '2025-07-24 11:24:39', '876119', '2025-08-02 01:17:21', 'Inactive', 'Inactive', '$2a$10$wD1yydAvUezol71PKbrcse9SzLLjNhxJHUFVIlppD97dzQjfeq7ja'),
(48, 2, 'Hoanganhpro', 'Nguyễn Xuân Chiến', 'avatar.jpg', '1234yeyeyeye1234@gmail.com', '0379366518', 'Hưng Yên', 'Nam', 'Active', '2006-07-25', '2025-07-25 13:02:10', '171952', '2025-08-02 05:11:39', 'Inactive', 'Inactive', '$2a$10$H.99bOjkvo5fZxdacO1jrObgsTOMfNBopzQSEAy5Ebq0AqaXQeecq'),
(49, 2, 'thanhhv27042004@gmail.com', 'hoang', 'avatar.jpg', 'daohuyhoang7@gmail.com', '0923456754', 'Hưng Yên', 'Male', 'Active', '2005-05-12', '2025-08-02 00:57:23', NULL, '2025-08-02 01:05:24', NULL, NULL, '$2a$10$EuHLk1LmHls/bw.jJmuFUOUynLcg1rAwd9MedZXa8NdXOs0jiFAYu'),
(54, 2, 'thanhhv2704200', 'hoang ba vi', 'avatar.jpg', 'ggffggfgffg@gmail.com', '0923456783', 'Hưng Yên', 'Male', 'Active', '2000-02-05', '2025-08-02 01:16:57', '171124', '2025-08-02 01:16:57', 'Inactive', 'Inactive', '$2a$10$yMQ2Ti/5b6v7ILtmRq39tunRh6P2hzwKbT4wTPfWjr//KOqv3z7cO'),
(55, 2, 'hoangbavi123', 'Huy Hoàng', 'avatar.jpg', 'huyhoangbavi123@gmail.com', '0934124678', 'Ba Vì', 'Female', 'Active', '2004-07-06', '2025-08-03 02:57:52', '127512', '2025-08-03 02:58:15', 'Inactive', 'Inactive', '$2a$10$Ywdane7a.TTftXfuTMTSHesRTSuNUEwdJGmcRo9mPljBRbsdZvpNy'),
(56, 2, 'huyhoangbavi123', 'Đào Huy Hoàng', 'avatar.jpg', 'hoangbavi123@gmail.com', '0967125471', 'Ba Vì , Hà Nội ', 'Male', 'Active', '2004-07-16', '2025-08-03 02:59:04', '142655', '2025-08-03 02:59:04', 'Inactive', 'Inactive', '$2a$10$ZbaA7Rxd7i3jXemN9q15mOVkAzb5yDaxYF/eOKURitQBkiXcpApRK'),
(57, 2, 'ducgiang123', 'Đức Giang', 'avatar.png', 'giang123@gmail.com', '0961245141', 'Hưng Yên, Hà Nội ', 'Male', 'Active', '2004-10-06', '2025-08-03 03:00:13', '543754', '2025-08-03 03:00:13', 'Inactive', 'Inactive', '$2a$10$H95lMmhB4Q7BW6Uk8.PuNuW9qRVT3AfhtHodVU6JBpyfgcuHSkRXe'),
(58, 2, 'xuanchien123', 'Nguyễn Xuân Chiến', 'avatar.png', 'chien123@gmail.com', '0987881241', 'Hưng Yên, Hà Nội ', 'Male', 'Active', '2004-02-11', '2025-08-03 03:01:21', '135432', '2025-08-03 03:01:21', 'Inactive', 'Inactive', '$2a$10$cmshOlIM8EriQ0WLfdb/2uC6EY0QCZAgnE6dB0eOyV01AmVXTn7oa'),
(59, 2, 'nhatquang123', 'Nhật Quang', 'avatar.jpg', 'nhatquang@gmail.com', '0967986324', 'Thanh Hóa, Hà Nội', 'Male', 'Active', '2004-03-09', '2025-08-03 03:03:03', '124634', '2025-08-03 03:03:03', 'Inactive', 'Inactive', '$2a$10$RBcf20.uDNaxONXTljfDr.GGj.wvK4gmmBv.eU5bEAkhYhyzzC/k2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `voucher`
--

CREATE TABLE `voucher` (
  `id` bigint(20) NOT NULL,
  `discount_type` enum('AMOUNT','PERCENT') NOT NULL,
  `discount_value` decimal(15,2) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `islocked` bit(1) NOT NULL,
  `min_order_amount` decimal(15,2) NOT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `start_date` datetime(6) NOT NULL,
  `status` enum('ACTIVE','EXPIRED','INACTIVE') NOT NULL,
  `used_quantity` bigint(20) DEFAULT NULL,
  `voucher_code` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `voucher`
--

INSERT INTO `voucher` (`id`, `discount_type`, `discount_value`, `end_date`, `islocked`, `min_order_amount`, `quantity`, `start_date`, `status`, `used_quantity`, `voucher_code`) VALUES
(1, 'PERCENT', 20.00, '2025-08-21 12:00:00.000000', b'0', 200000.00, 91, '2025-06-10 00:00:00.000000', 'ACTIVE', 1, 'SALE20P'),
(2, 'AMOUNT', 50000.00, '2025-08-21 12:00:00.000000', b'0', 250000.00, 10, '2025-06-01 00:00:00.000000', 'ACTIVE', 1, 'HOTDEAL'),
(3, 'AMOUNT', 50000.00, '2025-08-21 12:00:00.000000', b'0', 100000.00, 100, '2025-05-01 00:00:00.000000', 'ACTIVE', 47, 'EXPIRED50'),
(4, 'PERCENT', 15.00, '2025-08-21 12:00:00.000000', b'0', 150000.00, 20, '2025-07-01 00:00:00.000000', 'ACTIVE', 11, 'COMINGSOON'),
(5, 'AMOUNT', 5000.00, '2025-07-25 12:27:00.000000', b'0', 50000.00, 31, '2025-07-25 12:25:00.000000', 'EXPIRED', 5, 'SALE5K');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wallets`
--

CREATE TABLE `wallets` (
  `walletid` bigint(20) NOT NULL,
  `balance` decimal(20,2) DEFAULT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `wallets`
--

INSERT INTO `wallets` (`walletid`, `balance`, `userid`) VALUES
(1, 35977000.00, 48),
(2, 15977000.00, 54),
(3, 21931000.00, 55),
(4, 28166000.00, 56),
(5, 1203000.00, 57),
(6, 40991000.00, 58),
(7, 17980000.00, 59);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `warehouse`
--

CREATE TABLE `warehouse` (
  `WarehouseID` int(11) NOT NULL,
  `WarehouseName` varchar(255) NOT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `warehouse`
--

INSERT INTO `warehouse` (`WarehouseID`, `WarehouseName`, `Location`, `description`) VALUES
(1, 'Kho Trung Tâm', 'Ba Vì', 'Kho chính');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wishlist`
--

CREATE TABLE `wishlist` (
  `WishlistID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `productid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `wishlist`
--

INSERT INTO `wishlist` (`WishlistID`, `UserID`, `productid`) VALUES
(1, 48, 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `adjustment`
--
ALTER TABLE `adjustment`
  ADD PRIMARY KEY (`AdjustmentID`),
  ADD KEY `FK27q6o80pjlb4o0c6mmhrssdnb` (`batchid`);

--
-- Chỉ mục cho bảng `authorization`
--
ALTER TABLE `authorization`
  ADD PRIMARY KEY (`AuthorizationID`),
  ADD KEY `RoleID` (`RoleID`);

--
-- Chỉ mục cho bảng `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`blogid`);

--
-- Chỉ mục cho bảng `blogcategory`
--
ALTER TABLE `blogcategory`
  ADD PRIMARY KEY (`blogcategoryid`);

--
-- Chỉ mục cho bảng `blogdetail`
--
ALTER TABLE `blogdetail`
  ADD PRIMARY KEY (`blogdetailid`),
  ADD KEY `FK4009etao9jxpybgd8g9fs41ld` (`blogid`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`CartID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ProductDetailID` (`ProductDetailID`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Chỉ mục cho bảng `categoryweight`
--
ALTER TABLE `categoryweight`
  ADD PRIMARY KEY (`CategoryWeightID`),
  ADD KEY `CategoryID` (`CategoryID`);

--
-- Chỉ mục cho bảng `commentblog`
--
ALTER TABLE `commentblog`
  ADD PRIMARY KEY (`commentblogid`),
  ADD KEY `userid` (`userid`),
  ADD KEY `blogid` (`blogid`);

--
-- Chỉ mục cho bảng `commentproduct`
--
ALTER TABLE `commentproduct`
  ADD PRIMARY KEY (`CommentProductID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `FKqtfscvk0n3d0nvsalgqir56lv` (`productid`);

--
-- Chỉ mục cho bảng `contactus`
--
ALTER TABLE `contactus`
  ADD PRIMARY KEY (`contactusid`),
  ADD KEY `FKc52kupwktdtfwwjldb8mplxxn` (`userid`);

--
-- Chỉ mục cho bảng `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`DeviceID`),
  ADD KEY `WarehouseID` (`WarehouseID`);

--
-- Chỉ mục cho bảng `forgot_password`
--
ALTER TABLE `forgot_password`
  ADD PRIMARY KEY (`fpid`),
  ADD UNIQUE KEY `UKpft6qo9eo7qajhvgi97pt73kk` (`userid`);

--
-- Chỉ mục cho bảng `gallery`
--
ALTER TABLE `gallery`
  ADD PRIMARY KEY (`galleryid`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Chỉ mục cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`InventoryID`),
  ADD KEY `ProductDetailID` (`ProductDetailID`),
  ADD KEY `WarehouseID` (`WarehouseID`),
  ADD KEY `BatchID` (`BatchID`);

--
-- Chỉ mục cho bảng `myaddressbook`
--
ALTER TABLE `myaddressbook`
  ADD PRIMARY KEY (`addressbookid`),
  ADD KEY `FK4vkcjh44w6q01hrgaj692er1c` (`userid`);

--
-- Chỉ mục cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK22m2u35sj85g5tng94n2rd7eb` (`userid`);

--
-- Chỉ mục cho bảng `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`orderid`),
  ADD UNIQUE KEY `UKs6ggvnoh3hl99a6td88vqvmfx` (`ordercode`),
  ADD KEY `FK4yfk7m9cf7n5689y0c1j44e64` (`userid`),
  ADD KEY `FKnrduwglsych0g717gihrtflbu` (`voucher_id`);

--
-- Chỉ mục cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`orderdetailid`),
  ADD KEY `FK2ddhnw8i3tl2x1fxrl5g1navn` (`orderid`);

--
-- Chỉ mục cho bảng `orderreview`
--
ALTER TABLE `orderreview`
  ADD PRIMARY KEY (`orderreviewid`),
  ADD UNIQUE KEY `UKkq9uiw4kgtnmdkglb4jol6l2w` (`ordercode`),
  ADD KEY `FKrqrt8h1swocpqc80xulolk234` (`userid`);

--
-- Chỉ mục cho bảng `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `OrderID` (`OrderID`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`),
  ADD KEY `CategoryID` (`CategoryID`);

--
-- Chỉ mục cho bảng `productbatch`
--
ALTER TABLE `productbatch`
  ADD PRIMARY KEY (`BatchID`),
  ADD KEY `ProductDetailID` (`ProductDetailID`);

--
-- Chỉ mục cho bảng `productdetail`
--
ALTER TABLE `productdetail`
  ADD PRIMARY KEY (`ProductDetailID`),
  ADD KEY `FK5tqssatrmfiu9ayh3futup0wu` (`productid`),
  ADD KEY `FKejqb8da7lrkwppkkyi56w6r84` (`CategoryWeightID`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`RoleID`);

--
-- Chỉ mục cho bảng `seller_application`
--
ALTER TABLE `seller_application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK839ux3h65s2wyso5hghutcx6l` (`user_id`);

--
-- Chỉ mục cho bảng `shipment`
--
ALTER TABLE `shipment`
  ADD PRIMARY KEY (`ShipmentID`),
  ADD KEY `OrderID` (`OrderID`);

--
-- Chỉ mục cho bảng `soldbyseller`
--
ALTER TABLE `soldbyseller`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46p0njok9v3e5hkwr81xq3i7w` (`productdetailid`),
  ADD KEY `FKfr72eyepebfj2juqqin6fijqd` (`userid`);

--
-- Chỉ mục cho bảng `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Chỉ mục cho bảng `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Chỉ mục cho bảng `stockin`
--
ALTER TABLE `stockin`
  ADD PRIMARY KEY (`StockInID`),
  ADD KEY `SupplierID` (`SupplierID`),
  ADD KEY `WarehouseID` (`WarehouseID`);

--
-- Chỉ mục cho bảng `stockindetail`
--
ALTER TABLE `stockindetail`
  ADD PRIMARY KEY (`StockInDetailID`),
  ADD KEY `StockInID` (`StockInID`),
  ADD KEY `BatchID` (`BatchID`);

--
-- Chỉ mục cho bảng `stockout`
--
ALTER TABLE `stockout`
  ADD PRIMARY KEY (`StockOutID`),
  ADD KEY `WarehouseID` (`WarehouseID`),
  ADD KEY `OrderID` (`orderid`);

--
-- Chỉ mục cho bảng `stockoutdetail`
--
ALTER TABLE `stockoutdetail`
  ADD PRIMARY KEY (`StockOutDetailID`),
  ADD KEY `StockOutID` (`StockOutID`),
  ADD KEY `BatchID` (`BatchID`),
  ADD KEY `FKd8fm5wpab7si40c2f6eyutqvn` (`orderdetailid`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SupplierID`);

--
-- Chỉ mục cho bảng `tokenuser`
--
ALTER TABLE `tokenuser`
  ADD PRIMARY KEY (`tokenuserid`);

--
-- Chỉ mục cho bảng `updateprofilehistory`
--
ALTER TABLE `updateprofilehistory`
  ADD PRIMARY KEY (`historyid`),
  ADD KEY `FKrmeehvbpj2k798ag1eor2hh5h` (`userid`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `FK2ovmsl4hvm5vu1w8i308r5j6w` (`RoleID`);

--
-- Chỉ mục cho bảng `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpt2fcgppqfc1tbcvqctm7nuet` (`voucher_code`);

--
-- Chỉ mục cho bảng `wallets`
--
ALTER TABLE `wallets`
  ADD PRIMARY KEY (`walletid`),
  ADD KEY `FKg201i0rkhufy3unov95eu2xod` (`userid`);

--
-- Chỉ mục cho bảng `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`WarehouseID`);

--
-- Chỉ mục cho bảng `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`WishlistID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `FKgmfdg7ydih4ua53mfeltbjoek` (`productid`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `adjustment`
--
ALTER TABLE `adjustment`
  MODIFY `AdjustmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `authorization`
--
ALTER TABLE `authorization`
  MODIFY `AuthorizationID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `blog`
--
ALTER TABLE `blog`
  MODIFY `blogid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `blogcategory`
--
ALTER TABLE `blogcategory`
  MODIFY `blogcategoryid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `blogdetail`
--
ALTER TABLE `blogdetail`
  MODIFY `blogdetailid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `CartID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `CategoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `categoryweight`
--
ALTER TABLE `categoryweight`
  MODIFY `CategoryWeightID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT cho bảng `commentblog`
--
ALTER TABLE `commentblog`
  MODIFY `commentblogid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `commentproduct`
--
ALTER TABLE `commentproduct`
  MODIFY `CommentProductID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `contactus`
--
ALTER TABLE `contactus`
  MODIFY `contactusid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `device`
--
ALTER TABLE `device`
  MODIFY `DeviceID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `forgot_password`
--
ALTER TABLE `forgot_password`
  MODIFY `fpid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `gallery`
--
ALTER TABLE `gallery`
  MODIFY `galleryid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT cho bảng `inventory`
--
ALTER TABLE `inventory`
  MODIFY `InventoryID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `myaddressbook`
--
ALTER TABLE `myaddressbook`
  MODIFY `addressbookid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `order`
--
ALTER TABLE `order`
  MODIFY `orderid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  MODIFY `orderdetailid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT cho bảng `orderreview`
--
ALTER TABLE `orderreview`
  MODIFY `orderreviewid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT cho bảng `productbatch`
--
ALTER TABLE `productbatch`
  MODIFY `BatchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT cho bảng `productdetail`
--
ALTER TABLE `productdetail`
  MODIFY `ProductDetailID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `RoleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `seller_application`
--
ALTER TABLE `seller_application`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `shipment`
--
ALTER TABLE `shipment`
  MODIFY `ShipmentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `soldbyseller`
--
ALTER TABLE `soldbyseller`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT cho bảng `stockin`
--
ALTER TABLE `stockin`
  MODIFY `StockInID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT cho bảng `stockindetail`
--
ALTER TABLE `stockindetail`
  MODIFY `StockInDetailID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT cho bảng `stockout`
--
ALTER TABLE `stockout`
  MODIFY `StockOutID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `stockoutdetail`
--
ALTER TABLE `stockoutdetail`
  MODIFY `StockOutDetailID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SupplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `tokenuser`
--
ALTER TABLE `tokenuser`
  MODIFY `tokenuserid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `updateprofilehistory`
--
ALTER TABLE `updateprofilehistory`
  MODIFY `historyid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT cho bảng `voucher`
--
ALTER TABLE `voucher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `wallets`
--
ALTER TABLE `wallets`
  MODIFY `walletid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `warehouse`
--
ALTER TABLE `warehouse`
  MODIFY `WarehouseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `WishlistID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `adjustment`
--
ALTER TABLE `adjustment`
  ADD CONSTRAINT `FK27q6o80pjlb4o0c6mmhrssdnb` FOREIGN KEY (`batchid`) REFERENCES `productbatch` (`BatchID`);

--
-- Các ràng buộc cho bảng `authorization`
--
ALTER TABLE `authorization`
  ADD CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`);

--
-- Các ràng buộc cho bảng `blogdetail`
--
ALTER TABLE `blogdetail`
  ADD CONSTRAINT `FK4009etao9jxpybgd8g9fs41ld` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`);

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`);

--
-- Các ràng buộc cho bảng `categoryweight`
--
ALTER TABLE `categoryweight`
  ADD CONSTRAINT `categoryweight_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`);

--
-- Các ràng buộc cho bảng `commentblog`
--
ALTER TABLE `commentblog`
  ADD CONSTRAINT `commentblog_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `commentblog_ibfk_2` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`);

--
-- Các ràng buộc cho bảng `commentproduct`
--
ALTER TABLE `commentproduct`
  ADD CONSTRAINT `FKqtfscvk0n3d0nvsalgqir56lv` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `commentproduct_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `contactus`
--
ALTER TABLE `contactus`
  ADD CONSTRAINT `FKc52kupwktdtfwwjldb8mplxxn` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `device_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`);

--
-- Các ràng buộc cho bảng `forgot_password`
--
ALTER TABLE `forgot_password`
  ADD CONSTRAINT `FKi4guet8gckf9pkf7jtwbd9cj` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `gallery`
--
ALTER TABLE `gallery`
  ADD CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`);

--
-- Các ràng buộc cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`),
  ADD CONSTRAINT `inventory_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`);

--
-- Các ràng buộc cho bảng `myaddressbook`
--
ALTER TABLE `myaddressbook`
  ADD CONSTRAINT `FK4vkcjh44w6q01hrgaj692er1c` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FK22m2u35sj85g5tng94n2rd7eb` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `FK4yfk7m9cf7n5689y0c1j44e64` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`),
  ADD CONSTRAINT `FKnrduwglsych0g717gihrtflbu` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`);

--
-- Các ràng buộc cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD CONSTRAINT `FK2ddhnw8i3tl2x1fxrl5g1navn` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`);

--
-- Các ràng buộc cho bảng `orderreview`
--
ALTER TABLE `orderreview`
  ADD CONSTRAINT `FKrqrt8h1swocpqc80xulolk234` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`);

--
-- Các ràng buộc cho bảng `productbatch`
--
ALTER TABLE `productbatch`
  ADD CONSTRAINT `productbatch_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`);

--
-- Các ràng buộc cho bảng `productdetail`
--
ALTER TABLE `productdetail`
  ADD CONSTRAINT `FK5tqssatrmfiu9ayh3futup0wu` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `FKejqb8da7lrkwppkkyi56w6r84` FOREIGN KEY (`CategoryWeightID`) REFERENCES `categoryweight` (`CategoryWeightID`);

--
-- Các ràng buộc cho bảng `seller_application`
--
ALTER TABLE `seller_application`
  ADD CONSTRAINT `FK839ux3h65s2wyso5hghutcx6l` FOREIGN KEY (`user_id`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `soldbyseller`
--
ALTER TABLE `soldbyseller`
  ADD CONSTRAINT `FK46p0njok9v3e5hkwr81xq3i7w` FOREIGN KEY (`productdetailid`) REFERENCES `productdetail` (`ProductDetailID`),
  ADD CONSTRAINT `FKfr72eyepebfj2juqqin6fijqd` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `stockin`
--
ALTER TABLE `stockin`
  ADD CONSTRAINT `stockin_ibfk_1` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`),
  ADD CONSTRAINT `stockin_ibfk_2` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`);

--
-- Các ràng buộc cho bảng `stockindetail`
--
ALTER TABLE `stockindetail`
  ADD CONSTRAINT `stockindetail_ibfk_1` FOREIGN KEY (`StockInID`) REFERENCES `stockin` (`StockInID`),
  ADD CONSTRAINT `stockindetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`);

--
-- Các ràng buộc cho bảng `stockout`
--
ALTER TABLE `stockout`
  ADD CONSTRAINT `FK7ljvep6rr4pj89v3khvhofxtl` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`),
  ADD CONSTRAINT `stockout_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`);

--
-- Các ràng buộc cho bảng `stockoutdetail`
--
ALTER TABLE `stockoutdetail`
  ADD CONSTRAINT `FKd8fm5wpab7si40c2f6eyutqvn` FOREIGN KEY (`orderdetailid`) REFERENCES `orderdetail` (`orderdetailid`),
  ADD CONSTRAINT `stockoutdetail_ibfk_1` FOREIGN KEY (`StockOutID`) REFERENCES `stockout` (`StockOutID`),
  ADD CONSTRAINT `stockoutdetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`);

--
-- Các ràng buộc cho bảng `updateprofilehistory`
--
ALTER TABLE `updateprofilehistory`
  ADD CONSTRAINT `FKrmeehvbpj2k798ag1eor2hh5h` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`);

--
-- Các ràng buộc cho bảng `wallets`
--
ALTER TABLE `wallets`
  ADD CONSTRAINT `FKg201i0rkhufy3unov95eu2xod` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`);

--
-- Các ràng buộc cho bảng `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FKgmfdg7ydih4ua53mfeltbjoek` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`),
  ADD CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
