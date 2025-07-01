package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.pojos.User;
import com.ipi.gestionchampionnat.service.TeamService;
import com.ipi.gestionchampionnat.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;

@Controller
public class InitDataController {

    private TeamService teamService;
    private UserService userService;

    public InitDataController(TeamService teamService, UserService userService) {
        super();
        this.teamService = teamService;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {

        User admin = new User("admin", "admin@test.fr", "admin", "ADMIN", true);
        userService.ajouterUser(admin);

        // Équipes
        Team psg = new Team("Paris Saint-Germain",Date.from(LocalDate.of(1970, 8, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()),"psg-logo.png","Luis Enrique","Nasser Al-Khelaïfi","Professionnel","24 Rue du Commandant Guilbaud, 75016 Paris","0141234567","https://www.psg.fr/");

        Team om = new Team("Olympique de Marseille",
                Date.from(LocalDate.of(1899, 8, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "om-logo.png",
                "Jean-Louis Gasset",
                "Pablo Longoria",
                "Professionnel",
                "3 Boulevard Michelet, 13008 Marseille",
                "0491764567",
                "https://www.om.fr/"
        );
        Team lyon = new Team(
                "Olympique Lyonnais",
                Date.from(LocalDate.of(1950, 5, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "ol-logo.png",
                "Pierre Sage",
                "John Textor",
                "Professionnel",
                "10 Avenue Simone Veil, 69150 Décines-Charpieu",
                "0472696569",
                "https://www.ol.fr"
        );

        Team lille = new Team(
                "LOSC Lille",
                Date.from(LocalDate.of(1944, 9, 23).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "losc-logo.png",
                "Bruno Génésio",
                "Olivier Létang",
                "Professionnel",
                "261 Boulevard de Tournai, 59650 Villeneuve-d'Ascq",
                "0320284040",
                "https://www.losc.fr"
        );

        teamService.ajouterTeam(psg);
        teamService.ajouterTeam(om);
        teamService.ajouterTeam(lyon);
        teamService.ajouterTeam(lille);
    }
}