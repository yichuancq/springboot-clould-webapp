package cn.yichuan.shopclient.controller;

import cn.yichuan.shopclient.share.ShareMemberService;
import cn.yichuan.shopclient.share.ribbon.MemberService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    @Value("${remote.url}")
    String remoteUrl = "";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MemberService memberService;

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
     * 用feign进行远程调用API
     *
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

    /**
     * 在ribbon使用断路器
     *
     * @return
     */
    @ApiOperation("CallRibbon")
    @RequestMapping(value = "call/member/CallRibbon", method = RequestMethod.GET)
    @GetMapping(path = "call/member/CallRibbon")
    public String CallRibbon() {
        try {
            System.out.println("CallRibbon...");
            return memberService.showInfo();
        } catch (Exception ex) {
            logger.error("err:{}", ex.getMessage());
            ex.getStackTrace();
        }
        return "err";
    }

    /**
     * 在Feign中使用断路器
     */
    @ApiOperation("CallFeign")
    @RequestMapping(value = "call/member/CallFeign", method = RequestMethod.GET)
    @GetMapping(path = "call/member/CallFeign")
    public String CallFeign() {
        try {

            // shareMemberService.showInfo();
        } catch (Exception ex) {
            logger.error("err:{}", ex.getMessage());
            ex.getStackTrace();
        }
        return "err";
    }

}
