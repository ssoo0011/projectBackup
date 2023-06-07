package com.example.yj.mySchedule;

import com.example.yj.entity.MySchedule;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MyScheduleController {
    private final MyScheduleService myScheduleService;


    @PostMapping("/makeSchedule")
    public String makeSchedule(int city, String state, Date startDate,
                               @RequestParam(value = "contentTypeId" , required = false, defaultValue = "12") int contentTypeId, Model model)throws URISyntaxException, JsonProcessingException {
        //기본 콘텐츠타입 12
        List<Map<String, Object>> itemMap = myScheduleService.getData(city, state, contentTypeId, 1000);
        model.addAttribute("itemMap", itemMap);
        model.addAttribute("city", city);
        model.addAttribute("state", state);
        model.addAttribute("startDate", startDate);

        return "makeSchedule_form";
    }

    @PostMapping("/scheduleType")
    @ResponseBody
    public List<Map<String, Object>> scheduleType(int city, String state, int contentTypeId)throws URISyntaxException, JsonProcessingException {

        //기본 콘텐츠타입 12
        List<Map<String, Object>> itemMap = myScheduleService.getData(city, state, contentTypeId, 1000);

        return itemMap;
    }


    @GetMapping("/mySchedule")
    //내가 간 장소들 데려오기
    public String scheduleList(HttpSession session, Model model){
        
        String loginId = (String) session.getAttribute("loginId");
        //로그인 안돼있으면 로그인먼저 하기
        if (loginId == null){
            return "redirect:/login";
        }else{
            List<MySchedule> ScheduleList = myScheduleService.getScheduleList(loginId);
            model.addAttribute("ScheduleList", ScheduleList);
        }
        return "mySchedule_form";
    }

    @PostMapping("/saveSchedule")
    public String saveScheduleCon(HttpSession session,
                                  String visitSpot, String state, Date startDate, String imgSrc){

        String loginId = (String)session.getAttribute("loginId");
        myScheduleService.saveSchedule(loginId, state, visitSpot, startDate, imgSrc);

        return "redirect:/mySchedule";
    }
}
