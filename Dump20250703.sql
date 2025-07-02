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
  `BatchID` int DEFAULT NULL,
  `AdjustmentQuantity` int NOT NULL,
  `AdjustDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `reason` varchar(255) DEFAULT NULL,
  `AdjustmentType` enum('Add','Remove') NOT NULL,
  PRIMARY KEY (`AdjustmentID`),
  KEY `BatchID` (`BatchID`),
  CONSTRAINT `adjustment_ibfk_1` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustment`
--

LOCK TABLES `adjustment` WRITE;
/*!40000 ALTER TABLE `adjustment` DISABLE KEYS */;
INSERT INTO `adjustment` VALUES (2,4,12,'2025-06-24 14:47:44','....','Add'),(3,4,120,'2025-06-24 14:48:26','...','Remove');
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
  `UrlAuthorized` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `FeatureDescription` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `StatusSetting` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`AuthorizationID`),
  KEY `RoleID` (`RoleID`),
  CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `BlogID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `BlogCategoryID` int DEFAULT NULL,
  `Title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `BlogDateUpdate` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Author` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`BlogID`),
  KEY `UserID` (`UserID`),
  KEY `BlogCategoryID` (`BlogCategoryID`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`BlogCategoryID`) REFERENCES `blogcategory` (`BlogCategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,1,'Cách chọn giống lúa năng suất cao','Nội dung chi tiết về cách chọn giống lúa năng suất cao, chia sẻ kinh nghiệm thực tế từ các nông dân.','2025-06-26 14:35:21',NULL,'Active','Nguyễn Văn A'),(2,2,2,'Thị trường nông sản tháng 6','Bài viết phân tích thị trường nông sản tháng 6, cập nhật giá và xu hướng.','2025-06-26 14:35:21',NULL,'Active','Trần Thị B'),(3,1,1,'Kỹ thuật tưới tiêu thông minh','Giới thiệu các giải pháp tưới tiêu...','2025-06-26 14:35:21',NULL,'Active','Nguyễn Văn A'),(4,2,2,'Xu hướng phân bón hữu cơ','Bài viết về ưu điểm của phân bón hữu cơ...','2025-06-26 14:35:21',NULL,'Active','Trần Thị B'),(5,1,1,'Bảo quản nông sản sau thu hoạch','Các bước bảo quản nông sản đúng cách...','2025-06-26 14:35:21',NULL,'Active','Nguyễn Văn A'),(6,2,2,'Thị trường lúa gạo xuất khẩu','Phân tích thị trường xuất khẩu lúa gạo...','2025-06-26 14:35:21',NULL,'Active','Trần Thị B'),(7,1,1,'Ứng dụng công nghệ vào nông nghiệp','Ứng dụng IoT, AI vào sản xuất...','2025-06-26 14:35:21',NULL,'Active','Nguyễn Văn A'),(8,2,2,'Dự báo giá rau củ quả','Những yếu tố ảnh hưởng tới giá...','2025-06-26 14:35:21',NULL,'Active','Trần Thị B'),(9,1,1,'Kinh nghiệm trồng cây ăn trái','Chia sẻ kinh nghiệm thực tế...','2025-06-26 14:35:21',NULL,'Active','Nguyễn Văn A'),(10,2,2,'Xu hướng tiêu dùng nông sản sạch','Lợi ích và nhu cầu thực tế...','2025-06-26 14:35:21',NULL,'Active','Trần Thị B'),(11,3,1,'Kỹ thuật nuôi gà thả vườn','Hướng dẫn nuôi gà thả vườn hiệu quả.','2025-06-26 14:35:21',NULL,'Active','Đào Huy Hoàng'),(12,4,2,'Báo cáo thị trường chè tháng 6','Phân tích giá chè và xu hướng hiện tại.','2025-06-26 14:35:21',NULL,'Active','Tập Cận Bình'),(13,5,1,'Cách làm đất trồng khoai lang','Các bước làm đất để trồng khoai lang năng suất cao.','2025-06-26 14:35:21',NULL,'Active','Mát Jacker Bird'),(14,6,2,'Tin tức về xuất khẩu cà phê','Cập nhật tình hình xuất khẩu cà phê Việt Nam.','2025-06-26 14:35:21',NULL,'Active','Đào Huy Hoàng'),(15,3,1,'Nuôi ong lấy mật tự nhiên','Kinh nghiệm nuôi ong lấy mật hiệu quả.','2025-06-26 14:35:21',NULL,'Active','Chien Nguu'),(16,4,2,'Giá cả hạt điều tháng 6','Phân tích giá hạt điều và dự báo.','2025-06-26 14:35:21',NULL,'Active','Đô Nan Chăm'),(17,5,1,'Trồng rau mầm trong nhà','Hướng dẫn trồng rau mầm tại nhà đơn giản.','2025-06-26 14:35:21',NULL,'Active','Đào Huy Hoàng');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogcategory`
--

DROP TABLE IF EXISTS `blogcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogcategory` (
  `BlogCategoryID` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`BlogCategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogcategory`
--

LOCK TABLES `blogcategory` WRITE;
/*!40000 ALTER TABLE `blogcategory` DISABLE KEYS */;
INSERT INTO `blogcategory` VALUES (1,'Kinh nghiệm','Chia sẻ kinh nghiệm nông nghiệp'),(2,'Tin tức','Tin tức về nông sản');
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
  `thumbnail` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `detailcontent` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`blogdetailid`),
  KEY `BlogID` (`blogid`),
  CONSTRAINT `blogdetail_ibfk_1` FOREIGN KEY (`blogid`) REFERENCES `blog` (`BlogID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogdetail`
--

LOCK TABLES `blogdetail` WRITE;
/*!40000 ALTER TABLE `blogdetail` DISABLE KEYS */;
INSERT INTO `blogdetail` VALUES (1,1,'1.jpg','Chi tiết blog về giống lúa, các bước chọn lựa, ưu nhược điểm từng loại giống, kinh nghiệm thực tế đã áp dụng thành công.'),(2,2,'2.jpg','Phân tích thị trường nông sản tháng 6, dự đoán xu hướng giá cả, các yếu tố ảnh hưởng và lời khuyên cho nhà nông.'),(3,3,'3.jpg','Các bước lắp đặt hệ thống tưới thông minh...'),(4,4,'4.jpg','Ưu điểm khi sử dụng phân bón hữu cơ...'),(5,5,'5.jpg','Các bước bảo quản nông sản hiệu quả...'),(6,6,'7.jpg','Phân tích tiềm năng xuất khẩu lúa gạo...'),(7,7,'8.jpg','Giới thiệu các công nghệ mới ứng dụng...'),(8,8,'9.jpg','Dự báo giá cả rau củ quả trong mùa hè...'),(9,9,'10.jpg','Kinh nghiệm trồng cây ăn trái cho năng suất cao...'),(10,10,'11.jpg','Nhu cầu tiêu dùng và các lợi ích sức khỏe từ nông sản sạch...'),(11,11,'12.jpg','Hướng dẫn chi tiết nuôi gà thả vườn, chọn giống, và chăm sóc.'),(12,12,'13.jpg','Báo cáo chi tiết về thị trường chè, bao gồm các yếu tố ảnh hưởng.'),(13,13,'14.jpg','Các bước làm đất, chọn giống khoai lang, và thời gian thu hoạch.'),(14,14,'15.jpg','Tin tức về xuất khẩu cà phê, dự báo giá và nhu cầu quốc tế.'),(15,15,'16.jpg','Kinh nghiệm chọn vị trí nuôi ong, chăm sóc và lấy mật an toàn.'),(16,16,'17.jpg','Phân tích giá hạt điều, các yếu tố ảnh hưởng và thị trường.'),(17,17,'18.jpg','Hướng dẫn trồng rau mầm, dụng cụ cần thiết và cách chăm sóc.');
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
  PRIMARY KEY (`CartID`),
  KEY `UserID` (`UserID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
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
  `CategoryName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `Description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image_url` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`CategoryID`),
  UNIQUE KEY `unique_name` (`CategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Vegetables','Various vegetables such as cabbage, lettuce, carrots...','INACTIVE','b8963217-101e-44b3-acf3-edd7fa743d9b.loi_ich_cua_qua_thanh_long.webp'),(2,'Fruits','Fresh fruits like mango, dragon fruit, durian...','ACTIVE',NULL),(3,'Rice','ST25 rice, sticky rice, jasmine rice...','ACTIVE',NULL),(4,'Dried Goods','Dried vegetables and fruits for long-term storage','DELETED',NULL),(5,'Grains','Corn, beans, sesame, cashew nuts...','ACTIVE',NULL),(6,'Herbs','Natural herbs and spices used for flavoring, coloring, or preserving food, including items like ginger, turmeric, lemongrass, cinnamon, chili, and basil.','ACTIVE',NULL),(7,'Seeds','A variety of agricultural seeds used for planting or consumption, including sunflower seeds, sesame, flax, pumpkin seeds, and more.','ACTIVE',NULL),(8,'Oils','Various types of natural plant-based oils extracted from seeds, nuts, or fruits, commonly used for cooking, processing, or health purposes—such as soybean oil, sesame oil, peanut oil, and coconut oils.','ACTIVE',NULL),(9,'Spices','A variety of dried plant products used to season, flavor, or preserve food, including pepper, chili, cinnamon, star anise, turmeric, and cloves.','DELETED',NULL),(10,'DriedFruits','A selection of fruits that have been dried to remove moisture for preservation, including raisins, dates, apricots, figs, and dried mango.','DELETED',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoryweight`
