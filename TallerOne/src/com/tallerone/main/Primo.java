package com.tallerone.main;

public class Primo {
	 public static void main(String[] args) {
		 boolean primo=false;
		 int n=  Input.scannInt();
		 if(n>1){
			 primo=true;
			 for(int i=2;i<=n/2 && primo;i++){
				 //System.out.println("La n:" + n);
				 //System.out.println("La i:" + i);
				 if(n%i==0){
					 primo=false;
				 }
			 }
		 }
		 
		 System.out.println(primo);
	 }
}
