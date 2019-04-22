/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : itcuc_news

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-22 14:58:40
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
  `parent_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_function
-- ----------------------------
INSERT INTO `base_function` VALUES ('0', null, null, null, null, null, '权限管理', 'abstract', '#');
INSERT INTO `base_function` VALUES ('01', null, null, null, null, null, '管理员列表', '0', 'manager/list');
INSERT INTO `base_function` VALUES ('02', null, null, null, null, null, '角色管理', '0', 'role/list');
INSERT INTO `base_function` VALUES ('03', null, null, null, null, null, '菜单管理', '0', 'function/list');
INSERT INTO `base_function` VALUES ('abstract', null, null, null, null, null, '虚拟菜单', null, '#');

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
  `username` varchar(255) NOT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_manager
-- ----------------------------
INSERT INTO `base_manager` VALUES ('15617f36-89ef-4128-bb0b-5d6cba09864a', null, null, '1111', 'e10adc3949ba59abbe56e057f20f883e', 'zhangyi3', '1');
INSERT INTO `base_manager` VALUES ('51bbd0fb-223e-4bfb-bab1-266547c98df8', null, null, '11111', 'e10adc3949ba59abbe56e057f20f883e', 'zhangyi1', '1');
INSERT INTO `base_manager` VALUES ('9bd880a0-3462-4ea5-acf8-2ed5ae629bca', '0:0:0:0:0:0:0:1', '2019-04-19 13:58:37', 'zy', 'e10adc3949ba59abbe56e057f20f883e', 'zhangyi', '1');

-- ----------------------------
-- Table structure for base_rel_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `base_rel_manager_role`;
CREATE TABLE `base_rel_manager_role` (
  `id` varchar(255) NOT NULL,
  `manager_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_rel_manager_role
-- ----------------------------
INSERT INTO `base_rel_manager_role` VALUES ('0', '9bd880a0-3462-4ea5-acf8-2ed5ae629bca', '0');
INSERT INTO `base_rel_manager_role` VALUES ('5205bc10-9bbb-4739-8af5-758518daa431', '51bbd0fb-223e-4bfb-bab1-266547c98df8', '0');
INSERT INTO `base_rel_manager_role` VALUES ('7c28b091-8d3c-4e60-86ec-aede38962257', '15617f36-89ef-4128-bb0b-5d6cba09864a', '1');
INSERT INTO `base_rel_manager_role` VALUES ('a899aa64-b9cd-4ce5-a304-1ca34af4aa0e', '15617f36-89ef-4128-bb0b-5d6cba09864a', '0');

-- ----------------------------
-- Table structure for base_rel_role_function
-- ----------------------------
DROP TABLE IF EXISTS `base_rel_role_function`;
CREATE TABLE `base_rel_role_function` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `function_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_rel_role_function
-- ----------------------------
INSERT INTO `base_rel_role_function` VALUES ('0', '0', '0');
INSERT INTO `base_rel_role_function` VALUES ('1', '0', '01');
INSERT INTO `base_rel_role_function` VALUES ('2', '0', '02');
INSERT INTO `base_rel_role_function` VALUES ('3', '0', '03');
INSERT INTO `base_rel_role_function` VALUES ('4', '0', 'abstract');

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
INSERT INTO `base_role` VALUES ('1', '2019-04-12 15:30:05', '', '2019-04-12 15:30:08', '', '一般会员');

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
