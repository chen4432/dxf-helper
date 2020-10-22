package com.dxf.common.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

/**
 * @author GuJun
 * @date 2020/9/1
 */
public class ConfigUtil {

    /**
     * 读取classpath目录下指定文件并且解析为Config
     * @param resource classpath目录下的配置文件名，如app.conf等
     * @return 配置类
     */
    public static Config getConfig(String resource) {
        return ConfigFactory.parseResources(resource).resolve();
    }

    /**
     * 读取classpath目录下application.conf文件并解析为Config
     * @return 配置类
     */
    public static Config getConfig() {
        return ConfigFactory.load();
    }

    /**
     * 读取配置信息返回配置类，配置文件：application.conf
     * @param key 配置key
     * @param clazz 配置类Type
     * @param <T> 配置类Type
     * @return 配置类实例
     */
    public static <T> T getConfig(String key, Class<T> clazz) {
        return ConfigBeanFactory.create(getConfig().getConfig(key), clazz);
    }

    /**
     * 读取对应配置文件的配置信息返回配置类
     * @param resource 配置文件
     * @param key 配置key
     * @param clazz 配置类Type
     * @param <T> 配置类Type
     * @return 配置类实例
     */
    public static <T> T getConfig(String resource, String key, Class<T> clazz) {
        return ConfigBeanFactory.create(getConfig(resource).getConfig(key), clazz);
    }

}
