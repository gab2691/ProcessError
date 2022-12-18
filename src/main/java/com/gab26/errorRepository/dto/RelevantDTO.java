package com.gab26.errorRepository.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gab26.errorRepository.config.JacksonConfigurationString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelevantDTO {

    private Integer id;

    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String dataEvento;

    private Integer idSemana;

    @JsonDeserialize(using = JacksonConfigurationString.class)
    private String descOcorrencia;
}
