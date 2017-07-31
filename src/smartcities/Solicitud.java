package smartcities;

import estructuras.IdentificadoUnicamente;

public class Solicitud implements IdentificadoUnicamente
{
	/**
	 * Nombre del local Authority que hace la solicitud
	 */
	private String nombreLocalAuthority;
	/**
	 * Nombre del alcalde que hace la solicitud
	 */
	private String mayor;
	
	/**
	 * Crea una nueva solicitud con el nombre del local authority y del alcalde
	 * @param pNombre Nombre local Authority
	 * @param pMayor Nombre Alcalde
	 */
	public Solicitud (String pNombre, String pMayor){
		this.nombreLocalAuthority = pNombre;
		this.mayor = pMayor;
	}
	
	/**
	 * Retorna el identificador 
	 */
	public String darIdentificador() {
		return nombreLocalAuthority;
	}
	
	/**
	 * Retorna el nombre del alcalde que realizo la solicitud
	 * @return
	 */
	public String darMayor()
	{
		return mayor;
	}
}
