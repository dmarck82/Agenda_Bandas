package br.edu.utfpr.agenda.banda.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class AgendaDeShowsFilter {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataShowDe;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataShowAte;

    public LocalDate getDataShowDe() {
        return dataShowDe;
    }

    public void setDataShowDe(LocalDate dataShowDe) {
        this.dataShowDe = dataShowDe;
    }

    public LocalDate getDataShowAte() {
        return dataShowAte;
    }

    public void setDataShowAte(LocalDate dataShowAte) {
        this.dataShowAte = dataShowAte;
    }


    
}
