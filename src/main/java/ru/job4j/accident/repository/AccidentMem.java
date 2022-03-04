package ru.job4j.accident.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();
    private final List<AccidentType> types = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    public AccidentMem() {
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
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void save(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id - 1));
    }

    public Optional<List<Accident>> findByTypeId(int typeId) {
        return Optional.of(accidents.values()
                .stream()
                .filter(ac -> (ac.getType().getId() == typeId))
                .collect(Collectors.toList()));
    }

    public Optional<List<Accident>> findByType(AccidentType type) {
        return Optional.of(accidents.values()
                .stream()
                .filter(ac -> (ac.getType().equals(type)))
                .collect(Collectors.toList()));
    }

    public List<AccidentType> getAllTypes() {
        return types;
    }

    public List<Rule> getAllRules() {
        return rules;
    }

}
