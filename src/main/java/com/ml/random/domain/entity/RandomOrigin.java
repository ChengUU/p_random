package com.ml.random.domain.entity;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table( name = "random_origin")
public class RandomOrigin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origin_id")
    private Long originId;

    @Column(name = "r_front")
    private int rFront;

    @Column(name = "r_opposite")
    private int rOpposite;


    @Column(name = "modify_time")
    private Timestamp modifyTime;

    @Column(name = "create_time")
    private Timestamp createTime;


}
