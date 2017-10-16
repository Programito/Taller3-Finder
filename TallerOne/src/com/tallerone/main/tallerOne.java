package com.tallerone.main;

public class tallerOne {
	/**
	 * Suma dos valores dados por el usuario
	 * @param args
	 */
	public static void main(String[] args){
	//Primitivos: int,char,float,double
	//String	
	
	try{
		String str0=args[0];
		String str1=args[1];
		int valor0= Integer.valueOf(str0);
		int valor1=Integer.valueOf(str1);
		System.out.println("La suma de " + valor0 + " + " + valor1 + " = " + (valor0+valor1));
	}catch(NumberFormatException e){
		showErrorFormat();
	}catch(RuntimeException e){
		showErrorFormat();
	}
		
	}
	
	private static void showErrorFormat(){
		System.out.println("Debe digitar almenos 2 numeros separados"
				+ "separados por espacio");
	}
}
