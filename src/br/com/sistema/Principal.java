package br.com.sistema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalArgumentException, IllegalAccessException {
		Estado estadoDoRG1 = new Estado();
        estadoDoRG1.setSigla("SP");
        estadoDoRG1.setNome("Sao Paulo");

        RG rgDoCliente1 = new RG();
        rgDoCliente1.setNumero("123.456.789");
        rgDoCliente1.setOrgaoExpeditor("SSP");
        rgDoCliente1.setEstadoExpeditor(estadoDoRG1);

        Estado estadoDoRG2 = new Estado();
        estadoDoRG2.setSigla("MG");
        estadoDoRG2.setNome("Minas Gerais");

        RG rgDoCliente2 = new RG();
        rgDoCliente2.setNumero("123.456.780");
        rgDoCliente2.setOrgaoExpeditor("SSP");
        rgDoCliente2.setEstadoExpeditor(estadoDoRG2);

        Cliente cli = new Cliente();
        cli.setNome("Joao da Silva");
        cli.setNumeroCliente("12345");
        cli.setClienteDesde(new Date());
        cli.setRg(new RG[]{rgDoCliente1, rgDoCliente2});
		
        Jeyson json = new Jeyson();
        System.out.println(json.toString(cli));
	}

}
