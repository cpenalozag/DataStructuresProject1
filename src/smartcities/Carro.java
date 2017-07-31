package smartcities;

import java.util.Date;

import estructuras.IdentificadoUnicamente;

public class Carro implements IdentificadoUnicamente
{
	/**
	 * Color del vehiculo
	 */
	private String color;
	/**
	 * Matricula del vehiculo
	 */
	private String matricula;
	/**
	 * nombre del dueÃ±o del vehiculo
	 */
	private String nombreConductor;
	
	/**
	 * Posicion del salida del vehiculo
	 */
	private int posicionSalida;
	
	/**
	 * Posicion de entrada del vehiculo
	 */
	private int posicionEntrada;
	/**
	 * Crea el registro de un nuevo vehiculo al llegar al parqueadero
	 * @param pColor color del vehiculo
	 * @param pMatricula matricula del vehiculo
	 * @param pNombreConductor nombre de quien conduce el vehiculos
	 * @param pEntrada posicion en la que entro el carro
	 */

	public Carro (String pColor, String pMatricula, String pNombreConductor, int pEntrada) 
	{
		color = pColor;
		matricula = pMatricula;
		nombreConductor = pNombreConductor;
		posicionEntrada = pEntrada;
	}

	/**
	 * Da el color del vehiculo
	 * @return el color del vehiculo
	 */
	public String darColor()
	{
		return color;
	}
	/**
	 * Da la matricula del vehiculo
	 * @return la matricula del vehiculo
	 */
	public String darMatricula()
	{
		return matricula;
	}
	/**
	 * Da el nombre de quien conduce el vehiculo
	 * @return el nombre de quien conduce el vehiculo
	 */
	public String darNombreConductor ()
	{
		return nombreConductor;
	}

	/**
	 * Retorna la posicion de salida del vehiculo
	 * @return Posicion de salida del vehiculo
	 */
	public int darPosicionSalida()
	{
		return posicionSalida;
	}
	
	/**
	 * Asigna la posicion de salida del vehiculo
	 * @param pSalida Posicion de salida que tiene el vehiculo
	 */
	public void asignarPosicionSalida(int pSalida)
	{
		posicionSalida = pSalida;
	}
	
	/**
	 * Retorna la posicion de entrada del vehiculo
	 * @return La posicion en la que entro el vehiculo
	 */
	public int darPosicionEntrada()
	{
		return posicionEntrada;
	}
	
	/**
	 * Retorna la iniciales del nombre del dueño del vehiculo
	 * @return Iniciales del nombre del sueño del vehiculo
	 */
	public String darInicialesNombre()
	{
		String[] ini = new String[4];
		ini = nombreConductor.split(" ");
		String prim = String.valueOf(ini[0].charAt(0));
		String seg = String.valueOf(ini[1].charAt(0));
		return prim + seg;
	}
	/**
	 * Retorna el identificador del carro
	 * @return Identificador del carro 
	 */
	@Override
	public String darIdentificador() {
		// TODO Auto-generated method stub
		return matricula;		
	}	
	/** 
	 * Retorna la posicion de salida
	 * @return Posicion de salida
	 */
	public int darID()
	{
		return posicionSalida;
	}
}