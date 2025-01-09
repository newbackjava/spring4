package com.example.spring4.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {



    @GetMapping("/map/map")
    public String map() {
        return "map/map";
    }



    @GetMapping("/map/map2")
    @ResponseBody
    public List<MapVO> map2() {
        List<MapVO> list = new ArrayList<MapVO>();
        list.add(new MapVO(37.512806, 127.052612));
        return list;
    }
}
