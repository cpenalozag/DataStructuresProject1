package smartcities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import estructuras.Cola;
import estructuras.ListaSencillamenteEncadenada;
import estructuras.Pila;
import ordenamientos.Hybrid;
import ordenamientos.InsertionSort;
import ordenamientos.QuickSort;

public class SmartCities {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de ingreso al parqueadero.
	 */
	public final static String ARCHIVO_INGRESO = "./data/Ingreso_al_parqueadero.csv";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de salida del parqueadero.
	 */
	public final static String ARCHIVO_SALIDA = "./data/Salida_del_parqueadero.csv";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de ingreso al parqueadero.
	 */
	public final static String ARCHIVO_SOLICITUDES = "./data/Solicitudes_Alcaldes.csv";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de las colisiones por Boroughs.
	 */
	public final static String ARCHIVO_BOROUGHS = "./data/Boroughs.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de las colisiones por Ward.
	 */
	public final static String ARCHIVO_WARD = "./data/Ward.json";

	/**
	 * Separador de columnas de archivos CSV
	 */
	public final static String SEPARADOR = ";";
	
	/**
	 * Linea continua para imprimir el parqueadero
	 */
	public final static String LINEA_CONTINUA = "____________________________________________________";

	/**
	 * Linea punteada para imprimir el parqueadero
	 */
	public final static String LINEA_PUNTEADA = "- - - - - - - - - - - - - - - - - - - - - - - - - - ";

	/**
	 * Division parqueaderos 
	 */
	public final static String DIVISIONES = "||        ||        ||        ||        ||        ||";
	// -------------------------------------------------------------
	// Atrinutos
	// -------------------------------------------------------------

	/**
	 * Ordenamiento quick sort
	 */
	private QuickSort<Anho> qS;

	/**
	 * Ordenamiento híbrido (quick con insertion)
	 */
	private Hybrid<Anho> h;

	//Punto A
	/**
	 * Lista con las autoridades locales.
	 */
	private ListaSencillamenteEncadenada<LocalAuthority> localAuthoritiesA;

	/**
	 * Lista con los registros de colisiones por año que tienen más de cuarenta slight
	 */
	private ListaSencillamenteEncadenada<Anho> encimaDe40;

	/**
	 * Cola con las solicitudes realizadas por los alcaldes.
	 */
	private Cola<Solicitud> solicitudes;

	//Punto B
	/**
	 * Lista con las areas.
	 */
	private ListaSencillamenteEncadenada<Area> areas;

	/**
	 * Lista con los registros de colisiones por año que tienen más de cien serious
	 */
	private ListaSencillamenteEncadenada<Anho> porEncima100;


	//Punto C

	/**
	 * Cola de carros en orden de entrada.
	 */
	private Cola<Carro> colaEntrada;

	/**
	 * Pilas de carros parqueaderos 1, 2, 3, 4 y 5
	 */
	private Pila<Carro> pila1;
	private Pila<Carro> pila2;
	private Pila<Carro> pila3;
	private Pila<Carro> pila4;
	private Pila<Carro> pila5;

	/**
	 * Arreglo con las pilas de carros
	 */
	private Pila<Carro>[] pilas = new Pila[5];

	/**
	 * Pila de carros parqueadero temporal:
	 * Aca se estacionan los carros temporalmente cuando se quiere
	 * sacar un carro de un parqueadero y no es posible sacarlo con un solo movimiento
	 */
	private Pila<Carro> pilaTemporal;

	// -------------------------------------------------------------
	// Constructor
	// -------------------------------------------------------------

	/**
	 * Construye un nuevo sistema Smartcities.<br>
	 * <b> post: </b> El sistema de smartCities esta inicializado
	 */

