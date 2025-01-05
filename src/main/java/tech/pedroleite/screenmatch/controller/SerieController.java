package tech.pedroleite.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.pedroleite.screenmatch.model.Serie;
import tech.pedroleite.screenmatch.service.SerieService;

@RestController
public class SerieController {

    @Autowired
    private SerieService service;
    
    @GetMapping("/series")
    public List<Serie> exibirSeries() {
        return service.exibirSeries();
    }
}
