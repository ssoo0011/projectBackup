package com.example.yj.repository;

import com.example.yj.entity.TourList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourListRepository extends JpaRepository<TourList, Long> {

    @Query(value = "SELECT * " +
            "FROM   tour_list " +
            "WHERE  spot = :spot " +
            "GROUP  BY visit_spot " +
            "ORDER  BY Count(visit_spot) DESC " +
            "LIMIT  10;  ", nativeQuery = true)
    List<TourList> findMostVisitedSpots(@Param("spot") String spot);

    List<TourList> findFirstBySpot(String spot);

}
