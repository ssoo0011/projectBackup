package com.example.yj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply") //테이블 이름
@EntityListeners(value = {AuditingEntityListener.class})
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;
    private String content; //댓글내용
    @CreatedDate
    private LocalDate replyDate;//댓단날, 자동생성
    @Column(nullable = false)
    private String replyId; //댓단아이디
    @ManyToOne
    private Post post; //댓글 달린 포스트

    public void update(String content){
        this.content = content;
    }
}
