package ordenamientos;


import java.util.Random;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;
import smartcities.Anho;
import smartcities.ComparadorAnho;

public class QuickSort <T extends IdentificadoUnicamente>
{

	private static Random random = new Random();

	public void sort(ListaSencillamenteEncadenada<T> list)
	{
		quicksort(list, 0, list.size()-1);
	}
	
	public void sortB(ListaSencillamenteEncadenada<T> list)
	{
		quicksortB(list, 0, list.size()-1);
	}
	// Trabajo en Clase

	public void quicksort(ListaSencillamenteEncadenada<T> lista, int inicio, int fin)
	{
		if (fin<=inicio)return;
		int index = particion(lista, inicio, fin);
			quicksort(lista, inicio, index - 1);
			quicksort(lista, index + 1, fin);
	}
	
	public void quicksortB(ListaSencillamenteEncadenada<T> lista, int inicio, int fin)
	{
		if (fin<=inicio)return;
		int index = particionB(lista, inicio, fin);
			quicksortB(lista, inicio, index - 1);
			quicksortB(lista, index + 1, fin);
	}

	public int particion(ListaSencillamenteEncadenada<T> lista, int inicio, int fin)
	{
		ComparadorAnho comparador = new ComparadorAnho();
		int i = inicio, j = fin+1;
		T temp;
		T pivot = lista.get(inicio);

		while (true) {
			//
			while (comparador.compare((Anho)lista.get(++i), (Anho)pivot)<0)
				if(i==fin)break;
			while (comparador.compare((Anho)lista.get(--j), (Anho)pivot)>0)
				if(j==inicio)break;
			if (i >= j)break;
			
				temp = lista.get(i);
				lista.set(i, lista.get(j));
				lista.set(j, temp);
		}
		temp = lista.get(inicio);
		lista.set(inicio, lista.get(j));
		lista.set(j,temp);

		return j;
	}
	
	public int particionB(ListaSencillamenteEncadenada<T> lista, int inicio, int fin)
	{
		ComparadorAnho comparador = new ComparadorAnho();
		int i = inicio, j = fin+1;
		T temp;
		T pivot = lista.get(inicio);

		while (true) {
			//
			while (comparador.compareB((Anho)lista.get(++i), (Anho)pivot)<0)
				if(i==fin)break;
			while (comparador.compareB((Anho)lista.get(--j), (Anho)pivot)>0)
				if(j==inicio)break;
			if (i >= j)break;
			
				temp = lista.get(i);
				lista.set(i, lista.get(j));
				lista.set(j, temp);
		}
		temp = lista.get(inicio);
		lista.set(inicio, lista.get(j));
		lista.set(j,temp);

		return j;
	}

	public int eleccionPivote(int inicio, int fin)
	{
		/**
           Este procedimiento realiza la elecci�n de un �ndice que corresponde al pivote res-
           pecto al cual se realizar�  la partici�n de la lista. Se recomienda escoger el ele-
           mento que se encuentra en la mitad, o de forma aleatoria entre los �ndices [inicio, fin).
		 **/
		int pivot = (inicio + fin)/2; 
//		int pivot = randInt(inicio, fin);
		return pivot;
	}

	/**
      Retorna un número aleatorio que se encuentra en el intervalo [min, max]; inclusivo.
      @param min, índice inicial del intervalo.
      @param max, índice final del intervalo.
      @return Un número aleatorio en el intervalo [min, max].
	 **/
	public static int randInt(int min, int max) 
	{
		int randomNum = random.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	// Trabajo en Clase

}
