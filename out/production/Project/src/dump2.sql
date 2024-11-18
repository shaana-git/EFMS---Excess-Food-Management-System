-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: food_management
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `available_food`
--

DROP TABLE IF EXISTS `available_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `available_food` (
  `hotelname` varchar(40) NOT NULL,
  `fquan` int NOT NULL,
  `fcost` int NOT NULL,
  `fname` varchar(45) NOT NULL,
  PRIMARY KEY (`hotelname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `available_food`
--

LOCK TABLES `available_food` WRITE;
/*!40000 ALTER TABLE `available_food` DISABLE KEYS */;
INSERT INTO `available_food` VALUES ('MM',99,2222,'Dosa'),('RR',70,34343,'Biriyani'),('SS',70,99999,'Biriyani'),('Vairamaligai',80,80000,'Parotta');
/*!40000 ALTER TABLE `available_food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creds`
--

DROP TABLE IF EXISTS `creds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creds` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(45) NOT NULL,
  `upwd` varchar(45) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname_UNIQUE` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creds`
--

LOCK TABLES `creds` WRITE;
/*!40000 ALTER TABLE `creds` DISABLE KEYS */;
INSERT INTO `creds` VALUES (2,'rathan','Logarathan1$'),(3,'hhhhhhhhhh','Helloworld1$'),(4,'llllll','Lllllllll1$');
/*!40000 ALTER TABLE `creds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geo-info`
--

DROP TABLE IF EXISTS `geo-info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `geo-info` (
  `uid` int NOT NULL,
  `ulat` decimal(10,0) NOT NULL,
  `ulong` decimal(10,0) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `ulat_UNIQUE` (`ulat`),
  UNIQUE KEY `ulong_UNIQUE` (`ulong`),
  CONSTRAINT `Get info` FOREIGN KEY (`uid`) REFERENCES `u-info` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geo-info`
--

LOCK TABLES `geo-info` WRITE;
/*!40000 ALTER TABLE `geo-info` DISABLE KEYS */;
/*!40000 ALTER TABLE `geo-info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `session_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `is_active` tinyint DEFAULT '1',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (1,'2',NULL,1,'2024-11-09 16:20:02'),(2,'2',NULL,1,'2024-11-09 16:28:39'),(3,'2',NULL,1,'2024-11-09 16:33:17');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `u-info`
--

DROP TABLE IF EXISTS `u-info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `u-info` (
  `uid` int NOT NULL,
  `uaddr` varchar(45) DEFAULT NULL,
  `uphone` bigint DEFAULT NULL,
  `uprofimg` longblob,
  `name` varchar(45) DEFAULT NULL,
  `utype` varchar(45) DEFAULT NULL,
  `umail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uphone_UNIQUE` (`uphone`),
  UNIQUE KEY `uname_UNIQUE` (`name`),
  UNIQUE KEY `umail_UNIQUE` (`umail`),
  CONSTRAINT `Cred Details` FOREIGN KEY (`uid`) REFERENCES `creds` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `u-info`
--

LOCK TABLES `u-info` WRITE;
/*!40000 ALTER TABLE `u-info` DISABLE KEYS */;
INSERT INTO `u-info` VALUES (2,NULL,NULL,NULL,NULL,NULL,'techdare7@gmail.com'),(3,NULL,NULL,NULL,NULL,NULL,'tttt@gmail.com'),(4,NULL,NULL,NULL,NULL,NULL,'tttttjjj@gmail.com');
/*!40000 ALTER TABLE `u-info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-09 22:38:30
