package com.example.spring4.board.controller;

import com.example.spring4.board.vo.BoardVO;
import com.example.spring4.reply.service.ReplyService;
import com.example.spring4.reply.vo.ReplyVO;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import com.example.spring4.board.service.BoardService;

//@Log4j2
@Slf4j
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    @Value("${upload.path}")
    private String uploadPath;

    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("/board")
    public String board(
            @RequestParam(defaultValue = "1") int page, // 기본 페이지 번호
            @RequestParam(defaultValue = "10") int size, // 페이지 크기
            Model model) {

        PageInfo<BoardVO> pageInfo = boardService.getAllBoardsPage(page, size);
        model.addAttribute("pageInfo", pageInfo);
        return "board/board";
    }

    /*
    @GetMapping("board")
    public String board(Model model){
        System.out.println("board 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<BoardVO> list = boardService.getAllBoards();
        model.addAttribute("list", list);
        return "board/board";
    }
    */


    @GetMapping("create")
    public String create(BoardVO boardVO, Model model) {
        System.out.println("create 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return "board/create";
    }


    @PostMapping("create2")
    public String createBoard(@ModelAttribute BoardVO boardVO,
                              @RequestParam("file") MultipartFile file,
                              Model model) {

        System.out.println("------------------ " + boardVO);
        try {
            // 파일 업로드 처리
            if (!file.isEmpty()) {

                String originalFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + "_" + originalFileName;
                System.out.println(uploadPath);
                File uploadDir = new File(uploadPath);
                System.out.println(uploadDir.getAbsolutePath());
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                file.transferTo(new File(uploadPath + "/" + savedFileName));
                boardVO.setImg(savedFileName); // DB에 저장할 파일 이름
                System.out.println("------------------ " + boardVO.getImg());
            }

            // 줄바꿈 처리
            boardVO.setContent(boardVO.getContent().replace("\n", "<br>"));

            // 게시글 저장
            int result = boardService.insertBoard(boardVO);
            if (result == 1) {
                model.addAttribute("boardVO", boardVO);
                return "board/create2";
            } else {
                return "error/error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error/error";
        }
    } //create2


    /*
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
    */

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
        } //e;se
    } //delete

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
    }//read

    @GetMapping("find")
    public String find(String find, Model model){
        System.out.println("find 화면 호출 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        List<BoardVO> list = boardService.getBoardByContent(find);
        System.out.println("list.size() >>>>>>>>>>>>>>>>>>>>>>>>>>>> " + list.size());
        model.addAttribute("list", list);
        return "board/board";
    } //find

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
    } //update

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
    }//update2

}
