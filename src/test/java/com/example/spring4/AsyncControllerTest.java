package com.example.spring4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AsyncControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAsyncResponse() throws Exception {
        mockMvc.perform(get("/api/async")
                        .param("param", "world")) // 비동기 요청 파라미터
                .andExpect(status().isOk()) // HTTP 200 상태 코드 확인
                .andExpect(content().string("Hello, world!")); // 응답 내용 확인
    }
}