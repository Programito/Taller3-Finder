
public class ArrayMain {

	public static void main(String[] arguments) {
		// TODO Auto-generated method stub

		int  arrayInt[]; // Referenviado
		
		// primer error hay que instanciarlo
		// arrayInt[] = 12;
		// Inicializando
		arrayInt = new int[5];
		
		// Asigno  valor
		arrayInt[0]  = 100;
		arrayInt[1]  = 101;
		arrayInt[2]  = 102;
		arrayInt[3]  = 103;
		arrayInt[4]  = 104;
		
		// Calcular la media de los elemntos
		
		float average = arrayInt[0] + arrayInt[1] + arrayInt[2] + 
				        arrayInt[3] + arrayInt[4];
		
		average /=  arrayInt.length; // es lo mismo
		average = average / arrayInt.length;
		
		System.out.println("El promedio es: " + average);
		
		// Utilizo Método estático
		System.out.println("El promedio es: " + ArrayHelper.staticAverage(arrayInt));
		
		ArrayHelper helper = new ArrayHelper(arrayInt);
		System.out.println("El promedio es: " + helper.average());
	}

}
