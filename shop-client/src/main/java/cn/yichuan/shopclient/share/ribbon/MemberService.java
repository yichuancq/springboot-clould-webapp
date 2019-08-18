package cn.yichuan.shopclient.share.ribbon;

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
    @HystrixCommand(fallbackMethod = "hiError")
    public String showInfo() {
        logger.info("showInfo");
        //http://my-member-client
        //Object obj = restTemplate.getForObject("http://my-member-client/member/showInfo", String.class);
        Object obj = restTemplate.getForObject(remoteUrl + "/member/showInfo", String.class);
        return (String) obj;
    }

    //当服务不可用时会调用此方法并返回
    public String hiError() {
        return "This is a error.";
    }

}
