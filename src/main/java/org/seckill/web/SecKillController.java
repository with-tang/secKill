package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.ExecuteSecKill;
import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillResult;
import org.seckill.entity.SecKill;
import org.seckill.exceptions.RepeatKillException;
import org.seckill.exceptions.SecKillCloseException;
import org.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/seckill")
public class SecKillController
{
	@Autowired
	private SecKillService secKillService;
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model)
	{
		List<SecKill> list=secKillService.getSecKillList();
		model.addAttribute("list", list);
		return "list";
	}
	@RequestMapping(value="/{secKillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("secKillId") Long secKillId,Model model)
	{
		if(null==secKillId)
		{
			return "redirect:/seckill/list";		
		}
		SecKill seckill=secKillService.getById(secKillId);
		if(seckill==null)
		{
			return "foward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";	
	}
	@RequestMapping(value="/{secKillId}/exposer",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SecKillResult<Exposer> exposer(@PathVariable("secKillId") Long secKillId)
	{
		SecKillResult<Exposer> result;
		try
		{

			Exposer exposer=secKillService.exportSecKillUrl(secKillId);			
			 result=new SecKillResult<Exposer>(true,exposer);				 
		}
		catch(Exception ex)
		{
			logger.error(ex.getMessage(),ex);
			result=new SecKillResult<Exposer>(false,ex.getMessage());
			
		}
		return result;
	}
	@RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public  SecKillResult<ExecuteSecKill> execute(@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5,
			@CookieValue(value="killPhone")Long killPhone)
	{
		if(killPhone==null)
		{			
			return new SecKillResult<ExecuteSecKill>(false,"Î´×¢²á!");
		}		
		try
		{
			System.out.println("secKillId=  ¿ªÊ¼Ö´ÐÐÃëÉ±+seckillid killphone md5"+seckillId+","+  killPhone+" ," +md5);
			ExecuteSecKill execute=secKillService.executeSecKillProcedure(seckillId, killPhone, md5);
			System.out.println("executeSecKill(secKillId, killPhone, md5);"+killPhone+"md5:"+md5);
			System.out.println("secKillId="+execute.getSecKillId());
			System.out.println("stateInfo"+execute.getStateInfo());
			return new SecKillResult<ExecuteSecKill>(true,execute);
		}
		catch(RepeatKillException e){
			System.out.println(e);
			ExecuteSecKill execute=secKillService.executeSecKillProcedure(seckillId, killPhone, md5);
			return new SecKillResult<ExecuteSecKill>(true,execute);
		}
		catch(SecKillCloseException e)
		{
			System.out.println(e);
			ExecuteSecKill execute=secKillService.executeSecKillProcedure(seckillId, killPhone, md5);
			return new SecKillResult<ExecuteSecKill>(true,execute);
		}
		catch(Exception e)
		{ System.out.println(e);
			logger.error(e.getMessage(),e);
			ExecuteSecKill execute=secKillService.executeSecKillProcedure(seckillId, killPhone, md5);
			return new SecKillResult<ExecuteSecKill>(true,execute);
		}		
	}

	@RequestMapping(value ="/time/now",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SecKillResult<Long> time(){
		Date date = new Date();
		SecKillResult<Long> skr=  new SecKillResult<Long>(true,date.getTime());
		//System.out.println(skr.getData());;
		return skr;
	}
}

