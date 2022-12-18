package com.gab26.errorRepository.service;

import com.gab26.errorRepository.dto.ProcessErrorDto;
import com.gab26.errorRepository.model.Direction;
import com.gab26.errorRepository.model.InfoJobs;
import com.gab26.errorRepository.model.ProcessError;
import com.gab26.errorRepository.exception.ErrorDeleteInforme;
import com.gab26.errorRepository.exception.ErrorSaveInforme;
import com.gab26.errorRepository.exception.NoInformeById;
import com.gab26.errorRepository.exception.NoMoreInformeException;
import com.gab26.errorRepository.repository.ProcessErrorRepository;
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

import static com.gab26.errorRepository.model.Direction.PREV;


@Service
public class ProcessErrorService {

    @Autowired
    private ProcessErrorRepository historicoInformeRepository;

    @Autowired
    private InfoJobsService infoJobsService;

    @Autowired
    private ModelMapper modelMapper;

    public ProcessErrorService() {
    }
    
    public List<ProcessError> dailyInfo() {
        return this.historicoInformeRepository.findAllByDataEvento((new SimpleDateFormat("dd/MM/yyyy")).format(Calendar.getInstance().getTime()));
    }

    public ProcessError findById(int id) {
        ProcessError byId = this.historicoInformeRepository.findById(id).get();
        if(byId == null) throw new NoInformeById("No data was found");
        return verifyInfoJobs(byId);
    }

    private ProcessError verifyInfoJobs(ProcessError processError) {
        if(processError.getInfoJobs() == null){
            InfoJobs infoJobs = new InfoJobs(processError.getProcesso(),null,null, processError.getProcesso());
            infoJobsService.save(infoJobs);
            processError.setInfoJobs(infoJobs);
            processError = historicoInformeRepository.save(processError);
        }
        return processError;
    }

    public List<String> getProcessoSearche(String processo) {
        return this.historicoInformeRepository.findByProcessoLike(processo);
    }
    
    public List<ProcessError> findAllByParams(ProcessErrorDto dto){
        ProcessError processError = modelMapper.map(dto, ProcessError.class);
        Example<ProcessError> searchExample =
                Example.of(processError, ExampleMatcher.matching()
                             .withMatcher("ticket", GenericPropertyMatchers.contains().ignoreCase())
                             .withMatcher("eventoFuncionalidade", GenericPropertyMatchers.contains().ignoreCase())
                             .withMatcher("descAtuacao", GenericPropertyMatchers.contains().ignoreCase())
                             .withMatcher("descOcorrencia", GenericPropertyMatchers.contains().ignoreCase())
                             .withMatcher("processo", GenericPropertyMatchers.contains().ignoreCase())
                             .withIgnoreNullValues());
        List<ProcessError> collect = this.historicoInformeRepository.findAll(searchExample)
                .stream().sorted(Comparator.comparingInt(ProcessError::getId)
                        .reversed()).collect(Collectors.toList());

        if(collect.size() <= 0) throw new NoMoreInformeException("No Registry found");
        return collect;
    }

    public void save(ProcessErrorDto dto) {
        try {
            dto.setStrAnalista(System.getenv("USERNAME"));
            ProcessError processError = modelMapper.map(dto, ProcessError.class);
            historicoInformeRepository.save(processError);
        }catch (Exception e){
            throw new ErrorSaveInforme("Error, try again");
        }
    }

    public void delete(int id) {
        try {
            historicoInformeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ErrorDeleteInforme("Erro deleting this resgistry, try again");
        }
    }

    public ProcessError findNextOrPrevius(int id, String processo, Direction direction) {
        ProcessError process = direction.equals(PREV)
                ? historicoInformeRepository.findPreviusInforme(processo.trim(), id)
                : historicoInformeRepository.findNextInforme(processo, id);

        if (process == null) throw new NoMoreInformeException("No more data next");
        verifyInfoJobs(process);
        return process;
    };

    public List<ProcessError> findAllByDataEvento(String date){
        return historicoInformeRepository.findAllByDataEvento(date);
    }

}
