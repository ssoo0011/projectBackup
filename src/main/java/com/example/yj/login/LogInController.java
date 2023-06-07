package com.example.yj.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
//@RestController
public class LogInController {

    private final LogInService logInService;
    @GetMapping("/login")
    public String logInPage(){
        return "login_form";
    }

    @PostMapping("/login/user")
    @ResponseBody
    public int userConfirm(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session){
        int cnt = 0;
        if(logInService.userLoginConfirm(id, pw)){
            session.setAttribute("loginId", id); // 로그인한 아이디 세션 저장
            cnt = 1;
            return cnt;
        }
        return cnt;
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){//로그아웃
        session.removeAttribute("loginId");
        session.removeAttribute("userType");
        return "redirect:/home/";

    }

}
