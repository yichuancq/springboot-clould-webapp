package cn.yichuan.logisticsclient.share;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用feign进行远程调用API
 */
@FeignClient(name = "my-member-client", fallback = SchedualServiceImpl.class)
public interface ShareMemberService {
    //restful api 调用
    @GetMapping("/member/showInfo")
    String showInfo();
}
