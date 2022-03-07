package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentHibernate;

@Controller
public class IndexControlHiber {
    private final AccidentHibernate accidents;

    public IndexControlHiber(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("GETCOTR");
        model.addAttribute("accidentList", accidents.getAll());
        return "index";
    }
}