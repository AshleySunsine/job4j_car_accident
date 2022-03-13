package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public IndexController(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> list = new ArrayList<>(accidentJdbcTemplate.getAll().values());
        model.addAttribute("accidentList", list);
        return "index";
    }
}
