package smartcities;

import estructuras.IdentificadoUnicamente;

public class ComparadorAnho {


	public ComparadorAnho(){

	}
	/**
	 * Compara dos años por el el numero y el ward.
	 * @param a1 primer año para comparar. a1 != null.
	 * @param a2 segundo año para comparar. a2 != null.
	 * @return Retorna -1 si el año a2 tiene un valor "MAYOR" que a1. <br>
	 *         Retorna 1 si el año a2 tiene un valor "MENOR" que a1.
	 */

	public int compare ( Anho a1, Anho a2 )
	{
		int valorComparacion = -2;
		if (a1.darNombreLocalAuthority().compareToIgnoreCase(a2.darNombreLocalAuthority())>0){
			valorComparacion = 1;
		}
		else if (a1.darNombreLocalAuthority().compareToIgnoreCase(a2.darNombreLocalAuthority())<0){
			valorComparacion = -1;
		}
		else if (a1.darNombreLocalAuthority().equals(a2.darNombreLocalAuthority())){
			if (a1.darAnho()>a2.darAnho()){
				valorComparacion = 1;
			}
			else if (a1.darAnho()<a2.darAnho()){
				valorComparacion = -1;
			}
			else if (a1.darAnho()==a1.darAnho()){
				if (a1.darIdentificador().compareTo(a2.darIdentificador())<0){
					valorComparacion = 1;
				}
				else if (a1.darIdentificador().compareTo(a2.darIdentificador())>0){
					valorComparacion = -1;
				}
			}
		}
		return valorComparacion;
	}
	/**
	 * Compara dos años por el el numero y area.
	 * @param a1 primer año para comparar. a1 != null.
	 * @param a2 segundo año para comparar. a2 != null.
	 * @return Retorna -1 si el año a2 tiene un valor "MAYOR" que a1. <br>
	 *         Retorna 1 si el año a2 tiene un valor "MENOR" que a1.
	 */
	public int compareB ( Anho a1, Anho a2 )
	{
		int valorComparacion = -2;
		if (a1.darArea().compareToIgnoreCase(a2.darArea())>0){
			valorComparacion = 1;
		}
		else if (a1.darArea().compareToIgnoreCase(a2.darArea())<0){
			valorComparacion = -1;
		}
		else if (a1.darArea().equalsIgnoreCase(a2.darArea())){
			if (a1.darAnho()>a2.darAnho()){
				valorComparacion = 1;
			}
			else if (a1.darAnho()<a2.darAnho()){
				valorComparacion = -1;
			}
		}
		return valorComparacion;
	}
	
}
