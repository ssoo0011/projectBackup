package com.example.yj.post;

import com.example.yj.entity.Post;
import com.example.yj.entity.PostFile;
import com.example.yj.information.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final InformationService informationService;

    @GetMapping("/create") // 게시글 만들기
    public String writePost(@RequestParam Long scdId, String spot, Model model) { //이전페이지에서 스케쥴아이디, 장소 얻어오기
        model.addAttribute("scdId", scdId);
        model.addAttribute("spot", spot);

        return "post_form";
    }

    @PostMapping("/create") //글쓰기
    public String writePost(@ModelAttribute Post post, Long scdId, @RequestPart List<MultipartFile> imgFile) throws Exception {
        postService.savePost(post, scdId, imgFile);
        return "redirect:/post/list"; //글 다 쓰면 리스트로
    }

    @GetMapping("/check")  //게시글 있는지 없는지 체크
    public String checkPost(@RequestParam Long scdId, String spot, Date visitDate, Model model) {

        Post post = postService.getPost(scdId); //스케쥴 아이디로 게시글 여부 체크

        if (post != null) {
            return "redirect:/post/detail/" + post.getBno(); //있으면 게시글로 이동
        } else {
            //없으면 게시글 쓰러 가기
            model.addAttribute("scdId", scdId);
            model.addAttribute("spot", spot);
            model.addAttribute("visitDate", visitDate);

            return "post_form";
        }
    }

    @GetMapping("/list") //리스트 보기
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Post> paging = this.postService.getList(page);
        model.addAttribute("paging", paging);
        return "list_form";
    }

    @GetMapping(value = "/detail/{bno}") //글 보기
    public String detail(Model model, @PathVariable("bno") Long bno, HttpServletRequest request) {

        String userIp = request.getRemoteAddr(); //userIp구하기
        postService.getPost(bno, userIp);

        List<PostFile> postFileList = postService.getFile(bno);
        Post post = postService.getPost(bno, userIp);

        model.addAttribute("post", post);
        model.addAttribute("postFileList", postFileList);

        return "post_detail";
    }

    @GetMapping(value = "/modify/{bno}") // 글 수정
    public String modify(Model model, @PathVariable("bno") Long bno) {
        Post post = postService.modifyView(bno);
        model.addAttribute("post", post);
        return "post_modify_form";
    }

    @PostMapping(value = "/modify")
    public String modify(@ModelAttribute Post post, @RequestPart List <MultipartFile> imgFile) throws Exception {
        postService.modifyPost(post, imgFile);
        return "redirect:/post/detail/" + post.getBno();
    }
    @GetMapping("/delete/{bno}")
    public String deletePostCon(@PathVariable("bno") Long bno){
        postService.deletePost(bno);
        return "redirect:/post/list";
    }

    @PostMapping("/vote") //추천수
    @ResponseBody
    public int postVote(Long bno, HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        int cnt = postService.voteUser(bno, loginId);
        return cnt;
    }

    //지역명으로 게시글 찾기

    @PostMapping("/search") //게시글 검색
    public String searchSpotCon(@RequestParam("visitSpot") String spot,
                                @RequestParam(value = "page", defaultValue = "0") int page, Model model) {

        Page<Post> paging = postService.searchSpot(spot, page);
        model.addAttribute("paging", paging);
        return "list_form";
    }

}
