package com.example.spring4.cart.controller;

import com.example.spring4.cart.vo.CartDetailsDto;
import com.example.spring4.cart.service.CartService;
import com.example.spring4.cart.vo.CartVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Log4j2
@Slf4j
@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("cart")
    public String cart(HttpSession session, Model model) {
        if(session.getAttribute("id") != null) {
            String loginId = session.getAttribute("id").toString();
            List<CartDetailsDto> list = cartService.getCartsByMemberId(loginId);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(list);
            model.addAttribute("carts", list);
            return "cart/cart";
        }else{
            return "cart/cart";
        }
    }

    @Operation(summary = "CREATE PRODUCT", description = "선택한 물건 장바구니에 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "장바구니에 성공적으로 추가됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("create")
    @ResponseBody
    public boolean create(@RequestBody CartVO cartVO){
        System.out.println(cartVO);

        CartVO cartVO2 = cartService.createCart(cartVO);
        if (cartVO2 != null) {
            return true;
        }else {
            return true;
        }
    }

    @Operation(summary = "UPDATE PRODUCT COUNT", description = "선택한 물건 장바구니에서 개수 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "장바구니 수량이 성공적으로 수정됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "404", description = "장바구니 항목을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("update")
    @ResponseBody
    public int update(
            @RequestBody CartVO cartVO) {
        System.out.println(cartVO);
        int result = cartService.updateCount(cartVO);
        System.out.println(result);
        return result;
    }

    @Operation(summary = "DELETE PRODUCT", description = "선택한 물건 장바구니에서 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "장바구니 항목이 성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "장바구니 항목을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("delete")
    @ResponseBody
    public int delete(
            @RequestBody CartVO cartVO) {
        System.out.println(cartVO);
        int result = cartService.deleteProduct(cartVO);
        System.out.println(result);
        return result;
    }

    @Operation(summary = "DELETE PRODUCTS", description = "선택한 물건 리스트 장바구니에서 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "선택한 장바구니 항목들이 성공적으로 삭제됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/deleteProducts")
    @ResponseBody
    public int deleteProducts(@RequestBody List<Integer> deleteNoList) {
        System.out.println("========================================");
        System.out.println(deleteNoList);
        System.out.println("========================================");
        if (deleteNoList != null && !deleteNoList.isEmpty()) {
            return cartService.deleteProducts(deleteNoList);
        }
        return 0;
    }
}
