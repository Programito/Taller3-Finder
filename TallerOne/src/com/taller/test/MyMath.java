package com.taller.test;

import com.tallerone.main.Input;

public class MyMath {
	
	
	/**
	 * 1!=1
	 * 2!=2
	 * 3!=2x3=6
	 * 4!=6x4=24
	 * 5!=24x5=120
	 * 
	 * @param n
	 * @return
	 */
	public static int fact(int n){
		int fact=1;
		for(int i=1;i<=n;i++){
			fact = fact* i;
		}
		return fact;
	}
	
	public static float comnputeSerie(int n){
		int num=1; 
		float fact=1;
		float sum= 0;
		int signo=1;
		
		for(int i=1;i<=n;i++){
			fact= fact*i;
			if((i%2)!=0){
				sum=sum + signo* num/fact;
				num++;
				signo=signo * -1;
			}		
		}
		
		return sum;
	}
	
	public static int[] fibonacci(int n){
		int salida[]=new int[n];
		int resultado=0;
		int anterior;
		if(n==1){
			resultado=1;
			salida[0]=resultado;
		}
		else if(n==2){
			resultado=2;
			salida[1]=resultado;
		}
		else if(n>2){
			resultado=2;
			anterior=1;
			salida[0]=anterior;
			salida[1]=resultado;
			for(int i=0;i<n-2;i++){
				resultado=anterior+resultado;
				anterior=resultado-anterior;
				salida[i+2]=resultado;
			}
		}
		return salida;
	}
}
