package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Day;
import com.ipi.gestionchampionnat.pojos.Game;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.service.DayService;
import com.ipi.gestionchampionnat.service.GameService;
import com.ipi.gestionchampionnat.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/games")
public class AdminGameController {

    private final GameService gameService;
    private final TeamService teamService;
    private final DayService dayService;

    @Autowired
    public AdminGameController(GameService gameService, TeamService teamService, DayService dayService) {
        this.gameService = gameService;
        this.teamService = teamService;
        this.dayService = dayService;
    }

    @GetMapping
    public String listGames(Model model) {
        List<Game> games = gameService.recupererGames();
        model.addAttribute("games", games);
        return "admin/games/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("game", new Game());
        prepareFormData(model);
        return "admin/games/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Game game = gameService.recupererGame(id);
        if (game == null) {
            throw new IllegalArgumentException("Match invalide avec l'ID: " + id);
        }
        model.addAttribute("game", game);
        prepareFormData(model);
        return "admin/games/form";
    }

    @PostMapping("/save")
    public String saveGame(
            @ModelAttribute Game game,
            @RequestParam(required = false) Long team1Id,
            @RequestParam(required = false) Long team2Id,
            @RequestParam(required = false) Long dayId,
            RedirectAttributes redirectAttributes) {

        // Vérification que les équipes sont différentes
        if (team1Id != null && team1Id.equals(team2Id)) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Les deux équipes ne peuvent pas être identiques");
            return "redirect:/admin/games/new";
        }

        // Récupération des équipes
        if (team1Id != null) {
            Team team1 = teamService.recupererTeam(team1Id);
            game.setIdTeam1(team1);
        }
        if (team2Id != null) {
            Team team2 = teamService.recupererTeam(team2Id);
            game.setIdTeam2(team2);
        }

        // Récupération de la journée
        if (dayId != null) {
            Day day = dayService.recupererDay(dayId);
            game.setDay(day);
        }

        // Sauvegarde du match
        Game savedGame = gameService.modifierGame(game);
        
        String message = (game.getId() == null) ? 
            "Le match a été créé avec succès" : 
            "Le match a été modifié avec succès";
        redirectAttributes.addFlashAttribute("successMessage", message);
        
        return "redirect:/admin/games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Game game = gameService.recupererGame(id);
        if (game == null) {
            throw new IllegalArgumentException("Match invalide avec l'ID: " + id);
        }

        gameService.supprimerGame(id);
        redirectAttributes.addFlashAttribute("successMessage", "Le match a été supprimé avec succès");
        return "redirect:/admin/games";
    }

    private void prepareFormData(Model model) {
        List<Team> teams = teamService.recupererTeams();
        List<Day> days = dayService.recupererDays();
        model.addAttribute("teams", teams);
        model.addAttribute("days", days);
    }
}
