package com.gab26.errorRepository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "T_CSA_ARQUIVOS_S")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArquivosAtuacao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name = "FK_HIST_INFORME")
    private ProcessError historicoInforme;
    
    @Column(name = "DESC_NOME_ARQUIVO")
    private String nomeArquivo;

    @Column(name = "DESC_CAMINHO_ARQUIVO")
    private String caminhoArquivo;
}
