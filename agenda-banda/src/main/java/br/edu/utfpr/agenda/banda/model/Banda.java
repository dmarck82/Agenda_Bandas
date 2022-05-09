package br.edu.utfpr.agenda.banda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_banda")
public class Banda {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_banda;

    @NotNull 
    @Size(min = 3, max = 45)
    private String nome;
    
    @OneToMany
    @JoinColumn(name = "codigo_banda")
    private List<Integrantes> integrantes = new ArrayList<>();

    public Long getId_banda() {
        return id_banda;
    }
    public void setId_banda(Long id_banda) {
        this.id_banda = id_banda;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }  
    
    public List<Integrantes> getIntegrantes() {
        return integrantes;
    }
    public void setIntegrantes(List<Integrantes> integrantes) {
        this.integrantes = integrantes;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_banda == null) ? 0 : id_banda.hashCode());
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
        Banda other = (Banda) obj;
        if (id_banda == null) {
            if (other.id_banda != null)
                return false;
        } else if (!id_banda.equals(other.id_banda))
            return false;
        return true;
    }
  
}   
