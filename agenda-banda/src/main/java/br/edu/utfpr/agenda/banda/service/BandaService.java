package br.edu.utfpr.agenda.banda.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.agenda.banda.model.Banda;
import br.edu.utfpr.agenda.banda.repository.BandaRepository;

@Service
public class BandaService {
    
    @Autowired
    private BandaRepository bandaRepository;

    public Banda atualizar(@PathVariable Long codigo, @RequestBody Banda banda){
        
        Banda bandaSalva = this.bandaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(banda, bandaSalva, "codigo");

        return this.bandaRepository.save(bandaSalva);
    }
}
