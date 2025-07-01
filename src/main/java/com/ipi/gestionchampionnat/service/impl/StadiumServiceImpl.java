package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.StadiumDao;
import com.ipi.gestionchampionnat.pojos.Stadium;
import com.ipi.gestionchampionnat.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumServiceImpl implements StadiumService {
    @Autowired
    private StadiumDao stadiumDao;

    @Override
    public Stadium ajouterStadium(Stadium stadium) {
        return stadiumDao.save(stadium);
    }

    @Override
    public Stadium recupererStadium(Long id) {
        return stadiumDao.findById(id).orElse(null);
    }

    @Override
    public List<Stadium> recupererStadiums() {
        return stadiumDao.findAll();
    }

    @Override
    public Stadium modifierStadium(Stadium stadium) {
        return stadiumDao.save(stadium);
    }

    @Override
    public void supprimerStadium(Long id) {
        stadiumDao.deleteById(id);
    }

    @Override
    public Stadium recupererStadiumByName(String name) {
        return stadiumDao.findByName(name);
    }
}

