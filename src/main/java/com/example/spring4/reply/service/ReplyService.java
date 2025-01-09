package com.example.spring4.reply.service;

import com.example.spring4.reply.dao.ReplyMapper;
import com.example.spring4.reply.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//멤버변수 final로 선언한 것들을 램에 싱글톤을 생성되어있는 주소를 찾아서
//변수에 집어넣어줘. 서비스 객체 생성시 생성자를 호출할 때 그때 넣어줘.
public class ReplyService {
    private final ReplyMapper replyMapper;
//비지니스 레이어
//    public ReplyService(ReplyMapper replyMapper) {
//        this.replyMapper = replyMapper;
//    }

    //댓글 리스트
    public List<ReplyVO> getReplyByBbsNo(int no){
        return replyMapper.getReplyByBbsNo(no);
    }

    //댓글 추가
    public int insertReply(ReplyVO replyVO){
        return replyMapper.insertReply(replyVO);
    }

    //댓글 수정
    public int updateReply(ReplyVO replyVO){
        return replyMapper.updateReply(replyVO);
    }

    //댓글 삭제
    public int deleteReply(int replyId){
        return replyMapper.deleteReply(replyId);
    }

}
