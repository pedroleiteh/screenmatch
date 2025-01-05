package tech.pedroleite.screenmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.pedroleite.screenmatch.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    List<Serie> findTop5ByOrderByAvaliacaoDesc();
    
}
