package com.gab26.errorRepository.service;

import com.gab26.errorRepository.model.ArquivosAtuacao;
import com.gab26.errorRepository.model.ProcessError;
import com.gab26.errorRepository.repository.ArquivoAtuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArquivosAtuacaoService {
    @Autowired
    private ArquivoAtuacaoRepository arquivoAtuacaoRepository;
    
    public ArquivosAtuacaoService() {
    }
    
    public String nomeArquivo(ProcessError historicoInforme) {
        try {
            List<ArquivosAtuacao> allByHistoricoInforme = this.arquivoAtuacaoRepository.findAllByHistoricoInforme(historicoInforme);
            String nome = "";

            for (ArquivosAtuacao x: allByHistoricoInforme){
                nome = x.getNomeArquivo();
            }
            return nome;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
