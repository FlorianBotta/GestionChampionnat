package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.service.ChampionshipService;
import com.ipi.gestionchampionnat.service.DayService;
import com.ipi.gestionchampionnat.service.GameService;
import com.ipi.gestionchampionnat.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class GestionChampionnatController {

    private final ChampionshipService championshipService;
    private final DayService dayService;
    private final GameService gameService;
    private final TeamService teamService;

    public GestionChampionnatController(ChampionshipService championshipService,
                                        DayService dayService,
                                        GameService gameService,
                                        TeamService teamService) {
        this.championshipService = championshipService;
        this.dayService = dayService;
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Championship> championships = championshipService.recupererChampionships();
        model.addAttribute("championships", championships);
        return "public/index";
    }

    @GetMapping("/championships")
    public String listChampionships(Model model) {
        List<Championship> championships = championshipService.recupererChampionships();
        model.addAttribute("championships", championships);
        return "public/index";
    }

    @GetMapping("/championship")
    public String showChampionship(Model model, @RequestParam Long championshipId) {
        Championship championship = championshipService.recupererChampionship(championshipId);

        if (championship == null) {
            return "public/index";
        }

        List<Day> days = dayService.recupererDaysByChampionship(championship);
        model.addAttribute("days", days);
        model.addAttribute("championship", championship);

        // Une Map pour stocker les matchs par jour
        Map<Long, List<Game>> gamesByDay = new HashMap<>();

        // Récupérer tous les matchs pour chaque jour
        for (Day day : days) {
            List<Game> games = gameService.recupererGamesByDay(day);
            gamesByDay.put(day.getId(), games);
        }

        model.addAttribute("gamesByDay", gamesByDay);

        return "public/championship";
    }

    @GetMapping("/teams")
    public String listTeams(Model model) {
        List<Team> teams = teamService.recupererTeams();

        model.addAttribute("teams", teams);

        return "public/teams";
    }
    @GetMapping("/team")
    public String showTeamDetails(@RequestParam Long teamId, Model model) {
        Team team = teamService.recupererTeam(teamId);

        if (team == null) {
            return "redirect:/teams";
        }

        model.addAttribute("team", team);
        return "public/team-details";
    }

}
