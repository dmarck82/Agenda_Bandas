package br.edu.utfpr.agenda.banda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.agenda.banda.model.AgendaDeShows;

public interface AgendaDeShowsRepository extends JpaRepository <AgendaDeShows, Long>{
    
}
