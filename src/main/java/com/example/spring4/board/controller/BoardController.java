package com.example.spring4.board.controller;

import com.example.spring4.board.service.BoardService;
import com.example.spring4.board.vo.BoardVO;
import com.example.spring4.board.vo.BoardVO;
import com.example.spring4.member.vo.MemberVO;
import com.example.spring4.reply.service.ReplyService;
import com.example.spring4.reply.vo.ReplyVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
//@Slf4j
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("board")
    public String board(Model model){
        System.out.println("board 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<BoardVO> list = boardService.getAllBoards();
        model.addAttribute("list", list);
        log.log(Level.WARN, "전체 db목록 가지고 오다가 문제 생김.");
        log.info("Log4j2 with Lombok example.");
        log.error("This is an error log.");
        return "board/board";
    }

    @GetMapping("create")
    public String create(BoardVO boardVO, Model model) {
        System.out.println("create 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return "board/create";
    }

    @PostMapping("create2")
    public String create2(BoardVO boardVO, Model model) {
        System.out.println("create2 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // Java (Spring)
        boardVO.setContent(boardVO.getContent().replace("\n", "<br>"));

        try {
            int result = boardService.insertBoard(boardVO);
            if (result == 1) {
                model.addAttribute("boardVO", boardVO);
                return "board/create2";
            } else {
                return "error/error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }


    @GetMapping("delete")
    public String delete(int no, Model model) {
        System.out.println("delete 처리 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("board no >>>>>>>>>>>>> " + no);
        int result = boardService.deleteBoard(no);
        if (result > 0) {
            //삭제후 전체 목록을 가지는 화면이 필요함.
            //board/board.html에 전체 목록을 구해 전달해야함.
            List<BoardVO> list = boardService.getAllBoards();
            model.addAttribute("list", list);
            return "board/board";
        }else{
            return "error/error";
        }
    }

    @GetMapping("read")
    public String read(int no, Model model){
        System.out.println("read 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        BoardVO boardVO = boardService.getBoardByNo(no);

        // Java (Spring)
        boardVO.setContent(boardVO.getContent().replace("<br>", "\n"));
        List<ReplyVO> list = replyService.getReplyByBbsNo(no);

        if(boardVO != null){
            model.addAttribute("boardVO", boardVO);
            model.addAttribute("list", list);
        }
        return "board/read";
    }

    @GetMapping("find")
    public String read(String find, Model model){
        System.out.println("find 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        List<BoardVO> list = boardService.getBoardByContent(find);
        System.out.println("list.size() >>>>>>>>>>>>>>>>>>>>>>>>>>>> " + list.size());
        model.addAttribute("list", list);
        return "board/board";
    }

    @GetMapping("update")
    public String update(int no, Model model){
        System.out.println("update 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("update id >>>>>>>>>>>>> " + no);

        //no로 검색한 것을 수정화면에 보내자.
        BoardVO boardVO = boardService.getBoardByNo(no);
        // Java (Spring)
        boardVO.setContent(boardVO.getContent().replace("<br>", "\n"));

        model.addAttribute("boardVO", boardVO);
        return "board/update";
    }

    @PostMapping("update2")
    public String update2(BoardVO boardVO, Model model){
        System.out.println("update2 처리 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //스프링에게 클라이언트가 입력한 정보를 받아서 vo객체생성 후
        System.out.println("boardVO >>>>>>>>>>>>> " + boardVO);
        int result = boardService.updateBoard(boardVO);
        if (result > 0) {
            return "board/update2";
        }else{
            return "error/error";
        }
    }
}
