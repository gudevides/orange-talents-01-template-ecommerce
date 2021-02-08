package com.br.zup.mercadolivre.usuario;

import com.br.zup.mercadolivre.usuario.cadastrausuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}

