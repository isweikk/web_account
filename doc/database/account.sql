# Host: 127.0.0.1  (Version 5.7.26)
# Date: 2019-07-04 17:45:10
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cid` mediumint(8) unsigned NOT NULL DEFAULT '1001' COMMENT 'special ID, metadata, association query',
  `regtime` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `rank` tinyint(3) unsigned DEFAULT '0',
  `status` varchar(20) DEFAULT 'Inactive' COMMENT 'Actived, Inactive, Forbid',
  `nickname` varchar(20) DEFAULT 'default',
  `birthday` date DEFAULT '1900-01-01',
  `gender` varchar(8) DEFAULT 'male' COMMENT 'male female',
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000010055 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1000000000,1001,'2019-01-01 00:00:00',0,'Inactive','weikk','1900-01-01','male',NULL),(1000010000,1001,'2019-05-24 11:11:45',0,'Inactive','123@163.com','1900-01-01','male',NULL),(1000010053,1001,'2019-06-04 12:13:22',1,'INACTIVE','weikk123','2019-06-04','FEMALE',NULL),(1000010054,1001,'2019-06-04 12:15:41',1,'INACTIVE','weikk@163.com','2019-06-04','FEMALE',NULL);

#
# Structure for table "user_auth"
#

DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `suid` int(11) unsigned NOT NULL DEFAULT '0',
  `id_type` varchar(20) NOT NULL DEFAULT '',
  `identifier` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `verified` varchar(8) NOT NULL DEFAULT 'no' COMMENT '是否认证，yes/no',
  `bind_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `lognum` bigint(20) unsigned DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `unique_id` (`suid`),
  CONSTRAINT `unique_id` FOREIGN KEY (`suid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "user_auth"
#

INSERT INTO `user_auth` VALUES (1,1000000000,'NAME','weikk','123456','no','2019-06-04 00:00:00','2019-06-04 00:00:00',1),(2,1000010000,'EMAIL','123@163.com','123456','no','2019-06-03 00:00:00','2019-06-03 00:00:00',1),(3,1000010053,'NAME','weikk123','123456','no','2019-06-04 12:13:22','2019-06-04 12:13:22',1),(4,1000010054,'EMAIL','weikk@163.com','123456','no','2019-06-04 12:15:41','2019-06-04 12:15:41',1);
