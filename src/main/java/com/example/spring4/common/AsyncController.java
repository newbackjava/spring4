package com.example.spring4.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class AsyncController {


    @GetMapping("/async")
    public CompletableFuture<String> getAsyncResponse(@RequestParam String param) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // 비동기 작업 (1초 대기)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, " + param + "!";
        });
    }
}
