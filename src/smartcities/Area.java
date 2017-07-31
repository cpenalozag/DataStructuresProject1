package smartcities;

import java.util.Collections;

import ordenamientos.QuickSort;
import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;

public class Area implements IdentificadoUnicamente{
	
	/**
	 * Nombre del area
	 */
	private String nombre;
	/**
	 * Lista de años que tiene el area
	 */
	private ListaSencillamenteEncadenada<Anho> anhos;
	
	/**
	 * Ordenamiento quickSort
	 */
	private QuickSort<Anho> qS = new QuickSort<>();
	
	/**
	 * Construye una nueva area  e inicializa la lista de años
	 * @param pNombre
	 */
	public Area (String pNombre)
	{
		nombre = pNombre;
		anhos = new ListaSencillamenteEncadenada<>();
	}
	
	/**
	 * Agrega un año a la lista de años
	 * @param pAnho Anho que se desea agregar
	 */
	public void agregarAnho(Anho pAnho)
	{
		anhos.add(pAnho);
	}
	
	/**
	 * Retorna el identificador del area
	 * @return Identificador del area
	 */
	public String darIdentificador()
	{
		return nombre;
	}
	
	/**
	 * Retorna la cantidad de colisiones Serious que tuvo el area en el periodo que entra por parametro
	 * @param anhoMenor Limite inferior del periodo
	 * @param anhoMayor Limite superior del periodo
	 * @return Numero de colisiones serious en el periodo
	 */
	public int indiceColisionesSerious(int anhoMenor, int anhoMayor)
	{
		qS.sortB(anhos);
		int indice = 0;
		ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> ite = anhos.darIteradorSencillo();
		Anho actual = null;
		while(ite.hasNext())
		{
			actual = ite.next();
			if(actual.darAnho() >= anhoMenor && actual.darAnho() <= anhoMayor)
				indice += actual.darCantidadSerious();
		}
		return indice;
	}
	/**
	 * Retorna el historial de colisiones Serious que tiene el area
	 * @return Historial de colisiones Serious del area
	 */
	public String historialColisionesSerious()
	{
		qS.sortB(anhos);
		String historial = "";
		ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> ite = anhos.darIteradorSencillo();
		Anho actual = null;
		while (ite.hasNext())
		{
			actual = ite.next();
			historial = historial + ("En el año " + actual.darAnho() + " ocurrieron " + actual.darCantidadSerious() + " colisiones Serious." + "\n");
		} 
		return historial;
	}
	/**
	 * Retorna el promedio de colisiones que tiene el area por año	
	 * @return Promedio de colisiones Serious del area por año
	 */
	public double promedioColisionesSerious()
	{
		qS.sortB(anhos);
		int suma = 0;
		ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> ite = anhos.darIteradorSencillo();
		Anho actual = null;
		while(ite.hasNext())
		{
			actual = ite.next();
			suma += actual.darCantidadSerious();
		}
		return suma/anhos.size();
	}
	
	/**
	 * Retorna el ultimo reporte de colisiones serious que haya tenido el area
	 * @return Retorna el ultimo reporte de colisiones Serious del area
	 */
	
	public String darColisionSeriousMasReciente()
	{
		qS.sortB(anhos);
		Anho actual = null;
		ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> ite = anhos.darIteradorSencillo();
		while(ite.hasNext())
			actual = ite.next();
		return "El numero de colisiones Serious mas reciente es :" + actual.darCantidadSerious() + " y ocurrio en el año " + actual.darAnho(); 
	}
}