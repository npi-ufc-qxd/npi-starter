package ufc.quixada.npi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.ufc.quixada.npi.ldap", "ufc.quixada.npi"})
public class NpiStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NpiStarterApplication.class, args);
	}
}
