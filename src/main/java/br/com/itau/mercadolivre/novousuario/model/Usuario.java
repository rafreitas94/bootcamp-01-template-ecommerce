package br.com.itau.mercadolivre.novousuario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    private String login;
    @NotEmpty
    @Length(min = 6)
    private String senha;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime instanteCadastrado = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotEmpty @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCadastrado() {
        return instanteCadastrado;
    }
}
