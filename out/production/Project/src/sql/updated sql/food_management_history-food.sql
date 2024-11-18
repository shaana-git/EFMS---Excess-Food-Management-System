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
-- Table structure for table `history-food`
--

DROP TABLE IF EXISTS `history-food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history-food` (
  `uid` int NOT NULL,
  `utype` varchar(45) NOT NULL,
  `hotelname` varchar(40) NOT NULL,
  `fquan` int NOT NULL,
  `fcost` int NOT NULL,
  `fname` varchar(45) NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `User id matching` (`uid`),
  CONSTRAINT `User id matching` FOREIGN KEY (`uid`) REFERENCES `u-info` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history-food`
--

LOCK TABLES `history-food` WRITE;
/*!40000 ALTER TABLE `history-food` DISABLE KEYS */;
INSERT INTO `history-food` VALUES (2,'Individual','Hotel MM',99,2222,'Dosa',1),(2,'Individual','Hotel RR',70,34343,'Biriyani',2),(2,'Individual','Hotel SS',70,99999,'Biriyani',3),(2,'Individual','Hotel Vairamaligai',80,80000,'Parotta',4),(2,'Individual','Hotel ggg',67,5555,'ggggg',5),(2,'Individual','Hotel RR',90,33333,'Dosa',18),(2,'Individual','Hotel RR',90,33333,'Dosa',19),(2,'Individual','Hotel Vairamaligai',80,80000,'Parotta',30),(2,'Individual','Hotel RR',90,7777,'BIRIYANI',31),(2,'Individual','Hotel SS',100,3333,'BIRIYANI',32),(2,'Individual','Hotel Vairam',80,99999,'Parotta',34),(2,'Individual','Hotel Copper Kitchen',80,9999,'Parotta',100),(2,'Individual','Hotel Hyssop',90,3333,'Biriyani',102),(2,'Individual','Hotel Daalchini',99,33,'Parotta',104);
/*!40000 ALTER TABLE `history-food` ENABLE KEYS */;
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
