package com.example.competition.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer score;
    private Integer place;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;


    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", place=" + place +
                '}';
    }
}
