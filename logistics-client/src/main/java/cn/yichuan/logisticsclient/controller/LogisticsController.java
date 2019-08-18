package cn.yichuan.logisticsclient.controller;

import cn.yichuan.logisticsclient.share.SchedualServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logistics")
public class LogisticsController {

    private static final Logger logger = LoggerFactory.getLogger(LogisticsController.class);

    @Autowired
    private SchedualServiceImpl schedualService;

    /**
     * 在Feign中使用断路器
     */
    @ApiOperation("CallFeign")
    @RequestMapping(value = "call/member/CallFeign", method = RequestMethod.GET)
    @GetMapping(path = "call/member/CallFeign")
    public String CallFeign() {
        try {

            return schedualService.showInfo();
        } catch (Exception ex) {
            logger.error("err:{}", ex.getMessage());
            ex.getStackTrace();
        }
        return "err";
    }

}
