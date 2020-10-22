package br.com.itau.mercadolivre.model;

import br.com.itau.mercadolivre.validator.ExisteString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    @ExisteString(nomeAtributo = "login", classeDominio = Usuario.class)
    private final String login;
    @NotBlank
    @Length(min = 6, message = "deve ter no mínimo 6 caracteres")
    private final String senha;

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, new BCryptPasswordEncoder().encode(this.senha));
    }
}
