package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem(Map<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

}
