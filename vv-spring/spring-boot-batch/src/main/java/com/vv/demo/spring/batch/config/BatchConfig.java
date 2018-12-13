package com.vv.demo.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.FlowStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:批处理任务流程配置
 * @Author: wang'wei
 * @Create: 2018-03-23 9:07
 * @Modify By:
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Bean
    public Job job(){
        return jobs.get("myJob").start(step1()).next(step2()).build();
    }

    @Bean
    protected Step step1(){
        return new FlowStep();

    }

    @Bean
    protected Step step2(){
        return new FlowStep();
    }

}
