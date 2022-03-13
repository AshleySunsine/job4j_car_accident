package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class AccidentService {
    private AccidentHibernate accidentMem;

    public AccidentService(AccidentHibernate accidentMem) {

        this.accidentMem = accidentMem;
    }

    public Map<Integer, Accident> getAccidents() {
       return accidentMem != null ? accidentMem.getAll() : null;
    }

    public void save(Accident accident, String[] typeIds) {
           for (var i : typeIds) {
           accident.getRules().add(accidentMem.getAllRules().get(Integer.parseInt(i)));
        }
           if (accident.getId() == 0) {
               accidentMem.save(accident);
           } else {
               accidentMem.update(accident);
           }
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public Map<Integer, AccidentType> getAllTypes() {
        return accidentMem.getAllTypes();
    }

    public Map<Integer, Rule> getAllRules() {
        return accidentMem.getAllRules();
    }
}
