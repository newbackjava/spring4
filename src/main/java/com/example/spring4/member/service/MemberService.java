package com.example.spring4.member.service;

import com.example.spring4.member.dao.MemberMapper;
import com.example.spring4.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service //싱글톤객체 생성 + 비지니스 레이어(층)로 등록
@RequiredArgsConstructor  //@Autowired
//아래 선언된 멤버변수에 객체의 주소를 찾아서 다 넣어줌.
public class MemberService {

    final MemberMapper memberMapper; //200(DI)
    //PasswordEncoder passwordEncoder; //300

    public boolean login(MemberVO memberVO) {
        //전처리하고
        //dao를 찾아서 --> ok
        //dao.메서드 호출
        MemberVO memberVO1 = memberMapper.selectMemberById(memberVO.getId());
        if (memberVO1 != null) {
            if (memberVO1.getPw().equals(memberVO.getPw())) {
                return true; //로그인 성공
            } else {
                return false; //로그인 실패
            }
        }else{
            return false; //로그인 실패
        }
        //후처리
    }//login
    //요청하나당 함수하나
    //처리내용
    // - 전처리하고 나서
    // - dao에 데이터를 넘기면서 db처리해줘 요청
    // - 후처리도 담당

    //db처리할 것은 무조건!! 서비스를 거쳐서 dao를 호출!!
    //front controller --> controller(login())
    //--> service(login()) ---> dao(selectMemberById())
}
