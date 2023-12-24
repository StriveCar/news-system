package com.news.NS.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS常量配置实体类
 *把配置文件中的 key：value 映射到实体类中
 */

@Data
@Component
//批量的将外部的属性配置注入到bean对象的属性中
//prefix为外部的属性的共同前缀，根据外部的属性配置的前缀而定，在配置文件中就可以看到外部的属性配置
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
