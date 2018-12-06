package br.com.sistema;

import java.util.Date;

public class Cliente extends PessoaFisica{
	
    private String numeroCliente;
    @Formate 
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
