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
import br.edu.utfpr.agenda.banda.model.AgendaDeShows;
import br.edu.utfpr.agenda.banda.repository.AgendaDeShowsRepository;
import br.edu.utfpr.agenda.banda.service.AgendaDeShowService;

@RestController
@RequestMapping("/agendadeshows")
public class AgendaDeShowsResource {
    
    @Autowired
    private AgendaDeShowsRepository agendaDeShowsRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private AgendaDeShowService agendaDeShowService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_AGENDA_DE_SHOW') and #oauth2.hasScope('read')")
    public List<AgendaDeShows> listar(){
        return agendaDeShowsRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_AGENDA_DE_SHOW') and #oauth2.hasScope('write')")
    public ResponseEntity<AgendaDeShows> criar(@Valid @RequestBody AgendaDeShows agendaDeShows, HttpServletResponse response){
        
        AgendaDeShows agendaDeShowsSalvo = agendaDeShowsRepository.save(agendaDeShows);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, agendaDeShowsSalvo.getId_agenda_de_shows()));

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaDeShowsSalvo);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_AGENDA_DE_SHOW') and #oauth2.hasScope('read')")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<AgendaDeShows> agendaDeShows = this.agendaDeShowsRepository.findById(codigo);
        
        return agendaDeShows.isPresent() ? ResponseEntity.ok(agendaDeShows) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_AGENDA_DE_SHOW') and #oauth2.hasScope('write')")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        agendaDeShowsRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_AGENDA_DE_SHOW') and #oauth2.hasScope('write')")
    public ResponseEntity <AgendaDeShows> atualizar(@PathVariable Long codigo, @Valid @RequestBody AgendaDeShows agendaDeShow){
    
        AgendaDeShows agendaDeShowsSalvo = agendaDeShowService.atualizar(codigo, agendaDeShow);

        return ResponseEntity.ok(agendaDeShowsSalvo);
    }

}
