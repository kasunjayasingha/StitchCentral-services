-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: stitchcentral
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(100) NOT NULL,
  `postal_code` varchar(45) NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  `club` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone_no` varchar(11) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `customer_type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (58,'Kasun','Amal','Henawatha, Anangoda, Bataduwa, Galle.','Galle','0','dd','g','hg','kasunaaa@gmail.com','0766988963','123456','2023-12-03','2023-12-03','REGULAR'),(64,'dfd','dfdf','Henawatha, Anangoda, Bataduwa, Galle.','dsd','0','','','','kamal@gmail.com','0766988963','','2023-12-27','2023-12-27','GUEST'),(73,'Kasun','Jayasinghe','Henawatha, Anangoda, Bataduwa, Galle.','Galle','0','Epic','','','kasunjayasinghe@gmail.com','0766988963','','2024-01-20','2024-01-20','GUEST');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_Id` int DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  `description` text,
  `status` varchar(50) NOT NULL,
  `cancellation_reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cus_id_idx` (`customer_Id`),
  CONSTRAINT `fk_cus_id` FOREIGN KEY (`customer_Id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (123,58,'2023-12-03','ONLINE','gfgfg','PENDING',NULL),(129,64,'2023-12-27','ONLINE','dfdf','PENDING',NULL),(137,73,'2024-01-20','ONLINE','ghghg','PENDING',NULL);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_sample`
--

DROP TABLE IF EXISTS `client_sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_sample` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) NOT NULL,
  `file_type` varchar(45) NOT NULL,
  `path` text,
  `relative_path` text,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `appointment_id` int DEFAULT NULL,
  `file_data` mediumblob,
  `file_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_apoinment_id_idx` (`appointment_id`),
  CONSTRAINT `fk_apoinment_id` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_sample`
--

LOCK TABLES `client_sample` WRITE;
/*!40000 ALTER TABLE `client_sample` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_us`
--

DROP TABLE IF EXISTS `contact_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_us` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_us`
--

LOCK TABLES `contact_us` WRITE;
/*!40000 ALTER TABLE `contact_us` DISABLE KEYS */;
INSERT INTO `contact_us` VALUES (1,'2024-01-01','kasunharitha55@gmail.com','dfdfdfghghg','Kasun Thiwanka'),(2,'2024-01-03','kasunharitha55@gmail.com','fdfddf','ddgf null');
/*!40000 ALTER TABLE `contact_us` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `advance` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dispatch_date` date DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `swing_place` varchar(255) DEFAULT NULL,
  `appointment_id` int DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `invoice_no` varchar(255) DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `client_sample_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kuniie6sg0s2b9ftepoc8yy0g` (`client_sample_id`),
  KEY `FKlg8d853ctsyl15f0v922rs7j5` (`appointment_id`),
  CONSTRAINT `FKlg8d853ctsyl15f0v922rs7j5` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`id`),
  CONSTRAINT `FKn4by2x5khn4f4wpb5as0d63i0` FOREIGN KEY (`client_sample_id`) REFERENCES `client_sample` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(40) DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2023-12-19','Kasun','Jayasinghe','123456','OWNER','2024-01-27','kassa','ACTIVE','kasunharitha55@gmail.com'),(2,'2023-12-19','Kamal','Perrera','1234','DESIGNER','2023-12-28','kamala','ACTIVE','Kamal@gmail.com'),(3,'2023-12-20','Amal','Perera','123456','ADMIN','2024-01-29','amala','DEACTIVATE','amal@gmail.com'),(5,'2023-12-20','Nayana','Sunil','123456','ADMIN','2024-01-03','nayana','ACTIVE','nayana@gmail.com'),(6,'2024-01-03','pubudu','dulla','123456','ADMIN','2024-01-27','pubudu','ACTIVE','admin1@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-30 23:11:01
