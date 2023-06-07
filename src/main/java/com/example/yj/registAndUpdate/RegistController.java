package com.example.yj.registAndUpdate;

import com.example.yj.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/regist")
@RequiredArgsConstructor

public class RegistController {

    private final RegistService registService;

    @GetMapping("/create") //회원가입 폼으로 가기
    public String regist(){
        return "regist_form";
    }

    @PostMapping("/create") // 유저 회원가입
    public String regist(@ModelAttribute User user,
                         @RequestParam String year,
                         @RequestParam String month,
                         @RequestParam String day){

        registService.RegistUser(user, year, month, day);
        return "redirect:/login";

    }

    //아이디 중복체크
    @RequestMapping(value = "/idCheck", method = { RequestMethod.POST })
    @ResponseBody
    public int idCheck(@RequestParam("id") String id){
        System.out.println(id + "컨트롤러");
        int cnt = registService.idCheck(id);
        return cnt; //ajax에 보내줄 값
    }
}
