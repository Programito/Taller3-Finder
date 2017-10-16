package com.tallerone.main;
import java.util.InputMismatchException;

public class signo {
	
	 public static void main(String[] args) {
		
			 
			 System.out.println("0 introduce entero \n1 introduce float \n2 introduce String ");
			 int opcion=Input.scannInt();
			 int var=0;
			 if(opcion==0){
				 var= Input.scannInt();
				 if(var<0){
					 System.out.println("el numero es negativo");
				 }else if(var == 0){
					 System.out.println("el numero es 0");
				 }else{
					 System.out.println("El numero es positivo");
				 }
			 }else if(opcion==1){
				 float varFloat= Input.scannFloat();
				 if(varFloat<0){
					 System.out.println("el numero es negativo");
				 }else if(varFloat == 0){
					 System.out.println("el numero es 0");
				 }else{
					 System.out.println("El numero es positivo");
				 }
			 }else{
				 String varString=Input.scannLine();
				 var=Integer.parseInt(varString);
				 if(var<0){
					 System.out.println("el numero es negativo");
				 }else if(var == 0){
					 System.out.println("el numero es 0");
				 }else{
					 System.out.println("El numero es positivo");
				 }
			 }
			 
		
	 }

}
