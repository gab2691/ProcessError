
package com.gab26.errorRepository.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gab26.errorRepository.config.JacksonConfigurationString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@JsonInclude(Include.NON_EMPTY)
@Table(name = "T_CSA_RELEVANTE_INFORME")
@Getter
@Setter
@AllArgsConstructor
public class Relevantes {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonDeserialize(using = JacksonConfigurationString.class)
    @Column(name = "DATA_EVENTO")
    private String dataEvento;

    @Column(name = "ID_SEMANA")
    private Integer idSemana;

    @JsonDeserialize(using = JacksonConfigurationString.class)
    @Column(name = "DESC_OCORRENCIA")
    private String descOcorrencia;
    
    public Relevantes() {
        this.setIdSemana(Calendar.getInstance().get(3));
    }
}
