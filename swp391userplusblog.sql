-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: managerwarehouse
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
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustment`
--

LOCK TABLES `adjustment` WRITE;
/*!40000 ALTER TABLE `adjustment` DISABLE KEYS */;
INSERT INTO `adjustment` VALUES (1,'2025-06-25 14:32:36','Stock correction','Remove',10,1),(2,'2025-06-25 14:32:36','Damaged goods','Remove',1,1),(3,'2025-06-25 14:32:36','Inventory mismatch','Add',9,3),(4,'2025-06-25 14:32:36','Theft loss','Remove',1,4),(5,'2025-06-25 14:32:36','Returned from customer','Remove',8,5),(196,'2025-06-25 14:32:36','Stock correction','Add',15,6),(197,'2025-06-25 14:32:36','Damaged goods','Remove',3,7),(198,'2025-06-25 14:32:36','Inventory mismatch','Add',5,8),(199,'2025-06-25 14:32:36','Theft loss','Remove',2,9),(200,'2025-06-25 14:32:36','Returned from customer','Remove',6,10),(201,'2025-06-25 14:32:36','Stock correction','Add',18,11),(202,'2025-06-25 14:32:36','Damaged goods','Remove',4,12),(203,'2025-06-25 14:32:36','Inventory mismatch','Add',8,13),(204,'2025-06-25 14:32:36','Theft loss','Remove',1,14),(205,'2025-06-25 14:32:36','Returned from customer','Remove',7,15),(206,'2025-06-25 14:32:36','Stock correction','Add',25,16),(207,'2025-06-25 14:32:36','Damaged goods','Remove',2,17),(208,'2025-06-25 14:32:36','Inventory mismatch','Add',10,18),(209,'2025-06-25 14:32:36','Theft loss','Remove',3,19),(210,'2025-06-25 14:32:36','Returned from customer','Remove',5,20),(211,'2025-06-25 14:32:36','Stock correction','Add',20,21),(212,'2025-06-25 14:32:36','Damaged goods','Remove',1,22),(213,'2025-06-25 14:32:36','Inventory mismatch','Add',12,23),(214,'2025-06-25 14:32:36','Theft loss','Remove',4,24),(215,'2025-06-25 14:32:36','Returned from customer','Remove',9,25),(216,'2025-06-25 14:32:36','Stock correction','Add',16,26),(217,'2025-06-25 14:32:36','Damaged goods','Remove',3,27),(218,'2025-06-25 14:32:36','Inventory mismatch','Add',11,28),(219,'2025-06-25 14:32:36','Theft loss','Remove',2,29),(220,'2025-06-25 14:32:36','Returned from customer','Remove',6,30),(221,'2025-06-25 14:32:36','Stock correction','Add',19,31),(222,'2025-06-25 14:32:36','Damaged goods','Remove',4,32),(223,'2025-06-25 14:32:36','Inventory mismatch','Add',7,33),(224,'2025-06-25 14:32:36','Theft loss','Remove',1,34),(225,'2025-06-25 14:32:36','Returned from customer','Remove',8,35),(226,'2025-06-25 14:32:36','Stock correction','Add',21,36),(227,'2025-06-25 14:32:36','Damaged goods','Remove',5,37),(228,'2025-06-25 14:32:36','Inventory mismatch','Add',10,38),(229,'2025-06-25 14:32:36','Theft loss','Remove',2,39),(230,'2025-06-25 14:32:36','Returned from customer','Remove',7,40),(231,'2025-06-25 14:32:36','Stock correction','Add',24,41),(232,'2025-06-25 14:32:36','Damaged goods','Remove',3,42),(233,'2025-06-25 14:32:36','Inventory mismatch','Add',8,43),(234,'2025-06-25 14:32:36','Theft loss','Remove',1,44),(235,'2025-06-25 14:32:36','Returned from customer','Remove',6,45),(236,'2025-06-25 14:32:36','Stock correction','Add',15,46),(237,'2025-06-25 14:32:36','Damaged goods','Remove',2,47),(238,'2025-06-25 14:32:36','Inventory mismatch','Add',9,48),(239,'2025-06-25 14:32:36','Theft loss','Remove',3,49),(240,'2025-06-25 14:32:36','Returned from customer','Remove',5,50),(241,'2025-06-25 14:32:36','Stock correction','Add',18,51),(242,'2025-06-25 14:32:36','Damaged goods','Remove',4,52),(243,'2025-06-25 14:32:36','Inventory mismatch','Add',11,53),(244,'2025-06-25 14:32:36','Theft loss','Remove',2,54),(245,'2025-06-25 14:32:36','Returned from customer','Remove',8,55),(246,'2025-06-25 14:32:36','Stock correction','Add',20,56),(247,'2025-06-25 14:32:36','Damaged goods','Remove',3,57),(248,'2025-06-25 14:32:36','Inventory mismatch','Add',7,58),(249,'2025-06-25 14:32:36','Theft loss','Remove',1,59),(250,'2025-06-25 14:32:36','Returned from customer','Remove',6,60),(251,'2025-06-25 14:32:36','Stock correction','Add',25,61),(252,'2025-06-25 14:32:36','Damaged goods','Remove',2,62),(253,'2025-06-25 14:32:36','Inventory mismatch','Add',9,63),(254,'2025-06-25 14:32:36','Theft loss','Remove',3,64),(255,'2025-06-25 14:32:36','Returned from customer','Remove',7,65);
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,1,'Hướng dẫn trồng rau sạch tại nhà','Mẹo trồng rau sạch trong không gian nhỏ...','2025-07-06 14:00:00',NULL,'ACTIVE','Tom Carter'),(2,2,2,'Xu hướng chăn nuôi bền vững','Phân tích các phương pháp chăn nuôi hiện đại...','2025-07-06 14:05:00',NULL,'ACTIVE','Linda Green'),(3,1,1,'Kỹ thuật làm đất chuẩn bị mùa vụ','Các bước làm đất hiệu quả cho mùa màng...','2025-07-06 14:10:00',NULL,'ACTIVE','Tom Carter'),(4,2,2,'Tác động của thời tiết đến nông nghiệp','Dự báo và ứng phó với biến đổi khí hậu...','2025-07-06 14:15:00',NULL,'ACTIVE','Linda Green'),(5,1,1,'Phân bón tự nhiên từ rác thải','Hướng dẫn làm phân bón hữu cơ tại nhà...','2025-07-06 14:20:00',NULL,'ACTIVE','Tom Carter'),(6,2,2,'Thị trường hạt giống năm 2025','Phân tích xu hướng hạt giống mới...','2025-07-06 14:25:00',NULL,'ACTIVE','Linda Green'),(7,1,1,'Kỹ thuật bảo quản trái cây','Cách bảo quản trái cây tươi lâu...','2025-07-06 14:30:00',NULL,'ACTIVE','Tom Carter'),(8,2,2,'Công nghệ tưới tiêu tự động','Ứng dụng công nghệ trong nông nghiệp...','2025-07-06 14:35:00',NULL,'ACTIVE','Linda Green'),(9,1,1,'Lợi ích của trồng cây lâu năm','Ưu điểm và kinh nghiệm thực tế...','2025-07-06 14:40:00','2025-07-15 15:49:37','ACTIVE','Tom Carte'),(10,2,2,'Dự báo giá nông sản cuối năm','Phân tích giá cả và thị trường...','2025-07-06 14:45:00',NULL,'ACTIVE','Linda Green'),(19,1,5,'Day thon vĩ dạ','Bài thơ \"Đây thôn Vĩ Dạ\" của Hàn Mặc Tử là một tác phẩm nổi bật, thể hiện bức tranh thiên nhiên yên bình và tâm trạng u buồn của tác giả. Bài thơ không chỉ vẽ nên khung cảnh thơ mộng của thôn Vĩ mà còn bộc lộ nỗi khao khát yêu thương và nỗi đau chia ly trong cuộc sống. Dưới đây là nội dung bài thơ:\n\"Sao anh không về chơi thôn Vĩ?\nNhìn nắng hàng cau nắng mới lên.\nVườn ai mướt quá xanh như ngọc\nLá trúc che ngang mặt chữ điền.\nGió theo lối gió, mây đường mây,\nDòng nước buồn thiu, hoa bắp lay...\nThuyền ai đậu bến sông Trăng đó,\nCó chở trăng về kịp tối nay?\". \ntkaraoke.com\n\nBài thơ thể hiện sự hòa quyện giữa thiên nhiên và tâm hồn con người, mang đến cảm xúc sâu lắng cho người đọc. \nvienngocquy.com\n+1\nLời bài thơ Đây Thôn Vĩ Dạ (Hàn Mặc Tử)\n\nhttps://poem.tkaraoke.com › day_thon_vi_da.html\nBài thơ Đây thôn Vĩ Dạ – Hàn Mặc Tử - Viên Ngọc Quý\n\nhttps://vienngocquy.com › bai-tho-day-thon-vi-da-han-mac-tu\n\nView all\nBài thơ Đây thôn Vĩ Dạ – Hàn Mặc Tử - Viên Ngọc Quý\nMột bài thơ về những nhật đêm trong thôn Vĩ, với những hình ảnh vẻ đẹp và nỗi u buồn của tác giả. Phân tích bài thơ theo ba khổ, từ thiên nhiên, tới tâm trạng, cuối cùng là nhân ản…\n\nhttps://vienngocquy.com › bai-tho-day-thon-vi-da-han-mac-tu\nĐây Thôn Vĩ Dạ: Nội Dung Bài Thơ, Nghệ Thuật, Phân Tích','2025-07-09 16:35:50','2025-07-09 20:29:58','DELETED','Con mèo su kim'),(20,1,1,'s','s','2025-07-15 16:30:34',NULL,'DRAFT','s'),(21,1,2,'sá','ssa','2025-07-15 16:34:48',NULL,'DRAFT','sá'),(22,30,1,'hay','11','2025-07-15 16:49:52',NULL,'DRAFT','ssssssss'),(23,30,1,'phim chưởng','hay','2025-07-15 17:04:29',NULL,'DRAFT','năm can'),(24,31,1,'S','SSS','2025-07-15 21:15:18','2025-07-15 21:16:07','ACTIVE','văn cao'),(25,NULL,3,'hello','dsadsad','2025-07-21 01:43:53','2025-07-21 01:44:09','ACTIVE','dsdads'),(26,33,3,'s','s','2025-07-21 02:10:43','2025-07-21 02:11:00','DELETED','s');
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
INSERT INTO `blogcategory` VALUES (1,'Kinh nghiệm','Chia sẻ kinh nghiệm nông nghiệp'),(2,'Tin tức','Tin tức về nông sản'),(3,'Kinh nghiệm','Chia sẻ kinh nghiệm nông nghiệp'),(4,'Tin tức','Tin tức về nông sản'),(5,'Công nghệ','Cập nhật các công nghệ mới trong nông nghiệp'),(6,'Giải pháp','Các giải pháp tối ưu cho nông dân'),(7,'Hỏi đáp','Nơi trao đổi và hỏi đáp kinh nghiệm');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogdetail`
--

