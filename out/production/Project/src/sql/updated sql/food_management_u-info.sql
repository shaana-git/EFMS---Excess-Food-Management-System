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
-- Table structure for table `u-info`
--

DROP TABLE IF EXISTS `u-info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `u-info` (
  `uid` int NOT NULL,
  `uaddr` longtext,
  `uphone` varchar(10) DEFAULT NULL,
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
INSERT INTO `u-info` VALUES (2,'4/137A, 3rd Cross St, Shastri Colony, Chromepet, Chennai, Tamil Nadu 600044','8825624302',NULL,'Logarathan','Individual','techdare7@gmail.com'),(7,'Copper kitchen, Grand Southern Trunk Rd, Lakshman Nagar, Chromepet, Chennai, Tamil Nadu 600044','8888888888',NULL,'CHEF','Hotel','tonly2813@gmail.com'),(8,'Hyssop, Doctors Colony, Chromepet, Chennai, Tamil Nadu 600044','6666666666',NULL,'Haha','Hotel','tonly2814@gmail.com'),(9,'Daalchini , Grand Southern Trunk Rd, Chakrapani Colony, Chromepet, Chennai, Tamil Nadu 600044','9999999999',NULL,'Daalchini','Hotel','tonly2812@gmail.com'),(17,NULL,NULL,NULL,NULL,'Individual','testrathan@gmail.com');
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

-- Dump completed on 2024-11-13 19:56:21
