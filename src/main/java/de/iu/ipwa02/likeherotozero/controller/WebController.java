package de.iu.ipwa02.likeherotozero.controller;

import de.iu.ipwa02.likeherotozero.model.Co2Data;
import de.iu.ipwa02.likeherotozero.service.Co2DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        co2DataService.getLatestEmissionByCountry(country)
                .ifPresent(data -> model.addAttribute("result", data));
        model.addAttribute("selectedCountry", country);
        return "index";
    }

    @GetMapping("/backend")
    public String showBackendPage(Model model) {
        model.addAttribute("co2Data", new Co2Data()); // Für das Formular
        return "backend";
    }

    @PostMapping("/backend/add")
    public String addCo2Data(Co2Data co2Data, RedirectAttributes redirectAttributes) {
        co2DataService.saveData(co2Data);
        redirectAttributes.addFlashAttribute("successMessage", "Daten erfolgreich hinzugefügt!");
        return "redirect:/backend";
    }
}