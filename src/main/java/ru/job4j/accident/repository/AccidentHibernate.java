package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        System.out.println(accident);
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    public Accident update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    public Map<Integer, Accident> getAll() {
        List<Accident> accidentsList;
        Map<Integer, Accident> accidents = new HashMap<>();
        try (Session session = sf.openSession()) {
            accidentsList = session
                    .createQuery("from Accident ac join fetch ac.rules")
                    .list();
        }
        for (var accident : accidentsList) {
            accidents.put(accident.getId(), accident);
        }
        return accidents;
    }

    public Optional<Accident> findById(int id) {
        Accident acc;
        try (Session session = sf.openSession()) {
            acc = (Accident) session
                    .createQuery("from Accident ac join fetch ac.rules where ac.id = :Id")
                    .setParameter("Id", id)
                    .uniqueResult();
        }
        return Optional.of(acc);

    }

    public Map<Integer, AccidentType> getAllTypes() {
        List<AccidentType> types;
        try (Session session = sf.openSession()) {
            types = session
                    .createQuery("from AccidentType ")
                    .list();
        }
        Map<Integer, AccidentType> typeMap = new HashMap<>();
        for (var i: types) {
            typeMap.put(i.getId(), i);
        }
        return typeMap;
    }

    public Map<Integer, Rule> getAllRules() {
        List<Rule> rules;
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