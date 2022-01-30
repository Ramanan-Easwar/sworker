package org.example;

import org.worker.common.helper.SpringHelper;
import org.worker.jobs.SwJobs;
import org.worker.redis.IRedisService;
import org.worker.redis.JedisWorkerPubSub;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        IRedisService redisService = (IRedisService) SpringHelper.getBeanFactory().getBean("redisService");
        System.out.println("Worker starting");
//        redisService.subscribe("foo");
        SwJobs swJobs = (SwJobs) SpringHelper.getBeanFactory().getBean("swJobs");
//        SwJobs swJobs = new SwJobs(redisService);
        swJobs.start();

    }
}
