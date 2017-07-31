package cliente;

import java.io.BufferedWriter;
import java.io.IOException;
import java.security.Principal;
import java.util.Scanner;

import smartcities.SmartCities;

public class PruebasParteC {
	
	BufferedWriter escritor;
	Scanner lector;
	SmartCities principal;
	
	public PruebasParteC(BufferedWriter escritor, Scanner lector) {
		this.escritor = escritor;
		this.lector = lector;
	}
	
	public void pruebas() {
		try {
			//TOTO: Inicializar objetos de la parte C
			principal = new SmartCities();
			principal.importarCSV(principal.ARCHIVO_INGRESO);
			principal.importarCSV(principal.ARCHIVO_SALIDA);
			
			int pasosIngreso = 0;
			//TODO: Calcular el numero de pasos necesario para parquear todos los carros
			//		y guardar el numero en la variable 'pasosIngreso'
			pasosIngreso = principal.parquearCarros();
			
			escritor.write("Organizacion del parqueadero luego del ingreso\n     de los alcaldes:\n");
			escritor.flush();
			//TODO: Generar impresion de parqueadero luego del ingreso de todos los alcaldes
			//		(ver ejemplo abajo)
			
			principal.imprimirPilas();
			
			int pasosSalida = 0;
			//TODO: Calcular el numero de pasos necesario para sacar todos los carros
			//		y guardar el numero en la variable 'pasosSalida'
			pasosSalida = principal.sacarCarros();
			
			escritor.write("Numero de pasos ingreso: " + pasosIngreso + "\n");
			escritor.write("Numero de pasos salida: " + pasosSalida + "\n");
			escritor.write("Ingrese cualquier letra y Enter para continuar\n");
			escritor.flush();
			lector.nextLine();
			lector.nextLine();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}

/*
Ejemplo impresion del parqueadero luego del ingreso de todos los alcaldes
En cada puesto aparece el numero de entrada del alcalde junto con sus iniciales
____________________________________________
____________________________________________
| |      ||      ||      ||      ||      | |
| | 1DH  || 5JP  || 9SK  || 13NF || 17DT | |
| |      ||      ||      ||      ||      | |
| | ---- || ---- || ---- || ---- || ---- | |
| |      ||      ||      ||      ||      | |
| | 2MR  || 6PS  || 10KA || 14PD ||      | |
| |      ||      ||      ||      ||      | |
| | ---- || ---- || ---- || ---- || ---- | |
| |      ||      ||      ||      ||      | |
| | 3MS  || 7SB  || 11DB || 15GO ||      | |
| |      ||      ||      ||      ||      | |
| | ---- ||----- || ---- || ---- || ---- | |
| |      ||      ||      ||      ||      | |
| | 4RJ  || 8JA  || 12RW || 16JB ||      | |
| |      ||      ||      ||      ||      | |
| | ---- || ---- || ---- || ---- || ---- | |
| |      ||      ||      ||      ||      | |
*/