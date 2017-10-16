package com.taller.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TaylorTest {
	String tesStr;
	
	
	@Before
	public void before(){
		//cuando se repite
		tesStr="hola";
	}
	
	
	@Test
	public void factTest(){
		int fact=MyMath.fact(5);
		Assert.assertEquals(120, fact);
	}

	//@Test
	public void punto9Test(){
		int number=11;
		boolean actual= (number%2==0);
		Assert.assertEquals(true, actual);
	}
	
	
	@Test
	public void taylorTest(){
		float result=MyMath.comnputeSerie(4);
		Assert.assertEquals(0.6908, result,0.1);
	}
	
	@Test
	public void fibonacciTest(){
		
		//tesStr.equals("a");
	
		int[] expecteds= new int[]{1,2,3,5,8,13,21}; 
		int[] result=MyMath.fibonacci(expecteds.length);
		
		Assert.assertArrayEquals(expecteds, result);
	}
	
}
