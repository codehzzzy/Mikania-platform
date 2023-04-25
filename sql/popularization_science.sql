/*
Navicat MySQL Data Transfer

Source Server         : 124.71.65.12
Source Server Version : 80024
Source Host           : 124.71.65.12:13306
Source Database       : mikania_micrantha_kunth

Target Server Type    : MYSQL
Target Server Version : 80024
File Encoding         : 65001

Date: 2023-04-23 20:50:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for popularization_science
-- ----------------------------
DROP TABLE IF EXISTS `popularization_science`;
CREATE TABLE `popularization_science` (
  `id` int NOT NULL COMMENT '科普信息编号',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `url` varchar(255) DEFAULT NULL COMMENT '文章路径',
  `time` date DEFAULT NULL COMMENT '文章发表时间',
  `type` tinyint DEFAULT NULL COMMENT '帖子类型(1:危害;2:政策;3:防护手段;4:植株形态)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='科普信息表';

-- ----------------------------
-- Records of popularization_science
-- ----------------------------

-- ----------------------------
-- Table structure for post_info
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
  `id` int NOT NULL COMMENT '发布信息编号',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  `create_time` timestamp NULL DEFAULT (now()) COMMENT '发布时间',
  `pro_id` int DEFAULT NULL COMMENT '省编号',
  `city_id` int DEFAULT NULL COMMENT '市编号',
  `detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `status` tinyint DEFAULT NULL COMMENT '0:待检测;1:是;2:否',
  `similarity` varchar(255) DEFAULT NULL COMMENT '相似程度',
  `url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='发布信息表';

-- ----------------------------
-- Records of post_info
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wakuwaku2', 'dcf1744f38f5041c827a5ff31467e53a');
INSERT INTO `user` VALUES ('2', 'wakuwaku', 'dcf1744f38f5041c827a5ff31467e53a');
