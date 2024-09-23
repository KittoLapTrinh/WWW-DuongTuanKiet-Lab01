-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.1.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mydb
CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `mydb`;

-- Dumping structure for table mydb.account
CREATE TABLE IF NOT EXISTS `account` (
                                         `account_id` varchar(255) NOT NULL,
                                         `email` varchar(255) DEFAULT NULL,
                                         `full_name` varchar(255) DEFAULT NULL,
                                         `password` varchar(255) DEFAULT NULL,
                                         `phone` varchar(255) DEFAULT NULL,
                                         `status` tinyint(4) NOT NULL,
                                         PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.account: ~7 rows (approximately)
DELETE FROM `account`;
INSERT INTO `account` (`account_id`, `email`, `full_name`, `password`, `phone`, `status`) VALUES
                                                                                              ('kiet', 'kietduong@gmail.com', 'Duong Tuan Kiet', '123', '(+84) 348333253', 1),
                                                                                              ('nhu', 'nhu@gmail.com', 'Huynh Le Tam Nhu', '234', '0885715125', 1),
                                                                                              ('huy', 'huy@gmail.com', 'Vo Quoc Huy', '123', '02125252152', 1),
                                                                                              ('khoi', 'khoi@gmail.com', 'Nguyen Quoc Khoi 1', '123', '212512521521', 1),
                                                                                              ('met', 'met@gmail.com', 'Nguyen Thi Met', '123', '(+84) 815615961', -1),
                                                                                              ('ngoc', 'ngoc@gmail.com', 'Duong The Ngoc', '123', '08125125215', 1),
                                                                                              ('teo', 'teo@gmail.com', 'Nguyen Van Teo', '123', '(+84) 815615961', 1);

-- Dumping structure for table mydb.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
                                              `is_grant` tinyint(4) DEFAULT NULL,
                                              `note` varchar(255) DEFAULT NULL,
                                              `account_id` varchar(255) NOT NULL,
                                              `role_id` varchar(255) NOT NULL,
                                              PRIMARY KEY (`account_id`,`role_id`),
                                              KEY `FK8hpapq7v95y2lxuye1tggk6c1` (`role_id`),
                                              CONSTRAINT `FK8hpapq7v95y2lxuye1tggk6c1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
                                              CONSTRAINT `FKnqqon4lee4wweiwnw3sllhl43` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.grant_access: ~7 rows (approximately)
DELETE FROM `grant_access`;
INSERT INTO `grant_access` (`is_grant`, `note`, `account_id`, `role_id`) VALUES
                                                                             (1, 'account', 'kiet', 'user'),
                                                                             (1, 'Create account at: 2024-09-18T16:20:56.384332700', 'nhu', 'admin'),
                                                                             (1, 'account', 'huy', 'user'),
                                                                             (1, '', 'khoi', 'admin'),
                                                                             (1, '', 'met', 'user'),
                                                                             (1, 'account', 'ngoc', 'user'),
                                                                             (1, '', 'teo', 'admin');

-- Dumping structure for table mydb.log
CREATE TABLE IF NOT EXISTS `log` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                     `account_id` varchar(255) DEFAULT NULL,
                                     `login_time` datetime DEFAULT NULL,
                                     `logout_time` datetime DEFAULT NULL,
                                     `notes` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.log: ~73 rows (approximately)
DELETE FROM `log`;
INSERT INTO `log` (`id`, `account_id`, `login_time`, `logout_time`, `notes`) VALUES
                                                                                 (8, 'teo', '2024-09-24 00:00:00', NULL, 'user login'),
                                                                                 (9, 'met', '2024-09-24 15:30:45', NULL, 'login'),
                                                                                 (10, 'teo', '2024-09-24 21:01:56', NULL, 'user login'),
                                                                                 (11, 'teo', NULL, '2024-09-24 21:03:11', 'user logout'),
                                                                                 (12, 'teo', '2024-09-24 21:12:45', NULL, 'user login'),
                                                                                 (13, 'teo', '2024-09-24 21:17:04', NULL, 'user login'),
                                                                                 (14, 'teo', NULL, '2024-09-24 21:17:16', 'user logout'),
                                                                                 (15, 'teo', '2024-09-24 21:22:34', NULL, 'user login'),
                                                                                 (16, 'teo', NULL, '2024-09-24 21:22:42', 'user logout'),
                                                                                 (17, 'teo', '2024-09-24 22:11:40', NULL, 'admin login'),
                                                                                 (18, 'teo', NULL, '22024-09-24 22:12:04', 'user logout'),
                                                                                 (19, 'met', '2024-09-24 22:12:31', NULL, 'user login'),
                                                                                 (20, 'met', '2024-09-24 22:13:41', NULL, 'user login'),
                                                                                 (21, 'met', '2024-09-24 22:16:52', NULL, 'user login'),
                                                                                 (22, 'met', '2024-09-24 22:19:28', NULL, 'user login'),
                                                                                 (23, 'teo', '2024-09-24 23:24:28', NULL, 'admin login'),
                                                                                 (24, 'teo', '2024-09-24 23:25:33', NULL, 'admin login'),
                                                                                 (25, 'teo', '2024-09-24 23:26:57', NULL, 'admin login'),
                                                                                 (26, 'teo', '2024-09-24 23:27:16', NULL, 'admin login'),
                                                                                 (27, 'teo', '2024-09-24 23:30:02', NULL, 'admin login'),
                                                                                 (28, 'teo', '2024-09-24 23:46:50', NULL, 'admin login'),
                                                                                 (29, 'teo', '2024-09-17 23:47:44', NULL, 'admin login'),
                                                                                 (30, 'teo', '2024-09-18 00:29:56', NULL, 'admin login'),
                                                                                 (31, 'teo', '2024-09-18 00:32:37', NULL, 'admin login'),
                                                                                 (32, 'teo', '2024-09-18 00:34:39', NULL, 'admin login'),
                                                                                 (33, 'teo', '2024-09-18 10:09:20', NULL, 'admin login'),
                                                                                 (34, 'teo', '2024-09-18 10:14:55', NULL, 'admin login'),
                                                                                 (35, 'teo', '2024-09-18 10:20:10', NULL, 'admin login'),
                                                                                 (36, 'teo', '2024-09-18 10:24:55', NULL, 'admin login'),
                                                                                 (37, 'teo', '2024-09-18 10:25:50', NULL, 'admin login'),
                                                                                 (38, 'teo', '2024-09-18 10:34:40', NULL, 'admin login'),
                                                                                 (39, 'teo', '2024-09-18 15:55:42', NULL, 'admin login'),
                                                                                 (40, 'teo', '2024-09-18 15:57:21', NULL, 'admin login'),
                                                                                 (41, 'teo', '2024-09-18 16:18:08', NULL, 'admin login'),
                                                                                 (42, 'teo', '2024-09-18 16:20:26', NULL, 'admin login'),
                                                                                 (43, 'teo', '2024-09-18 16:34:04', NULL, 'admin login'),
                                                                                 (44, 'teo', '2024-09-18 16:38:33', NULL, 'admin login'),
                                                                                 (45, 'teo', '2024-09-18 16:39:30', NULL, 'admin login'),
                                                                                 (46, 'teo', NULL, '2024-09-18 16:40:26', 'user logout'),
                                                                                 (47, 'teo', NULL, '2024-09-18 16:41:17', 'user logout'),
                                                                                 (48, 'duy', '2024-09-18 16:41:21', NULL, 'admin login'),
                                                                                 (49, 'duy', NULL, '2024-09-18 16:41:34', 'user logout'),
                                                                                 (50, 'teo', '2024-09-18 19:54:19', NULL, 'admin login'),
                                                                                 (51, 'teo', '2024-09-18 20:14:57', NULL, 'admin login'),
                                                                                 (52, 'teo', '2024-09-18 20:45:34', NULL, 'admin login'),
                                                                                 (53, 'teo', '2024-09-18 20:49:01', NULL, 'admin login'),
                                                                                 (54, 'teo', '2024-09-18 20:50:23', NULL, 'admin login'),
                                                                                 (55, 'teo', '2024-09-18 20:51:21', NULL, 'admin login'),
                                                                                 (56, 'teo', '2024-09-18 20:53:32', NULL, 'admin login'),
                                                                                 (57, 'teo', '2024-09-18 20:55:21', NULL, 'admin login'),
                                                                                 (58, 'teo', '2024-09-18 20:56:10', NULL, 'admin login'),
                                                                                 (59, 'teo', '2024-09-18 20:58:58', NULL, 'admin login'),
                                                                                 (60, 'teo', '2024-09-18 21:01:10', NULL, 'admin login'),
                                                                                 (61, 'teo', '2024-09-18 21:11:39', NULL, 'admin login'),
                                                                                 (62, 'teo', '2024-09-18 21:12:31', NULL, 'admin login'),
                                                                                 (63, 'teo', '2024-09-18 21:16:40', NULL, 'admin login'),
                                                                                 (64, 'teo', '2024-09-18 21:18:19', NULL, 'admin login'),
                                                                                 (65, 'teo', '2024-09-18 21:19:07', NULL, 'admin login'),
                                                                                 (66, 'teo', '2024-09-18 21:20:32', NULL, 'admin login'),
                                                                                 (67, 'kiet', '2024-09-18 21:33:27', NULL, 'user login'),
                                                                                 (68, 'kiet', '2024-09-18 21:36:56', NULL, 'user login'),
                                                                                 (69, 'kiet', '2024-09-18 21:38:17', NULL, 'user login'),
                                                                                 (70, 'kiet', '2024-09-18 21:55:06', NULL, 'user login'),
                                                                                 (71, 'kiet', '2024-09-18 21:55:56', NULL, 'user login'),
                                                                                 (72, 'kiet', NULL, '2024-09-18 21:56:00', 'user logout'),
                                                                                 (73, 'teo', '2024-09-18 21:56:17', NULL, 'admin login'),
                                                                                 (74, 'teo', '2024-09-20 16:53:53', NULL, 'admin login'),
                                                                                 (75, 'teo', NULL, '2024-09-20 16:55:47', 'user logout'),
                                                                                 (76, 'nhu', '2024-09-20 16:56:07', NULL, 'admin login'),
                                                                                 (77, 'nhu', NULL, '2024-09-20 16:56:12', 'user logout'),
                                                                                 (78, 'kiet', '2024-09-20 16:56:16', NULL, 'user login'),
                                                                                 (79, 'kiet', NULL, '2024-09-20 16:56:21', 'user logout'),
                                                                                 (80, 'teo', '2024-09-20 16:56:25', NULL, 'admin login');

-- Dumping structure for table mydb.role
CREATE TABLE IF NOT EXISTS `role` (
                                      `role_id` varchar(255) NOT NULL,
                                      `description` varchar(255) DEFAULT NULL,
                                      `role_name` varchar(255) DEFAULT NULL,
                                      `status` tinyint(4) NOT NULL,
                                      PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.role: ~2 rows (approximately)
DELETE FROM `role`;
INSERT INTO `role` (`role_id`, `description`, `role_name`, `status`) VALUES
                                                                         ('admin', 'admin role', 'administrator', 1),
                                                                         ('user', 'user role', 'user', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;