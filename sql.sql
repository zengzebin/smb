/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.1.73-community : Database - rainwater
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rainwater` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rainwater`;

/*Table structure for table `area` */

DROP TABLE IF EXISTS `area`;

CREATE TABLE `area` (
  `Parent` int(3) NOT NULL AUTO_INCREMENT,
  `AreaName` varchar(10) DEFAULT NULL,
  `Code` varchar(10) DEFAULT NULL,
  `ParentId` int(3) DEFAULT NULL,
  PRIMARY KEY (`Parent`)
) ENGINE=MyISAM AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `Parent` varchar(150) DEFAULT NULL,
  `Name` varchar(60) DEFAULT NULL,
  `Code` varchar(60) DEFAULT NULL,
  `ParentId` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_Code` (`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=3510 DEFAULT CHARSET=utf8;

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` int(1) DEFAULT NULL,
  `Path` varchar(100) DEFAULT NULL,
  `Time` datetime DEFAULT NULL,
  `Parent` int(3) DEFAULT '0',
  `Status` int(2) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=5262 DEFAULT CHARSET=utf8;

/*Table structure for table `st_pptn_r` */

DROP TABLE IF EXISTS `st_pptn_r`;

CREATE TABLE `st_pptn_r` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `STCD` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `TM` datetime DEFAULT NULL COMMENT '时间',
  `DRP` decimal(5,1) DEFAULT NULL COMMENT '时段降水量',
  `INTV` decimal(5,2) DEFAULT NULL COMMENT '时段长',
  `PDR` decimal(5,2) DEFAULT NULL COMMENT '降水历时',
  `DYP` decimal(5,1) DEFAULT NULL COMMENT 'DYP',
  `WTH` char(1) DEFAULT NULL COMMENT '天气状况',
  PRIMARY KEY (`id`),
  KEY `index_stcd` (`STCD`),
  KEY `index_tm` (`TM`)
) ENGINE=MyISAM AUTO_INCREMENT=1131331 DEFAULT CHARSET=utf8;

/*Table structure for table `st_pptn_r_new` */

DROP TABLE IF EXISTS `st_pptn_r_new`;

CREATE TABLE `st_pptn_r_new` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `STCD` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `STMN` varchar(8) DEFAULT NULL COMMENT '测站名称',
  `RVNM` varchar(8) DEFAULT NULL COMMENT '河流名称',
  `BSNM` varchar(8) DEFAULT NULL COMMENT '流域名称',
  `ADDVCD` varchar(8) DEFAULT NULL COMMENT '行政区',
  `TM` datetime DEFAULT NULL COMMENT '时间',
  `DYP` decimal(5,1) DEFAULT NULL COMMENT 'DYP',
  `WTH` char(1) DEFAULT NULL COMMENT '天气状况',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `index_STCD` (`STCD`),
  KEY `index_RVNM` (`RVNM`),
  KEY `index_TM` (`TM`),
  KEY `index_STMN` (`STMN`)
) ENGINE=MyISAM AUTO_INCREMENT=1142199 DEFAULT CHARSET=utf8;

/*Table structure for table `st_river_r` */

DROP TABLE IF EXISTS `st_river_r`;

CREATE TABLE `st_river_r` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `STCD` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `TM` datetime DEFAULT NULL COMMENT '时间',
  `Z` decimal(7,3) DEFAULT NULL COMMENT '水位',
  `Q` decimal(9,3) DEFAULT NULL COMMENT '流量',
  `WPTN` varchar(1) DEFAULT NULL COMMENT '水势',
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_STCD` (`STCD`),
  KEY `INDEX_TM` (`TM`)
) ENGINE=MyISAM AUTO_INCREMENT=21002 DEFAULT CHARSET=utf8;

/*Table structure for table `st_rsvr_r` */

DROP TABLE IF EXISTS `st_rsvr_r`;

