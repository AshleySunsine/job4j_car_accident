package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

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
        List<Accident> list = new ArrayList<>(accidentService.getAccidents().values());
        model.addAttribute("accidentList", list);
        return "index";
    }
}