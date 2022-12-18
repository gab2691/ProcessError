package com.gab26.errorRepository.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gab26.errorRepository.model.ProcessError;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(value = "spring")
@SpringBootTest
@TestPropertySource(locations= "classpath:application.properties")
public class UpdateGridTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void homeTest() throws Exception {
        ProcessError historicoInforme = new ProcessError();
        historicoInforme.setStrAnalista("t734536");
        historicoInforme.setProcesso("WZUPDA225");

        String historicoJson = mapper.writeValueAsString(historicoInforme);

        this.mockMvc.perform(post("/updateGrid/home")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoJson))
                .andExpect(MockMvcResultMatchers.view().name("lists/listIlnforme"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listInforme"))
                .andExpect(status().isOk());
    }
}
