package com.tallerone.main;

public class ecuacionCuadratica {
	public static void main(String[] args) {
		System.out.println("Introduce el valor de a");
		float a= Input.scannFloat();
		System.out.println("Introduce el valor de b");
		float b= Input.scannFloat();
		System.out.println("Introduce el valor de c");
		float c= Input.scannFloat();
		c=(float) (Math.exp(b) - (4 * a * c));
		System.out.println(c);
		if(c<0){
			System.out.println(" la raiz es negativa");
			c=-c;
			c= (float) (Math.sqrt(c));
			float xPositivo=(-b) / (2 * a);
			float xNegativo =(-b) / (2 * a);
			System.out.println("El valor positivo es: " + xPositivo +"+" + c + "i\nEl valor negativo es: " + xNegativo + "-" + c +"i");
		}
		else{
			c= (float) (Math.sqrt(c));
			float xPositivo=(-b + c) / (2 * a);
			//valor negativo
			c =(-b -c) / (2 * a);
		System.out.println("El valor positivo es: " + xPositivo + "\nEl valor negativo es: " +  c);
		}
	}
}

