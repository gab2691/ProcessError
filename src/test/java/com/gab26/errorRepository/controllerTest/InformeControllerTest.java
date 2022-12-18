package com.gab26.errorRepository.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gab26.errorRepository.model.InfoJobs;
import com.gab26.errorRepository.model.ProcessError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(value = "spring")
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class InformeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Before
    public void setup() {
    }

    @Test
    public void homeTest() throws Exception {
        this.mockMvc.perform(get("/informe/pageInforme"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void containerTest() throws Exception {
        this.mockMvc.perform(get("//historicoabends/informe/mainContainer"))
                .andExpect(MockMvcResultMatchers.view().name("mainDetalhes"))
                /*.andExpect(MockMvcResultMatchers.model().attributeExists("user"))*/
                .andExpect(MockMvcResultMatchers.model().attributeExists("listInforme"));

    }

    @Test
    public void copyHistoricoInformeTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        /*historicoInformeOk.setDataEvento("25/10/2021");*/
        historicoInformeOk.setStrAnalista("t734536");
        historicoInformeOk.setProcesso("WZUPDA225");
        historicoInformeOk.setDataEvento("25/10/2021");

        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);

        this.mockMvc.perform(post("/informe/copyInforme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteHistoricoInformeTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        historicoInformeOk.setDataEvento("25/10/2021");
        historicoInformeOk.setStrAnalista("t734536");
        historicoInformeOk.setProcesso("WZUPDA225");

        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);

        this.mockMvc.perform(post("/informe/deleteInforme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(status().isOk());
    }

    @Test
    public void getNextInformeTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        historicoInformeOk.setDataEvento("25/10/2021");
        historicoInformeOk.setStrAnalista("t734536");
        historicoInformeOk.setProcesso("WZUPD310");
        historicoInformeOk.setId(168);
        InfoJobs infoJobs = new InfoJobs();
        infoJobs.setDescricao("teste");
        infoJobs.setProduto("tesouraria");
        infoJobs.setJob("WZUPD310");
        infoJobs.setBranch("brasil");
        historicoInformeOk.setInfoJobs(infoJobs);


        ProcessError historicoInformeNull = new ProcessError();
        historicoInformeNull.setDataEvento("25/10/2021");
        historicoInformeNull.setStrAnalista("t734536");
        historicoInformeNull.setProcesso("WZUPD310");
        historicoInformeNull.setId(3788);
        InfoJobs infoJobsNull = new InfoJobs();
        infoJobsNull.setDescricao("teste");
        infoJobsNull.setProduto("tesouraria");
        infoJobsNull.setJob("WZUPD310");
        infoJobsNull.setBranch("brasil");
        historicoInformeNull.setInfoJobs(infoJobs);


        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);
        String historicoStringNull = mapper.writeValueAsString(historicoInformeNull);

        this.mockMvc.perform(post("/informe/getNextInforme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(MockMvcResultMatchers.view().name("modals/modalInforme"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("informe"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/informe/getNextInforme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringNull))
                .andExpect(MockMvcResultMatchers.view().name("modals/modalInforme"))
                .andExpect(status().isOk());
    }


    @Test
    public void getPreviusInformeTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        historicoInformeOk.setDataEvento("25/10/2021");
        historicoInformeOk.setStrAnalista("t734536");
        historicoInformeOk.setProcesso("WZUPD310");
        historicoInformeOk.setId(3788);

        ProcessError historicoInformeNull = new ProcessError();
        historicoInformeNull.setDataEvento("25/10/2021");
        historicoInformeNull.setStrAnalista("t734536");
        historicoInformeNull.setProcesso("WZUPD310");
        historicoInformeNull.setId(168);
        InfoJobs infoJobsNull = new InfoJobs();
        infoJobsNull.setDescricao("teste");
        infoJobsNull.setProduto("tesouraria");
        infoJobsNull.setJob("WZUPD310");
        infoJobsNull.setBranch("brasil");
        historicoInformeNull.setInfoJobs(infoJobsNull);


        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);
        String historicoStringNull = mapper.writeValueAsString(historicoInformeNull);

        this.mockMvc.perform(post("/informe/getPreviusInforme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(MockMvcResultMatchers.view().name("modals/modalInforme"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("informe"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/informe/getPreviusInforme")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringNull))
                .andExpect(status().isNoContent());
    }


    @Test
    public void getInformeByIdTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        historicoInformeOk.setId(3788);
        historicoInformeOk.setProcesso("WZUPD310");

        ProcessError historicoInformeNull = new ProcessError();
        historicoInformeNull.setId(168000);
        historicoInformeNull.setProcesso("WZUPD310");


        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);
        String historicoStringNull = mapper.writeValueAsString(historicoInformeNull);

        this.mockMvc.perform(post("/informe/getInformeById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(MockMvcResultMatchers.view().name("modals/modalInforme"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("informe"))
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/informe/getInformeById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringNull))
                .andExpect(status().isNoContent());
    }


    @Test
    public void getInformesSearcheTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        historicoInformeOk.setId(3788);
        historicoInformeOk.setProcesso("WZUPD310");

        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);

        this.mockMvc.perform(post("/informe/getInformeBySearch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(MockMvcResultMatchers.view().name("lists/listIlnforme"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listInforme"))
                .andExpect(status().isOk());

    }

    @Test
    public void getJobsTest() throws Exception {
        ProcessError historicoInformeOk = new ProcessError();
        historicoInformeOk.setProcesso("WZUPD310");

        String historicoStringOk = mapper.writeValueAsString(historicoInformeOk);

        this.mockMvc.perform(post("/informe/getJobsSearche")
                .contentType(MediaType.APPLICATION_JSON)
                .content(historicoStringOk))
                .andExpect(status().isOk());
    }


    @Test
    public void findInfoJobsTest() throws Exception {
        InfoJobs infoJobs = new InfoJobs();
        infoJobs.setJob("WZUPS025");

        String job = mapper.writeValueAsString(infoJobs);

        this.mockMvc.perform(post("/informe/findInfoJobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(job))
                .andExpect(status().isOk());
    }


    @Test
    public void insertHistoricoInformeTest() throws Exception {
        byte[] imagenErrada = Files.readAllBytes(Paths.get("src/main/resources/static/imagens/logoSantander.png"));
        MockMultipartFile procedimento = new MockMultipartFile("historicoInforme", "", "application/json","{\"processo\": \"WZUPS025\", \"dataEvento\": \"03/11/2021\"}".getBytes());
        MockMultipartFile infoJobs = new MockMultipartFile("infoJobs", "", "application/json","{\"processo\": \"WZUPS025\"}".getBytes());
        MockMultipartFile fileErrada = new MockMultipartFile("file", "logoSantander", "image/png", imagenErrada);

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/informe/insertInforme")
                .file(procedimento)
                .file(infoJobs)
                .file(fileErrada))
                .andExpect(status().isOk());
    }

    @Test
    public void updateHistoricoInformeTest() throws Exception {
        byte[] imagenErrada = Files.readAllBytes(Paths.get("src/main/resources/static/imagens/logoSantander.png"));
        MockMultipartFile procedimento = new MockMultipartFile("historicoInforme", "", "application/json","{\"processo\": \"WZUPS025\", \"dataEvento\": \"03/11/2021\"}".getBytes());
        MockMultipartFile infoJobs = new MockMultipartFile("infoJobs", "", "application/json","{\"job\": \"WZUPD225\"}".getBytes());
        MockMultipartFile fileErrada = new MockMultipartFile("file", "logoSantander", "image/png", imagenErrada);

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/informe/updateInforme")
                .file(procedimento)
                .file(infoJobs)
                .file(fileErrada))
                .andExpect(status().isOk());
    }

}