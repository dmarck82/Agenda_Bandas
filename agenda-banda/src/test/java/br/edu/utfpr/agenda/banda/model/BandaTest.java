package br.edu.utfpr.agenda.banda.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BandaTest {

	@Test
	@DisplayName("Verifica se a inserção de ID's é valida")
	public void setIdValida(){
		final Banda instance = new Banda();
		final long esperado = 123;

		instance.setId_banda(esperado);
		final Long obtido = instance.getId_banda();
		assertEquals(esperado, obtido);
	}

	@Test
	@DisplayName("Verifica a inserção de nomes")
	public void setNomeValido(){
		final Banda instance = new Banda();
		final String esperado = "abcdefghijklmnopqrstuvxywz";
		
		instance.setNome(esperado);
		final String obtido = instance.getNome();
		assertEquals(esperado, obtido);
	}

	@Test
	@DisplayName("Verifica se existe integrantes na banda")
	public void verificaIntegrantesBanda(){
		final Banda instance = new Banda();
		assertNotNull(instance.getIntegrantes());
	}
}
