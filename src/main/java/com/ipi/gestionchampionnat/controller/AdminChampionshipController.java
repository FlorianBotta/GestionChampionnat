package com.ipi.gestionchampionnat.controller;

import com.ipi.gestionchampionnat.pojos.Championship;
import com.ipi.gestionchampionnat.service.ChampionshipService;
import com.ipi.gestionchampionnat.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/championships")
public class AdminChampionshipController {

    private final ChampionshipService championshipService;
    private final TeamService teamService;
    private final String UPLOAD_DIR = "src/main/resources/static/img/";

    @Autowired
    public AdminChampionshipController(ChampionshipService championshipService, TeamService teamService) {
        this.championshipService = championshipService;
        this.teamService = teamService;
    }

    @GetMapping
    public String listChampionships(Model model) {
        List<Championship> championships = championshipService.recupererChampionships();
        model.addAttribute("championships", championships);
        return "admin/championships/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("championship", new Championship());
        return "admin/championships/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Championship championship = championshipService.recupererChampionship(id);
        if (championship == null) {
            throw new IllegalArgumentException("Championnat invalide avec l'ID: " + id);
        }
        model.addAttribute("championship", championship);
        return "admin/championships/form";
    }

    @PostMapping("/save")
    public String saveChampionship(
            @ModelAttribute Championship championship,
            @RequestParam(value = "logoFile", required = false) MultipartFile logoFile,
            RedirectAttributes redirectAttributes) {

        try {
            // Gestion de l'upload du logo
            if (logoFile != null && !logoFile.isEmpty()) {
                String fileName = handleLogoUpload(logoFile);
                championship.setLogo(fileName);
            }

            // Validation des points
            if (!validatePoints(championship)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Les points doivent être des valeurs positives");
                return "redirect:/admin/championships/new";
            }

            // Validation des dates
            if (championship.getEndDate().before(championship.getStartDate())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "La date de fin doit être postérieure à la date de début");
                return "redirect:/admin/championships/new";
            }

            // Sauvegarde du championnat
            Championship savedChampionship = championshipService.modifierChampionship(championship);

            String message = (championship.getId() == null) ?
                    "Le championnat a été créé avec succès" :
                    "Le championnat a été modifié avec succès";
            redirectAttributes.addFlashAttribute("successMessage", message);

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erreur lors de l'upload du logo: " + e.getMessage());
            return "redirect:/admin/championships/new";
        }

        return "redirect:/admin/championships";
    }

    @GetMapping("/delete/{id}")
    public String deleteChampionship(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Championship championship = championshipService.recupererChampionship(id);
        if (championship == null) {
            throw new IllegalArgumentException("Championnat invalide avec l'ID: " + id);
        }

        // Suppression du logo si existant
        if (championship.getLogo() != null) {
            try {
                Path logoPath = Paths.get(UPLOAD_DIR + championship.getLogo());
                Files.deleteIfExists(logoPath);
            } catch (IOException e) {
                // Log l'erreur mais continue la suppression
                System.err.println("Erreur lors de la suppression du logo: " + e.getMessage());
            }
        }

        championshipService.supprimerChampionship(id);
        redirectAttributes.addFlashAttribute("successMessage", "Le championnat a été supprimé avec succès");
        return "redirect:/admin/championships";
    }

    @GetMapping("/teams/{id}")
    public String manageTeams(@PathVariable Long id, Model model) {
        Championship championship = championshipService.recupererChampionship(id);
        if (championship == null) {
            throw new IllegalArgumentException("Championnat invalide avec l'ID: " + id);
        }

        model.addAttribute("championship", championship);
        model.addAttribute("allTeams", teamService.recupererTeams());
        return "admin/championships/teams";
    }

    private String handleLogoUpload(MultipartFile file) throws IOException {
        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);

        // Create directories if they do not exist
        Files.createDirectories(path.getParent());

        // Save the file
        Files.write(path, file.getBytes());

        return fileName;
    }

    private boolean validatePoints(Championship championship) {
        return championship.getWonPoint() >= 0 &&
                championship.getDrawPoint() >= 0 &&
                championship.getLostPoint() >= 0;
    }
}