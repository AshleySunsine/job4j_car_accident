package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (id, name) values (?)",
                accident.getName());
        return accident;
    }

    public Map<Integer, Accident> getAll() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        jdbc.query("select id, name from accident",
                (rs, row) -> {
            Accident accident = new Accident();
            int id = rs.getInt("id");
            accident.setId(id);
            accident.setName(rs.getString("name"));
            accidentMap.put(id, accident);
            return accident;
        });
        return accidentMap;
    }

    public Optional<Accident> findById(int id) throws SQLException {
        Accident acc = new Accident();
        PreparedStatement ps = jdbc.getDataSource()
                .getConnection()
                .prepareStatement("select * from accident where id=(?)");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            acc.setName(rs.getString("name"));
            acc.setId(rs.getInt("id"));
        }
        return Optional.of(acc);
    }

}
