package com.example.spring4.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailsDto {
    private long no;
    private String memberId;
    private String memberName;
    private String memberTel;
    private long productId;
    private String productName;
    private int productPrice;
    private int count;
}
