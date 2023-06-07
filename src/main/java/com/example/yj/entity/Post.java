package com.example.yj.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post") //테이블 이름
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; //
    @Column
    private Long scdId; // 어떤 스케쥴의 게시물인지
    @Column(length = 200, nullable = false)
    private String title; // 제목
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용
    @Column(nullable = false)
    private String visitSpot; // 방문장소
    @Column(nullable = false)
    private java.sql.Date visitDate; //방문날짜
    @CreatedDate
    private LocalDate postDate; // 글쓴 날
    @Column(nullable = false)
    private Integer visitPost; // 조회수
    @Column(nullable = false)
    private String pub; // 공개여부? (t -> 공개, f ->비공개)
    @Column(nullable = false)
    private Integer likeNum; // 좋아요수
    @Column(nullable = false)
    private String writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    // cascade : 글을 삭제하면 댓글이 모두 삭제되도록!
    private List<Reply> replyList;

    public void update(String title, String content, String pub){
        //게시글 수정 메소드
        this.title = title;
        this.content = content;
        this.pub = pub;
    }
    
    public void addVisitPost(Integer visitPost){
        //방문자수 업데이트 메소드
        this.visitPost = visitPost;
    }

    public void addLikeNum(Integer likeNum){
        //추천수 업데이트 메소드
        this.likeNum = likeNum;
    }

}
