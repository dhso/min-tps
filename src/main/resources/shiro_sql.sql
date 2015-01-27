--权限表
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

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` bool default false,
  CONSTRAINT `pk_permissions` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create unique index `idx_permissions_permission` on `permissions`(`permission`);

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` bool default false,
  CONSTRAINT `pk_roles` primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create unique index `idx_roles_role` on `roles`(`role`);

DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  CONSTRAINT pk_roles_permissions PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  CONSTRAINT pk_users_roles PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `users` VALUES 
('1', 'admin', 'admin', null, false), 
('2', 'adminuser', 'adminuser', null, false), 
('3', 'user', 'user', null, false);
COMMIT;

BEGIN;
INSERT INTO `permissions` VALUES 
('1', 'editUser', null, true), 
('2', 'showUser', null, true), 
('3', 'addUser', null, true), 
('4', 'deleteUser', null, true);
COMMIT;

BEGIN;
INSERT INTO `roles` VALUES 
('1', 'admin', 'admin user', true), 
('2', 'user', 'user', true);
COMMIT;

BEGIN;
INSERT INTO `roles_permissions` VALUES 
('1', '1'), ('1', '2'), ('1', '3'), 
('1', '4'), ('2', '2');
COMMIT;

BEGIN;
INSERT INTO `user_roles` VALUES 
('1', '1'), 
('1', '2'), 
('2', '2'), 
('3', '1');
COMMIT;
--文章表
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `writer` varchar(20) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `pk_articles` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `pk_categories` PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `categories` VALUES 
('1', '优惠活动',now()), 
('2', '婚礼百科',now()), 
('3', '客片展示',now()), 
('4', '操作指南',now());
COMMIT;
BEGIN;
INSERT INTO `articles` VALUES 
('1', '1','优惠活动一','优惠活动一的内容啦啦啦','admin',now()), 
('2', '1','优惠活动二','优惠活动二的内容啦啦啦','admin',now()), 
('3', '2','婚礼百科一','婚礼百科一的内容啦啦啦','admin',now()), 
('4', '4','操作指南一','操作指南一的内容啦啦啦','admin',now()),
('5', '1','优惠活动三','优惠活动一的内容啦啦啦','admin',now()), 
('6', '1','优惠活动四','优惠活动二的内容啦啦啦','admin',now()),
('7', '1','优惠活动五','优惠活动一的内容啦啦啦','admin',now()), 
('8', '1','优惠活动六','优惠活动二的内容啦啦啦','admin',now()),
('9', '1','优惠活动七','优惠活动一的内容啦啦啦','admin',now()), 
('10', '1','优惠活动八','优惠活动二的内容啦啦啦','admin',now()),
('11', '1','优惠活动九','优惠活动一的内容啦啦啦','admin',now()), 
('12', '1','优惠活动十','优惠活动二的内容啦啦啦','admin',now()),
('13', '1','优惠活动十一','优惠活动一的内容啦啦啦','admin',now()), 
('14', '1','优惠活动十二','优惠活动二的内容啦啦啦','admin',now()),
('15', '1','优惠活动十三','优惠活动一的内容啦啦啦','admin',now()), 
('16', '1','优惠活动十四','优惠活动二的内容啦啦啦','admin',now()),
('17', '1','优惠活动十五','优惠活动一的内容啦啦啦','admin',now()), 
('18', '1','优惠活动十六','优惠活动二的内容啦啦啦','admin',now()),
('19', '1','优惠活动十七','优惠活动一的内容啦啦啦','admin',now()), 
('20', '1','优惠活动十八','优惠活动二的内容啦啦啦','admin',now());
COMMIT;