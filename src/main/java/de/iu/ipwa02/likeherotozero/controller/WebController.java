package de.iu.ipwa02.likeherotozero.controller;

import de.iu.ipwa02.likeherotozero.model.Co2Data;
import de.iu.ipwa02.likeherotozero.service.Co2DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final Co2DataService co2DataService;

    @Autowired
    public WebController(Co2DataService co2DataService) {
        this.co2DataService = co2DataService;
    }

    @GetMapping("/")
    public String showPublicPage(Model model) {
        model.addAttribute("countries", co2DataService.findAllCountries());
        return "index";
    }

    @PostMapping("/search")
    public String findCo2DataByCountry(@RequestParam String country, Model model) {
        model.addAttribute("countries", co2DataService.findAllCountries());
        model.addAttribute("selectedCountry", country);

        co2DataService.getLatestEmissionByCountry(country)
                .ifPresentOrElse(
                        data -> model.addAttribute("result", data),
                        () -> model.addAttribute("noResult", true)
                );
        return "index";
    }

    @GetMapping("/backend")
    public String showBackendPage(Model model) {
        model.addAttribute("co2Data", new Co2Data()); // Für das Formular
        return "backend";
    }

    @PostMapping("/backend/add")
    public String addCo2Data(@ModelAttribute Co2Data co2Data,
                             RedirectAttributes redirectAttributes) {
        try {
            co2DataService.saveData(co2Data);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Daten erfolgreich hinzugefügt! Sie müssen noch genehmigt werden.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Fehler beim Speichern: " + e.getMessage());
        }
        return "redirect:/backend";
    }

    @GetMapping("/backend/review")
    public String showReviewPage(Model model) {
        model.addAttribute("pendingData", co2DataService.getPendingData());
        return "backend_review";
    }

    @PostMapping("/backend/approve/{id}")
    public String approveData(@PathVariable Long id,
                              RedirectAttributes redirectAttributes) {
        try {
            co2DataService.approveData(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Datensatz erfolgreich genehmigt!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Fehler bei der Genehmigung: " + e.getMessage());
        }
        return "redirect:/backend/review";
    }

}