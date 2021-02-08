package com.br.zup.mercadolivre.usuario.cadastrausuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Representa uma senha limpa no sistema
 */
public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        Assert.hasLength(senha, "Senha não pode ser em branco");
        Assert.isTrue(senha.length() >= 6, "Senha precisa ter 6 ou mais caracteres");
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(senha);
    }

    public String getSenha() {
        return senha;
    }
}
