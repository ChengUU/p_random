package com.ml.random.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "random_statistics")
public class RandomStatistics {


    public static int TYPE_ONE = 1;  //全局普通统计
    public static int TYPE_TWO = 2;  //正面普通统计
    public static int TYPE_THREE = 3;  //反面普通统计
    public static int TYPE_FOUR = 4;  //1相关统计

    @Id
    @Column(name = "statistics_id")
    private String statisticsId ;

    @Column(name = "origin_id")
    private Long originId;

    @Column(name = "type")
    private int type;

    @Column(name = "s_one")
    private int sOne;

    @Column(name = "s_two")
    private int sTwo;

    @Column(name = "s_three")
    private int sThree;

    @Column(name = "s_four")
    private int sFour;

    @Column(name = "s_five")
    private int sFive;

    @Column(name = "s_six")
    private int sSix;

    @Column(name = "s_seven")
    private int sSeven;

    @Column(name = "s_eight")
    private int sEight;

    @Column(name = "s_nine")
    private int sNine;

    @Column(name = "s_ten")
    private int sTen;

    @Column(name = "s_others")
    private int sOthers;

    @Column(name = "s_front")
    private long sFront;

    @Column(name = "s_opposite")
    private long sOpposite;

    @Column(name = "modify_time")
    private Timestamp modifyTime;

    @Column(name = "create_time")
    private Timestamp createTime;
}
