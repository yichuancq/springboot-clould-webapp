package cn.yichuan.shopclient.controller;

import cn.yichuan.shopclient.share.ShareMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Value("${remote.url}")
    String remoteUrl = "";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private ShareMemberService shareMemberService;

    /**
     * @return
     */
    @ApiOperation("远程调用member服务的showInfo")
    @RequestMapping(value = "call/member/showInfo", method = RequestMethod.GET)
    @GetMapping(path = "call/member/showInfo")
    public String CallMemberShowInfo() {
        //http://localhost:8084/member/showInfo
        try {
            System.out.println("remoteUrl:" + remoteUrl);
            // 远程调用
            return restTemplate.getForObject(remoteUrl + "/member/showInfo", String.class);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return "err";
    }

    /**
     * @return
     */
    @ApiOperation("远程Feign调用member服务的showInfo")
    @RequestMapping(value = "call/member/showInfo2", method = RequestMethod.GET)
    @GetMapping(path = "call/member/showInfo2")
    public String CallFeignMemberShowInfo() {
        //http://localhost:8084/member/showInfo
        try {
            System.out.println("远程Feign调用member服务的showInfo");
            // 远程调用
            return shareMemberService.showInfo();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return "err";
    }
}
