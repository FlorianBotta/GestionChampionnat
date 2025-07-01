package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.TeamChampionship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamChampionshipDao extends JpaRepository<TeamChampionship, Long> {
    List<TeamChampionship> findByChampionship(Championship championship);
    List<TeamChampionship> findByTeam(Team team);
}
