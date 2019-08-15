package cn.yichuan.memberclient;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableEurekaClient //本服务启动后，会自动注册到 Eureka 服务中
@EnableSwagger2
public class MemberClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberClientApplication.class, args);
    }

    /**
     * 表示支持负载均衡
     *
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * http://localhost:8084/swagger-ui.html#/
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了）
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        ApiInfo apiInfo = new ApiInfo("基于Spring boot2+ 的图书推荐系统EurekaDemo",//大标题
                "member终端EurekaClient",//小标题
                "0.1",//版本
                "NO terms of service",
                "yichuan",//作者
                "我的github链接",//链接显示文字
                "https://github.com/yichuancq"//网站链接
        );
        return apiInfo;
    }

}
