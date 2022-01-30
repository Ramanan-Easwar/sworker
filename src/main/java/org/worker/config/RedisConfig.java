package org.worker.config;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisConfig {

    private String host;
    private int port;
    private String password;

}
