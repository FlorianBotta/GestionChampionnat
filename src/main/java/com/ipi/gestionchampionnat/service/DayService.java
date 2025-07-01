package com.ipi.gestionchampionnat.service;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Day;

import java.util.List;

public interface DayService {
    Day ajouterDay(Day day);
    Day recupererDay(Long id);
    List<Day> recupererDays();
    Day modifierDay(Day day);
    void supprimerDay(Long id);
    List<Day> recupererDaysByChampionship(Championship championship);
}