--

DROP TABLE IF EXISTS `categoryweight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoryweight` (
  `categoryweightid` int NOT NULL AUTO_INCREMENT,
  `unit` varchar(20) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `categoryid` bigint NOT NULL,
  PRIMARY KEY (`categoryweightid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoryweight`
--

LOCK TABLES `categoryweight` WRITE;
/*!40000 ALTER TABLE `categoryweight` DISABLE KEYS */;
INSERT INTO `categoryweight` VALUES (1,'g',1000,2),(2,'g',200,2),(3,'kg',1,3),(4,'kg',1,2),(5,'g',150,2),(6,'g',10,2),(7,'g',2,2),(8,'g',3,2),(9,'g',4,2),(10,'l',39,8),(11,'g',200,1),(12,'',2,2),(13,'kg',1414,2),(14,'kg',4141,2),(15,'g',12,2),(16,'kg',22,2),(17,'g',1221,3);
/*!40000 ALTER TABLE `categoryweight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentblog`
--

DROP TABLE IF EXISTS `commentblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentblog` (
  `CommentBlogID` int NOT NULL AUTO_INCREMENT,
  `BlogID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `CommentText` text COLLATE utf8mb4_general_ci,
  `ParentCommentID` int DEFAULT NULL,
  `Like` int DEFAULT '0',
  `Report` int DEFAULT '0',
  `Status` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Rating` int DEFAULT '0',
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CommentBlogID`),
  KEY `BlogID` (`BlogID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `commentblog_ibfk_1` FOREIGN KEY (`BlogID`) REFERENCES `blog` (`BlogID`),
  CONSTRAINT `commentblog_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentblog`
--

LOCK TABLES `commentblog` WRITE;
/*!40000 ALTER TABLE `commentblog` DISABLE KEYS */;
INSERT INTO `commentblog` VALUES (1,1,2,'Bài viết rất hữu ích, cảm ơn tác giả!',NULL,0,0,'Active',5,'2025-06-26 14:35:21'),(2,2,1,'Thông tin thị trường rất sát thực tế.',NULL,0,0,'Active',4,'2025-06-26 14:35:21'),(3,11,4,'Bài viết rất thực tế, cảm ơn tác giả!',NULL,0,0,'Active',5,'2025-06-26 14:35:21'),(4,12,5,'Thông tin hữu ích, mong có thêm bài mới.',NULL,0,0,'Active',4,'2025-06-26 14:35:21');
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
  `ProductDetailID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `CommentText` text COLLATE utf8mb4_general_ci,
  `ParentCommentID` int DEFAULT NULL,
  `Like` int DEFAULT '0',
  `Report` int DEFAULT '0',
  `Status` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Rating` int DEFAULT '0',
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CommentProductID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `commentproduct_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`),
  CONSTRAINT `commentproduct_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentproduct`
--

LOCK TABLES `commentproduct` WRITE;
/*!40000 ALTER TABLE `commentproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentproduct` ENABLE KEYS */;
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
  `DeviceName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Status` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Description` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`DeviceID`),
  KEY `WarehouseID` (`WarehouseID`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `ProductID` int NOT NULL,
  `ImageURL` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '',
  PRIMARY KEY (`galleryid`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (40,57,'17b8573d-8f2e-47dc-8055-616927cf594d.jpg'),(42,5,'d1f315d6-9bb2-46c7-bd43-3a512004e885.jpg'),(43,5,'8b0114d7-15b4-49af-b27c-d6980bfda8ef.jpg'),(44,5,'21d5d427-f11c-43ea-afa6-df02b03db95c.webp'),(45,5,'9e193b97-211e-4ab3-ab00-0696e867d0fb.jpg');
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
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`),
  CONSTRAINT `inventory_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `address` varchar(255) DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `settoaddress` varchar(255) DEFAULT NULL,
  `userid` bigint NOT NULL,
  PRIMARY KEY (`addressbookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myaddressbook`
--

LOCK TABLES `myaddressbook` WRITE;
/*!40000 ALTER TABLE `myaddressbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `myaddressbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `OrderDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Status` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TotalAmount` decimal(15,2) DEFAULT NULL,
  `FullName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ordercode` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `discount_amount` decimal(15,2) DEFAULT NULL,
  `final_amount` decimal(15,2) NOT NULL,
  `voucher_code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  UNIQUE KEY `OrderCode` (`ordercode`),
  KEY `UserID` (`UserID`),
  KEY `FKnrduwglsych0g717gihrtflbu` (`voucher_id`),
  CONSTRAINT `FKnrduwglsych0g717gihrtflbu` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,'2025-06-11 10:15:00','DELIVERED',150000.00,'Nguyen Van A','a@example.com','0912345678','123 Le Loi, HCM','Giao buổi sáng','#ORD5823941',NULL,0.00,NULL,NULL),(2,2,'2025-06-11 11:00:00','DELIVERED',250000.00,'Tran Thi B','b@example.com','0923456789','456 Nguyen Hue, HCM','Không cay','#ORD3319058',NULL,0.00,NULL,NULL),(3,3,'2025-06-11 12:30:00','DELIVERED',350000.00,'Le Van C','c@example.com','0934567890','789 Tran Hung Dao, HCM','Giao giờ hành chính','#ORD2034876',NULL,0.00,NULL,NULL),(4,4,'2025-06-11 13:45:00','DELIVERED',50000.00,'Pham Thi D','d@example.com','0945678901','1010 Cach Mang Thang 8, HCM','Đặt nhầm','#ORD4901732',NULL,0.00,NULL,NULL),(5,5,'2025-06-11 15:00:00','DELIVERED',420000.00,'Vo Van E','e@example.com','0956789012','1212 Vo Van Tan, HCM','Giao chiều muộn','#ORD7649205',NULL,0.00,NULL,NULL),(6,3,'2025-06-13 14:30:00','PENDING',185000.00,'Le Thi C','c@example.com','0934567890','789 Tran Hung Dao, HCM','Chưa xác định','#ORD0193842',NULL,0.00,NULL,NULL),(7,4,'2025-06-13 16:45:00','REMOVED',320000.00,'Pham Van E','e@example.com','0945678901','321 Cach Mang Thang 8, HCM','Giao sau 6PM','#ORD8472109',NULL,0.00,NULL,NULL);
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
  `OrderID` int DEFAULT NULL,
  `ProductDetailID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Price` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`orderdetailid`),
  KEY `OrderID` (`OrderID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`),
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (2,1,1,2,240000.00),(3,2,1,1,120000.00),(4,3,2,3,240000.00),(5,4,3,5,225000.00),(6,5,2,1,80000.00),(7,6,3,2,90000.00);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
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
  `Status` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `ProductName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Status` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,2,'Fresh Mango','Sweet mangoes harvested from organic farms.','INACTIVE'),(6,2,'Dragon Fruit','Red-flesh dragon fruit, rich in antioxidants.','INACTIVE'),(7,3,'Jasmine Rice','Fragrant jasmine rice, long grain.','ACTIVE'),(8,3,'Brown Rice','Unpolished brown rice with high fiber.','ACTIVE'),(9,1,'Potatoes','Naturally grown.','OUT_OF_STOCK'),(12,8,'Thằng Hoàng FPT','','INACTIVE'),(13,1,'Gạo ST25','Gạo thơm đặc sản Sóc Trăng','INACTIVE'),(14,2,'Mít Thái','Mít ngọt, múi to','ACTIVE'),(15,3,'Cà rốt Đà Lạt','Tươi ngon, ngọt','ACTIVE'),(16,1,'Gạo Lứt','Tốt cho sức khỏe','ACTIVE'),(17,2,'Sầu riêng Ri6','Cơm vàng, hạt lép','INACTIVE'),(18,3,'Khoai lang Nhật','Ruột tím, dẻo','ACTIVE'),(19,1,'Gạo Nàng Hương','Thơm, dẻo mềm','ACTIVE'),(20,2,'Xoài Cát Chu','Ngọt thanh','ACTIVE'),(21,3,'Cải bó xôi','Xanh tươi, sạch','ACTIVE'),(22,1,'Gạo Tám Thái','Dẻo thơm','INACTIVE'),(23,2,'Dưa hấu ruột đỏ','Ngọt mát','ACTIVE'),(24,3,'Củ cải trắng','Trồng theo hướng hữu cơ','ACTIVE'),(25,1,'Gạo nếp cái hoa vàng','Dẻo mềm, dùng làm xôi','ACTIVE'),(26,2,'Cam sành','Ngọt, ít hạt','ACTIVE'),(27,3,'Rau muống','Tươi, không hóa chất','ACTIVE'),(28,1,'Gạo hữu cơ','Không hóa chất, tốt cho sức khỏe','ACTIVE'),(29,2,'Chuối tiêu','Chín cây tự nhiên','ACTIVE'),(30,3,'Bí đỏ','Ngọt, mềm','INACTIVE'),(31,1,'Gạo ST24','Đặc sản miền Tây','INACTIVE'),(32,2,'Táo đỏ','Ngọt, giòn','INACTIVE'),(33,3,'Cải thìa','Tươi, sạch','ACTIVE'),(34,1,'Gạo Hương Lài','Thơm dịu, mềm cơm','ACTIVE'),(35,2,'Ổi không hạt','Ngọt, giòn','ACTIVE'),(36,3,'Rau dền','Màu đẹp, nhiều dinh dưỡng','ACTIVE'),(37,1,'Gạo Thơm Jasmine','Dẻo, mềm, thích hợp cho cơm gia đình','ACTIVE'),(38,2,'Dưa lưới','Ngọt mát, trồng theo công nghệ cao','ACTIVE'),(39,3,'Rau cải xanh','Thu hoạch trong ngày, rau sạch','ACTIVE'),(40,1,'Gạo Tài Nguyên','Đặc sản miền Tây, cơm mềm','ACTIVE'),(41,2,'Lê Hàn Quốc','Giòn ngọt, nhập khẩu chính ngạch','ACTIVE'),(42,3,'Đậu bắp','Giòn, ít xơ, sạch','ACTIVE'),(43,1,'Gạo Bắc Hương','Thơm, mềm, ít nở','ACTIVE'),(44,2,'Nho đỏ Ninh Thuận','Không hạt, ngọt dịu','ACTIVE'),(45,3,'Cà chua bi','Tươi, dùng cho salad','ACTIVE'),(46,1,'Gạo Nhật','Dẻo, hạt tròn, dùng cho sushi','INACTIVE'),(47,2,'Mận hậu','Giòn, vị chua ngọt','ACTIVE'),(48,3,'Cải bẹ xanh','Phù hợp nấu canh, luộc','ACTIVE'),(49,1,'Gạo Campuchia','Hạt dài, thơm nhẹ','ACTIVE'),(50,2,'Thanh long ruột đỏ','Ngọt, đẹp mắt','ACTIVE'),(51,3,'Mồng tơi','Tốt cho tiêu hoá, nhiều chất nhầy','ACTIVE'),(53,2,'Quýt đường','Ngọt đậm, ít hạt','ACTIVE'),(56,2,'Đu đủ','Chín cây, ngọt mềm','INACTIVE'),(57,2,'Nhãn Lồng Hưng Yên','Thơm, ngon','ACTIVE'),(60,1,'sdsd','sđsds','ACTIVE'),(61,4,'sđsds','sđsds','ACTIVE'),(62,3,'sdsđs','ds','ACTIVE'),(63,1,'sđs','sđssd','ACTIVE'),(64,1,'fsffdfd','dffdfd','ACTIVE'),(65,8,'sfssds','sdfsdfsd','INACTIVE'),(66,7,'safsfsd','sdffsdfsdfsd','ACTIVE'),(67,2,'sdffsdsdf','sdfsdf','INACTIVE'),(68,1,'scsd','sccs','ACTIVE'),(69,3,'cds','sccs','OUT_OF_STOCK');
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
  `ManufactureDate` date DEFAULT NULL,
  `importedquantity` int DEFAULT NULL,
  `soldquantity` int DEFAULT NULL,
  PRIMARY KEY (`BatchID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `productbatch_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatch`
