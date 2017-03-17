/**
 * data.cassandra
 * com.jet.data.web
 * WebConfig.java
 * <p>
 * 2016年9月22日-上午11:53:32
 * 2016济中节能-版权所有
 */
package com.jet.dsm.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.jet.dsm.filter.AuthorizationBeanFilter;

/**
 * @ClassName com.jet.data.web.WebConfig
 * @author wangcj
 * @CreateTime 2016年9月22日 上午11:53:32
 * @version 1.0.0
 * @Description
 */

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/iconAdd").setViewName("iconAdd");
        
        registry.addViewController("/energyMap").setViewName("energyMap");
        registry.addViewController("/energyState").setViewName("energyState");
        registry.addViewController("/projectConsume").setViewName("projectConsume");
        registry.addViewController("/energyAnalyze").setViewName("energyAnalyze");
        registry.addViewController("/airQuality").setViewName("airQuality");
        registry.addViewController("/demandResponse").setViewName("demandResponse");
        registry.addViewController("/trendAnalyze").setViewName("trendAnalyze");
        registry.addViewController("/reportQuery").setViewName("reportQuery");
        registry.addViewController("/weatherNow").setViewName("weatherNow");
        registry.addViewController("/dataMonitor").setViewName("dataMonitor");
        registry.addViewController("/reportManage").setViewName("reportManage");
        registry.addViewController("/alarmManage").setViewName("alarmManage");
        registry.addViewController("/co2Manage").setViewName("co2Manage");
        registry.addViewController("/energyResource").setViewName("energyResource");
        registry.addViewController("/alarmQuery").setViewName("alarmQuery");
        registry.addViewController("/recordManage").setViewName("recordManage");
        registry.addViewController("/baseData").setViewName("baseData");
        registry.addViewController("/systemManage").setViewName("systemManage");
        registry.addViewController("/403").setViewName("403");
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("zh_CN"));
        return cookieLocaleResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"*"});
        viewResolver.setCache(false);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        //engine.addDialect(new LayoutDialect());
        return engine;
    }

    @Bean
    public TemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setOrder(1);
        resolver.setCacheable(false);
        resolver.setTemplateMode("HTML5");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/static/");
    }

}
