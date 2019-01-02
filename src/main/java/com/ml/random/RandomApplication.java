package com.ml.random;

import com.ml.random.domain.constant.CommonConstant;
import com.ml.random.domain.entity.RandomOrigin;
import com.ml.random.domain.repository.RandomOriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@EnableScheduling
@SpringBootApplication
public class RandomApplication implements CommandLineRunner {

    @Autowired
    RandomOriginRepository randomOriginRepository;

    public static void main(String[] args) {
        SpringApplication.run(RandomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (CommonConstant.originCounter == 0) {
            List<RandomOrigin> randomOrigins = randomOriginRepository.findAll(PageRequest.of(0, 20, new Sort(Sort.Direction.DESC, "originId"))).getContent();
            if (!CollectionUtils.isEmpty(randomOrigins)) {
                for (int i = 0; i < 19; i++) {
                    if (randomOrigins.get(i).getRFront() == randomOrigins.get(i + 1).getRFront()) {
                        CommonConstant.originCounter++;
                    } else {
                        break;
                    }
                }

                for (int i = 0; i < 19; i++) {
                    if (randomOrigins.get(i).getRFront() != randomOrigins.get(i + 1).getRFront() && randomOrigins.get(i + 1).getRFront() != randomOrigins.get(i + 2).getRFront()) {
                        CommonConstant.originOneCounter++;
                    }else {
                        break;
                    }
                }
            }
        }

        CommonConstant.initSwitch = 1;
    }
}

