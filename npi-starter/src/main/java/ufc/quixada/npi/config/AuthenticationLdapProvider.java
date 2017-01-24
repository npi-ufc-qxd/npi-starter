package ufc.quixada.npi.config;

import br.ufc.quixada.npi.ldap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import ufc.quixada.npi.model.Usuario;
import ufc.quixada.npi.repository.UsuarioRepository;

import javax.inject.Named;
import java.util.Collection;

@Named
public class AuthenticationLdapProvider  implements AuthenticationProvider {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String cpf = authentication.getName();
        String password = (String) authentication.getCredentials();

        Usuario usuario= usuarioRepository.findByCpf(cpf);

        if(usuario == null) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }

        Collection<? extends GrantedAuthority> authorities = usuarioService.getByCpf(cpf).getAuthorities();

        if (!usuarioService.autentica(cpf, password) || authorities == null || authorities.isEmpty()) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }

        return new UsernamePasswordAuthenticationToken(cpf, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
