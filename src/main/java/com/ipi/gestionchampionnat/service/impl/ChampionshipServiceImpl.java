package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.ChampionshipDao;
import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {
    @Autowired
    private ChampionshipDao championshipDao;

    @Override
    public Championship ajouterChampionship(Championship championship) {
        return championshipDao.save(championship);
    }

    @Override
    public Championship recupererChampionship(Long id) {
        return championshipDao.findById(id).orElse(null);
    }

    @Override
    public List<Championship> recupererChampionships() {
        return championshipDao.findAll();
    }

    @Override
    public Championship modifierChampionship(Championship championship) {
        return championshipDao.save(championship);
    }

    @Override
    public void supprimerChampionship(Long id) {
        championshipDao.deleteById(id);
    }
}
