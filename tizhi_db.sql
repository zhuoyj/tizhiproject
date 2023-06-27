/*
Navicat MySQL Data Transfer

Source Server         : new
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : tizhi_db

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2023-04-06 14:24:05
*/
use tizhi_db1;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_actions
-- ----------------------------
DROP TABLE IF EXISTS `tb_actions`;
CREATE TABLE `tb_actions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '测试名称',
  `address` varchar(255) DEFAULT NULL COMMENT '测试地点',
  `times` date DEFAULT NULL COMMENT '测试时间',
  `typeid` int(11) DEFAULT NULL COMMENT '1 体测 2 体育竞赛',
  `status` int(255) DEFAULT NULL COMMENT '0 负责人 1 老师 2 学生',
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `exid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_actions
-- ----------------------------
INSERT INTO `tb_actions` VALUES ('1', '修改后的测试1', '北京海淀区', '2023-03-31', '1', '2', '2', '5', '1633309042124394496');
INSERT INTO `tb_actions` VALUES ('2', '修改后的测试2', '北京海淀区', '2023-03-31', '1', '2', '2', '8', '1633309042124394496');
INSERT INTO `tb_actions` VALUES ('3', '修改后的测试3', '北京海淀区', '2023-03-31', '1', '2', '2', '9', '1633309042124394496');
INSERT INTO `tb_actions` VALUES ('4', '修改后的体育竞赛1', '北京海淀区', '2023-03-25', '2', '2', '2', '5', '1633318988069081088');
INSERT INTO `tb_actions` VALUES ('5', '修改后的体育竞赛2', '北京海淀区', '2023-03-25', '2', '2', '2', '8', '1633318988069081088');
INSERT INTO `tb_actions` VALUES ('6', '修改后的体育竞赛3', '北京海淀区', '2023-03-25', '2', '2', '2', '9', '1633318988069081088');

-- ----------------------------
-- Table structure for tb_action_score
-- ----------------------------
DROP TABLE IF EXISTS `tb_action_score`;
CREATE TABLE `tb_action_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `score` decimal(10,0) DEFAULT NULL COMMENT '分数',
  `videos` varchar(255) DEFAULT NULL COMMENT '视频文件地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_action_score
-- ----------------------------
INSERT INTO `tb_action_score` VALUES ('1', '1', '24', '100', null);
INSERT INTO `tb_action_score` VALUES ('2', '1', '25', '95', null);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', '球类');
INSERT INTO `tb_category` VALUES ('2', '其他');

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL COMMENT '1 器材 2 场地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('3', '篮球', '1', '1');
INSERT INTO `tb_goods` VALUES ('4', '足球', '1', '1');
INSERT INTO `tb_goods` VALUES ('5', '校内体育场', '0', '2');
INSERT INTO `tb_goods` VALUES ('6', '校内足球场', '0', '2');
INSERT INTO `tb_goods` VALUES ('7', '校内篮球场', '0', '2');

-- ----------------------------
-- Table structure for tb_liaotian
-- ----------------------------
DROP TABLE IF EXISTS `tb_liaotian`;
CREATE TABLE `tb_liaotian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `contents` text,
  `times` datetime DEFAULT NULL,
  `touid` int(11) DEFAULT NULL,
  `isread` bit(1) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL COMMENT '1 单聊 2群聊',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_liaotian
