package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.ipi.gestionchampionnat.pojos.Championship;

@Repository
public interface DayDao extends JpaRepository<Day, Long> {
    List<Day> findByChampionship(Championship championship);
    List<Day> findByChampionshipOrderByNumber(Championship championship);
}
