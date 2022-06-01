package br.edu.utfpr.agenda.banda.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.utfpr.agenda.banda.repository.BandaRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BandaTestIntegracao {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BandaRepository bandaRepository;

    Banda banda;

    @BeforeAll
    public void iniciar(){
        this.banda = new Banda();
        banda.setNome("teste");
    }

    @Test
    public void criarNovaBandaTest(){
        
        Banda banda = new Banda();
        banda.setNome("teste");

        HttpEntity<Banda> httpEntity = new HttpEntity<Banda>(banda);

        ResponseEntity<Banda> response = this.testRestTemplate.exchange("/banda", 
        HttpMethod.POST, httpEntity, Banda.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getNome(), "teste");
    }

    @Test
    public void buscarTodasBandasTest() {

        ResponseEntity<Banda[]> response = this.testRestTemplate
            .exchange("/banda", 
             HttpMethod.GET, 
             null, 
             Banda[].class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void buscarBandaPorIdTest() {
        Banda bandaSalva = this.bandaRepository.save(banda);
        ResponseEntity<Banda> response = this.testRestTemplate
            .exchange("/banda/" + bandaSalva.getId_banda(), 
             HttpMethod.GET, 
             null, 
             Banda.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNome(), "teste");
    }

    @Test
    public void alterarBandaTest() {

        Banda bandaSalva = this.bandaRepository.save(banda);

        Banda banda = new Banda();
        banda.setNome("teste");
        
        HttpEntity<Banda> httpEntity = new HttpEntity<>(banda);

        ResponseEntity<Banda> response = this.testRestTemplate
            .exchange("/banda/" + bandaSalva.getId_banda(),
             HttpMethod.PUT, httpEntity,
             Banda.class);

    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNome(), "teste");
    }
}
