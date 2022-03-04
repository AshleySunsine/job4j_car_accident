package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
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

    public List<AccidentType> getAllTypes() {
        return accidentMem.getAllTypes();
    }

    public List<Rule> getAllRules() {
        return accidentMem.getAllRules();
    }
}
