package com.gab26.errorRepository.controller;

import com.gab26.errorRepository.dto.RelevantDTO;
import com.gab26.errorRepository.model.Relevantes;
import com.gab26.errorRepository.repository.RelevantesRepository;
import com.gab26.errorRepository.service.RelevantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/relevantes")
public class RelevantesController {
    
    @Autowired
    private RelevantesService relevantesService;
    @Autowired
    private RelevantesRepository relevantesRepository;
    
    @GetMapping("/getPageRelevantes")
    public ModelAndView relevantes() {
        ModelAndView modelAndView = (new ModelAndView("relevantes")).addObject("relevantes", this.relevantesService.relevantesDairio());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
    
    @PostMapping("/getOcorrenciaSearch")
    public ModelAndView ocorrenciaBysearch(@RequestBody Relevantes relevantes){
        ModelAndView modelAndView = (new ModelAndView("lists/listRelevantes")).addObject("relevantes", this.relevantesService.findAllByParams(relevantes));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
    
    @GetMapping("/ocorrencia/{id}")
    public ModelAndView ocorrenciaById(@PathVariable int id) {
        ModelAndView modelAndView = (new ModelAndView("modals/modalRelevantes")).addObject("relevantes", this.relevantesService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
    
    @PostMapping("/saveRelevantes")
    public ResponseEntity saveRelvantes(@RequestBody RelevantDTO dto) {
        relevantesService.save(dto);
        return ResponseEntity.ok("Resgistry saved");
    }

    
    @PostMapping("/updateRelevantes")
    public ResponseEntity updateRelevantes(@RequestBody Relevantes relevantes) {
        return new ResponseEntity(this.relevantesRepository.save(relevantes), HttpStatus.OK);
    }

    @PostMapping("/copyRelevantes")
    public ResponseEntity copyHistoricoInforme(@RequestBody Relevantes relevantes) {
        return new ResponseEntity(this.relevantesRepository.save(relevantes), HttpStatus.OK);
    }
    
    @PostMapping("/deleteRelevantes")
    public ResponseEntity deleteRelevantes(@RequestBody Relevantes relevantes) {
        this.relevantesRepository.delete(relevantes);
        return new ResponseEntity("relevantes deletado com sucesso", HttpStatus.OK);

    }


}
