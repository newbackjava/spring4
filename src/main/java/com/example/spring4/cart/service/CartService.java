package com.example.spring4.cart.service;

import com.example.spring4.cart.dao.CartMapper;
import com.example.spring4.cart.vo.CartDetailsDto;
import com.example.spring4.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    //장바구니에 넣기
    public int createCart(CartVO cartVO){
        //넣기전에 기존에 장바구니에 이미 있는지 확인해야함.
        //memberId, productId로 검색
        CartVO findCartVO = cartMapper.findProductByMemberId(
                cartVO.getProductId(), cartVO.getMemberId());
        //검색결과가 있으면 count+1해서 update처리
        int result = 0;
        if(findCartVO != null){
            findCartVO.setCount(findCartVO.getCount()+1);
            result = cartMapper.updateCount(findCartVO);
        }else {
            //검색결과가 없으면 장바구니에 하나 추가
            result = cartMapper.createCart(cartVO);
            System.out.println("추가 후 cartVO = " + cartVO);
        }
        return result;
    }

    //장바구니 로그인사람기준으로 검색 목록
    public List<CartDetailsDto> findCartsByMemberId(String memberId){
        return cartMapper.findCartsByMemberId(memberId);
    }

    //장바구니 물건 하나 삭제
    public int deleteProduct(long no){
        return cartMapper.deleteProduct(no);
    } //cartId

    //장바구니 여러개 삭제
    public int deleteProducts(List<Long> deleteNoList){
        return cartMapper.deleteProducts(deleteNoList);
    }

    //장바구니 수량 수정
    public int updateCount(CartVO cartVO){
        return cartMapper.updateCount(cartVO);
    }

    CartVO findProductByMemberId(long ProductId,
                                 String memberId){
        return cartMapper.findProductByMemberId(ProductId, memberId);
    }
}
