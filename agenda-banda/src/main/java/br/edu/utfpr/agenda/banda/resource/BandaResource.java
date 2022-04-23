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
import br.edu.utfpr.agenda.banda.model.Banda;
import br.edu.utfpr.agenda.banda.repository.BandaRepository;
import br.edu.utfpr.agenda.banda.service.BandaService;

@RestController
@RequestMapping("/banda")
public class BandaResource {
    
    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private BandaService bandaService;
    
    @GetMapping
    public List<Banda> listar(){
        return bandaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Banda> criar(@Valid @RequestBody Banda banda, HttpServletResponse response){
        
        Banda bandaSalvo = bandaRepository.save(banda);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, bandaSalvo.getId_banda()));

        return ResponseEntity.status(HttpStatus.CREATED).body(bandaSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Banda> banda = this.bandaRepository.findById(codigo);

        return banda.isPresent() ? ResponseEntity.ok(banda) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        bandaRepository.deleteById(codigo);
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity <Banda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Banda banda){

        Banda bandaSalva = bandaService.atualizar(codigo, banda);
        
        return ResponseEntity.ok(bandaSalva);
    }         
}
