--秒杀存储过程
DELIMITER $$ --; TO $$
--定义存储过程
CREATE PROCEDURE seckill_execute(
in v_secKillId bigint,
in v_phone bigint,
in v_killTime timestamp,
out r_result int)
begin
	declare v_insertCount int default 0;
	declare v_updateCount int default 0;
	start transaction;
	insert ignore into success_killed(secKillId,user_phone,state)
    	values(v_secKillId,v_phone,0)   ;
    select row_count() into v_insertCount;
   if (v_insertCount<0) then
   	 rollback;
    	set r_result=-2;
   	 elseif (v_insertCount=0) then
  	  rollback;
   	 set r_result=-1;
	else
		update seckill 
		set number=number-1
		where number>0 
		and secKillId=v_secKillId 
		and v_killTime>start_time 
		and v_killTime<end_time;
		select row_count() into v_updateCount;
		 if (v_updateCount<0) then
		    rollback;
		    set r_result=-2;
		    elseif (v_updateCount=0) then
		    rollback;
		    set r_result=-1;
			else
			commit;
			set r_result=1;
		end if;
  end if;
end;


delimeter ;
--初始化
set @r_result=-3;
--调用存储过程
call seckill_execute(1000,15926270104,now(),@r_result);
--获取结果
select @r_result;