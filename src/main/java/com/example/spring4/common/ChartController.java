package com.example.spring4.common;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @GetMapping("chart/chart")
    public String index() {
        return "chart/chart";
    }

    @GetMapping("chart/chart2")
    @ResponseBody
    public ResponseEntity<List<ChartVO>> index2() {
        List<ChartVO> list = List.of(
                new ChartVO("일하기", 11),
                new ChartVO("먹기", 2),
                new ChartVO("컴퓨터", 2),
                new ChartVO("TV보기", 2),
                new ChartVO("잠자기", 7)
        );
        return ResponseEntity.ok(list); // 올바른 방식으로 ResponseEntity 반환
    }
}