--

LOCK TABLES `productbatch` WRITE;
/*!40000 ALTER TABLE `productbatch` DISABLE KEYS */;
INSERT INTO `productbatch` VALUES (1,1,'2025-06-01',NULL,NULL),(2,2,'2025-06-03',NULL,NULL),(3,3,'2025-06-05',NULL,NULL),(4,1,'2024-06-24',100,0);
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
  `ProductID` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `Expiry` int DEFAULT NULL,
  `detailname` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `categoryweightid` int NOT NULL,
  PRIMARY KEY (`ProductDetailID`),
  KEY `ProductID` (`ProductID`),
  KEY `FKejqb8da7lrkwppkkyi56w6r84` (`categoryweightid`),
  CONSTRAINT `FKejqb8da7lrkwppkkyi56w6r84` FOREIGN KEY (`categoryweightid`) REFERENCES `categoryweight` (`categoryweightid`),
  CONSTRAINT `productdetail_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetail`
--

LOCK TABLES `productdetail` WRITE;
/*!40000 ALTER TABLE `productdetail` DISABLE KEYS */;
INSERT INTO `productdetail` VALUES (1,5,15000,1,'1000 g','ACTIVE',1),(2,6,15000,10,'200 g','ACTIVE',2),(3,8,20000,1,'1 kg','ACTIVE',3),(4,6,111111,1,'500 g','ACTIVE',1),(5,17,1111111,1,'500 g','ACTIVE',1),(6,23,22222,1,'1 kg','ACTIVE',4),(7,6,1212212,3,'1 kg','INACTIVE',4),(8,5,10000,1,'150 g','ACTIVE',5),(9,5,1,1,'10 g','ACTIVE',6),(10,5,2,2,'2 g','ACTIVE',7),(11,5,3,3,'3 g','ACTIVE',8),(12,5,4,4,'4.0g','ACTIVE',9),(13,12,10000,1,'39.0l','ACTIVE',10),(14,16,100,10,'200.0 g','ACTIVE',11),(15,9,10,10,'200 g','ACTIVE',11),(16,6,1,1,'2 ','ACTIVE',12),(17,5,1,1,'1414 kg','ACTIVE',13),(18,5,414,4,'4141 kg','INACTIVE',14),(19,5,12,12,'12 g','ACTIVE',15),(20,5,12,122,'22 kg','ACTIVE',16),(21,21,1,1,'1221 g','ACTIVE',17);
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
  `RoleName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `Status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'Active',
  `Description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin','ACTIVE','Manages the entire system and user permissions.'),(2,'Warehouse Staff','ACTIVE','Handles inventory, stock in/out, and warehouse tracking.'),(3,'Sales Staff','ACTIVE','Handles customer orders and sales activities.'),(4,'Delivery Staff','ACTIVE','Delivers goods between internal locations or branches.'),(5,'Customer','ACTIVE','Places orders and tracks their own order status.'),(6,'Guest','ACTIVE','Unauthenticated user with view-only access.'),(7,'Content Manager','ACTIVE','Manages product information, promotions, and site content.'),(8,'Support Staff','ACTIVE','Provides customer support and handles user inquiries, complaints, and issues related to orders, deliveries, or general system usage.'),(9,'Admin','Active',NULL),(10,'User','Active',NULL),(11,'Admin','Active',NULL),(12,'User','Active',NULL);
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
  `DeliveryAddress` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Status` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ShipmentID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `shipment_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldbyseller`
