package org.worker.redis;

public interface IRedisService {

    void publish(String channel, String message);

    void subscribe(String channel);

    void hset(String key, String value);




}
