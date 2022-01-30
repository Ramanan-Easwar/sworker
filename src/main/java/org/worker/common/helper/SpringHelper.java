package org.worker.common.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public enum SpringHelper {
    INSTANCE;

    private ApplicationContext context;
    Logger logger = LoggerFactory.getLogger(SpringHelper.class);

    SpringHelper() {
        System.out.println("hey");
        logger.debug("INIT SPRING HELPER!!!!");
        context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        logger.warn("finished initialising !!!!");
    }

    public static BeanFactory getBeanFactory() {
        System.out.println("hello!");
        if (INSTANCE.context == null) {
            throw new RuntimeException("Spring not init!");
        }
        return INSTANCE.context;
    }

}
