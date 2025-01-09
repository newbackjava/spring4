package com.example.spring4.cart.controller;

import com.example.spring4.cart.service.CartService;
import com.example.spring4.cart.vo.CartDetailsDto;
import com.example.spring4.cart.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("cart") //contextpath/cart
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("delete")
    @ResponseBody
    public boolean delete(@RequestBody CartVO cartVO, Model model) {
        System.out.println("cartVO = " + cartVO);
        int result = cartService.deleteProduct(cartVO.getNo());
        System.out.println("result = " + result);

        if(result == 1) {
            System.out.println("카트 삭제 성공.");
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("update")
    @ResponseBody
    public boolean update(@RequestBody CartVO cartVO, Model model) {
        System.out.println("cartVO = " + cartVO);
        int result = cartService.updateCount(cartVO);
        System.out.println("result = " + result);

        if(result == 1) {
            System.out.println("카트 개수 수정 성공.");
            return true;
        }else{
            return false;
        }
    }
    
    @PostMapping("create")
    @ResponseBody
    public boolean create(@RequestBody CartVO cartVO, Model model) {
        System.out.println("cartVO = " + cartVO);
        int result = cartService.createCart(cartVO);
        System.out.println("result = " + result);

        if(result == 1) {
            System.out.println("카트 추가 성공.");
            return true;
        }else{
            return false;
        }
    }

    @GetMapping("cart") //contextpath/cart/cart
    public String cart(HttpSession session, Model model) {
        System.out.println("cart 화면 요청>>>>>>>>>>>>>>>> ");
        //로그인한 사람의 카트정보를 다 받아와서 model속성으로 지정
        if(session.getAttribute("id") == null) {
            return "cart/cart";
        }
        String loginId = session.getAttribute("id").toString();
        List<CartDetailsDto> list = cartService.findCartsByMemberId(loginId);
        System.out.println(list);
        if (list.size() > 0) {
            model.addAttribute("carts", list);
        }
        return "cart/cart";
    }
}
