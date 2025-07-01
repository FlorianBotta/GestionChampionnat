package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDao extends JpaRepository<Game, Long> {
    List<Game> findByDay(Day day);
    List<Game> findByIdTeam1OrIdTeam2(Team team1Id, Team team2Id);
}

