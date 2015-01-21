### WebDemo

## create tables
create table `accounts` (
  `id` int primary key auto_increment,
  `username` nchar (20) not null,
  `password` nchar (20) not null
);

create table `permissions` (
  `id` int primary key auto_increment,
  `name` nchar (20) not null,
  `url` nchar (100) not null,
  `parent` int default null,
  `type` nchar (1) not null
);

create table `roles` (
  `id` int primary key auto_increment,
  `name` nchar (20) not null
);

create table `account_role` (
  `id` int primary key auto_increment,
  `account` int not null,
  `role` int not null
);

create table `role_permission` (
  `id` int primary key auto_increment,
  `role` int not null,
  `permission` int not null
);

create table `account_permission` (
  `id` int primary key auto_increment,
  `account` int not null,
  `permission` int not null
);

## Add foreign keys
alter table `account_role`
  add constraint `FK_ACCOUNT_ROLE` foreign key (`account`) references accounts (`id`);
alter table `account_role`
  add constraint `FK_ROLE_ACCOUNT` foreign key (`role`) references `roles` (`id`);

alter table `account_permission`
  add constraint `FK_ACCOUNT_PERMISSION` foreign key (`account`) references accounts (`id`);
alter table `account_permission`
  add constraint `FK_PERMISSION_ACCOUNT` foreign key (`permission`) references `permissions` (`id`);

alter table `role_permission`
  add constraint `FK_ROLE_PERMISSION` foreign key (`role`) references `roles` (`id`);
alter table `role_permission`
  add constraint `FK_PERMISSION_ROLE` foreign key (`permission`) references `permissions` (`id`);

alter table `permissions`
  add constraint `FK_PERMISSION_PARENT` foreign key (`parent`) references `permissions` (`id`);

## Initial data
# Root User
insert into accounts(`username`, `password`) values ('admin', 'admin');
insert into accounts(`username`, `password`) values ('unlimited', 'unlimited');

insert into roles(`name`) values ('admin');
insert into roles(`name`) values ('user');

insert into account_role(`account`, `role`) values (1, 1), (2, 2);

insert into `permissions`(`name`, `url`, `type`) values ('功能管理', '/permissions', '1');
insert into `permissions`(`name`, `url`, `type`, `parent`)
  values ('列表', '/permissions/list', '0', 1), ('添加对话框', '/permissions/add', '0', 1), ('插入', '/permissions/insert', '0', 1),
    ('修改对话框', '/permissions/edit', '0', 1), ('更新', '/permissions/update', '0', 1), ('删除', '/permissions/remove', '0', 1);

insert into `permissions`(`name`, `url`, `type`) values ('角色管理', '/roles', '1');
insert into `permissions`(`name`, `url`, `type`, `parent`)
  values
    ('列表', '/roles/datagrid', '0', 8),
    ('添加对话框', '/roles/add', '0', 8),
    ('插入', '/roles/insert', '0', 8),
    ('修改对话框', '/roles/edit', '0', 8),
    ('更新', '/roles/update', '0', 8),
    ('删除', '/roles/remove', '0', 8);

insert into `permissions`(`name`, `url`, `type`) values ('用户管理', '/accounts', '1');
insert into `permissions`(`name`, `url`, `type`, `parent`)
  values
    ('列表', '/accounts/datagrid', '0', 15),
    ('添加对话框', '/accounts/add', '0', 15),
    ('插入', '/accounts/insert', '0', 15),
    ('修改对话框', '/accounts/edit', '0', 15),
    ('更新', '/accounts/update', '0', 15),
    ('删除', '/accounts/remove', '0', 15);

insert into `permissions`(`name`, `url`, `type`) values ('角色权限管理', '/accounts', '1');
insert into `permissions`(`name`, `url`, `type`, `parent`)
  values
    ('列表', '/accounts/datagrid', '0', 22),
    ('添加对话框', '/accounts/add', '0', 22),
    ('插入', '/accounts/insert', '0', 22),
    ('修改对话框', '/accounts/edit', '0', 22),
    ('更新', '/accounts/update', '0', 22),
    ('删除', '/accounts/remove', '0', 22);

insert into `permissions`(`name`, `url`, `type`) values ('角色权限管理', '/accounts', '1');
insert into `permissions`(`name`, `url`, `type`, `parent`)
  values
    ('列表', '/accounts/datagrid', '0', 29),
    ('添加对话框', '/accounts/add', '0', 29),
    ('插入', '/accounts/insert', '0', 29),
    ('修改对话框', '/accounts/edit', '0', 29),
    ('更新', '/accounts/update', '0', 29),
    ('删除', '/accounts/remove', '0', 29);

DELIMITER //
create procedure `alterRoot`(max int unsigned)
  begin
   declare i int unsigned default 1;
    while i <= max do
      begin
        insert into `account_permission` (`account`, `permission`) values (1, i);
        set i = i + 1;
      end;
    end while;
  end//
DELIMITER ;

call `alterRoot`(35)