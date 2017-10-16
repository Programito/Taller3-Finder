package com.tallerone.main;

public class Factorial {
	 public static void main(String[] args) {
		 System.out.println("El numero maximo es:" + Integer.MAX_VALUE);
		 int i;
		 int n=  Input.scannInt();
		 while(n<0){
			 System.out.println("el numero introducido no puede ser negativo");
			 n=  Input.scannInt();
		 }
		 int resultado=1;
		
		 
		 boolean sobrepaso=false;
		 for(i=0;i<n; i++){
			 
			 long tem = (i+1) * (long)resultado; 
			 
			 if(tem < Integer.MAX_VALUE){
				 resultado= (i+1) * resultado;
			 }else{
				 sobrepaso=true;
				 break;
			 }
			
		 }
		 
		 if(sobrepaso){
			 System.out.println("la entrada " + n +" es demasiado grande. El valor maximo de n es 12");
		 }
		 else{
			 System.out.println(resultado);
		 }
		 
	 }
}
