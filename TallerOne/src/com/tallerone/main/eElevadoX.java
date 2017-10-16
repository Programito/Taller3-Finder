package com.tallerone.main;

public class eElevadoX {
	 public static void main(String[] args) {
		 int i;
		 //int n=  Input.scannInt();
		 int n=3;
		 while(n<0){
			 System.out.println("el numero introducido no puede ser negativo");
			 n=  Input.scannInt();
		 }
		 float factorial=1;
		 float x=(float)n;
		 float resultado=1;
		 
		 boolean sobrepaso=false;
		 for(i=0;i<n; i++){
			 //System.out.println("valor i:" + i);
			 if(i>0){
				 x*=x;
			 }
			
			factorial= (i+1) * factorial;
		
			 
			 resultado=resultado + (x/factorial);
			 /*System.out.println("Valor x:" + x);
			 System.out.println("Valor factorial:" + factorial);
			 System.out.println("sumar: " + x/factorial);
			 System.out.println(resultado);*/
			
		 }
		 
		 System.out.println(resultado);
		 
	 }
}
