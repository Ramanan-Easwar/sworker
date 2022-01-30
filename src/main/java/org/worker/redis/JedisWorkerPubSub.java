package org.worker.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.worker.fib.Factorial;
import redis.clients.jedis.JedisPubSub;

public class JedisWorkerPubSub extends JedisPubSub {
    Logger logger = LoggerFactory.getLogger(JedisWorkerPubSub.class);

    IRedisService redisService;

    public JedisWorkerPubSub(IRedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
        logger.info("you have a new index to calculate now! {}", message);

        try {
            long indexValue = Long.parseLong(message);
            logger.info("changing to long {}", indexValue);
            //TODO: make it as a bean
            long factorial = Factorial.fact(indexValue);
            logger.info("Write to cache : factorial of {} = {}", indexValue, factorial);
            redisService.hset(String.valueOf(indexValue), String.valueOf(factorial));
        } catch (NumberFormatException e) {
            logger.error("Cannot find the factorial of a string!");
        }
    }
}
