package estructuras.test;

import smartcities.Anho;
import smartcities.SmartCities;
import estructuras.ListaSencillamenteEncadenada;
import junit.framework.TestCase;

public class JsonTest extends TestCase{

	protected ListaSencillamenteEncadenada<Anho> lista = new ListaSencillamenteEncadenada<>();
	SmartCities s = new SmartCities();


	public void testLeerA(){
		s.leerArchivoA(SmartCities.ARCHIVO_WARD);
		lista = s.darEncimaDe40();

		Anho a1 = new Anho(2010, 40, 1, 4, 45, "Abbey", "Barking and Dagenham");
		Anho a2 = new Anho(2014, 41, 0, 2, 43, "Abbey", "Barking and Dagenham");
		Anho a3 = new Anho(2011, 42, 0, 5, 47, "Abbey", "Merton");

		assertEquals(a1.darIdentificador(), lista.removerPrimero().darIdentificador());
		assertEquals(a2.darIdentificador(), lista.removerPrimero().darIdentificador());
		assertEquals(a3.darIdentificador(), lista.removerPrimero().darIdentificador());
	}
	
	public void testLeerB()
	{
		s.leerArchivoB(SmartCities.ARCHIVO_BOROUGHS);
		lista = s.darPorEncimaDe100();
		
		Anho a1 = new Anho(1999, 1343, 15, 220, 1578, "Barnet");
		Anho a2 = new Anho(2000, 1326, 8, 217, 1551, "Barnet");
		Anho a3 = new Anho(2001, 1249, 11, 210, 147, "Barnet");
		
		assertEquals(a1.darInfo(), lista.removerPrimero().darInfo());
		assertEquals(a2.darInfo(), lista.removerPrimero().darInfo());
		assertEquals(a3.darInfo(), lista.removerPrimero().darInfo());
	}
}
