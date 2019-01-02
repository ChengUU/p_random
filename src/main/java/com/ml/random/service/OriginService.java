package com.ml.random.service;

import com.ml.random.domain.constant.CommonConstant;
import com.ml.random.domain.entity.RandomOrigin;
import com.ml.random.domain.entity.RandomStatistics;
import com.ml.random.domain.entity.RandomWeight;
import com.ml.random.domain.repository.RandomOriginRepository;
import com.ml.random.domain.repository.RandomStatisticsRepository;
import com.ml.random.domain.repository.RandomWeightRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OriginService {

    @Autowired
    RandomWeightRepository randomWeightRepository;

    @Autowired
    RandomOriginRepository randomOriginRepository;

    @Autowired
    RandomStatisticsRepository randomStatisticsRepository;

    public void generatorOrigin(int type) {
        RandomOrigin randomOrigin = new RandomOrigin();
        if (type == 1) {
            randomOrigin.setRFront(1);
        } else if (type == 0) {
            randomOrigin.setROpposite(1);
        }
        randomOrigin.setModifyTime(Timestamp.valueOf(LocalDateTime.now()));
        randomOrigin.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        List<RandomOrigin> lastRandomOrigin = randomOriginRepository.findAll(PageRequest.of(0, 2, new Sort(Sort.Direction.DESC, "originId"))).getContent();
        randomOriginRepository.save(randomOrigin);
        if (!CollectionUtils.isEmpty(lastRandomOrigin)) {
            if (randomOrigin.getRFront() != lastRandomOrigin.get(0).getRFront()) {
                autoStaticMsgCommon(randomOrigin);
                //statisticsWeightCommon();
            } else {
                CommonConstant.originCounter++;
            }

            if (randomOrigin.getRFront() != lastRandomOrigin.get(0).getRFront()){
                if (lastRandomOrigin.size() <= 1){
                    CommonConstant.originOneCounter++;
                }else if (lastRandomOrigin.get(1).getRFront() != lastRandomOrigin.get(0).getRFront()){
                    CommonConstant.originOneCounter++;
                }
            }else {
                if (CommonConstant.originOneCounter != 0){
                    autoStaticMsgOne(randomOrigin);
                    //statisticsWeightCommon();
                }
            }

        }
    }


    public void autoStaticMsgCommon(RandomOrigin randomOrigin) {

        RandomStatistics randomStatisticsTotal = new RandomStatistics();
        List<RandomStatistics> randomStatisticsTotalList = randomStatisticsRepository.findByType(RandomStatistics.TYPE_ONE, PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "originId")));
        if (!CollectionUtils.isEmpty(randomStatisticsTotalList)) {
            BeanUtils.copyProperties(randomStatisticsTotalList.get(0),randomStatisticsTotal);
        }
        randomStatisticsTotal.setOriginId(randomOrigin.getOriginId() - 1);
        randomStatisticsTotal.setType(RandomStatistics.TYPE_ONE);
        randomStatisticsTotal.setStatisticsId(System.currentTimeMillis() + "");
        randomStatisticsTotal.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        randomStatisticsTotal.setModifyTime(Timestamp.valueOf(LocalDateTime.now()));
        setStatisticsNum(randomStatisticsTotal, CommonConstant.originCounter);
        if (randomOrigin.getRFront() == 1) {
            randomStatisticsTotal.setSFront(randomStatisticsTotal.getSFront() + CommonConstant.originCounter);
        } else if (randomOrigin.getROpposite() == 1) {
            randomStatisticsTotal.setSOpposite(randomStatisticsTotal.getSOpposite() + CommonConstant.originCounter);
        }
        randomStatisticsRepository.save(randomStatisticsTotal);


        RandomStatistics randomStatistics = new RandomStatistics();
        if (randomOrigin.getRFront() == 0) {
            List<RandomStatistics> randomStatisticsList = randomStatisticsRepository.findByType(RandomStatistics.TYPE_TWO, PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "originId")));
            if (!CollectionUtils.isEmpty(randomStatisticsList)) {
                BeanUtils.copyProperties(randomStatisticsList.get(0),randomStatistics);
            }
            randomStatistics.setType(RandomStatistics.TYPE_TWO);
            randomStatistics.setSFront(randomStatistics.getSFront() + CommonConstant.originCounter);
        } else if (randomOrigin.getROpposite() == 0) {
            List<RandomStatistics> randomStatisticsList = randomStatisticsRepository.findByType(RandomStatistics.TYPE_THREE, PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "originId")));
            if (!CollectionUtils.isEmpty(randomStatisticsList)) {
               BeanUtils.copyProperties(randomStatisticsList.get(0),randomStatistics);
            }
            randomStatistics.setType(RandomStatistics.TYPE_THREE);
            randomStatistics.setSOpposite(randomStatistics.getSOpposite()  + CommonConstant.originCounter);
        }
        randomStatistics.setOriginId(randomOrigin.getOriginId() - 1);
        randomStatistics.setStatisticsId(System.currentTimeMillis() + "");
        randomStatistics.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        randomStatistics.setModifyTime(Timestamp.valueOf(LocalDateTime.now()));
        setStatisticsNum(randomStatistics, CommonConstant.originCounter);
        randomStatisticsRepository.save(randomStatistics);

        CommonConstant.originCounter = 1;

    }


    public void autoStaticMsgOne(RandomOrigin randomOrigin) {
        List<RandomStatistics> randomStatisticsOnes = randomStatisticsRepository.findByType(RandomStatistics.TYPE_FOUR, PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "originId")));
        RandomStatistics randomStatisticsOne = new RandomStatistics();
        if (!CollectionUtils.isEmpty(randomStatisticsOnes)) {
            BeanUtils.copyProperties(randomStatisticsOnes.get(0),randomStatisticsOne);
        }
        randomStatisticsOne.setOriginId(randomOrigin.getOriginId() - 1);
        randomStatisticsOne.setType(RandomStatistics.TYPE_FOUR);
        randomStatisticsOne.setStatisticsId(System.currentTimeMillis() + "");
        randomStatisticsOne.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        randomStatisticsOne.setModifyTime(Timestamp.valueOf(LocalDateTime.now()));
        randomStatisticsOne.setSFront(randomStatisticsOne.getSFront() + CommonConstant.originOneCounter);
        setStatisticsNum(randomStatisticsOne, CommonConstant.originOneCounter);
        randomStatisticsRepository.save(randomStatisticsOne);
        CommonConstant.originOneCounter = 0;

    }

    public void setStatisticsNum(RandomStatistics randomStatistics, Integer counter) {
        if (counter == 1) {
            randomStatistics.setSOne(randomStatistics.getSOne() + 1);
        } else if (counter == 2) {
            randomStatistics.setSTwo(randomStatistics.getSTwo() + 1);
        } else if (counter == 3) {
            randomStatistics.setSThree(randomStatistics.getSThree() + 1);
        } else if (counter == 4) {
            randomStatistics.setSFour(randomStatistics.getSFour() + 1);
        } else if (counter == 5) {
            randomStatistics.setSFive(randomStatistics.getSFive() + 1);
        } else if (counter == 6) {
            randomStatistics.setSSix(randomStatistics.getSSix() + 1);
        } else if (counter == 7) {
            randomStatistics.setSSeven(randomStatistics.getSSeven() + 1);
        } else if (counter == 8) {
            randomStatistics.setSEight(randomStatistics.getSEight() + 1);
        } else if (counter == 9) {
            randomStatistics.setSNine(randomStatistics.getSNine() + 1);
        } else if (counter == 10) {
            randomStatistics.setSTen(randomStatistics.getSTen() + 1);
        } else if (counter > 10) {
            randomStatistics.setSOthers(randomStatistics.getSOthers() + 1);
        }

    }


    /**
     * 计算权重
     */
    public void statisticsWeightCommon() {
        for (int i = 1; i <= 3; i++) {
            RandomStatistics randomStatistics = null;
            List<RandomStatistics> randomStatisticsTotalList = randomStatisticsRepository.findByType(i, PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "originId")));
            if (CollectionUtils.isEmpty(randomStatisticsTotalList)) {
                continue;
            }else {
                randomStatistics = randomStatisticsTotalList.get(0);
            }

            RandomWeight randomWeight = new RandomWeight();
            RandomWeight randomWeightTemp = randomWeightRepository.findByOriginIdAndType(randomStatistics.getOriginId(),i);
            if (ObjectUtils.isEmpty(randomWeightTemp)){
                randomWeight.setWeightId(System.currentTimeMillis() + "");
            }else {
               continue;
            }
            randomWeight.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            randomWeight.setModifyTime(Timestamp.valueOf(LocalDateTime.now()));
            randomWeight.setOriginId(randomStatistics.getOriginId());
            randomWeight.setType(i);
            randomWeight.setWOne(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSOne(),11,11));
            randomWeight.setWTwo(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSTwo(),10,11));
            randomWeight.setWThree(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSThree(),9,11));
            randomWeight.setWFour(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSFour(),8,11));
            randomWeight.setWFive(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSFive(),7,11));
            randomWeight.setWSix(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSSix(),6,11));
            randomWeight.setWSeven(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSSeven(),5,11));
            randomWeight.setWEight(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSEight(),4,11));
            randomWeight.setWNine(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSNine(),3,11));
            randomWeight.setWTen(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSTen(),2,11));
            randomWeight.setWOthers(weight(randomStatistics.getSFront() + randomStatistics.getSOpposite(),randomStatistics.getSOthers(),1,11));
            randomWeightRepository.save(randomWeight);
        }

    }


    public BigDecimal weight(Long total, int element, int n, int x) {
        BigDecimal temp = BigDecimal.valueOf(total).multiply(BigDecimal.valueOf(Math.pow(2, n - 1))).divide(BigDecimal.valueOf(Math.pow(2, x) - 1), 9, BigDecimal.ROUND_UP);
        BigDecimal weight = (BigDecimal.valueOf(element).subtract(temp)).divide(BigDecimal.valueOf(Math.pow(2, n - 1)), 9, BigDecimal.ROUND_UP);
        return weight;
    }

}
