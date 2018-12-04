package br.com.sistema;

import br.com.sistema.util.Atributo;
import br.com.sistema.util.Tipo;

@Tipo("cliente")
public class Cliente extends PessoaFisica{
	

    private String numeroCliente;
    private Date clienteDesde;

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public Date getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(Date clienteDesde) {
        this.clienteDesde = clienteDesde;
    }
	
	
}
