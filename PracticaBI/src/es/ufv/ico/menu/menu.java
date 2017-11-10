package es.ufv.ico.menu;

import java.util.Scanner;

public class menu {
	static int matriz[][] = new int[3][3];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pintarMenu();
		Scanner scanner = new Scanner(System.in);
		
		int[] valoresIntroducidos;
		int opcion = scanner.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Deber introduciar cada numero de cada fila separada por un espacio");
			guardarMatriz();
			if(conprobarMatriz(matriz))
				pintarMatriz(matriz);
			break;
		case 2:
			System.out.println("Deber introduciar cada numero de cada fila separada por un espacio");
			guardarMatriz();
			if(conprobarMatriz(matriz))
				pintarMatriz(matriz);
			break;
		case 3:
			System.out.println("Deber introduciar cada numero de cada fila separada por un espacio");
			guardarMatriz();
			if(conprobarMatriz(matriz))
				pintarMatriz(matriz);

		default:
			System.out.println("No ha elegido una opcion valida");
			break;
		}
		

	}
	
	private static void guardarMatriz() {
		for(int i=1; i<=3; i++) {
			String fila = leeMatriz();
			String[] row = fila.split(" ");
			matriz[i-1][0] = Integer.parseInt(row[0]);
			matriz[i-1][1] = Integer.parseInt(row[1]);
			matriz[i-1][2] = Integer.parseInt(row[2]);
			if(row[0] == row[1] || row[0] == row[2] || row[1] == row[2]) {
				System.out.println("Lo numero deben ser distinto");
			}
		}
		
	}

	private static boolean conprobarMatriz(int[][] matriz) {
		boolean num = true;
		for(int i= 1; i<3; i++) {
			for(int j= 1; j<3; j++) {
				if(matriz[i-1][j-1] == matriz[i][j])
				{ num = false;}
					
				
			}
		}
		return num;

	}

	private static void pintarMenu() {
		System.out.println("Seleccione una opcion\n");
		System.out.println("1. Dijkstra");
		System.out.println("2. Greedy");
		System.out.println("3. A*");
	}

	private static String  leeMatriz() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private static void pintarMatriz(int[][] matriz) {
		System.out.println("/------------------/");
		for(int i= 0; i<3; i++) {
			for(int j= 0; j<3; j++) {
				System.out.print(" | ");
				System.out.print(matriz[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
		}
		System.out.println("/------------------/");
	}

}
