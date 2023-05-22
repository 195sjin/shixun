/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.30 : Database - mv
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mv` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mv`;

/*Table structure for table `t_movie` */

DROP TABLE IF EXISTS `t_movie`;

CREATE TABLE `t_movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(30) DEFAULT NULL,
  `movie_type` varchar(30) DEFAULT NULL,
  `actor` varchar(30) DEFAULT NULL,
  `director` varchar(30) DEFAULT NULL,
  `minS` timestamp NULL DEFAULT NULL,
  `movieDate` timestamp NULL DEFAULT NULL,
  `file_path` varchar(50) DEFAULT NULL,
  `news_id` int DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `update_user` varchar(30) DEFAULT NULL,
  `is_deleted` int DEFAULT '0' COMMENT '0表示未被删除，1表示已经被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_movie` */

insert  into `t_movie`(`id`,`movie_name`,`movie_type`,`actor`,`director`,`minS`,`movieDate`,`file_path`,`news_id`,`update_time`,`create_user`,`update_user`,`is_deleted`) values 
(1,'色戒','幻觉',NULL,NULL,NULL,NULL,NULL,1,'2023-05-22 20:54:25','admin','admin',0);

/*Table structure for table `t_movie_type` */

DROP TABLE IF EXISTS `t_movie_type`;

CREATE TABLE `t_movie_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_type` varchar(30) DEFAULT NULL,
  `number` int DEFAULT '0',
  `update_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `update_user` varchar(30) DEFAULT NULL,
  `is_deleted` int DEFAULT '0' COMMENT '0表示正常，1表示禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_movie_type` */

insert  into `t_movie_type`(`id`,`movie_type`,`number`,`update_time`,`create_time`,`create_user`,`update_user`,`is_deleted`) values 
(1,'幻觉',2,'2023-05-22 20:54:25','2023-05-22 20:54:25','admin','admin',0),
(2,'动作片',3,'2023-05-22 16:11:01','2023-05-22 16:11:01','admin','admin',0),
(3,'恐怖片',1,'2023-05-22 16:11:35','2023-05-22 16:11:35','admin','admin',0),
(4,'恐怖片1',0,'2023-05-22 16:13:30','2023-05-22 16:13:30','admin','admin',0),
(5,'恐怖片11',1,'2023-05-22 19:47:28','2023-05-22 19:47:28','admin','admin',0);

/*Table structure for table `t_news` */

DROP TABLE IF EXISTS `t_news`;

CREATE TABLE `t_news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `update_user` varchar(30) DEFAULT NULL,
  `is_deleted` int DEFAULT '0' COMMENT '0表示正常，1表示禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_news` */

insert  into `t_news`(`id`,`title`,`content`,`update_time`,`create_user`,`update_user`,`is_deleted`) values 
(1,'色戒上线','希望大家认真学习',NULL,NULL,NULL,0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `status` int DEFAULT '1' COMMENT '1表示正常，0表示禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`password`,`name`,`email`,`status`) values 
(542113460919,'020313','admin','zijin195@!63.com',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
