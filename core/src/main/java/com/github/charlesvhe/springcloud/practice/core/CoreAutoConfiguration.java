package com.github.charlesvhe.springcloud.practice.core;

import javax.servlet.Filter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.TagMetadataRule;
import org.springframework.cloud.netflix.ribbon.TagFilter;
import org.springframework.cloud.netflix.ribbon.TagInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by charles on 2017/5/25.
 */
@Configuration
@EnableWebMvc
public class CoreAutoConfiguration extends WebMvcConfigurerAdapter {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new TagInterceptor());
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TagMetadataRule getTagMetadataRule() {
        return new TagMetadataRule();
    }

    @Bean
    public Filter TraceFilter() {
        return new TagFilter();
    }
}
