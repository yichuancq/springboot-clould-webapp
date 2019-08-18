package cn.yichuan.zipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
@EnableEurekaClient //本服务启动后，会自动注册到 Eureka 服务中
public class ZipKinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipKinApplication.class, args);
    }

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/getServerPort")
    @ResponseBody
    public String getMember() {

        String url = "http://PROVIDER2/getMsgFromProvider2";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("restTemplate 调用 第二个服务提供者的服务 服务提供者服务 :" + result);
        return result;

    }

}
