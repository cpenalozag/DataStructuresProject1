package ordenamientos;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;
import smartcities.Anho;
import smartcities.ComparadorAnho;


public class Hybrid <T extends IdentificadoUnicamente>
{
	public ListaSencillamenteEncadenada<T> sort(ListaSencillamenteEncadenada<T> list)
	{
		hybridSort(list, 0, list.size()-1);
		return list;
	}

	public void hybridSort(ListaSencillamenteEncadenada<T> lista, int inicio, int fin)
	{
		int size = (fin+1) - inicio;
		if (inicio < fin){
			if (size <= 10){
				InsertionSort<T> i = new InsertionSort<>();
				i.sort(lista);
			}
			else{
				QuickSort<T> q = new QuickSort<>();
				q.sort(lista);
			}
		}
	}
}
