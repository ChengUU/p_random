package com.ml.random.schedule;

import com.ml.random.domain.constant.CommonConstant;
import com.ml.random.service.OriginService;
import com.ml.random.util.RandomUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GeneratorOriginSchedule {

//    @Autowired
//    OriginService originService;
//
//    @Scheduled(fixedDelay = 200)
//    public void generatorOrigin(){
//        if (CommonConstant.initSwitch == 1){
//            int num = Integer.parseInt(RandomUnit.generateRandom(1,RandomUnit.RANDOM_ONE))%2;
//            log.info("生产随机数:" + num);
//            originService.generatorOrigin(num);
//        }
//    }


}
