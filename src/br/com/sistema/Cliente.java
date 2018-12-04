package br.com.sistema;

import br.com.sistema.util.Atributo;
import br.com.sistema.util.Tipo;

@Tipo("cliente")
public class Cliente extends PessoaFisica{
	
	@Atributo ("numero")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
