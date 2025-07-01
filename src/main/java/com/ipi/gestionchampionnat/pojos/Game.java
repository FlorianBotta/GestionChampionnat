package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer team1Point;
    private Integer team2Point;
    private Integer idTeam1;
    private Integer idTeam2;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTeam1Point() {
        return team1Point;
    }

    public void setTeam1Point(Integer team1Point) {
        this.team1Point = team1Point;
    }

    public Integer getTeam2Point() {
        return team2Point;
    }

    public void setTeam2Point(Integer team2Point) {
        this.team2Point = team2Point;
    }

    public Integer getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(Integer idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public Integer getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(Integer idTeam2) {
        this.idTeam2 = idTeam2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
