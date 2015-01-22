-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` bool default false,
  CONSTRAINT `pk_users` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX `idx_users_username` on `users`(`username`);

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES 
('1', 'admin', 'admin', null, false), 
('2', 'adminuser', 'adminuser', null, false), 
('3', 'user', 'user', null, false);
COMMIT;

-- ----------------------------
--  Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` bool default false,
  CONSTRAINT `pk_permissions` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create unique index `idx_permissions_permission` on `permissions`(`permission`);

-- ----------------------------
--  Records of `permissions`
-- ----------------------------
BEGIN;
INSERT INTO `permissions` VALUES 
('1', 'editUser', null, true), 
('2', 'showUser', null, true), 
('3', 'addUser', null, true), 
('4', 'deleteUser', null, true);
COMMIT;

-- ----------------------------
--  Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` bool default false,
  CONSTRAINT `pk_roles` primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create unique index `idx_roles_role` on `roles`(`role`);
-- ----------------------------
--  Records of `roles`
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES 
('1', 'admin', 'admin user', true), 
('2', 'user', 'user', true);
COMMIT;

-- ----------------------------
--  Table structure for `roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  CONSTRAINT pk_roles_permissions PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `roles_permissions`
-- ----------------------------
BEGIN;
INSERT INTO `roles_permissions` VALUES 
('1', '1'), ('1', '2'), ('1', '3'), 
('1', '4'), ('2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  CONSTRAINT pk_users_roles PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_roles`
-- ----------------------------
BEGIN;
INSERT INTO `user_roles` VALUES 
('1', '1'), 
('1', '2'), 
('2', '2'), 
('3', '1');
COMMIT;


