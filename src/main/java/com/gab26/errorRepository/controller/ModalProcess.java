package com.gab26.errorRepository.controller;

import com.gab26.errorRepository.dto.ProcessErrorDto;
import com.gab26.errorRepository.model.Direction;
import com.gab26.errorRepository.service.ProcessErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/modal")
public class ModalProcess {

    @Autowired
    private ProcessErrorService processErrorService;


    @ResponseBody
    @PostMapping("copyInforme")
    public ResponseEntity copyHistoricoInforme(@RequestBody ProcessErrorDto dto){
        processErrorService.save(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("deleteInforme/{id}")
    public ResponseEntity deleteHistoricoInforme(@PathVariable int id) {
        processErrorService.delete(id);
        return ResponseEntity.ok().body("Registry deleted");
    }

    @ResponseBody
    @PutMapping("updateInforme")
    public ResponseEntity<String> updateProcess(@RequestPart("historicoInforme") ProcessErrorDto dto) {
        processErrorService.save(dto);
        return ResponseEntity.ok("process update");
    }


    @GetMapping("getPreviusOrNext/{id}/{process}/{direction}")
    public ModelAndView getNextInforme(@PathVariable int id, @PathVariable String process, @PathVariable("direction") Direction direction) {
        ModelAndView modelAndView = new ModelAndView("modals/modalInforme")
                .addObject("informe", processErrorService.findNextOrPrevius(id, process, direction));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping("/updateList")
    public ModelAndView home(@RequestBody ProcessErrorDto dto){
        ModelAndView modelAndView = (new ModelAndView("lists/listIlnforme"))
                .addObject("listInforme", this.processErrorService.findAllByDataEvento(dto.getDataEvento()));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}
