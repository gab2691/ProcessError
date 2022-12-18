package com.gab26.errorRepository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "T_CSA_LISTA_JOBS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoJobs {

    @Id
    @Column(name = "ID_JOB")
    private String job;

    @Column(name = "DESCR_BRANCH")
    private String branch;

    @Column(name = "DESC_FUNCIONAL")
    private String descricao;

    @Column(name = "DESC_PRODUTO")
    private String produto;
}
