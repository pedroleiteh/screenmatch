package tech.pedroleite.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.pedroleite.screenmatch.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    
}
