package ordenamientos;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;
import smartcities.Anho;
import smartcities.ComparadorAnho;


public class InsertionSort <T extends IdentificadoUnicamente>
{
	public ListaSencillamenteEncadenada<T> sort(ListaSencillamenteEncadenada<T> list)
	{
		return insertionSort(list);
	}

	/*
     Ordena un arreglo de enteros, usando Ordenamiento por inserci�n.
     @param arr Arreglo de enteros.
	 **/
	private ListaSencillamenteEncadenada<T> insertionSort(ListaSencillamenteEncadenada<T> arr)
	{
		ComparadorAnho comparador = new ComparadorAnho();
		for (int i=1; i<arr.size(); i++){
			for (int j=i; j>0; j--){
				if (comparador.compare((Anho)arr.get(j) , (Anho)arr.get(j-1))<0){
					T temp = arr.get(j-1);
					arr.set(j-1, arr.get(j));
					arr.set(j, temp);
				}

			}
		}
		return arr;
	}



}
