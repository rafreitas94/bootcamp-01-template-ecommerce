package br.com.itau.mercadolivre.config.jwt;

import br.com.itau.mercadolivre.novousuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ComercialUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuariosRepository.findByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new UsuarioSistema(usuario.getLogin(), usuario.getSenha());
    }
}
