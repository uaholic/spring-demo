package com.gyq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = {"com.gyq.controller"})
public class WebConfig implements WebMvcConfigurer {
    //  配置默认的defaultServlet处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置静态资源处理
        //对静态资源的请求转发到容器缺省的servlet，而不使用DispatcherServlet
        configurer.enable("default");
    }

    /**
     * 这是定义一个ParameterizableViewController调用时立即转到视图的快捷方式。
     * 如果在视图生成响应之前没有Java控制器逻辑要执行，则在静态情况下使用它。
     * <mvc:view-controller path="/" view-name="index"/>
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    /**
     * 静态资源访问控制：假如defaultServlet 没有过滤到接收的静态资源是会报404的
     * 配置addResourceHandlers 不能继承WebMvcConfigurationSupport
     * 因为它拥有子类DelegatingWebMvcConfiguration 已经重写了这个方法。所以运行时，在它方法debug的时候，
     * 发现我写的方法一点用都没 注意！！！
     * 下面相当于
     * <mvc:resources mapping="/resources/**"    location="/statics/" />
     * 支持location="classpath:xxxxx"
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("/WEB-INF/statics/");
    }

    //    配置jsp视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);//设置优先级
        resolver.setCache(false);
        return resolver;
    }

    @Bean //Thymeleaf视图解析器
    public ViewResolver thymeleafViewResolver(ISpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setCache(false);//去掉缓存
        viewResolver.setOrder(1);//设置优先级
        viewResolver.setCacheUnresolved(false);
        return viewResolver;
    }

    @Bean    //模板引擎
    public ISpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.clearTemplateCache();
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {          //模板解析器
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        templateResolver.setOrder(1);
        templateResolver.setCacheable(false);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

}
