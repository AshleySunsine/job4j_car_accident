package ru.job4j.accident.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

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

    public Integer create(Accident accident) {
        accidents.put(accident.getId(), accident);
        return accident.getId();
    }

}
