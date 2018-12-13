package com.vv.demo.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.utils.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@PersistJobDataAfterExecution // 保存在JobDataMap传递的参数,当你要一个计数器的时候,详情可参见以下这个例子.
//@DisallowConcurrentExecution // 保证多个任务间不会同时执行.所以在多任务执行时最好加上
public class StateJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(StateJob.class);      

/*	public static final String FAVORITE_COLOR = "favorite color";
	public static final String EXECUTION_COUNT = "count";
*/
	// 这个属性如不是static,那么每次都要实例这个任务类,始终打印为: 1
	private static int _counter = 1;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO 自动生成的方法存根
		
		//JobKey jobKey = context.getJobDetail().getKey();
		Key jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();
		
		for(int i = 0; i< _counter*10000; i++){
			_log.debug(""+i);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
		_log.error("执行时间:  " + sdf.format(new Date()) + " - 任务Key: " + jobKey
				//+ "-错失策略：["+context.getTrigger().getMisfireInstruction()+"] - ColorJob静态变量值: " + _counter
				+ " - 下次时间:  " + sdf.format(context.getTrigger().getNextFireTime())
				+ " - 执行任务数：" + context.getScheduler().getMetaData().getNumberOfJobsExecuted()
				);
		} catch (SchedulerException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if(_counter % 3 == 0){
			 try {
			      Thread.sleep(40L * 1000L);
			    } catch (Exception e) {
			      //
			    }
		}
		
		_counter++;
		
		
	}

}
