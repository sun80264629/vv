package com.vv.demo.quartz.listener;

import java.text.SimpleDateFormat;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzTriggerListener implements TriggerListener  {
	private static Logger _log = LoggerFactory.getLogger(QuartzSchedulerListener.class);      
	@Override
	public String getName() {
		// TODO 自动生成的方法存根
		return "QuartzTriggerListener";
	}

	/**
	 * Trigger 被触发
	 */
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggerFired ...");
	}

	/**
	 * 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。
	 * TriggerListener 给了一个选择去否决 Job 的执行。
	 * 假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
	 */
	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO 自动生成的方法存根
		_log.debug("execute vetoJobExecution ...");
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO 自动生成的方法存根
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		_log.warn("execute triggerMisfired ... \n上次时间：" + sdf.format(trigger.getPreviousFireTime())
				//+ "\n上次时间（NextFireTime）：" + sdf.format(trigger.getNextFireTime())
				//+ "\n末次时间（FinalFireTime）：" + trigger.getFinalFireTime() == null ? "-" : sdf.format(trigger.getFinalFireTime())
				//+ "\n开始时间（StartTime）：" + sdf.format(trigger.getStartTime())
				//+ "\n结束时间（EndTime）：" + trigger.getEndTime()== null ? "-" : sdf.format(trigger.getEndTime())
				);
	}

	/**
	 * Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法。
	 * 这不是说这个 Trigger 将不再触发了，而仅仅是当前 Trigger 的触发(并且紧接着的 Job 执行) 结束时。
	 * 这个 Trigger 也许还要在将来触发多次的。
	 */
	/*@Override //版本2.2.1
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggerComplete ...");
	}*/

	@Override	//版本1.8.6
	public void triggerComplete(Trigger arg0, JobExecutionContext arg1, int arg2) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggerComplete ...");
	}

}
