package br.edu.utfpr.agenda.banda.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.utfpr.agenda.banda.repository.BandaRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BandaTestIntegracao {
    

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NTYwMTMxMTEsInVzZXJfbmFtZSI6ImFkbWluQHRkc2FwaS5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX0NBREFTVFJBUl9BR0VOREFfREVfU0hPVyIsIlJPTEVfUkVNT1ZFUl9DQVNBX0RFX1NIT1ciLCJST0xFX1BFU1FVSVNBUl9JTlRFR1JBTlRFIiwiUk9MRV9DQURBU1RSQVJfQkFOREEiLCJST0xFX1JFTU9WRVJfQUdFTkRBX0RFX1NIT1ciLCJST0xFX1BFU1FVSVNBUl9CQU5EQSIsIlJPTEVfQ0FEQVNUUkFSX0NBU0FfREVfU0hPVyIsIlJPTEVfUEVTUVVJU0FSX0FHRU5EQV9ERV9TSE9XIiwiUk9MRV9QRVNRVUlTQVJfQ0FTQV9ERV9TSE9XIiwiUk9MRV9SRU1PVkVSX0lOVEVHUkFOVEUiLCJST0xFX0NBREFTVFJBUl9JTlRFR1JBTlRFIiwiUk9MRV9SRU1PVkVSX0JBTkRBIl0sImp0aSI6ImNJelJCcm5NbXZBbDBfMGp2TFIxRlZnSjlmQSIsImNsaWVudF9pZCI6ImFuZ3VsYXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.KwwS3T9aSIdX1UNtrlRLVI94YXa-utHTbyzAs_2zPrc";
    
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
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        
        HttpEntity<Banda> httpEntity = new HttpEntity<Banda>(banda, headers);

        ResponseEntity<Banda> response = this.testRestTemplate.exchange("/banda", 
        HttpMethod.POST, httpEntity, Banda.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getNome(), "teste");
    }

    @Test
    public void buscarTodasBandasTest() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        
        HttpEntity<Banda> httpEntity = new HttpEntity<Banda>(banda, headers);

        ResponseEntity<Banda[]> response = this.testRestTemplate
            .exchange("/banda", 
             HttpMethod.GET, 
             httpEntity, 
             Banda[].class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void buscarBandaPorIdTest() {
        Banda bandaSalva = this.bandaRepository.save(banda);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        
        HttpEntity<Banda> httpEntity = new HttpEntity<Banda>(banda, headers);

        ResponseEntity<Banda> response = this.testRestTemplate
            .exchange("/banda/" + bandaSalva.getId_banda(), 
             HttpMethod.GET, 
             httpEntity, 
             Banda.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getNome(), "teste");
    }

    @Test
    public void removerBandaTest(){
        //Banda banda = new Banda();
        //banda.setNome("Teste_remover");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        
        HttpEntity<Banda> httpEntity = new HttpEntity<Banda>(banda, headers);
        
        Banda bandaSalva = this.bandaRepository.save(banda);

        ResponseEntity<Void> response = this.testRestTemplate
            .exchange("/banda/" + bandaSalva.getId_banda(),
            HttpMethod.DELETE,
            httpEntity,
            Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
