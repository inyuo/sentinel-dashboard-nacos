package com.alibaba.csp.sentinel.dashboard.config.nacos;

import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;


/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Configuration
public class NacosConfig {
    @Value("${nacos.address}")
    private String addr;   //Nacos地址

    @Value("${nacos.namespace}")
    private String namespace;   //Nacos地址
    @Value("${nacos.username}")
    private String username;
    @Value("${nacos.password}")
    private String password;

    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }


    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        //nacos集群地址
        properties.put(PropertyKeyConst.SERVER_ADDR,addr);
        //namespace为空即为public
        properties.put(PropertyKeyConst.NAMESPACE,namespace);
        properties.put(PropertyKeyConst.USERNAME,username);
        properties.put(PropertyKeyConst.PASSWORD,password);
        return ConfigFactory.createConfigService(properties);
    }
}
