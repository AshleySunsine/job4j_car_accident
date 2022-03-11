package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        List<Accident> accidents = new ArrayList<>();
        try (Session session = sf.openSession()) {
            accidents = session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
        return accidents;
    }

    public Optional<Accident> findById(int id) {
        Accident acc = new Accident();
        try (Session session = sf.openSession()) {
            acc = (Accident) session
                    .createQuery("from Accident where id = :Id")
                    .setParameter("Id", id)
                    .uniqueResult();
        }
        return Optional.of(acc);

    }

    public Map<Integer, AccidentType> getAllTypes() {
        List<AccidentType> types = new ArrayList<>();
        try (Session session = sf.openSession()) {
            types = session
                    .createQuery("from AccidentType ", AccidentType.class)
                    .list();
        }
        Map<Integer, AccidentType> typeMap = new HashMap<>();
        for (var i: types) {
            typeMap.put(i.getId(), i);
        }
        return typeMap;
    }

    public Map<Integer, Rule> getAllRules() {
        List<Rule> rules = new ArrayList<>();
        try (Session session = sf.openSession()) {
            rules = session
                    .createQuery("from Rule ", Rule.class)
                    .list();
        }
        Map<Integer, Rule> ruleMap = new HashMap<>();
        for (var i: rules) {
            ruleMap.put(i.getId(), i);
        }
        return ruleMap;
    }

}