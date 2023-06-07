package com.example.yj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user") //테이블 이름

public class User {
    @Id
    private String userId;
    @Column (length = 100, nullable = false)
    private String userPw;
    @Column (length = 10, nullable = false)
    private String userName;
    @Column
    private java.sql.Date birth;
}
