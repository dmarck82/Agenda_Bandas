package br.edu.utfpr.agenda.banda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.agenda.banda.model.Integrantes;
import br.edu.utfpr.agenda.banda.repository.IntegrantesRepository;

@Service
public class IntegrantesService {
    
    @Autowired
    private IntegrantesRepository integrantesRepository;

    public Integrantes atualizar(@PathVariable Long codigo, @RequestBody Integrantes integrantes){
        
        Integrantes intengrantesSalvo = this.integrantesRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        return this.integrantesRepository.save(intengrantesSalvo);
    }
}
