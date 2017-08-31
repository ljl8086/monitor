package com.na.monitor.client;

import com.na.monitor.core.NodeData;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MonitorClientAutoConfiguration implements ApplicationContextAware,BeanFactoryPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(MonitorClientAutoConfiguration.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("-------monitor--------");

        String zookeeperUrl = applicationContext.getEnvironment().getProperty("spring.light.zookeeper.url");
        //单位：毫秒
        Integer zookeeperTimeout = applicationContext.getEnvironment().getProperty("spring.light.zookeeper.timeout",Integer.class,30*1000);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ZkClient.class);
        beanDefinitionBuilder.addConstructorArgValue(zookeeperUrl);
        beanDefinitionBuilder.addConstructorArgValue(zookeeperTimeout);
        ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition("zkClient",beanDefinitionBuilder.getBeanDefinition());
    }
}
