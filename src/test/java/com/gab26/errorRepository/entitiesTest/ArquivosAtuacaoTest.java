package com.gab26.errorRepository.entitiesTest;


import com.gab26.errorRepository.model.ArquivosAtuacao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArquivosAtuacaoTest {



    @Test
    public void testGetter(){
        ArquivosAtuacao arquivosAtuacao = new ArquivosAtuacao();
        arquivosAtuacao.setNomeArquivo("abc");
        arquivosAtuacao.setId(33);
        assertEquals("abc", arquivosAtuacao.getNomeArquivo());
        assertEquals(33, arquivosAtuacao.getId());
    }
}
