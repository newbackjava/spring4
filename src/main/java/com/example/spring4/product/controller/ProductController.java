package com.example.spring4.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("product") //contextpath/product
public class ProductController {

    @GetMapping("product") //contextpath/product/product
    public String product() {
        System.out.println("product 화면 요청>>>>>>>>>>>>>>>> ");
        return "product/product";
    }
}
