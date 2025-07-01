package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.DayDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements DayService {
    @Autowired
    private DayDao dayDao;

    @Override
    public Day ajouterDay(Day day) {
        return dayDao.save(day);
    }

    @Override
    public Day recupererDay(Long id) {
        return dayDao.findById(id).orElse(null);
    }

    @Override
    public List<Day> recupererDays() {
        return dayDao.findAll();
    }

    @Override
    public Day modifierDay(Day day) {
        return dayDao.save(day);
    }

    @Override
    public void supprimerDay(Long id) {
        dayDao.deleteById(id);
    }

    @Override
    public List<Day> recupererDaysByChampionship(Championship championship) {
        return dayDao.findByChampionshipOrderByNumber(championship);
    }
}