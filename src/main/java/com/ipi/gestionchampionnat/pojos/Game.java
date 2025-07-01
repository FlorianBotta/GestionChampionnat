package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer team1Point;
    private Integer team2Point;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team idTeam1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team idTeam2;

    public Game(Integer team1Point, Integer team2Point, Team idTeam1, Team idTeam2, Day day) {
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.idTeam1 = idTeam1;
        this.idTeam2 = idTeam2;
        this.day = day;
    }

    public Game() {

    }

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

    public Team getIdTeam2() {
        return idTeam2;
    }

    public void setIdTeam2(Team idTeam2) {
        this.idTeam2 = idTeam2;
    }

    public Team getIdTeam1() {
        return idTeam1;
    }

    public void setIdTeam1(Team idTeam1) {
        this.idTeam1 = idTeam1;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
