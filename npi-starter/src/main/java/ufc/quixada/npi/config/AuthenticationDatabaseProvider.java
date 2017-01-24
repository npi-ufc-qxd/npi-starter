package ufc.quixada.npi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ufc.quixada.npi.model.Usuario;
import ufc.quixada.npi.repository.UsuarioRepository;

@Component
public class AuthenticationDatabaseProvider implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCpf(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário e/ou senha inválidos");
        }
        return usuario;
    }
}
