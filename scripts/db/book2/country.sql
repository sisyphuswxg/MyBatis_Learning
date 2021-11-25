/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL8 : Database - mybatis_test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatis_test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mybatis_test`;

/*Table structure for table `batch` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `countryname` varchar(255) NULL,
  `countrycode` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `country` */

insert into `country`(`countryname`,`countrycode`)
values ('中国','CN'),('美国','US'),('俄罗斯','RU'),('英国','GB'),('法国','FR');

