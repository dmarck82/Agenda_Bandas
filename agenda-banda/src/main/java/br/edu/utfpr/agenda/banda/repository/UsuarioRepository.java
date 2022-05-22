package br.edu.utfpr.agenda.banda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.agenda.banda.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

    public Optional<Usuario> findByEmail(String email);
}