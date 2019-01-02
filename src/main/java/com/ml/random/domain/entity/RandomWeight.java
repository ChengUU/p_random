package com.ml.random.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "random_weight")
public class RandomWeight {


    public static int TYPE_ONE = 1;  //全局普通统计
    public static int TYPE_TWO = 2;  //正面普通统计
    public static int TYPE_THREE = 3;  //反面普通统计
    public static int TYPE_FOUR = 4;  //1相关统计

    @Id
    @Column(name = "weight_id")
    private String weightId ;

    @Column(name = "origin_id")
    private Long originId;

    @Column(name = "type")
    private int type;

    @Column(name = "w_one")
    private BigDecimal wOne;

    @Column(name = "w_two")
    private BigDecimal wTwo;

    @Column(name = "w_three")
    private BigDecimal wThree;

    @Column(name = "w_four")
    private BigDecimal wFour;

    @Column(name = "w_five")
    private BigDecimal wFive;

    @Column(name = "w_six")
    private BigDecimal wSix;

    @Column(name = "w_seven")
    private BigDecimal wSeven;

    @Column(name = "w_eight")
    private BigDecimal wEight;

    @Column(name = "w_nine")
    private BigDecimal wNine;

    @Column(name = "w_ten")
    private BigDecimal wTen;

    @Column(name = "w_others")
    private BigDecimal wOthers;

    @Column(name = "modify_time")
    private Timestamp modifyTime;

    @Column(name = "create_time")
    private Timestamp createTime;


}
