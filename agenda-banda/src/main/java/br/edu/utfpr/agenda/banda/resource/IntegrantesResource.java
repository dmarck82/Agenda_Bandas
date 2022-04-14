package br.edu.utfpr.agenda.banda.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.agenda.banda.model.Integrantes;
import br.edu.utfpr.agenda.banda.repository.IntegrantesRepository;

@RestController
@RequestMapping("/integrantes")
public class IntegrantesResource {
    
    @Autowired
    private IntegrantesRepository integrantesRepository;

    @GetMapping
    public List<Integrantes> listar(){
        return integrantesRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Integrantes> criar(@RequestBody Integrantes integrantes, HttpServletResponse response){
        Integrantes integrantesSalvo = integrantesRepository.save(integrantes);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(integrantesSalvo
        .getId_integrantes()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(integrantesSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Integrantes> integrantes = this.integrantesRepository.findById(codigo);

        return integrantes.isPresent() ? ResponseEntity.ok(integrantes) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        integrantesRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <Integrantes> alterarPeloCodigo(@RequestBody Integrantes integrantesNova, @PathVariable Long codigo){
        Optional<Integrantes> integrantesVelha = integrantesRepository.findById(codigo);
        if(integrantesVelha.isPresent()){
            Integrantes integrantesTemp = integrantesVelha.get();
            integrantesTemp.setNome(integrantesNova.getNome());
            integrantesTemp.setSobrenome(integrantesNova.getSobrenome());
            integrantesTemp.setTelefone(integrantesNova.getTelefone());
            integrantesTemp.setCpf(integrantesNova.getCpf());
            integrantesTemp.setEmail(integrantesNova.getEmail());
            integrantesTemp.setId_banda(integrantesNova.getId_banda());
            integrantesRepository.save(integrantesTemp);
            return new ResponseEntity<Integrantes>(integrantesTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
