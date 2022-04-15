package br.edu.utfpr.agenda.banda.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_agenda_de_shows")
public class AgendaDeShows {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_agenda_de_shows;
    @NotNull
    private Date data;
    @NotNull
    private Float cache;
    private Long id_casa_de_show;
    private Long id_banda;
    
    public Long getId_agenda_de_shows() {
        return id_agenda_de_shows;
    }
    public void setId_agenda_de_shows(Long id_agenda_de_shows) {
        this.id_agenda_de_shows = id_agenda_de_shows;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Float getCache() {
        return cache;
    }
    public void setCache(Float cache) {
        this.cache = cache;
    }
    public Long getId_casa_de_show() {
        return id_casa_de_show;
    }
    public void setId_casa_de_show(Long id_casa_de_show) {
        this.id_casa_de_show = id_casa_de_show;
    }
    public Long getId_banda() {
        return id_banda;
    }
    public void setId_banda(Long id_banda) {
        this.id_banda = id_banda;
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
