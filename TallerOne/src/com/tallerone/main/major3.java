package com.tallerone.main;

public class major3 {
    public static void main(String[] args) {
    	
		try {
			String str0 = args[0];
			int valor0 = Integer.valueOf(str0);
			String str1 = args[1];
			int valor1 = Integer.valueOf(str1);
			String str2 = args[2];
			int valor2 = Integer.valueOf(str2);
			int resultado = valor0;
			if(resultado < valor1){
				resultado=valor1;
			}
			if(resultado < valor2){
				resultado=valor2;
			}
			System.out.println("El valor mayor de los 3 es " + resultado);
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
