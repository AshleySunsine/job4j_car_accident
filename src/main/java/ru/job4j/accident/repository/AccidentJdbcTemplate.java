package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.DelayQueue;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
       /* String typeName = "";
        try {
            PreparedStatement ps = jdbc.getDataSource()
                    .getConnection()
                    .prepareStatement("select name from AccidentType where id = ?");
            ps.setInt(1, accident.getType().getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                typeName = rs.getString("name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/

        jdbc.update("insert into accident (name, type_id) values (?, ?)",
                accident.getName(), accident.getType().getId());
        for (var r : accident.getRules()) {
            jdbc.update("insert into accident_rules (accident_id, rules_id) VALUES (?, ?)",
                    accident.getId(), r.getId());
        }
        return accident;
    }

    public Map<Integer, Accident> getAll() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query("select id, name, text from accident",
                (rs, row) -> {
            Accident accident = new Accident();
            int id = rs.getInt("id");
            accident.setId(id);
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accidentMap.put(id, accident);
            return accident;
        });
        return accidentMap;
    }

    public Optional<Accident> findById(int id) {
        Accident acc = new Accident();
        try {
            PreparedStatement ps = jdbc.getDataSource()
                    .getConnection()
                    .prepareStatement("select * from accident where id=(?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc.setName(rs.getString("name"));
                acc.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return Optional.of(acc);

    }

    public Map<Integer, AccidentType> getAllTypes() {
        Map<Integer, AccidentType> accidentType = new HashMap<>();
        jdbc.query("select id, name from accidenttype",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    int id = rs.getInt("id");
                    type.setId(id);
                    type.setName(rs.getString("name"));
                    accidentType.put(id, type);
                    return type;
                });
        System.out.println("++++++   " + accidentType);
        return accidentType;
    }

    public Map<Integer, Rule> getAllRules() {
        Map<Integer, Rule> accidentRule = new HashMap<>();
        jdbc.query("select id, name from Rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    int id = rs.getInt("id");
                    rule.setId(id);
                    rule.setName(rs.getString("name"));
                    accidentRule.put(id, rule);
                    return rule;
                });
        return accidentRule;
    }

}
