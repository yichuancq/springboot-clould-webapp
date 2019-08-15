package cn.yichuan.shopclient.share;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.url}")
    String remoteUrl = "";

    //使用断路器
    @HystrixCommand(fallbackMethod = "ErrorMethod")
    public String showInfo() {
        logger.info("showInfo");
        return restTemplate.getForObject(remoteUrl + "/member/showInfo", String.class);
    }

    //当服务不可用时会调用此方法并返回
    public String ErrorMethod(String name) {
        return name + ",This is a error.";
    }

}
