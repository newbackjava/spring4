package com.example.spring4.board.service;

import com.example.spring4.board.dao.BoardMapper;
import com.example.spring4.board.vo.BoardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public int insertBoard(BoardVO boardVO) {
        return boardMapper.insertBoard(boardVO);
    }

    public BoardVO getBoardByNo(int no) {
        return boardMapper.selectBoardByNo(no);
    }

    public List<BoardVO> getAllBoards() {
        return boardMapper.selectBoardAll();
    }

    public int updateBoard(BoardVO boardVO) {
        return boardMapper.updateBoard(boardVO);
    }

    public int deleteBoard(int no) {
        return boardMapper.deleteBoard(no);
    }

    public List<BoardVO> getBoardByContent(String find) {
        return boardMapper.getBoardByContent(find);
    }

    public PageInfo<BoardVO> getAllBoardsPage(int pageNum, int pageSize) {
        // PageHelper로 페이징 시작
        PageHelper.startPage(pageNum, pageSize);
        List<BoardVO> list = boardMapper.getAllBoardsPage();
        return new PageInfo<>(list); // PageInfo에 결과를 래핑
    }
}
