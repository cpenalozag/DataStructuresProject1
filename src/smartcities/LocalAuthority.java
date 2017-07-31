package smartcities;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;

public class LocalAuthority implements IdentificadoUnicamente
{
	/**
	 * Nombre del local authority
	 */
	private String nombre;
	/**
	 * Lista de wards que tiene el local authority
	 */
	private ListaSencillamenteEncadenada<Ward> wards;
	/**
	 * Inicializa un nuevo local Authority y la lista de wards
	 * @param pNombre Nombre del local authority
	 */
	public LocalAuthority(String pNombre){
		nombre=pNombre;
		wards = new ListaSencillamenteEncadenada<>();

	}
	/**
	 * Agrega un ward a la lista de wards
	 * @param pWard Ward que se quiere agregar
	 */
	public void agregarWard(Ward pWard){
		wards.add(pWard);
	}
	/**
	 * Retorna el identificador del local authority
	 */
	public String darIdentificador() {
		return nombre;
	}
	
	/**
	 * Retorna la lista de wards 
	 * @return Lista wards
	 */
	
	public ListaSencillamenteEncadenada<Ward> darListaWards()
	{
		return wards;
	}
}
