package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexController {
    private AccidentMem acMem;

    public IndexController(AccidentMem acMem) {
        this.acMem = acMem;
    }

    @GetMapping("/")
    public String index(Model model) {
        AccidentService accidentService = new AccidentService();
        accidentService.initMem();
        model.addAttribute("accidentList", accidentService.getAccidents());
        return "index";
    }
}