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

import br.edu.utfpr.agenda.banda.model.Banda;
import br.edu.utfpr.agenda.banda.repository.BandaRepository;

@RestController
@RequestMapping("/banda")
public class BandaResource {
    
    @Autowired
    private BandaRepository bandaRepository;

    @GetMapping
    public List<Banda> listar(){
        return bandaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Banda> criar(@RequestBody Banda banda, HttpServletResponse response){
        Banda bandaSalvo = bandaRepository.save(banda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(bandaSalvo
        .getId_banda()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(bandaSalvo);
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
    public ResponseEntity <Banda> alterarPeloCodigo(@RequestBody Banda bandaNova, @PathVariable Long codigo){
        Optional<Banda> banda = bandaRepository.findById(codigo);
        if(banda.isPresent()){
            Banda BandaTemp = banda.get();
            BandaTemp.setNome(bandaNova.getNome());
            bandaRepository.save(BandaTemp);
            return new ResponseEntity<Banda>(BandaTemp, HttpStatus.OK);
        }
        else   
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}