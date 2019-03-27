/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : itcuc_news

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-27 16:39:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_function
-- ----------------------------
DROP TABLE IF EXISTS `base_function`;
CREATE TABLE `base_function` (
  `id` varchar(255) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK77rvv9uf7dwy69a2e32qdftg3` (`parent_id`),
  CONSTRAINT `FK77rvv9uf7dwy69a2e32qdftg3` FOREIGN KEY (`parent_id`) REFERENCES `base_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_function
-- ----------------------------
INSERT INTO `base_function` VALUES ('0', null, null, null, null, null, '管理员管理', '#', 'abstract');
INSERT INTO `base_function` VALUES ('01', null, null, null, null, null, '管理员列表', 'managerlist', '0');
INSERT INTO `base_function` VALUES ('02', null, null, null, null, null, '角色管理', 'roleList', '0');
INSERT INTO `base_function` VALUES ('03', null, null, null, null, null, '菜单管理', 'functionList', '0');
INSERT INTO `base_function` VALUES ('abstract', null, null, null, null, null, '虚拟菜单', '#', 'abstract');

-- ----------------------------
-- Table structure for base_manager
-- ----------------------------
DROP TABLE IF EXISTS `base_manager`;
CREATE TABLE `base_manager` (
  `id` varchar(255) NOT NULL,
  `login_addr` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_manager
-- ----------------------------
INSERT INTO `base_manager` VALUES ('9bd880a0-3462-4ea5-acf8-2ed5ae629bca', null, null, null, 'e10adc3949ba59abbe56e057f20f883e', 'zhangyi');

-- ----------------------------
-- Table structure for base_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `base_manager_role`;
CREATE TABLE `base_manager_role` (
  `manager_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FKkcys6yp2l3te38ityfyu4645n` (`role_id`),
  KEY `FKnhs9kruc7orrmo9rvam3c8wb5` (`manager_id`),
  CONSTRAINT `FKkcys6yp2l3te38ityfyu4645n` FOREIGN KEY (`role_id`) REFERENCES `base_role` (`id`),
  CONSTRAINT `FKnhs9kruc7orrmo9rvam3c8wb5` FOREIGN KEY (`manager_id`) REFERENCES `base_manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_manager_role
-- ----------------------------
INSERT INTO `base_manager_role` VALUES ('9bd880a0-3462-4ea5-acf8-2ed5ae629bca', '0');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` varchar(255) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `modify_user` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('0', null, null, null, null, '超级管理员');

-- ----------------------------
-- Table structure for base_role_function
-- ----------------------------
DROP TABLE IF EXISTS `base_role_function`;
CREATE TABLE `base_role_function` (
  `role_id` varchar(255) NOT NULL,
  `function_id` varchar(255) NOT NULL,
  KEY `FK2naj6hyey0u5ya58bjirn6vye` (`function_id`),
  KEY `FKbjkx3b62sqbvryl0snfsyao3g` (`role_id`),
  CONSTRAINT `FK2naj6hyey0u5ya58bjirn6vye` FOREIGN KEY (`function_id`) REFERENCES `base_function` (`id`),
  CONSTRAINT `FKbjkx3b62sqbvryl0snfsyao3g` FOREIGN KEY (`role_id`) REFERENCES `base_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role_function
-- ----------------------------
INSERT INTO `base_role_function` VALUES ('0', '0');
INSERT INTO `base_role_function` VALUES ('0', '01');
INSERT INTO `base_role_function` VALUES ('0', '02');
INSERT INTO `base_role_function` VALUES ('0', '03');
INSERT INTO `base_role_function` VALUES ('0', 'abstract');

-- ----------------------------
-- Table structure for news_article
-- ----------------------------
DROP TABLE IF EXISTS `news_article`;
CREATE TABLE `news_article` (
  `id` varchar(255) NOT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `content` longtext,
  `content_text` longtext,
  `created_time` datetime DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `source_author` varchar(255) DEFAULT NULL,
  `source_link` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_article
-- ----------------------------

-- ----------------------------
-- Table structure for news_article_addition
-- ----------------------------
DROP TABLE IF EXISTS `news_article_addition`;
CREATE TABLE `news_article_addition` (
  `id` varchar(255) NOT NULL,
  `article_id` varchar(255) DEFAULT NULL,
  `dislike` int(11) DEFAULT NULL,
  `favorite` int(11) DEFAULT NULL,
  `readed` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_article_addition
-- ----------------------------

-- ----------------------------
-- Table structure for news_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `news_article_comment`;
CREATE TABLE `news_article_comment` (
  `id` varchar(255) NOT NULL,
  `article_id` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_article_comment
-- ----------------------------

-- ----------------------------
-- Table structure for news_user
-- ----------------------------
DROP TABLE IF EXISTS `news_user`;
CREATE TABLE `news_user` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_signin_ip` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_user
-- ----------------------------
