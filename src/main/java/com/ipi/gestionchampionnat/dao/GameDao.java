package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {
    List<Game> findByDay(Day day);

    List<Game> findByIdTeam1OrIdTeam2(Integer team1Id, Integer team2Id);
}

