package com.gab26.errorRepository.entitiesTest;


import com.gab26.errorRepository.model.ProcessError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoricoInformeTest {


    @Test
    public void testFormatDate() throws ParseException {
        ProcessError historicoInforme = new ProcessError();
        historicoInforme.setDataEvento("03/11/2021");
    }
}
