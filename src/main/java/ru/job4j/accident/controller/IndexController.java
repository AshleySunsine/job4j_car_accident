package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem acMem = new AccidentMem();
        model.addAttribute("accidentList", acMem.initRepos());
        return "index";
    }
}