	public SmartCities(){
		h = new Hybrid<>();
		qS = new QuickSort();
		solicitudes = new Cola<>();
		importarCSV(ARCHIVO_SOLICITUDES);
		encimaDe40 = new ListaSencillamenteEncadenada<>();
		localAuthoritiesA = new ListaSencillamenteEncadenada<>();
		leerArchivoA(ARCHIVO_WARD);
		areas = new ListaSencillamenteEncadenada<>();
		porEncima100 = new ListaSencillamenteEncadenada<>();

		colaEntrada = new Cola<Carro>();

		pila1 = new Pila<Carro>();
		pila2 = new Pila<Carro>();
		pila3 = new Pila<Carro>();
		pila4 = new Pila<Carro>();
		pila5 = new Pila<Carro>();
		pilas[0]=pila1;
		pilas[1]=pila2;
		pilas[2]=pila3;
		pilas[3]=pila4;
		pilas[4]=pila5;
		pilaTemporal = new Pila<Carro>();
	}

	/**
	 * Retorna la lista de a�os por encima de 40
	 */
	public ListaSencillamenteEncadenada<Anho> darEncimaDe40()
	{
		return encimaDe40;
	}
	/**
	 * Retorna la lista de anos por encima de 10
	 */
	public ListaSencillamenteEncadenada<Anho> darPorEncimaDe100()
	{
		return porEncima100;
	}
	/**
	 * Prueba la lectura del archivo CSV
	 */
	public void probarLecturaCSV(){
		System.out.println("Ingreso al parqueadero:");
		importarCSV(ARCHIVO_INGRESO);
		System.out.println();
		System.out.println("Salida del parqueadero:");
		importarCSV(ARCHIVO_SALIDA);
		System.out.println();
		System.out.println("Solicitudes alcaldes:");
		importarCSV(ARCHIVO_SOLICITUDES);
	}


	/**
	 * Importa la informaci�n del archivo que llega por par�metro
	 * post:	Se agregaron los datos del archivo al sistema
	 * @param ruta la ruta donde se encuentra el archivo. ruta != null
	 */
	public void importarCSV(String ruta)
	{
		try
		{
			File f = new File (ruta);

			BufferedReader in = new BufferedReader ( new FileReader( f ) );
			String linea = in.readLine( );

			switch (ruta){
			case ARCHIVO_INGRESO:
				linea = in.readLine();
				int pos1 = 0;
				while (linea!=null)
				{
					pos1++;
					String[] partes = linea.split( SEPARADOR );
					registrarCarro(partes[3], partes[2], partes[0].substring(1), pos1 );
					linea=in.readLine( );
				}
				break;
			case ARCHIVO_SALIDA:
				linea = in.readLine();
				int pos = 0;
				while (linea!=null)
				{
					pos++;
					String[] partes = linea.split( SEPARADOR );
					ListaSencillamenteEncadenada<Carro>.IteradorSencillo<Carro> ite = colaEntrada.darIteradorSencillo();
					boolean encontro = false;
					Carro actual = null;
					while(ite.hasNext() && !encontro)
					{
						actual = ite.next();
						String nom = partes[2];
						if(actual.darIdentificador().equals(nom))
						{
							actual.asignarPosicionSalida(pos);
							encontro = true;
						}
					}
					linea=in.readLine( );
				}
				break;
			case ARCHIVO_SOLICITUDES:
				String[] partesA = linea.split( SEPARADOR );
				linea = in.readLine();
				while (linea!=null)
				{
					String[] partes = linea.split( SEPARADOR );
					Solicitud nuevaSolicitud = new Solicitud(partes[0], partes[1]);
					solicitudes.add(nuevaSolicitud);
					linea=in.readLine( );
				}
				break;
			}
			in.close( );
		}
		catch (Exception e)
		{
			e.printStackTrace( );
		}
	}

