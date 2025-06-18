-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: managerwarehouse
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
  `AdjustmentID` int(11) NOT NULL AUTO_INCREMENT,
  `WarehouseID` int(11) NOT NULL,
  `DeviceID` int(11) DEFAULT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
  `AdjustDate` datetime DEFAULT current_timestamp(),
  `Reason` text DEFAULT NULL,
  PRIMARY KEY (`AdjustmentID`),
  KEY `WarehouseID` (`WarehouseID`),
  KEY `DeviceID` (`DeviceID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `adjustment_ibfk_1` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`),
  CONSTRAINT `adjustment_ibfk_2` FOREIGN KEY (`DeviceID`) REFERENCES `device` (`DeviceID`),
  CONSTRAINT `adjustment_ibfk_3` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustment`
--

LOCK TABLES `adjustment` WRITE;
/*!40000 ALTER TABLE `adjustment` DISABLE KEYS */;
/*!40000 ALTER TABLE `adjustment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `AuthorizationID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleID` int(11) DEFAULT NULL,
  `UrlAuthorized` varchar(255) DEFAULT NULL,
  `FeatureDescription` varchar(255) DEFAULT NULL,
  `StatusSetting` varchar(20) DEFAULT NULL,
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
  `BlogID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `BlogCategoryID` int(11) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Content` text DEFAULT NULL,
  `CreatedAt` datetime DEFAULT current_timestamp(),
  `BlogDateUpdate` datetime DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BlogID`),
  KEY `UserID` (`UserID`),
  KEY `BlogCategoryID` (`BlogCategoryID`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`BlogCategoryID`) REFERENCES `blogcategory` (`BlogCategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogcategory`
--

DROP TABLE IF EXISTS `blogcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogcategory` (
  `BlogCategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(100) DEFAULT NULL,
  `Description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`BlogCategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogcategory`
--

LOCK TABLES `blogcategory` WRITE;
/*!40000 ALTER TABLE `blogcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `blogcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogdetail`
--

