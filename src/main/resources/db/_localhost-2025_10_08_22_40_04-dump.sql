-- MySQL dump 10.13  Distrib 9.3.0, for macos15.2 (arm64)
--
-- Host: 127.0.0.1    Database: shopping_cart
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart_items`
--
CREATE DATABASE IF NOT EXISTS schema_name;

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `cart_id` bigint unsigned NOT NULL,
  `product_id` bigint unsigned NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `unit_price` decimal(10,2) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_cartitem_cart_product` (`cart_id`,`product_id`),
  KEY `idx_cart_items_product` (`product_id`),
  CONSTRAINT `fk_cartitems_cart` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cartitems_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (38,2,1,4,799.00,'2025-10-08 08:57:31','2025-10-08 17:11:25'),(39,2,3,3,249.00,'2025-10-08 13:37:06','2025-10-08 21:37:14'),(44,1,3,5,249.00,'2025-10-08 14:10:24','2025-10-08 22:10:30');
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_carts_user` (`user_id`),
  UNIQUE KEY `UK_64t7ox312pqal3p7fg9o503c2` (`user_id`),
  CONSTRAINT `fk_carts_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,3,'2025-10-05 15:43:14','2025-10-06 18:27:15',_binary ''),(2,2,'2025-10-08 04:26:05','2025-10-08 04:26:05',_binary '');
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `order_id` bigint unsigned NOT NULL COMMENT 'Linked order',
  `product_id` bigint unsigned DEFAULT NULL COMMENT 'Linked product',
  `unit_price` decimal(10,2) NOT NULL COMMENT 'Unit price at order time',
  `quantity` int NOT NULL COMMENT 'Quantity ordered',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last updated time',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`),
  CONSTRAINT `fk_orderitem_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_orderitem_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,24,4,349.00,1,'2025-10-07 18:00:51','2025-10-07 18:00:51'),(2,24,3,249.00,1,'2025-10-07 18:00:51','2025-10-07 18:00:51'),(3,24,2,749.00,1,'2025-10-07 18:00:51','2025-10-07 18:00:51'),(4,25,3,249.00,1,'2025-10-07 18:01:29','2025-10-07 18:01:29'),(5,26,4,349.00,1,'2025-10-08 04:26:21','2025-10-08 04:26:21'),(6,27,5,849.00,1,'2025-10-08 04:27:44','2025-10-08 04:27:44'),(7,27,4,349.00,1,'2025-10-08 04:27:44','2025-10-08 04:27:44'),(8,27,3,249.00,1,'2025-10-08 04:27:44','2025-10-08 04:27:44'),(9,31,3,249.00,1,'2025-10-08 06:47:42','2025-10-08 06:47:42'),(10,32,4,349.00,1,'2025-10-08 06:51:10','2025-10-08 06:51:10'),(11,33,5,849.00,1,'2025-10-08 06:52:32','2025-10-08 06:52:32'),(12,34,5,849.00,1,'2025-10-08 06:53:17','2025-10-08 06:53:17'),(13,34,4,349.00,1,'2025-10-08 06:53:17','2025-10-08 06:53:17'),(14,34,3,249.00,1,'2025-10-08 06:53:17','2025-10-08 06:53:17'),(15,34,2,749.00,4,'2025-10-08 06:53:17','2025-10-08 06:53:17'),(16,35,1,799.00,1,'2025-10-08 07:57:54','2025-10-08 07:57:54'),(17,35,2,749.00,2,'2025-10-08 07:57:54','2025-10-08 07:57:54'),(18,35,3,249.00,1,'2025-10-08 07:57:54','2025-10-08 07:57:54'),(19,35,4,349.00,1,'2025-10-08 07:57:54','2025-10-08 07:57:54'),(20,35,5,849.00,1,'2025-10-08 07:57:54','2025-10-08 07:57:54'),(21,36,5,849.00,4,'2025-10-08 08:53:03','2025-10-08 08:53:03'),(22,36,4,349.00,4,'2025-10-08 08:53:03','2025-10-08 08:53:03'),(23,36,3,249.00,1,'2025-10-08 08:53:03','2025-10-08 08:53:03'),(24,37,1,799.00,5,'2025-10-08 13:52:01','2025-10-08 13:52:01'),(25,38,4,349.00,3,'2025-10-08 14:07:14','2025-10-08 14:07:14'),(26,38,3,249.00,20,'2025-10-08 14:07:14','2025-10-08 14:07:14'),(27,39,3,249.00,1,'2025-10-08 14:07:55','2025-10-08 14:07:55'),(28,39,2,749.00,1,'2025-10-08 14:07:55','2025-10-08 14:07:55');
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_number` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `status` enum('CANCELLED','DELIVERED','PAID','PENDING','SHIPPED') COLLATE utf8mb4_unicode_ci NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `total_quantity` int NOT NULL DEFAULT '0',
  `payment_method` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `recipient_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `recipient_phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shipping_addr` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cancelled_at` datetime(6) DEFAULT NULL,
  `delivered_at` datetime(6) DEFAULT NULL,
  `notes` text COLLATE utf8mb4_unicode_ci,
  `paid_at` datetime(6) DEFAULT NULL,
  `shipped_at` datetime(6) DEFAULT NULL,
  `shipping_address` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_orders_order_number` (`order_number`),
  KEY `idx_orders_user` (`user_id`),
  KEY `idx_orders_status_created` (`status`,`created_at`),
  CONSTRAINT `fk_orders_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'ORD20241001001',3,'PAID',1297.00,3,'ONLINE','陈亚骐','13800138000','成都市高新区科园南街 88 号','2025-10-05 15:43:14','2025-10-07 02:56:21',NULL,NULL,NULL,NULL,NULL,NULL),(2,'陈亚骐1759843455',3,'PENDING',20474.00,13,'test','testName','15879091005','test address','2025-10-07 13:24:15','2025-10-07 13:24:15',NULL,NULL,'test notes',NULL,NULL,NULL),(3,'陈亚骐1759843705',3,'PENDING',1198.00,2,'test','testName','15879091005','test address','2025-10-07 13:28:25','2025-10-07 13:28:25',NULL,NULL,'test notes',NULL,NULL,NULL),(9,'陈亚骐1759844171',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 13:36:22','2025-10-07 13:36:22',NULL,NULL,'test notes',NULL,NULL,NULL),(10,'陈亚骐1759844314',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 13:38:35','2025-10-07 13:38:35',NULL,NULL,'test notes',NULL,NULL,NULL),(11,'陈亚骐1759844325',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 13:39:54','2025-10-07 13:39:54',NULL,NULL,'test notes',NULL,NULL,NULL),(12,'陈亚骐1759844403',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 13:40:54','2025-10-07 13:40:55',NULL,NULL,'test notes',NULL,NULL,NULL),(13,'陈亚骐1759844951',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 13:49:12','2025-10-07 13:49:12',NULL,NULL,'test notes',NULL,NULL,NULL),(14,'陈亚骐1759845024',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 13:50:41','2025-10-07 13:50:41',NULL,NULL,'test notes',NULL,NULL,NULL),(21,'陈亚骐1759847238',3,'PENDING',4794.00,6,'test','testName','15879091005','test address','2025-10-07 14:27:19','2025-10-07 14:27:19',NULL,NULL,'test notes',NULL,NULL,NULL),(22,'陈亚骐1759858045',3,'PENDING',7289.00,11,'Online','wqe','15879091005','324234','2025-10-07 17:27:26','2025-10-07 17:27:26',NULL,NULL,'',NULL,NULL,NULL),(24,'陈亚骐1759860051',3,'PENDING',1347.00,3,'WeChat Pay','亚骐 陈','15879091005','123123','2025-10-07 18:00:51','2025-10-07 18:00:51',NULL,NULL,'2213123',NULL,NULL,NULL),(25,'陈亚骐1759860088',3,'PAID',249.00,1,'Alipay','wqe','15879091005','1231','2025-10-07 18:01:29','2025-10-07 18:01:29',NULL,NULL,'21323131312','2025-10-08 13:56:00.450724',NULL,NULL),(26,'admin1759897581',2,'PAID',349.00,1,'Cash on Delivery','admin','15879091005','1232132','2025-10-08 04:26:21','2025-10-08 04:26:21',NULL,NULL,'213213123123123123','2025-10-08 07:33:11.778826',NULL,NULL),(27,'admin1759897663',2,'PAID',1447.00,3,'WeChat Pay','亚骐 陈','15879091005','1`1qweww','2025-10-08 04:27:44','2025-10-08 04:27:44',NULL,NULL,'weqewq','2025-10-08 07:33:22.535407',NULL,NULL),(31,'admin1759906062',2,'PAID',249.00,1,'Online','wqe','15879091005','213123','2025-10-08 06:47:42','2025-10-08 06:47:42',NULL,NULL,'231123','2025-10-08 07:33:15.165777',NULL,NULL),(32,'admin1759906270',2,'PAID',349.00,1,'Online','wqe','15879091005','fffff','2025-10-08 06:51:10','2025-10-08 06:51:10',NULL,NULL,'131232','2025-10-08 07:56:11.831500',NULL,NULL),(33,'admin1759906351',2,'PENDING',849.00,1,'Online','亚骐 陈','15879091005','1111','2025-10-08 06:52:32','2025-10-08 06:52:32',NULL,NULL,'',NULL,NULL,NULL),(34,'admin1759906397',2,'PAID',4443.00,7,'Online','亚骐 陈','15879091005','2222','2025-10-08 06:53:17','2025-10-08 06:53:17',NULL,NULL,'','2025-10-08 06:53:23.726053',NULL,NULL),(35,'admin1759910273',2,'PENDING',3744.00,6,'Alipay','亚骐 陈','13354556666','成华区成都理工大学','2025-10-08 07:57:54','2025-10-08 07:57:54',NULL,NULL,'',NULL,NULL,NULL),(36,'admin1759913583',2,'PAID',5041.00,9,'Online','3223','15879091005','test stock','2025-10-08 08:53:03','2025-10-08 08:53:03',NULL,NULL,'','2025-10-08 08:53:10.864720',NULL,NULL),(37,'陈亚骐1759931520',3,'PAID',3995.00,5,'Online','123','15879000000','测试cart增减item数量功能','2025-10-08 13:52:01','2025-10-08 13:52:01',NULL,NULL,'','2025-10-08 13:52:05.220977',NULL,NULL),(38,'陈亚骐1759932433',3,'PAID',6027.00,23,'Online','Trump','15879091005','123123123123','2025-10-08 14:07:14','2025-10-08 14:07:14',NULL,NULL,'','2025-10-08 14:07:15.700412',NULL,NULL),(39,'陈亚骐1759932475',3,'PAID',998.00,2,'Online','21312','13333333333','123213','2025-10-08 14:07:55','2025-10-08 14:07:55',NULL,NULL,'213123','2025-10-08 14:08:01.018623',NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `price` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `image_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL,
  `brand` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `review_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_products_visible` (`visible`),
  KEY `idx_products_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'iPhone 15','Apple smartphone',799.00,0,'../../public/the_cape_of_good_hope.JPG',1,'2025-10-05 15:43:14','2025-10-08 21:52:00',_binary '',NULL,NULL,NULL,NULL),(2,'Galaxy S24','Samsung smartphone',749.00,44,'../../public/the_cape_of_good_hope.JPG',1,'2025-10-05 15:43:14','2025-10-08 22:07:55',_binary '',NULL,NULL,NULL,NULL),(3,'AirPods Pro 2','Wireless earbuds',249.00,98,'../../public/the_cape_of_good_hope.JPG',1,'2025-10-05 15:43:14','2025-10-08 22:07:55',_binary '',NULL,NULL,NULL,NULL),(4,'AirPods Pro 3','Wireless earbuds3',349.00,113,'../../public/the_cape_of_good_hope.JPG',1,'2025-10-05 15:43:14','2025-10-08 22:07:13',_binary '',NULL,NULL,NULL,NULL),(5,'Galaxy S25','Samsung smartphone',849.00,116,'../../public/the_cape_of_good_hope.JPG',1,'2025-10-05 15:43:14','2025-10-08 16:53:03',_binary '',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'USER',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` bit(1) NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_users_username` (`username`),
  UNIQUE KEY `uk_users_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice','123456','Alice Chen','alice@example.com','USER','2025-10-05 15:43:14','2025-10-05 17:04:23',_binary '\0',NULL,NULL,NULL,NULL),(2,'admin','123456','Site Admin','admin@example.com','ADMIN','2025-10-05 15:43:14','2025-10-05 17:04:23',_binary '\0',NULL,NULL,NULL,NULL),(3,'陈亚骐','123456',NULL,'yaqichan@foxmail.com','USER','2025-10-05 17:05:07','2025-10-05 17:05:07',_binary '','ROOM 3002 BUILDING 3 UNIT 1 NO. 99 JINJIANG ROAD','亚骐','陈','15879091005');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_user_orders`
--

DROP TABLE IF EXISTS `v_user_orders`;
/*!50001 DROP VIEW IF EXISTS `v_user_orders`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_user_orders` AS SELECT 
 1 AS `id`,
 1 AS `order_number`,
 1 AS `user_id`,
 1 AS `status`,
 1 AS `total_amount`,
 1 AS `total_quantity`,
 1 AS `payment_method`,
 1 AS `recipient_name`,
 1 AS `recipient_phone`,
 1 AS `created_at`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_user_orders`
--

/*!50001 DROP VIEW IF EXISTS `v_user_orders`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_user_orders` AS select `o`.`id` AS `id`,`o`.`order_number` AS `order_number`,`o`.`user_id` AS `user_id`,`o`.`status` AS `status`,`o`.`total_amount` AS `total_amount`,`o`.`total_quantity` AS `total_quantity`,`o`.`payment_method` AS `payment_method`,`o`.`recipient_name` AS `recipient_name`,`o`.`recipient_phone` AS `recipient_phone`,`o`.`created_at` AS `created_at` from `orders` `o` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-08 22:40:05
