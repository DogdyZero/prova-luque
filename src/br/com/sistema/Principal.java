package br.com.sistema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalArgumentException, IllegalAccessException {
		Cliente cli = new Cliente();
		cli.setNome("Ricardo");
		RG rg = new RG();
		rg.setNumero("123456");
		RG[] rgs = new RG[10];
		rgs[0] = rg;
		cli.setRg(rgs);
		RG rg2 = new RG();
		
		RG[] rgs2 = new RG[10];
		rg2.setNumero("123456");
		rgs2[0] = rg2;
		rg2.setNumero("452636");
		rgs2[1] = rg2;

		Cliente cli2 = new Cliente();
		cli2.setNome("Rodrigo");
		
		cli2.setRg(rgs2);
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		listaClientes.add(cli);
		listaClientes.add(cli2);
		
		Json json = new Json();
		System.out.println(json.geradorJson(cli2));

	}

}
