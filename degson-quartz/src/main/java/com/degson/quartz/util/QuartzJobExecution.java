package com.degson.quartz.util;

import org.quartz.JobExecutionContext;
import com.degson.quartz.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author degson
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