--

LOCK TABLES `soldbyseller` WRITE;
/*!40000 ALTER TABLE `soldbyseller` DISABLE KEYS */;
INSERT INTO `soldbyseller` VALUES (1,2,9);
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
  `note` tinytext COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`StockInID`),
  KEY `SupplierID` (`SupplierID`),
  KEY `WarehouseID` (`WarehouseID`),
  CONSTRAINT `FKqpedmvy18q37rkmabub44d6oj` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`supplierid`),
  CONSTRAINT `stockin_ibfk_2` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `StockInDetailID` int NOT NULL AUTO_INCREMENT,
  `StockInID` int NOT NULL,
  `ProductDetailID` int NOT NULL,
  `Quantity` int NOT NULL,
  `unitprice` int DEFAULT NULL,
  `BatchID` int DEFAULT NULL,
  PRIMARY KEY (`StockInDetailID`),
  KEY `StockInID` (`StockInID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  KEY `BatchID` (`BatchID`),
  CONSTRAINT `stockindetail_ibfk_1` FOREIGN KEY (`StockInID`) REFERENCES `stockin` (`StockInID`),
  CONSTRAINT `stockindetail_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`),
  CONSTRAINT `stockindetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `OrderID` int DEFAULT NULL,
  `stockoutdate` date DEFAULT NULL,
  `note` tinytext COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`StockOutID`),
  KEY `WarehouseID` (`WarehouseID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `stockout_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`),
  CONSTRAINT `stockout_ibfk_2` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `ProductDetailID` int NOT NULL,
  `Quantity` int NOT NULL,
  `BatchID` int DEFAULT NULL,
  `orderdetailid` bigint NOT NULL,
  PRIMARY KEY (`StockOutDetailID`),
  KEY `StockOutID` (`StockOutID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  KEY `BatchID` (`BatchID`),
  KEY `FKd8fm5wpab7si40c2f6eyutqvn` (`orderdetailid`),
  CONSTRAINT `FKd8fm5wpab7si40c2f6eyutqvn` FOREIGN KEY (`orderdetailid`) REFERENCES `orderdetail` (`orderdetailid`),
  CONSTRAINT `stockoutdetail_ibfk_1` FOREIGN KEY (`StockOutID`) REFERENCES `stockout` (`StockOutID`),
  CONSTRAINT `stockoutdetail_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`),
  CONSTRAINT `stockoutdetail_ibfk_3` FOREIGN KEY (`BatchID`) REFERENCES `productbatch` (`BatchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `supplierid` int NOT NULL AUTO_INCREMENT,
  `contactinfo` varchar(100) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL,
  `suppliername` varchar(255) NOT NULL,
  PRIMARY KEY (`supplierid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'hoangngu@gmail.com','loi_ich_cua_qua_thanh_long.webp','Hoàng Ngu');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `updateprofilehistory`
