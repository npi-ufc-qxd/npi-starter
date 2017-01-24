package ufc.quixada.npi.model;


import br.ufc.quixada.npi.ldap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostLoad;

public class PessoaEntityListener {

    @Autowired
    private UsuarioService usuarioService;

    @PostLoad
    public void loadPessoa(Usuario usuario) {
        AutowireHelper.autowire(this, this.usuarioService);
        br.ufc.quixada.npi.ldap.model.Usuario usuarioLdap = usuarioService.getByCpf(usuario.getCpf());
        usuario.setNome(usuarioLdap.getNome());
        usuario.setEmail(usuarioLdap.getEmail());
    }
}
