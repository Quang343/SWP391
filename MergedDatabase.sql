-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: managerwarehouse
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adjustment`
--

DROP TABLE IF EXISTS `adjustment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adjustment` (
  `AdjustmentID` int NOT NULL AUTO_INCREMENT,
  `AdjustDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `reason` varchar(255) DEFAULT NULL,
  `adjustmenttype` enum('Add','Remove') NOT NULL,
  `adjustmentquantity` int NOT NULL,
  `batchid` int DEFAULT NULL,
  PRIMARY KEY (`AdjustmentID`),
  KEY `FK27q6o80pjlb4o0c6mmhrssdnb` (`batchid`),
  CONSTRAINT `FK27q6o80pjlb4o0c6mmhrssdnb` FOREIGN KEY (`batchid`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustment`
--

LOCK TABLES `adjustment` WRITE;
/*!40000 ALTER TABLE `adjustment` DISABLE KEYS */;
INSERT INTO `adjustment` VALUES (1,'2025-07-21 11:12:00','....','Add',12,4),(2,'2025-06-26 01:17:09','Lỗi','Remove',10,1),(3,'2025-06-26 21:06:51','add','Add',2,2),(4,'2025-06-27 10:57:03','hư','Remove',1,8),(5,'2025-06-27 10:57:12','hỏng','Remove',2,3),(6,'2025-06-27 10:57:25','hết hạn','Remove',2,5),(7,'2025-07-04 10:34:48','hư hại','Remove',10,1),(8,'2025-07-04 10:41:16','Lỗi','Remove',1,1),(9,'2025-07-08 18:24:36','Lỗi','Remove',1,6),(10,'2025-07-24 00:24:34',NULL,'Remove',1,65);
/*!40000 ALTER TABLE `adjustment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `AuthorizationID` int NOT NULL AUTO_INCREMENT,
  `RoleID` int DEFAULT NULL,
  `UrlAuthorized` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `FeatureDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `StatusSetting` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AuthorizationID`),
  KEY `RoleID` (`RoleID`),
  CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorization`
--

LOCK TABLES `authorization` WRITE;
/*!40000 ALTER TABLE `authorization` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `blogid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `blogcategoryid` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `createdat` datetime DEFAULT NULL,
  `blogdateupdate` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`blogid`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,1,'Hướng dẫn trồng rau sạch tại nhà','Mẹo trồng rau sạch trong không gian nhỏ...','2025-07-06 14:00:00',NULL,'ACTIVE','Đào Huy Hoàng'),(2,2,2,'Xu hướng chăn nuôi bền vững','Phân tích các phương pháp chăn nuôi hiện đại...','2025-07-06 14:05:00',NULL,'ACTIVE','Linda Green'),(3,1,1,'Kỹ thuật làm đất chuẩn bị mùa vụ','Các bước làm đất hiệu quả cho mùa màng...','2025-07-06 14:10:00',NULL,'ACTIVE','Tom Carter'),(4,2,2,'Tác động của thời tiết đến nông nghiệp','Dự báo và ứng phó với biến đổi khí hậu...','2025-07-06 14:15:00',NULL,'ACTIVE','Linda Green'),(5,1,1,'Phân bón tự nhiên từ rác thải','Hướng dẫn làm phân bón hữu cơ tại nhà...','2025-07-06 14:20:00',NULL,'ACTIVE','Tom Carter'),(6,2,2,'Thị trường hạt giống năm 2025','Phân tích xu hướng hạt giống mới...','2025-07-06 14:25:00',NULL,'ACTIVE','Linda Green'),(7,1,1,'Kỹ thuật bảo quản trái cây','Cách bảo quản trái cây tươi lâu...','2025-07-06 14:30:00',NULL,'ACTIVE','Tom Carter'),(8,2,2,'Công nghệ tưới tiêu tự động','Ứng dụng công nghệ trong nông nghiệp...','2025-07-06 14:35:00',NULL,'ACTIVE','Linda Green'),(9,1,1,'Lợi ích của trồng cây lâu năm','Ưu điểm và kinh nghiệm thực tế...','2025-07-06 14:40:00','2025-07-15 15:49:37','ACTIVE','Tom Carte'),(10,2,2,'Dự báo giá nông sản cuối năm','Phân tích giá cả và thị trường...','2025-07-06 14:45:00',NULL,'ACTIVE','Linda Green'),(19,1,5,'Day thon vĩ dạ','Bài thơ \"Đây thôn Vĩ Dạ\"...','2025-07-09 16:35:50','2025-07-23 01:07:01','ACTIVE','Con mèo su kim'),(20,1,1,'s','s','2025-07-15 16:30:34',NULL,'DRAFT','s'),(21,1,2,'sá','ssa','2025-07-15 16:34:48','2025-07-23 00:40:49','DELETED','sá'),(22,30,1,'hay','11','2025-07-15 16:49:52','2025-07-23 00:40:43','DELETED','ssssssss'),(23,30,1,'phim chưởng','hay','2025-07-15 17:04:29','2025-07-23 00:37:45','DELETED','năm can'),(27,36,1,'Công Nghệ Tưới Nhỏ Giọt Tiết Kiệm Nước','Hệ thống tưới nhỏ giọt giúp tiết kiệm 50%...','2025-07-22 14:41:31','2025-07-23 00:52:39','ACTIVE','Đào Huy Hoàng'),(28,36,1,'Tối Ưu Năng Suất Lúa Gạo Với Giống Mới','Nghiên cứu giống lúa mới mang lại năng suất vượt trội...','2025-07-22 20:20:56','2025-07-23 15:01:15','INACTIVE','Đào Huy Hoàng'),(29,40,1,'Sử Dụng Phân Hữu Cơ Trong Canh Tác Sạch','Phân hữu cơ là xu hướng bền vững...','2025-07-23 00:53:27',NULL,'ACTIVE','Đào Huy Hoàng'),(30,40,3,'7 Loại Rau Dễ Trồng Cho Người Mới Bắt Đầu','Danh sách các loại rau phổ biến...','2025-07-23 00:54:37','2025-07-24 03:54:10','INACTIVE','Đào Huy Hoàng'),(31,40,1,'Xu Hướng Nông Nghiệp Số 2025','Các giải pháp công nghệ số giúp quản lý trang trại...','2025-07-23 00:56:21','2025-07-23 00:57:37','ACTIVE','Đô Nan Chăm'),(32,40,1,'Kỹ Thuật Ghép Cành Cho Cây Ăn Quả','Hướng dẫn từng bước kỹ thuật ghép cành...','2025-07-23 00:59:12',NULL,'ACTIVE','Lý Bạch'),(33,40,1,'Dinh Dưỡng Từ Rau Mầm Tự Trồng','Rau mầm giàu vitamin, dễ trồng tại nhà...','2025-07-23 01:00:06',NULL,'ACTIVE','Lý Bạch'),(34,40,1,'Quản Lý Dịch Hại Trên Cây Trồng Bằng Sinh Học','Ứng dụng biện pháp sinh học giúp kiểm soát sâu bệnh...','2025-07-23 01:01:07',NULL,'ACTIVE','Đào Huy Hoàng'),(35,40,1,'Những Điều Lưu Ý Khi Chọn Giống Cây Trồng','Các tiêu chí cần thiết khi chọn giống...','2025-07-23 01:01:58',NULL,'ACTIVE','Nguyễn Đăng Khoa'),(36,40,1,'Sáng Kiến Đổi Mới Trong Chuỗi Cung Ứng Nông Sản','Công nghệ blockchain, truy xuất nguồn gốc...','2025-07-23 01:05:34',NULL,'ACTIVE','Tk Chiến Fpt'),(37,NULL,3,'Sử dụng hoàn toàn chế phẩm sinh học sản xuất lúa, giảm 15% chi phí','Vụ hè thu 2025, Công ty TNHH Trường Sơn BIO phối hợp với Công ty Giống cây trồng miền Nam - Chi nhánh Cờ Đỏ (TP Cần Thơ) triển khai mô hình trình diễn sử dụng hoàn toàn chế phẩm sinh học trên diện tích 2 hecta lúa giống Đài Thơm tại ấp An Thạnh, xã Thạnh Phú, TP Cần Thơ.\n\nMô hình ứng dụng quy trình canh tác gồm 3 lần sử dụng chế phẩm sinh học, từ khâu xử lý rơm rạ, cải tạo đất, diệt ốc bươu vàng đến phòng trừ sâu bệnh, nấm hại và cung cấp dinh dưỡng.\n\nCụ thể, lần 1 sử dụng 5 lít chế phẩm sinh học/hecta để diệt ốc bươu vàng, tích hợp vi sinh vật phân hủy rơm rạ, cải tạo đất, tiêu diệt mầm bệnh, hạ phèn và chống ngộ độc hữu cơ. Lần 2 tiếp tục phun 5 lít chế phẩm diệt nấm bệnh và 5 lít diệt sâu/hecta. Lần 3 sử dụng 3 lít chế phẩm diệt nấm, 3 lít diệt sâu và 5 lít chế phẩm siêu dinh dưỡng/hecta.','2025-07-24 03:59:17','2025-07-24 03:59:49','ACTIVE','Đào Huy Hoàng'),(38,NULL,3,'Lạc lối giữa nông trại cam trù phú xứ Thanh','Năm 2016, anh Nguyễn Văn Chung gom hết vốn liếng, cầm cố nhà cửa vay ngân hàng để thuê, mua lại từng thửa đất của bà con quanh vùng làm nông trại. Nhiều người lắc đầu, bảo anh khác người. Có người buông lời: “Thời nay ai còn đi trồng cây ở rừng? Có khùng mới bỏ tiền tỷ lên đồi trồng cam”.\n\nAnh Chung thì khác. Người đàn ông trung niên có cái lý riêng của mình khi đưa ra quyết định táo bạo như vậy. Với anh, trở về với nông nghiệp là cách bản thân trả ơn cho đất. “Mình sống bao năm nhờ đất, ăn cơm nhờ hạt gạo từ đất, giờ còn sức thì phải làm gì đó cho đất được sống lại”, anh tâm sự.\n\nĐất đồi Vân Du khi ấy khô cằn, đá lẫn trong đất, phải dùng cuốc xẻng mà nạy từng viên một. Cỏ dại cao lút đầu người. Mùa nắng đất nứt nẻ như chân ruộng bỏ hoang, mùa mưa nước chảy tràn, xói cả lớp đất mặt','2025-07-24 04:02:06',NULL,'ACTIVE','Đào Huy Hoàng'),(39,40,3,'Đề xuất hỗ trợ 3.300 xã phường bán nông sản online','Giám đốc cơ quan xúc tiến thương mại của Bộ Nông nghiệp và Môi trường nêu ý tưởng hỗ trợ 3.300 xã phường cả nước bán nông sản online.\n\nĐề xuất được ông Nguyễn Minh Tiến, Giám đốc Trung tâm Xúc tiến Thương mại Nông nghiệp và Môi trường (Bộ Nông nghiệp và Môi trường) nêu tại tọa đàm \"Niềm tin số: Tương lai của Thương mại điện tử\" hôm 17/7.\n\nTừ 1/7, cả nước có hơn 3.300 xã phường sau khi sắp xếp đơn vị hành chính. Theo ông Tiến, để tận dụng kênh thương mại điện tử đang phát triển, cơ quan quản lý và nền tảng bán hàng có thể hợp tác để hỗ trợ từng xã tiếp cận thị trường này.\n\n\"Tại sao chúng ta không hướng tới hỗ trợ mỗi xã đều làm thương mại điện tử. Từ đó, các lãnh đạo địa phương sẽ kể câu chuyện về nông sản, vùng nguyên liệu của mình và tạo được niềm tin để cho người tiêu dùng\", ông nói.','2025-07-24 04:07:09','2025-07-24 04:07:25','ACTIVE','VN Express'),(40,40,2,'gjh','hghfjfdh','2025-07-25 13:22:50',NULL,'DRAFT','hu');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogcategory`
--

DROP TABLE IF EXISTS `blogcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogcategory` (
  `blogcategoryid` bigint NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`blogcategoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogcategory`
--

LOCK TABLES `blogcategory` WRITE;
/*!40000 ALTER TABLE `blogcategory` DISABLE KEYS */;
INSERT INTO `blogcategory` VALUES (1,'Kinh nghiệm','Chia sẻ kỹ thuật canh tác hiệu quả'),(2,'Tin tức','Tin tức thị trường nông sản mới nhất'),(3,'Góc nhìn','Phân tích và bình luận về ngành nông nghiệp'),(4,'Xu hướng','Các xu hướng phát triển nông nghiệp hiện đại'),(5,'Công nghệ','Cập nhật công nghệ nông nghiệp thông minh'),(6,'Giải pháp','Giải pháp thực tiễn nâng cao năng suất'),(7,'Hỏi đáp','Kênh tương tác và giải đáp thắc mắc cho nông dân');
/*!40000 ALTER TABLE `blogcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogdetail`
--

DROP TABLE IF EXISTS `blogdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogdetail` (
  `blogdetailid` int NOT NULL AUTO_INCREMENT,
  `blogid` int DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `detailcontent` varchar(255) NOT NULL,
  PRIMARY KEY (`blogdetailid`),
  KEY `FK4009etao9jxpybgd8g9fs41ld` (`blogid`),
  CONSTRAINT `FK4009etao9jxpybgd8g9fs41ld` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogdetail`
