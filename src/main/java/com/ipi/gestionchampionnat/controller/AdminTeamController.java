package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Country;
import com.ipi.gestionchampionnat.pojos.Stadium;
import com.ipi.gestionchampionnat.pojos.Team;
import com.ipi.gestionchampionnat.service.CountryService;
import com.ipi.gestionchampionnat.service.StadiumService;
import com.ipi.gestionchampionnat.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
        List<Team> teams = teamService.recupererTeams();
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
            @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
            RedirectAttributes redirectAttributes) {

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

        // Handle logo upload
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                // Create upload directory if it doesn't exist
                File dir = new File("src/main/resources/static/img");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Generate unique filename
                String filename = UUID.randomUUID() + "_" + logoFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/img/" + filename);
                Files.write(path, logoFile.getBytes());

                // Set logo in team
                team.setLogo(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save team
        teamService.modifierTeam(team);
        redirectAttributes.addFlashAttribute("successMessage",
                "L'équipe a été " + (team.getId() == null ? "créée" : "modifiée") + " avec succès!");
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