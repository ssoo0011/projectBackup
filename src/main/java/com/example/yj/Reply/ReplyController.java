package com.example.yj.Reply;

import com.example.yj.entity.Post;
import com.example.yj.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final PostService postService;

    @PostMapping("/reply/create/{bno}") //댓글달기

    public String createReplyCon(HttpSession session, @PathVariable("bno") Long bno, String content,
                                 Model model){
        Post post = postService.getPost(bno);
        String loginId = (String)session.getAttribute("loginId");
        replyService.createReplService(loginId, content, post);
        return "redirect:/post/detail/"+bno;
    }

    @PostMapping("/reply/modify/{rno}") //댓글 수정
    public String modifyReplyCon(@PathVariable("rno") Long rno, Long bno, String content){
        replyService.modifyReply(rno, content);
        return "redirect:/post/detail/"+bno;

    }

}