--

DROP TABLE IF EXISTS `updateprofilehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `updateprofilehistory` (
  `HistoryID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `UpdateInfo` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `UpdateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`HistoryID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `updateprofilehistory_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `updateprofilehistory`
--

LOCK TABLES `updateprofilehistory` WRITE;
/*!40000 ALTER TABLE `updateprofilehistory` DISABLE KEYS */;
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
  `RoleID` int NOT NULL,
  `UserName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `FullName` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Image` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Phone` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Gender` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `CreatedAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `otp` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `LastTimeUpdatePass` datetime DEFAULT NULL,
  `googleid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `statusgg` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `RoleID` (`RoleID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'warehouse_tom','Tom Carter','tom.jpg','hashed_pw1','tom@company.com','0911000001','Warehouse Zone A','Male','Active','1985-11-01','2025-06-09 09:13:38','836271','2025-06-09 09:13:38',NULL,NULL),(2,3,'sales_linda','Linda Green','linda.jpg','hashed_pw2','linda@company.com','0911000002','Sales Office 2','Female','Active','1991-08-20','2025-06-09 09:13:38','274918','2025-06-09 09:13:38',NULL,NULL),(3,4,'delivery_jake','Jake White','jake.jpg','hashed_pw3','jake@delivery.com','0911000003','Delivery Hub B','Male','Active','1993-04-10','2025-06-09 09:13:38','591027','2025-06-09 09:13:38',NULL,NULL),(4,5,'customer_sara','Sara Smith','sara.jpg','hashed_pw4','sara@gmail.com','0911000004','789 Main St','Female','Active','1998-12-30','2025-06-09 09:13:38','109482','2025-06-09 09:13:38',NULL,NULL),(5,6,'Guest User','Guest User',NULL,'hashed_pw5','guest@anonymous.com','0911000005','N/A','Male','Active','2000-01-01','2025-06-09 09:13:38','000000','2025-06-09 02:22:20',NULL,NULL),(6,7,'content_ann','Ann Writer','ann.jpg','hashed_pw6','ann@content.com','0911000006','Content Dept.','Female','Active','1990-06-15','2025-06-09 09:13:38','847362','2025-06-09 09:13:38',NULL,NULL),(7,8,'support_mike','Mike Support','mike.jpg','hashed_pw7','mike@support.com','0911000007','Support Center','Male','Active','1987-03-05','2025-06-09 09:13:38','294753','2025-06-09 09:13:38',NULL,NULL),(8,1,'admin','admin',NULL,'admin123','admin102@gmail.com','102102102','Street_xxx','Male','Active',NULL,'2025-06-09 02:15:02','102102','2025-06-09 02:15:02',NULL,NULL),(9,5,'user01','user123',NULL,'user123','123@gmail.com','0912345682','Mountain','Other','Ban',NULL,'2025-06-09 02:23:54','123123','2025-06-09 02:35:32',NULL,NULL),(11,6,'Hoang Ngu','Hoang Ngu',NULL,'123456','chiennguyena8k22@gmail.com','0379366518','Ba Vì','Male','Active','2025-05-29','2025-06-24 14:54:28','0','2025-06-24 23:27:39',NULL,NULL);
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
  `voucher_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `discount_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount_value` decimal(15,2) NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `used_quantity` bigint DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `islocked` bit(1) NOT NULL,
  `min_order_amount` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'hoangngu123456','2025-06-24 14:03:00','2025-06-24 16:03:00','AMOUNT',50000.00,10,0,'ACTIVE',_binary '\0',200000.00),(2,'hoangngu1234567','2025-05-28 14:12:00','2025-07-16 14:12:00','AMOUNT',55555.00,10,0,'ACTIVE',_binary '\0',200000.00);
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `WarehouseID` int NOT NULL AUTO_INCREMENT,
  `WarehouseName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`WarehouseID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'Central Warehouse A','District 9, Ho Chi Minh City','Main storage for fruits, rice, and dry goods.');
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
  `ProductDetailID` int DEFAULT NULL,
  PRIMARY KEY (`WishlistID`),
  KEY `UserID` (`UserID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `wishlist_ibfk_2` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
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

-- Dump completed on 2025-07-03  2:45:16
