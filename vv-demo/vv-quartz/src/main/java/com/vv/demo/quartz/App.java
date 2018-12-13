package com.vv.demo.quartz;


/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		/*Logger log = LoggerFactory.getLogger(SimpleTriggerExample.class);
		try {
			Scheduler sched = StdSchedulerFactory.getDefaultScheduler();

			Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
			JobDetail job = newJob(SimpleJob.class).withIdentity("job3",
					"group1").build();

			SimpleTrigger trigger = newTrigger()
					.withIdentity("trigger3", "group1")
					.startAt(startTime)
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(10)
									.withRepeatCount(5)).build();

			Date ft = sched.scheduleJob(job, trigger);
			log.info(job.getKey() + " will run at: " + ft + " and repeat: "
					+ trigger.getRepeatCount() + " times, every "
					+ trigger.getRepeatInterval() / 1000 + " seconds");

			log.info("------- Starting Scheduler ----------------");
			sched.start();
			log.info("------- Started Scheduler -----------------");

			log.info("------- Waiting 100 seconds... --------------");

			try {
				// wait 33 seconds to show jobs
				Thread.sleep(100L * 1000L);
				// executing...
			} catch (Exception e) {
				//
			}

			log.info("------- Shutting Down ---------------------");

			sched.shutdown(true);

			log.info("------- Shutdown Complete -----------------");
		} catch (SchedulerException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		//System.out.println("Hello World!");
	}
}
