package com.example.yj.repository;

import com.example.yj.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByVisitSpot (String visitSpot, Pageable pageable);
    Page <Post> findByPub(String t, Pageable pageable);
    List<Post> findByWriter(String writer);
    Page<Post> findAll(Pageable pageable);
    @Query(value = "SELECT * FROM post ORDER BY like_num DESC LIMIT 4", nativeQuery = true)
    List<Post> findPopPost(); //인기 젤 많은 글 찾기
    Optional<Post> findByScdId(Long scdId);

}
