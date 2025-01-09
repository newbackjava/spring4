package com.example.spring4.board.service;

import com.example.spring4.board.dao.BoardMapper;
import com.example.spring4.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
