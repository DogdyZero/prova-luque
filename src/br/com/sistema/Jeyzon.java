package br.com.sistema;

import java.io.IOException;
import java.lang.reflect.Field;


public class Jeyzon {
	public String geradorJson(Cliente clientes) throws ClassNotFoundException, IOException, IllegalArgumentException, IllegalAccessException {
		
		StringBuilder sb = new StringBuilder();
		String nomeClasse = clientes.getClass().getName();
		Class<?> classe = Class.forName(nomeClasse);
		
		Field[] f = classe.getDeclaredFields();
		sb.append("{");
		for(Field campos:f) {
			campos.setAccessible(true);
			
			sb.append("\""+campos.getName()+"\" :");
			sb.append("\""+campos.get(clientes)+"\",");
		}
		sb.append("");
		
		Field[] fSuper = classe.getSuperclass().getDeclaredFields();
		for(Field campos:fSuper) {
			campos.setAccessible(true);
			Object valorAtributo = campos.get(clientes);
			Object tipoAtributo = campos.getType();
			
			if (tipoAtributo.toString().contains("[L")) {
				
				sb.append("\""+campos.getName()+"\": [{");
				for (Object obj : (Object[]) valorAtributo)
				{
					if (obj== null)
						break;

					Class classeFilha;
					try
					{
						classeFilha = Class.forName(obj.getClass().getCanonicalName());
						Field[] camposFilha = classeFilha.getDeclaredFields();
						String nomeAtributoFilha = "";
						Object valorAtributoFilha = null;
						for (Field campoFilha : camposFilha)
						{
							try
							{
								nomeAtributoFilha = campoFilha.getName();
								campoFilha.setAccessible(true);
								valorAtributoFilha = campoFilha.get(obj); 
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
							
							sb.append("\""+nomeAtributoFilha+"\" :");
							sb.append("\""+valorAtributoFilha+"\",");
							//System.out.println("(Filhafilha) " + nomeAtributoFilha + ": "
							//		+ valorAtributoFilha);
						}
					}
					catch (ClassNotFoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sb = sb.delete(sb.toString().length()-1, sb.toString().length());
					sb.append("},{");


				}
				sb = sb.delete(sb.toString().length()-2, sb.toString().length());
				sb.append("]");
				
			}
			
			//sb.append("\""+campos.getName()+"\" :");
			//sb.append("\""+campos.get(clientes)+"\"\n}");
			
		}
		sb.append("");
		sb.append("}");
		
		return sb.toString();
		
	}
}
