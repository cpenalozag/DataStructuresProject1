package smartcities;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;

public class Ward implements IdentificadoUnicamente
{
	/**
	 * Nombre del ward
	 */
	private String nombre;
	
	/**
	 * Local authority del ward
	 */
	private String localAuthority;
	/**
	 * Lista de a�os que contiene el ward
	 */
	private ListaSencillamenteEncadenada<Anho> anhos;
	/**
	 * Crea un nuevo ward con el nombre que le entra por parametro y el local authority al que pertenece
	 * @param pNombre Nombre del ward
	 * @param pLocalAuthority Local Authority al que pertenece
	 */
	public Ward(String pNombre, String pLocalAuthority){
		nombre = pNombre;
		localAuthority = pLocalAuthority;
		anhos = new ListaSencillamenteEncadenada<>();
	}
	
	/**
	 * Agrega un a�o a la lista de a�os que contiene el ward
	 * @param pAnho A�o que se quiere agregar
	 */
	
	public void agregarAnho (Anho pAnho){
		anhos.add(pAnho);
	}
	
	/**
	 * Retorna el identificador del ward. Su nombre
	 */
	public String darIdentificador() {
		return nombre;
	}
	
	/**
	 * Retorna la lista de a�os que contiene el ward
	 * @return Lista a�os del ward
	 */
	public ListaSencillamenteEncadenada<Anho> darAnhos()
	{
		return anhos;
	}
}
