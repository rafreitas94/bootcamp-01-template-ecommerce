package br.com.itau.mercadolivre.config.jwt;

import br.com.itau.mercadolivre.novousuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);
}
