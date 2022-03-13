package ru.job4j.accident.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    private final Gson gson =  new GsonBuilder().create();


    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAllTypes().values());
        model.addAttribute("rules", accidentService.getAllRules().values());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = Arrays.toString(req.getParameterValues("rIds"))
                .replace("[", "")
                .replace("]", "")
                .replaceAll("\\s+", "")
                .split(",");
            accidentService.save(accident, rIds);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id).get();
        Set<Rule> rules = accident.getRules();
        List<Integer> ruleIds = rules.stream().map(Rule::getId).collect(Collectors.toList());
        model.addAttribute("accident", accident);
        model.addAttribute("accidentRules", ruleIds);
        model.addAttribute("accidentType", accident.getType().getId());
        model.addAttribute("accidentText", accident.getText());
        model.addAttribute("accidentAdress", accident.getAddress());
        model.addAttribute("types", accidentService.getAllTypes().values());
        model.addAttribute("rules", accidentService.getAllRules().values());
        return "accident/update";
    }

}
