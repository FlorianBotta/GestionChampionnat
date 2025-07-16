package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Stadium;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.service.CountryService;
import com.ipi.gestionchampionnat.service.StadiumService;
import com.ipi.gestionchampionnat.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/teams")
public class AdminTeamController {

    private final TeamService teamService;
    private final CountryService countryService;
    private final StadiumService stadiumService;

    @Value("${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

    @Autowired
    public AdminTeamController(TeamService teamService, CountryService countryService, StadiumService stadiumService) {
        this.teamService = teamService;
        this.countryService = countryService;
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public String listTeams(Model model) {
        List<Team> teams = teamService.recupererTeamsOrderByCreationDate();
        model.addAttribute("teams", teams);
        return "admin/teams/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("team", new Team());
        prepareFormData(model);
        return "admin/teams/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Team team = teamService.recupererTeam(id);
        if (team == null) {
            throw new IllegalArgumentException("Invalid team Id: " + id);
        }
        model.addAttribute("team", team);
        prepareFormData(model);
        return "admin/teams/form";
    }


    @PostMapping("/save")
    public String saveTeam(
            @ModelAttribute Team team,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Long stadiumId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate creationDate,
            RedirectAttributes redirectAttributes) {

        // Get existing team if it exists
        Team existingTeam = null;
        if (team.getId() != null) {
            existingTeam = teamService.recupererTeam(team.getId());
            if (existingTeam != null) {
                // Conserver les champs qui ne sont pas dans le formulaire
                team.setLogo(existingTeam.getLogo());  // Si vous avez un champ logo
            }
        }

        team.setCreationDate(creationDate);

        // Set country if specified
        if (countryId != null) {
            Country country = countryService.recupererCountry(countryId);
            team.setCountry(country);
        }

        // Set stadium if specified
        if (stadiumId != null) {
            Stadium stadium = stadiumService.recupererStadium(stadiumId);
            team.setStadium(stadium);
        }

        // Save team
        teamService.modifierTeam(team);
        redirectAttributes.addFlashAttribute("successMessage",
                "L'équipe a été " + (existingTeam == null ? "créée" : "modifiée") + " avec succès!");
        return "redirect:/admin/teams";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Team team = teamService.recupererTeam(id);
        if (team == null) {
            throw new IllegalArgumentException("Invalid team Id: " + id);
        }

        teamService.supprimerTeam(id);
        redirectAttributes.addFlashAttribute("successMessage", "L'équipe a été supprimée avec succès!");
        return "redirect:/admin/teams";
    }

    private void prepareFormData(Model model) {
        List<Country> countries = countryService.recupererCountries();
        List<Stadium> stadiums = stadiumService.recupererStadiums();
        model.addAttribute("countries", countries);
        model.addAttribute("stadiums", stadiums);
    }
}