/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : bank

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-12 13:17:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '产品名称',
  `type` varchar(64) DEFAULT NULL COMMENT '产品类型',
  `status` varchar(10) DEFAULT NULL COMMENT '状态 可购买...',
  `channel` varchar(10) DEFAULT NULL COMMENT '发行渠道',
  `risk_level` varchar(10) DEFAULT NULL COMMENT '风险等级',
  `return_rate` decimal(6,0) DEFAULT NULL COMMENT '收益率',
  `start_sale_time` datetime DEFAULT NULL COMMENT '起售日期',
  `end_sale_time` datetime DEFAULT NULL COMMENT '停售日期',
  `start_interest_time` datetime DEFAULT NULL COMMENT '起息日期',
  `description` varchar(255) DEFAULT NULL COMMENT '产品描述',
  `project_id` int(11) DEFAULT NULL COMMENT '产品系列id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '产品系列名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `trade_no` varchar(64) DEFAULT NULL COMMENT '交易编号',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `product_id` int(10) DEFAULT NULL COMMENT '理财产品id',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '交易金额',
  `income` decimal(10,0) DEFAULT NULL COMMENT '预计收益',
  `trade_time` datetime DEFAULT NULL COMMENT '交易时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `account` varchar(255) DEFAULT NULL COMMENT '交易账户',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(128) DEFAULT NULL COMMENT '住址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
