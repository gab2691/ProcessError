package com.gab26.errorRepository.repository;

import com.gab26.errorRepository.model.ProcessError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Cacheable;
import java.util.List;
import java.util.Optional;

@Repository
@Cacheable(false)
public interface ProcessErrorRepository extends JpaRepository<ProcessError, Integer> {

    @Query("SELECT h from ProcessError h where h.dataEvento = :dataevento")
    List<ProcessError> findAllByDataEvento(String dataevento);
    
    Optional<ProcessError> findById(Integer id);
    
    @Query("select h.processo from ProcessError h where h.processo like %:processo% group by h.processo")
    List<String> findByProcessoLike(String processo);
    
    @Query(value = "SELECT * FROM T_CSA_HIST_INFORME WHERE id < :id and processo =:processo ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ProcessError findPreviusInforme(String processo, Integer id);

    @Query(value = "SELECT * FROM T_CSA_HIST_INFORME WHERE id > :id and processo =:processo ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ProcessError findNextInforme(String processo, Integer id);
}
