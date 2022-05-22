package br.edu.utfpr.agenda.banda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.edu.utfpr.agenda.banda.config.property.AgendaBandaProperty;

@SpringBootApplication
@EnableConfigurationProperties(AgendaBandaProperty.class)
public class AgendaBandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaBandaApplication.class, args);
	}

}
