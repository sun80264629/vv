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

public class ConcurrentJob implements Job {
	private static Logger _log = LoggerFactory.getLogger(ConcurrentJob.class);

	public static final String FAVORITE_COLOR = "favorite color";
	public static final String EXECUTION_COUNT = "count";

	// 这个属性如不是static,那么每次都要实例这个任务类,始终打印为: 1
	private static int _counter = 1;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO 自动生成的方法存根

		Key jobKey = context.getJobDetail().getKey();

		JobDataMap data = context.getJobDetail().getJobDataMap();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			_log.error("执行时间:  "
					+ sdf.format(new Date())
					+ " - 【"
					+ jobKey
					// +
					// "-错失策略：["+context.getTrigger().getMisfireInstruction()+"] - ColorJob静态变量值: "
					// + _counter
					+ "】 - 下次时间:  "
					+ sdf.format(context.getTrigger().getNextFireTime())
					+ " - 执行任务数："
					+ context.getScheduler().getMetaData().getNumberOfJobsExecuted()
					+ " - 静态变量：" + _counter);
		} catch (SchedulerException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if (_counter % 3 == 0) {
			try {
				Thread.sleep(120L * 1000L);
			} catch (Exception e) {
				//
			}
		}
		_counter++;
	}

}
