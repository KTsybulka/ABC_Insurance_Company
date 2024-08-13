-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: insurance
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `insurance_claim`
--

DROP TABLE IF EXISTS `insurance_claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_claim` (
  `claim_id` int NOT NULL AUTO_INCREMENT,
  `reg_product_id` int DEFAULT NULL,
  `claim_date` date DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `claim_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`claim_id`),
  KEY `reg_product_id` (`reg_product_id`),
  KEY `userid` (`userid`),
  CONSTRAINT `insurance_claim_ibfk_1` FOREIGN KEY (`reg_product_id`) REFERENCES `registered_product` (`reg_product_id`),
  CONSTRAINT `insurance_claim_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_claim`
--

LOCK TABLES `insurance_claim` WRITE;
/*!40000 ALTER TABLE `insurance_claim` DISABLE KEYS */;
INSERT INTO `insurance_claim` (`reg_product_id`, `claim_date`, `userid`, `description`, `claim_status`)
VALUES (4, '2024-03-01', 1, 'Screen cracked, needs replacement.', 'Pending'),
(3, '2024-04-10', 2, 'Battery issue, requires servicing.', 'Approved');
/*!40000 ALTER TABLE `insurance_claim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-02  9:39:46
