package com.example.spring4.reply.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {
    private int id;
    private int oriid; //board
    private String content;
    private String writer;
}