LOCK TABLES `blogdetail` WRITE;
/*!40000 ALTER TABLE `blogdetail` DISABLE KEYS */;
INSERT INTO `blogdetail` VALUES (1,1,'1.jpg','Chi tiết cách trồng rau sạch trong chậu...'),(2,2,'2.jpg','Phân tích chi tiết các phương pháp chăn nuôi bền vững...'),(3,3,'3.jpg','Hướng dẫn từng bước làm đất chuẩn bị mùa vụ...'),(4,4,'4.jpg','Tác động cụ thể của thời tiết đến cây trồng...'),(5,5,'5.jpg','Công thức làm phân bón tự nhiên từ rác thải hữu cơ...'),(6,6,'6.jpg','Phân tích xu hướng hạt giống mới trên thị trường...'),(7,7,'7.jpg','Hướng dẫn bảo quản trái cây tươi lâu tại nhà...'),(8,8,'8.jpg','Ứng dụng công nghệ tưới tiêu tự động trong nông nghiệp...'),(9,9,'9.jpg','Ưu điểm và kinh nghiệm thực tế trồng cây lâu năm...'),(10,10,'10.jpg','Dự báo giá cả và phân tích thị trường nông sản cuối năm...'),(14,19,'a593f9a4-4cda-4115-8eb9-b04788dd07c0_anh-nen-2k-dep-nhat_094443817.jpg','Nội dung chi tiết mô tả thêm blog.'),(15,20,'6135eaa9-5d7e-43ce-89e5-f94b9d611949_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(16,21,'f6c88b1c-4f09-4926-b4aa-dc7437c72a28_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(17,22,'b688607e-6f94-42bf-8b79-a663c402516b_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(18,23,'d9464815-d9ed-44f8-8612-ac4ad9307187_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(19,24,'d9464815-d9ed-44f8-8612-ac4ad9307187_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.'),(20,25,'5dbe1b55-4496-4afb-9129-48e38d1dc21b_piclumenmarquee-06.webp','Nội dung chi tiết mặc định hoặc để rỗng'),(21,26,'84e68998-e037-4bf9-a486-53c321272204_piclumenmarquee-06.webp','Nội dung chi tiết mô tả thêm blog.');
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
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (356,23,5,3,30000),(357,23,5,2,20000),(358,23,9,2,40000),(359,23,9,1,20000),(360,23,9,1,20000),(366,27,13,1,15000),(367,27,13,1,15000),(370,27,5,1,10000),(371,27,5,1,10000),(372,27,5,1,10000),(373,27,5,1,10000),(374,27,5,1,10000),(375,27,5,1,10000),(433,26,1,1,15000),(434,26,64,1,11111),(466,30,1,1,15000),(467,30,18,1,35000),(468,31,8,1,30000),(469,31,8,1,30000),(470,31,8,1,30000),(471,31,8,1,30000),(472,31,19,1,25000),(473,31,8,1,30000),(474,32,22,3,66000),(490,33,5,1,2000),(491,34,5,1,2000);
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
INSERT INTO `category` VALUES (1,'Rau lá (xà lách, rau muống…)  ','300g–500g','Active','FrontEnd/assets/svg/1/vegetable.svg',NULL),(2,'Củ (khoai, cà rốt, củ cải…)','500g–1kg','Active','FrontEnd/assets/svg/1/cup.svg',NULL),(3,'Trái cây vừa (cam, táo…)','1kg–1.5kg','Active','FrontEnd/assets/svg/1/meats.svg',NULL),(4,'Trái cây lớn (dưa, mít…)','2kg–4kg','Active','FrontEnd/assets/svg/1/breakfast.svg',NULL),(5,'Gạo (các loại)','2kg–5kg','Active','FrontEnd/assets/svg/1/frozen.svg',NULL),(6,'Hạt (đậu, mè, yến mạch…) ','200g–500g','Active','FrontEnd/assets/svg/1/milk.svg',NULL),(7,'Gia vị khô (tỏi, tiêu, ớt khô…)','100g–200g','Active','FrontEnd/assets/svg/1/pet.svg',NULL),(8,'Nấm tươi (kim châm, bào ngư…)','250g–400g','Active','FrontEnd/assets/svg/1/biscuit.svg',NULL),(9,'Rau gia vị (hành, ngò…)','100g–150g','Active','FrontEnd/assets/svg/1/grocery.svg',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentblog`
--

LOCK TABLES `commentblog` WRITE;
/*!40000 ALTER TABLE `commentblog` DISABLE KEYS */;
INSERT INTO `commentblog` VALUES (1,33,24,'hello ae',0,0,'Active','2025-07-21 01:41:13'),(2,33,24,'s',0,0,'Active','2025-07-21 02:13:16');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactus`
--

LOCK TABLES `contactus` WRITE;
/*!40000 ALTER TABLE `contactus` DISABLE KEYS */;
INSERT INTO `contactus` VALUES (1,'Thạch Thất, Hà Nội','2025-06-24 14:42:17.937000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','hay vậy ta wowwwww','0969652156',22),(2,'Nghệ An province','2025-07-14 15:51:28.727000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','kkkkkkkkkkkkkkkkkkkkkk h','0969652156',30);
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
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery` (
  `galleryid` bigint NOT NULL AUTO_INCREMENT,
  `ProductID` int DEFAULT NULL,
  `imageurl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`galleryid`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (1,1,'https://png.pngtree.com/png-vector/20240719/ourlarge/pngtree-a-green-apple-with-water-drops-png-image_13149924.png'),(2,2,'https://png.pngtree.com/recommend-works/png-clipart/20250415/ourlarge/pngtree-oranges-on-white-plate-png-image_15951508.png'),(3,3,'https://png.pngtree.com/png-clipart/20190905/original/pngtree-a-peeled-banana-png-image_4513249.jpg'),(4,4,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(5,5,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(6,6,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(7,7,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(8,8,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(9,9,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(10,10,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(11,11,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(12,12,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(13,13,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(14,14,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(15,15,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(16,16,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(17,17,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(18,18,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(19,19,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(20,20,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(21,21,'FrontEnd/assets/images/veg-2/product/21.png'),(22,22,'FrontEnd/assets/images/veg-2/product/21.png'),(23,23,'FrontEnd/assets/images/veg-2/product/21.png'),(24,24,'FrontEnd/assets/images/veg-2/product/21.png'),(25,25,'FrontEnd/assets/images/veg-2/product/21.png'),(26,26,'FrontEnd/assets/images/veg-2/product/21.png'),(27,27,'FrontEnd/assets/images/veg-2/product/21.png'),(28,28,'FrontEnd/assets/images/veg-2/product/21.png'),(29,29,'FrontEnd/assets/images/veg-2/product/21.png'),(30,30,'FrontEnd/assets/images/veg-2/product/21.png'),(31,31,'FrontEnd/assets/images/veg-2/product/21.png'),(32,32,'FrontEnd/assets/images/veg-2/product/21.png'),(33,33,'FrontEnd/assets/images/veg-2/product/21.png'),(34,34,'FrontEnd/assets/images/veg-2/product/21.png'),(35,35,'FrontEnd/assets/images/veg-2/product/21.png'),(36,36,'FrontEnd/assets/images/veg-2/product/21.png'),(37,37,'FrontEnd/assets/images/veg-2/product/21.png'),(38,38,'FrontEnd/assets/images/veg-2/product/22.png'),(39,39,'FrontEnd/assets/images/veg-2/product/23.png'),(40,40,'FrontEnd/assets/images/veg-2/product/23.png'),(41,41,'FrontEnd/assets/images/veg-2/product/23.png'),(42,42,'FrontEnd/assets/images/veg-2/product/23.png'),(43,43,'FrontEnd/assets/images/veg-2/product/23.png'),(44,44,'FrontEnd/assets/images/veg-2/product/23.png'),(45,45,'FrontEnd/assets/images/veg-2/product/23.png'),(46,46,'FrontEnd/assets/images/veg-2/product/23.png'),(47,47,'FrontEnd/assets/images/veg-2/product/23.png'),(48,48,'FrontEnd/assets/images/veg-2/product/23.png'),(49,49,'FrontEnd/assets/images/veg-2/product/23.png'),(50,50,'FrontEnd/assets/images/veg-2/product/23.png'),(51,51,'FrontEnd/assets/images/veg-2/product/23.png'),(52,52,'FrontEnd/assets/images/veg-2/product/23.png'),(53,53,'FrontEnd/assets/images/veg-2/product/23.png'),(54,54,'FrontEnd/assets/images/veg-2/product/23.png'),(55,55,'FrontEnd/assets/images/veg-2/product/23.png'),(56,56,'FrontEnd/assets/images/veg-2/product/23.png'),(57,57,'FrontEnd/assets/images/veg-2/product/23.png'),(58,58,'FrontEnd/assets/images/veg-2/product/23.png'),(59,59,'FrontEnd/assets/images/veg-2/product/23.png'),(60,60,'FrontEnd/assets/images/veg-2/product/23.png'),(61,61,'FrontEnd/assets/images/veg-2/product/23.png'),(62,62,'FrontEnd/assets/images/veg-2/product/23.png'),(63,63,'FrontEnd/assets/images/veg-2/product/23.png'),(64,1,'https://png.pngtree.com/png-vector/20241024/ourlarge/pngtree-apple-with-leaf-and-sliced-half-for-fruit-designs-png-image_14154967.png'),(65,2,'FrontEnd/assets/images/veg-2/product/23.png'),(66,1,'https://img.lovepik.com/png/20231106/fruit-fresh-full-of-juice-cuisine-health-delicious_514362_wh860.png'),(67,1,'https://png.pngtree.com/png-vector/20240520/ourlarge/pngtree-green-apple-slice-isolated-on-white-background-png-image_12499540.png'),(68,1,'https://png.pngtree.com/png-clipart/20190618/original/pngtree-hand-painted-color-apple-cartoon-png-image_3949514.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myaddressbook`
--

LOCK TABLES `myaddressbook` WRITE;
/*!40000 ALTER TABLE `myaddressbook` DISABLE KEYS */;
INSERT INTO `myaddressbook` VALUES (1,'Nghệ An province','2025-06-23 16:40:29.397000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','hay nha',18),(2,'nghe an','2025-07-01 12:09:43.925000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','Home',23),(3,'Nghệ An province','2025-07-01 12:27:11.261000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','a',24),(4,'Nghệ An province','2025-07-01 12:27:25.736000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','So good','0969652156','Home',24),(5,'nghe an','2025-07-01 12:32:44.050000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','q','0969652156','home',25),(6,'Nghệ An province','2025-07-01 12:38:21.151000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','sdsdas','0969652156','home',25),(12,'nghe an','2025-07-03 16:04:39.856000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé','0969652156','Home',26),(13,'Thạch Thất, Hà Nội','2025-07-03 16:09:53.527000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship về đó','0969652156','Home',26),(14,'Thạch Thất, Hà Nội','2025-07-07 17:06:02.726000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship đến trọ','0969652156','trọ',29),(15,'hanoi','2025-07-10 21:28:25.327000','anhnhhe186218@fpt.edu.vn','Nguyễn Hoàng Anh','ship đến','0969652156','Trọ',11),(18,'nghe an','2025-07-14 16:38:47.192000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship đến đó','0969652156','nhà',30),(19,'Thạch Thất, Hà Nội','2025-07-16 07:40:17.094000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship đến đó','0969652156','nhà',31),(20,'Thị xã Hoàng Mai, Tỉnh Nghệ An','2025-07-18 17:47:50.610000','nguyenhoanganh712004qp@gmail.com','Nguyễn Hoàng Anh','Ship đến','0969652156','Nhà',32),(21,'Thị xã Hoàng Mai, Tỉnh Nghệ An','2025-07-18 18:28:01.645000','nguyenhoanganh712004qp@gmail.com','Nguyễn Hoàng Anh','Ship ','0969652156','Nhà',33),(22,'nghe an','2025-07-20 10:06:29.095000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','s','0969652156','s',33),(23,'Nghệ An province','2025-07-20 10:06:47.308000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','s','0969652156','nhà',33),(24,'Nghệ An province','2025-07-20 10:06:50.437000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','s','0969652156','nhà',33),(25,'Thạch Thất, Hà Nội','2025-07-21 02:33:07.028000','1234yeyeyeye1234@gmail.com','Nguyễn Hoàng Anh','ship','0969652156','nhà',34);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'123 Le Loi, HCM',0.00,'a@example.com',150000.00,'Nguyen Van A','Giao buổi sáng','ORD5823941','2025-06-11 10:15:00','0912345678','CANCELLED',150000.00,NULL,1,NULL),(2,'456 Nguyen Hue, HCM',0.00,'b@example.com',250000.00,'Tran Thi B','Không cay','#ORD3319058','2025-06-11 11:00:00','0923456789','PENDING',250000.00,NULL,2,NULL),(3,'789 Tran Hung Dao, HCM',0.00,'c@example.com',350000.00,'Le Van C','Giao giờ hành chính','#ORD2034876','2025-06-11 12:30:00','0934567890','CONFIRMED',350000.00,NULL,3,NULL),(4,'1010 Cach Mang Thang 8, HCM',0.00,'d@example.com',50000.00,'Pham Thi D','Đặt nhầm','#ORD4901732','2025-06-11 13:45:00','0945678901','DELIVERED',50000.00,NULL,4,NULL),(5,'1212 Vo Van Tan, HCM',0.00,'e@example.com',420000.00,'Vo Van E','Giao chiều muộn','#ORD7649205','2025-06-11 15:00:00','0956789012','DELIVERED',420000.00,NULL,5,NULL),(6,'789 Tran Hung Dao, HCM',0.00,'c@example.com',185000.00,'Le Thi C','Chưa xác định','#ORD0193842','2025-06-13 14:30:00','0934567890','DELIVERED',185000.00,NULL,3,NULL),(7,'321 Cach Mang Thang 8, HCM',0.00,'e@example.com',320000.00,'Pham Van E','Giao sau 6PM','#ORD8472109','2025-06-13 16:45:00','0945678901','REMOVED',320000.00,NULL,4,NULL),(8,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',257000.00,'Nguyễn Hoàng Anh','ship về trọ gửi về home','1','2025-07-03 16:05:22','0969652156','CONFIRMED',257000.00,'',26,NULL),(12,'Thạch Thất, Hà Nội',55000.00,'1234yeyeyeye1234@gmail.com',220000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','2','2025-07-03 16:21:42','0969652156','DELIVERED',275000.00,'SALE20P',26,1),(13,'nghe an',55000.00,'1234yeyeyeye1234@gmail.com',220000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','4','2025-07-03 16:44:53','0969652156','CANCELLED',275000.00,'SALE20P',26,1),(14,'Thạch Thất, Hà Nội',43000.00,'1234yeyeyeye1234@gmail.com',172000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','5','2025-07-03 16:47:53','0969652156','PENDING',215000.00,'SALE20P',26,1),(15,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',15000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','3','2025-07-03 16:50:40','0969652156','PENDING',15000.00,'',26,NULL),(16,'nghe an',5000.00,'1234yeyeyeye1234@gmail.com',55000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','6','2025-07-03 16:54:34','0969652156','PENDING',60000.00,'SALE5K',26,5),(17,'nghe an',5000.00,'1234yeyeyeye1234@gmail.com',55000.00,'Nguyễn Hoàng Anh','sản phẩm này cần tươi hơn nhé gửi về Home','7','2025-07-03 16:58:21','0969652156','PENDING',60000.00,'SALE5K',26,5),(18,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','99','2025-07-04 15:29:13','0969652156','PENDING',2000.00,'',26,NULL),(19,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','236487','2025-08-05 16:28:10','0969652156','PENDING',2000.00,'',26,NULL),(20,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','118695','2025-07-04 16:59:02','0969652156','PENDING',2000.00,'',26,NULL),(21,'Thạch Thất, Hà Nội',5000.00,'1234yeyeyeye1234@gmail.com',56000.00,'Nguyễn Hoàng Anh','ship về đó gửi về Home','215732','2025-07-05 17:34:01','0969652156','PENDING',61000.00,'SALE5K',26,5),(22,'Thạch Thất, Hà Nội',0.00,'1234yeyeyeye1234@gmail.com',26111.00,'Nguyễn Hoàng Anh','ship đến trọ (trọ)','749803','2025-07-07 17:42:05','0969652156','PENDING',26111.00,'',29,NULL),(23,'Thạch Thất, Hà Nội',5000.00,'1234yeyeyeye1234@gmail.com',66111.00,'Nguyễn Hoàng Anh','ship đến trọ (trọ)','479190','2025-07-07 17:51:52','0969652156','PENDING',71111.00,'SALE5K',29,5),(24,'hanoi',5000.00,'anhnhhe186218@fpt.edu.vn',60000.00,'Nguyễn Hoàng Anh','ship đến (Trọ)','724958','2025-07-10 21:29:33','0969652156','PENDING',65000.00,'SALE5K',11,5),(25,'Thị xã Hoàng Mai, Tỉnh Nghệ An',0.00,'nguyenhoanganh712004qp@gmail.com',15000.00,'Nguyễn Hoàng Anh','Ship  (Nhà)','140839','2025-07-18 18:29:39','0969652156','PENDING',15000.00,'',33,NULL),(26,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','s (s)','892284','2025-07-20 15:41:32','0969652156','CANCELLED',2000.00,'',33,NULL),(27,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',4000.00,'Nguyễn Hoàng Anh','s (s)','551113','2025-07-20 15:52:31','0969652156','CANCELLED',4000.00,'',33,NULL),(28,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',4000.00,'Nguyễn Hoàng Anh','s (s)','180644','2025-07-20 16:19:41','0969652156','PENDING',4000.00,'',33,NULL),(29,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',8000.00,'Nguyễn Hoàng Anh','s (s)','290043','2025-07-20 19:58:10','0969652156','CANCELLED',8000.00,'',33,NULL),(30,'nghe an',0.00,'1234yeyeyeye1234@gmail.com',2000.00,'Nguyễn Hoàng Anh','s (s)','888922','2025-07-21 02:14:49','0969652156','CANCELLED',2000.00,'',33,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (1,240000.00,2,1,1),(2,120000.00,1,1,1),(3,240000.00,3,1,2),(4,225000.00,5,1,3),(5,80000.00,1,1,2),(6,90000.00,2,1,3),(7,105000.00,6,3,8),(8,20000.00,9,1,8),(9,132000.00,22,6,8),(10,220000.00,22,10,12),(11,20000.00,9,1,12),(12,35000.00,6,1,12),(13,220000.00,22,10,13),(14,20000.00,9,1,13),(15,35000.00,6,1,13),(16,90000.00,8,3,14),(17,125000.00,19,5,14),(18,15000.00,13,1,15),(19,60000.00,13,4,16),(20,60000.00,13,4,17),(21,2000.00,5,1,18),(22,2000.00,5,1,19),(23,2000.00,5,1,20),(24,4000.00,5,2,21),(25,35000.00,6,1,21),(26,22000.00,10,1,21),(27,15000.00,1,1,22),(28,11111.00,64,1,22),(29,60000.00,1,4,23),(30,11111.00,64,1,23),(31,35000.00,6,1,24),(32,30000.00,8,1,24),(33,15000.00,1,1,25),(34,2000.00,5,1,26),(35,4000.00,5,2,27),(36,4000.00,5,2,28),(37,8000.00,5,4,29),(38,2000.00,5,1,30);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderreview`
--

LOCK TABLES `orderreview` WRITE;
/*!40000 ALTER TABLE `orderreview` DISABLE KEYS */;
INSERT INTO `orderreview` VALUES (1,'/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1751882269418_piclumenmarquee-06.webp','Thực phẩm sạch nha sếp ơi','215732',5,'0','0',26,'2025-07-07 16:55:39'),(2,'2','2','118695',2,'2','2',26,NULL),(3,'/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1751885531739_download.jpg','Hoa quả sạch','479190',4,'1','1',29,'2025-07-07 17:52:32'),(4,'/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1752157814728_piclumenmarquee-06.webp','ngon','724958',4,'1','1',11,'2025-07-10 21:29:59'),(5,'/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/1752838211507_piclumenmarquee-06.webp','sản phẩm ngon','140839',5,'1','1',33,'2025-07-18 18:30:02'),(6,'','','892284',0,'0','0',33,NULL),(7,'','','551113',0,'0','0',33,NULL),(8,'','','180644',0,'0','0',33,NULL),(9,'','','290043',0,'0','0',33,NULL),(10,'','','888922',0,'0','0',33,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'Cà rốt Đà Lạt','Sản phẩm Táo nhập khẩu từ Hoa Kỳ là một trong những loại trái cây được yêu thích nhất trên toàn thế giới, không chỉ bởi hương vị thơm ngon mà còn vì những lợi ích tuyệt vời đối với sức khỏe. Những trái táo này được chọn lọc từ những vườn táo nổi tiếng tại các vùng đất trù phú ở Hoa Kỳ, nơi có điều kiện khí hậu lý tưởng và quy trình canh tác hiện đại. Chính vì vậy, bạn hoàn toàn có thể yên tâm về chất lượng của từng trái táo, từ khi chúng được thu hoạch cho đến khi đến tay bạn.','ACTIVE'),(2,1,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(3,1,'Thăn bò Úc đông lạnh','Thịt bò thăn đông lạnh','ACTIVE'),(4,1,'Tôm sú biển đông lạnh','Tôm sú nguyên con đông lạnh','ACTIVE'),(5,1,'Muối i-ốt','Muối i-ốt tinh khiết','ACTIVE'),(6,1,'Trá trà atiso','Trá atiso túi lọc','ACTIVE'),(7,7,'Gạo ST25','Gạo thơm ST25 chất lượng cao','ACTIVE'),(8,8,'Sữa Vinamilk','Sữa tươi tiệt trùng','ACTIVE'),(9,1,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống','ACTIVE'),(10,1,'Bún dong','Bún dong khô sạch','ACTIVE'),(11,9,'kẹo','Clean dried glass noodles','ACTIVE'),(12,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(13,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(14,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(15,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(16,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(17,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(18,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(19,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(20,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(21,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(22,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(23,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(24,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(25,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(26,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(27,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(28,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(29,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(30,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(31,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(32,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(33,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(34,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(35,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(36,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(37,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(38,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(39,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(40,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(41,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(42,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(43,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(44,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(45,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(46,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(47,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(48,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(49,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(50,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(51,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE'),(52,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(53,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(54,1,'Cà rốt Đà Lạt','Cà rốt tươi, ngọt','ACTIVE'),(55,2,'Táo Mỹ','Táo nhập khẩu từ Hoa Kỳ','ACTIVE'),(56,3,'Thăn bò Úc đông lạnh','Thịt thăn bò đông lạnh hảo hạng','ACTIVE'),(57,4,'Tôm sú biển đông lạnh','Tôm sú nguyên con, cấp đông IQF','ACTIVE'),(58,5,'Muối i-ốt','Muối i-ốt tinh khiết, đóng gói 500 g','ACTIVE'),(59,6,'Trà atiso túi lọc','Trá atiso Đà Lạt, 25 túi/hộp','ACTIVE'),(60,7,'Gạo thơm ST25','Gạo ST25 chất lượng cao, bao 5 kg','ACTIVE'),(61,8,'Sữa tươi Vinamilk','Sữa tiệt trùng 100% sữa tươi','ACTIVE'),(62,9,'Kẹo dừa Bến Tre','Kẹo dừa truyền thống, hộp 200 g','ACTIVE'),(63,9,'Bún dong khô','Bún dong sạch, gói 500 g','ACTIVE');
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
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatch`
--

LOCK TABLES `productbatch` WRITE;
/*!40000 ALTER TABLE `productbatch` DISABLE KEYS */;
INSERT INTO `productbatch` VALUES (1,1,'2025-12-01',20,0),(2,2,'2025-12-03',200,50),(3,3,'2024-12-05',150,30),(4,4,'2024-12-07',180,40),(5,5,'2024-12-09',250,60),(6,6,'2024-12-11',300,100),(7,7,'2024-12-13',120,20),(8,8,'2024-12-15',500,200),(9,9,'2024-12-17',220,90),(10,10,'2024-12-19',400,150),(11,11,'2024-12-01',100,20),(12,12,'2024-12-03',200,50),(13,13,'2024-12-05',150,30),(14,14,'2024-12-07',180,40),(15,15,'2024-12-09',250,60),(16,16,'2024-12-11',300,100),(17,17,'2024-12-13',120,20),(18,18,'2024-12-15',500,200),(19,19,'2024-12-17',220,90),(20,20,'2024-12-19',400,150),(21,21,'2024-12-21',350,130),(22,22,'2024-12-23',400,160),(23,23,'2024-12-25',270,90),(24,24,'2024-12-27',300,120),(25,25,'2024-12-29',320,110),(26,26,'2024-12-31',230,70),(27,27,'2025-01-02',150,40),(28,28,'2025-01-04',290,100),(29,29,'2025-01-06',180,60),(30,30,'2025-01-08',250,80),(31,31,'2025-01-10',200,90),(32,32,'2025-01-12',270,100),(33,33,'2025-01-14',340,120),(34,34,'2025-01-16',310,110),(35,35,'2025-01-18',150,50),(36,36,'2025-01-20',400,150),(37,37,'2025-01-22',280,90),(38,38,'2025-01-24',350,130),(39,39,'2025-01-26',220,80),(40,40,'2025-01-28',230,90),(41,41,'2025-01-30',500,200),(42,42,'2025-02-01',210,60),(43,43,'2025-02-03',320,130),(44,44,'2025-02-05',200,70),(45,45,'2025-02-07',300,110),(46,46,'2025-02-09',270,90),(47,47,'2025-02-11',330,120),(48,48,'2025-02-13',250,80),(49,49,'2025-02-15',350,140),(50,50,'2025-02-17',180,60),(51,51,'2025-02-19',300,120),(52,52,'2025-02-21',200,70),(53,53,'2025-02-23',400,160),(54,54,'2025-02-25',250,90),(55,55,'2025-02-27',320,130),(56,56,'2025-03-01',200,60),(57,57,'2025-03-03',280,100),(58,58,'2025-03-05',220,80),(59,59,'2025-03-07',340,120),(60,60,'2025-03-09',400,150),(61,61,'2025-03-11',350,130),(62,62,'2025-03-13',230,80),(63,63,'2025-03-15',270,90),(64,64,'2026-03-15',270,90),(65,65,'2025-03-15',230,90),(66,1,'2025-07-04',109,0),(67,2,'2025-06-03',121,0),(68,3,'2025-07-03',99,0),(69,1,'2024-06-24',100,6),(70,2,'2025-06-07',110,0),(71,1,'2025-06-13',100,0),(72,4,'2025-06-02',115,0),(73,5,'2025-06-04',120,0),(74,6,'2025-06-06',110,0),(75,7,'2025-06-08',100,5),(76,8,'2025-06-10',105,0),(77,9,'2025-06-12',115,0),(78,10,'2025-06-14',122,0),(79,11,'2025-06-16',119,0),(80,12,'2025-06-18',101,0),(81,13,'2025-06-20',111,0),(82,14,'2025-07-03',123,0),(83,15,'2025-06-24',117,0),(84,1,'2025-06-26',120,0),(85,2,'2025-06-28',100,0),(86,3,'2025-06-30',108,6),(87,4,'2025-07-02',121,0),(88,5,'2025-07-04',115,0),(89,6,'2025-07-03',110,0),(90,7,'2025-07-08',100,0),(91,8,'2025-07-10',105,0),(92,9,'2025-07-12',115,0),(93,10,'2025-07-14',122,0),(94,11,'2025-07-16',119,0),(95,12,'2025-07-18',101,0),(96,13,'2025-07-20',111,0),(97,14,'2025-07-22',123,0),(98,15,'2025-07-24',117,0),(99,4,'2025-08-01',112,0),(100,5,'2025-07-13',110,0),(101,2,'2024-06-06',120,6),(102,7,'2025-08-07',115,0),(103,8,'2025-08-09',120,0),(104,9,'2025-08-11',110,0),(105,10,'2025-08-13',105,0),(106,12,'2025-08-17',122,0),(107,13,'2025-08-19',101,0),(108,14,'2025-08-21',125,0),(109,15,'2025-08-23',103,0),(110,1,'2025-08-25',100,0),(111,2,'2025-08-27',117,0),(112,3,'2025-08-29',114,0),(113,5,'2025-09-02',102,0),(114,6,'2025-09-04',111,0),(115,7,'2023-10-06',2,2),(116,1,'2025-07-04',1,0),(117,6,'2025-07-04',1,0),(118,13,'2025-07-24',110,0),(119,11,'2025-07-04',122,0),(120,11,'2025-07-06',100,0),(121,4,'2025-07-04',100,0),(122,7,'2025-07-02',1,0),(123,15,'2025-07-07',23,0),(124,1,'2025-07-04',109,0),(125,2,'2025-06-03',121,0),(126,3,'2025-07-03',99,0),(127,1,'2024-06-24',100,6),(128,2,'2025-06-07',110,0),(129,1,'2025-06-13',100,0),(130,4,'2025-06-02',115,0),(131,5,'2025-06-04',120,0),(132,6,'2025-06-06',110,0),(133,7,'2025-06-08',100,5),(134,8,'2025-06-10',105,0),(135,9,'2025-06-12',115,0),(136,10,'2025-06-14',122,0),(137,11,'2025-06-16',119,0),(138,12,'2025-06-18',101,0),(139,13,'2025-06-20',111,0),(140,14,'2025-07-03',123,0),(141,15,'2025-06-24',117,0),(142,1,'2025-06-26',120,0),(143,2,'2025-06-28',100,0),(144,3,'2025-06-30',108,6),(145,4,'2025-07-02',121,0),(146,5,'2025-07-04',115,0),(147,6,'2025-07-03',110,0),(148,7,'2025-07-08',100,0),(149,8,'2025-07-10',105,0),(150,9,'2025-07-12',115,0),(151,10,'2025-07-14',122,0),(152,11,'2025-07-16',119,0),(153,12,'2025-07-18',101,0),(154,13,'2025-07-20',111,0),(155,14,'2025-07-22',123,0),(156,15,'2025-07-24',117,0),(157,4,'2025-08-01',112,0),(158,5,'2025-07-13',110,0),(159,2,'2024-06-06',120,6),(160,7,'2025-08-07',115,0),(161,8,'2025-08-09',120,0),(162,9,'2025-08-11',110,0),(163,10,'2025-08-13',105,0),(164,12,'2025-08-17',122,0),(165,13,'2025-08-19',101,0),(166,14,'2025-08-21',125,0),(167,15,'2025-08-23',103,0),(168,1,'2025-08-25',100,0),(169,2,'2025-08-27',117,0),(170,3,'2025-08-29',114,0),(171,5,'2025-09-02',102,0),(172,6,'2025-09-04',111,0),(173,7,'2023-10-06',2,2),(174,1,'2025-07-04',1,0),(175,6,'2025-07-04',1,0),(176,13,'2025-07-24',110,0),(177,11,'2025-07-04',122,0),(178,11,'2025-07-06',100,0),(179,4,'2025-07-04',100,0),(180,7,'2025-07-02',1,0),(181,15,'2025-07-07',23,0);
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
  `status` enum('ACTIVE','INACTIVE','OUT_OF_STOCK') DEFAULT NULL,
  PRIMARY KEY (`ProductDetailID`),
  KEY `FK5tqssatrmfiu9ayh3futup0wu` (`productid`),
  KEY `FKejqb8da7lrkwppkkyi56w6r84` (`CategoryWeightID`),
  CONSTRAINT `FK5tqssatrmfiu9ayh3futup0wu` FOREIGN KEY (`productid`) REFERENCES `product` (`ProductID`),
  CONSTRAINT `FKejqb8da7lrkwppkkyi56w6r84` FOREIGN KEY (`CategoryWeightID`) REFERENCES `categoryweight` (`CategoryWeightID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetail`
--

LOCK TABLES `productdetail` WRITE;
/*!40000 ALTER TABLE `productdetail` DISABLE KEYS */;
INSERT INTO `productdetail` VALUES (1,1,15000,1,7,'500g Bag','ACTIVE'),(2,2,45000,2,10,'1kg Bag','INACTIVE'),(3,2,120000,3,5,'800g Box','ACTIVE'),(4,4,180000,4,3,'1kg Pack','ACTIVE'),(5,5,2000,5,24,'500g Pack','ACTIVE'),(6,6,35000,6,12,'20 Tea Bags Box','ACTIVE'),(7,7,25000,7,60,'2kg Bag','ACTIVE'),(8,8,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(9,9,20000,9,180,'300g Pack','ACTIVE'),(10,10,22000,10,12,'500g Bag','ACTIVE'),(11,11,33000,11,12,'500g Bag','ACTIVE'),(12,12,13000,12,7,'500g Bag','ACTIVE'),(13,13,15000,1,7,'500g Bag','ACTIVE'),(14,14,45000,2,10,'1kg Bag','ACTIVE'),(15,15,120000,3,5,'800g Box','ACTIVE'),(16,16,180000,4,3,'1kg Pack','ACTIVE'),(17,17,10000,5,24,'500g Pack','ACTIVE'),(18,18,35000,6,12,'20 Tea Bags Box','ACTIVE'),(19,19,25000,7,60,'2kg Bag','ACTIVE'),(20,20,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(21,21,20000,9,180,'300g Pack','ACTIVE'),(22,22,22000,10,12,'500g Bag','ACTIVE'),(23,23,33000,11,12,'500g Bag','ACTIVE'),(24,24,13000,12,7,'500g Bag','ACTIVE'),(25,25,15000,1,7,'500g Bag','ACTIVE'),(26,26,45000,2,10,'1kg Bag','ACTIVE'),(27,27,120000,3,5,'800g Box','ACTIVE'),(28,28,180000,4,3,'1kg Pack','ACTIVE'),(29,29,10000,5,24,'500g Pack','ACTIVE'),(30,30,35000,6,12,'20 Tea Bags Box','ACTIVE'),(31,31,25000,7,60,'2kg Bag','ACTIVE'),(32,32,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(33,33,20000,9,180,'300g Pack','ACTIVE'),(34,34,22000,10,12,'500g Bag','ACTIVE'),(35,35,33000,11,12,'500g Bag','ACTIVE'),(36,36,13000,12,7,'500g Bag','ACTIVE'),(37,37,15000,1,7,'500g Bag','ACTIVE'),(38,38,45000,2,10,'1kg Bag','ACTIVE'),(39,39,120000,3,5,'800g Box','ACTIVE'),(40,40,180000,4,3,'1kg Pack','ACTIVE'),(41,41,15000,1,7,'500g Bag','ACTIVE'),(42,42,45000,2,10,'1kg Bag','ACTIVE'),(43,43,120000,3,5,'800g Box','ACTIVE'),(44,44,180000,4,3,'1kg Pack','ACTIVE'),(45,45,10000,5,24,'500g Pack','ACTIVE'),(46,46,35000,6,12,'20 Tea Bags Box','ACTIVE'),(47,47,25000,7,60,'2kg Bag','ACTIVE'),(48,48,30000,8,30,'Box of 4 Small Packs','ACTIVE'),(49,49,20000,9,180,'300g Pack','ACTIVE'),(50,50,22000,10,12,'500g Bag','ACTIVE'),(51,51,33000,11,12,'500g Bag','ACTIVE'),(52,52,13000,12,7,'500g Bag','ACTIVE'),(53,53,15000,1,7,'500g Bag','ACTIVE'),(54,54,45000,2,10,'1kg Bag','ACTIVE'),(55,55,120000,3,5,'800g Box','ACTIVE'),(56,56,180000,4,3,'1kg Pack','ACTIVE'),(57,57,11111,5,3,'1kg Pack','ACTIVE'),(58,58,1111,5,3,'1kg Pack','ACTIVE'),(59,59,11111,5,3,'1kg Pack','ACTIVE'),(60,60,11111,5,3,'1kg Pack','ACTIVE'),(61,61,11111,5,3,'1kg Pack','ACTIVE'),(62,62,11111,5,3,'1kg Pack','ACTIVE'),(63,63,11111,5,3,'1kg Pack','ACTIVE'),(64,1,11111,5,3,'1kg Pack','ACTIVE'),(65,4,11111111,5,3,'1kg Pack','ACTIVE');
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
INSERT INTO `role` VALUES (1,'admin','Active',NULL),(2,'user','Active',NULL),(3,'blogger','Active',NULL),(4,'seller','Active',NULL),(5,'warehourestaff','Active',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldbyseller`
--

LOCK TABLES `soldbyseller` WRITE;
/*!40000 ALTER TABLE `soldbyseller` DISABLE KEYS */;
INSERT INTO `soldbyseller` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,2),(6,6,2),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,1),(13,13,1),(14,14,1),(15,15,1),(16,16,1),(17,17,1),(18,18,1),(19,19,1),(20,20,1),(21,21,1),(22,22,1),(23,23,1),(24,24,1),(25,25,1),(26,26,1),(27,27,1),(28,28,1),(29,29,1),(30,30,1),(31,31,1),(32,32,1),(33,33,1),(34,34,1),(35,35,1),(36,36,1),(37,37,1),(38,38,1),(39,39,1),(40,40,1),(41,41,1),(42,42,1),(43,43,1),(44,44,1),(45,45,1),(46,46,1),(47,47,1),(48,48,1),(49,49,1),(50,50,1),(51,51,1),(52,52,1),(53,53,1),(54,54,1),(55,55,1),(56,56,1),(57,57,1),(58,58,1),(59,59,1),(60,60,1),(61,60,1),(62,61,1),(63,63,1),(64,64,1),(65,65,1);
/*!40000 ALTER TABLE `soldbyseller` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockin`
--

LOCK TABLES `stockin` WRITE;
/*!40000 ALTER TABLE `stockin` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockindetail`
--

DROP TABLE IF EXISTS `stockindetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockindetail` (
  `StockInID` int NOT NULL,
  `Quantity` int NOT NULL,
  `unitprice` int DEFAULT NULL,
  `BatchID` int DEFAULT NULL,
  KEY `StockInID` (`StockInID`),
  KEY `BatchID` (`BatchID`),
  CONSTRAINT `stockindetail_ibfk_1` FOREIGN KEY (`StockInID`) REFERENCES `stockin` (`StockInID`),
  CONSTRAINT `stockindetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockindetail`
--

LOCK TABLES `stockindetail` WRITE;
/*!40000 ALTER TABLE `stockindetail` DISABLE KEYS */;
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
  `stockoutdate` date DEFAULT NULL,
  `note` tinytext,
  PRIMARY KEY (`StockOutID`),
  KEY `WarehouseID` (`WarehouseID`),
  KEY `OrderID` (`orderid`),
  CONSTRAINT `FK7ljvep6rr4pj89v3khvhofxtl` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderid`),
  CONSTRAINT `stockout_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockout`
--

LOCK TABLES `stockout` WRITE;
/*!40000 ALTER TABLE `stockout` DISABLE KEYS */;
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
  `OrderID` int NOT NULL,
  `quantity` int NOT NULL,
  `orderdetailid` bigint NOT NULL,
  PRIMARY KEY (`StockOutDetailID`),
  KEY `StockOutID` (`StockOutID`),
  KEY `BatchID` (`BatchID`),
  KEY `idx_stockoutdetail_OrderID` (`OrderID`),
  KEY `FKd8fm5wpab7si40c2f6eyutqvn` (`orderdetailid`),
  CONSTRAINT `FKd8fm5wpab7si40c2f6eyutqvn` FOREIGN KEY (`orderdetailid`) REFERENCES `orderdetail` (`orderdetailid`),
  CONSTRAINT `stockoutdetail_ibfk_1` FOREIGN KEY (`StockOutID`) REFERENCES `stockout` (`StockOutID`),
  CONSTRAINT `stockoutdetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockoutdetail`
--

LOCK TABLES `stockoutdetail` WRITE;
/*!40000 ALTER TABLE `stockoutdetail` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `updateprofilehistory`
--

LOCK TABLES `updateprofilehistory` WRITE;
/*!40000 ALTER TABLE `updateprofilehistory` DISABLE KEYS */;
INSERT INTO `updateprofilehistory` VALUES (1,'Username:Hoàng Anh Đẹp z','2025-06-18 16:38:09.041000',16),(2,'Username:Hoàng Anh Đẹp Zai vippro','2025-06-18 16:38:41.727000',16),(3,'Full Name:Nguyễn , Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:Fri Jun 01 00:00:00 ICT 2007','2025-06-24 11:48:20.067000',21),(4,'','2025-06-24 11:50:09.962000',21),(5,'Full Name:Nguyễn Hoàng Anh','2025-06-24 11:53:41.387000',21),(6,'','2025-06-24 11:56:23.529000',21),(7,'Dob:Wed Jan 07 00:00:00 ICT 2004','2025-06-24 11:57:03.951000',21),(8,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:Thu Jan 01 00:00:00 ICT 2004','2025-06-24 13:06:52.634000',23),(9,'','2025-07-01 11:21:43.789000',23),(10,'Dob:2004-01-07','2025-07-01 12:10:24.969000',23),(11,'Dob:2004-01-08','2025-07-01 12:13:06.371000',23),(12,'Dob:2004-01-07','2025-07-01 12:13:17.003000',23),(13,'Phone:0969652156, Address:Nghệ An, Gender:male, Dob:2004-01-07','2025-07-01 12:24:16.330000',24),(14,'','2025-07-01 12:25:23.987000',24),(15,'Gender:female','2025-07-01 12:25:35.164000',24),(16,'Gender:male','2025-07-01 12:25:44.604000',24),(17,'Address:a','2025-07-01 12:27:39.569000',24),(18,'Address:Nghệ An province','2025-07-01 12:28:03.882000',24),(19,'Full Name:Nguyễn Hoàng Anh Dzai','2025-07-01 12:28:16.432000',24),(20,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Nghệ An province, Gender:male, Dob:2004-01-07','2025-07-06 16:44:03.168000',26),(21,'Address:Thị xã Hoàng Mai, Nghệ An province','2025-07-06 16:44:34.286000',26),(22,'Username:Hoàng Anh, Phone:0969652156, Address:Nghệ An province, Gender:male, Dob:2004-01-07','2025-07-07 17:05:29.966000',29),(23,'Username:Hoàng Anh, Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Nghệ An, Gender:male, Dob:2004-01-07','2025-07-10 21:37:01.892000',11),(24,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thị xã Hoàng Mai, Tỉnh Nghệ An, Gender:male, Dob:2004-01-07','2025-07-14 16:57:10.992000',30),(25,'Full Name:Nguyễn Hoàng Anh, Phone:0969652156, Address:Thạch Thất, Hà Nội, Gender:male, Dob:2004-01-07','2025-07-18 18:23:04.491000',33);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,4,'bà chủ thầu',NULL,NULL,'john.doe@example.com',NULL,NULL,'female','Active',NULL,'2025-06-07 18:34:36',NULL,NULL,NULL,NULL,''),(2,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:34:40',NULL,NULL,NULL,NULL,''),(3,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:36:09',NULL,NULL,NULL,NULL,''),(4,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:38:56',NULL,NULL,NULL,NULL,''),(5,4,NULL,NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 18:52:53',NULL,NULL,NULL,NULL,''),(6,2,'john.doe@example.com',NULL,NULL,'john.doe@example.com',NULL,NULL,NULL,'Active',NULL,'2025-06-07 19:15:07',NULL,NULL,NULL,'Inactive',''),(7,2,'Hoàng Anh đep zai vippro','Nguyễn Hoàng Anh',NULL,'yeyeye@gmail.com','0969652156','Nghệ An ','male','','2004-01-07','2025-06-07 20:33:32','305952','2025-06-15 10:32:16',NULL,'Inactive',''),(8,2,'Admin01',NULL,NULL,'nguyenhoanganh@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-09 09:36:24','987044',NULL,'SDS','Active',''),(9,2,'Admin01',NULL,NULL,'hello@fpt.edu.vn',NULL,NULL,NULL,'Active',NULL,'2025-06-10 09:46:03','333889',NULL,'In',NULL,''),(10,2,'Admin01',NULL,NULL,'hello1@fpt.edu.vn',NULL,NULL,NULL,'Active',NULL,'2025-06-10 10:00:13','074940',NULL,'In','Active',''),(11,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1752157563864_piclumenmarquee-06.webp','anhnhhe186218@fpt.edu.vn','0969652156','Nghệ An','male','Active','2004-01-07','2025-06-10 10:03:05','020085','2025-07-10 21:37:02','In','Active',''),(12,2,'Admin01',NULL,NULL,'nguyenhoanganhqp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-11 07:36:46','298714',NULL,'In','Inactive',''),(13,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','nguyenhoanganh71200qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-11 08:08:16',NULL,NULL,'108664088110428909546','Active',''),(14,2,'hoàng anh','Nguyễn Hoàng Anh',NULL,'1234yeyeyeye@gmail.com','0969652151','Nghệ An province','male','Active','2007-06-05','2025-06-15 10:34:45','383949','2025-06-15 12:10:42','In','Inactive',''),(15,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1749990153213_piclumen-marquee-06.webp','1234yeyeyeye12@gmail.com','0969652156','Nghệ An province','male','Active','2004-01-07','2025-06-15 12:22:08','006872','2025-06-15 15:28:32','In','Inactive',''),(16,2,'Hoàng Anh Đẹp Zai vippro','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1749999362751_piclumen-marquee-06.webp','1234yeyeyeye123@gmail.com','0969652156','Nghệ An','male','Active','2005-01-07','2025-06-15 19:31:00','701652','2025-06-18 16:38:42','Inactive','Inactive',''),(17,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye12@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-19 09:05:20',NULL,NULL,'102069777070774205995','Active',''),(18,2,'Channel','Ye123 Channel','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1750303920404_piclumen-marquee-06.webp','1234yeyeyeye123@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-19 09:25:00',NULL,NULL,'102069777070774205995','Active',''),(19,2,'hoàng anháddada',NULL,NULL,'nguyenhoanganh71204qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-19 09:39:21','644256',NULL,'Inactive','Inactive',''),(20,2,'Hoàng Anh',NULL,NULL,'1234yeyeyeye134@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-24 11:27:43','527151','2025-06-24 11:27:43','Inactive','Inactive','$2a$10$Ds/l13y2LJfev/hrGSoa.OnVPvGyvH/BBRMfmNICC59.zEY/kyAS.'),(21,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1750740452655_piclumen-marquee-06.webp','1234yeyeyey@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-07','2025-06-24 11:45:49','829281','2025-06-24 12:16:52','Inactive','Inactive','$2a$10$EOUE3a/MHHePH7flzbEYOemWSu7pT5BGNKyGPedyKRM7NImYgeYr6'),(22,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','nguyenhoang@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-06-24 13:00:32',NULL,NULL,'108664088110428909546','Active',''),(23,2,'Hoàng anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1750745230723_piclumen-marquee-06.webp','12341234@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-07','2025-06-24 13:05:49','016368','2025-07-01 12:13:17','Inactive','Inactive','$2a$10$FLWW5FUM8gga8HYu5p/psOwS4HlaxCHhE1ANsobSLXBN2tSUAYtmW'),(24,2,'Hoàng Anh','Nguyễn Hoàng Anh Dzai','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','p@gmail.com','0969652156','Nghệ An province','male','Active','2004-01-07','2025-07-01 12:16:36',NULL,'2025-07-01 12:28:16','108664088110428909546','Active',''),(25,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','234@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-01 12:32:09',NULL,NULL,'102069777070774205995','Active',''),(26,2,'Hoàng Anh','Nguyễn Hoàng Anh','/AgriculturalWarehouseManagement/FrontEnd/assets/images/inner-page/user/1751881152286_ChatGPT Image 12_54_38 7 thg 7, 2025.png','1234yeyeyeye1@gmail.com','0969652156','Thị xã Hoàng Mai, Nghệ An province','male','Active','2004-01-07','2025-07-01 12:40:14','738907','2025-07-07 16:39:12','Inactive','Inactive','$2a$10$CAfRIZ9dvWG.bc7x.lauKeHxWLkl1MILRZOs9O6oeh0AkcvdRkTj.'),(27,2,'Hoàng Anh',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nguyenhoanganh712004qp@gms',NULL,NULL,NULL,'Active',NULL,'2025-07-01 14:32:57','416706',NULL,'Inactive','Inactive','$2a$10$7QvcQ2aNaXXEExOx1D6Qb.K3hPH6TNRu6BkHHtIW02zK.lp1hFrCq'),(28,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://lh3.googleusercontent.com/a/ACg8ocIAFIRSrfKftOCWJh3-xbCnBxWvrwxpgVS8-G1oKoslPCl9dQ=s96-c','nguyenhoanganh7p@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-06 18:23:51',NULL,NULL,'108664088110428909546','Active',''),(29,2,'Hoàng Anh','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye134@gmail.com','0969652156','Nghệ An province','male','Active','2004-01-07','2025-07-07 17:04:04',NULL,'2025-07-07 17:05:30','102069777070774205995','Active',''),(30,2,'Hoàng Anh','Nguyễn Hoàng An','/AgriculturalWarehouseManagementApplication/FrontEnd/assets/images/inner-page/user/1752554639647_piclumenmarquee-06.webp','nguyenhoanganh71200ddd4qp@gmail.com','0969652156','Thị xã Hoàng Mai, Tỉnh Nghệ An','male','Active','2004-01-07','2025-07-14 14:40:56','613172','2025-07-15 11:44:00','Inactive','Inactive','$2a$10$RX.uhp6MI0k3m2P5r0Mvue.4fCYNyBr40Wr4Y1uTPveZ3orhCO8ri'),(31,2,'Channel','Ye123 Channel','https://lh3.googleusercontent.com/a/ACg8ocKVTg5NafhXt6Tk1zen8oHxiYoZqgHovjFqDesbjdumoy97XW0=s96-c','1234yeyeyeye111134@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-15 17:25:51',NULL,'2025-07-15 17:26:20','102069777070774205995','Active',''),(32,2,'Hoàng Anh 111',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nguyenhoanganh712ss004qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-18 09:38:36','149044','2025-07-18 09:54:50','Inactive','Inactive','$2a$10$O5p7AttNm.YAFLbsK3G9HuANJYx/NxKIgUJFpc4UgHwL8IvvgT/ga'),(33,2,'Hoàng Anh','Nguyễn Hoàng Anh','https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nguyenhoanganh71200ssssss4qp@gmail.com','0969652156','Thạch Thất, Hà Nội','male','Active','2004-01-07','2025-07-18 18:21:50','957851','2025-07-18 18:23:05','Inactive','Inactive','$2a$10$bUykq.gpXHmaX/YBVe1PcOKta5Ije62loA3Ih0d5417us77DVL2vS'),(34,2,'Hoàng Anh Pro',NULL,'https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg','nguyenhoanganh712004qp@gmail.com',NULL,NULL,NULL,'Active',NULL,'2025-07-21 02:31:34','733984',NULL,'Inactive','Inactive','$2a$10$MveTRhuZ7/KVT1G.omgZtuKBZvXTg/mjPjg08v59.U14/aIAjnv2G');
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
INSERT INTO `voucher` VALUES (1,'PERCENT',20.00,'2025-07-21 12:00:00.000000',_binary '\0',200000.00,91,'2025-06-10 00:00:00.000000','ACTIVE',1,'SALE20P'),(2,'AMOUNT',50000.00,'2025-07-21 12:00:00.000000',_binary '\0',250000.00,10,'2025-06-01 00:00:00.000000','ACTIVE',1,'HOTDEAL'),(3,'AMOUNT',50000.00,'2025-07-21 12:00:00.000000',_binary '\0',100000.00,100,'2025-05-01 00:00:00.000000','EXPIRED',45,'EXPIRED50'),(4,'PERCENT',15.00,'2025-07-21 12:00:00.000000',_binary '',150000.00,20,'2025-07-01 00:00:00.000000','ACTIVE',10,'COMINGSOON'),(5,'AMOUNT',5000.00,'2025-07-21 12:00:00.000000',_binary '\0',50000.00,31,'2025-06-15 15:01:00.000000','ACTIVE',5,'SALE5K');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallets`
--

LOCK TABLES `wallets` WRITE;
/*!40000 ALTER TABLE `wallets` DISABLE KEYS */;
INSERT INTO `wallets` VALUES (1,3000.00,33),(2,0.00,34);
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
INSERT INTO `warehouse` VALUES (1,'2','2','2');
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,16,7),(4,17,1),(5,17,1),(7,17,2),(8,17,10),(37,18,1),(38,18,62),(39,18,16),(40,18,2),(43,23,1),(44,23,1),(48,23,7),(49,23,8),(50,23,10),(51,23,12),(52,23,43),(53,23,43),(54,23,55),(55,29,1),(57,11,17),(60,33,6),(61,33,8),(62,33,6);
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

-- Dump completed on 2025-07-21  2:41:44
