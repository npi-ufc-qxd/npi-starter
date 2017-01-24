package ufc.quixada.npi.model;


import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority {

    ALUNO, SERVIDOR;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
