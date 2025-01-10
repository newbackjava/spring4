package com.example.spring4.cart.dao;

import com.example.spring4.cart.vo.CartDetailsDto;
import com.example.spring4.cart.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CartMapper {

    //장바구니에 이미 들어있는지 확인
    CartVO findProductByMemberId(@Param("productId") long productId,
                                 @Param("memberId") String memberId);

    //장바구니 전체 목록
    List<CartDetailsDto> findCartsByMemberId(@Param("memberId") String memberId);

    //장바구니 넣기
    int createCart(CartVO cartVO);

    //개수 카운트
    int updateCount(CartVO cartVO);

    int deleteProduct(CartVO cartVO);

    int deleteProducts(List<Integer> deleteNoList);
}
