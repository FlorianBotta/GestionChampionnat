package com.ipi.gestionchampionnat.dao;

import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
    List<Team> findByCountry(Country country);
    Team findByName(String name);
}
