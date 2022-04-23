package br.edu.utfpr.agenda.banda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tb_integrantes")
public class Integrantes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_integrantes;

    @NotNull @Size(min = 3, max =45)
    private String nome;

    @NotNull @Size(min = 3, max = 45)
    private String sobrenome;

    @NotNull @Size(min = 3, max = 15)
    private String telefone;

    @NotNull @Size(min = 3, max = 11)
    private String cpf;

    @NotNull @Size(min = 3, max = 45) @Email
    private String email;

    @NotNull
    private Long codigo_banda;
    
    public Long getId_integrantes() {
        return id_integrantes;
    }
    public void setId_integrantes(Long id_integrantes) {
        this.id_integrantes = id_integrantes;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   
    public Long getCodigo_banda() {
        return codigo_banda;
    }
    public void setCodigo_banda(Long codigo_banda) {
        this.codigo_banda = codigo_banda;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_integrantes == null) ? 0 : id_integrantes.hashCode());
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
        Integrantes other = (Integrantes) obj;
        if (id_integrantes == null) {
            if (other.id_integrantes != null)
                return false;
        } else if (!id_integrantes.equals(other.id_integrantes))
            return false;
        return true;
    }
}
