package com.gab26.errorRepository.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gab26.errorRepository.model.Relevantes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(value = "spring")
@SpringBootTest
@TestPropertySource(locations= "classpath:application.properties")
public class RelevantesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;



    @Test
    public void relevantesTest() throws Exception {
        this.mockMvc.perform(get("/relevantes/getPageRelevantes"))
                .andExpect(MockMvcResultMatchers.view().name("relevantes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("relevantes"))
                .andExpect(status().isOk());
    }

    @Test
    public void ocorrenciaBysearchTest() throws Exception {
        Relevantes relevantes = new Relevantes();
        relevantes.setIdSemana(48);
        relevantes.setDescOcorrencia("Devido a atrasos na disponibilização dos arquivos de MTM por Madri, o processamento de Collateral está com atraso. Previsão de conclusão é as 9:30 ");

        String relevantesJson = mapper.writeValueAsString(relevantes);

        this.mockMvc.perform(post("/relevantes/getOcorrenciaSearch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(relevantesJson))
                .andExpect(MockMvcResultMatchers.view().name("lists/listRelevantes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("relevantes"))
                .andExpect(status().isOk());
    }

    @Test
    public void ocorrenciaByIdTest() throws Exception {
        Relevantes relevantes = new Relevantes();
        relevantes.setId(53);

        String relevantesJson = mapper.writeValueAsString(relevantes);

        this.mockMvc.perform(post("/relevantes/getOcorrenciaById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(relevantesJson))
                .andExpect(MockMvcResultMatchers.view().name("modals/modalRelevantes"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("relevantes"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveRelvantesTest() throws Exception {
        Relevantes relevantes = new Relevantes();
        relevantes.setIdSemana(1);
        /*relevantes.setDataEvento("01/07/2020");*/
        relevantes.setDescOcorrencia("teste");

        String relevantesJson = mapper.writeValueAsString(relevantes);

        this.mockMvc.perform(post("/relevantes/saveRelevantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(relevantesJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRelevantesTest() throws Exception {
        Relevantes relevantes = new Relevantes();
        relevantes.setIdSemana(2);
        /*relevantes.setDataEvento("01/07/2020");*/
        relevantes.setDescOcorrencia("test update");

        String relevantesJson = mapper.writeValueAsString(relevantes);

        this.mockMvc.perform(post("/relevantes/updateRelevantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(relevantesJson))
                .andExpect(status().isOk());
    }

    @Test
    public void copyHistoricoInformeTest() throws Exception {
        Relevantes relevantes = new Relevantes();
        relevantes.setIdSemana(1);

        String relevantesJson = mapper.writeValueAsString(relevantes);

        this.mockMvc.perform(post("/relevantes/copyRelevantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(relevantesJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRelevantesTest() throws Exception {
        Relevantes relevantes = new Relevantes();
        relevantes.setIdSemana(276);

        String relevantesJson = mapper.writeValueAsString(relevantes);

        this.mockMvc.perform(post("/relevantes/deleteRelevantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(relevantesJson))
                .andExpect(status().isOk());
    }
}
