package com.br.zup.mercadolivre.usuario.cadastrausuario;

import com.br.zup.mercadolivre.config.validacao.UniqueValue;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class CadastraUsuarioRequest {

    @Email @NotBlank @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Usuário já cadastrado no sistema!")
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;

    public CadastraUsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.email = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(email, senha);
    }
}
