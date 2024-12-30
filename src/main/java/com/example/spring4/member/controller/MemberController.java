package com.example.spring4.member.controller;

import com.example.spring4.member.service.MemberService;
import com.example.spring4.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("member") //contextpath/member
@RequiredArgsConstructor //@Autowired
//멤버변수 @Autowired로 주입해줄 것을 모두 찾아서 넣어줌.
public class MemberController {

    //싱글톤은 프로젝트 시작할 때 만들어짐.@Service마찬가지
    //램에 어디에 MemberService싱글톤이 만들어졌는지 찾아야함.
    //@Autowired //램에 이미 만들어진 싱글톤 객체의 주소를 찾아서
            //아래에 있는 변수에 넣어줌.
            //찾을 때는 클래스로 만든 객체가 어디있는지 찾음.
            //클래스 타입으로 만든 객체의 주소를 찾아서
            //아래 변수에 넣어줌.
    final MemberService memberService; //100
//    AService a;
//    BService b;
    @GetMapping("create") //contextpath/member/create
    public String create() {
        System.out.println("create 화면 요청>>>>>>>>>>>>>>>> ");
        return "member/create";
    }

    @GetMapping("member") //contextpath/member/member
    public String member() {
        System.out.println("member 화면 요청>>>>>>>>>>>>>>>> ");
        return "member/member";
    }

    @PostMapping("login")
    public String login(MemberVO memberVO,
                        HttpSession session,
                        Model model) {
        //컨트롤러의 함수를 호출할 때 스프링에 필요한 객체를 달라고 함.
        System.out.println("memberVO>>>>>>>>>>>>> " + memberVO);
        //서비스에 vo 받은 것 주면서 전처리해줘 요청!!!
        boolean result = memberService.login(memberVO);//boolean
        if (result) {
            //로그인되었다라는 정보를 서버에 남겨서
            //로그인 되었는지에 따라 화면 다르게 구성하고 싶은 경우
            //세션, id설정
            session.setAttribute("id", memberVO.getId());
        }else{
            model.addAttribute("result", "로그인실패!!재입력해주세요.!");
        }

        System.out.println("세션값 설정 확인 >> " + session.getAttribute("id"));

        //싱글톤 객체 찾아서 메서드 호출해야할 것 같음.
        //싱글톤 객체는 프로젝트 시작시
        // 어노테이션이나,
        // java, xml, properties를
        //다 스캔해서 싱글톤으로 만든다.
       return "member/member"; //로그인 페이지!
    }
}
