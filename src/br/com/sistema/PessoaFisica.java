package br.com.sistema;

import br.com.sistema.util.Atributo;
import br.com.sistema.util.Tipo;

@Tipo("cliente")
public class PessoaFisica {
	
	@Atributo ("RG")
	private RG[] rg;

	public RG[] getRg() {
		return rg;
	}

	public void setRg(RG[] rg) {
		this.rg = rg;
	}
	
	
}
