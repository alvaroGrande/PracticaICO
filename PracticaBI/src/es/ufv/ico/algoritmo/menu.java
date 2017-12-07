package es.ufv.ico.algoritmo;

import java.util.Scanner;

public class menu {
	private  int[][] matriz_inicial= new int [3][3];
	private static int matriz_final[][] = new int[3][3];
	private static String metodo;
	private static String heuristica;
	private int[][] matriz;
		public menu () {
			//System.out.println("aa");
			inicio();
			
		}
		public void inicio() {
		pintarMenu();
		Scanner scanner = new Scanner(System.in);
		
		int[] valoresIntroducidos;
		//int opcion = scanner.nextInt();
//		switch (opcion) {
//		case 1:
			System.out.println("Deber introduciar cada numero de cada fila separada por un espacio");
			guardarMatriz();
			
			if(comprobarMatriz(matriz_inicial))
				pintarMatriz(matriz_inicial);
				
//			break;
//		case 2:
//			System.out.println("Deber introduciar cada numero de cada fila separada por un espacio");
//			guardarMatriz();
//			pintarMatriz(matriz_inicial);
//			break;
//		case 3:
//			System.out.println("Deber introduciar cada numero de cada fila separada por un espacio");
//			guardarMatriz();
//
//		default:
//			System.out.println("No ha elegido una opcion valida");
//			break;
//		}
		

	}
	
	public  void guardarMatriz() {

		for(int i=0; i<3; i++) {
			String fila = leeMatriz();

			String[] row =fila.split(" ");
			for(int j=0;j<row.length;j++) {
			matriz_inicial[i][j] = Integer.parseInt(row[j]);
			matriz_inicial[i][j] = Integer.parseInt(row[j]);
			matriz_inicial[i][j] = Integer.parseInt(row[j]);
			}
			if(row[0] == row[1] || row[0] == row[2] || row[1] == row[2]) {
				System.out.println("Lo numero deben ser distintos");
			}

		}
		
	}

	public    boolean comprobarMatriz(int[][] matriz) {
		boolean num = true;
		for(int i= 1; i<3; i++) {
			for(int j= 1; j<3; j++) {

				if(matriz[i-1][j-1] == matriz[i][j])
				{ num = false;}
					
				
			}
		}
		return num;

	}

	public    String pintarMenu() {
		System.out.println("Seleccione una opcion\n");
		System.out.println("1. A*");
		System.out.println("2. Greedy");
		System.out.println("3. Avara");
		metodo = leeMatriz();
		System.out.println("Metodo escogido \n" + metodo);
		if(metodo.equals("1") || metodo.equals("3")) {
		System.out.println("Seleccione una heuristica\n");
		heuristica=leeMatriz();
		System.out.println("Heuristica escogida \n" + heuristica);

		}
		
		return metodo;

	}

	private static String  leeMatriz() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public int[][] pintarMatriz(int[][] m) {
		System.out.println("Puzzle Inicial  : "  );

		System.out.println("/------------------/");
		System.out.println(m);
		for(int i= 0; i<3; i++) {
			for(int j= 0; j<3; j++) {
				System.out.print(" | ");
				System.out.print(m[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
		}
		System.out.println("/------------------/");
		matriz_inicial=m;

		setMatriz(matriz_inicial);
		return matriz;
	}
	
	public int[][] getMatriz() {
		return matriz_inicial;
	}

	public void setMatriz(int[][] matriz_inicial) {
		this.matriz_inicial = matriz_inicial;
	}
	public static String getMetodo() {
		return metodo;
	}
	public static void setMetodo(String metodo) {
		menu.metodo = metodo;
	}
	public static String getHeuristica() {
		return heuristica;
	}
	public static void setHeuristica(String heuristica) {
		menu.heuristica = heuristica;
	}

	}
