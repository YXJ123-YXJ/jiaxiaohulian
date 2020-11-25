/*
Navicat MySQL Data Transfer

Source Server         : aa
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : jiaxiaohulian

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2020-11-12 16:45:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `a_name` varchar(20) NOT NULL COMMENT '管理员名字',
  `a_pwd` varchar(80) NOT NULL COMMENT '管理员密码',
  `a_salt` varchar(255) DEFAULT NULL COMMENT '随机盐',
  `a_sex` varchar(2) DEFAULT NULL COMMENT '管理员性别',
  `a_addtime` datetime DEFAULT NULL COMMENT '管理员创建日期',
  `a_touxiang` varchar(255) DEFAULT NULL COMMENT '头像',
  `a_phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `a_role` varchar(255) DEFAULT NULL COMMENT '权限，普通管理员，超级管理员',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('aaa', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '男', '2020-10-14 14:14:44', null, '12534535544', '1', '超级管理员');
INSERT INTO `admin` VALUES ('bbb', 'bbb222', null, '女', '2020-10-15 14:15:05', null, '42423432422', '2', '普通管理员');
INSERT INTO `admin` VALUES ('ddd', 'ddd', null, '男', '2020-10-20 12:35:08', null, '1243544564', '3', '普通管理员');
INSERT INTO `admin` VALUES ('ggg', 'ggg', null, '女', '2020-10-20 18:18:30', null, '123211242', '4', '普通管理员');

-- ----------------------------
-- Table structure for `dongtailiulan`
-- ----------------------------
DROP TABLE IF EXISTS `dongtailiulan`;
CREATE TABLE `dongtailiulan` (
  `u_name` varchar(20) NOT NULL COMMENT '用户名（即谁浏览了这条动态）',
  `dt_id` int(11) NOT NULL COMMENT '动态id',
  PRIMARY KEY (`u_name`,`dt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dongtailiulan
-- ----------------------------

-- ----------------------------
-- Table structure for `gonggaochakan`
-- ----------------------------
DROP TABLE IF EXISTS `gonggaochakan`;
CREATE TABLE `gonggaochakan` (
  `u_name` varchar(20) NOT NULL COMMENT '用户（谁查看了这条公告）',
  `tz_id` int(11) NOT NULL COMMENT '通知公告id',
  PRIMARY KEY (`tz_id`,`u_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gonggaochakan
-- ----------------------------

-- ----------------------------
-- Table structure for `liuyan`
-- ----------------------------
DROP TABLE IF EXISTS `liuyan`;
CREATE TABLE `liuyan` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `l_text` text COMMENT '留言内容',
  `u_namefrom` varchar(20) DEFAULT NULL COMMENT '留言人',
  `u_nameto` varchar(20) DEFAULT NULL COMMENT '给谁留的言',
  `l_huifu` text COMMENT '回复内容',
  `l_createtime` datetime DEFAULT NULL COMMENT '留言时间',
  `l_fujian` varchar(255) DEFAULT NULL COMMENT '附件（即文件）',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of liuyan
-- ----------------------------
INSERT INTO `liuyan` VALUES ('1', '我的留言1', '王老师', 'aaa', null, '2020-10-06 18:17:00', 'files/2020-10-06/20201006181700ce34f03307ec458fadcd2149688b57ee.jpg');
INSERT INTO `liuyan` VALUES ('2', '最近好吗', '王老师', 'aaa', 'dffgf', '2020-10-06 19:33:46', 'files/2020-10-06/20201006193346c294a3b728bf4f86964b6941d2da3c17.');
INSERT INTO `liuyan` VALUES ('3', '豆腐干豆腐的', '王老师', 'aaa', '负担和国际化', '2020-10-07 16:41:47', 'files/2020-10-07/20201007164147c1bfdf689294405ca6740c8cc48cdd00.');
INSERT INTO `liuyan` VALUES ('4', '焚枯食淡付款的时候看到', 'aaa', '王老师', '', '2020-10-06 16:45:37', null);
INSERT INTO `liuyan` VALUES ('5', '和客户的反馈很多事', '小明家长', '王老师', '凤凰国际', '2020-10-05 16:46:19', null);
INSERT INTO `liuyan` VALUES ('6', '你好吗', '王老师', '小明家长', null, '2020-10-07 18:32:29', 'files/2020-10-07/20201007183229cb5893b2ba1649e590cdf9786c5f3f2f.');
INSERT INTO `liuyan` VALUES ('7', '测试1111111111111111111测试11111', '王老师', 'aaa', null, '2020-10-08 20:12:41', 'files/2020-10-08/2020100820124028e270709c8f47aba628e25c70e92809.');
INSERT INTO `liuyan` VALUES ('8', 'dfsfs', 'aaa', '王老师', 'gggggdddd', '2020-10-01 20:27:35', null);
INSERT INTO `liuyan` VALUES ('9', '郭德纲大概', 'aaa', '王老师', null, '2020-10-10 20:44:31', 'files/2020-10-10/2020101020443158080e4a9ec44a07842a8a89893ece43.');
INSERT INTO `liuyan` VALUES ('10', '特特他', 'aaa', '王老师', '发过几个环节', '2020-10-05 20:56:31', null);
INSERT INTO `liuyan` VALUES ('11', '花括号肯定会', 'aaa', '王老师', '', '2020-10-08 20:56:59', null);
INSERT INTO `liuyan` VALUES ('12', '缴费收费', 'aaa', '王老师', null, '2020-10-06 20:57:38', null);
INSERT INTO `liuyan` VALUES ('13', '鼓捣鼓捣刚刚发布', '王老师', 'aaa', null, '2020-10-10 21:02:51', 'files/2020-10-10/20201010210250535c7aa7388d4f2381ae5e5a779813ee.');
INSERT INTO `liuyan` VALUES ('14', '对方回复回复后果', '王老师', 'aaa', null, '2020-10-10 21:03:05', 'files/2020-10-10/20201010210304214bede0db5443f49992c9fe36aec0b4.');
INSERT INTO `liuyan` VALUES ('16', '一天', '王老师', 'ret', null, '2020-10-20 21:02:45', '/images/2020-10-20/160319896500026586fb0b91a4e96b78b0d4331bb144b.jpg');
INSERT INTO `liuyan` VALUES ('18', '讽德诵功风格', '王老师', '小红', null, '2020-11-04 13:47:15', null);
INSERT INTO `liuyan` VALUES ('19', '发送到发顺丰', '王老师', '小红', null, null, null);
INSERT INTO `liuyan` VALUES ('20', '海关豆腐干', '王老师', '红家长', null, null, null);
INSERT INTO `liuyan` VALUES ('21', '古典风格', '王老师', '红家长', null, null, null);
INSERT INTO `liuyan` VALUES ('22', '放电饭锅', '王老师', '红老师', null, null, null);
INSERT INTO `liuyan` VALUES ('23', '高第非苟得', '王老师', '红老师', null, null, null);
INSERT INTO `liuyan` VALUES ('24', '古典风格黑胡椒', '王老师', 'aaa', null, '2020-10-31 14:50:07', null);
INSERT INTO `liuyan` VALUES ('25', '股东会非结构化', '王老师', '王家长', '电风扇感受到', '2020-10-31 14:57:41', null);
INSERT INTO `liuyan` VALUES ('26', '所说的非官方湖广会馆', 'aaa', '王老师', null, '2020-11-03 20:02:24', null);
INSERT INTO `liuyan` VALUES ('27', 'hhhhhjjjjjjj', '王老师', '小明', null, '2020-11-12 15:52:29', null);
INSERT INTO `liuyan` VALUES ('28', 'gdgfdgfggggggggg', '小明', '王老师', null, '2020-11-12 15:54:38', null);
INSERT INTO `liuyan` VALUES ('29', '发广告很过分个', '小明', '王老师', null, '2020-11-12 16:13:12', null);
INSERT INTO `liuyan` VALUES ('30', '的规划法规规划和', '王老师', '王家长', null, '2020-11-12 16:17:07', null);
INSERT INTO `liuyan` VALUES ('31', '撒的发生的方法', '王家长', '王老师', null, '2020-11-12 16:18:52', null);

-- ----------------------------
-- Table structure for `pinglun`
-- ----------------------------
DROP TABLE IF EXISTS `pinglun`;
CREATE TABLE `pinglun` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `p_text` text COMMENT '评论内容',
  `p_createtime` datetime DEFAULT NULL COMMENT '评论时间',
  `u_name` varchar(20) DEFAULT NULL COMMENT '评论者',
  `bdt_id` int(11) DEFAULT NULL COMMENT '被评论动态的id',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pinglun
-- ----------------------------
INSERT INTO `pinglun` VALUES ('4', '测试评论二测试评论二测试评论二测试评论二测试评论二', '2020-10-04 17:15:14', '小明', '8');
INSERT INTO `pinglun` VALUES ('9', '', '2020-10-04 19:50:11', '小明', '4');
INSERT INTO `pinglun` VALUES ('10', '很nice', '2020-10-04 19:52:44', '小明', '4');
INSERT INTO `pinglun` VALUES ('11', '测试评论为空', '2020-10-04 19:54:43', '小明', '4');
INSERT INTO `pinglun` VALUES ('14', '删除成功了呀\r\n', '2020-10-06 09:52:40', '王老师', '4');
INSERT INTO `pinglun` VALUES ('15', '测试index', '2020-10-06 11:01:43', '王老师', '4');
INSERT INTO `pinglun` VALUES ('16', '测试index1', '2020-10-06 11:06:14', '王老师', '4');
INSERT INTO `pinglun` VALUES ('17', '测试dongTai1', '2020-10-06 11:06:44', '王老师', '4');
INSERT INTO `pinglun` VALUES ('18', '和华为凤凰网IE回复', '2020-10-07 13:48:40', '王老师', '8');
INSERT INTO `pinglun` VALUES ('19', '大方式认购人', '2020-10-07 15:15:16', '王老师', '8');
INSERT INTO `pinglun` VALUES ('22', 'huy', '2020-10-09 20:45:59', '王老师', '8');
INSERT INTO `pinglun` VALUES ('23', 'huyrt', '2020-10-09 20:46:15', '王老师', '4');
INSERT INTO `pinglun` VALUES ('24', 'English', '2020-10-09 20:47:25', '王老师', '8');
INSERT INTO `pinglun` VALUES ('27', '万变不离其中', '2020-10-09 20:48:33', '王老师', '9');
INSERT INTO `pinglun` VALUES ('32', '这就很nice了', '2020-10-10 19:33:59', '王老师', '4');
INSERT INTO `pinglun` VALUES ('40', 'test', '2020-10-13 18:55:37', '王老师', '8');
INSERT INTO `pinglun` VALUES ('41', 'test11', '2020-10-13 18:56:15', '王老师', '8');
INSERT INTO `pinglun` VALUES ('42', '手机', '2020-10-14 11:00:00', '王老师', '8');
INSERT INTO `pinglun` VALUES ('45', '好风光哈哈', '2020-10-18 18:43:16', '王老师', '32');
INSERT INTO `pinglun` VALUES ('46', '雪山呀', '2020-10-18 18:43:55', '王老师', '22');
INSERT INTO `pinglun` VALUES ('47', '柔柔弱弱', '2020-10-20 20:17:01', '王老师', '32');
INSERT INTO `pinglun` VALUES ('48', '哈哈哈', '2020-10-20 20:17:54', '王老师', '31');
INSERT INTO `pinglun` VALUES ('49', '变量合法吗\n', '2020-10-24 14:24:29', '王老师', '31');
INSERT INTO `pinglun` VALUES ('50', '的发挥地方', '2020-10-24 17:13:25', '王老师', '26');
INSERT INTO `pinglun` VALUES ('51', '的的广泛地', '2020-10-28 19:43:39', '王老师', '31');
INSERT INTO `pinglun` VALUES ('52', '谷多工坊', '2020-10-28 19:43:57', '王老师', '28');
INSERT INTO `pinglun` VALUES ('55', '发放给回复好借好还根据', '2020-10-30 17:26:05', '王老师', '26');
INSERT INTO `pinglun` VALUES ('56', '第三方的规划', '2020-11-03 19:57:30', 'aaa', '43');
INSERT INTO `pinglun` VALUES ('57', 'ddfgh', '2020-11-04 14:33:33', 'aaa', '43');
INSERT INTO `pinglun` VALUES ('58', '地方规范化', '2020-11-05 17:48:45', 'aaa', '43');
INSERT INTO `pinglun` VALUES ('59', 'gdfd ', '2020-11-12 15:43:18', '王老师', '46');
INSERT INTO `pinglun` VALUES ('60', 'hghg', '2020-11-12 15:48:34', '王老师', '32');
INSERT INTO `pinglun` VALUES ('61', 'hfg ', '2020-11-12 15:51:06', '王老师', '47');
INSERT INTO `pinglun` VALUES ('62', '法国队哈哈', '2020-11-12 16:11:43', '小明', '47');
INSERT INTO `pinglun` VALUES ('63', '这是个啥', '2020-11-12 16:18:08', '王家长', '50');

-- ----------------------------
-- Table structure for `qingjia`
-- ----------------------------
DROP TABLE IF EXISTS `qingjia`;
CREATE TABLE `qingjia` (
  `q_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `q_text` text COMMENT '请假原因',
  `us_name` varchar(20) DEFAULT NULL COMMENT '学生姓名',
  `q_uj_flag` tinyint(4) DEFAULT '0' COMMENT '家长审核状态(0审核中，1已准假，2不同意)',
  `q_ut_flag` tinyint(4) DEFAULT '0' COMMENT '辅导员审核状态(0审核中，1已准假，2不同意)',
  `q_ua_flag` tinyint(4) DEFAULT '0' COMMENT '学生处审核状态(0审核中，1已准假，2不同意)',
  `q_days` tinyint(4) DEFAULT NULL COMMENT '请假天数（大于两天为3）',
  `q_timefrom` datetime DEFAULT NULL COMMENT '请假开始时间',
  `q_timeto` datetime DEFAULT NULL COMMENT '请假结束时间',
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qingjia
-- ----------------------------
INSERT INTO `qingjia` VALUES ('1', '不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服不舒服', '小明', '0', '0', '0', '2', '2020-10-13 00:00:00', '2020-10-15 00:00:00');
INSERT INTO `qingjia` VALUES ('2', '难受', '小明', '0', '0', '0', '7', '2020-10-13 17:13:12', '2020-10-22 17:13:24');
INSERT INTO `qingjia` VALUES ('4', 'ghfghfhfgfgf', '小明', '0', '0', '0', '2', '2020-11-12 00:00:00', '2020-11-13 00:00:00');
INSERT INTO `qingjia` VALUES ('5', '发的规划', '小明', '0', '0', '0', '3', '2020-11-12 00:00:00', '2020-11-16 00:00:00');

-- ----------------------------
-- Table structure for `shishengdongtai`
-- ----------------------------
DROP TABLE IF EXISTS `shishengdongtai`;
CREATE TABLE `shishengdongtai` (
  `dt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dt_name` varchar(255) DEFAULT NULL COMMENT '动态名称',
  `dt_text` text COMMENT '动态内容',
  `u_name` varchar(20) DEFAULT NULL COMMENT '发布人',
  `dt_createtime` datetime DEFAULT NULL COMMENT '动态发布时间',
  `dt_tupian` varchar(255) DEFAULT NULL COMMENT '附加图片',
  PRIMARY KEY (`dt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shishengdongtai
-- ----------------------------
INSERT INTO `shishengdongtai` VALUES ('1', '动态1', '动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态', '王老师', '2020-10-01 14:09:41', null);
INSERT INTO `shishengdongtai` VALUES ('2', '动态2', '动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态', '小明', '2020-09-30 14:09:44', null);
INSERT INTO `shishengdongtai` VALUES ('4', '动态4', '动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态', 'aaa', '2020-10-06 14:09:52', null);
INSERT INTO `shishengdongtai` VALUES ('6', '动态6', '动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态', '小明', '2020-09-29 14:09:59', null);
INSERT INTO `shishengdongtai` VALUES ('7', '动态7', '动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态', '王老师', '2020-10-06 14:10:02', null);
INSERT INTO `shishengdongtai` VALUES ('8', '动态8', '动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态', '王老师', '2020-10-13 14:10:06', null);
INSERT INTO `shishengdongtai` VALUES ('9', '测试1', '测试测试测试', '小明', null, null);
INSERT INTO `shishengdongtai` VALUES ('22', 'gdhgf', '十点了', '王老师', '2020-10-10 20:02:27', '/images/2020-10-16/834cf4ebc4f34985ab1502b2b812dc16.jpg');
INSERT INTO `shishengdongtai` VALUES ('24', '打得分为', '郭德纲大概', '王老师', '2020-10-16 16:27:18', '/images/2020-10-16/18f88caabb654b25a22ad06ec2eb026c.jpg');
INSERT INTO `shishengdongtai` VALUES ('26', '股东会挂号费', '防守打法', '王老师', '2020-10-16 16:43:09', '/images/2020-10-16/f55b471d36c14dd3aae48dfbd5df4010.jpg');
INSERT INTO `shishengdongtai` VALUES ('28', '第一个动态', '鼓捣鼓捣', '王老师', '2020-10-16 16:52:17', '/images/2020-10-16/f55b471d36c14dd3aae48dfbd5df4010.jpg');
INSERT INTO `shishengdongtai` VALUES ('31', '干活干活干活干活', '对符合规划法规', '王老师', '2020-10-18 16:43:28', '/images/2020-10-18/16030106081998a32154cc3e44dc8a6658eebbf7f3e3a.jpg');
INSERT INTO `shishengdongtai` VALUES ('32', '测试文件地址移动之后', '测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后测试文件地址移动之后', '王老师', '2020-10-18 18:42:50', '/images/2020-10-18/1603017770215190a2a5ee4bf4be39e04e738148edb3b.jpg');
INSERT INTO `shishengdongtai` VALUES ('33', 'ffhgfh', 'fgfdhdhdhhdh', '小明', '2020-10-31 09:32:37', null);
INSERT INTO `shishengdongtai` VALUES ('41', '鬼地方火锅', '十多个地方', '王老师', '2020-10-31 14:56:33', null);
INSERT INTO `shishengdongtai` VALUES ('42', '测试文件呀', '价格好几个结构化', 'aaa', '2020-11-03 19:47:00', '/images/2020-11-03/16044040206626e6a619c31534c19957fc8083b9d365d.jpg');
INSERT INTO `shishengdongtai` VALUES ('43', '第一个动态', '第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态第一个动态', 'aaa', '2020-11-03 19:57:08', '/images/2020-11-03/1604404628346fea0ba850bb14e5b9f2a4f49806ac6cd.jpg');
INSERT INTO `shishengdongtai` VALUES ('46', '法规和环境', '的换个房间号', 'aaa', '2020-11-05 18:18:45', '/images/2020-11-05/1604571524674f02702c54b8e4f04baf6d7d75a9a4b10.jpg');
INSERT INTO `shishengdongtai` VALUES ('47', '第三个动态', 'gdfgfghffghfg', '王老师', '2020-11-12 15:50:52', '/images/2020-11-12/16051674526438685178d8d724f1b946bfe85ca85823a.jpg');
INSERT INTO `shishengdongtai` VALUES ('48', '第一个动态', 'fghjgjhhjgjghjhg', '小明', '2020-11-12 15:54:01', '/images/2020-11-12/16051676413552298f7cbb55a42238624046e5c5c2e7e.png');
INSERT INTO `shishengdongtai` VALUES ('49', '第三个动态', '发挥更丰富', '小明', '2020-11-12 16:12:11', '/images/2020-11-12/160516873100688bbb4aabcc5477f94b05c7ad892dbc6.jpg');
INSERT INTO `shishengdongtai` VALUES ('50', '鼓捣鼓捣', '发郭德纲的风格', '王老师', '2020-11-12 16:16:09', '/images/2020-11-12/16051689686520e080937f07640cebb835442c425c197.png');

-- ----------------------------
-- Table structure for `system`
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `system_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `xitongjianjie` text COMMENT '系统简介',
  `xitongxieyi` text COMMENT '系统协议',
  `lianxiwomen` text COMMENT '联系我们',
  `guanyuwomen` text COMMENT '关于我们',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system
-- ----------------------------
INSERT INTO `system` VALUES ('1', '本家校互联系统是针对目前学生家校互联的实际需求，从实际工作出发，对过去的学生家校互联系统存在的问题进行分析，结合计算机系统的结构、概念、模型、原理、方法，在计算机各种优势的情况下，采用目前最流行的B/S结构和java中流行的MVC三层设计模式和idea集成环境、MySQL数据库设计并实现的 。本家校互联系统主要包括系统校园新闻模块、师生动态模块、留言交流模块、通知公告模块、请假管理模块、登录注册模块、个人设置和退出模块等多个模块。它帮助学生家校互联实现了信息化、网络化，通过测试，实现了系统设计目标，相比传统的管理模式，本系统合理的利用了学生家校互联数据资源，有效的减少了学生家校互联的经济投入，大大提高了学生家校互联的效率。', '系统通知系统通知系统通知系统通知系统通知系统通知系统通知系统通知155sdfhhh', '联系我们联系我们联系我们联系我们联系我们联系我们联系我们联系我们45587', '关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们关于我们3354');

-- ----------------------------
-- Table structure for `tongzhigonggao`
-- ----------------------------
DROP TABLE IF EXISTS `tongzhigonggao`;
CREATE TABLE `tongzhigonggao` (
  `tz_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tz_name` varchar(255) DEFAULT NULL COMMENT '通知标题',
  `tz_text` text COMMENT '通知公告内容',
  `tz_createtime` datetime DEFAULT NULL COMMENT '公告发布时间',
  `tz_level` varchar(20) DEFAULT NULL COMMENT '通知公告级别(学校1，学院2，系3，班级4)',
  `tz_textfor` text COMMENT '通知抬头显示的“各学院：”之类的',
  `tz_textfrom` text COMMENT '结尾时显示的什么什么办公室、中心',
  PRIMARY KEY (`tz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tongzhigonggao
-- ----------------------------
INSERT INTO `tongzhigonggao` VALUES ('1', '放假通知1', '明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课明天不上课11', '2020-10-06 18:44:06', '3', '软件学院1', '学院办公室1');
INSERT INTO `tongzhigonggao` VALUES ('2', '补课通知', '明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课明天补课', '2020-10-05 18:47:49', '3', '软件工程', '系教学研究中心');
INSERT INTO `tongzhigonggao` VALUES ('3', '紧急通知', '放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，放假了，77', '2020-10-16 19:43:40', '1', '软件学院', '小卖部');
INSERT INTO `tongzhigonggao` VALUES ('4', '元旦了', '元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了元旦了5678666', '2020-10-16 19:43:31', '1', '全校4', '院长办公室');
INSERT INTO `tongzhigonggao` VALUES ('5', '元旦了', '苏打水是否神鼎飞丹砂', '2020-11-12 16:22:56', '2', '软件学院', '小卖部');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_name` varchar(20) NOT NULL COMMENT '用户名',
  `u_pwd` varchar(80) NOT NULL COMMENT '密码',
  `u_salt` varchar(255) DEFAULT NULL COMMENT '随机盐(用于MD5加密后添加的盐)',
  `u_quanxian` tinyint(4) NOT NULL COMMENT '权限（1、老师   2、家长   3、学生）管理员（66（随便写的66不起作用））',
  `u_sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `u_age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `u_dizhi` varchar(255) DEFAULT NULL COMMENT '地址',
  `u_touxiang` varchar(255) DEFAULT NULL COMMENT '头像',
  `u_phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `u_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `u_idcard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `u_addtime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `u_flag` tinyint(4) DEFAULT NULL COMMENT '学生状态（在校1，未在校0）',
  `us_name` varchar(20) DEFAULT NULL COMMENT '家长对应的学生姓名（此字段针对于家长）',
  `ut_name` varchar(20) DEFAULT NULL COMMENT '学生对应的老师姓名，或家长对应的学生的老师姓名（此字段针对于学生和家长）',
  `uj_name` varchar(20) DEFAULT NULL COMMENT '学生对应的家长姓名（自定义学生）',
  `u_shenhe` tinyint(4) DEFAULT NULL COMMENT '用户注册时管理员审核（待审核0，账号正常1，未通过2，账号已冻结3）管理员（111（随便写的，在登录时做管理员与用户的标识））',
  `u_stuno` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生学号（只有学生有）',
  `u_yuanxi` varchar(255) DEFAULT NULL COMMENT '院系',
  `u_clazz` varchar(255) DEFAULT NULL COMMENT '学生班级',
  `u_role` varchar(255) DEFAULT NULL COMMENT '用户角色(加shiro之后）',
  PRIMARY KEY (`u_stuno`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('aaa', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '1', '男', '23', '黑龙江哈尔滨', null, '13339993933', 'aaa@ss.com', '239884388498493933', '2020-10-01 19:05:30', null, '', null, null, '1', '1', '软件学院', null, 'teacher');
INSERT INTO `user` VALUES ('小明', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '3', '男', '21', '黑龙江哈尔滨', null, '1333999393', 'aba@ss.com', null, '2020-10-03 17:07:51', '1', null, '王老师', '王家长', '1', '2', '软件学院', 'r1003', 'student');
INSERT INTO `user` VALUES ('王家长', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '2', '女', '45', '黑龙江哈尔滨', '/images/2020-10-16/1602839253947f0d20cffab1d40e7be443ffa64fcb208.jpg', '1424646534', 'fdsf@163.com', '1312234423423424', '2020-10-13 17:08:49', null, '小明', null, null, '1', '3', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('王老师', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '1', '男', '74', null, '/images/2020-10-16/1602839253947f0d20cffab1d40e7be443ffa64fcb208.jpg', '1234434534', '313@163.com', null, null, null, null, null, null, '1', '4', '软件学院', null, 'teacher');
INSERT INTO `user` VALUES ('小明家长', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '2', '女', '56', null, null, '1234434534', '222@321.com', '42423423423323242', '2020-10-14 14:59:10', null, '小明', null, null, '1', '5', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('ttt', 'ttt', null, '3', null, null, null, null, null, null, null, null, null, null, null, null, '1', '6', null, null, 'student');
INSERT INTO `user` VALUES ('uy', 'tt', null, '3', null, null, null, null, null, null, null, null, null, null, null, null, '1', '7', null, null, 'student');
INSERT INTO `user` VALUES ('trtr', 'te', null, '3', null, null, null, null, null, null, null, null, null, null, null, null, '1', '8', null, null, 'student');
INSERT INTO `user` VALUES ('gd', 'gff', null, '3', null, null, null, null, null, null, null, null, null, null, null, null, '3', '9', null, null, 'student');
INSERT INTO `user` VALUES ('ret', 'rer', null, '2', null, null, null, null, null, null, null, null, null, null, null, null, '3', '11', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('ter', 'gdg', null, '2', null, null, null, null, null, null, null, null, null, null, null, null, '3', '12', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('fs', 'fs', null, '2', null, null, null, null, null, null, null, null, null, null, null, null, '3', '14', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('fd', 'gd', null, '2', null, null, null, null, null, null, null, null, null, null, null, null, '1', '15', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('fds', 'fs', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '0', '16', null, null, 'teacher');
INSERT INTO `user` VALUES ('hfg34', 'fdf', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '1', '18', null, null, 'teacher');
INSERT INTO `user` VALUES ('fs', 'hfgh', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '3', '19', null, null, 'teacher');
INSERT INTO `user` VALUES ('hfg', 'fs', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '1', '20', null, null, 'teacher');
INSERT INTO `user` VALUES ('hhf', 'jgh', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '0', '21', null, null, 'teacher');
INSERT INTO `user` VALUES ('ghfg', 'fh', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '2', '22', null, null, 'teacher');
INSERT INTO `user` VALUES ('fsdf', 'sdf', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '1', '25', null, null, 'teacher');
INSERT INTO `user` VALUES ('fhf', 'hgfh', null, '2', null, null, null, null, null, null, null, null, null, null, null, null, '1', '26', null, null, 'jiazhang');
INSERT INTO `user` VALUES ('aaa11', 'ea470aec026942407bfa85f9be409a9a', 'KIdvsoKB', '3', '男', '12', '第三方第三方', '/images/2020-11-01/1604216626179af5f15591e0e4062a4feb716a3d3e2c3.jpg', '18804625718', 'aaa@12.com', null, '2020-11-01 15:43:46', '0', '', 'trtr', 'sdf', '1', '27', '商学院', 'g1001', 'student');
INSERT INTO `user` VALUES ('bbb1', 'bbb222', null, '2', '男', '34', '第三方第三方', '/images/2020-11-01/1604217575133f7b37124178f4e819fb142a48bbcb827.jpg', '18804625718', 'aaa@12.com', null, '2020-11-01 15:59:35', '0', 'ere', null, '', '1', '29', '0', null, 'jiazhang');
INSERT INTO `user` VALUES ('ccc', '03dd675c2d7c8dbba9b80bfd296c9b96', 'LPsUyA2W', '3', '男', '12', '第三方第三方', '/images/2020-11-10/1604973769519f57214db5bf541c3a429d8b4e1e240b3.jpg', '18804625718', 'aaa@12.com', null, '2020-11-10 10:02:50', '0', '', 'aaa', 'sdf', '1', '30', '软件学院', 'j1001', 'student');
INSERT INTO `user` VALUES ('ddd', '63ce1148d1efe87e0d7f295859702cc5', '^&MolWCl', '3', '男', '23', '第三方第三方', '/images/2020-11-10/16049998326753f1495ff89fe40b9a32e738e9dd305fe.jpg', '18804625718', 'aaa@12.com', null, '2020-11-10 17:17:13', '0', '', 'aaa', '王家长', '0', '31', '商学院', 'g1001', 'student');
INSERT INTO `user` VALUES ('admin', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '66', '男', null, null, null, '156456465765', null, null, '2020-11-11 16:31:00', null, null, null, null, '111', '32', null, null, '超级管理员');
INSERT INTO `user` VALUES ('admin1', '5a2bef5644f1e77bc5ed6f3f81b17b7d', '&6jNN3ZG', '66', '女', null, '', '', '342343534', '', '', '2020-11-12 16:33:24', null, '', '', '', '111', '33', '', '', '普通管理员');
INSERT INTO `user` VALUES ('admin2', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '66', '女', null, null, null, '342343534', null, null, '2020-11-09 16:35:23', null, null, null, null, '111', '34', null, null, '普通管理员');
INSERT INTO `user` VALUES ('admin3', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '66', '男', null, null, null, '156456465765', null, null, '2020-11-11 16:36:15', null, null, null, null, '111', '35', null, null, '普通管理员');
INSERT INTO `user` VALUES ('admin4', '0f3626b0314a0736cef396bd2876df07', 'X0*7ps$#', '66', '男', null, null, null, '156456465', null, null, '2020-11-03 16:37:09', null, null, null, null, '111', '36', null, null, '普通管理员');
INSERT INTO `user` VALUES ('aaa3', '8df2bb443643a708e569ac32f45aba7c', 'seJBVRH2', '66', '男', null, null, null, '4245465564888', null, null, '2020-11-11 19:05:31', '111', null, null, null, '111', '38', null, null, '普通管理员');

-- ----------------------------
-- Table structure for `xinwendianji`
-- ----------------------------
DROP TABLE IF EXISTS `xinwendianji`;
CREATE TABLE `xinwendianji` (
  `u_name` varchar(20) NOT NULL COMMENT '用户名（即谁点击了这条新闻）',
  `x_id` int(11) NOT NULL COMMENT '新闻id',
  PRIMARY KEY (`u_name`,`x_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xinwendianji
-- ----------------------------

-- ----------------------------
-- Table structure for `xuexiaoxinwen`
-- ----------------------------
DROP TABLE IF EXISTS `xuexiaoxinwen`;
CREATE TABLE `xuexiaoxinwen` (
  `x_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `x_name` varchar(255) DEFAULT NULL COMMENT '新闻标题',
  `x_text` text COMMENT '新闻内容',
  `x_type` varchar(20) DEFAULT NULL COMMENT '新闻类别',
  `x_createtime` datetime DEFAULT NULL COMMENT '新闻发表时间',
  `x_tupian` varchar(255) DEFAULT NULL COMMENT '新闻图片',
  PRIMARY KEY (`x_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xuexiaoxinwen
-- ----------------------------
INSERT INTO `xuexiaoxinwen` VALUES ('1', '特朗普夫妇自曝确诊新冠 林更新评论：平安是福吧', ' 新浪娱乐讯 10月2日，美国总统特朗普发文称自己和第一夫人新冠检测呈阳性，引发热议。随后，林更新评论该新闻报道，写道：“46年属狗今年73，平安是福吧”。', '娱乐', '2020-10-02 17:43:56', null);
INSERT INTO `xuexiaoxinwen` VALUES ('2', '杨怡庆与罗仲谦结婚4周年：感谢缘分遇见彼此', '新浪娱乐讯 10月2日，杨怡[微博]晒出与罗仲谦[微博]的接吻照，庆祝结婚4周年纪念日，她写道：“2017年的今天，我们身处布拉格，度过了第一个结婚周年。2020年的今天，你有你的工作，我有我忙碌（带女儿），虽然平淡，但却充实。感谢缘分让我们遇见彼此，结婚周年快乐，相知相识。P.s 不得不提这张照片的幕后功臣，新一代年轻有为的摄影师袁伟豪，谢谢你为我们记录了美好的时刻。”', '搞笑', '2020-10-07 17:45:00', null);
INSERT INTO `xuexiaoxinwen` VALUES ('3', '美国歌手Cardi B', '美国歌手Cardi B在社交平台发了一张中国大妈表情包，就是那张6位大妈围作星状，“姐妹齐心其利断金”的表情包，Cardi B还配文道：“all my brain cells coming together so I can spell ‘Necessary’”。\r\n\r\n　　中国网友们看到也忍俊不禁，称Cardi B是跨服冲浪第一人：“跨服5G冲浪选手！”“哈哈哈哈哈笑死我了，和卡老师拥有同款。”', '新奇', '2020-10-12 17:46:28', null);
INSERT INTO `xuexiaoxinwen` VALUES ('4', '石原里美方官宣结婚 还未同居年内选好日子登记', '特别感谢大家一直以来对我的照顾。\r\n\r\n　　非常不好意思因为个人事务打扰到大家，我要和交往了很久的圈外男性结婚了。\r\n\r\n　　随着日渐加深彼此的了解，我确定了他是那个可以相互分享相互克服所有困难的人。\r\n\r\n　　虽然我还不够成熟，但是从现在开始，希望我们可以一起珍爱彼此所爱之物，一起走过接下来的人生。 \r\n\r\n　　我要对迄今为止一直支持我的每一个人表示由衷的感谢，并且我希望我作为一名演员作为一个成人能进一步的成长。\r\n\r\n　　今后也希望继续得到各位的指导和鞭策。\r\n\r\n　　最后，由衷地为大家的健康和幸福祈祷。', '娱乐', '2020-10-13 17:47:21', null);
INSERT INTO `xuexiaoxinwen` VALUES ('15', '测试新闻', '多个地方', 'gdgd', '2020-10-16 16:52:53', '/images/2020-10-16/f55b471d36c14dd3aae48dfbd5df4010.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('16', '你好', '郭德纲大概', '怪怪的', '2020-10-16 16:58:55', '/images/2020-10-16/18f88caabb654b25a22ad06ec2eb026c.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('17', '会返回', '郭德纲地方', '古典风格', '2020-10-16 17:03:48', '/images/2020-10-16/834cf4ebc4f34985ab1502b2b812dc16.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('18', '方式', '郭德纲好和', '郭德纲', '2020-10-16 17:07:34', '/images/2020-10-16/1602839253947f0d20cffab1d40e7be443ffa64fcb208.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('19', '测试新闻', '测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻测试新闻', '财经', '2020-10-16 17:54:44', '/images/2020-10-16/16028420837550cb8b46f1f46471aa1b5505097fd401f.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('20', '方式', '古典风格', '古典风格', '2020-10-18 16:25:17', '/images/2020-10-18/160300951693045ff8303e97f4192a7a452b2599fb194.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('21', '美国歌手Cardi B', '　　中国网友们看到也忍俊不禁，称Cardi B是跨服冲浪第一人：“跨服5G冲浪选手！”“哈哈哈哈哈笑死我了，和卡老师拥有同款。”', '财经', '2020-10-29 15:13:43', '/images/2020-10-29/1603955623089c3e6ee7138034d949a0d694c87eafc1c.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('22', '杨怡庆与罗仲谦结婚4周年：感谢缘分遇见彼此', '新浪娱乐讯 10月2日，杨怡[微博]晒出与罗仲谦[微博]的接吻照，庆祝结婚4周年纪念日，她写道：“2017年的今天，我们身处布拉格，度过了第一个结婚周年。2020年的今天，你有你的工作，我有我忙碌（带女儿），虽然平淡，但却充实。感谢缘分让我们遇见彼此，结婚周年快乐，相知相识。P.s 不得不提这张照片的幕后功臣，新一代年轻有为的摄影师袁伟豪，谢谢你为我们记录了美好的时刻。”', '搞笑', '2020-10-29 16:57:52', '/images/2020-10-29/16039618717257d02b932220e40e481a552b9c4e9f052.jpg');
INSERT INTO `xuexiaoxinwen` VALUES ('24', '特朗普夫妇自曝确诊新冠 林更新评论：平安是福吧', ' 新浪娱乐讯 10月2日，美国总统特朗普发文称自己和第一夫人新冠检测呈阳性，引发热议。随后，林更新评论该新闻报道，写道：“46年属狗今年73，平安是福吧”', '娱乐', '2020-11-12 16:20:36', '/images/2020-11-12/16051692362382fb5a18af3ce455b9ffe1525ad1ab0b7.jpg');

-- ----------------------------
-- Table structure for `youqinglianjie`
-- ----------------------------
DROP TABLE IF EXISTS `youqinglianjie`;
CREATE TABLE `youqinglianjie` (
  `lj_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `lj_name` varchar(255) DEFAULT NULL COMMENT '链接名',
  `lj_src` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `lj_createtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`lj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of youqinglianjie
-- ----------------------------
INSERT INTO `youqinglianjie` VALUES ('1', '阿里巴巴', 'https://www.alibaba.com/', '2020-10-22 19:37:41');
INSERT INTO `youqinglianjie` VALUES ('4', '腾讯', 'https://www.tencent.com/', '2020-10-22 19:35:47');
INSERT INTO `youqinglianjie` VALUES ('6', '谷歌', 'https://www.google.cn/', '2020-10-22 19:33:52');
INSERT INTO `youqinglianjie` VALUES ('7', '百度', 'http://www.baidu.com/', '2020-10-22 19:34:14');
INSERT INTO `youqinglianjie` VALUES ('8', '新浪', 'https://www.sina.com.cn/', '2020-10-22 19:39:03');

-- ----------------------------
-- Table structure for `yuanxi`
-- ----------------------------
DROP TABLE IF EXISTS `yuanxi`;
CREATE TABLE `yuanxi` (
  `y_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `y_xueyuan` varchar(255) NOT NULL COMMENT '学院',
  `y_xi` varchar(255) DEFAULT NULL COMMENT '系',
  `y_clazz` varchar(255) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`y_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yuanxi
-- ----------------------------
INSERT INTO `yuanxi` VALUES ('1', '软件学院', '软件工程', 'r1001');
INSERT INTO `yuanxi` VALUES ('2', '软件学院', '计算机科学与技术', 'j1001');
INSERT INTO `yuanxi` VALUES ('3', '软件学院', '软件技术', 'rj1001');
INSERT INTO `yuanxi` VALUES ('4', '商学院', '电子商务', 'd1001');
INSERT INTO `yuanxi` VALUES ('5', '商学院', '国际经济学', 'g1001');
INSERT INTO `yuanxi` VALUES ('6', '艺术学院', '平面设计', 'p1001');
INSERT INTO `yuanxi` VALUES ('7', '艺术学院', '室内设计', 's1001');
INSERT INTO `yuanxi` VALUES ('8', '软件学院', '软件工程', 'r1002');
INSERT INTO `yuanxi` VALUES ('9', '软件学院', '软件工程', 'r1003');
INSERT INTO `yuanxi` VALUES ('10', '软件学院', '软件工程', 'r1004');
INSERT INTO `yuanxi` VALUES ('11', '软件学院', '计算机科学与技术', 'j1002');
INSERT INTO `yuanxi` VALUES ('12', '软件学院', '计算机科学与技术', 'j1003');
INSERT INTO `yuanxi` VALUES ('13', '软件学院', '计算机科学与技术', 'j1004');
INSERT INTO `yuanxi` VALUES ('14', '软件学院', '计算机科学与技术', 'j1005');
INSERT INTO `yuanxi` VALUES ('15', '软件学院', '软件技术', 'rj1002');
INSERT INTO `yuanxi` VALUES ('16', '软件学院', '软件技术', 'rj1003');
INSERT INTO `yuanxi` VALUES ('17', '商学院', '电子商务', 'd1002');
INSERT INTO `yuanxi` VALUES ('18', '商学院', '电子商务', 'd1003');
INSERT INTO `yuanxi` VALUES ('19', '商学院', '国际经济学', 'g1002');
INSERT INTO `yuanxi` VALUES ('20', '商学院', '国际经济学', 'g1003');
INSERT INTO `yuanxi` VALUES ('21', '商学院', '国际经济学', 'g1004');
INSERT INTO `yuanxi` VALUES ('22', '艺术学院', '平面设计', 'p1002');
INSERT INTO `yuanxi` VALUES ('23', '艺术学院', '平面设计', 'p1003');
INSERT INTO `yuanxi` VALUES ('24', '艺术学院', '室内设计', 's1002');
INSERT INTO `yuanxi` VALUES ('25', '艺术学院', '室内设计', 's1003');
INSERT INTO `yuanxi` VALUES ('26', '艺术学院', '室内设计', 's1004');