CREATE TABLE `st_rsvr_r` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `STCD` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `TM` datetime DEFAULT NULL COMMENT '时间',
  `RZ` decimal(7,3) DEFAULT NULL COMMENT '库上水位',
  `W` decimal(9,3) DEFAULT NULL COMMENT '蓄水量',
  `INQ` decimal(9,3) DEFAULT NULL COMMENT '入库流量',
  `RWPTN` varchar(2) DEFAULT NULL COMMENT '水势',
  `RsvrName` varchar(10) DEFAULT NULL COMMENT '水库名称',
  `Altitude` decimal(9,3) DEFAULT NULL COMMENT '高程',
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_STCD` (`STCD`),
  KEY `INDEX_TM` (`TM`)
) ENGINE=MyISAM AUTO_INCREMENT=18411 DEFAULT CHARSET=utf8;

/*Table structure for table `st_rvfcch_b` */

DROP TABLE IF EXISTS `st_rvfcch_b`;

CREATE TABLE `st_rvfcch_b` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `STCD` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `WRZ` decimal(7,3) DEFAULT NULL COMMENT '警戒水位',
  `MODITIME` datetime DEFAULT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`),
  KEY `INDEX_STCD` (`STCD`),
  KEY `INDEX_TIME` (`MODITIME`)
) ENGINE=MyISAM AUTO_INCREMENT=18659 DEFAULT CHARSET=utf8;

/*Table structure for table `st_stbprp_b` */

DROP TABLE IF EXISTS `st_stbprp_b`;

CREATE TABLE `st_stbprp_b` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `STCD` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `STMN` varchar(30) DEFAULT NULL COMMENT '测站名称',
  `RVNM` varchar(30) DEFAULT NULL COMMENT '河流名称',
  `HNNM` varchar(16) DEFAULT NULL COMMENT '水系名称',
  `BSNM` varchar(30) DEFAULT NULL COMMENT '流域名称',
  `LGTD` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `LTTD` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `STLC` varchar(50) DEFAULT NULL COMMENT '站址',
  `ADDVCD` varchar(6) DEFAULT NULL COMMENT '行政区划码',
  `DTMNM` varchar(16) DEFAULT NULL COMMENT '基面名称',
  `DTMEL` decimal(7,3) DEFAULT NULL COMMENT '基面高程',
  `STTP` varchar(2) DEFAULT NULL COMMENT '站类',
  `FRGRD` varchar(1) DEFAULT NULL COMMENT '报汛等级',
  `BGFRYM` varchar(6) DEFAULT NULL COMMENT '始报年月',
  `ADMAUTH` varchar(20) DEFAULT NULL COMMENT '信息管理单位',
  `STBK` varchar(1) DEFAULT NULL COMMENT '测站岸别',
  `DRNA` decimal(7,0) DEFAULT NULL COMMENT '集水面积',
  `DSTRVM` decimal(6,1) DEFAULT NULL COMMENT '至河口距离',
  `USFL` varchar(1) DEFAULT NULL COMMENT '启用标志',
  `PHCD` varchar(6) DEFAULT NULL COMMENT '拼音码',
  `DTPR` decimal(7,3) DEFAULT NULL COMMENT '基面修正值',
  `ESSTYM` varchar(6) DEFAULT NULL COMMENT '建站年月',
  `ATCUNIT` varchar(20) DEFAULT NULL COMMENT '隶属行业单位',
  `LOCALITY` varchar(10) DEFAULT NULL COMMENT '交换管理单位',
  `STAZT` decimal(10,0) DEFAULT NULL COMMENT '测站方位',
  `COMMENTS` varchar(50) DEFAULT NULL COMMENT '备注',
  `MODITIME` datetime DEFAULT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`),
  KEY `index_Addvcd` (`ADDVCD`),
  KEY `index_STCD` (`STCD`)
) ENGINE=MyISAM AUTO_INCREMENT=1210 DEFAULT CHARSET=utf8;

/*Table structure for table `stcd_type` */

DROP TABLE IF EXISTS `stcd_type`;

CREATE TABLE `stcd_type` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(2) DEFAULT NULL COMMENT '测站代码',
  `typeName` varchar(20) DEFAULT NULL COMMENT '测站类型',
  PRIMARY KEY (`id`),
  KEY `index_type` (`type`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) DEFAULT NULL COMMENT '测站编码',
  `sex` varchar(2) DEFAULT NULL COMMENT '测站编码',
  `age` int(2) DEFAULT NULL COMMENT '测站编码',
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16292 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
