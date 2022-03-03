package ru.job4j.accident.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public AccidentService() {
        Map<Integer, Accident> accidents = new HashMap<>();
        Accident acc1 = new Accident();
        Accident acc2 = new Accident();
        Accident acc3 = new Accident();
        acc1.setName("acc1");
        acc2.setName("acc2");
        acc3.setName("acc3");
        acc1.setId(1);
        acc2.setId(2);
        acc3.setId(3);
        acc1.setType(AccidentType.of(1, "Две машины"));
        acc2.setType(AccidentType.of(2, "Машина и человек"));
        acc3.setType(AccidentType.of(3, "Машина и велосипед"));
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

    public void save(Accident accident) {
        accidentMem.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }
}
