package com.tallerone.main;

public class fibonacci {
	public static void main(String[] args) {
		int n= Input.scannInt();
		int resultado=0;
		int anterior;
		if(n==1){
			resultado=1;
		}
		else if(n==2){
			resultado=2;
		}
		else if(n>2){
			resultado=2;
			anterior=1;
			for(int i=0;i<n-2;i++){
				resultado=anterior+resultado;
				anterior=resultado-anterior;
			}
		}
		System.out.println(resultado);
		
	}
}
