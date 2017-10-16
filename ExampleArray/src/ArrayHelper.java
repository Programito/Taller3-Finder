
public class ArrayHelper {

	private int array[];
	
	public ArrayHelper(int[] array){
		this.array = array;
	}
	
	public float average (){
		int suma = 0;
		
		for (int i=0; i < array.length; i++){
			suma = suma + array[i];
		}
		
		float average = (float) suma / array.length;
		
		return average;
	}
	
	// Calcula el promedio
	public static float staticAverage(int[] array){
		int suma = 0;
		
		for (int i=0; i < array.length; i++){
			suma = suma + array[i];
		}
		
		float average = (float) suma / array.length;
		
		return average;
	}
	
}
