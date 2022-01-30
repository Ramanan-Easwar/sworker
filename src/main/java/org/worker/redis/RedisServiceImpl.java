package org.worker.redis;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.worker.common.helper.exception.JedisLocalException;
import org.worker.config.RedisConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

@Getter
@Setter
public class RedisServiceImpl implements IRedisService {

    private RedisConfig redisConfig;
    private Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    public RedisServiceImpl(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }
    //TODO: make the jedis connection a connection pool instead of calling it each time

    @Override
    public void publish(String channel, String message) {
        try (Jedis j = new Jedis(new HostAndPort(redisConfig.getHost(), redisConfig.getPort()))) {
            j.publish(channel, message);
        }
        catch (JedisException e) {
            throw new JedisLocalException(e.getLocalizedMessage());
        }
    }

    @Override
    public void subscribe(String channel) {
        //TODO: make this as a lambda function instead of running it like a scrub
        // basically refer to konotor's sub method that aravinth wrote. for this refer:https://codedestine.com/redis-jedis-pub-sub-java/
        logger.info("Inside the pub sub method!I");
        logger.error("fff");
        try (Jedis j = new Jedis(new HostAndPort(redisConfig.getHost(), redisConfig.getPort()))) {

            JedisWorkerPubSub jedisWorkerPubSub = new JedisWorkerPubSub(this);
            j.subscribe(jedisWorkerPubSub, channel);
        } catch (Exception e) {
            logger.error("Something went wrong! {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void hset(String key_pair, String value) {
        String key = "index:swroker";
        try(Jedis j = new Jedis(new HostAndPort(redisConfig.getHost(), redisConfig.getPort()))) {
            j.hset(key, key_pair, value);
        }
    }
}


