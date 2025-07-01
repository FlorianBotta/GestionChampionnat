package com.ipi.gestionchampionnat.pojos;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String logo;
    private Date startDate;
    private Date endDate;
    private Integer wonPoint;
    private Integer lostPoint;
    private Integer drawPoint;
    private String typeRanking;

    public Championship() {
    }

    public Championship(String name, String logo, Date startDate, Date endDate, Integer wonPoint, Integer lostPoint, Integer drawPoint, String typeRanking) {
        this.name = name;
        this.logo = logo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wonPoint = wonPoint;
        this.lostPoint = lostPoint;
        this.drawPoint = drawPoint;
        this.typeRanking = typeRanking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(Integer wonPoint) {
        this.wonPoint = wonPoint;
    }

    public Integer getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(Integer lostPoint) {
        this.lostPoint = lostPoint;
    }

    public Integer getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(Integer drawPoint) {
        this.drawPoint = drawPoint;
    }

    public String getTypeRanking() {
        return typeRanking;
    }

    public void setTypeRanking(String typeRanking) {
        this.typeRanking = typeRanking;
    }
}
