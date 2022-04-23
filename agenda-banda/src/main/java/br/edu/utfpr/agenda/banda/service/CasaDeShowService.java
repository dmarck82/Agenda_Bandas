package br.edu.utfpr.agenda.banda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.agenda.banda.model.CasaDeShow;
import br.edu.utfpr.agenda.banda.repository.CasaDeShowRepository;

@Service
public class CasaDeShowService {
    
    @Autowired
    private CasaDeShowRepository casaDeShowRepository;

    public CasaDeShow atualizar(@PathVariable Long codigo, @RequestBody CasaDeShow casaDeShow){

        CasaDeShow casaDeShowSalvo = this.casaDeShowRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        return this.casaDeShowRepository.save(casaDeShowSalvo);
    }
}
