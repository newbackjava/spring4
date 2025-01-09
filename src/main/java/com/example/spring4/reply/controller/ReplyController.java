package com.example.spring4.reply.controller;

import com.example.spring4.reply.service.ReplyService;
import com.example.spring4.reply.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("reply") //contextpath/cart
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("reply") //contextpath/cart/cart
    public String cart() {
        System.out.println("reply 화면 요청>>>>>>>>>>>>>>>> ");
        return "reply/reply"; //templates/reply/reply.html
    }

    @PostMapping("create2")
    @ResponseBody //html이 아닌 것을 응답할때
    public boolean create2(@RequestBody ReplyVO replyVO) {
        System.out.println("-----" + replyVO);
        int result = replyService.insertReply(replyVO);
        if (result == 1) {
            return true; //http의 응답으로 true가 전달
        }else{
            return false;
        }
    }



//reply id
    @DeleteMapping("delete/{id}") //replyvo에 id변수값 설정
    @ResponseBody
    //컨트롤러왔다가 template으로 안넘어가고 데이터만
    // 전달하고자 하는 경우
    public boolean deleteReply(@PathVariable int id) {
        System.out.println("서버로 전달된 replyId " + id);

        int result = replyService.deleteReply(id);
        if (result == 1) {
            return true; //http의 응답으로 true가 전달
        }else{
            return false;
        }
    } //reply detele

    //@RequestBody
    // 자바스크립트에서 json으로 보낸 것을 vo에 넣어줌.
    @PutMapping("update")
    @ResponseBody
    //html이 아닌 return한 데이터를 http에 넣어서 전송해줘.
    public boolean updateReply(@RequestBody ReplyVO replyVO){
        System.out.println(replyVO);
        int result = replyService.updateReply(replyVO);
        if (result == 1) {
            return true; //http의 응답으로 true가 전달
        }else{
            return false;
        }
    } //reply update
}
