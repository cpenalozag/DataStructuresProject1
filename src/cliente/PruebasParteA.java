package cliente;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import smartcities.SmartCities;

public class PruebasParteA {
	
	BufferedWriter escritor;
	Scanner lector;
	
	//TODO: Declarar objetos de la parte A
	SmartCities principal;
	
	public PruebasParteA(BufferedWriter escritor, Scanner lector) {
		this.escritor = escritor;
		this.lector = lector;
	}
	
	public void pruebas() {
		int opcion = -1;
		
		//TODO: Inicializar objetos de la parte A
		principal = new SmartCities();
		
		long tiempoDeCarga = System.nanoTime();
		//TODO: Cargar informacion de la parte A
		
		tiempoDeCarga = System.nanoTime() - tiempoDeCarga;
		
		while (opcion != 0) {
			try {
				escritor.write("---------------Pruebas Proyecto A---------------\n");
				escritor.write("Informacion cargada en: " + tiempoDeCarga + " nanosegundos\n");
				escritor.write("Reportes:\n");
				escritor.write("1: Generar reporte de autoridades locales con mas\n     de 40 colisiones Slight al ano\n");
				escritor.write("2: Barrios en que mas generaron alarmas Slight\n     en un periodo de tiempo\n");
				escritor.write("3: Historial de colisiones Slight para un barrio\n");
				escritor.write("4: Promedio de colisiones Slight para un barrio\n");
				escritor.write("5: Valor mas reciente de colisiones Slight de un\n     barrio\n");
				escritor.write("0: Volver\n");
				escritor.write("------------------------------------------------\n");
				escritor.flush();
				opcion = lector.nextInt();
				
				switch(opcion) {
				case 1: reporte1(); break;
				case 2: reporte2(); break;
				case 3: reporte3(); break;
				case 4: reporte4(); break;
				case 5: reporte5(); break;
				}
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			catch (NumberFormatException nfe) {
				try {
					escritor.write("No ingreso el periodo de tiempo correctamente\n");
					escritor.write("Ingrese cualquier letra y enter para continuar\n");
					escritor.flush();
					lector.nextLine();
					lector.nextLine();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			catch (InputMismatchException ime) {
				try {
					escritor.write("No ingreso un numeral\n");
					escritor.write("Ingrese cualquier letra y enter para continuar\n");
					escritor.flush();
					lector.nextLine();
					lector.nextLine();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	private void reporte1() throws IOException{
		long tiempo = System.nanoTime();
		principal.generarReporte1A();
		
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte2() throws IOException{
		escritor.write("Ingrese un periodo de tiempo con el formato AAAA-AAAA\n");
		escritor.flush();
		lector.nextLine();
		String[] periodo = lector.nextLine().trim().split("-");
		if (periodo.length < 2) {
			throw new NumberFormatException();
		}
		int inicio = Integer.parseInt(periodo[0].trim());
		int fin = Integer.parseInt(periodo[1].trim());
		long tiempo = System.nanoTime();
		//TODO: Generar reporte del barrio que genero mas alarmas Slight en el rango de fechas 'inicio'-'fin'
		principal.asignarLAReporte2(inicio, fin);
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Inicio: " + inicio + "\n");
		escritor.write("fin: " + fin + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte3() throws IOException{
		escritor.write("Ingrese un barrio\n");
		escritor.flush();
		lector.nextLine();
		String barrio = lector.nextLine();
		long tiempo = System.nanoTime();
		//TODO: Generar reporte historial de colisiones Slight para el 'barrio'
		principal.generarReporte3A(barrio);
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Barrio: " + barrio + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte4() throws IOException{
		escritor.write("Ingrese un barrio\n");
		escritor.flush();
		lector.nextLine();
		String barrio = lector.nextLine();
		long tiempo = System.nanoTime();
		//TODO: Generar el promedio de colisiones Slight para el 'barrio'
		principal.generarReporte4A(barrio);
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Barrio: " + barrio + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte5() throws IOException{
		escritor.write("Ingrese un barrio\n");
		escritor.flush();
		lector.nextLine();
		String barrio = lector.nextLine();
		long tiempo = System.nanoTime();
		//TODO: Generar el valor mas reciente (ultimo ano) de colisiones Slight para el 'barrio'
		principal.generarReporte5A(barrio);
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Barrio: " + barrio + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
}