DROP TABLE IF EXISTS `blogdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogdetail` (
  `BlogDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `BlogID` int(11) DEFAULT NULL,
  `Thumbnail` varchar(255) DEFAULT NULL,
  `DetailContent` text DEFAULT NULL,
  PRIMARY KEY (`BlogDetailID`),
  KEY `BlogID` (`BlogID`),
  CONSTRAINT `blogdetail_ibfk_1` FOREIGN KEY (`BlogID`) REFERENCES `blog` (`BlogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogdetail`
--

LOCK TABLES `blogdetail` WRITE;
/*!40000 ALTER TABLE `blogdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `blogdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `CartID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
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
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(100) NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CategoryID`),
  UNIQUE KEY `unique_name` (`CategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Vegetables','Various vegetables such as cabbage, lettuce, carrots...','INACTIVE'),(2,'Fruits','Fresh fruits like mango, dragon fruit, durian...','ACTIVE'),(3,'Rice','ST25 rice, sticky rice, jasmine rice...','ACTIVE'),(4,'Dried Goods','Dried vegetables and fruits for long-term storage','DELETED'),(5,'Grains','Corn, beans, sesame, cashew nuts...','ACTIVE'),(6,'Herbs','Natural herbs and spices used for flavoring, coloring, or preserving food, including items like ginger, turmeric, lemongrass, cinnamon, chili, and basil.','ACTIVE'),(7,'Seeds','A variety of agricultural seeds used for planting or consumption, including sunflower seeds, sesame, flax, pumpkin seeds, and more.','ACTIVE'),(8,'Oils','Various types of natural plant-based oils extracted from seeds, nuts, or fruits, commonly used for cooking, processing, or health purposes—such as soybean oil, sesame oil, peanut oil, and coconut oils.','ACTIVE'),(9,'Spices','A variety of dried plant products used to season, flavor, or preserve food, including pepper, chili, cinnamon, star anise, turmeric, and cloves.','DELETED'),(10,'DriedFruits','A selection of fruits that have been dried to remove moisture for preservation, including raisins, dates, apricots, figs, and dried mango.','DELETED');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentblog`
--

DROP TABLE IF EXISTS `commentblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentblog` (
  `CommentBlogID` int(11) NOT NULL AUTO_INCREMENT,
  `BlogID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `CommentText` text DEFAULT NULL,
  `ParentCommentID` int(11) DEFAULT NULL,
  `Like` int(11) DEFAULT 0,
  `Report` int(11) DEFAULT 0,
  `Status` varchar(50) DEFAULT NULL,
  `Rating` int(11) DEFAULT 0,
  `CreatedAt` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`CommentBlogID`),
  KEY `BlogID` (`BlogID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `commentblog_ibfk_1` FOREIGN KEY (`BlogID`) REFERENCES `blog` (`BlogID`),
  CONSTRAINT `commentblog_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentblog`
--

LOCK TABLES `commentblog` WRITE;
/*!40000 ALTER TABLE `commentblog` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentproduct`
--

DROP TABLE IF EXISTS `commentproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentproduct` (
  `CommentProductID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDetailID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `CommentText` text DEFAULT NULL,
  `ParentCommentID` int(11) DEFAULT NULL,
  `Like` int(11) DEFAULT 0,
  `Report` int(11) DEFAULT 0,
  `Status` varchar(50) DEFAULT NULL,
  `Rating` int(11) DEFAULT 0,
  `CreatedAt` datetime DEFAULT current_timestamp(),
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
  `DeviceID` int(11) NOT NULL AUTO_INCREMENT,
  `WarehouseID` int(11) NOT NULL,
  `DeviceName` varchar(255) NOT NULL,
  `Status` varchar(100) DEFAULT NULL,
  `Description` text DEFAULT NULL,
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
  `GalleryID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `ImageURL` varchar(255) DEFAULT '',
  PRIMARY KEY (`GalleryID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (1,9,'4013e28a-992a-4945-ac23-6105c9ffb789.1.jpg'),(2,9,'be37fba7-a989-43d3-9dc4-0fac2aa963e2.1.jpg'),(3,9,'58697046-c809-4d36-91a8-1f73ab9edc12.2.jpg'),(4,9,'deead1df-e5f7-4429-93c4-176cbc92a58b.3.jpg'),(5,9,'64d5616e-650d-482f-ba98-42d68a0afccf.4.jpg');
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `InventoryID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDetailID` int(11) DEFAULT NULL,
  `WarehouseID` int(11) DEFAULT NULL,
  `BatchID` int(11) DEFAULT NULL,
  `QuantityInStock` int(11) DEFAULT 0,
  `LastUpdated` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `OrderDate` datetime DEFAULT current_timestamp(),
  `Status` varchar(50) DEFAULT NULL,
  `TotalAmount` decimal(15,2) DEFAULT NULL,
  `FullName` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Note` text DEFAULT NULL,
  `OrderCode` varchar(50) NOT NULL,
  PRIMARY KEY (`OrderID`),
  UNIQUE KEY `OrderCode` (`OrderCode`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,'2025-06-11 10:15:00','DELIVERED',150000.00,'Nguyen Van A','a@example.com','0912345678','123 Le Loi, HCM','Giao buổi sáng','#ORD5823941'),(2,2,'2025-06-11 11:00:00','DELIVERED',250000.00,'Tran Thi B','b@example.com','0923456789','456 Nguyen Hue, HCM','Không cay','#ORD3319058'),(3,3,'2025-06-11 12:30:00','DELIVERED',350000.00,'Le Van C','c@example.com','0934567890','789 Tran Hung Dao, HCM','Giao giờ hành chính','#ORD2034876'),(4,4,'2025-06-11 13:45:00','DELIVERED',50000.00,'Pham Thi D','d@example.com','0945678901','1010 Cach Mang Thang 8, HCM','Đặt nhầm','#ORD4901732'),(5,5,'2025-06-11 15:00:00','DELIVERED',420000.00,'Vo Van E','e@example.com','0956789012','1212 Vo Van Tan, HCM','Giao chiều muộn','#ORD7649205'),(6,3,'2025-06-13 14:30:00','PENDING',185000.00,'Le Thi C','c@example.com','0934567890','789 Tran Hung Dao, HCM','Chưa xác định','#ORD0193842'),(7,4,'2025-06-13 16:45:00','REMOVED',320000.00,'Pham Van E','e@example.com','0945678901','321 Cach Mang Thang 8, HCM','Giao sau 6PM','#ORD8472109');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `OrderDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) DEFAULT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Price` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`OrderDetailID`),
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
  `PaymentID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) DEFAULT NULL,
  `PaymentDate` datetime DEFAULT current_timestamp(),
  `Amount` decimal(15,2) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
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
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryID` int(11) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,2,'Fresh Mango','Sweet mangoes harvested from organic farms.','ACTIVE'),(6,2,'Dragon Fruit','Red-flesh dragon fruit, rich in antioxidants.','ACTIVE'),(7,3,'Jasmine Rice','Fragrant jasmine rice, long grain.','ACTIVE'),(8,3,'Brown Rice','Unpolished brown rice with high fiber.','ACTIVE'),(9,1,'Potatoes','Naturally grown.','OUT_OF_STOCK');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productbatch`
--

DROP TABLE IF EXISTS `productbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productbatch` (
  `BatchID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductDetailID` int(11) DEFAULT NULL,
  `ManufactureDate` date DEFAULT NULL,
  `ProductDate` date DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`BatchID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  CONSTRAINT `productbatch_ibfk_1` FOREIGN KEY (`ProductDetailID`) REFERENCES `productdetail` (`ProductDetailID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productbatch`
--

LOCK TABLES `productbatch` WRITE;
/*!40000 ALTER TABLE `productbatch` DISABLE KEYS */;
INSERT INTO `productbatch` VALUES (1,1,'2025-06-01','2025-06-10',200),(2,2,'2025-06-03','2025-06-07',150),(3,3,'2025-06-05','2025-06-20',300);
/*!40000 ALTER TABLE `productbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productdetail`
--

DROP TABLE IF EXISTS `productdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productdetail` (
  `ProductDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) DEFAULT NULL,
  `Price` decimal(15,2) DEFAULT NULL,
  `Weight` decimal(10,2) DEFAULT NULL,
  `Expiry` int(11) DEFAULT NULL,
  `DetailName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ProductDetailID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `productdetail_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetail`
--

LOCK TABLES `productdetail` WRITE;
/*!40000 ALTER TABLE `productdetail` DISABLE KEYS */;
INSERT INTO `productdetail` VALUES (1,7,120000.00,5.00,12,'Gạo ST25 túi 5kg'),(2,5,80000.00,2.00,5,'Xoài cát Hòa Lộc 2kg'),(3,6,45000.00,1.00,10,'Khoai lang Nhật 1kg');
/*!40000 ALTER TABLE `productdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(100) NOT NULL,
  `Status` varchar(20) DEFAULT 'Active',
  `Description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin','ACTIVE','Manages the entire system and user permissions.'),(2,'Warehouse Staff','ACTIVE','Handles inventory, stock in/out, and warehouse tracking.'),(3,'Sales Staff','ACTIVE','Handles customer orders and sales activities.'),(4,'Delivery Staff','ACTIVE','Delivers goods between internal locations or branches.'),(5,'Customer','ACTIVE','Places orders and tracks their own order status.'),(6,'Guest','ACTIVE','Unauthenticated user with view-only access.'),(7,'Content Manager','ACTIVE','Manages product information, promotions, and site content.'),(8,'Support Staff','ACTIVE','Provides customer support and handles user inquiries, complaints, and issues related to orders, deliveries, or general system usage.');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `ShipmentID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) DEFAULT NULL,
  `ShipmentDate` datetime DEFAULT NULL,
  `DeliveryAddress` varchar(255) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
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
-- Table structure for table `stockin`
--

DROP TABLE IF EXISTS `stockin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockin` (
  `StockInID` int(11) NOT NULL AUTO_INCREMENT,
  `SupplierID` int(11) NOT NULL,
  `WarehouseID` int(11) NOT NULL,
  `StockInDate` datetime DEFAULT current_timestamp(),
  `Note` text DEFAULT NULL,
  PRIMARY KEY (`StockInID`),
  KEY `SupplierID` (`SupplierID`),
  KEY `WarehouseID` (`WarehouseID`),
  CONSTRAINT `stockin_ibfk_1` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`id`),
  CONSTRAINT `stockin_ibfk_2` FOREIGN KEY (`WarehouseID`) REFERENCES `warehouse` (`WarehouseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  `StockInDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `StockInID` int(11) NOT NULL,
  `ProductDetailID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `UnitPrice` decimal(10,2) DEFAULT NULL,
  `BatchID` int(11) DEFAULT NULL,
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
  `StockOutID` int(11) NOT NULL AUTO_INCREMENT,
  `WarehouseID` int(11) NOT NULL,
  `OrderID` int(11) DEFAULT NULL,
  `StockOutDate` datetime DEFAULT current_timestamp(),
  `Note` text DEFAULT NULL,
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
  `StockOutDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `StockOutID` int(11) NOT NULL,
  `ProductDetailID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `BatchID` int(11) DEFAULT NULL,
  PRIMARY KEY (`StockOutDetailID`),
  KEY `StockOutID` (`StockOutID`),
  KEY `ProductDetailID` (`ProductDetailID`),
  KEY `BatchID` (`BatchID`),
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
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(255) DEFAULT NULL,
  `contact_info` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `updateprofilehistory`
--

DROP TABLE IF EXISTS `updateprofilehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `updateprofilehistory` (
  `HistoryID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `UpdateInfo` varchar(255) DEFAULT NULL,
  `UpdateTime` datetime DEFAULT current_timestamp(),
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
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleID` int(11) NOT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `FullName` varchar(255) DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `CreatedAt` datetime DEFAULT current_timestamp(),
  `OTP` varchar(255) NOT NULL,
  `LastTimeUpdatePass` datetime DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `RoleID` (`RoleID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'warehouse_tom','Tom Carter','tom.jpg','hashed_pw1','tom@company.com','0911000001','Warehouse Zone A','Male','Active','1985-11-01','2025-06-09 09:13:38','836271','2025-06-09 09:13:38'),(2,3,'sales_linda','Linda Green','linda.jpg','hashed_pw2','linda@company.com','0911000002','Sales Office 2','Female','Active','1991-08-20','2025-06-09 09:13:38','274918','2025-06-09 09:13:38'),(3,4,'delivery_jake','Jake White','jake.jpg','hashed_pw3','jake@delivery.com','0911000003','Delivery Hub B','Male','Active','1993-04-10','2025-06-09 09:13:38','591027','2025-06-09 09:13:38'),(4,5,'customer_sara','Sara Smith','sara.jpg','hashed_pw4','sara@gmail.com','0911000004','789 Main St','Female','Active','1998-12-30','2025-06-09 09:13:38','109482','2025-06-09 09:13:38'),(5,6,'Guest User','Guest User',NULL,'hashed_pw5','guest@anonymous.com','0911000005','N/A','Male','Active','2000-01-01','2025-06-09 09:13:38','000000','2025-06-09 02:22:20'),(6,7,'content_ann','Ann Writer','ann.jpg','hashed_pw6','ann@content.com','0911000006','Content Dept.','Female','Active','1990-06-15','2025-06-09 09:13:38','847362','2025-06-09 09:13:38'),(7,8,'support_mike','Mike Support','mike.jpg','hashed_pw7','mike@support.com','0911000007','Support Center','Male','Active','1987-03-05','2025-06-09 09:13:38','294753','2025-06-09 09:13:38'),(8,1,'admin','admin',NULL,'admin123','admin102@gmail.com','102102102','Street_xxx','Male','Active',NULL,'2025-06-09 02:15:02','102102','2025-06-09 02:15:02'),(9,5,'user01','user123',NULL,'user123','123@gmail.com','0912345682','Mountain','Other','Ban',NULL,'2025-06-09 02:23:54','123123','2025-06-09 02:35:32');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `voucher_code` varchar(50) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `discount_type` varchar(50) NOT NULL,
  `discount_value` decimal(15,2) NOT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `used_quantity` bigint(20) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `WarehouseID` int(11) NOT NULL AUTO_INCREMENT,
  `WarehouseName` varchar(255) NOT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Description` text DEFAULT NULL,
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
  `WishlistID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `ProductDetailID` int(11) DEFAULT NULL,
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

-- Dump completed on 2025-06-18  0:12:49
