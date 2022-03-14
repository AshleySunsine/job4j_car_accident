package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        System.out.println("SAVE");
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(
                        "insert into accident (address, name, text,  type_id) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, accident.getAddress());
                statement.setString(2, accident.getName());
                statement.setString(3, accident.getText());
                statement.setInt(4, accident.getType().getId());
                return statement;
            }
        }, holder);
        int primaryKey = -1;
        if (holder.getKeys().size() > 1) {
            System.out.println(holder.getKeys().get("id"));
            primaryKey = (int) holder.getKeys().get("id");
        }
        StringBuilder sb = new StringBuilder();
        for (var r : accident.getRules()) {
            sb.append("insert into accident_rules (accident_id, rules_id) VALUES ("
                    + primaryKey + ", "
                    + r.getId() + ");"
                    + System.lineSeparator());
        }
        System.out.println("Save");
        System.out.println(sb);
        jdbc.update(sb.toString());
        return accident;
    }

    public Accident update(Accident accident) {
        System.out.println("UPDATE");
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(
                        "update accident set "
                               + "address = ?, "
                               + "name = ?, "
                               + "text = ?,  "
                               + "type_id = ? "
                               + "where accident.id = ?", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, accident.getAddress());
                statement.setString(2, accident.getName());
                statement.setString(3, accident.getText());
                statement.setInt(4, accident.getType().getId());
                statement.setInt(5, accident.getId());
                return statement;
            }
        }, holder);
        int primaryKey = -1;
        if (holder.getKeys().size() > 1) {
            System.out.println(holder.getKeys().get("id"));
            primaryKey = (int) holder.getKeys().get("id");
        }
        jdbc.update("delete from accident_rules where accident_id = " + primaryKey);
        StringBuilder sb = new StringBuilder();
        for (var r : accident.getRules()) {
            sb.append("insert into accident_rules (accident_id, rules_id) VALUES ("
                    + primaryKey + ", "
                    + r.getId() + ");"
                    + System.lineSeparator());
        }
        System.out.println(sb);
        jdbc.update(sb.toString());
        return accident;
    }

    public Map<Integer, Accident> getAll() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query("select accident.id, accident.name, accident.text, accident.address, "
                        + "accidenttype.id as accidenttypeId ,"
                        + " accidenttype.name as accidenttypeName "
                        + "from accident join accidenttype on accidenttype.id = accident.type_id",
                (rs, row) -> {
            Accident accident = new Accident();
            AccidentType accidentType = new AccidentType();
            int id = rs.getInt("id");
            accident.setId(id);
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accidentType.setName(rs.getString("accidenttypeName"));
            accidentType.setId(rs.getInt("accidenttypeId"));
            accident.setType(accidentType);
            accidentMap.put(id, accident);
            return accident;
        });

        jdbc.query("select * from accident_rules as ar join rules on ar.rules_id = rules.id",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    accidentMap.get(rs.getInt("accident_id")).getRules().add(rule);
                    return accidentMap;
                });
        return accidentMap;
    }

    public Optional<Accident> findById(int id) {
        Accident acc = new Accident();
        try {
            PreparedStatement ps = jdbc.getDataSource()
                    .getConnection()
                    .prepareStatement("select accident.id, "
                           + "accident.name, "
                           + "accident.text, "
                           + "accident.address, "
                           + "accidenttype.id as accidenttypeId, "
                           + "accidenttype.name as accidenttypeName "
                           + "from accident, accidenttype where accident.id = ? "
                           + "and accidenttype.id = accident.type_id");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            AccidentType accidentType = new AccidentType();
            while (rs.next()) {
                acc.setName(rs.getString("name"));
                acc.setId(rs.getInt("id"));
                acc.setText(rs.getString("text"));
                acc.setAddress(rs.getString("address"));
                accidentType.setName(rs.getString("accidenttypeName"));
                accidentType.setId(rs.getInt("accidenttypeId"));
                acc.setType(accidentType);
            }

            jdbc.query("select * from accident_rules as ar join rules on ar.rules_id = rules.id",
                    (rs2, row) -> {
                        Rule rule = new Rule();
                        rule.setId(rs2.getInt("id"));
                        rule.setName(rs2.getString("name"));
                        acc.getRules().add(rule);
                        return acc;
                    });
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
