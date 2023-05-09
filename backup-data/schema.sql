-- MariaDB dump 10.19  Distrib 10.10.2-MariaDB, for osx10.18 (x86_64)
--
-- Host: localhost    Database: kakao
-- ------------------------------------------------------
-- Server version	10.10.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE SCHEMA IF NOT EXISTS `kakao` DEFAULT CHARACTER SET utf8mb4;
USE `kakao`;

--
-- Table structure for table `cart_list`
--

DROP TABLE IF EXISTS `cart_list_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_list_tb` (
                                `cart_list_id` int(11) NOT NULL AUTO_INCREMENT,
                                `cart_id` int(11) NOT NULL,
                                `option_id` int(11) NOT NULL,
                                `quantity` int(11) NOT NULL,
                                `price` int(11) NOT NULL,
                                PRIMARY KEY (`cart_list_id`),
                                KEY `cart_list_cart_id_idx` (`cart_id`),
                                KEY `cart_list_option_id_idx` (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_tb` (
                             `member_id` int(11) NOT NULL AUTO_INCREMENT,
                             `email` varchar(100) NOT NULL,
                             `password` varchar(256) NOT NULL,
                             `user_name` varchar(45) NOT NULL,
                             `roles` varchar(30) DEFAULT NULL,
                             PRIMARY KEY (`member_id`),
                             UNIQUE KEY `user_id_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `option`
--

DROP TABLE IF EXISTS `option_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_tb` (
                             `option_id` int(11) NOT NULL AUTO_INCREMENT,
                             `product_id` int(11) DEFAULT NULL,
                             `option_name` varchar(100) NOT NULL,
                             `price` int(11) NOT NULL,
                             PRIMARY KEY (`option_id`),
                             KEY `option_product_id_idx` (`product_id`),
                             CONSTRAINT `option_product_id` FOREIGN KEY (`product_id`) REFERENCES `product_tb` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_list`
--

DROP TABLE IF EXISTS `order_list_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_list_tb` (
                                 `order_list_id` int(11) NOT NULL AUTO_INCREMENT,
                                 `option_id` int(11) NOT NULL,
                                 `quantity` int(11) NOT NULL,
                                 `price` int(11) NOT NULL,
                                 `order_id` int(11) NOT NULL,
                                 PRIMARY KEY (`order_list_id`),
                                 KEY `order_list_option_id_idx` (`option_id`),
                                 CONSTRAINT `order_list_option_id` FOREIGN KEY (`option_id`) REFERENCES `option_tb` (`option_id`),
                                 KEY `order_list_order_id_idx` (`order_id`),
                                 CONSTRAINT `order_list_order_id` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `order_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_tb` (
                            `order_id` int(11) NOT NULL AUTO_INCREMENT,
                            `member_id` int(11) NOT NULL,
                            PRIMARY KEY (`order_id`),
                            KEY `order_member_id_idx` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_tb` (
                              `product_id` int(11) NOT NULL AUTO_INCREMENT,
                              `product_name` varchar(100) NOT NULL,
                              `description` varchar(1000) DEFAULT NULL,
                              `image` varchar(500) DEFAULT NULL,
                              `price` int(11) DEFAULT NULL,
                              PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17  9:18:55