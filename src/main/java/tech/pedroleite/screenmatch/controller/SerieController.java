package tech.pedroleite.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.pedroleite.screenmatch.model.Serie;
import tech.pedroleite.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;
    
    @GetMapping
    public List<Serie> exibirSeries() {
        return service.exibirSeries();
    }

    @GetMapping("/top5")
    public List<Serie> exibirTop5Series() {
        return service.top5melhoresSeries();
    }

    @GetMapping("/{id}")
    public Serie exibirSeriePorId(@PathVariable Long id) {
        return service.exibirSeriePorId(id);
    }
}
