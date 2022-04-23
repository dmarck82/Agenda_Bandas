package br.edu.utfpr.agenda.banda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.agenda.banda.model.AgendaDeShows;
import br.edu.utfpr.agenda.banda.repository.AgendaDeShowsRepository;

@Service
public class AgendaDeShowService {
    
    @Autowired
    private AgendaDeShowsRepository agendaDeShowRepository;

    public AgendaDeShows atualizar(@PathVariable Long codigo, @RequestBody AgendaDeShows agendaDeShow){

        AgendaDeShows agendaDeShowSalvo = this.agendaDeShowRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        return this.agendaDeShowRepository.save(agendaDeShowSalvo);
    }
}
