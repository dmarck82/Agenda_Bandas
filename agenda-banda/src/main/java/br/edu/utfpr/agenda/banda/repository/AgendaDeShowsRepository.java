package br.edu.utfpr.agenda.banda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.agenda.banda.model.AgendaDeShows;
import br.edu.utfpr.agenda.banda.repository.agenda.AgendaDeShowsRepositoryQuery;

public interface AgendaDeShowsRepository extends JpaRepository <AgendaDeShows, Long>, AgendaDeShowsRepositoryQuery{
    
}
