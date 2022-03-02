package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    Map<Integer, ru.job4j.accident.model.Accident> accidents = new HashMap<>();

    public Map<Integer, ru.job4j.accident.model.Accident> initRepos() {
        Accident acc1 = new Accident();
        Accident acc2 = new Accident();
        Accident acc3 = new Accident();
        acc1.setName("acc1");
        acc2.setName("acc2");
        acc3.setName("acc3");
        accidents.put(1, acc1);
        accidents.put(2, acc2);
        accidents.put(3, acc3);
        return accidents;
    }


}
