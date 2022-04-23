package br.edu.utfpr.agenda.banda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.agenda.banda.model.Banda;


public interface BandaRepository extends JpaRepository <Banda, Long>{

}
