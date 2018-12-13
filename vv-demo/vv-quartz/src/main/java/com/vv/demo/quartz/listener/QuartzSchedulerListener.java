package com.vv.demo.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzSchedulerListener implements SchedulerListener {
	private static Logger _log = LoggerFactory.getLogger(QuartzSchedulerListener.class);      
	/*@Override
	public void jobScheduled(Trigger trigger) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobScheduled ...");
	}

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobUnscheduled ...");
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggerFinalized ...");
	}

	*//**
	 * trigger触发器暂停
	 *//*
	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggerPaused ...");
	}

	@Override
	public void triggersPaused(String triggerGroup) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggersPaused ...");
	}

	*//**
	 * trigger触发器恢复
	 *//*
	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggerResumed ...");
	}

	@Override
	public void triggersResumed(String triggerGroup) {
		// TODO 自动生成的方法存根
		_log.debug("execute triggersResumed ...");
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobAdded ...");
	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobDeleted ...");
	}

	@Override
	public void jobPaused(JobKey jobKey) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobPaused ...");
	}

	@Override
	public void jobsPaused(String jobGroup) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobsPaused ...");
	}

	@Override
	public void jobResumed(JobKey jobKey) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobResumed ...");
	}

	@Override
	public void jobsResumed(String jobGroup) {
		// TODO 自动生成的方法存根
		_log.debug("execute jobsResumed ...");
	}


	@Override
	public void schedulerInStandbyMode() {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulerInStandbyMode ...");
	}

	@Override
	public void schedulerStarted() {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulerStarted ...");
	}

	@Override
	public void schedulerStarting() {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulerStarting ...");
	}

	@Override
	public void schedulerShutdown() {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulerShutdown ...");
	}

	@Override
	public void schedulerShuttingdown() {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulerShuttingdown ...");
	}

	@Override
	public void schedulingDataCleared() {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulingDataCleared ...");
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		// TODO 自动生成的方法存根
		_log.debug("execute schedulerError ...");
	}*/

	@Override
	public void jobScheduled(Trigger trigger) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void jobUnscheduled(String triggerName, String triggerGroup) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void triggersPaused(String triggerName, String triggerGroup) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void triggersResumed(String triggerName, String triggerGroup) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void jobDeleted(String jobName, String groupName) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void jobsPaused(String jobName, String jobGroup) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void jobsResumed(String jobName, String jobGroup) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void schedulerInStandbyMode() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void schedulerStarted() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void schedulerShutdown() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void schedulerShuttingdown() {
		// TODO 自动生成的方法存根
		
	}

}
