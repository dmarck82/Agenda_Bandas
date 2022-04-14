package br.edu.utfpr.agenda.banda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_casa_de_show")
public class CasaDeShow {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_casa_de_show;
    private String nome;
    private String telefone;
    private String responsavel;
    private String email;

    public Long getId_casa_de_show() {
        return id_casa_de_show;
    }
    public void setId_casa_de_show(Long id_casa_de_show) {
        this.id_casa_de_show = id_casa_de_show;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_casa_de_show == null) ? 0 : id_casa_de_show.hashCode());
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
        CasaDeShow other = (CasaDeShow) obj;
        if (id_casa_de_show == null) {
            if (other.id_casa_de_show != null)
                return false;
        } else if (!id_casa_de_show.equals(other.id_casa_de_show))
            return false;
        return true;
    }    
}
