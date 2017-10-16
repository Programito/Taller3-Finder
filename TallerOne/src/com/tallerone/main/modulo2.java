package com.tallerone.main;

public class modulo2 {

	/**
	 * * Suma dos valores dados por el usuario
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Primitivos: int,char,float,double
		// String
		try {
			String str0 = args[0];
			int valor0 = Integer.valueOf(str0);
			if(valor0 % 2 ==0){
				System.out.println("El " + valor0 + " es par");
			}
			else{
				System.out.println("El " + valor0 + " es impar");
			}
		} catch (NumberFormatException e) {
			showErrorFormat(false);
		} catch (RuntimeException e) {
			showErrorFormat(true);
		}
	}

	private static void showErrorFormat(boolean b) {
		if(b){
			System.out.println("Necesitas un valor de entrada");
		}
		else{
			System.out.println("La entrada no es un numero");	
		}
	}
}
