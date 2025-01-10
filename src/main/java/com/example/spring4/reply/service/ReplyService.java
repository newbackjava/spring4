package com.example.spring4.reply.service;

import com.example.spring4.board.vo.BoardVO;
import com.example.spring4.reply.dao.ReplyMapper;
import com.example.spring4.reply.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    // 댓글 추가
    public int insertReply(ReplyVO replyVO) {
        return replyMapper.insertReply(replyVO);
    }

    // 특정 게시글의 댓글 가져오기
    public List<ReplyVO> getReplyByBbsNo(int no) {
        return replyMapper.getReplyByBbsNo(no);
    }

    // 댓글 수정
    public int updateReply(ReplyVO replyVO) {
        return replyMapper.updateReply(replyVO);
    }

    // 댓글 삭제
    public int deleteReply(int replyId) {
        return replyMapper.deleteReply(replyId);
    }
}

