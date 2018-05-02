package com.topweshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mongoding
 * @create 2017/4/15.
 * @blog https://mongoding.github.io
 */
@RestController
public class DcController {

    @Autowired
    DcClient dcClient;

    @GetMapping("/say")
    public String dc() {
        return dcClient.consumer();
    }

}