--

LOCK TABLES `blogdetail` WRITE;
/*!40000 ALTER TABLE `blogdetail` DISABLE KEYS */;
INSERT INTO `blogdetail` VALUES (1,1,'1.jpg','Chi tiết cách trồng rau sạch trong chậu...'),(2,2,'2.jpg','Phân tích chi tiết các phương pháp chăn nuôi bền vững...'),(3,3,'3.jpg','Hướng dẫn từng bước làm đất chuẩn bị mùa vụ...'),(4,4,'4.jpg','Tác động cụ thể của thời tiết đến cây trồng...'),(5,5,'5.jpg','Công thức làm phân bón tự nhiên từ rác thải hữu cơ...'),(6,6,'6.jpg','Phân tích xu hướng hạt giống mới trên thị trường...'),(7,7,'7.jpg','Hướng dẫn bảo quản trái cây tươi lâu tại nhà...'),(8,8,'8.jpg','Ứng dụng công nghệ tưới tiêu tự động trong nông nghiệp...'),(9,9,'9.jpg','Ưu điểm và kinh nghiệm thực tế trồng cây lâu năm...'),(10,10,'10.jpg','Dự báo giá cả và phân tích thị trường nông sản cuối năm...'),(14,19,'a593f9a4-4cda-4115-8eb9-b04788dd07c0_anh-nen-2k-dep-nhat_094443817.jpg','Nội dung chi tiết mô tả thêm blog.'),(15,20,'6135eaa9-5d7e-43ce-89e5-f94b9d611949_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(16,21,'f6c88b1c-4f09-4926-b4aa-dc7437c72a28_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(17,22,'b688607e-6f94-42bf-8b79-a663c402516b_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(18,23,'d9464815-d9ed-44f8-8612-ac4ad9307187_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(22,27,'f3720f1f-6791-481a-89d2-d8e5a0ccf3b7_15.jpg','Nội dung chi tiết mô tả thêm blog.'),(23,28,'df2e07f7-50a6-4a1e-8ed8-38533056e88f_anh-nnthok-1663843300827438862164.webp','Nội dung chi tiết mô tả thêm blog.'),(24,29,'acb996bd-7053-47cb-8e36-62f645a19622_q4_3.jpg','Nội dung chi tiết mặc định hoặc để rỗng'),(25,30,'ef893e7f-00df-4aa1-892c-728fc04c5786_OIP.webp','Nội dung chi tiết mặc định hoặc để rỗng'),(26,31,'da33411c-459b-481d-ab70-27b18a470aa5_OIP%20(3).webp','Nội dung chi tiết mặc định hoặc để rỗng'),(27,32,'750ccc7c-d154-4f93-b0cf-60ab49e32599_OIP (1).webp','Nội dung chi tiết mặc định hoặc để rỗng'),(28,33,'0c518c12-1613-4836-b082-e6d4d193f1d6_OIP (7).webp','Nội dung chi tiết mặc định hoặc để rỗng'),(29,34,'0c4c4ecc-17e4-4d74-b60f-b00c7799d803_OIP (2).webp','Nội dung chi tiết mặc định hoặc để rỗng'),(30,35,'a7d548ea-03c8-4611-abc8-ad0ccb5bfefa_OIP (6).webp','Nội dung chi tiết mặc định hoặc để rỗng'),(31,36,'8fb4b3d8-60a9-44a4-8109-ff6ec31e4602_OIP.jpg','Nội dung chi tiết mặc định hoặc để rỗng'),(32,37,'c08889a3-780f-4690-8f4e-3c689dfddda7_Screenshot%202025-07-24%20035838.png','Nội dung chi tiết mặc định hoặc để rỗng'),(33,38,'12d2be5d-c92f-4958-be70-1094675c8681_Screenshot 2025-07-24 040149.png','Nội dung chi tiết mặc định hoặc để rỗng'),(34,39,'163ad3ea-7ad2-4a32-8b3c-7a23cecffce0_Screenshot%202025-07-24%20040638.png','Nội dung chi tiết mô tả thêm blog.'),(35,40,'7a7f4806-d17a-4fa0-985e-f1ffc791c57a_NguyeenXuanChien_HE181121_PT2.jpg','Nội dung chi tiết mô tả thêm blog.');
/*!40000 ALTER TABLE `blogdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `ProductDetailID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`CartID`),
  KEY `UserID` (`UserID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB AUTO_INCREMENT=592 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (356,23,5,3,30000),(357,23,5,2,20000),(358,23,9,2,40000),(359,23,9,1,20000),(360,23,9,1,20000),(366,27,13,1,15000),(367,27,13,1,15000),(370,27,5,1,10000),(371,27,5,1,10000),(372,27,5,1,10000),(373,27,5,1,10000),(374,27,5,1,10000),(375,27,5,1,10000),(433,26,1,1,15000),(434,26,64,1,11111),(466,30,1,1,15000),(467,30,18,1,35000),(468,31,8,1,30000),(469,31,8,1,30000),(470,31,8,1,30000),(471,31,8,1,30000),(472,31,19,1,25000),(473,31,8,1,30000),(474,32,22,3,66000),(490,33,5,1,2000),(491,34,5,29,58000),(492,33,10,1,22000),(493,35,6,1,35000),(494,35,8,1,30000),(495,35,10,1,22000),(496,35,13,1,15000),(497,35,13,1,15000),(498,35,13,1,15000),(499,35,13,1,15000),(500,35,17,1,10000),(501,35,9,1,20000),(502,35,11,1,33000),(503,35,13,1,15000),(504,35,8,1,30000),(505,35,10,1,22000),(506,35,12,1,13000),(507,35,14,1,45000),(508,35,18,1,35000),(509,35,22,1,22000),(510,35,24,1,13000),(511,35,29,1,10000),(512,35,24,1,13000),(513,35,1,1,15000),(514,35,2,1,45000),(515,35,4,1,180000),(516,35,5,1,2000),(517,35,6,1,35000),(518,35,7,1,25000),(519,35,8,1,30000),(520,35,9,1,20000),(521,35,10,1,22000),(522,35,11,1,33000),(523,35,12,1,13000),(524,35,13,1,15000),(525,35,14,1,45000),(526,35,17,1,10000),(527,35,18,1,35000),(528,35,19,1,25000),(529,35,20,1,30000),(530,35,21,1,20000),(531,35,22,1,22000),(532,35,23,1,33000),(533,35,24,1,13000),(534,35,25,1,15000),(535,35,26,1,45000),(536,35,29,1,10000),(537,35,30,1,35000),(538,35,31,1,25000),(539,35,32,1,30000),(540,35,33,1,20000),(541,35,34,1,22000),(542,35,35,1,33000),(543,35,36,1,13000),(544,35,37,1,15000),(545,35,38,1,45000),(546,35,41,1,15000),(547,35,42,1,45000),(548,35,45,1,10000),(549,35,46,1,35000),(550,35,47,1,25000),(551,35,48,1,30000),(552,35,49,1,20000),(553,35,50,1,22000),(554,35,51,1,33000),(555,35,52,1,13000),(556,35,54,1,45000),(557,35,55,1,120000),(558,35,1,1,15000),(559,35,1,1,15000),(560,35,1,1,15000),(561,35,1,1,15000),(562,35,1,1,15000),(563,35,1,1,15000),(564,35,1,1,15000),(565,35,1,1,15000),(585,39,4,1,180000);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(100) DEFAULT NULL,
  `Description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `imageurl` varchar(500) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Rau lá (xà lách, rau muống…)  ','300g–500g','ACTIVE','6c52d831-ab17-4631-9143-a1d01e1ec924.pokemon-pokémon.gif',NULL),(2,'Củ (khoai, cà rốt, củ cải…)','500g–1kg','Active','FrontEnd/assets/svg/1/cup.svg',NULL),(3,'Trái cây vừa (cam, táo…)','1kg–1.5kg','Active','FrontEnd/assets/svg/1/meats.svg',NULL),(4,'Trái cây lớn (dưa, mít…)','2kg–4kg','Active','FrontEnd/assets/svg/1/breakfast.svg',NULL),(5,'Gạo (các loại)','2kg–5kg','Active','FrontEnd/assets/svg/1/frozen.svg',NULL),(6,'Hạt (đậu, mè, yến mạch…) ','200g–500g','Active','FrontEnd/assets/svg/1/milk.svg',NULL),(7,'Gia vị khô (tỏi, tiêu, ớt khô…)','100g–200g','Active','FrontEnd/assets/svg/1/pet.svg',NULL),(8,'Nấm tươi (kim châm, bào ngư…)','250g–400g','Active','FrontEnd/assets/svg/1/biscuit.svg',NULL),(9,'Rau gia vị (hành, ngò…)','100g–150g','Active','FrontEnd/assets/svg/1/grocery.svg',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoryweight`
--

DROP TABLE IF EXISTS `categoryweight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoryweight` (
  `CategoryWeightID` int NOT NULL AUTO_INCREMENT,
  `CategoryID` int NOT NULL,
  `weight` double DEFAULT NULL,
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CategoryWeightID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `categoryweight_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoryweight`
--

LOCK TABLES `categoryweight` WRITE;
/*!40000 ALTER TABLE `categoryweight` DISABLE KEYS */;
INSERT INTO `categoryweight` VALUES (1,1,300,'g'),(2,1,350,'g'),(3,1,400,'g'),(4,1,500,'g'),(5,2,500,'g'),(6,2,650,'g'),(7,2,800,'g'),(8,2,1000,'g'),(9,3,1,'kg'),(10,3,1.15,'kg'),(11,3,1.3,'kg'),(12,3,1.5,'kg'),(13,4,2,'kg'),(14,4,2.6,'kg'),(15,4,3.2,'kg'),(16,4,4,'kg'),(17,5,2,'kg'),(18,5,3,'kg'),(19,5,4,'kg'),(20,5,5,'kg'),(21,6,200,'g'),(22,6,300,'g'),(23,6,400,'g'),(24,6,500,'g'),(25,7,100,'g'),(26,7,130,'g'),(27,7,160,'g'),(28,7,200,'g'),(29,8,250,'g'),(30,8,300,'g'),(31,8,350,'g'),(32,8,400,'g'),(33,9,100,'g'),(34,9,115,'g'),(35,9,130,'g'),(36,9,150,'g');
/*!40000 ALTER TABLE `categoryweight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentblog`
--

DROP TABLE IF EXISTS `commentblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentblog` (
  `commentblogid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `blogid` int NOT NULL,
  `commenttext` text,
  `likes` int DEFAULT '0',
  `dislikes` int DEFAULT '0',
  `status` varchar(50) DEFAULT 'Active',
  `createdat` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentblogid`),
  KEY `userid` (`userid`),
  KEY `blogid` (`blogid`),
  CONSTRAINT `commentblog_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`),
  CONSTRAINT `commentblog_ibfk_2` FOREIGN KEY (`blogid`) REFERENCES `blog` (`blogid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentblog`
--

LOCK TABLES `commentblog` WRITE;
/*!40000 ALTER TABLE `commentblog` DISABLE KEYS */;
INSERT INTO `commentblog` VALUES (6,40,36,'hay quá\r\n',0,0,'Active','2025-07-24 03:49:19'),(7,40,36,'tuyệt vời quá\r\n',0,0,'Active','2025-07-24 03:50:22'),(8,40,35,'\"Sao anh không về chơi thôn Vĩ?\r\nNhìn nắng hàng cau nắng mới lên.\r\nVườn ai mướt quá xanh như ngọc\r\nLá trúc che ngang mặt chữ điền.\r\nGió theo lối gió, mây đường mây,\r\nDòng nước buồn thiu, hoa bắp lay...\r\nThuyền ai đậu bến sông Trăng đó,\r\nCó chở trăng về kịp tối nay?',0,0,'Active','2025-07-24 03:51:01'),(9,40,36,'Trước lầu Ngưng Bích khóa xuân,\r\n\r\nVẻ non xa tấm trăng gần ở chung.\r\n\r\nBốn bề bát ngát xa trông,\r\n\r\nCát vàng cồn nọ bụi hồng dặm kia.\r\n\r\nBẽ bàng mây sớm đèn khuya,\r\n\r\nNửa tình nửa cảnh như chia tấm lòng.\r\n\r\nTưởng người dưới nguyệt chén đồng,\r\n\r\nTin sương luống những rày trông mai chờ.\r\n\r\nBên trời góc bể bơ vơ,\r\n\r\nTấm son gột rửa bao giờ cho phai.\r\n\r\nXót người tựa cửa hôm mai,\r\n\r\nQuạt nồng ấp lạnh những ai đó giờ?\r\n\r\nSân Lai cách mấy nắng mưa,\r\n\r\nCó khi gốc tử đã vừa người ôm.\r\n\r\nBuồn trông cửa bể chiều hôm,\r\n\r\nThuyền ai thấp thoáng cánh buồm xa xa?\r\n\r\nBuồn trông ngọn nước mới sa,\r\n\r\nHoa trôi man mác biết là về đâu?\r\n\r\nBuồn trông nội cỏ rầu rầu,\r\n\r\nChân mây mặt đất một màu xanh xanh.\r\n\r\nBuồn trông gió cuốn mặt duềnh,\r\n\r\nẦm ầm tiếng sóng kêu quanh ghế ngồi.',0,0,'Active','2025-07-24 03:52:06'),(10,40,34,'Trước lầu Ngưng Bích khóa xuân,\r\n\r\nVẻ non xa tấm trăng gần ở chung.\r\n\r\nBốn bề bát ngát xa trông,\r\n\r\nCát vàng cồn nọ bụi hồng dặm kia.\r\n\r\nBẽ bàng mây sớm đèn khuya,\r\n\r\nNửa tình nửa cảnh như chia tấm lòng.\r\n\r\nTưởng người dưới nguyệt chén đồng,\r\n\r\nTin sương luống những rày trông mai chờ.\r\n\r\nBên trời góc bể bơ vơ,\r\n\r\nTấm son gột rửa bao giờ cho phai.\r\n\r\nXót người tựa cửa hôm mai,\r\n\r\nQuạt nồng ấp lạnh những ai đó giờ?\r\n\r\nSân Lai cách mấy nắng mưa,\r\n\r\nCó khi gốc tử đã vừa người ôm.\r\n\r\nBuồn trông cửa bể chiều hôm,\r\n\r\nThuyền ai thấp thoáng cánh buồm xa xa?\r\n\r\nBuồn trông ngọn nước mới sa,\r\n\r\nHoa trôi man mác biết là về đâu?\r\n\r\nBuồn trông nội cỏ rầu rầu,\r\n\r\nChân mây mặt đất một màu xanh xanh.\r\n\r\nBuồn trông gió cuốn mặt duềnh,\r\n\r\nẦm ầm tiếng sóng kêu quanh ghế ngồi.',0,0,'Active','2025-07-24 03:52:34'),(11,40,33,'Trước lầu Ngưng Bích khóa xuân,\r\n\r\nVẻ non xa tấm trăng gần ở chung.\r\n\r\nBốn bề bát ngát xa trông,\r\n\r\nCát vàng cồn nọ bụi hồng dặm kia.\r\n\r\nBẽ bàng mây sớm đèn khuya,\r\n\r\nNửa tình nửa cảnh như chia tấm lòng.\r\n\r\nTưởng người dưới nguyệt chén đồng,\r\n\r\nTin sương luống những rày trông mai chờ.\r\n\r\nBên trời góc bể bơ vơ,\r\n\r\nTấm son gột rửa bao giờ cho phai.\r\n\r\nXót người tựa cửa hôm mai,\r\n\r\nQuạt nồng ấp lạnh những ai đó giờ?\r\n\r\nSân Lai cách mấy nắng mưa,\r\n\r\nCó khi gốc tử đã vừa người ôm.\r\n\r\nBuồn trông cửa bể chiều hôm,\r\n\r\nThuyền ai thấp thoáng cánh buồm xa xa?\r\n\r\nBuồn trông ngọn nước mới sa,\r\n\r\nHoa trôi man mác biết là về đâu?\r\n\r\nBuồn trông nội cỏ rầu rầu,\r\n\r\nChân mây mặt đất một màu xanh xanh.\r\n\r\nBuồn trông gió cuốn mặt duềnh,\r\n\r\nẦm ầm tiếng sóng kêu quanh ghế ngồi.',0,0,'Active','2025-07-24 03:52:45'),(12,40,39,'hay quá ta ..',0,0,'Active','2025-07-24 04:08:23'),(13,40,39,'jo\r\n',0,0,'Active','2025-07-25 13:22:03');
/*!40000 ALTER TABLE `commentblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentproduct`
--

DROP TABLE IF EXISTS `commentproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentproduct` (
  `CommentProductID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `CommentText` text,
  `Dislikes` int DEFAULT '0',
  `Status` varchar(50) DEFAULT NULL,
  `Rating` int DEFAULT '0',
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `Likes` int DEFAULT '0',
  `productid` int NOT NULL,
  PRIMARY KEY (`CommentProductID`),
  KEY `UserID` (`UserID`),
  KEY `FKqtfscvk0n3d0nvsalgqir56lv` (`productid`),
  CONSTRAINT `commentproduct_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `FKqtfscvk0n3d0nvsalgqir56lv` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentproduct`
--

LOCK TABLES `commentproduct` WRITE;
/*!40000 ALTER TABLE `commentproduct` DISABLE KEYS */;
INSERT INTO `commentproduct` VALUES (1,23,'Very fresh carrots!',6,'Visible',5,'2025-06-18 08:20:21',5,1),(2,23,'Good price and fast delivery.',7,'Visible',4,'2025-06-18 08:20:21',9,1),(3,3,'Crisp and sweet apples.',0,'Visible',5,'2025-06-18 08:20:21',0,2),(4,1,'The beef was not very fresh.',1,'Reported',2,'2025-06-18 08:20:21',0,4),(5,4,'Great shrimp, firm and delicious.',0,'Visible',5,'2025-06-18 08:20:21',0,4),(6,5,'Decent salt, value for money.',0,'Visible',4,'2025-06-18 08:20:21',0,5),(7,1,'Fragrant and relaxing tea.',0,'Visible',5,'2025-06-18 08:20:21',0,6),(8,3,'Rice is really aromatic and soft.',0,'Visible',5,'2025-06-18 08:20:21',0,7),(9,2,'Easy to drink, my kids love it.',0,'Visible',5,'2025-06-18 08:20:21',0,8),(10,4,'A bit too sweet for my taste.',0,'Visible',3,'2025-06-18 08:20:21',0,9),(11,2,'A bit too sweet for my taste.',1,'Visible',2,'2025-06-18 08:20:21',1,11),(12,23,'hoa quả ngon',3,'Reported',5,'2025-06-28 16:33:44',1,1),(13,23,'ko ngon',2,'Reported',1,'2025-06-28 17:10:29',3,1),(14,23,'ko ngon',6,'Reported',1,'2025-06-28 17:10:52',5,1),(15,22,'hoa quả phun thuốc sâu',1,'Reported',1,'2025-06-28 18:17:46',3,1),(16,22,'hoa quả ko tươi lắm',0,'Reported',3,'2025-06-28 18:19:58',0,1),(17,23,'hàng trung quốc à web',0,'Reported',2,'2025-06-28 18:40:05',1,1),(18,23,'cam phun thuốc à web',0,'Visible',1,'2025-06-29 09:15:29',2,2),(19,23,'ngon',0,'Visible',5,'2025-06-30 14:17:45',0,55),(20,11,'được',5,'Visible',4,'2025-07-10 21:25:23',3,10),(21,32,'ngon',1,'Visible',5,'2025-07-18 17:41:49',1,4);
/*!40000 ALTER TABLE `commentproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactus`
--

DROP TABLE IF EXISTS `contactus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactus` (
  `contactusid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`contactusid`),
  KEY `FKc52kupwktdtfwwjldb8mplxxn` (`userid`),
  CONSTRAINT `FKc52kupwktdtfwwjldb8mplxxn` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactus`
--

LOCK TABLES `contactus` WRITE;
/*!40000 ALTER TABLE `contactus` DISABLE KEYS */;
INSERT INTO `contactus` VALUES (1,'Thạch Thất, Hà Nội','2025-06-24 14:42:17.937000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','hay vậy ta wowwwww','0969652156',22),(2,'Nghệ An province','2025-07-14 15:51:28.727000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','kkkkkkkkkkkkkkkkkkkkkk h','0969652156',30),(3,'Thạch Thất, Hà Nội','2025-07-23 00:18:46.321000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','sdasdasdsaassas','0969652151',33);
/*!40000 ALTER TABLE `contactus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `DeviceID` int NOT NULL AUTO_INCREMENT,
  `WarehouseID` int NOT NULL,
  `DeviceName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Description` text,
  PRIMARY KEY (`DeviceID`),
  KEY `WarehouseID` (`WarehouseID`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgot_password`
--

DROP TABLE IF EXISTS `forgot_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_password` (
  `fpid` int NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) NOT NULL,
  `otp` int NOT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`fpid`),
  UNIQUE KEY `UKpft6qo9eo7qajhvgi97pt73kk` (`userid`),
  CONSTRAINT `FKi4guet8gckf9pkf7jtwbd9cj` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_password`
--

LOCK TABLES `forgot_password` WRITE;
/*!40000 ALTER TABLE `forgot_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `forgot_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery` (
  `galleryid` bigint NOT NULL AUTO_INCREMENT,
  `ProductID` int DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`galleryid`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (6,6,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(7,7,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(8,8,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(9,9,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(10,10,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(11,11,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(12,12,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(13,13,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(14,14,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(15,15,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(16,16,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(17,17,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(18,18,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(19,19,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(20,20,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(21,21,'FrontEnd/assets/images/veg-2/product/21.png'),(22,22,'FrontEnd/assets/images/veg-2/product/21.png'),(23,23,'FrontEnd/assets/images/veg-2/product/21.png'),(24,24,'FrontEnd/assets/images/veg-2/product/21.png'),(25,25,'FrontEnd/assets/images/veg-2/product/21.png'),(26,26,'FrontEnd/assets/images/veg-2/product/21.png'),(27,27,'FrontEnd/assets/images/veg-2/product/21.png'),(28,28,'FrontEnd/assets/images/veg-2/product/21.png'),(29,29,'FrontEnd/assets/images/veg-2/product/21.png'),(30,30,'FrontEnd/assets/images/veg-2/product/21.png'),(31,31,'FrontEnd/assets/images/veg-2/product/21.png'),(32,32,'FrontEnd/assets/images/veg-2/product/21.png'),(33,33,'FrontEnd/assets/images/veg-2/product/21.png'),(34,34,'FrontEnd/assets/images/veg-2/product/21.png'),(35,35,'FrontEnd/assets/images/veg-2/product/21.png'),(36,36,'FrontEnd/assets/images/veg-2/product/21.png'),(37,37,'FrontEnd/assets/images/veg-2/product/21.png'),(38,38,'FrontEnd/assets/images/veg-2/product/22.png'),(39,39,'FrontEnd/assets/images/veg-2/product/23.png'),(40,40,'FrontEnd/assets/images/veg-2/product/23.png'),(41,41,'FrontEnd/assets/images/veg-2/product/23.png'),(42,42,'FrontEnd/assets/images/veg-2/product/23.png'),(43,43,'FrontEnd/assets/images/veg-2/product/23.png'),(44,44,'FrontEnd/assets/images/veg-2/product/23.png'),(45,45,'FrontEnd/assets/images/veg-2/product/23.png'),(46,46,'FrontEnd/assets/images/veg-2/product/23.png'),(47,47,'FrontEnd/assets/images/veg-2/product/23.png'),(48,48,'FrontEnd/assets/images/veg-2/product/23.png'),(49,49,'FrontEnd/assets/images/veg-2/product/23.png'),(50,50,'FrontEnd/assets/images/veg-2/product/23.png'),(51,51,'FrontEnd/assets/images/veg-2/product/23.png'),(52,52,'FrontEnd/assets/images/veg-2/product/23.png'),(53,53,'FrontEnd/assets/images/veg-2/product/23.png'),(54,54,'FrontEnd/assets/images/veg-2/product/23.png'),(55,55,'FrontEnd/assets/images/veg-2/product/23.png'),(56,56,'FrontEnd/assets/images/veg-2/product/23.png'),(57,57,'FrontEnd/assets/images/veg-2/product/23.png'),(58,58,'FrontEnd/assets/images/veg-2/product/23.png'),(59,59,'FrontEnd/assets/images/veg-2/product/23.png'),(60,60,'FrontEnd/assets/images/veg-2/product/23.png'),(61,61,'FrontEnd/assets/images/veg-2/product/23.png'),(62,62,'FrontEnd/assets/images/veg-2/product/23.png'),(63,63,'FrontEnd/assets/images/veg-2/product/23.png'),(81,69,'e67d836c-389d-4305-a128-7a22b5b970bb.742d672d-9780-47a8-b89a-abf81b9c3f8b.meme-meo-an-bong-ngo-gif.gif'),(82,1,'db835061-72e6-404a-a55d-e1bd3bd2e14f.jpg'),(87,2,'918ee510-9005-4bb5-8c3c-49e3300ea442.gif'),(88,2,'087c0cd7-dafe-45bb-979c-cb53dce7094b.gif'),(89,2,'274a7e88-b1d3-469b-895a-8ae8d13a3492.gif'),(90,2,'8b2c0291-ba9d-4781-a6df-9a0db9d5e8e9.gif'),(91,2,'8633ee7b-ab71-442b-8cbe-66978500589b.gif'),(92,3,'84a1ba03-a228-4b20-a32c-20d04821edad.gif'),(93,3,'20e09855-f025-4a58-bb80-47c92e0105b6.gif'),(94,3,'6f516a5e-656d-4edd-8801-2921282e842b.gif'),(95,3,'73185e98-b708-4590-b39c-d37ddc7a6dcc.gif'),(96,4,'722492e4-1902-4261-a2f2-03263ab15dc4.gif'),(98,4,'4a321c59-0264-4936-9292-22b3fd1574c1.gif'),(99,4,'b8cd44bb-7cf4-4540-9440-872c3a867494.gif'),(100,4,'6f593625-9862-4dc7-9139-47ec2a77ae2c.gif');
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `InventoryID` int NOT NULL AUTO_INCREMENT,
  `ProductDetailID` int DEFAULT NULL,
  `WarehouseID` int DEFAULT NULL,
  `BatchID` int DEFAULT NULL,
  `QuantityInStock` int DEFAULT '0',
  `LastUpdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`InventoryID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  KEY `WarehouseID` (`WarehouseID`),
  KEY `BatchID` (`BatchID`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`),
  CONSTRAINT `inventory_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myaddressbook`
--

DROP TABLE IF EXISTS `myaddressbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myaddressbook` (
  `addressbookid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `settoaddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`addressbookid`),
  KEY `FK4vkcjh44w6q01hrgaj692er1c` (`userid`),
  CONSTRAINT `FK4vkcjh44w6q01hrgaj692er1c` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myaddressbook`
--

LOCK TABLES `myaddressbook` WRITE;
/*!40000 ALTER TABLE `myaddressbook` DISABLE KEYS */;
INSERT INTO `myaddressbook` VALUES (1,'Nghệ An province','2025-06-23 16:40:29.397000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','hay nha',18),(2,'nghe an','2025-07-01 12:09:43.925000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','Home',23),(3,'Nghệ An province','2025-07-01 12:27:11.261000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','a',24),(4,'Nghệ An province','2025-07-01 12:27:25.736000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','Home',24),(5,'nghe an','2025-07-01 12:32:44.050000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','q','0969652156','home',25),(6,'Nghệ An province','2025-07-01 12:38:21.151000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','sdsdas','0969652156','home',25),(12,'nghe an','2025-07-03 16:04:39.856000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé','0969652156','Home',26),(13,'Thạch Thất, Hà Nội','2025-07-03 16:09:53.527000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship về đó','0969652156','Home',26),(14,'Thạch Thất, Hà Nội','2025-07-07 17:06:02.726000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship đến trọ','0969652156','trọ',29),(15,'hanoi','2025-07-10 21:28:25.327000','anhnhhe186218@fpt.edu.vn','Nguyễn Hoàng Anh','ship đến','0969652156','Trọ',11),(18,'nghe an','2025-07-14 16:38:47.192000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship đến đó','0969652156','nhà',30),(19,'Thạch Thất, Hà Nội','2025-07-16 07:40:17.094000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship đến đó','0969652156','nhà',31),(20,'Thị xã Hoàng Mai, Tỉnh Nghệ An','2025-07-18 17:47:50.610000','nguyenhoanganh712004qp@gmail.com','Nguyễn Hoàng Anh','Ship đến','0969652156','Nhà',32),(21,'Thị xã Hoàng Mai, Tỉnh Nghệ An','2025-07-18 18:28:01.645000','nguyenhoanganh712004qp@gmail.com','Nguyễn Hoàng Anh','Ship ','0969652156','Nhà',33),(22,'nghe an','2025-07-20 10:06:29.095000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','s','0969652156','s',33),(23,'Nghệ An province','2025-07-20 10:06:47.308000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','s','0969652156','nhà',33),(24,'Nghệ An province','2025-07-20 10:06:50.437000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','s','0969652156','nhà',33),(25,'Thạch Thất, Hà Nội','2025-07-21 02:33:07.028000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship','0969652156','nhà',34),(26,'Nghệ An','2025-07-23 03:09:57.933000','nguyenhoanganh712004qp@gmail.com','Nguyễn Hoàng Anh','s','0969652156','nhà',35),(27,'Thạch Thất, Hà Nội','2025-07-23 15:24:21.352000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship','0969652156','nhà',37),(28,'Ba Vì','2025-07-24 00:51:21.511000','daohuyhoang507@gmail.com','Nguyễn Hoàng Anh','123','0923456789','Số nhà 01',39),(29,'Ba Vì','2025-07-24 00:51:41.226000','daohuyhoang507@gmail.com','Đào Hoàng Anh','123','0923456789','Số nhà 01',39),(30,'Ba Vì','2025-07-24 16:09:47.380000','daohuyhoang507@gmail.com','Đào Huy Hoàng','sdsds','0912345678','Số nhà 01',40),(31,'Ba Vì','2025-07-25 13:07:07.387000','daohuyhoang507@gmail.com','Đào Huy Hoàng','dd','0912345678','Số nhà 01',48);
/*!40000 ALTER TABLE `myaddressbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderid` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount_amount` decimal(15,2) DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `final_amount` decimal(15,2) NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordercode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `orderdate` datetime DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `totalamount` decimal(15,2) NOT NULL,
  `voucher_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `userid` int NOT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `UKs6ggvnoh3hl99a6td88vqvmfx` (`ordercode`),
  KEY `FK4yfk7m9cf7n5689y0c1j44e64` (`userid`),
  KEY `FKnrduwglsych0g717gihrtflbu` (`voucher_id`),
  CONSTRAINT `FK4yfk7m9cf7n5689y0c1j44e64` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`),
  CONSTRAINT `FKnrduwglsych0g717gihrtflbu` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'123 Le Loi, HCM',0.00,'a@example.com',150000.00,'Nguyen Van A','Giao buổi sáng','ORD5823941','2025-06-11 10:15:00','0912345678','CANCELLED',150000.00,NULL,1,NULL),(2,'456 Nguyen Hue, HCM',0.00,'b@example.com',250000.00,'Tran Thi B','Không cay','#ORD3319058','2025-06-11 11:00:00','0923456789','CANCELLED',250000.00,NULL,2,NULL),(3,'789 Tran Hung Dao, HCM',0.00,'c@example.com',350000.00,'Le Van C','Giao giờ hành chính','#ORD2034876','2025-06-11 12:30:00','0934567890','CANCELLED',350000.00,NULL,3,NULL),(4,'1010 Cach Mang Thang 8, HCM',0.00,'d@example.com',50000.00,'Pham Thi D','Đặt nhầm','#ORD4901732','2025-06-11 13:45:00','0945678901','CONFIRMED',50000.00,NULL,4,NULL),(5,'1212 Vo Van Tan, HCM',0.00,'e@example.com',420000.00,'Vo Van E','Giao chiều muộn','#ORD7649205','2025-06-11 15:00:00','0956789012','CONFIRMED',420000.00,NULL,5,NULL),(6,'789 Tran Hung Dao, HCM',0.00,'c@example.com',185000.00,'Le Thi C','Chưa xác định','#ORD0193842','2025-06-13 14:30:00','0934567890','CONFIRMED',185000.00,NULL,3,NULL),(7,'321 Cach Mang Thang 8, HCM',0.00,'e@example.com',320000.00,'Pham Van E','Giao sau 6PM','#ORD8472109','2025-06-13 16:45:00','0945678901','CONFIRMED',320000.00,NULL,4,NULL),(8,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',257000.00,'Nguyễn Hoàng Anh','ship về trọ gửi về home','1','2025-07-03 16:05:22','0969652156','CONFIRMED',257000.00,'',26,NULL),(12,'Thạch Thất, Hà Nội',55000.00,'1234yeyeyeye1234@gmail.com',220000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','2','2025-07-03 16:21:42','0969652156','DELIVERED',275000.00,'SALE20P',26,1),(13,'nghe an',55000.00,'1234yeyeyeye1234@gmail.com',220000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','4','2025-07-03 16:44:53','0969652156','CANCELLED',275000.00,'SALE20P',26,1),(14,'Thạch Thất, Hà Nội',43000.00,'1234yeyeyeye1234@gmail.com',172000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','5','2025-07-03 16:47:53','0969652156','CONFIRMED',215000.00,'SALE20P',26,1),(15,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',15000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','3','2025-07-03 16:50:40','0969652156','CONFIRMED',15000.00,'',26,NULL),(16,'nghe an',5000.00,'1234yeyeyeye1234@gmail.com',55000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','6','2025-07-03 16:54:34','0969652156','PENDING',60000.00,'SALE5K',26,5),(17,'nghe an',5000.00,'1234yeyeyeye1234@gmail.com',55000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','7','2025-07-03 16:58:21','0969652156','PENDING',60000.00,'SALE5K',26,5),(18,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','99','2025-07-04 15:29:13','0969652156','PENDING',2000.00,'',26,NULL),(19,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','236487','2025-08-05 16:28:10','0969652156','PENDING',2000.00,'',26,NULL),(20,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','118695','2025-07-04 16:59:02','0969652156','PENDING',2000.00,'',26,NULL),(21,'Thạch Thất, Hà Nội',5000.00,'1234yeyeyeye1234@gmail.com',56000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','215732','2025-07-05 17:34:01','0969652156','PENDING',61000.00,'SALE5K',26,5),(22,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',26111.00,'Nguyễn Hoàng Anh','ship đến trọ (trọ)','749803','2025-07-07 17:42:05','0969652156','PENDING',26111.00,'',29,NULL),(23,'Thạch Thất, Hà Nội',5000.00,'1234yeyeyeye1234@gmail.com',66111.00,'Nguyễn Hoàng Anh','ship đến trọ (trọ)','479190','2025-07-07 17:51:52','0969652156','PENDING',71111.00,'SALE5K',29,5),(24,'hanoi',5000.00,'anhnhhe186218@fpt.edu.vn',60000.00,'Nguyễn Hoàng Anh','ship đến (Trọ)','724958','2025-07-10 21:29:33','0969652156','CONFIRMED',65000.00,'SALE5K',11,5),(25,'Thị xã Hoàng Mai, Tỉnh Nghệ An',0.00,'nguyenhoanganh712004qp@gmail.com',15000.00,'Nguyễn Hoàng Anh','Ship  (Nhà)','140839','2025-07-18 18:29:39','0969652156','PENDING',15000.00,'',33,NULL),(26,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','s (s)','892284','2025-07-20 15:41:32','0969652156','CANCELLED',2000.00,'',33,NULL),(27,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',4000.00,'Nguyễn Hoàng Anh','s (s)','551113','2025-07-20 15:52:31','0969652156','CANCELLED',4000.00,'',33,NULL),(28,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',4000.00,'Nguyễn Hoàng Anh','s (s)','180644','2025-07-20 16:19:41','0969652156','PENDING',4000.00,'',33,NULL),(29,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',8000.00,'Nguyễn Hoàng Anh','s (s)','290043','2025-07-20 19:58:10','0969652156','CANCELLED',8000.00,'',33,NULL),(30,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','s (s)','888922','2025-07-21 02:14:49','0969652156','CANCELLED',2000.00,'',33,NULL),(31,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship (nhà)','66791','2025-07-23 15:25:24','0969652156','CANCELLED',2000.00,'',37,NULL),(32,'Ba Vì',0.00,'daohuyhoang507@gmail.com',2000.00,'Đào Huy Hoàng','sdsds (Số nhà 01)','195120','2025-07-24 16:09:55','0912345678','COMPLETED',2000.00,'',40,NULL),(33,'Ba Vì',0.00,'daohuyhoang507@gmail.com',15000.00,'Đào Huy Hoàng','sdsds (Số nhà 01)','541962','2025-07-24 17:22:22','0912345678','PENDING',15000.00,'',40,NULL),(34,'Ba Vì',27000.00,'daohuyhoang507@gmail.com',153000.00,'Đào Huy Hoàng','sdsds (Số nhà 01)','290579','2025-07-25 12:28:11','0912345678','PENDING',180000.00,'COMINGSOON',40,4),(35,'Ba Vì',50000.00,'daohuyhoang507@gmail.com',130000.00,'Đào Huy Hoàng','sdsds (Số nhà 01)','556185','2025-07-25 12:32:36','0912345678','PENDING',180000.00,'EXPIRED50',40,3),(36,'Ba Vì',50000.00,'daohuyhoang507@gmail.com',67000.00,'Đào Huy Hoàng','dd (Số nhà 01)','772780','2025-07-25 13:09:33','0912345678','PENDING',117000.00,'EXPIRED50',48,3);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `orderdetailid` bigint NOT NULL AUTO_INCREMENT,
  `price` decimal(15,2) NOT NULL,
  `productdetailid` bigint NOT NULL,
  `quantity` int NOT NULL,
  `orderid` bigint NOT NULL,
  PRIMARY KEY (`orderdetailid`),
  KEY `FK2ddhnw8i3tl2x1fxrl5g1navn` (`orderid`),
  CONSTRAINT `FK2ddhnw8i3tl2x1fxrl5g1navn` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (1,240000.00,2,1,1),(2,120000.00,1,1,1),(3,240000.00,3,1,2),(4,225000.00,5,1,3),(5,80000.00,1,1,2),(6,90000.00,2,1,3),(7,105000.00,6,3,8),(8,20000.00,9,1,8),(9,132000.00,22,6,8),(10,220000.00,22,10,12),(11,20000.00,9,1,12),(12,35000.00,6,1,12),(13,220000.00,22,10,13),(14,20000.00,9,1,13),(15,35000.00,6,1,13),(16,90000.00,8,3,14),(17,125000.00,19,5,14),(18,15000.00,13,1,15),(19,60000.00,13,4,16),(20,60000.00,13,4,17),(21,2000.00,5,1,18),(22,2000.00,5,1,19),(23,2000.00,5,1,20),(24,4000.00,5,2,21),(25,35000.00,6,1,21),(26,22000.00,10,1,21),(27,15000.00,1,1,22),(28,11111.00,64,1,22),(29,60000.00,1,4,23),(30,11111.00,64,1,23),(31,35000.00,6,1,24),(32,30000.00,8,1,24),(33,15000.00,1,1,25),(34,2000.00,5,1,26),(35,4000.00,5,2,27),(36,4000.00,5,2,28),(37,8000.00,5,4,29),(38,2000.00,5,1,30),(39,2000.00,5,1,31),(40,2000.00,5,1,32),(41,15000.00,13,1,33),(42,180000.00,4,1,34),(43,180000.00,4,1,35),(44,117000.00,12,9,36);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderreview`
--

DROP TABLE IF EXISTS `orderreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderreview` (
  `orderreviewid` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordercode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` int DEFAULT '0',
  `updatetimeform` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `updatetimeimage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `userid` int NOT NULL,
  `updatedatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`orderreviewid`),
  UNIQUE KEY `UKkq9uiw4kgtnmdkglb4jol6l2w` (`ordercode`),
  KEY `FKrqrt8h1swocpqc80xulolk234` (`userid`),
  CONSTRAINT `FKrqrt8h1swocpqc80xulolk234` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderreview`
--

LOCK TABLES `orderreview` WRITE;
/*!40000 ALTER TABLE `orderreview` DISABLE KEYS */;
INSERT INTO `orderreview` VALUES (1,'','Thực phẩm sạch nha sếp ơi','215732',5,'0','0',26,'2025-07-07 16:55:39'),(2,'2','2','118695',2,'2','2',26,NULL),(3,'/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1751885531739_download.jpg','Hoa quả sạch','479190',4,'1','1',29,'2025-07-07 17:52:32'),(4,'/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1752157814728_piclumenmarquee-06.webp','ngon','724958',4,'1','1',11,'2025-07-10 21:29:59'),(5,'/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/1752838211507_piclumenmarquee-06.webp','sản phẩm ngon','140839',5,'1','1',33,'2025-07-18 18:30:02'),(6,'','','892284',0,'0','0',33,NULL),(7,'','','551113',0,'0','0',33,NULL),(8,'','','180644',0,'0','0',33,NULL),(9,'','','290043',0,'0','0',33,NULL),(10,'','','888922',0,'0','0',33,NULL),(11,'','','66791',0,'0','0',37,NULL),(12,'1753349179445_meme-meo-an-bong-ngo-gif.gif','','195120',0,'0','1',40,NULL),(13,'','','541962',0,'0','0',40,NULL),(14,'','','290579',0,'0','0',40,NULL),(15,'','','556185',0,'0','0',40,NULL),(16,'','','772780',0,'0','0',48,NULL);
/*!40000 ALTER TABLE `orderreview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `PaymentDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Amount` decimal(15,2) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `OrderID` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `CategoryID` int DEFAULT NULL,
  `ProductName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'Cà rốt Vn','Sản phẩm Táo nhập khẩu từ Hoa Kỳ là một trong những loại trái cây được yêu thích nhất trên toàn thế giới, không chỉ bởi hương vị thơm ngon mà còn vì những lợi ích tuyệt vời đối với sức khỏe. Những trái táo này được chọn lọc từ những vườn táo nổi tiếng tại các vùng đất trù phú ở Hoa Kỳ, nơi có điều kiện khí hậu lý tưởng và quy trình canh tác hiện đại. Chính vì vậy, bạn hoàn toàn có thể yên tâm về chất lượng của từng trái táo, từ khi chúng được thu hoạch cho đến khi đến tay bạn.','ACTIVE'),(2,2,'Táo Mỹ s','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(3,1,'Thăn bò Úc đông lạnh','Thịt bò thăn đông lạnh','ACTIVE'),(4,1,'Tôm sú biển đông lạnh','Tôm sú nguyên con đông lạnh','ACTIVE'),(5,1,'Muối i-ốt','Muối i-ốt tinh khiết','ACTIVE'),(6,1,'Trá trà atiso','Trá atiso túi lọc','ACTIVE'),(7,7,'Gạo ST25','Gạo thơm ST25 chất lượng cao','ACTIVE'),(8,8,'Sữa Vinamilk','Sữa tươi tiệt trùng','ACTIVE'),(9,1,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống','ACTIVE'),(10,1,'Bún dong','Bún dong khô sạch','ACTIVE'),(11,9,'kẹo','Clean dried glass noodles','ACTIVE'),(12,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(13,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(14,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(15,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(16,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(17,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(18,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(19,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(20,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(21,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(22,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(23,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(24,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(25,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(26,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(27,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(28,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(29,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(30,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(31,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(32,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(33,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(34,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(35,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(36,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(37,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(38,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(39,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(40,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(41,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(42,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(43,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(44,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(45,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(46,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(47,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(48,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(49,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(50,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(51,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(52,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(53,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(54,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(55,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(56,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(57,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(58,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(59,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(60,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(61,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(62,1,'cà ro','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(63,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(64,1,'ao len','s','ACTIVE'),(65,1,'ssdsdsd','12','ACTIVE'),(66,1,'123','123','ACTIVE'),(67,1,'cà','123','ACTIVE'),(68,1,'cà chua','','ACTIVE'),(69,2,'ddgdgdg','dgdggd','ACTIVE'),(70,2,'rau cải bắp xanh','','ACTIVE');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productbatch`
--

DROP TABLE IF EXISTS `productbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productbatch` (
  `BatchID` int NOT NULL AUTO_INCREMENT,
  `ProductDetailID` int DEFAULT NULL,
  `manufacturedate` date DEFAULT NULL,
  `importedquantity` int DEFAULT NULL,
  `soldquantity` int DEFAULT NULL,
  PRIMARY KEY (`BatchID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `productbatch_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatch`
--

LOCK TABLES `productbatch` WRITE;
/*!40000 ALTER TABLE `productbatch` DISABLE KEYS */;
INSERT INTO `productbatch` VALUES (1,1,'2025-07-04',109,0),(2,2,'2025-06-03',121,0),(3,3,'2025-07-03',99,0),(4,1,'2024-06-24',100,0),(5,2,'2025-06-07',110,0),(6,1,'2025-06-13',100,0),(7,4,'2025-06-02',115,0),(8,5,'2025-06-04',120,0),(9,6,'2025-06-06',110,0),(10,7,'2025-06-08',100,0),(11,8,'2025-06-10',105,0),(12,9,'2025-06-12',115,0),(13,10,'2025-06-14',122,0),(14,11,'2025-06-16',119,0),(15,12,'2025-06-18',101,0),(16,13,'2025-06-20',111,0),(17,14,'2025-07-03',123,0),(18,15,'2025-06-24',117,0),(19,1,'2025-06-26',120,0),(20,2,'2025-06-28',100,0),(21,3,'2025-06-30',108,1),(22,4,'2025-07-02',121,0),(23,5,'2025-07-04',115,0),(24,6,'2025-07-03',110,0),(25,7,'2025-07-08',100,0),(26,8,'2025-07-10',105,0),(27,9,'2025-07-12',115,0),(28,10,'2025-07-14',122,0),(29,11,'2025-07-16',119,0),(30,12,'2025-07-18',101,0),(31,13,'2025-07-20',111,0),(32,14,'2025-07-21',122,0),(33,15,'2025-07-24',117,0),(34,4,'2025-08-01',112,0),(35,5,'2025-07-13',110,0),(36,2,'2024-06-06',120,0),(37,7,'2025-08-07',115,0),(38,8,'2025-08-09',120,0),(39,9,'2025-08-11',110,0),(40,10,'2025-08-13',105,0),(41,12,'2025-08-17',122,0),(42,13,'2025-08-19',101,0),(43,3,'2025-08-21',125,0),(44,15,'2025-08-23',103,0),(45,1,'2025-08-25',100,0),(46,2,'2025-08-27',117,0),(47,3,'2025-08-29',114,0),(48,5,'2025-09-02',102,0),(49,6,'2025-09-04',111,0),(50,7,'2023-10-06',2,0),(51,1,'2025-07-04',1,0),(52,6,'2025-07-04',1,0),(53,13,'2025-07-24',110,0),(54,11,'2025-07-04',122,0),(55,11,'2025-07-06',100,0),(56,4,'2025-07-04',100,0),(57,7,'2025-07-02',1,0),(58,15,'2025-07-07',23,0),(59,14,'2025-01-02',11,0),(60,5,'2025-07-18',10,0),(61,3,'2025-07-16',100,0),(62,14,'2025-07-22',100,0),(63,15,'2025-07-23',11,0),(64,1,'2021-07-09',9,0),(65,1,'2025-05-01',50,1);
/*!40000 ALTER TABLE `productbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productdetail`
--

DROP TABLE IF EXISTS `productdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productdetail` (
  `ProductDetailID` int NOT NULL AUTO_INCREMENT,
  `productid` int NOT NULL,
  `price` double DEFAULT NULL,
  `CategoryWeightID` int DEFAULT NULL,
  `Expiry` int DEFAULT NULL,
  `detailname` varchar(500) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  PRIMARY KEY (`ProductDetailID`),
  KEY `FK5tqssatrmfiu9ayh3futup0wu` (`productid`),
  KEY `FKejqb8da7lrkwppkkyi56w6r84` (`CategoryWeightID`),
  CONSTRAINT `FK5tqssatrmfiu9ayh3futup0wu` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`),
  CONSTRAINT `FKejqb8da7lrkwppkkyi56w6r84` FOREIGN KEY (`CategoryWeightID`) REFERENCES `categoryweight` (`CategoryWeightID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetail`
--

LOCK TABLES `productdetail` WRITE;
/*!40000 ALTER TABLE `productdetail` DISABLE KEYS */;
INSERT INTO `productdetail` VALUES (1,1,15000,1,7,'300 g','INACTIVE'),(2,2,45000,2,10,'1kg Bag','INACTIVE'),(3,2,120000,3,5,'800g Box','ACTIVE'),(4,4,180000,4,3,'1kg Pack','ACTIVE'),(5,5,2000,5,24,'500g Pack','ACTIVE'),(6,6,35000,6,12,'20 Tea Bags Box','ACTIVE'),(7,7,25000,7,60,'2kg Bag','ACTIVE'),(8,8,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(9,9,20000,9,180,'300g Pack','ACTIVE'),(10,10,22000,10,12,'500g Bag','ACTIVE'),(11,11,33000,11,12,'500g Bag','ACTIVE'),(12,12,13000,12,7,'500g Bag','ACTIVE'),(13,13,15000,1,7,'500g Bag','ACTIVE'),(14,14,45000,2,10,'1kg Bag','ACTIVE'),(15,15,120000,3,5,'800g Box','ACTIVE'),(16,16,180000,4,3,'1kg Pack','ACTIVE'),(17,17,10000,5,24,'500g Pack','ACTIVE'),(18,18,35000,6,12,'20 Tea Bags Box','ACTIVE'),(19,19,25000,7,60,'2kg Bag','ACTIVE'),(20,20,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(21,21,20000,9,180,'300g Pack','ACTIVE'),(22,22,22000,10,12,'500g Bag','ACTIVE'),(23,23,33000,11,12,'500g Bag','ACTIVE'),(24,24,13000,12,7,'500g Bag','ACTIVE'),(25,25,15000,1,7,'500g Bag','ACTIVE'),(26,26,45000,2,10,'1kg Bag','ACTIVE'),(27,27,120000,3,5,'800g Box','ACTIVE'),(28,28,180000,4,3,'1kg Pack','ACTIVE'),(29,29,10000,5,24,'500g Pack','ACTIVE'),(30,30,35000,6,12,'20 Tea Bags Box','ACTIVE'),(31,31,25000,7,60,'2kg Bag','ACTIVE'),(32,32,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(33,33,20000,9,180,'300g Pack','ACTIVE'),(34,34,22000,10,12,'500g Bag','ACTIVE'),(35,35,33000,11,12,'500g Bag','ACTIVE'),(36,36,13000,12,7,'500g Bag','ACTIVE'),(37,37,15000,1,7,'500g Bag','ACTIVE'),(38,38,45000,2,10,'1kg Bag','ACTIVE'),(39,39,120000,3,5,'800g Box','ACTIVE'),(40,40,180000,4,3,'1kg Pack','ACTIVE'),(41,41,15000,1,7,'500g Bag','ACTIVE'),(42,42,45000,2,10,'1kg Bag','ACTIVE'),(43,43,120000,3,5,'800g Box','ACTIVE'),(44,44,180000,4,3,'1kg Pack','ACTIVE'),(45,45,10000,5,24,'500g Pack','ACTIVE'),(46,46,35000,6,12,'20 Tea Bags Box','ACTIVE'),(47,47,25000,7,60,'2kg Bag','ACTIVE'),(48,48,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(49,49,20000,9,180,'300g Pack','ACTIVE'),(50,50,22000,10,12,'500g Bag','ACTIVE'),(51,51,33000,11,12,'500g Bag','ACTIVE'),(52,52,13000,12,7,'500g Bag','ACTIVE'),(53,53,15000,1,7,'500g Bag','ACTIVE'),(54,54,45000,2,10,'1kg Bag','ACTIVE'),(55,55,120000,3,5,'800g Box','ACTIVE'),(56,56,180000,4,3,'1kg Pack','ACTIVE'),(57,57,11111,5,3,'1kg Pack','ACTIVE'),(58,58,1111,5,3,'1kg Pack','ACTIVE'),(59,59,11111,5,3,'1kg Pack','ACTIVE'),(60,60,11111,5,3,'1kg Pack','ACTIVE'),(61,61,11111,5,3,'1kg Pack','ACTIVE'),(62,62,11111,5,3,'1kg Pack','ACTIVE'),(63,63,11111,5,3,'1kg Pack','ACTIVE'),(64,1,11111,5,3,'1kg Pack','ACTIVE'),(65,4,11111111,5,3,'1kg Pack','ACTIVE'),(67,12,12000,1,12,'300 g','ACTIVE'),(68,67,120000,3,12,'400 g','ACTIVE');
/*!40000 ALTER TABLE `productdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `RoleID` int NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Active',
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','Active',NULL),(2,'USER','Active',NULL),(3,'BLOGGER','INACTIVE',NULL),(4,'SELLER','Active',NULL),(5,'WAREHOUSE','Active',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_application`
--

DROP TABLE IF EXISTS `seller_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller_application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contract_expiry_date` date DEFAULT NULL,
  `contract_months` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `cv_image` varchar(255) NOT NULL,
  `status` enum('APPROVED','EXPIRED','PENDING','REJECTED') NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK839ux3h65s2wyso5hghutcx6l` (`user_id`),
  CONSTRAINT `FK839ux3h65s2wyso5hghutcx6l` FOREIGN KEY (`user_id`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_application`
--

LOCK TABLES `seller_application` WRITE;
/*!40000 ALTER TABLE `seller_application` DISABLE KEYS */;
INSERT INTO `seller_application` VALUES (1,NULL,3,'2025-07-25 07:15:07.656135','c86b9f21-2050-433e-a004-71912b12c9ba.jpg','PENDING',40);
/*!40000 ALTER TABLE `seller_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `ShipmentID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `ShipmentDate` datetime DEFAULT NULL,
  `DeliveryAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ShipmentID`),
  KEY `OrderID` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soldbyseller`
--

DROP TABLE IF EXISTS `soldbyseller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soldbyseller` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productdetailid` int NOT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46p0njok9v3e5hkwr81xq3i7w` (`productdetailid`),
  KEY `FKfr72eyepebfj2juqqin6fijqd` (`userid`),
  CONSTRAINT `FK46p0njok9v3e5hkwr81xq3i7w` FOREIGN KEY (`productdetailid`) REFERENCES `productdetail` (`ProductDetailID`),
  CONSTRAINT `FKfr72eyepebfj2juqqin6fijqd` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldbyseller`
--

LOCK TABLES `soldbyseller` WRITE;
/*!40000 ALTER TABLE `soldbyseller` DISABLE KEYS */;
INSERT INTO `soldbyseller` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,2),(6,6,2),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,1),(13,13,1),(14,14,1),(15,15,1),(16,16,1),(17,17,1),(18,18,1),(19,19,1),(20,20,1),(21,21,1),(22,22,1),(23,23,1),(24,24,1),(25,25,1),(26,26,1),(27,27,1),(28,28,1),(29,29,1),(30,30,1),(31,31,1),(32,32,1),(33,33,1),(34,34,1),(35,35,1),(36,36,1),(37,37,1),(38,38,1),(39,39,1),(40,40,1),(41,41,1),(42,42,1),(43,43,1),(44,44,1),(45,45,1),(46,46,1),(47,47,1),(48,48,1),(49,49,1),(50,50,1),(51,51,1),(52,52,1),(53,53,1),(54,54,1),(55,55,1),(56,56,1),(57,57,1),(58,58,1),(59,59,1),(60,60,1),(61,60,1),(62,61,1),(63,63,1),(64,64,1),(65,65,1),(321,67,39),(322,68,39);
/*!40000 ALTER TABLE `soldbyseller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('11f859a3-eb08-49d1-9fba-480c69dda3e8','e291e690-54c6-43bd-a7cf-02d561547084',1753195361064,1753195363575,1800,1753197163575,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockin`
--

DROP TABLE IF EXISTS `stockin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockin` (
  `StockInID` int NOT NULL AUTO_INCREMENT,
  `SupplierID` int NOT NULL,
  `WarehouseID` int NOT NULL,
  `StockInDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `note` tinytext,
  PRIMARY KEY (`StockInID`),
  KEY `SupplierID` (`SupplierID`),
  KEY `WarehouseID` (`WarehouseID`),
  CONSTRAINT `stockin_ibfk_1` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`),
  CONSTRAINT `stockin_ibfk_2` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockin`
--

LOCK TABLES `stockin` WRITE;
/*!40000 ALTER TABLE `stockin` DISABLE KEYS */;
INSERT INTO `stockin` VALUES (1,2,1,'2025-02-06 18:30:00','Nhập thử đợt 1'),(2,4,1,'2025-03-06 20:52:00','Lô hàng mới tháng 3'),(3,5,1,'2025-05-08 21:03:00','Đợt hàng nhập khẩn'),(4,3,1,'2025-06-06 18:40:00','Bổ sung hàng tồn kho'),(5,4,1,'2025-06-04 10:42:00','Cập nhật số lượng sản phẩm'),(6,7,1,'2025-06-06 10:44:00','Đơn hàng nhập từ nhà cung cấp A'),(7,5,1,'2025-06-06 10:43:00','Nhập hàng đầu tháng'),(8,5,1,'2025-04-10 22:44:00','Hàng hóa nhập do thiếu hụt'),(9,4,1,'2025-06-21 04:45:00','Tiếp nhận lô hàng khẩn cấp'),(10,2,1,'2025-04-24 00:45:00','Kiểm thử nhập kho'),(11,5,1,'2025-03-06 22:48:00','Nhập bổ sung kho miền Bắc'),(12,2,1,'2025-07-03 22:59:00','Nhập đợt hàng theo yêu cầu khách'),(13,2,1,'2025-05-16 23:11:00','Giao hàng gấp từ NCC'),(14,2,1,'2025-02-14 12:34:00','Lô hàng Valentine'),(15,2,1,'2025-05-10 00:26:00','Giao buổi đêm theo hợp đồng'),(16,3,1,'2025-07-05 15:08:00','Thêm lô mới theo kế hoạch'),(17,4,1,'2025-07-03 14:20:00','Nhập hàng tết âm lịch'),(18,2,1,'2025-07-04 14:20:00','Thử nghiệm hệ thống nhập'),(19,3,1,'2025-07-04 14:20:00','Nhập  định kỳ'),(20,3,1,'2025-03-14 14:24:00','Đơn hàng từ hợp đồng Q1'),(21,2,1,'2025-05-09 14:24:00','Nhập hàng định kỳ'),(22,2,1,'2025-07-08 14:24:00','Tiếp nhận vật tư nội bộ'),(23,2,1,'2025-02-21 14:24:00','Kiểm kê bổ sung số liệu'),(24,2,1,'2025-07-08 14:24:00','Lô hàng thử nghiệm chất lượng'),(25,4,1,'2025-07-18 14:24:00','Hàng trả lại nhà cung cấp'),(26,3,1,'2025-07-18 10:20:00','Lô hàng ưu tiên cấp tốc'),(27,3,1,'2025-07-18 23:00:00','Nhập ngoài giờ hành chính'),(28,2,1,'2025-05-09 23:00:00','Nhập cho bộ phận R&D'),(29,2,1,'2025-05-10 11:11:00','Nhập theo đơn lẻ'),(30,4,1,'2025-07-21 14:24:00','Hàng hóa hỗ trợ khẩn'),(31,3,1,'2025-04-17 14:24:00','Lô hàng phục vụ triển lãm'),(32,3,1,'2025-07-22 11:11:00','Nhập lô mẫu trưng bày'),(33,3,1,'2025-07-23 14:24:00','Tiếp nhận hàng mới về'),(34,3,1,'2025-07-23 14:24:00','Cập nhật kho tổng hợp');
/*!40000 ALTER TABLE `stockin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockindetail`
--

DROP TABLE IF EXISTS `stockindetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockindetail` (
  `StockInDetailID` int NOT NULL AUTO_INCREMENT,
  `StockInID` int NOT NULL,
  `Quantity` int NOT NULL,
  `unitprice` int DEFAULT NULL,
  `BatchID` int DEFAULT NULL,
  PRIMARY KEY (`StockInDetailID`),
  KEY `StockInID` (`StockInID`),
  KEY `BatchID` (`BatchID`),
  CONSTRAINT `stockindetail_ibfk_1` FOREIGN KEY (`StockInID`) REFERENCES `stockin` (`StockInID`),
  CONSTRAINT `stockindetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockindetail`
--

LOCK TABLES `stockindetail` WRITE;
/*!40000 ALTER TABLE `stockindetail` DISABLE KEYS */;
INSERT INTO `stockindetail` VALUES (1,1,110,12000,16),(2,1,121,12300,2),(3,2,100,12000,6),(4,2,112,11100,4),(5,2,110,11000,5),(6,3,115,12000,7),(7,3,120,12000,8),(8,3,119,13000,14),(9,3,115,10000,23),(10,4,100,9000,3),(11,4,100,11000,10),(12,5,110,10000,51),(13,5,119,13000,29),(14,5,105,14000,26),(15,6,101,10000,30),(16,6,122,12000,28),(17,7,100,11000,46),(18,8,121,11000,22),(19,8,120,12000,19),(20,9,115,11000,12),(21,9,115,11000,27),(22,9,115,11500,37),(23,10,105,12500,11),(24,10,122,22000,13),(25,11,110,11100,39),(26,12,122,12200,42),(27,13,110,11000,9),(28,14,110,11000,35),(29,14,110,10000,60),(30,15,122,12000,61),(31,16,122,10000,32),(32,17,117,10000,33),(33,18,100,10000,62),(34,19,112,10000,34),(35,19,120,10000,36),(36,20,100,12000,63),(37,20,117,10000,47),(38,21,1,10000,17),(39,22,101,10000,15),(40,22,102,12000,50),(41,23,1,1,59),(42,24,1,1111,58),(43,25,99,10000,45),(44,26,1,11000,52),(45,27,23,23000,38),(46,28,111,11000,20),(47,29,11,111111,40),(48,30,111,11111,43),(49,30,112,11400,21),(50,31,111,11111,41),(51,32,100,10000,1),(52,32,121,12000,18),(53,32,100,12000,24),(54,33,11,11,31),(55,34,9,9000,25);
/*!40000 ALTER TABLE `stockindetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockout`
--

DROP TABLE IF EXISTS `stockout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockout` (
  `StockOutID` int NOT NULL AUTO_INCREMENT,
  `WarehouseID` int NOT NULL,
  `orderid` bigint DEFAULT NULL,
  `stockoutdate` datetime(6) DEFAULT NULL,
  `note` tinytext,
  `status` enum('EXPORTED','RETURNED') NOT NULL,
  PRIMARY KEY (`StockOutID`),
  KEY `WarehouseID` (`WarehouseID`),
  KEY `OrderID` (`orderid`),
  CONSTRAINT `FK7ljvep6rr4pj89v3khvhofxtl` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`),
  CONSTRAINT `stockout_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockout`
--

LOCK TABLES `stockout` WRITE;
/*!40000 ALTER TABLE `stockout` DISABLE KEYS */;
INSERT INTO `stockout` VALUES (1,1,2,'2025-07-24 08:42:03.751272','Xuất kho nhanh cho đơn hàng #ORD3319058','EXPORTED');
/*!40000 ALTER TABLE `stockout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockoutdetail`
--

DROP TABLE IF EXISTS `stockoutdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockoutdetail` (
  `StockOutDetailID` int NOT NULL AUTO_INCREMENT,
  `StockOutID` int NOT NULL,
  `BatchID` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `orderdetailid` bigint NOT NULL,
  PRIMARY KEY (`StockOutDetailID`),
  KEY `StockOutID` (`StockOutID`),
  KEY `BatchID` (`BatchID`),
  KEY `FKd8fm5wpab7si40c2f6eyutqvn` (`orderdetailid`),
  CONSTRAINT `FKd8fm5wpab7si40c2f6eyutqvn` FOREIGN KEY (`orderdetailid`) REFERENCES `orderdetail` (`orderdetailid`),
  CONSTRAINT `stockoutdetail_ibfk_1` FOREIGN KEY (`StockOutID`) REFERENCES `stockout` (`StockOutID`),
  CONSTRAINT `stockoutdetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockoutdetail`
--

LOCK TABLES `stockoutdetail` WRITE;
/*!40000 ALTER TABLE `stockoutdetail` DISABLE KEYS */;
INSERT INTO `stockoutdetail` VALUES (1,1,21,1,3),(2,1,65,1,5);
/*!40000 ALTER TABLE `stockoutdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `SupplierID` int NOT NULL AUTO_INCREMENT,
  `SupplierName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contactinfo` varchar(100) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (2,'Nông Sản Việt','nongsanvietnam@gmail.com','meme-meo-an-bong-ngo-gif.gif'),(3,'Nông Sản Nhà Quê','nongsannhaque@gmail.com','videoplayback-Trim.gif'),(4,'Agriculture','agriculture@gmail.com','original-789a2336ecfb6c9b427f0576e4eeca9c.gif'),(5,'Agricultures','agricultures@gmail.com','pokemon-pokémon.gif'),(6,'Thế giới nông sản','thegioinongsan@gmail.com','ryPtNE.gif'),(7,'Trang Trại Xanh Việt','vietgreenfarm@gmail.com','vietgreen.png'),(8,'Thu Hoạch Tươi','freshharvest@gmail.com','harvest.png'),(9,'Organica','organica.vn@gmail.com','organica.png'),(10,'Nông Sạch Hà Nội','nongsachhanoi@gmail.com','hanoi.png'),(11,'Eco Nông Sản','ecoagri.vn@gmail.com','ecoagri.png'),(12,'Sạch Nông Sản','sachnongsan@gmail.com','sachnongsan.png'),(13,'Nông Sản VietGAP','vietgapproduce@gmail.com','vietgap.png'),(14,'Dalat Farm Fresh','dalatfarmfresh@gmail.com','dalatfarm.png'),(15,'Nông Trại Hòa Bình','hoabinhagri@gmail.com','hoabinh.png'),(16,'Organic Việt Nam','organicvn2025@gmail.com','organicvn.png'),(17,'Bio Farm Việt','biofarmvn@gmail.com','biofarm.png'),(18,'Vườn Xanh','greengarden@gmail.com','greengarden.png');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokenuser`
--

DROP TABLE IF EXISTS `tokenuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokenuser` (
  `tokenuserid` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tokenuserid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokenuser`
--

LOCK TABLES `tokenuser` WRITE;
/*!40000 ALTER TABLE `tokenuser` DISABLE KEYS */;
INSERT INTO `tokenuser` VALUES (1,'12Email','dsds'),(2,'1234yeyeyeye1234@gmail.com','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0eWV5ZXlleWUxMjM0QGdtYWlsLmNvbSIsImlhdCI6MTc1MTYwNTUxOCwiZXhwIjoxNzUxNjA2NzE4fQ.5LqQ041urYDiEWY2ke3DLEgYg1U7XuPK8h8HcWEYxfw'),(3,'nguyenhoanganh712004qp@gmail.com','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW5ob2FuZ2FuaDcxMjAwNHFwQGdtYWlsLmNvbSIsImlhdCI6MTc1MTYwNDk1MSwiZXhwIjoxNzUxNjA2MTUxfQ.MWmURabUJ4cPuAuDxksVsNnENuJrn8slQD6nK4H_m-o');
/*!40000 ALTER TABLE `tokenuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `updateprofilehistory`
--

DROP TABLE IF EXISTS `updateprofilehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `updateprofilehistory` (
  `historyid` int NOT NULL AUTO_INCREMENT,
  `updateinfo` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updatetime` datetime(6) NOT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`historyid`),
  KEY `FKrmeehvbpj2k798ag1eor2hh5h` (`userid`),
  CONSTRAINT `FKrmeehvbpj2k798ag1eor2hh5h` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `updateprofilehistory`
--

LOCK TABLES `updateprofilehistory` WRITE;
/*!40000 ALTER TABLE `updateprofilehistory` DISABLE KEYS */;
INSERT INTO `updateprofilehistory` VALUES (1,'Username:Hoàng Anh Đẹp z','2025-06-18 16:38:09.041000',16),(2,'Username:Hoàng Anh Đẹp Zai vippro','2025-06-18 16:38:41.727000',16),(3,'Full Name:Nguyễn , Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:Fri Jun 01 00:00:00 ICT 2007','2025-06-24 11:48:20.067000',21),(4,'','2025-06-24 11:50:09.962000',21),(5,'Full Name:Nguyễn Hoàng Anh','2025-06-24 11:53:41.387000',21),(6,'','2025-06-24 11:56:23.529000',21),(7,'Dob:Wed Jan 07 00:00:00 ICT 2004','2025-06-24 11:57:03.951000',21),(8,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:Thu Jan 01 00:00:00 ICT 2004','2025-06-24 13:06:52.634000',23),(9,'','2025-07-01 11:21:43.789000',23),(10,'Dob:2004-01-07','2025-07-01 12:10:24.969000',23),(11,'Dob:2004-01-08','2025-07-01 12:13:06.371000',23),(12,'Dob:2004-01-07','2025-07-01 12:13:17.003000',23),(13,'Phone:0969652156, Address:Nghệ An, Gender:male, Dob:2004-01-07','2025-07-01 12:24:16.330000',24),(14,'','2025-07-01 12:25:23.987000',24),(15,'Gender:female','2025-07-01 12:25:35.164000',24),(16,'Gender:male','2025-07-01 12:25:44.604000',24),(17,'Address:a','2025-07-01 12:27:39.569000',24),(18,'Address:Nghệ An province','2025-07-01 12:28:03.882000',24),(19,'Full Name:Nguyễn Hoàng Anh Dzai','2025-07-01 12:28:16.432000',24),(20,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Nghệ An province, Gender:male, Dob:2004-01-07','2025-07-06 16:44:03.168000',26),(21,'Address:Thị xã Hoàng Mai, Nghệ An province','2025-07-06 16:44:34.286000',26),(22,'Username:Hoàng Anh, Phone:0969652156, Address:Nghệ An province, Gender:male, Dob:2004-01-07','2025-07-07 17:05:29.966000',29),(23,'Username:Hoàng Anh, Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Nghệ An, Gender:male, Dob:2004-01-07','2025-07-10 21:37:01.892000',11),(24,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thị xã Hoàng Mai, Tỉnh Nghệ An, Gender:male, Dob:2004-01-07','2025-07-14 16:57:10.992000',30),(25,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:2004-01-07','2025-07-18 18:23:04.491000',33),(26,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:2004-01-07','2025-07-21 10:30:45.780000',34),(27,'Gender:female','2025-07-21 20:40:05.058000',34),(28,'Gender:male','2025-07-22 20:15:40.554000',34),(29,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:2004-01-01','2025-07-23 03:10:17.559000',35),(30,'Username:hoangngubavi, Full Name:Đào Hoàng Anh, Phone:0923456789, Address:Hưng Yên, Gender:female, Dob:2007-06-30','2025-07-24 00:52:22.439000',39),(31,'Full Name:Ưng Hoàng Anh','2025-07-24 00:55:10.882000',39),(32,'Gender:Nữ','2025-07-24 02:05:45.536000',39),(33,'','2025-07-24 02:05:55.769000',39),(34,'Full Name:Nguyễn Xuân Chiến, Phone:0379366518, Address:Hưng Yên, Gender:Nam, Dob:2004-05-29','2025-07-24 09:26:17.737000',43),(35,'Full Name:Nguyễn Xuân Chiến, Phone:0379366518, Address:Hưng Yên, Gender:Nam','2025-07-25 13:12:09.616000',48);
/*!40000 ALTER TABLE `updateprofilehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `RoleID` int DEFAULT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `FullName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `OTP` varchar(10) DEFAULT NULL,
  `LastTimeUpdatePass` datetime DEFAULT NULL,
  `GoogleID` varchar(255) DEFAULT NULL,
  `StatusGG` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`UserID`),
  KEY `FK2ovmsl4hvm5vu1w8i308r5j6w` (`RoleID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,4,'bà chủ thầu',NULL,NULL,'john.doe@example.com',NULL,NULL,'female','Active',NULL,'2025-06-07 18:34:36',NULL,NULL,NULL,NULL,''),(2,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:34:40',NULL,NULL,NULL,NULL,''),(3,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:36:09',NULL,NULL,NULL,NULL,''),(4,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:38:56',NULL,NULL,NULL,NULL,''),(5,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:52:53',NULL,NULL,NULL,NULL,''),(6,2,'john.doe@example.com',NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 19:15:07',NULL,NULL,NULL,'Inactive',''),(7,2,'Hoàng Anh đep zai vippro','Nguyễn Hoàng Anh',NULL,'yeyeye@gmail.com','0969652156','Nghệ An ','male','','2004-01-07','2025-06-07 20:33:32','305952','2025-06-15 10:32:16',NULL,'Inactive',''),(8,2,'Admin01',NULL,NULL,'nguyenhoanganh@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-09 09:36:24','987044',NULL,'SDS','Active',''),(9,2,'Admin01',NULL,NULL,'hello@fpt.edu.vn',NULL,NULL,NULL,'Active',NULL,'2025-06-10 09:46:03','333889',NULL,'In',NULL,''),(10,2,'Admin01',NULL,NULL,'hello1@fpt.edu.vn',NULL,NULL,NULL,'Active',NULL,'2025-06-10 10:00:13','074940',NULL,'In','Active',''),(11,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1752157563864_piclumenmarquee-06.webp','anhnhhe186218@fpt.edu.vn','0969652156','Nghệ An','male','Active','2004-01-07','2025-06-10 10:03:05','020085','2025-07-10 21:37:02','In','Active',''),(12,2,'Admin01',NULL,NULL,'nguyenhoanganhqp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-11 07:36:46','298714',NULL,'In','Inactive',''),(13,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','nguyenhoanganh71200qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-11 08:08:16',NULL,NULL,'108664088110428909546','Active',''),(14,2,'hoàng anh','Nguyễn Hoàng Anh',NULL,'1234yeyeyeye@gmail.com','0969652151','Nghệ An province','male','Active','2007-06-05','2025-06-15 10:34:45','383949','2025-06-15 12:10:42','In','Inactive',''),(15,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1749990153213_piclumen-marquee-06.webp','1234yeyeyeye12@gmail.com','0969652156','Nghệ An province','male','Active','2004-01-07','2025-06-15 12:22:08','006872','2025-06-15 15:28:32','In','Inactive',''),(16,2,'Hoàng Anh Đẹp Zai vippro','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1749999362751_piclumen-marquee-06.webp','1234yeyeyeye123@gmail.com','0969652156','Nghệ An','male','Active','2005-01-07','2025-06-15 19:31:00','701652','2025-06-18 16:38:42','Inactive','Inactive',''),(17,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye12@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-19 09:05:20',NULL,NULL,'102069777070774205995','Active',''),(18,2,'Channel','Ye123 Channel','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1750303920404_piclumen-marquee-06.webp','1234yeyeyeye123@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-19 09:25:00',NULL,NULL,'102069777070774205995','Active',''),(19,2,'hoàng anháddada',NULL,NULL,'nguyenhoanganh71204qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-19 09:39:21','644256',NULL,'Inactive','Inactive',''),(20,2,'Hoàng Anh',NULL,NULL,'1234yeyeyeye134@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-24 11:27:43','527151','2025-06-24 11:27:43','Inactive','Inactive','$2a$10$Ds/l13y2LJfev/hrGSoa.OnVPvGyvH/BBRMfmNICC59.zEY/kyAS.'),(21,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1750740452655_piclumen-marquee-06.webp','1234yeyeyey@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-07','2025-06-24 11:45:49','829281','2025-06-24 12:16:52','Inactive','Inactive','$2a$10$EOUE3a/MHHePH7flzbEYOemWSu7pT5BGNKyGPedyKRM7NImYgeYr6'),(22,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','nguyenhoang@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-24 13:00:32',NULL,NULL,'108664088110428909546','Active',''),(23,2,'Hoàng anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1750745230723_piclumen-marquee-06.webp','12341234@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-07','2025-06-24 13:05:49','016368','2025-07-01 12:13:17','Inactive','Inactive','$2a$10$FLWW5FUM8gga8HYu5p/psOwS4HlaxCHhE1ANsobSLXBN2tSUAYtmW'),(24,2,'Hoàng Anh','Nguyễn Hoàng Anh Dzai','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','p@gmail.com','0969652156','Nghệ An province','male','Active','2004-01-07','2025-07-01 12:16:36',NULL,'2025-07-01 12:28:16','108664088110428909546','Active',''),(25,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','234@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-01 12:32:09',NULL,NULL,'102069777070774205995','Active',''),(26,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1751881152286_ChatGPT Image 12_54_38 7 thg 7, 2025.png','1234yeyeyeye1@gmail.com','0969652156','Thị xã Hoàng Mai, Nghệ An province','male','Active','2004-01-07','2025-07-01 12:40:14','738907','2025-07-07 16:39:12','Inactive','Inactive','$2a$10$CAfRIZ9dvWG.bc7x.lauKeHxWLkl1MILRZOs9O6oeh0AkcvdRkTj.'),(27,2,'Hoàng Anh',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nguyenhoanganh712004qp@gms',NULL,NULL,NULL,'Active',NULL,'2025-07-01 14:32:57','416706',NULL,'Inactive','Inactive','$2a$10$7QvcQ2aNaXXEExOx1D6Qb.K3hPH6TNRu6BkHHtIW02zK.lp1hFrCq'),(28,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','nguyenhoanganh7p@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-06 18:23:51',NULL,NULL,'108664088110428909546','Active',''),(29,2,'Hoàng Anh','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye134@gmail.com','0969652156','Nghệ An province','male','Active','2004-01-07','2025-07-07 17:04:04',NULL,'2025-07-07 17:05:30','102069777070774205995','Active',''),(30,2,'Hoàng Anh','Nguyễn Hoàng An','/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/1752554639647_piclumenmarquee-06.webp','nguyenhoanganh71200ddd4qp@gmail.com','0969652156','Thị xã Hoàng Mai, Tỉnh Nghệ An','male','Active','2004-01-07','2025-07-14 14:40:56','613172','2025-07-15 11:44:00','Inactive','Inactive','$2a$10$RX.uhp6MI0k3m2P5r0Mvue.4fCYNyBr40Wr4Y1uTPveZ3orhCO8ri'),(31,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye111134@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-15 17:25:51',NULL,'2025-07-15 17:26:20','102069777070774205995','Active',''),(32,2,'Hoàng Anh 111',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nguyenhoanganh712ss004qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-18 09:38:36','149044','2025-07-18 09:54:50','Inactive','Inactive','$2a$10$O5p7AttNm.YAFLbsK3G9HuANJYx/NxKIgUJFpc4UgHwL8IvvgT/ga'),(33,5,'HoàngAnh','Nguyễn Hoàng Anh','avatar.gif','hoangngu@gmail.com','0969652156','Thạch Thất, Hà Nội','Nam','Active','2004-01-07','2025-07-18 18:21:50','957851','2025-07-23 23:57:00','Inactive','Inactive','$2a$10$AXY9saT2vp9twsmwTAwZ0uaBPQVYRRG/qmqFmLyMRX0ewsE9g/sJK'),(34,2,'Hoàng Anh Pro','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/1753072814880_piclumenmarquee-06.webp','nguyenhoanganh712004ssssqp@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-07','2025-07-21 02:31:34','733984','2025-07-22 20:15:41','Inactive','Inactive','$2a$10$MveTRhuZ7/KVT1G.omgZtuKBZvXTg/mjPjg08v59.U14/aIAjnv2G'),(35,2,'HoangAnhPro','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/1753215043477_piclumenmarquee-06.jpg','1234yeyeyeye123ss4@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-01','2025-07-23 03:08:51','728405','2025-07-23 03:10:44','Inactive','Inactive','$2a$10$6mZUfpTWzuyKrynZoy8n6.elo5DnxrfDoHuy0ZNnvAHhy3iscOioy'),(36,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye123sss4@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-23 04:02:19',NULL,NULL,'102069777070774205995','Active',''),(37,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyey@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-23 04:19:56',NULL,NULL,'102069777070774205995','Active',''),(38,2,'Chiến','Nguyễn Xuân Chiến','https://lh3.googleusercontent.com/a/ACg8ocI8QAOEdV9Lr2cgAZ-zcupl83Au0gCMHKfYshP22i0NymZBt_bL=s96-c','chiennguyen@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-23 23:26:03',NULL,NULL,'102420462442666974036','Active',''),(39,2,'hoangngubavi','Ưng Hoàng Anh','avatar.gif','nguyenhoanganh712004qp@gmail.com','0923456789','Hưng Yên','Nam','Active','2007-06-30','2025-07-24 00:45:17','924751','2025-07-24 08:23:59','Inactive','Inactive','$2a$10$XmLrNYH57tnrzxDH1/Y3F.lLhMer/E1s3U28fGI.RIw8h8hp0Z9wi'),(40,2,'daohuyhoang507@gmail.com','Hoang123','1753352578941_kha-banh-mua-quat.gif','daohuyhoang507@gmail.com','0342654334','Hà Nội','male','Active','2003-05-17','2025-07-22 14:40:13','416934','2025-07-24 17:22:59','Inactive','Inactive','$2a$10$rjn/PYi0nlKKf0ggiGT83OPZv2Uvx8XvVlcjBo35Cnixg1uz/.FGa'),(41,4,'chien','Nguyễn Xuân Chiến','https://lh3.googleusercontent.com/a/ACg8ocI8QAOEdV9Lr2cgAZ-zcupl83Au0gCMHKfYshP22i0NymZBt_bL=s96-c','ch@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-24 09:01:39',NULL,NULL,'102420462442666974036','Active',''),(42,2,'hoanganh_user',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','123fffff@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-24 09:07:02','196181',NULL,'Inactive','Inactive','$2a$10$7WCiq7M86urBcZ5d4Gjxi.yobNpYVsI/nz1etFeKQL5q7vdcInZJi'),(43,4,'chen','Nguyễn Xuân Chiến','avatar.gif','chiennguyena8k22@gmail.com','0379366518','','Nam','Active','2004-05-29','2025-07-24 09:12:25','566474','2025-07-25 13:17:04','Inactive','Inactive','$2a$10$U0kLc4F7TAYqyfpVZWlZ2uScy/yeaOqajUQsd3cwLrpq15e1XAD.e'),(44,5,'warehouse_staff',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nhatquang182@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-24 11:22:55','694783',NULL,'Inactive','Inactive','$2a$10$5lcA.CkEr9ijnFwOmT8zgenNr.0K71JDlh7WmYDln7zt6d8CTNMim'),(45,1,'admin_123',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','thanhhv27042004@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-24 11:24:39','876119',NULL,'Inactive','Inactive','$2a$10$wD1yydAvUezol71PKbrcse9SzLLjNhxJHUFVIlppD97dzQjfeq7ja'),(46,4,'N.X','Chiến N.X','','xuanchien7hhha3@gmail.com','','','','Active','2004-05-29','2025-07-25 11:11:11',NULL,'2025-07-25 11:13:51','117217480650098955895','Active',''),(47,2,'N.X','Chiến N.X','','xuanchien7a3@gmail.com','','','','Active','2006-07-25','2025-07-25 11:24:52',NULL,'2025-07-25 11:50:49','117217480650098955895','Active',''),(48,2,'Hoanganhpro','Nguyễn Xuân Chiến','avatar.jpg','1234yeyeyeye1234@gmail.com','0379366518','Hưng Yên','Nam','Active','2006-07-25','2025-07-25 13:02:10','171952','2025-07-25 13:12:10','Inactive','Inactive','$2a$10$H.99bOjkvo5fZxdacO1jrObgsTOMfNBopzQSEAy5Ebq0AqaXQeecq');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount_type` enum('AMOUNT','PERCENT') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount_value` decimal(15,2) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `islocked` bit(1) NOT NULL,
  `min_order_amount` decimal(15,2) NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `start_date` datetime(6) NOT NULL,
  `status` enum('ACTIVE','EXPIRED','INACTIVE') COLLATE utf8mb4_unicode_ci NOT NULL,
  `used_quantity` bigint DEFAULT NULL,
  `voucher_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpt2fcgppqfc1tbcvqctm7nuet` (`voucher_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'PERCENT',20.00,'2025-08-21 12:00:00.000000',_binary '\0',200000.00,91,'2025-06-10 00:00:00.000000','ACTIVE',1,'SALE20P'),(2,'AMOUNT',50000.00,'2025-08-21 12:00:00.000000',_binary '\0',250000.00,10,'2025-06-01 00:00:00.000000','ACTIVE',1,'HOTDEAL'),(3,'AMOUNT',50000.00,'2025-08-21 12:00:00.000000',_binary '\0',100000.00,100,'2025-05-01 00:00:00.000000','ACTIVE',47,'EXPIRED50'),(4,'PERCENT',15.00,'2025-08-21 12:00:00.000000',_binary '\0',150000.00,20,'2025-07-01 00:00:00.000000','ACTIVE',11,'COMINGSOON'),(5,'AMOUNT',5000.00,'2025-07-25 12:27:00.000000',_binary '\0',50000.00,31,'2025-07-25 12:25:00.000000','EXPIRED',5,'SALE5K');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallets`
--

DROP TABLE IF EXISTS `wallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallets` (
  `walletid` bigint NOT NULL AUTO_INCREMENT,
  `balance` decimal(20,2) DEFAULT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`walletid`),
  KEY `FKg201i0rkhufy3unov95eu2xod` (`userid`),
  CONSTRAINT `FKg201i0rkhufy3unov95eu2xod` FOREIGN KEY (`userid`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallets`
--

LOCK TABLES `wallets` WRITE;
/*!40000 ALTER TABLE `wallets` DISABLE KEYS */;
INSERT INTO `wallets` VALUES (1,3000.00,33),(2,40000.00,34),(3,0.00,35),(4,2000.00,37),(5,0.00,38),(6,0.00,39),(7,7813000.00,40),(8,0.00,41),(9,0.00,42),(10,0.00,43),(11,0.00,44),(12,0.00,45),(13,0.00,46),(14,0.00,47),(15,99933000.00,48);
/*!40000 ALTER TABLE `wallets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `WarehouseID` int NOT NULL AUTO_INCREMENT,
  `WarehouseName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`WarehouseID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'Kho Trung Tâm','Ba Vì','Kho chính');
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `WishlistID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `productid` int NOT NULL,
  PRIMARY KEY (`WishlistID`),
  KEY `UserID` (`UserID`),
  KEY `FKgmfdg7ydih4ua53mfeltbjoek` (`productid`),
  CONSTRAINT `FKgmfdg7ydih4ua53mfeltbjoek` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,16,7),(4,17,1),(5,17,1),(7,17,2),(8,17,10),(37,18,1),(38,18,62),(39,18,16),(40,18,2),(43,23,1),(44,23,1),(48,23,7),(49,23,8),(50,23,10),(51,23,12),(52,23,43),(53,23,43),(54,23,55),(55,29,1),(57,11,17),(64,35,17),(65,37,6),(66,39,1),(67,39,1),(68,48,1);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-25 21:35:09
