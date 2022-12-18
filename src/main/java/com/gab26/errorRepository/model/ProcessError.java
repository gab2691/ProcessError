
package com.gab26.errorRepository.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gab26.errorRepository.config.JacksonConfigurationString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "T_CSA_HIST_INFORME")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessError {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "DATA_EVENTO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String dataEvento;

    @Column(name = "SISTEMA")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String sistema;

    @Column(name = "PROCESSO")
    private String processo;

    @Column(name = "EQUIPES")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String equipes;

    @Column(name = "TICKET")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String ticket;

    @Column(name = "PRODUTO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String produto;

    @Column(name = "EVENTO_FUNCIONALIDAE")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String eventoFuncionalidade;

    @Column(name = "DESC_ATUACAO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String descAtuacao;

    @Column(name = "DESC_OCORRENCIA")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String descOcorrencia;

    @Column(name = "GE_TP_OCORRENCIA")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String strTipoOcorrencia;

    @Column(name = "GE_ACAO_INTERNA")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String strAcaoInterna;

    @Column(name = "GE_CORE_PROCESSO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String coreProcesso;

    @Column(name = "GE_TP_ATUACAO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String strTipoAtuacao;

    @Column(name = "ST_SEGUIMENTO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String seguimento;

    @Column(name = "ST_TICKET")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String stTicket;

    @Column(name = "ST_OBSERVACAO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String observacao;

    @Column(name = "ST_RESPONSAVEL")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String resposavel;

    @Column(name = "DESTINATARIO")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String destinatario;

    @Column(name = "ANALISTA")
    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String strAnalista;

    @Column(name = "ST_ENABLE_INFORME")
    private Boolean blnInforme;

    @Column(name = "FILE_UPLOAD")
    private String filePath;

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "INFO_JOBS", referencedColumnName = "ID_JOB")
    private InfoJobs infoJobs;
}
