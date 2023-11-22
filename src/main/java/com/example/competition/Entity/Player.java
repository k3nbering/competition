package com.example.competition.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {
    private String name;
    private Integer age;
    private String gender;
    private Double height;
    private Double weight;
    private String info;
    private Boolean accepted;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinTable(name = "team_players", joinColumns = {@JoinColumn(name = "player_id")}, inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private Team team;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", info='" + info + '\'' +
                ", accepted=" + accepted +
                ", id=" + id +
                '}';
    }
}
