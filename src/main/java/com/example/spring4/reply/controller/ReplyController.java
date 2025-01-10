package com.example.spring4.reply.controller;

import com.example.spring4.reply.service.ReplyService;
import com.example.spring4.board.vo.BoardVO;
import com.example.spring4.reply.vo.ReplyVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Log4j2
@Slf4j
@Controller
@RequestMapping("reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "CREATE REPLY",
            description = "해당 게시물에 대한 댓글 추가")
    @PostMapping("create2")
    @ResponseBody
    public int create2(@RequestBody ReplyVO replyVO, Model model) {
        System.out.println("create2 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("replyVO>>>>>>>>>>>>>>>>>>>>>>>>>> " + replyVO);
        try {
            int result = replyService.insertReply(replyVO);
            if (result == 1) {
                return replyVO.getId();
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    } //create

    @Operation(summary = "UPDATE REPLY",
            description = "해당 게시물에 대한 댓글 수정")
    @PutMapping("update")
    @ResponseBody
    public boolean updateReply(@RequestBody ReplyVO replyVO) {
        // 수정 로직 구현
        System.out.println("-------------- " + replyVO);
        int result = replyService.updateReply(replyVO);
        boolean result2 = false;
        if (result == 1) {
            result2 = true;
        }
        return result2;
    }

    @Operation(summary = "DELETE REPLY",
            description = "해당 게시물에 대한 댓글 삭제")
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public boolean deleteReply(@PathVariable("id") int id) {
        // 삭제 로직 구현
        System.out.println("@DeleteMapping(\"delete/{id}\")-------------- " + id);
        int result = replyService.deleteReply(id);
        boolean result2 = false;
        if (result == 1) {
            result2 = true;
        }
        return result2;
    }
}
