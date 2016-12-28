--���ݿ��ʼ���ű�--
--�������ݿ�
create database seckill;
--ʹ�����ݿ�
use seckill;
--������ɱ����
create table seckill
(
'seckill_id'  bigint not null    comment'��Ʒ���ID',
'name' varchar(120) not null comment '��Ʒ����',
'number' int not null comment '��Ʒ����',
'start_time' timestamp not null comment '��ʼʱ��',
'end_time' timestamp not null comment '����ʱ��',
'create_time' timestamp not null default current_timestamp comment '����ʱ��',
primary key(seckill_id),
key idx_start_time(start_time),
key idx_stop_time(stop_time),
key  idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT charset=utf8 comment='��ɱ����';
--��ʼ������
insert into seckill(name,number,start_time,end_time)values
('1000Ԫ��ɱiphone7',100,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('2000Ԫ��ɱiphone7 plus',200,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('1000Ԫ��ɱ����pro6 plus',100,'2016-12-22 00:00:00','2017-01-01 00:00:00'),
('100Ԫ��ɱ��Ϊmate9',100,'2016-12-25 00:00:00','2017-01-01 00:00:00');

--��ɱ�ɹ���ϸ��
--�û���¼��֤�����Ϣ
create table success_killed(
'seckill_id' bigint not null comment '��Ʒid',
'user_phone' bigint not null comment '�û��ֻ�',
'state' tinyint not null default -1 comment '״̬��ʶ��-1����Ч  0���ɹ� 1���Ѹ���',
'create_time' timestamp  not null comment '����ʱ��',
primary key(seckill_id,userphone),--union key
key  idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT charset=utf8 comment='��ɱ����';


--�������ݿ����̨
mysql:

--�������������⣬�������ִ�гɹ���
create table seckill(seckill_id BIGINT NOT NULL  AUTO_INCREMENT COMMENT '��Ʒ���id',
	name VARCHAR(120) NOT NULL COMMENT '��Ʒ����',
	number INT NOT NULL COMMENT '��Ʒ����',
	start_time Datetime NOT NULL COMMENT '��ʼʱ��',
	end_time DATETIME NOT NULL COMMENT '����ʱ��',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	PRIMARY KEY (seckill_id),
	KEY idx_start_time (start_time),
	KEY idx_end_time (end_time),
	KEY idx_create_time (create_time)
)ENGINE = INNODB AUTO_INCREMENT=1000 DEFAULT charset = utf8 COMMENT = '��ɱ����';

insert into seckill(name,number,start_time,end_time)values
('1000Ԫ��ɱiphone7',100,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('2000Ԫ��ɱiphone7 plus',200,'2016-12-24 00:00:00','2017-01-01 00:00:00'),
('1000Ԫ��ɱ����pro6 plus',100,'2016-12-22 00:00:00','2017-01-01 00:00:00'),
('100Ԫ��ɱ��Ϊmate9',100,'2016-12-25 00:00:00','2017-01-01 00:00:00');


create table success_killed(
seckill_id bigint not null comment '��Ʒid',
user_phone bigint not null comment '�û��ֻ�',
state tinyint not null default -1 comment '״̬��ʶ��-1����Ч  0���ɹ� 1���Ѹ���',
create_time timestamp  not null comment '����ʱ��',
primary key(seckill_id,user_phone),
key  idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT charset=utf8 comment='��ɱ����';
