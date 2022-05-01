package br.edu.utfpr.agenda.banda.repository.agenda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.agenda.banda.model.AgendaDeShows;
import br.edu.utfpr.agenda.banda.repository.filter.AgendaDeShowsFilter;

public interface AgendaDeShowsRepositoryQuery {

    public Page<AgendaDeShows> filtrar(AgendaDeShowsFilter agendaDeShowsFilter, Pageable pageable);
    
}