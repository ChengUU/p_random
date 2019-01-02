package com.ml.random.domain.dto.beanDto;

import com.ml.random.domain.entity.RandomOrigin;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OriginDto {

    private Long from;  //从多少

    private Long to;  //到多少

    private List<RandomOrigin> origin;

    private Integer frontNum;

    private Integer oppositeNum;

}
