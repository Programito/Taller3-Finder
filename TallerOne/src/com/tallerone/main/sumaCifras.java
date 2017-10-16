package com.tallerone.main;

public class sumaCifras {
    public static void main(String[] args) {
    	
		try {
			String str0 = args[0];
			int valor0 = Integer.valueOf(str0);
			System.out.print("El resultado de la suma de cifras del numero "+ valor0);
			int resultado=0;
			while(valor0>0){
				resultado= valor0 %10 + resultado;
				valor0= valor0/10;
			}
			System.out.println(" es: " + resultado);
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
