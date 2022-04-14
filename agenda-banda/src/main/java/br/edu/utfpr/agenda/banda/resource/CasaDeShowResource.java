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

import br.edu.utfpr.agenda.banda.model.CasaDeShow;
import br.edu.utfpr.agenda.banda.repository.CasaDeShowRepository;

@RestController
@RequestMapping("/casadeshow")
public class CasaDeShowResource {
    
    @Autowired
    private CasaDeShowRepository casaDeShowRepository;

    @GetMapping
    public List<CasaDeShow> listar(){
        return casaDeShowRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<CasaDeShow> criar(@RequestBody CasaDeShow casaDeShow, HttpServletResponse response){
        CasaDeShow casaDeShowSalvo = casaDeShowRepository.save(casaDeShow);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(casaDeShow
        .getId_casa_de_show()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(casaDeShowSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity <?> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<CasaDeShow> casaDeShow = this.casaDeShowRepository.findById(codigo);

        return casaDeShow.isPresent() ? ResponseEntity.ok(casaDeShow) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public void deletarPeloCodigo(@PathVariable Long codigo){
        casaDeShowRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity <CasaDeShow> alterarPeloCodigo(@RequestBody CasaDeShow casaDeShowNova, @PathVariable Long codigo){
        Optional<CasaDeShow> casaDeShowVelha = casaDeShowRepository.findById(codigo);
        if(casaDeShowVelha.isPresent()){
            CasaDeShow casaDeShowTemp = casaDeShowVelha.get();
            casaDeShowTemp.setNome(casaDeShowNova.getNome());
            casaDeShowTemp.setTelefone(casaDeShowNova.getTelefone());
            casaDeShowTemp.setResponsavel(casaDeShowNova.getResponsavel());
            casaDeShowTemp.setEmail(casaDeShowNova.getEmail());
            casaDeShowRepository.save(casaDeShowTemp);
            return new ResponseEntity<CasaDeShow>(casaDeShowTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}