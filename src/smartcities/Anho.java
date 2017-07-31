package smartcities;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;

public class Anho implements IdentificadoUnicamente
{

	/**
	 * Cantidad de colisiones leves
	 */
	private int slight;
	/**
	 * Cantidad de colisiones fatales
	 */
	private int fatal;
	/**
	 * Cantidad de colisiones serias
	 */
	private int serious;
	/**
	 * Cantidad de colisiones total
	 */
	private int total;
	/**
	 * Nombre de la autoridad local
	 */
	private String localAuthority;
	/**
	 * Nombre del ward
	 */
	private String ward;

	/**
	 * Numero del año
	 */
	private int anho;

	/**
	 * Area a la que pertenece el a�o
	 */
	private String area;

	/**
	 * Constructor año parte A
	 * @param pAnho Año del registro de colisiones
	 * @param pSlight Cantidad de accidentes slight
	 * @param pFatal Cantidad de accidentes fatal
	 * @param pSerious Cantidad de accidentes serious
	 * @param pTotal Cantidad de accidentes total
	 * @param pWard Nombre del ward
	 * @param pLocalAuthority Nombre de la local authority
	 */
	public Anho (int pAnho, int pSlight, int pFatal, int pSerious, int pTotal, String pWard, String pLocalAuthority)
	{
		anho = pAnho;
		slight = pSlight;
		fatal = pFatal;
		serious = pSerious;
		total = pTotal;
		localAuthority = pLocalAuthority;
		ward = pWard;
	}

	/**
	 * Constructor año parte A
	 * @param pAnho Año del registro de colisiones
	 * @param pSlight Cantidad de accidentes slight
	 * @param pFatal Cantidad de accidentes fatal
	 * @param pSerious Cantidad de accidentes serious
	 * @param pTotal Cantidad de accidentes total
	 * @param pArea Nombre del area al que pertenece 
	 */
	public Anho (int pAnho, int pSlight, int pFatal, int pSerious, int pTotal, String pArea)
	{
		anho = pAnho;
		slight = pSlight;
		fatal = pFatal;
		serious = pSerious;
		total = pTotal;
		area = pArea;
	}

	/**
	 * Retorna el Area
	 * 
	 */
	public String darArea()
	{
		return area;
	}

	/**
	 * Retorna el año 
	 */
	public int darAnho(){
		return anho;
	}

	/**
	 * Retorna la cantidad de colisiones leves
	 * @return cantidad de colisiones leves
	 */
	public int darCantidadSlight()
	{
		return slight;
	}
	/**
	 * Retorna la cantidad de colisiones leves
	 * @return cantidad de colisiones leves
	 */
	public int darCantidadFatal()
	{
		return fatal;
	}
	/**
	 * Retorna la cantidad de colisiones leves
	 * @return cantidad de colisiones leves
	 */
	public int darCantidadSerious()
	{
		return serious;
	}
	/**
	 * Retorna la cantidad de colisiones leves
	 * @return cantidad de colisiones leves
	 */
	public int darCantidadTotal()
	{
		return total;
	}
	
	/**
	 * Retorna el ward al que pertenece
	 * @return Ward al que pertenece
	 */

	public String darNombreWard(){
		return ward;
	}
	/**
	 * Retorn el nombre del local Authority al que pertenece
	 * @return Local authority al que pertenece
	 */
	
	public String darNombreLocalAuthority(){
		return localAuthority;
	}

	/**
	 * Cambiar cantidad colisiones leves
	 * @param Nueva cantidad de colisiones leves
	 */
	public void cambiarCantidadSlight(int pLeves)
	{
		slight = pLeves;
	}

	/**
	 * Cambiar cantidad colisiones fatales
	 * @param Nueva cantidad de colisiones fatales
	 */
	public void cambiarCantidadFatal(int pFatal)
	{
		fatal = pFatal;
	}

	/**
	 * Cambiar cantidad colisiones serias
	 * @param Nueva cantidad de colisiones serias
	 */
	public void cambiarCantidadSerious(int pSerious)
	{
		serious = pSerious;
	}
	
	/**
	 * Retorna el identificador del anho
	 * @return Identificador del a�o
	 */
	public String darIdentificador() {
		return "" + anho;
	}

	/**
	 * Retorna la informacion de colisiones slight del a�o
	 * @return Informacion de colisiones slight del a�o
	 */
	public String darInfoSlight(){
		return anho + ": " + darCantidadSlight();
	}
	
	/**
	 * Retorna toda la informacion del a�o
	 */
	public String darInfo(){
		return anho + " " + fatal + " " + slight + " " + serious;
	}

}
