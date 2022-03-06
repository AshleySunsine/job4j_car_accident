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
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

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
        accidents.put(acc1.getId(), acc1);
        accidents.put(acc2.getId(), acc2);
        accidents.put(acc3.getId(), acc3);
        AccidentType accType1 = AccidentType.of(1, "Две машины");
        AccidentType accType2 = AccidentType.of(2, "Машина и человек");
        AccidentType accType3 = AccidentType.of(3, "Машина и велосипед");
        types.put(accType1.getId(), accType1);
        types.put(accType2.getId(), accType2);
        types.put(accType3.getId(), accType3);
        Rule rule1 = Rule.of(1, "Статья. 1");
        Rule rule2 = Rule.of(2, "Статья. 2");
        Rule rule3 = Rule.of(3, "Статья. 3");
        rules.put(rule1.getId(), rule1);
        rules.put(rule2.getId(), rule2);
        rules.put(rule3.getId(), rule3);
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

    public Map<Integer, AccidentType> getAllTypes() {
        return types;
    }

    public Map<Integer, Rule> getAllRules() {
        return rules;
    }

}
