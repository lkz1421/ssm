/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : shiro_test

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-03-10 11:23:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sec_permission
-- ----------------------------
DROP TABLE IF EXISTS `sec_permission`;
CREATE TABLE `sec_permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_permission
-- ----------------------------
INSERT INTO `sec_permission` VALUES ('1', 'user:create', '2016-03-09 15:42:07', '2016-03-09 15:42:10');
INSERT INTO `sec_permission` VALUES ('2', 'user:view', '2016-03-09 15:43:35', '2016-03-09 15:43:39');

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_role
-- ----------------------------
INSERT INTO `sec_role` VALUES ('1', 'admin', '2016-03-09 11:58:12', '2016-03-09 11:58:16');
INSERT INTO `sec_role` VALUES ('2', 'user', '2016-03-09 15:09:04', '2016-03-09 15:09:08');

-- ----------------------------
-- Table structure for sec_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_permission`;
CREATE TABLE `sec_role_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_id外键` (`permission_id`),
  KEY `role_id外键1` (`role_id`),
  CONSTRAINT `permission_id外键` FOREIGN KEY (`permission_id`) REFERENCES `sec_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id外键1` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_role_permission
-- ----------------------------
INSERT INTO `sec_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sec_role_permission` VALUES ('2', '2', '1');
INSERT INTO `sec_role_permission` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES ('1', 'jacky', '9661FD65249B026EBEA0F49927E82F0E', '2016-03-08 16:37:59', '2016-03-08 16:38:06');
INSERT INTO `sec_user` VALUES ('2', 'cheng', '89975C5E5D407916E8080D137C48DDD7', '2016-03-09 15:09:35', '2016-03-09 15:10:16');

-- ----------------------------
-- Table structure for sec_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_user_role`;
CREATE TABLE `sec_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `role_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id外键` (`user_id`),
  KEY `role_id外键` (`role_id`),
  CONSTRAINT `role_id外键` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id外键` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_user_role
-- ----------------------------
INSERT INTO `sec_user_role` VALUES ('1', '1', '1');
INSERT INTO `sec_user_role` VALUES ('2', '2', '2');
