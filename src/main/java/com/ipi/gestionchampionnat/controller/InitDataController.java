package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.*;
import com.ipi.gestionchampionnat.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;

@Controller
public class InitDataController {

    private TeamService teamService;
    private UserService userService;
    private DayService dayService;
    private GameService gameService;
    private ChampionshipService championshipService;
    private TeamChampionshipService teamChampionshipService;
    private StadiumService stadiumService;
    private CountryService countryService;

    public InitDataController(TeamService teamService, UserService userService, DayService dayService, GameService gameService, ChampionshipService championshipService, TeamChampionshipService teamChampionshipService, StadiumService stadiumService, CountryService countryService) {
        super();
        this.teamService = teamService;
        this.userService = userService;
        this.dayService = dayService;
        this.gameService = gameService;
        this.championshipService = championshipService;
        this.teamChampionshipService = teamChampionshipService;
        this.stadiumService = stadiumService;
        this.countryService = countryService;
    }

    @PostConstruct
    private void init() {

        User admin = new User("admin", "admin@test.fr", "admin", "ADMIN", true);
        userService.ajouterUser(admin);

        Championship ligue1 = new Championship(
                "Ligue 1 Uber Eats",
                "ligue1.png",
                Date.from(LocalDate.of(2025, 8, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.of(2026, 5, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                3,
                0,
                1,
                "totalPoints"
        );

        Championship championsLeague = new Championship(
                "UEFA Champions League",
                "uefa.png",
                Date.from(LocalDate.of(2025, 9, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.of(2026, 6, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                3,
                0,
                1,
                "goalDifference"
        );

        championshipService.ajouterChampionship(ligue1);
        championshipService.ajouterChampionship(championsLeague);

        Day day1Ligue1 = new Day(
                "Journée 1",
                ligue1
        );

        Day day2Ligue1 = new Day(
                "Journée 2",
                ligue1
        );

        Day day1ChampionsLeague = new Day(
                "Phase de groupes - Journée 1",
                championsLeague
        );
        Day day2ChampionsLeague = new Day(
                "Phase de groupes - Journée 2",
                championsLeague
        );
        Day day3ChampionsLeague = new Day(
                "Phase de groupes - Journée 3",
                championsLeague
        );

        dayService.ajouterDay(day1Ligue1);
        dayService.ajouterDay(day2Ligue1);
        dayService.ajouterDay(day1ChampionsLeague);
        dayService.ajouterDay(day2ChampionsLeague);
        dayService.ajouterDay(day3ChampionsLeague);

        Stadium parcDesPrinces = new Stadium(
                "Parc des Princes",
                "24 Rue du Commandant Guilbaud, 75016 Paris",
                47929,
                "0141230000"
        );

        Stadium stadeVelodrome = new Stadium(
                "Stade Vélodrome",
                "3 Boulevard Michelet, 13008 Marseille",
                67394,
                "0491760000"
        );

        Stadium groupamaStadium = new Stadium(
                "Groupama Stadium",
                "10 Avenue Simone Veil, 69150 Décines-Charpieu",
                59186,
                "0472690000"
        );

        Stadium stadePierreMauroy = new Stadium(
                "Stade Pierre-Mauroy",
                "261 Boulevard de Tournai, 59650 Villeneuve-d'Ascq",
                50000,
                "0320280000"
        );

        stadiumService.ajouterStadium(parcDesPrinces);
        stadiumService.ajouterStadium(stadeVelodrome);
        stadiumService.ajouterStadium(groupamaStadium);
        stadiumService.ajouterStadium(stadePierreMauroy);

        Country france = new Country(
                "France",
                "france.png"
        );

        Country spain = new Country(
                "Espagne",
                "espagne.png"
        );

        Country germany = new Country(
                "Allemagne",
                "allemagne.png"
        );

        Country italy = new Country(
                "Italie",
                "italie.png"
        );

        countryService.ajouterCountry(france);
        countryService.ajouterCountry(spain);
        countryService.ajouterCountry(germany);
        countryService.ajouterCountry(italy);

        // Équipes
        Team psg = new Team("Paris Saint-Germain",
                Date.from(LocalDate.of(1970, 8, 12).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "psg.png",
                "Luis Enrique",
                "Nasser Al-Khelaïfi",
                "Professionnel",
                "24 Rue du Commandant Guilbaud, 75016 Paris",
                "0141234567",
                "https://www.psg.fr/",
                parcDesPrinces,
                france);

        Team om = new Team("Olympique de Marseille",
                Date.from(LocalDate.of(1899, 8, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "om.png",
                "Jean-Louis Gasset",
                "Pablo Longoria",
                "Professionnel",
                "3 Boulevard Michelet, 13008 Marseille",
                "0491764567",
                "https://www.om.fr/",
                stadeVelodrome,
                france

        );
        Team lyon = new Team(
                "Olympique Lyonnais",
                Date.from(LocalDate.of(1950, 5, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "ol.png",
                "Pierre Sage",
                "John Textor",
                "Professionnel",
                "10 Avenue Simone Veil, 69150 Décines-Charpieu",
                "0472696569",
                "https://www.ol.fr",
                groupamaStadium,
                france
        );

        Team lille = new Team(
                "LOSC Lille",
                Date.from(LocalDate.of(1944, 9, 23).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "lille.png",
                "Bruno Génésio",
                "Olivier Létang",
                "Professionnel",
                "261 Boulevard de Tournai, 59650 Villeneuve-d'Ascq",
                "0320284040",
                "https://www.losc.fr",
                stadePierreMauroy,
                france
        );

        teamService.ajouterTeam(psg);
        teamService.ajouterTeam(om);
        teamService.ajouterTeam(lyon);
        teamService.ajouterTeam(lille);

        TeamChampionship psgInLigue1 = new TeamChampionship(ligue1, psg); // PSG
        TeamChampionship omInLigue1 = new TeamChampionship(ligue1, om);  // OM
        TeamChampionship lyonInLigue1 = new TeamChampionship(ligue1, lyon); // Lyon
        TeamChampionship lilleInLigue1 = new TeamChampionship(ligue1, lille); // Lille

        TeamChampionship psgInUCL = new TeamChampionship(championsLeague, psg); // PSG
        TeamChampionship omInUCL = new TeamChampionship(championsLeague, om);  // OM

        teamChampionshipService.ajouterTeamChampionship(psgInLigue1);
        teamChampionshipService.ajouterTeamChampionship(omInLigue1);
        teamChampionshipService.ajouterTeamChampionship(lyonInLigue1);
        teamChampionshipService.ajouterTeamChampionship(lilleInLigue1);

        teamChampionshipService.ajouterTeamChampionship(psgInUCL);
        teamChampionshipService.ajouterTeamChampionship(omInUCL);

        Game match1 = new Game(
                3,  // team1Point
                1,  // team2Point
                psg,  // idTeam1 (PSG)
                om,  // idTeam2 (OM)
                day1Ligue1   // idDay
        );

        Game match2 = new Game(
                0,
                2,
                lille,
                lyon,
                day1Ligue1
        );

        Game match3 = new Game(
                2,
                2,
                psg,  // PSG
                lille,  // Lille
                day2Ligue1
        );

        Game match4 = new Game(
                1,
                0,
                om,  // OM
                lyon,  // Lyon
                day2Ligue1
        );

        gameService.ajouterGame(match1);
        gameService.ajouterGame(match2);
        gameService.ajouterGame(match3);
        gameService.ajouterGame(match4);

    }
}