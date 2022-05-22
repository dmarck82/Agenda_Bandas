package br.edu.utfpr.agenda.banda.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.agenda.banda.event.RecursoCriadoEvent;
import br.edu.utfpr.agenda.banda.model.Integrantes;
import br.edu.utfpr.agenda.banda.repository.IntegrantesRepository;
import br.edu.utfpr.agenda.banda.service.IntegrantesService;

@RestController
@RequestMapping("/integrantes")
public class IntegrantesResource {
    
    @Autowired
    private IntegrantesRepository integrantesRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private IntegrantesService integrantesService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_INTEGRANTE') and #oauth2.hasScope('read')")
    public List<Integrantes> listar(){
        return integrantesRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_INTEGRANTE') and #oauth2.hasScope('write')")
    public ResponseEntity<Integrantes> criar(@Valid @RequestBody Integrantes integrantes, HttpServletResponse response){
        
        Integrantes integrantesSalvo = integrantesRepository.save(integrantes);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, integrantesSalvo.getId_integrantes()));

        return ResponseEntity.status(HttpStatus.CREATED).body(integrantesSalvo);

    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_INTEGRANTE') and #oauth2.hasScope('read')")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Integrantes> integrantes = this.integrantesRepository.findById(codigo);

        return integrantes.isPresent() ? ResponseEntity.ok(integrantes) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_INTEGRANTE') and #oauth2.hasScope('write')")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        integrantesRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_INTEGRANTE') and #oauth2.hasScope('write')")
    public ResponseEntity <Integrantes> atualizar(@PathVariable Long codigo, @Valid @RequestBody Integrantes integrantes){

        Integrantes integrantesSalvo = integrantesService.atualizar(codigo, integrantes);

        return ResponseEntity.ok(integrantesSalvo);
    }
    
}
