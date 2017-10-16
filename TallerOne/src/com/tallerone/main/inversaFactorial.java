package com.tallerone.main;

public class inversaFactorial {
	 public static void main(String[] args) {
		 int n=  Input.scannInt();
		 float factorial=1;
		 float resultado=0;
		 float j=1;
	if(n>0){
		resultado=1;
		for(int i=1;i<n; i++){
			System.out.println("j: "+ j);
			factorial= (j+2) * (j+1) * factorial;
			j=j+2;
			
			if(i%2==1){
				resultado= resultado - ((i+1)/factorial);
				//System.out.println("factorial:" + factorial);
				//System.out.println("resta");
			}
			else{
				resultado = resultado + ((i+1)/factorial);
				//System.out.println("factorial:" + factorial);
				//System.out.println("suma");
			}
				
		}
	}
	
	System.out.println("resultado: "+ resultado);
	 
	}
}
