package br.com.sistema;

import java.lang.reflect.Field;

public class Jeyson2 {
	private StringBuilder sb;
	private String nmClasse;
	private Object obj;
	Object valorAtributo;
	Object tipoAtributo;
	Object nomeAtributo;
	
	public Jeyson2() {
		sb = new StringBuilder();
	}
	public String toString(Cliente cli) {
		this.obj =(Object) cli;
		nmClasse = obj.getClass().getName();
		try {
			Class<?> classe = Class.forName(nmClasse);
			verificarSubClasse(classe);
			sb.append("}");

			
		} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		
		return sb.toString();
	}
	private void verificarSubClasse(Class classe) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		
		Field[] f = classe.getDeclaredFields();
		sb.append("{");
		for(Field campos:f) {
			campos.setAccessible(true);
			this.valorAtributo = campos.get(obj);
			this.tipoAtributo = campos.getType();
			this.nomeAtributo = campos.getName();
			if(!verificarTiposCompostos(f))
			sb.append("\""+campos.getName()+"\" :"+"\""+campos.get(obj)+"\",");
			
		}
		verificarSuperClasse(classe);
	}
	
	private void verificarSuperClasse(Class classe) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		Field[] f = classe.getSuperclass().getDeclaredFields();
		

		for(Field campos:f) {
			campos.setAccessible(true);
			this.valorAtributo = campos.get(obj);
			this.tipoAtributo = campos.getType();
			this.nomeAtributo = campos.getName();
			if(!verificarTiposCompostos(f))
				sb.append("\""+campos.getName()+"\" :"+"\""+campos.get(obj)+"\",");

		}

	}
	private boolean verificarTiposCompostos(Field[] campos) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		if(this.tipoAtributo.toString().contains("[L")) {
			verificarArray(campos);
			sb = sb.delete(sb.toString().length()-1, sb.toString().length());

			sb.append("]");
			return true;
		}
			
		else if(this.tipoAtributo.toString().equals("class Estado")) {
			verificarObjeto(campos);
			sb = sb.delete(sb.toString().length()-1, sb.toString().length());
			sb.append("},");
			return true;
		}
			
		return false;
	}
	
	private void verificarObjeto(Field[] campos) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		Class<?> classeFilha =(Class<?>)this.tipoAtributo;
		sb.append("\""+this.nomeAtributo+"\" : {");
		Field[] ff = classeFilha.getDeclaredFields();
		obj = this.valorAtributo;
		for(Field campoFilha: ff ) {
			this.nomeAtributo= campoFilha.getName();
			campoFilha.setAccessible(true);
			campoFilha.get(this.valorAtributo);
			this.tipoAtributo = campoFilha.getType();
			if(!verificarTiposCompostos(ff))
			sb.append("\""+campoFilha.getName()+"\" :"+"\""+campoFilha.get(obj)+"\",");
		}
	}

	public void verificarArray(Field[] campos) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
	    
	    sb.append("\""+this.nomeAtributo+"\" : [ ");
		for (Object obj : (Object[]) this.valorAtributo)
		{
		    sb.append("{");
			if (obj== null)
				break;
			Class classeFilha;
			
			classeFilha = Class.forName(obj.getClass().getCanonicalName());
			Field[] ff = classeFilha.getDeclaredFields();

			for(Field campoFilha: ff ) {
				this.nomeAtributo= campoFilha.getName();
				campoFilha.setAccessible(true);
				this.valorAtributo = campoFilha.get(obj);
				this.tipoAtributo = campoFilha.getType();
				if(!verificarTiposCompostos(ff))
					sb.append("\""+campoFilha.getName()+"\" :"+"\""+campoFilha.get(obj)+"\",");
			}
			sb = sb.delete(sb.toString().length()-1, sb.toString().length());
			sb.append("},");
		}
		sb = sb.delete(sb.toString().length()-1, sb.toString().length());
		sb.append("}");
			
	}
}
