package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
@Transactional
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query("from Accident ac join fetch ac.rules")
    List<Accident> findAll();
}