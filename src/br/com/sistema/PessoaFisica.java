package br.com.sistema;

import br.com.sistema.util.Atributo;
import br.com.sistema.util.Tipo;

@Tipo("cliente")
public class PessoaFisica {
	
    private String nome;
    private RG[] rg;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RG[] getRg() {
        return rg;
    }

    public void setRg(RG[] rg) {
        this.rg = rg;
    }
	
	
}