	/**
	 * Importa la informaci�n del archivo que llega por par�metro
	 * post:	Se agregaron los datos del archivo al sistema
	 * @param ruta la ruta donde se encuentra el archivo. ruta != null
	 */
	public void leerArchivoA(String ruta) 
	{
		File f = new File(ruta);
		try {
			JSONTokener tkn = new JSONTokener(new FileReader(f));
			JSONArray arr = new JSONArray(tkn);

			for(int i = 0; i < arr.length(); i++)
			{
				JSONObject actual = arr.getJSONObject(i);
				JSONArray info = actual.names();
				String localAuthority = actual.getString("Local Authority");
				String nombreWard = actual.getString("Ward name");
				Ward nuevoWard = new Ward(nombreWard, localAuthority);
				for(int j = 0; j< info.length(); j++)
				{
					String txt = info.getString(j);
					if(txt.startsWith("Slight")){
						int anho = Integer.parseInt(txt.substring(txt.length()-4,txt.length()));
						int slight = (int) actual.get("Slight "+ anho);
						int fatal = actual.getInt("Fatal "+ anho);
						int serious = actual.getInt("Serious "+ anho);
						int total = actual.getInt("Total "+ anho);

						Anho nuevoAnho = new Anho(anho, slight, fatal, serious, total, nombreWard, localAuthority);
						nuevoWard.agregarAnho(nuevoAnho);

						if(slight >= 40)
						{
							encimaDe40.add(nuevoAnho);
						}
					}
				}
				ListaSencillamenteEncadenada<LocalAuthority>.IteradorSencillo<LocalAuthority> it = localAuthoritiesA.darIteradorSencillo();
				boolean encontro = false;
				LocalAuthority nuevo = null;

				while(it.hasNext() && !encontro)
				{
					nuevo = it.next();

					if(nuevo.darIdentificador().equals(localAuthority)){
						nuevo.agregarWard(nuevoWard);
						encontro = true;
					}
				}
				if(!encontro){
					nuevo = new LocalAuthority(localAuthority);
					nuevo.agregarWard(nuevoWard);
					localAuthoritiesA.add(nuevo);
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Importa la informaci�n del archivo que llega por par�metro
	 * post:	Se agregaron los datos del archivo al sistema
	 * @param ruta la ruta donde se encuentra el archivo. ruta != null
	 */
	public void leerArchivoB(String ruta)
	{
		File f = new File(ruta);
		try {
			JSONTokener tkn = new JSONTokener(new FileReader(f));
			JSONArray arr = new JSONArray(tkn);

			for(int i = 0; i < arr.length(); i++)
			{
				JSONObject actual = arr.getJSONObject(i);
				JSONArray info = actual.names();
				String nombreArea = actual.getString("Area");
				Area nuevaArea = new Area (nombreArea);
				for(int j = 0; j< info.length(); j++)
				{
					String tipoColision = info.getString(j);
					if(tipoColision.startsWith("Serious")){
						int anho = Integer.parseInt(tipoColision.substring(tipoColision.length()-4,tipoColision.length()));
						double slight = actual.getDouble("Slight "+ anho);
						int cSlight = (int) (slight * 1000);
						int fatal = actual.getInt("Fatal "+anho);
						int serious = actual.getInt("Serious "+ anho);
						int total = actual.getInt("Total "+ anho);

						Anho nuevoAnho = new Anho(anho, cSlight, fatal, serious, total, nombreArea);
						nuevaArea.agregarAnho(nuevoAnho);

						if(serious > 100)
						{
							porEncima100.add(nuevoAnho);
						}
					}
				}
				areas.add(nuevaArea);
			}
			qS.sortB(porEncima100);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Genera un reporte, por autoridad local, que contenga todas las colisiones Slight que estén por encima del máximo permitido (40 al año).
	 */
	public void generarReporte1A(){
		Anho actual = null;
		String txt = "";
		qS.sort(encimaDe40);

		ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> it = encimaDe40.darIteradorSencillo();
		String laActual = "";
		while(it.hasNext()){
			actual = it.next();
			if (laActual.equals("")){
				laActual = actual.darNombreLocalAuthority();
				txt += "Local Authority: " + laActual + "\n";
			}
			else if (!actual.darNombreLocalAuthority().equals(laActual)){
				laActual = actual.darNombreLocalAuthority();
				txt += "\n \n Local Authority: " + laActual + "\n";
			}
			txt += "\n Ward: " + actual.darNombreWard(); 
			txt += "\n Cantidad accidentes slight " + actual.darAnho() + ": " + actual.darCantidadSlight();
		}
		System.out.println(txt);

	}

	/**
	 * Llama el método de generar reporte 1A para cada local authority que realizó una solicitud.
	 * @param inf Limite inferior rango fechas
	 * @param sup Limite superior rango fechas
	 */
	public void asignarLAReporte2(int inf, int sup){
		String resp = "";
		ListaSencillamenteEncadenada<Solicitud>.IteradorSencillo<Solicitud> i = solicitudes.darIteradorSencillo();
		while (i.hasNext()){
			Solicitud actual = i.next();
			resp += actual.darIdentificador() + ": " + generarReporte2A(inf, sup, actual.darIdentificador());
		}
		System.out.println(resp);
	}

	/**
	 * Imprime la informaci�n del barrio con el �ndice de colisiones slight m�s alto en un rango de a�os
	 * @param inf Limite inferior del rango de fechas
	 * @param sup Limite superior del rango de fechas
	 * @oaram pLocalAuthority nombre de la autoridad local en la cual se buscará el barrio.
	 */
	public String generarReporte2A(int inf, int sup, String pLocalAuthority){
		Ward masSlight = null;
		int cantMas = 0;
		ListaSencillamenteEncadenada<LocalAuthority>.IteradorSencillo<LocalAuthority> itL = localAuthoritiesA.darIteradorSencillo();
		boolean encontro = false;
		while(itL.hasNext() && !encontro){
			LocalAuthority actual = itL.next();
			if (actual.darIdentificador().equals(pLocalAuthority)){
				encontro = true;
				ListaSencillamenteEncadenada<Ward>.IteradorSencillo<Ward> itW = actual.darListaWards().darIteradorSencillo();
				while (itW.hasNext()){
					Ward wActual = itW.next();
					int cantActual = 0;

					ListaSencillamenteEncadenada<Anho> anhos = wActual.darAnhos();
					h.sort(anhos);
					ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> itA = anhos.darIteradorSencillo();
					boolean acabo = false;
					while (itA.hasNext() && !acabo){
						Anho aActual = itA.next();
						if (aActual.darAnho()>sup){
							acabo = true;
						}
						cantActual+=aActual.darCantidadSlight();
					}
					if (cantActual>cantMas){
						cantMas = cantActual;
						masSlight = wActual;
					}
				}
			}
		}
		return "Ward con m�s colisiones slight: " + masSlight.darIdentificador() + "(" + cantMas +") \n";
	}

	/**
	 * Imprime el historial de colisiones slight para el barrio dado
	 * @param pNombreWard Nombre del barrio sobre el que se hara la consulta
	 */
	public void generarReporte3A(String pNombreWard){
		String resp = "";
		ListaSencillamenteEncadenada<Solicitud>.IteradorSencillo<Solicitud> i = solicitudes.darIteradorSencillo();
		while (i.hasNext()){
			Solicitud actual = i.next();
			ListaSencillamenteEncadenada<LocalAuthority>.IteradorSencillo<LocalAuthority> itL = localAuthoritiesA.darIteradorSencillo();
			while (itL.hasNext()){
				LocalAuthority lActual = itL.next();
				if (lActual.darIdentificador().equals(actual.darIdentificador())){
					ListaSencillamenteEncadenada<Ward>.IteradorSencillo<Ward> itW = lActual.darListaWards().darIteradorSencillo();
					while (itW.hasNext()){
						Ward wActual = itW.next();
						if (wActual.darIdentificador().equals(pNombreWard)){
							resp += "Colisiones slight por a�o: \n";
							ListaSencillamenteEncadenada<Anho> anhos = wActual.darAnhos();
							h.sort(anhos);
							ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> itA = anhos.darIteradorSencillo();
							while (itA.hasNext()){
								Anho aActual = itA.next();
								resp += aActual.darInfoSlight() + "\n";
							}
						}
					}
				}
			}
		}
		if (resp.equals("")){
			resp = "No existe un barrio con el nombre dado en las autoridades locales que realizaron solicitudes.";
		}
		System.out.println(resp);
	}

	/**
	 * Calcula el promedio de colisiones slight para un barrio dado.
	 * @param pNombreWard Barrio sobre el cual se realizará la consulta.
	 */
	public void generarReporte4A(String pNombreWard){
		String resp = "";
		int cant = 0;
		int tot = 0;
		ListaSencillamenteEncadenada<Solicitud>.IteradorSencillo<Solicitud> i = solicitudes.darIteradorSencillo();
		while (i.hasNext()){
			Solicitud actual = i.next();
			ListaSencillamenteEncadenada<LocalAuthority>.IteradorSencillo<LocalAuthority> itL = localAuthoritiesA.darIteradorSencillo();
			while (itL.hasNext()){
				LocalAuthority lActual = itL.next();
				if (lActual.darIdentificador().equals(actual.darIdentificador())){
					ListaSencillamenteEncadenada<Ward>.IteradorSencillo<Ward> itW = lActual.darListaWards().darIteradorSencillo();
					while (itW.hasNext()){
						Ward wActual = itW.next();
						if (wActual.darIdentificador().equals(pNombreWard)){
							ListaSencillamenteEncadenada<Anho> anhos = wActual.darAnhos();
							h.sort(anhos);
							tot = anhos.size();
							ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> itA = anhos.darIteradorSencillo();
							while (itA.hasNext()){
								Anho aActual = itA.next();
								cant += aActual.darCantidadSlight();
							}
						}
					}
				}
			}
		}
		if (cant==0){
			resp = "No existe un barrio con el nombre dado en las autoridades locales que realizaron solicitudes.";
		}
		else{
			resp = "La cantidad promedio de accidentes slight en el barrio " + pNombreWard + " es: " + cant/tot;
		}
		System.out.println(resp);
	}

	/**
	 * Busca el valor más reciente de colisiones slight para el barrio dado.
	 * @param pNombreWard Barrio sobre el cual se realizará la consulta.
	 */
	public void generarReporte5A(String pNombreWard){
		String resp = "";
		ListaSencillamenteEncadenada<Solicitud>.IteradorSencillo<Solicitud> i = solicitudes.darIteradorSencillo();
		while (i.hasNext()){
			Solicitud actual = i.next();
			ListaSencillamenteEncadenada<LocalAuthority>.IteradorSencillo<LocalAuthority> itL = localAuthoritiesA.darIteradorSencillo();
			while (itL.hasNext()){
				LocalAuthority lActual = itL.next();
				if (lActual.darIdentificador().equals(actual.darIdentificador())){
					ListaSencillamenteEncadenada<Ward>.IteradorSencillo<Ward> itW = lActual.darListaWards().darIteradorSencillo();
					while (itW.hasNext()){
						Ward wActual = itW.next();
						if (wActual.darIdentificador().equals(pNombreWard)){
							resp += "Entrada colisiones slight m�s reciente: \n";
							ListaSencillamenteEncadenada<Anho> anhos = wActual.darAnhos();
							h.sort(anhos);
							Anho ultimo = anhos.get((anhos.size()-1));
							resp += ultimo.darInfoSlight() + " colisiones.";
						}
					}
				}
			}
		}
		if (resp.equals("")){
			resp = "No existe un barrio con el nombre dado en las autoridades locales que realizaron solicitudes.";
		}
		System.out.println(resp);
	}
	/**
	 * Genera un reporte por area que contenga todas las colisiones serious
	 */
	
	public void darReporteParte1B()
	{
		qS.sortB(porEncima100);
		System.out.println("Serious collisions by cities");
		ListaSencillamenteEncadenada<Anho>.IteradorSencillo<Anho> it = porEncima100.darIteradorSencillo();
		Anho actual = null;
		String areaAct = null;
		while(it.hasNext())
		{
			actual = it.next();
			if(areaAct != null)
			{
				if(areaAct.equalsIgnoreCase(actual.darArea()))
				{
					System.out.println("Colisiones Serious " + actual.darIdentificador() + " :" + actual.darCantidadSerious());			
				}
				else
				{	
					areaAct = actual.darArea();
					System.out.println("Area: " + actual.darArea());
					System.out.println("Colisiones Serious " + actual.darIdentificador() + " :" + actual.darCantidadSerious());
				}
			}
			else
			{
				areaAct = actual.darArea();
				System.out.println("Area: " + actual.darArea());
				System.out.println("Colisiones Serious " + actual.darIdentificador() + " :" + actual.darCantidadSerious());
			}
		}
	}
	
	/**
	 * Genera un reporte con el historial de colisiones del area que entra por parametro
	 * @param area Area del que se quiere conocer el historial
	 */
	public void darReporteParte3B()
	{
		if(solicitudes.size() > 0)
		{
			Solicitud solAct = solicitudes.dequeue();
			System.out.println("Solicitud realizada por: " + solAct.darMayor().substring(1));
			historialSerious(solAct.darIdentificador());
		}
		else
		{
			System.out.println("No hay solicitudes en cola");
		}
	}
	/**
	 * Genera un reporte con el promedio de colisiones del area que entra por parametro
	 * @param area Area del que se quiere conocer el promedio
	 */
	public void darReporteParte4B()
	{
		if(solicitudes.size() > 0)
		{
			Solicitud solAct = solicitudes.dequeue();
			System.out.println("Solicitud realizada por: " + solAct.darMayor().substring(1));
			promedioColisionesSeriousArea(solAct.darIdentificador());
		}
		else
		{
			System.out.println("No hay solicitudes en cola");
		}
	}
	/**
	 * Genera un reporte con la ultima cantidad de colisiones serious del area que entra por parametro
	 * @param area Area del que se quiere conocer el ultimo reporte
	 */
	public void darReporteParte5B()
	{
		if(solicitudes.size() > 0)
		{
			Solicitud solAct = solicitudes.dequeue();
			System.out.println("Solicitud realizada por " + solAct.darMayor().substring(1));
			valorSeriousMasReciente(solAct.darIdentificador());
		}
		else
		{
			System.out.println("No hay solicitudes en cola");
		}
	}
	/**
	 * Retorna el nombre del area que tiene la cantidad de colisiones Serious mas alta y su valor en el rango de a�os que entran como parametro
	 * @param anhoMenor Limite inferior del rango
	 * @param anhoMayor Limite superior del rango
	 * @return El nombre del area con la cantidad de colisiones en el rango de a�os
	 */
	public void areaIndiceSeriousMasAlto(int anhoMenor, int anhoMayor)
	{
		int mayor =0;
		Area resp = null;
		Area actual = null;
		ListaSencillamenteEncadenada<Area>.IteradorSencillo<Area> ite = areas.darIteradorSencillo();
		while (ite.hasNext())
		{
			actual = ite.next();
			if(actual.indiceColisionesSerious(anhoMenor, anhoMayor) > mayor)
			{
				mayor = actual.indiceColisionesSerious(anhoMenor, anhoMayor);
				resp = actual;
			}
		}
		System.out.println("El area " + resp.darIdentificador() + " tiene el indice de colisiones Serious: " + mayor);
	}
	/**
	 * Imprime en la consola el historial de colisiones serious del area que entra por parametro
	 * @param nomArea Nombre del area del cual se quiere imprimir el historial de colisiones
	 */
	public void historialSerious(String nomArea)
	{
		Area actual = null;
		ListaSencillamenteEncadenada<Area>.IteradorSencillo<Area> ite = areas.darIteradorSencillo();
		boolean encontro = false;
		while(ite.hasNext() && !encontro)
		{
			actual = ite.next();
			if(actual.darIdentificador().equalsIgnoreCase(nomArea))
			{
				System.out.println(actual.historialColisionesSerious());
				encontro = true;
			}
		}
		if(!encontro)
			System.out.println("El area no existe.");
	}
	
	/**
	 * Imprime en la consola el promedio de colisiones serious del area que entra por parametro	
	 * @param nomArea Nombre del area del cual se quiere conocer el promedio 
	 */

	public void promedioColisionesSeriousArea (String nomArea)
	{
		Area actual = null;
		ListaSencillamenteEncadenada<Area>.IteradorSencillo<Area> ite = areas.darIteradorSencillo();
		boolean encontro = false;
		while(ite.hasNext() && !encontro)
		{
			actual = ite.next();
			if(actual.darIdentificador().equalsIgnoreCase(nomArea))
			{ 
				System.out.println("El promedio de colisiones del area " + actual.darIdentificador() + " es: " + actual.promedioColisionesSerious());
				encontro = true;
			}
		}
		if(!encontro)
			System.out.println("No existe el area ingresada");
	}
	
	/**
	 * Imprime en consola el valor de colisiones serious mas reciente del area que entra por parametro
	 * @param nomArea Nombre del area del cual se quiere conocer la cantidad mas reciente
	 */

	public void valorSeriousMasReciente(String nomArea)
	{
		Area actual = null;
		ListaSencillamenteEncadenada<Area>.IteradorSencillo<Area> ite = areas.darIteradorSencillo();
		boolean encontro = false;
		while(ite.hasNext() && !encontro)
		{
			actual = ite.next();
			if(actual.darIdentificador().equalsIgnoreCase(nomArea))
			{ 
				System.out.println(actual.darColisionSeriousMasReciente());
				encontro = true;
			}
		}
		if(!encontro)
			System.out.println("No existe el area ingresada");
	}
	/**
	 * 
	 */
	// PUNTO C

	/**
	 * Imprime una representación gráfica de la cola de carros esperando ser parqueados.
	 * @return cola de carros
	 */
	public void imprimirCola(){

		ListaSencillamenteEncadenada<Carro>.IteradorSencillo<Carro> i = colaEntrada.darIteradorSencillo();
		String representacion = "Cola carros en espera: ";

		while (i.hasNext()){
			representacion += ((Carro) i.next()).darMatricula() + "  ";
		}
		System.out.println(representacion);
	}

	/**
	 * Imprime en consola el parqueadero luego de que todos los alcaldes hayan llegado
	 */
	public void imprimirPilas(){

		String[] linea = new String[4];
		linea[0] ="||  ";
		linea[1] ="||  ";
		linea[2] ="||  ";
		linea[3] ="||  ";
		for (int i=0; i<pilas.length; i++)
		{
			ListaSencillamenteEncadenada<Carro>.IteradorSencillo<Carro> it = pilas[i].darIteradorSencillo();
			if(pilas[i].size() == 4)
			{for(int j = linea.length-1 ; j >= 0 ; j--)
			{
				if(it.hasNext())
				{
					Carro act = it.next();
					String posicion = String.valueOf(act.darPosicionEntrada());
					if(posicion.length() == 1)
						posicion = " " + posicion;
					linea[j]+= posicion +  act.darInicialesNombre() + "  ||  ";
				}
				else
				{
					linea[j] += "      ||";
				}
			}
			}
			else if(pilas[i].size()== 3)
			{
				for(int j = linea.length-2 ; j >= 0 ; j--)
				{
					if(it.hasNext())
					{
						Carro act = it.next();
						String posicion = String.valueOf(act.darPosicionEntrada());
						if(posicion.length() == 1)
							posicion = " " + posicion;
						linea[j]+= posicion +  act.darInicialesNombre() + "  ||  ";
					}
					else
					{
						linea[j] += "      ||";
					}
				}
				linea[linea.length-1] += "      ||";
			}
			else if(pilas[i].size() == 2)
			{
				for(int j = linea.length-3 ; j >= 0 ; j--)
				{
					if(it.hasNext())
					{
						Carro act = it.next();
						String posicion = String.valueOf(act.darPosicionEntrada());
						if(posicion.length() == 1)
							posicion = " " + posicion;
						linea[j]+= posicion +  act.darInicialesNombre() + "  ||  ";
					}
					else
					{
						linea[j] += "      ||";
					}
				}
				linea[linea.length-2] += "      ||";
				linea[linea.length-1] += "      ||";
			}
			else if(pilas[i].size() == 1)
			{
				linea[linea.length-3] += "      ||";
				linea[linea.length-2] += "      ||";
				linea[linea.length-1] += "      ||";
				Carro act = it.next();
				String posicion = String.valueOf(act.darPosicionEntrada());
				if(posicion.length() == 1)
					posicion = " " + posicion;
				linea[linea.length-4] += posicion +  act.darInicialesNombre() + "  ||  ";
			}
		}
		System.out.println(LINEA_CONTINUA);
		System.out.println(LINEA_CONTINUA);
		System.out.println(DIVISIONES);
		System.out.println(LINEA_PUNTEADA);
		System.out.println(DIVISIONES);
		System.out.println(linea[0]);
		System.out.println(DIVISIONES);
		System.out.println(LINEA_PUNTEADA);
		System.out.println(DIVISIONES);
		System.out.println(linea[1]);
		System.out.println(DIVISIONES);
		System.out.println(LINEA_PUNTEADA);
		System.out.println(DIVISIONES);
		System.out.println(linea[2]);
		System.out.println(DIVISIONES);
		System.out.println(LINEA_PUNTEADA);
		System.out.println(DIVISIONES);
		System.out.println(linea[3]);
		System.out.println(DIVISIONES);
		System.out.println(LINEA_PUNTEADA);
		System.out.println(DIVISIONES);
	}

	/**
	 * Registra el carro que quiere ingresar al parqueadero y el vehiculo ingresa a la cola de carros pendientes por parquear
	 * @param pColor color del vehiculo
	 * @param pMatricula matricula del vehiculo
	 * @param pNombreConductor nombre de quien conduce el vehiculo
	 */
	public void registrarCarro (String pColor, String pMatricula, String pNombreConductor, int pos)
	{
		Carro nuevo = new Carro(pColor, pMatricula, pNombreConductor, pos);
		colaEntrada.enqueue(nuevo);
	}    

	/**
	 * Busca un parqueadero con cupo dentro de los 3 existentes y parquea el carro
	 * @param aParquear es el carro que se saca de la cola de carros que estan esperando para ser parqueados
	 */
	public void parquearCarro(Carro aParquear) 
	{	
		boolean espacio = false;
		for(int i = 0; i<pilas.length && !espacio; i++ )
		{
			if(pilas[i].size() < 4)
			{
				pilas[i].push(aParquear);
				espacio = true;
			}
		}	
	}
	/**
	 * Parquea todos los carros que estan en la cola de espera
	 * @return Cantidad de movimientos que realizo para parquear todos los carros en orden
	 */
	public int parquearCarros()
	{
		int posicion = colaEntrada.size();
		int movimientos = 0;
		while(colaEntrada.size() > 0 && posicion>0)
		{
			Carro act = colaEntrada.dequeue();
			movimientos++;
			if(act.darPosicionSalida() == posicion)
			{
				parquearCarro(act);
				posicion--;
			}
			else
			{
				colaEntrada.enqueue(act);
				movimientos ++;
			}
		}
		return movimientos;
	}
	/**
	 * Saca los carros del parqueadero, iterando sobre las pilas 
	 * @return Cantidad de movimientos que realizo para sacar los carros
	 */
	public int sacarCarros ()
	{
		int movimientos = 0;
		for(int i =0; i < pilas.length ; i ++)
		{
			while(pilas[i].size() > 0)
			{
				pilas[i].pop();
				movimientos ++;
			}
		}
		return movimientos;
	}
}
