package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexController {
    private AccidentMem acMem;
    private AccidentService accidentService;

    public IndexController(AccidentMem acMem, AccidentService accidentService) {
        this.acMem = acMem;
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidentList", accidentService.getAccidents());
        return "index";
    }
}