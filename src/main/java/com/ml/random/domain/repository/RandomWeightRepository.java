package com.ml.random.domain.repository;

import com.ml.random.domain.entity.RandomWeight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomWeightRepository extends JpaRepository<RandomWeight,String> {

    RandomWeight findByOriginIdAndType(Long originId,Integer type);

}
