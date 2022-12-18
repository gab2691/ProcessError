package com.gab26.errorRepository.controller;

import com.gab26.errorRepository.dto.ProcessErrorDto;
import com.gab26.errorRepository.model.Direction;
import com.gab26.errorRepository.model.InfoJobs;
import com.gab26.errorRepository.model.ProcessError;
import com.gab26.errorRepository.service.ProcessErrorService;
import com.gab26.errorRepository.service.InfoJobsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/informe")
public class InformeController {

    @Autowired
    private ProcessErrorService historicoInformeService;

    @Autowired
    private InfoJobsService infoJobsService;

    @Autowired
    private ModelMapper modelMapper;


    public InformeController() {
    }

    @GetMapping("pageInforme")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("mainContainer")
    public ModelAndView mainContainer() {
        ModelAndView modelAndView = (new ModelAndView("mainDetalhes"))
                .addObject("listInforme", this.historicoInformeService.dailyInfo())
                .addObject("user", System.getenv("USERNAME"));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }




    @PostMapping("/getInformeById/{id}")
    public ModelAndView getInformeById(@PathVariable int id) {
       ModelAndView modelAndView = (new ModelAndView("modals/modalInforme"))
                .addObject("informe", this.historicoInformeService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }


    @PostMapping("getInformeBySearch")
    public ModelAndView getInformesSearche(@RequestBody ProcessError processError) {
        return (
                new ModelAndView("lists/listIlnforme"))
                .addObject("listInforme",
                        this.historicoInformeService.findAllByParams(modelMapper.map(processError, ProcessErrorDto.class)));
    }

    @GetMapping("dailyProcess")
    public ModelAndView getProcessSysDate(){
        return new ModelAndView("lists/listIlnforme").addObject("listInforme", this.historicoInformeService.dailyInfo());
    }

    @ResponseBody
    @GetMapping("jobsAutoComplete/{job}")
    public List<String> getJobs(@PathVariable String job) {
        return this.historicoInformeService.getProcessoSearche(job.toUpperCase());
    }

    @ResponseBody
    @GetMapping("findInfoJobs/{job}")
    public InfoJobs findInfoJobs(@PathVariable String job) {
        return this.infoJobsService.findByJob(job);
    }


    @ResponseBody
    @PostMapping("insertInforme")
    public ResponseEntity<String> insertHistoricoInforme(@RequestPart("historicoInforme") ProcessErrorDto dto, @RequestPart("infoJobs") InfoJobs infoJobs) {
        historicoInformeService.save(dto);
        return ResponseEntity.ok("process saved");
    }


}
