package com.example.spring4.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PayController {

    @GetMapping("/pay/pay")
    public String pay() {
        return "pay/pay";
    }

    @PostMapping("/pay/confirm")
    @ResponseBody
    public String confirm(@RequestBody PayVO payVO) {
        System.out.println(payVO);
        return "ok";
    }
}
