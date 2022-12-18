package com.gab26.errorRepository.dto;

import com.gab26.errorRepository.model.InfoJobs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessErrorDto {

    private Integer id;
    private String dataEvento;
    private String sistema;
    private String processo;
    private String equipes;
    private String ticket;
    private String produto;
    private String descAtuacao;
    private String descOcorrencia;
    private String strTipoOcorrencia;
    private String strAcaoInterna;
    private String coreProcesso;
    private String strTipoAtuacao;
    private String seguimento;
    private String stTicket;
    private String observacao;
    private String resposavel;
    private String destinatario;
    private String strAnalista;
    private Boolean blnInforme;
    private String filePath;
    private InfoJobs infoJobs;
}
