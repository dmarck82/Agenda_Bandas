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
import br.edu.utfpr.agenda.banda.model.CasaDeShow;
import br.edu.utfpr.agenda.banda.repository.CasaDeShowRepository;
import br.edu.utfpr.agenda.banda.service.CasaDeShowService;

@RestController
@RequestMapping("/casadeshow")
public class CasaDeShowResource {
    
    @Autowired
    private CasaDeShowRepository casaDeShowRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CasaDeShowService casaDeShowService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CASA_DE_SHOW') and #oauth2.hasScope('read')")
    public List<CasaDeShow> listar(){
        return casaDeShowRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CASA_DE_SHOW') and #oauth2.hasScope('write')")
    public ResponseEntity<CasaDeShow> criar(@Valid @RequestBody CasaDeShow casaDeShow, HttpServletResponse response){
        
        CasaDeShow casaDeShowSalvo = casaDeShowRepository.save(casaDeShow);
        
        publisher.publishEvent(new RecursoCriadoEvent(this, response, casaDeShowSalvo.getId_casa_de_show()));

        return ResponseEntity.status(HttpStatus.CREATED).body(casaDeShowSalvo);

    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CASA_DE_SHOW') and #oauth2.hasScope('read')")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<CasaDeShow> casaDeShow = this.casaDeShowRepository.findById(codigo);

        return casaDeShow.isPresent() ? ResponseEntity.ok(casaDeShow) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_CASA_DE_SHOW') and #oauth2.hasScope('write')")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        casaDeShowRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CASA_DE_SHOW') and #oauth2.hasScope('write')")
    public ResponseEntity <CasaDeShow> atualizar(@PathVariable Long codigo, @Valid @RequestBody CasaDeShow casaDeShow){

        CasaDeShow casaDeShowSalvo = casaDeShowService.atualizar(codigo, casaDeShow);

        return ResponseEntity.ok(casaDeShowSalvo);
    }
}