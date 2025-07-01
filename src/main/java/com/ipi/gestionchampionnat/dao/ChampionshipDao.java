package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionshipDao extends JpaRepository<Championship, Long> {
    List<Championship> findByName(String name);
}
