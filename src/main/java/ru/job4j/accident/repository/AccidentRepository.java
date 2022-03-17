package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query("from Accident ac join fetch ac.rules join fetch ac.type")
    List<Accident> findAll();

    @Query("from Accident ac join fetch ac.rules join fetch ac.type where ac.id = :Id")
    Optional<Accident> findById(int id);
}