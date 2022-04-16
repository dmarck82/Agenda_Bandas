package br.edu.utfpr.agenda.banda.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import br.edu.utfpr.agenda.banda.event.RecursoCriadoEvent;
import br.edu.utfpr.agenda.banda.model.AgendaDeShows;
import br.edu.utfpr.agenda.banda.repository.AgendaDeShowsRepository;

@RestController
@RequestMapping("/agendadeshows")
public class AgendaDeShowsResource {
    
    @Autowired
    private AgendaDeShowsRepository agendaDeShowsRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<AgendaDeShows> listar(){
        return agendaDeShowsRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<AgendaDeShows> criar(@Valid @RequestBody AgendaDeShows agendaDeShows, HttpServletResponse response){
        
        AgendaDeShows agendaDeShowsSalvo = agendaDeShowsRepository.save(agendaDeShows);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, agendaDeShowsSalvo.getId_agenda_de_shows()));

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaDeShowsSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<AgendaDeShows> agendaDeShows = this.agendaDeShowsRepository.findById(codigo);
        
        return agendaDeShows.isPresent() ? ResponseEntity.ok(agendaDeShows) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        agendaDeShowsRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <AgendaDeShows> alterarPeloCodigo(@RequestBody AgendaDeShows agendaDeShowsNova, @PathVariable Long codigo){
        Optional<AgendaDeShows> agendaDeShowsVelha = agendaDeShowsRepository.findById(codigo);
        if(agendaDeShowsVelha.isPresent()){
            AgendaDeShows agendaDeShowsTemp = agendaDeShowsVelha.get();
            agendaDeShowsTemp.setData(agendaDeShowsNova.getData());
            agendaDeShowsTemp.setCache(agendaDeShowsNova.getCache());
            agendaDeShowsTemp.setId_banda(agendaDeShowsNova.getId_banda());
            agendaDeShowsTemp.setId_casa_de_show(agendaDeShowsNova.getId_casa_de_show());
            agendaDeShowsRepository.save(agendaDeShowsTemp);
            return new ResponseEntity<AgendaDeShows>(agendaDeShowsTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
