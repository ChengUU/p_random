package com.ml.random.domain.repository;

import com.ml.random.domain.entity.RandomStatistics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RandomStatisticsRepository extends JpaRepository<RandomStatistics,String> {
    List<RandomStatistics> findByType(int type, Pageable pageable);
}
