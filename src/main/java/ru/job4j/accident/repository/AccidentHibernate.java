package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident save(Accident accident) {
        System.out.println(accident);
        return this.tx(session -> {
            session.save(accident);
            return accident;
                });
    }

    public Accident update(Accident accident) {
        return this.tx(session -> {
            session.update(accident);
            return accident;
        });
    }

    public Map<Integer, Accident> getAll() {
        List<Accident> accidentsList;
        Map<Integer, Accident> accidents = new HashMap<>();
            accidentsList = this.tx(session -> {
                return (List<Accident>) session.createQuery("from Accident ac join fetch ac.rules")
                        .list();
            });
        for (var accident : accidentsList) {
            accidents.put(accident.getId(), accident);
        }
        return accidents;
    }

    public Optional<Accident> findById(int id) {
        return this.tx(session -> {
            return Optional.ofNullable((Accident) session
                    .createQuery("from Accident ac join fetch ac.rules where ac.id = :Id")
                    .setParameter("Id", id)
                    .uniqueResult());
        });

    }

    public Map<Integer, AccidentType> getAllTypes() {
        List<AccidentType> types = this.tx(session -> {
            return session.createQuery("from AccidentType ").list();
        });
        Map<Integer, AccidentType> typeMap = new HashMap<>();
        for (var i: types) {
            typeMap.put(i.getId(), i);
        }
        return typeMap;
    }

    public Map<Integer, Rule> getAllRules() {
        List<Rule> rules = this.tx(session -> {
            return session.createQuery("from Rule ", Rule.class).list();
        });

        Map<Integer, Rule> ruleMap = new HashMap<>();
        for (var i: rules) {
            ruleMap.put(i.getId(), i);
        }
        return ruleMap;
    }

}