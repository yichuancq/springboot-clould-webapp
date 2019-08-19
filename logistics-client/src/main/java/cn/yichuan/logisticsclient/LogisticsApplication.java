package cn.yichuan.logisticsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient //本服务启动后，会自动注册到 Eureka 服务中
@EnableFeignClients //启用feign进行远程调用

@EnableSwagger2
@EnableDiscoveryClient
@EnableHystrix
public class LogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class, args);
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
     * http://localhost:8082/swagger-ui.html#/
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("logistics-client API实时接口文档")
                        .description("用于实时查看、测试API")
                        .contact(new Contact("yichuancq", "https://github.com/yichuancq", "1012027293@qq.com"))
                        .version("版本号:1.0")
                        .build())
                .select()
                //API基础扫描路径
                .apis(RequestHandlerSelectors.basePackage("cn.yichuan.shopclient.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
