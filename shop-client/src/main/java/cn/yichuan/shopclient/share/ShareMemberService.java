package cn.yichuan.shopclient.share;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用feign进行远程调用API
 */
@FeignClient(name = "my-member-client")
public interface ShareMemberService {
    //restful api 调用
    @GetMapping("/member/showInfo")
    String showInfo();
}
