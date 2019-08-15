package cn.yichuan.memberclient.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {


    @ApiOperation("member服务端 showInfo")
    @RequestMapping(value = "/showInfo", method = RequestMethod.GET)
    @GetMapping(path = "showInfo")
    public String showInfo() {

        return "member服务端";
    }


}
