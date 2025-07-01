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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/championship/{championshipId}")
    public String showChampionship(@PathVariable Long championshipId, Model model) {
        List<Championship> championships = championshipService.recupererChampionships();
        model.addAttribute("championships", championships);

        // Changed from getChampionshipById to recupererChampionship
        Championship championship = championshipService.recupererChampionship(championshipId);
        if (championship != null) {
            model.addAttribute("selectedChampionship", championship);
            model.addAttribute("selectedChampionshipId", championshipId);

            // Changed from getDaysByChampionshipId to recupererDaysByChampionship
            List<Day> days = dayService.recupererDaysByChampionship(championship);
            model.addAttribute("days", days);

            // If there are days, default to showing the first day's games
            if (!days.isEmpty()) {
                Day firstDay = days.get(0);
                model.addAttribute("selectedDayId", firstDay.getId());

                // Update this line when you have the equivalent method in GameService
                List<Game> games = gameService.recupererGamesByDay(firstDay);
                model.addAttribute("games", games);
            }
        }

        return "public/index";
    }

    @GetMapping("/teams")
    public String listTeams(Model model) {
        List<Team> teams = teamService.recupererTeams();
        List<Championship> championships = championshipService.recupererChampionships();

        model.addAttribute("teams", teams);
        model.addAttribute("championships", championships);

        return "public/teams";
    }

}
