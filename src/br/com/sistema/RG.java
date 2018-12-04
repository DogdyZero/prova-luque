package br.com.sistema;

import br.com.sistema.util.Atributo;
import br.com.sistema.util.Tipo;

@Tipo("cliente")
public class RG {
	@Atributo ("numero")
	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
