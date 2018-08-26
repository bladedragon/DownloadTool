/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : downloadbase

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-08-25 21:10:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `fileinf`
-- ----------------------------
DROP TABLE IF EXISTS `fileinf`;
CREATE TABLE `fileinf` (
  `f_id` bigint(20) NOT NULL,
  `foldername` varchar(255) DEFAULT 'ROOT' COMMENT ' ',
  `f_name` varchar(255) DEFAULT NULL,
  `f_path` varchar(255) DEFAULT NULL,
  `f_suffix` varchar(255) DEFAULT NULL,
  `f_time` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fileinf
-- ----------------------------
INSERT INTO `fileinf` VALUES ('20', 'study', '谷歌访问助手', 'D://temp/e330ceb2-6da2-431d-8cad-4a1af938f8d5.zip', '.zip', '2018-08-24 13:39:37', 'z');
INSERT INTO `fileinf` VALUES ('21', 'anime', '秒速五厘米预告', 'D://temp/12698641-3595-43dd-a9bd-33b1a976e14a.mp4', '.mp4', '2018-08-24 14:16:40', 'z');
INSERT INTO `fileinf` VALUES ('22', 'anime', '秒速五厘米解析', 'D://temp/894aa38e-3e89-45c4-8f47-593860c96e06.mp4', '.mp4', '2018-08-24 14:31:42', 'zzz');
INSERT INTO `fileinf` VALUES ('23', '讲课相关', '留言板实例', 'D://temp/0cdcefbf-68e0-4eae-aad9-f828c62f2035.md', '.md', '2018-08-24 14:38:21', 'zzz');
INSERT INTO `fileinf` VALUES ('24', '讲课相关', '数据结构', 'D://temp/d6701dae-bc5a-4366-b7dc-2d4b408ac7d6.png', '.png', '2018-08-24 17:46:31', 'zzz');
INSERT INTO `fileinf` VALUES ('25', 'study', '管脚分配', 'D://temp/0beabb22-8fae-43ec-8d96-99d664b64b77.xls', '.xls', '2018-08-24 17:48:43', 'z');
INSERT INTO `fileinf` VALUES ('26', 'study', '自荐书', 'D://temp/55ce2d2e-5bf7-42e9-9ce6-ec6bbb2e4554.docx', '.docx', '2018-08-24 17:49:21', 'z');

-- ----------------------------
-- Table structure for `folder`
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `folderid` bigint(20) NOT NULL,
  `foldername` varchar(255) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  `foldertime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`folderid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of folder
-- ----------------------------
INSERT INTO `folder` VALUES ('1', 'ROOT', null, '2018-08-23 00:00:00');
INSERT INTO `folder` VALUES ('2', 'study', 'ROOT', '2018-08-25 01:25');
INSERT INTO `folder` VALUES ('3', '讲课相关', 'study', '2018-08-25 16:41');
INSERT INTO `folder` VALUES ('4', 'anime', 'ROOT', '2018-08-25 17:25');
INSERT INTO `folder` VALUES ('5', '自定义文件夹1', 'study', '2018-8-25 17:14');
INSERT INTO `folder` VALUES ('6', '自定义文件夹2', '自定义文件夹1', '2018-8-25 17:15');
INSERT INTO `folder` VALUES ('28', '音乐', 'ROOT', '2018-08-25 20:29:17');

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('31');
INSERT INTO `hibernate_sequence` VALUES ('31');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `identity` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '0', 'z', 'z');
INSERT INTO `user` VALUES ('10', '0', '123456', 'zzz');
INSERT INTO `user` VALUES ('27', '0', '123456', 'meng');
