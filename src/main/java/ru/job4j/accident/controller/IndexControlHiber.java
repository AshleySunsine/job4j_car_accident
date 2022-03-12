package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControlHiber {
    private final AccidentHibernate accidents;

    public IndexControlHiber(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> list = new ArrayList<>(accidents.getAll().values());
        model.addAttribute("accidentList", list);
        System.out.println("HIBERNATE  " + list);
        return "index";
    }
}