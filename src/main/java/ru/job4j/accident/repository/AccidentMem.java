package ru.job4j.accident.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Scope("singleton")
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem(Map<Integer, Accident> accidents) {
        this.accidents.putAll(accidents);
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void save(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        for (var i : accidents.entrySet()) {
            if (i.getValue().getId() == id) {
                return Optional.of(i.getValue());
            }
        }
        return Optional.empty();
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

}
