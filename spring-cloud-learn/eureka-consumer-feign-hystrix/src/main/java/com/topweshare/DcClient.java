package com.topweshare;

import com.topweshare.api.HelloService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mongoding
 * @create 2017/6/24.
 * @blog https://mongoding.github.io
 */
@FeignClient(name = "user-service-provider", fallback = DcClientFallback.class)
public interface DcClient extends HelloService{

    @GetMapping("/dc")
    String consumer();


}
