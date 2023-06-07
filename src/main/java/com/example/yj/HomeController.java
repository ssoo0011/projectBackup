package com.example.yj;

import com.example.yj.entity.Post;
import com.example.yj.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    @GetMapping("/")
    public String goHome(Model model){

        Map<String, String>popSpotImgMap = postService.popularSpotImg(); //인기 장소, 사진 불러오기
        List<Post> popPostList = postService.popularPost(); // 인기 게시글 리스트 불러오기

        model.addAttribute("popSpotImgMap", popSpotImgMap);
        model.addAttribute("popPostList", popPostList);

        return "content/home_form";
    }




}
