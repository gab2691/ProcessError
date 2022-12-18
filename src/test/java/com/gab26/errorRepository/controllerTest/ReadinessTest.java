package com.gab26.errorRepository.controllerTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(value = "spring")
@SpringBootTest
@TestPropertySource(locations= "classpath:application.properties")
public class ReadinessTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void liveness() throws Exception {
        this.mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("ok", "ok"));
    }
}