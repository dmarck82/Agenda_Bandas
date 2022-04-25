package br.edu.utfpr.agenda.banda.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_agenda_de_shows")
public class AgendaDeShows {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_agenda_de_shows;

    @NotNull
    private LocalDate data;

    @NotNull
    private Float cache;

    @ManyToOne
    @JoinColumn(name = "codigo_banda")
    private Banda banda;
    
    @ManyToOne
    @JoinColumn(name = "codigo_casa_de_show")
    private CasaDeShow casaDeShow;

    public Long getId_agenda_de_shows() {
        return id_agenda_de_shows;
    }
    public void setId_agenda_de_shows(Long id_agenda_de_shows) {
        this.id_agenda_de_shows = id_agenda_de_shows;
    }
    public Float getCache() {
        return cache;
    }
    public void setCache(Float cache) {
        this.cache = cache;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Banda getBanda() {
        return banda;
    }
    public void setBanda(Banda banda) {
        this.banda = banda;
    }
    public CasaDeShow getCasaDeShow() {
        return casaDeShow;
    }
    public void setCasaDeShow(CasaDeShow casaDeShow) {
        this.casaDeShow = casaDeShow;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_agenda_de_shows == null) ? 0 : id_agenda_de_shows.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AgendaDeShows other = (AgendaDeShows) obj;
        if (id_agenda_de_shows == null) {
            if (other.id_agenda_de_shows != null)
                return false;
        } else if (!id_agenda_de_shows.equals(other.id_agenda_de_shows))
            return false;
        return true;
    }
}
