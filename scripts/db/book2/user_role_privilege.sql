/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL8 : Database - mybatis_test
*********************************************************************
*/


CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatis_test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mybatis_test`;


-- ----------------------------
-- (1) Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
                            `user_password` varchar(50) DEFAULT NULL COMMENT '密码',
                            `user_email` varchar(50) DEFAULT 'test@mybatis.tk' COMMENT '邮箱',
                            `user_info` text COMMENT '简介',
                            `head_img` blob COMMENT '头像',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1035 DEFAULT CHARSET=utf8 COMMENT='用户表';

alter table sys_user comment '用户表';

INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', 'admin@mybatis.tk', '管理员用户', 0x1231231230, '2016-06-07 01:11:12');
INSERT INTO `sys_user` VALUES ('1001', 'test', '123456', 'test@mybatis.tk', '测试用户', 0x1231231230, '2016-06-07 00:00:00');



-- ----------------------------
-- (2) Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
                            `enabled` int(11) DEFAULT NULL COMMENT '有效标志',
                            `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

alter table sys_role comment '角色表';

INSERT INTO `sys_role` VALUES ('1', '管理员', '1', '1', '2016-04-01 17:02:14');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '1', '1', '2016-04-01 17:02:34');



-- ----------------------------
-- (3) Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                                 `privilege_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
                                 `privilege_url` varchar(200) DEFAULT NULL COMMENT '权限URL',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限表';

alter table sys_privilege comment '权限表';

INSERT INTO `sys_privilege` VALUES ('1', '用户管理', '/users');
INSERT INTO `sys_privilege` VALUES ('2', '角色管理', '/roles');
INSERT INTO `sys_privilege` VALUES ('3', '系统日志', '/logs');
INSERT INTO `sys_privilege` VALUES ('4', '人员维护', '/persons');
INSERT INTO `sys_privilege` VALUES ('5', '单位维护', '/companies');



-- ----------------------------
-- (4) Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

alter table sys_privilege comment '用户角色关联表';

INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('1001', '2');



-- ----------------------------
-- (5) Table structure for sys_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege`;
CREATE TABLE `sys_role_privilege` (
                                      `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                      `privilege_id` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

alter table sys_role_privilege comment '角色权限关联表';

INSERT INTO `sys_role_privilege` VALUES ('1', '1');
INSERT INTO `sys_role_privilege` VALUES ('1', '3');
INSERT INTO `sys_role_privilege` VALUES ('1', '2');
INSERT INTO `sys_role_privilege` VALUES ('2', '4');
INSERT INTO `sys_role_privilege` VALUES ('2', '5');