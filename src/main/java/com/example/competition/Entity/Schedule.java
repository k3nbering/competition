package com.example.competition.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "first_team")
    private Team teamFirst;
    @OneToOne
    @JoinColumn(name = "second_team")
    private Team teamSecond;
    private Date date;


}
