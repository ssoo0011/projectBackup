package com.example.yj.PopularSpot;

import com.example.yj.entity.MySchedule;
import com.example.yj.entity.TourList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/spot")
@RequiredArgsConstructor
public class PopularSpotController {
    private final PopularSpotService populaSpotService;
    @GetMapping("/popSpot/{popSpot}")
    public String popSpotCon(@PathVariable("popSpot")String popSpot, Model model){

        List<TourList> touristSpotList = populaSpotService.getPopSpot(popSpot);
        model.addAttribute("spot", popSpot);
        model.addAttribute("touristSpotList", touristSpotList);
        return "popSpot_form";
    }
}
