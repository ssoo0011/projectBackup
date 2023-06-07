package com.example.yj.PopularSpot;

import com.example.yj.entity.MySchedule;
import com.example.yj.entity.TourList;
import com.example.yj.repository.MyScheduleRepository;
import com.example.yj.repository.TourListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularSpotService {
    private final TourListRepository tourListRepository;
    public List<TourList> getPopSpot(String spot) {

        List<TourList> touristSpotList = tourListRepository.findMostVisitedSpots(spot);
        return touristSpotList;
    }
}