-- ----------------------------
INSERT INTO `tb_liaotian` VALUES ('43', '2', '风格反对广泛的', '2023-03-26 19:36:38', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('44', '23', '24545', '2023-03-26 19:36:47', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('45', '24', '563456546', '2023-03-26 19:36:59', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('46', '23', '1231', '2023-03-26 19:54:37', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('47', '23', 'vfhfhgf', '2023-03-26 19:55:39', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('48', '23', '1', '2023-03-26 19:56:17', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('49', '23', '1', '2023-03-26 19:56:18', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('50', '23', '1', '2023-03-26 19:56:20', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('51', '23', '1', '2023-03-26 19:56:22', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('52', '25', '我是1113', '2023-04-06 12:17:10', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('53', '2', '我是教导主任', '2023-04-06 12:18:09', null, '\0', '2');
INSERT INTO `tb_liaotian` VALUES ('54', '3', '我是a01', '2023-04-06 12:24:50', null, '\0', '2');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(155) DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(155) DEFAULT NULL COMMENT '菜单图标',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `isend` bit(1) DEFAULT NULL COMMENT '是否最后一层',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '首页', 'indexView', 'HomeIcon', '0', '100', '');
INSERT INTO `tb_menu` VALUES ('2', '学校负责人管理', 'userView', 'UserIcon', '0', '200', '\0');
INSERT INTO `tb_menu` VALUES ('3', '权限管理', 'roleView', 'GithubIcon', '0', '300', '\0');
INSERT INTO `tb_menu` VALUES ('4', '老师管理', 'teacherView', 'AirplayIcon', '0', '201', '\0');
INSERT INTO `tb_menu` VALUES ('5', '学生管理', 'studentView', 'AwardIcon', '0', '202', '\0');
INSERT INTO `tb_menu` VALUES ('6', '体测管理', 'ticeView', 'GitMergeIcon', '0', '203', '\0');
INSERT INTO `tb_menu` VALUES ('7', '班级管理', 'classView', 'GridIcon', '0', '204', '\0');
INSERT INTO `tb_menu` VALUES ('8', '体育竞赛', 'jingsaiView', 'BarChartIcon', '0', '205', '\0');
INSERT INTO `tb_menu` VALUES ('9', '器材分类管理', 'categories', 'CommandIcon', '0', '101', '\0');
INSERT INTO `tb_menu` VALUES ('10', '器材管理', 'goods', 'CodepenIcon', '0', '102', '\0');
INSERT INTO `tb_menu` VALUES ('11', '器材申请管理', 'orders', 'FileTextIcon', '0', '103', '\0');
INSERT INTO `tb_menu` VALUES ('12', '场地管理', 'places', 'FlagIcon', '0', '104', '\0');
INSERT INTO `tb_menu` VALUES ('13', '场地申请管理', 'placeorder', 'GridIcon', '0', '105', '\0');
INSERT INTO `tb_menu` VALUES ('14', '在线留言板', 'messegs', 'CodesandboxIcon', '0', '400', '\0');

-- ----------------------------
-- Table structure for tb_msg
-- ----------------------------
DROP TABLE IF EXISTS `tb_msg`;
CREATE TABLE `tb_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `times` datetime DEFAULT NULL,
  `isread` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_msg
-- ----------------------------
INSERT INTO `tb_msg` VALUES ('1', '1', '3', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('2', '4', '3', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('3', '4', '18', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('4', '4', '3', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('5', '4', '18', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('6', '4', '3', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('7', '4', '18', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('8', '4', '3', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('9', '4', '18', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('10', '4', '14', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('11', '4', '15', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('12', '4', '16', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('13', '4', '17', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('14', '1', '14', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('15', '1', '15', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('16', '1', '16', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('17', '1', '17', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('18', '4', '14', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('19', '4', '15', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('20', '4', '16', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('21', '4', '17', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('22', '1', '14', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('23', '1', '15', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('24', '1', '16', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('25', '1', '17', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('26', '1', '14', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('27', '1', '15', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('28', '1', '16', '2023-03-08 00:00:00', '');
INSERT INTO `tb_msg` VALUES ('29', '1', '17', '2023-03-08 00:00:00', '');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL COMMENT '1 器材 2 场地',
  `times` varchar(255) DEFAULT NULL COMMENT '时间段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', '3', '99', '3', '23', null, '1', null);
INSERT INTO `tb_order` VALUES ('4', '5', null, '1', '23', null, '2', '2023-03-27 00:00:00,2023-03-28 00:00:00');

-- ----------------------------
-- Table structure for tb_order_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_log`;
CREATE TABLE `tb_order_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL,
  `times` datetime DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_log
-- ----------------------------
INSERT INTO `tb_order_log` VALUES ('1', '1', '2023-03-26 16:45:37', '23', '0');
INSERT INTO `tb_order_log` VALUES ('2', '1', '2023-03-26 17:10:37', '2', '1');
INSERT INTO `tb_order_log` VALUES ('3', '1', '2023-03-26 17:47:52', '23', '3');
INSERT INTO `tb_order_log` VALUES ('5', '3', '2023-03-26 18:54:57', '23', '0');
INSERT INTO `tb_order_log` VALUES ('6', '4', '2023-03-26 18:57:27', '23', '0');
INSERT INTO `tb_order_log` VALUES ('7', '4', '2023-03-26 19:01:01', '2', '1');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员');
INSERT INTO `tb_role` VALUES ('2', '学校负责人');
INSERT INTO `tb_role` VALUES ('3', '老师');
INSERT INTO `tb_role` VALUES ('4', '学生');

-- ----------------------------
-- Table structure for tb_rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `tb_rolemenu`;
CREATE TABLE `tb_rolemenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuid` int(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_rolemenu
-- ----------------------------
INSERT INTO `tb_rolemenu` VALUES ('1', '1', '1');
INSERT INTO `tb_rolemenu` VALUES ('2', '1', '2');
INSERT INTO `tb_rolemenu` VALUES ('8', '2', '1');
INSERT INTO `tb_rolemenu` VALUES ('10', '3', '1');
INSERT INTO `tb_rolemenu` VALUES ('11', '5', '1');
INSERT INTO `tb_rolemenu` VALUES ('12', '4', '1');
INSERT INTO `tb_rolemenu` VALUES ('14', '7', '1');
INSERT INTO `tb_rolemenu` VALUES ('15', '8', '1');
INSERT INTO `tb_rolemenu` VALUES ('18', '9', '1');
INSERT INTO `tb_rolemenu` VALUES ('36', '7', '2');
INSERT INTO `tb_rolemenu` VALUES ('37', '8', '2');
INSERT INTO `tb_rolemenu` VALUES ('38', '4', '2');
INSERT INTO `tb_rolemenu` VALUES ('39', '1', '3');
INSERT INTO `tb_rolemenu` VALUES ('41', '10', '1');
INSERT INTO `tb_rolemenu` VALUES ('42', '8', '3');
INSERT INTO `tb_rolemenu` VALUES ('43', '6', '1');
INSERT INTO `tb_rolemenu` VALUES ('44', '6', '2');
INSERT INTO `tb_rolemenu` VALUES ('45', '6', '3');
INSERT INTO `tb_rolemenu` VALUES ('46', '1', '4');
INSERT INTO `tb_rolemenu` VALUES ('47', '6', '4');
INSERT INTO `tb_rolemenu` VALUES ('48', '8', '4');
INSERT INTO `tb_rolemenu` VALUES ('49', '11', '3');
INSERT INTO `tb_rolemenu` VALUES ('50', '11', '2');
INSERT INTO `tb_rolemenu` VALUES ('51', '12', '1');
INSERT INTO `tb_rolemenu` VALUES ('52', '13', '3');
INSERT INTO `tb_rolemenu` VALUES ('53', '13', '2');
INSERT INTO `tb_rolemenu` VALUES ('54', '14', '2');
INSERT INTO `tb_rolemenu` VALUES ('55', '14', '3');
INSERT INTO `tb_rolemenu` VALUES ('56', '14', '4');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(150) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `headpic` varchar(150) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(150) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `idcardno` bigint(20) DEFAULT NULL COMMENT '身份证',
  `userid` bigint(20) DEFAULT NULL COMMENT '学号',
  `userclassid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0 正常 1 待审核',
  PRIMARY KEY (`id`),
  KEY `role_id` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '123', 'http://localhost:10086/imgs/20230308083138.png', '校友会', '1', '\0', '18', '1101', '河北', null, null, null, '1');
INSERT INTO `tb_user` VALUES ('2', 'u01', '123', 'http://localhost:10086/imgs/20230308083110.jpg', '教导主任', '2', null, null, '15241212711', '山西省晋中市', null, null, null, '1');
INSERT INTO `tb_user` VALUES ('3', 'a01', '123', 'http://localhost:10086/imgs/20230308112754.png', 'a01', '3', null, null, '123', '123', null, null, '5', '1');
INSERT INTO `tb_user` VALUES ('14', null, null, 'http://localhost:10086/imgs/20230308112910.png', '1110', '4', '', '1', '1', '1', '14243121244559800', '1110', '9', '1');
INSERT INTO `tb_user` VALUES ('15', null, null, '', '1111', '4', '\0', '2', '2', '2', '14243121244559811', '1111', '9', '1');
INSERT INTO `tb_user` VALUES ('16', '', '', 'http://localhost:10086/imgs/20230308112910.png', '110', '4', '', '1', '1', '11', '14243121244559700', '110', '5', null);
INSERT INTO `tb_user` VALUES ('17', '', '', '', '111', '4', '\0', '2', '2', '2', '14243121244559711', '111', '5', '1');
INSERT INTO `tb_user` VALUES ('18', 'a02', '123', 'http://localhost:10086/imgs/20230308120216.png', 'a02', '3', null, null, '13211001100', '北京海淀区', null, null, '9', '1');
INSERT INTO `tb_user` VALUES ('22', '测试仪', '123', 'http://localhost:10086/imgs/20230308123606.jpg', '测试仪', '2', null, null, '13211001100', '北京海淀区', null, null, null, '1');
INSERT INTO `tb_user` VALUES ('23', '老师01', '123', null, '老师01', '3', null, null, null, null, null, null, '9', '1');
INSERT INTO `tb_user` VALUES ('24', '1112', '123', '', '1112', '4', '', '11', '1', '1', '14243121244559813', '1112', '9', '1');
INSERT INTO `tb_user` VALUES ('25', '1113', '9822', 'http://localhost:10086/imgs/20230406110137.jpg', '1113', '4', '\0', '12', '2', '2asdsadsad11111111', '14243121244559822', '1113', '9', '1');

-- ----------------------------
-- Table structure for tb_user_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_class`;
CREATE TABLE `tb_user_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_class
-- ----------------------------
INSERT INTO `tb_user_class` VALUES ('5', '三年一班');
INSERT INTO `tb_user_class` VALUES ('8', '三年二班');
INSERT INTO `tb_user_class` VALUES ('9', '三年三班');

-- ----------------------------
-- Table structure for tb_voice
-- ----------------------------
DROP TABLE IF EXISTS `tb_voice`;
CREATE TABLE `tb_voice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `times` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `tb_voice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_voice
-- ----------------------------
INSERT INTO `tb_voice` VALUES ('2', '测试', '<p>123</p>', '2022-09-19 11:06:11', '1');
INSERT INTO `tb_voice` VALUES ('3', '2', '<p>2</p>', '2023-01-25 14:46:26', null);
INSERT INTO `tb_voice` VALUES ('4', '3', '<p>3</p>', '2023-01-25 14:46:29', null);
INSERT INTO `tb_voice` VALUES ('5', '4', '<p>4</p>', '2023-01-25 14:46:32', null);
INSERT INTO `tb_voice` VALUES ('6', '5', '<p>5</p>', '2023-01-25 14:46:34', null);
INSERT INTO `tb_voice` VALUES ('7', '6', '<p>6</p>', '2023-01-25 14:46:37', null);
INSERT INTO `tb_voice` VALUES ('8', '7', '<p>7</p>', '2023-01-25 14:46:40', null);
