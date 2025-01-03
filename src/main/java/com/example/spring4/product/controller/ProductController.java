package com.example.spring4.product.controller;

import com.example.spring4.product.vo.ItemVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("product") //contextpath/product
public class ProductController {

    @GetMapping("product") //contextpath/product/product
    public String product() {
        System.out.println("product 화면 요청>>>>>>>>>>>>>>>> ");
        return "redirect:/product/items";
    }

    @GetMapping("items")
    public String getItems(Model model) {
        // 물건 리스트 생성
        List<ItemVO> items = new ArrayList<>();

        items.add(new ItemVO(1, "물건 1", "이것은 첫 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(2,"물건 2", "이것은 두 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(3, "물건 3", "이것은 세 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(4, "물건 4", "이것은 네 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(5, "물건 5", "이것은 다섯 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(6, "물건 6", "이것은 여섯 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(7, "물건 7", "이것은 일곱 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(8, "물건 8", "이것은 여덟 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(9, "물건 9", "이것은 아홉 번째 물건입니다.", "/img/bye.png"));
        items.add(new ItemVO(10, "물건 10", "이것은 열 번째 물건입니다.", "/img/bye.png"));

        // 모델에 리스트 추가
        model.addAttribute("items", items);
        return "product/product"; // Thymeleaf 템플릿 이름
    }

    // 아이템 상세 페이지 요청 처리
    @GetMapping("/read/{id}")
    public String getItemDetail(@PathVariable("id") int id, Model model) {
        // 임시 데이터 - 실제로는 데이터베이스에서 가져와야 합니다.
        ItemVO item = new ItemVO(id, " 물건 " + id, "이것은 상세 설명입니다. ID: " + id, "/img/bye.png");

        // 모델에 상세 정보 추가
        model.addAttribute("itemVO", item);
        return "product/read"; // Thymeleaf 템플릿 이름
    }

}