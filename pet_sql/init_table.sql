use petcage;
create table if not exists merchant (
  id int primary key auto_increment comment '自增主键',
  phone varchar(50) comment '手机号',
  open_id varchar(100) comment '微信open_id',
  pwd varchar(255) comment '密码',
  token varchar(255) comment '登录access token',
  create_time datetime default current_timestamp comment '创建时间',
  update_time datetime comment '更新时间'
) comment '商家表' default charset='utf8'
;

-- 小程序信息：
create table if not exists merchant_app_info (
  id int primary key auto_increment comment '自增主键',
  version varchar(100) comment '小程序版本',
  create_time datetime default current_timestamp comment '创建时间',
  update_time datetime default current_timestamp comment '更新时间'
) comment '小程序信息表' default charset='utf8';

-- 商家宠笼表
create table if not exists merchant_petcage(
  id int primary key auto_increment comment '自增主键',
  phone varchar(50) comment '手机号',
  device_id varchar(250) comment '设备id',
  create_time datetime default current_timestamp comment '创建时间',
  update_time datetime default current_timestamp comment '更新时间'
) comment '商家宠笼表' default charset='utf8';
