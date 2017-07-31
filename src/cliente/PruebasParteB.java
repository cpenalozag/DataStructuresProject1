package cliente;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import smartcities.SmartCities;

public class PruebasParteB {
	
	BufferedWriter escritor;
	Scanner lector;
	//TODO: Declarar objetos de la parte B
	SmartCities principal;
	
	public PruebasParteB(BufferedWriter escritor, Scanner lector) {
		this.escritor = escritor;
		this.lector = lector;
	}
	
	public void pruebas() {
		int opcion = -1;
		
		//TODO: Inicializar objetos de la parte B
		principal = new SmartCities(); 
		
		long tiempoDeCarga = System.nanoTime();
		//TODO: Cargar informacion de la parte B
		
		principal.leerArchivoB(principal.ARCHIVO_BOROUGHS);
		
		tiempoDeCarga = System.nanoTime() - tiempoDeCarga;
		
		while (opcion != 0) {
			try {
				escritor.write("---------------Pruebas Proyecto B---------------\n");
				escritor.write("Informacion cargada en: " + tiempoDeCarga + " nanosegundos\n");
				escritor.write("Reportes:\n");
				escritor.write("1: Generar reporte de colisiones Serious que\n     estan por encima del maximo permitido\n");
				escritor.write("2: Area con el indice de colisiones Serios mas\n     alto en un periodo de tiempo\n");
				escritor.write("3: Historial de colisiones Serios para un area\n");
				escritor.write("4: Promedio de colisiones Serious para un area\n");
				escritor.write("5: Valor mas reciente de colisiones Serious de\n     un area\n");
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
		//TODO: Generar reporte de colisiones Serios que estan por encima del maximo permitido
		
		principal.darReporteParte1B();
		
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
		//TODO: Generar reporte del area con el indice de colisiones Serious mas alto en el rango de fechas 'inicio'-'fin'
		principal.areaIndiceSeriousMasAlto(inicio, fin);
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Inicio: " + inicio + "\n");
		escritor.write("fin: " + fin + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte3() throws IOException{
		escritor.write("Ingrese un area\n");
		escritor.flush();
		lector.nextLine();
		String area = lector.nextLine();
		long tiempo = System.nanoTime();
		//TODO: Generar reporte historial de colisiones Serious para el 'area'
		principal.darReporteParte3B();
		
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Area: " + area + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte4() throws IOException{
		escritor.write("Ingrese un area\n");
		escritor.flush();
		lector.nextLine();
		String area = lector.nextLine();
		long tiempo = System.nanoTime();
		//TODO: Generar el promedio de colisiones Serious para el 'area'
		principal.darReporteParte4B();
		
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Area: " + area + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
	
	private void reporte5() throws IOException{
		escritor.write("Ingrese un area\n");
		escritor.flush();
		lector.nextLine();
		String area = lector.nextLine();
		long tiempo = System.nanoTime();
		//TODO: Generar el valor mas reciente (ultimo ano) de colisiones Serious para el 'area'
		principal.darReporteParte5B();
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Area: " + area + "\n");
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}
}