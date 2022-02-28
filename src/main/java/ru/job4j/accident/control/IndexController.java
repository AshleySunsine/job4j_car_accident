package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
        model.addAttribute("tableList", list);
        return "index";
    }
}