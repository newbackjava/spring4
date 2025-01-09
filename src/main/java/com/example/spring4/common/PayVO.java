package com.example.spring4.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayVO {
    private String applyNum;       // 승인 번호
    private String bankName;       // 은행 이름
    private String buyerAddr;      // 구매자 주소
    private String buyerEmail;     // 구매자 이메일
    private String buyerName;      // 구매자 이름
    private String buyerPostcode;  // 구매자 우편번호
    private String buyerTel;       // 구매자 전화번호
    private String cardName;       // 카드 이름
    private String cardNumber;     // 카드 번호
    private int cardQuota;         // 카드 할부 개월 수
    private String currency;       // 통화
    private String customData;     // 사용자 정의 데이터
    private String impUid;         // 포트원 결제 고유 ID
    private String merchantUid;    // 상점 주문 번호
    private String name;           // 상품명
    private int paidAmount;        // 결제 금액
    private long paidAt;           // 결제 시간 (Unix Timestamp)
    private String payMethod;      // 결제 방식
    private String pgProvider;     // PG사 이름
    private String pgTid;          // PG사 거래 ID
    private String pgType;         // PG사 결제 유형
    private String receiptUrl;     // 영수증 URL
    private String status;         // 결제 상태
    private boolean success;       // 결제 성공 여부
}
