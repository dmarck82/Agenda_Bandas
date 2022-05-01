package br.edu.utfpr.agenda.banda.repository.agenda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.agenda.banda.model.AgendaDeShows;
import br.edu.utfpr.agenda.banda.repository.filter.AgendaDeShowsFilter;

public class AgendaDeShowsRepositoryImpl implements AgendaDeShowsRepositoryQuery{
    
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<AgendaDeShows> filtrar(AgendaDeShowsFilter agendaDeShowsFilter, Pageable pageable) {
                
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<AgendaDeShows> criteria = builder.createQuery(AgendaDeShows.class);
        Root<AgendaDeShows> root = criteria.from(AgendaDeShows.class);

        Predicate[] predicates = criarRestriçoes(agendaDeShowsFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<AgendaDeShows> query = manager.createQuery(criteria);

        adicionarRestriçoesPaginação(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(agendaDeShowsFilter));
    }
    
    private Long total(AgendaDeShowsFilter agendaDeShowsFilter) {
        
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<AgendaDeShows> root = criteria.from(AgendaDeShows.class);

        Predicate[] predicates = criarRestriçoes(agendaDeShowsFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestriçoesPaginação(TypedQuery<AgendaDeShows> query, Pageable pageable) {
        
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);

    }

    private Predicate[] criarRestriçoes(AgendaDeShowsFilter agendaDeShowsFilter, CriteriaBuilder builder,
            Root<AgendaDeShows> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(agendaDeShowsFilter.getDataShowDe() != null){
            predicates.add(
                builder.greaterThanOrEqualTo(root.get("dataShow"), agendaDeShowsFilter.getDataShowDe())
            );
        }

        if(agendaDeShowsFilter.getDataShowAte() != null){
            predicates.add(
                builder.lessThanOrEqualTo(root.get("dataShow"), agendaDeShowsFilter.getDataShowAte())
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }




}
