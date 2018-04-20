/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : localhost:3306
 Source Schema         : fealty_db

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 17/04/2018 13:43:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_job
-- ----------------------------
DROP TABLE IF EXISTS `auth_job`;
CREATE TABLE `auth_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cron` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tigger_time` datetime NULL DEFAULT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of auth_job
-- ----------------------------
INSERT INTO `auth_job` VALUES (1, 'testJob1', 'testJob', '测试', 'com.fux.auth.quartz.TestJob', '0 0/1 * * * ?', NULL, '00');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` enum('menu','button') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reorder` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES (1, b'1', '系统管理', 0, NULL, 'sys', 'menu', 'sys', 1);
INSERT INTO `auth_permission` VALUES (2, b'1', '菜单管理', 1, NULL, 'permission:list', 'menu', 'permission/list', 2);
INSERT INTO `auth_permission` VALUES (3, b'1', '角色管理', 1, NULL, 'role:list', 'menu', 'role/list', 3);
INSERT INTO `auth_permission` VALUES (4, b'1', '用户管理', 1, NULL, 'user:list', 'menu', 'user/list', 4);
INSERT INTO `auth_permission` VALUES (5, b'1', '新增菜单', 2, NULL, 'permission:add', 'button', 'permission/add', NULL);
INSERT INTO `auth_permission` VALUES (6, b'1', '修改菜单', 2, NULL, 'permission:edit', 'button', 'permission/edit', NULL);
INSERT INTO `auth_permission` VALUES (17, b'1', '定时任务管理', 1, NULL, 'job:list', 'menu', 'job/list', NULL);
INSERT INTO `auth_permission` VALUES (8, b'1', '新增角色', 3, NULL, 'role:add', 'button', 'role/add', NULL);
INSERT INTO `auth_permission` VALUES (9, b'1', '新增用户', 4, NULL, 'user:add', 'button', 'user/add', NULL);
INSERT INTO `auth_permission` VALUES (11, b'1', '删除菜单', 2, NULL, 'permission:delete', 'button', 'permission/delete', NULL);
INSERT INTO `auth_permission` VALUES (12, b'1', '修改角色', 3, NULL, 'role:edit', 'button', 'role/edit', NULL);
INSERT INTO `auth_permission` VALUES (13, b'1', '删除角色', 3, NULL, 'role:delete', 'button', 'role/delete', NULL);
INSERT INTO `auth_permission` VALUES (14, b'1', '修改用户', 4, NULL, 'user:edit', 'button', 'user/edit', NULL);
INSERT INTO `auth_permission` VALUES (15, b'1', '删除用户', 4, NULL, 'user:delete', 'button', 'user/delete', NULL);
INSERT INTO `auth_permission` VALUES (16, b'1', '角色授权', 3, NULL, 'role:grant', 'button', 'role/grant', NULL);
INSERT INTO `auth_permission` VALUES (18, b'1', '新增修改任务', 17, NULL, 'job:add', 'button', 'job/add', NULL);
INSERT INTO `auth_permission` VALUES (19, b'1', '触发任务', 17, NULL, 'job:tigger', 'button', 'job/tigger', NULL);
INSERT INTO `auth_permission` VALUES (21, b'1', '停止任务', 17, NULL, 'job:pause', 'button', 'job/pause', NULL);
INSERT INTO `auth_permission` VALUES (22, b'1', '恢复任务', 17, NULL, 'job:resume', 'menu', 'job/resume', NULL);
INSERT INTO `auth_permission` VALUES (24, b'1', '删除任务', 17, NULL, 'job:delete', 'menu', 'job/delete', NULL);

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES (1, b'1', '管理员', '超级管理员');

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission`  (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_auth_role_permission_auth_permission`(`permission_id`) USING BTREE,
  INDEX `FK_auth_role_permission_auth_role`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` VALUES (12, 1, 93);
INSERT INTO `auth_role_permission` VALUES (8, 1, 92);
INSERT INTO `auth_role_permission` VALUES (3, 1, 91);
INSERT INTO `auth_role_permission` VALUES (11, 1, 90);
INSERT INTO `auth_role_permission` VALUES (6, 1, 89);
INSERT INTO `auth_role_permission` VALUES (5, 1, 88);
INSERT INTO `auth_role_permission` VALUES (2, 1, 87);
INSERT INTO `auth_role_permission` VALUES (24, 1, 86);
INSERT INTO `auth_role_permission` VALUES (22, 1, 85);
INSERT INTO `auth_role_permission` VALUES (21, 1, 84);
INSERT INTO `auth_role_permission` VALUES (19, 1, 83);
INSERT INTO `auth_role_permission` VALUES (18, 1, 82);
INSERT INTO `auth_role_permission` VALUES (17, 1, 81);
INSERT INTO `auth_role_permission` VALUES (1, 1, 80);
INSERT INTO `auth_role_permission` VALUES (13, 1, 94);
INSERT INTO `auth_role_permission` VALUES (16, 1, 95);
INSERT INTO `auth_role_permission` VALUES (4, 1, 96);
INSERT INTO `auth_role_permission` VALUES (9, 1, 97);
INSERT INTO `auth_role_permission` VALUES (14, 1, 98);
INSERT INTO `auth_role_permission` VALUES (15, 1, 99);

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` date NULL DEFAULT NULL,
  `last_login_time` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `update_time` date NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_t1iph3dfc25ukwcl9xemtnojn`(`username`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES (1, '2018-04-11', NULL, '管理员', '1', 1, NULL, 'admin');

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_auth_user_role_auth_user`(`user_id`) USING BTREE,
  INDEX `FK_auth_user_role_auth_role`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
