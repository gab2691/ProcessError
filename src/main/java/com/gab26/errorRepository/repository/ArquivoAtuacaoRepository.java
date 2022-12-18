package com.gab26.errorRepository.repository;

import com.gab26.errorRepository.model.ArquivosAtuacao;
import com.gab26.errorRepository.model.ProcessError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoAtuacaoRepository extends JpaRepository<ArquivosAtuacao, Long> {
    List<ArquivosAtuacao> findAllByHistoricoInforme(ProcessError historicoInforme);
}
