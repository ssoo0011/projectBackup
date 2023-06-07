package com.example.yj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TourList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourSpotList;

    @Column
    private String spot; //방문지역
    @Column
    private String visitSpot; //방문장소들
    @Column
    private String imgSrc;
    @Column
    private Long scheduleId; //게시물 번호
}
