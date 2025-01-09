package com.example.spring4.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChartVO {
    private String doing;
    private int time2;
}

