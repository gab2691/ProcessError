package com.gab26.errorRepository.repository;

import com.gab26.errorRepository.model.Relevantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelevantesRepository extends JpaRepository<Relevantes, Long> {
    @Query("SELECT h from Relevantes h where h.dataEvento = :dataevento")
    List<Relevantes> findAllByDataEvento(String dataevento);
    
    Relevantes findById(Integer id);
    
    Relevantes findTopByOrderByIdDesc();
}
