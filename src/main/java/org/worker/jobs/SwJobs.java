package org.worker.jobs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.worker.common.helper.RedisConstants;
import org.worker.redis.IRedisService;

public class SwJobs {

    IRedisService redisService; // = (IRedisService) SpringHelper.getBeanFactory().getBean("RedisService");
    Logger logger = LoggerFactory.getLogger(SwJobs.class);

    public SwJobs(IRedisService redisService) {
        this.redisService = redisService;
    }

    public void start() {
        logger.info("Starting worker! ");
        logger.info("Establishing connection to redis channel: {} ", RedisConstants.CHANNEL_NAME);
        redisService.subscribe(RedisConstants.CHANNEL_NAME);
    }
}
