package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public void initMem() {
        Map<Integer, Accident> accidents = new HashMap<>();
        Accident acc1 = new Accident();
        Accident acc2 = new Accident();
        Accident acc3 = new Accident();
        acc1.setName("acc1");
        acc2.setName("acc2");
        acc3.setName("acc3");
        accidents.put(1, acc1);
        accidents.put(2, acc2);
        accidents.put(3, acc3);
        accidentMem = new AccidentMem(accidents);
    }

    public Map<Integer, Accident> getAccidents() {
        if (accidentMem != null) {
            return accidentMem.getAccidents();
        }
        return null;
    }
}
