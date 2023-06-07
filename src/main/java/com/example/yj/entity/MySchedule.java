package com.example.yj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MySchedule") //테이블 이름
public class MySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId; //스케쥴 번호
    @Column(nullable = false)
    private String spot; // 방문지역
    @Column(nullable = false)
    private String visitSpot; // 방문장소
    private Date date; //방문 날짜
    @Column
    private String imgSrc; //이미지소스
    @Column(nullable = false)
    private String userId; // 아이디 가진사람

}
