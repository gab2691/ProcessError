package com.gab26.errorRepository.service;

import com.gab26.errorRepository.dto.RelevantDTO;
import com.gab26.errorRepository.exception.handleNoRelvants;
import com.gab26.errorRepository.model.Relevantes;
import com.gab26.errorRepository.repository.RelevantesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelevantesService {
    
    @Autowired
    private RelevantesRepository relevantesRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RelevantesService() {
    }
    
    public List<Relevantes> relevantesDairio() {
        try {
            return this.relevantesRepository.findAllByDataEvento((new SimpleDateFormat("dd-MMM-yyyy")).format(Calendar.getInstance().getTime()));
        } catch (handleNoRelvants var2) {
           throw new handleNoRelvants("Error geting relevants");
        }
    }
    
    public List<Relevantes> findAllByParams(Relevantes relevantes) {
        Example<Relevantes> searchExample =
                Example.of(relevantes, ExampleMatcher.matching().withMatcher("descOcorrencia", GenericPropertyMatchers.contains().caseSensitive().ignoreCase()).withIgnoreNullValues());
        return this.relevantesRepository
                .findAll(searchExample).stream()
                              .sorted(Comparator.comparingInt(Relevantes::getId).reversed()).collect(Collectors.toList());
    }

    public void save(RelevantDTO dto) {
        Relevantes relevantes = modelMapper.map(dto, Relevantes.class);
        relevantesRepository.save(relevantes);
    }

    public Relevantes findById(int id) {
        Relevantes relevant = relevantesRepository.findById(id);
        if(relevant == null) throw new handleNoRelvants("No data Found");
        return relevant;
    }
}
