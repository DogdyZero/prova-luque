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
			
			
		} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();
		}
		
		
		return null;
	}
	private void verificarSubClasse(Class classe) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		
		Field[] f = classe.getDeclaredFields();
		System.out.println("{");
		for(Field campos:f) {
			campos.setAccessible(true);
			this.valorAtributo = campos.get(obj);
			this.tipoAtributo = campos.getType();
			
			if(!verificarTiposCompostos(f))
				System.out.println("\""+campos.getName()+"\" :"+"\""+campos.get(obj)+"\",");
			
		}
		verificarSuperClasse(classe);
	}
	
	private void verificarSuperClasse(Class classe) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		Field[] f = classe.getSuperclass().getDeclaredFields();
		for(Field campos:f) {
			campos.setAccessible(true);
			this.valorAtributo = campos.get(obj);
			this.tipoAtributo = campos.getType();
			if(!verificarTiposCompostos(f))
				System.out.println("\""+campos.getName()+"\" :"+"\""+campos.get(obj)+"\",");

		}
		sb.append("");
		sb.append("}");
		
	}
	private boolean verificarTiposCompostos(Field[] campos) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		if(this.tipoAtributo.toString().contains("[L")) {
			verificarArray(campos);
			return true;
		}
			
		else if(this.tipoAtributo.toString().equals("class br.com.sistema.Estado")) {
			verificarObjeto(campos);
			return true;
		}
			
		return false;
	}
	
		private void verificarObjeto(Field[] campos) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		Class<?> classeFilha =(Class<?>)this.tipoAtributo;
		sb.append(":{");
		Field[] ff = classeFilha.getDeclaredFields();
		obj = this.valorAtributo;
		for(Field campoFilha: ff ) {
			this.nomeAtributo= campoFilha.getName();
			campoFilha.setAccessible(true);
			campoFilha.get(this.valorAtributo);
			this.tipoAtributo = campoFilha.getType();
			if(!verificarTiposCompostos(ff))
				System.out.println("\""+campoFilha.getName()+"\" :"+"\""+campoFilha.get(obj)+"\",");

		}
	}
	public void verificarArray(Field[] campos) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		
		for (Object obj : (Object[]) this.valorAtributo)
		{
			if (obj== null)
				break;
			Class classeFilha;
			sb.append(":{");
			classeFilha = Class.forName(obj.getClass().getCanonicalName());
			Field[] ff = classeFilha.getDeclaredFields();

			for(Field campoFilha: ff ) {
				this.nomeAtributo= campoFilha.getName();
				campoFilha.setAccessible(true);
				this.valorAtributo = campoFilha.get(obj);
				this.tipoAtributo = campoFilha.getType();
				if(!verificarTiposCompostos(ff))
					System.out.println("\""+campoFilha.getName()+"\" :"+"\""+campoFilha.get(obj)+"\",");

			}
		}
			
		
		
		
	}
}
