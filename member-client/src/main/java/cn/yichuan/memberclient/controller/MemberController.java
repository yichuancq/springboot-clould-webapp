package cn.yichuan.memberclient.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @ApiOperation("member服务端 showInfo")
    @RequestMapping(value = "/showInfo", method = RequestMethod.GET)
    @GetMapping(path = "showInfo")
    public String showInfo() {

        logger.info("hello");
        return "member服务端";
    }


}
