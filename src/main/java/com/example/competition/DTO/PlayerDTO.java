package com.example.competition.DTO;

import lombok.Data;

@Data
public class PlayerDTO {
    private String name;
    private Integer age;
    private String gender;
    private Double height;
    private Double weight;
    private String info;
    private Boolean accepted;
}
