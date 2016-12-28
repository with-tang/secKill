--数据库初始化脚本--
--创建数据库
create database seckill;
--使用数据库
use seckill;
--创建秒杀库存表
create table seckill
(
'seckill_id'  bigint not null    comment'商品库存ID',
'name' varchar(120) not null comment '商品名称',
'number' int not null comment '商品数量',
'start_time' timestamp not null comment '开始时间',
'end_time' timestamp not null comment '结束时间',
'create_time' timestamp not null default current_timestamp comment '创建时间',
primary key(seckill_id),
key idx_start_time(start_time),
key idx_stop_time(stop_time),
key  idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT charset=utf8 comment='秒杀库存表';
--初始化数据
insert into seckill(name,number,start_time,end_time)values
('1000元秒杀iphone7',100,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('2000元秒杀iphone7 plus',200,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('1000元秒杀魅族pro6 plus',100,'2016-12-22 00:00:00','2017-01-01 00:00:00'),
('100元秒杀华为mate9',100,'2016-12-25 00:00:00','2017-01-01 00:00:00');

--秒杀成功明细表
--用户登录认证相关信息
create table success_killed(
'seckill_id' bigint not null comment '商品id',
'user_phone' bigint not null comment '用户手机',
'state' tinyint not null default -1 comment '状态标识：-1：无效  0：成功 1：已付款',
'create_time' timestamp  not null comment '创建时间',
primary key(seckill_id,userphone),--union key
key  idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT charset=utf8 comment='秒杀库存表';


--连接数据库控制台
mysql:

--上面的语句有问题，下面的事执行成功的
create table seckill(seckill_id BIGINT NOT NULL  AUTO_INCREMENT COMMENT '商品库存id',
	name VARCHAR(120) NOT NULL COMMENT '商品名称',
	number INT NOT NULL COMMENT '商品数量',
	start_time Datetime NOT NULL COMMENT '开始时间',
	end_time DATETIME NOT NULL COMMENT '结束时间',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (seckill_id),
	KEY idx_start_time (start_time),
	KEY idx_end_time (end_time),
	KEY idx_create_time (create_time)
)ENGINE = INNODB AUTO_INCREMENT=1000 DEFAULT charset = utf8 COMMENT = '秒杀库存表';

insert into seckill(name,number,start_time,end_time)values
('1000元秒杀iphone7',100,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('2000元秒杀iphone7 plus',200,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('1000元秒杀魅族pro6 plus',100,'2016-12-22 00:00:00','2017-01-01 00:00:00'),
('100元秒杀华为mate9',100,'2016-12-25 00:00:00','2017-01-01 00:00:00');


create table success_killed(
seckill_id bigint not null comment '商品id',
user_phone bigint not null comment '用户手机',
state tinyint not null default -1 comment '状态标识：-1：无效  0：成功 1：已付款',
create_time timestamp  not null comment '创建时间',
primary key(seckill_id,user_phone),
key  idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT charset=utf8 comment='秒杀库存表';
