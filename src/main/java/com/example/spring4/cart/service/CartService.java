package com.example.spring4.cart.service;

import com.example.spring4.cart.dao.CartMapper;
import com.example.spring4.cart.vo.CartVO;
import com.example.spring4.cart.vo.CartDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public List<CartDetailsDto> getCartsByMemberId(String memberId) {
        return cartMapper.findCartsByMemberId(memberId);
    }


    public CartVO createCart(CartVO cartVO) {
        //저장하기전 이미 장바구니에 있는지 확인
        CartVO findCartVO = cartMapper.findProductByMemberId(cartVO.getProductId(), cartVO.getMemberId());
        System.out.println("------------------------------------------------------------");

        if (findCartVO == null) {
            cartMapper.createCart(cartVO);
        }else{
            findCartVO.setCount(findCartVO.getCount() + 1);
            int result = cartMapper.updateCount(findCartVO);
            if(result == 1){
                findCartVO = cartMapper.findProductByMemberId(cartVO.getProductId(), cartVO.getMemberId());
            }
        }
        return findCartVO;
    }

    public int updateCount(CartVO cartVO) {
        return cartMapper.updateCount(cartVO);
    }

    public int deleteProduct(CartVO cartVO) {
        return cartMapper.deleteProduct(cartVO);
    }

    public int deleteProducts(List<Integer> deleteNoList) {
        return cartMapper.deleteProducts(deleteNoList);
    }
}
