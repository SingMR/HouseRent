/*
Navicat MySQL Data Transfer

Source Server         : poland
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : house

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-12-12 10:59:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `userpwd` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'wym', '12345678');

-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT,
  `house_desc` varchar(255) NOT NULL COMMENT '租房描述',
  `house_model` varchar(20) NOT NULL COMMENT '房屋类型，几室几厅',
  `house_area` varchar(20) NOT NULL COMMENT '房屋面积',
  `house_floor` varchar(20) NOT NULL COMMENT '房屋楼层',
  `house_type` varchar(20) NOT NULL COMMENT '出租方式',
  `house_price` int(10) NOT NULL COMMENT '出租价格',
  `house_address` varchar(100) NOT NULL COMMENT '出租地址',
  `house_image` varchar(1000) NOT NULL COMMENT '房屋简介照片',
  `community_name` varchar(100) NOT NULL COMMENT '小区名字',
  `house_linkman` varchar(11) NOT NULL COMMENT '房屋联系电话',
  `house_oriented` varchar(20) NOT NULL COMMENT '房屋朝向',
  `house_detailes_img` varchar(1000) NOT NULL COMMENT '房屋详细页面展示图片',
  `publisher` varchar(50) NOT NULL DEFAULT '管理员' COMMENT '发布人',
  `publish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_house
-- ----------------------------
INSERT INTO `t_house` VALUES ('30', '碧家全新公寓 东坑店 高颜值配置 24H管家', '1室0厅1卫', '20', '中层/9层', '整租', '600', '东坑城区', 'http://localhost:8090/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '碧桂园豪庭一期', '13813813800', '南北', 'http://localhost:8090/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8090/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8090/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~', 'wym', '2019-12-12 00:28:43');
INSERT INTO `t_house` VALUES ('31', '温馨一房一厅拎包入住，业主急租，看房方便', '1室1厅1卫', '55', '中层/30层', '合租', '1700', '虎门万达中心', 'http://localhost:8090/images/382f528c-59ee-4a4e-bb37-564b88e8489e.jpg', '虎门万达广场', '18818818822', '东西', 'http://localhost:8090/images/92930fad-8fdb-4320-a66e-5189b9c66727.jpg~http://localhost:8090/images/c7f62778-a9b5-4832-b988-452f6ead0d9d.jpg~http://localhost:8090/images/1b33a1f3-a3e5-4f1f-8945-150581a6ee08.jpg~', 'wym', '2019-12-12 00:32:02');
INSERT INTO `t_house` VALUES ('32', '市政中心 豪装修5房2厅 宽敞明亮 温馨舒适体验家的感觉', '5室2厅2卫 ', '283', '低层/16层', '整租', '10000', '南城行政中心', 'http://localhost:8090/images/70b4181c-b6c6-46be-9149-78b4a73b494d.jpg', '凯名轩', '16816816800', '南北', 'http://localhost:8090/images/8ce42414-680f-438d-81c5-00b06514ad1d.jpg~http://localhost:8090/images/447f8686-d8f5-48b3-b2c3-09690abe2ae7.jpg~http://localhost:8090/images/bf6d7ced-f44f-4522-8bbb-0502ee921794.jpg~http://localhost:8090/images/d0f7319c-41b1-485f-a0e5-82d3c09ee5ef.jpg~http://localhost:8090/images/830a5fe1-5ece-4a7e-92c6-fab86410b757.jpg~', 'wym', '2019-12-12 00:36:46');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `h_id` int(11) NOT NULL COMMENT '房屋租赁id',
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `order_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_user` varchar(20) NOT NULL,
  PRIMARY KEY (`o_id`),
  KEY `fk1` (`h_id`),
  KEY `fk2` (`u_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`h_id`) REFERENCES `t_house` (`h_id`),
  CONSTRAINT `fk2` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('11', '30', '3', '2019-12-12 00:46:14', 'wym');
INSERT INTO `t_order` VALUES ('12', '30', '3', '2019-12-12 00:54:44', 'wym');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) NOT NULL,
  `u_password` varchar(100) NOT NULL,
  `u_phone_number` varchar(11) DEFAULT NULL COMMENT '用户注册手机号码，用于找回密码',
  `u_nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `uniq` (`u_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('3', 'wym', '123456', '13556730677', 'wym');
INSERT INTO `t_users` VALUES ('11', 'lzk', 'lzk', '18320495603', '李先生');
