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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (10,'2',NULL,0,'2024-11-09 18:20:37'),(11,'2',NULL,0,'2024-11-09 18:22:30'),(12,'2','Individual',0,'2024-11-09 18:24:07'),(13,'2','Individual',0,'2024-11-09 18:55:08'),(14,'5',NULL,1,'2024-11-09 19:47:12'),(15,'2','Individual',0,'2024-11-09 20:06:21'),(16,'2','Individual',0,'2024-11-09 20:30:30'),(17,'2','Individual',0,'2024-11-10 13:00:13'),(18,'2','Individual',0,'2024-11-10 13:04:27'),(19,'2','Individual',0,'2024-11-10 13:10:13'),(20,'2','Individual',0,'2024-11-10 13:14:24'),(21,'2','Individual',0,'2024-11-10 13:21:00'),(22,'2','Individual',0,'2024-11-10 13:27:21'),(23,'2','Individual',0,'2024-11-10 13:29:49'),(24,'2','Individual',0,'2024-11-10 14:14:35'),(25,'2','Individual',0,'2024-11-10 14:17:26'),(26,'2','Individual',0,'2024-11-11 06:07:07'),(27,'2','Individual',0,'2024-11-11 06:11:21'),(28,'2','Individual',0,'2024-11-11 06:16:23'),(29,'2','Individual',0,'2024-11-11 06:25:23'),(30,'2','Individual',0,'2024-11-11 06:26:13'),(31,'2','Individual',0,'2024-11-11 06:27:10'),(32,'2','Individual',0,'2024-11-11 06:31:37'),(33,'2','Individual',0,'2024-11-11 06:34:53'),(34,'7','Hotel',0,'2024-11-11 06:38:37'),(35,'2','Individual',0,'2024-11-11 16:52:05'),(36,'2','Individual',0,'2024-11-11 17:14:38'),(37,'2','Individual',0,'2024-11-11 17:17:34'),(38,'2','Individual',0,'2024-11-11 17:19:22'),(39,'2','Individual',0,'2024-11-11 17:26:16'),(40,'2','Individual',0,'2024-11-11 17:27:38'),(41,'2','Individual',0,'2024-11-11 17:29:54'),(42,'2','Individual',0,'2024-11-11 17:37:50'),(43,'2','Individual',0,'2024-11-11 17:48:56'),(44,'2','Individual',0,'2024-11-11 17:50:39'),(45,'2','Individual',0,'2024-11-11 17:57:04'),(46,'2','Individual',0,'2024-11-11 17:59:16'),(52,'7','Hotel',0,'2024-11-12 15:50:34'),(53,'7','Hotel',0,'2024-11-12 15:55:33'),(54,'7','Hotel',0,'2024-11-12 15:58:05'),(55,'9','Hotel',0,'2024-11-12 15:59:16'),(56,'9','Organization',0,'2024-11-12 16:04:34'),(57,'9','Organization',0,'2024-11-12 16:06:13'),(58,'9','Hotel',0,'2024-11-12 16:06:51'),(59,'9','Hotel',0,'2024-11-12 16:08:21'),(60,'2','Individual',0,'2024-11-12 16:09:06'),(61,'2','Individual',0,'2024-11-12 16:13:46');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
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
